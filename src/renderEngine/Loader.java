package renderEngine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.EXTTextureFilterAnisotropic;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;

public class Loader 
{
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();
	
	public RawModel loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices)
	{
		int vaoID = createVAO();
		int[] vboIDs = new int[4];
		vboIDs[0] = bindIndicesBuffer(indices);
		vboIDs[1] = storeDataInAttributeList(0, 3, positions);
		vboIDs[2] = storeDataInAttributeList(1, 2, textureCoords);
		vboIDs[3] = storeDataInAttributeList(2, 3, normals);
		unbindVAO();
		return new RawModel(vaoID, indices.length, vboIDs);
	}
	
	//for text
	//returns an int[] where the first entry is the vao and the rest are vbos
	public int[] loadToVAO(float[] positions, float[] textureCoords)
	{
		//TODO store the vbo somewhere so it can be deleted later
		int[] vertexObjects = new int[3];
		vertexObjects[0] = createVAO();
		vertexObjects[1] = storeDataInAttributeList(0, 2, positions);
		vertexObjects[2] = storeDataInAttributeList(1, 2, textureCoords);
		unbindVAO();
		return vertexObjects;
	}
	
	//for water
	public RawModel loadToVAO(float[] positions, int dimensions) 
	{
		int vaoID = createVAO();
		int[] vboIDs = new int[1];
		vboIDs[0] = this.storeDataInAttributeList(0, dimensions, positions);
	    unbindVAO();
	    return new RawModel(vaoID, positions.length / dimensions, vboIDs);
	}
	
	public int loadTexture(String fileName)
	{
		Texture texture = null;
		try 
		{
			//PrintStream originalStream = System.out;
			//PrintStream dummyStream = new PrintStream(
			//								new OutputStream()
			//								{
			//								    public void write(int b) 
			//								    {
			//								        //no output
			//								    }
			//								});
			//System.setOut(dummyStream);
			//texture = TextureLoader.getTexture("PNG", new FileInputStream("res/"+fileName+".png"));
			//System.out.println("Tryin load: '/res/"+fileName+".png'");
			//texture = TextureLoader.getTexture("PNG", Class.class.getResourceAsStream("/res/"+fileName+".png"));
			//texture = TextureLoader.getTexture("PNG", Thread.currentThread().getContextClassLoader().getResourceAsStream("res/"+fileName+".png"));
			texture = TextureLoader.getTexture("PNG", (new FileInputStream("res/"+fileName+".png")));
			//System.setOut(originalStream);
			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D,  GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
			//numbers closer to 0 are more blurred textures, closer to -1 is less blurry
			//set to 0 if using anisotropic filtering
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, 0); //my custom amount = -0.4f; //thin matrix's amount  = -1f;
			if(GLContext.getCapabilities().GL_EXT_texture_filter_anisotropic)
			{
				float amount = Math.min(4f, GL11.glGetFloat(EXTTextureFilterAnisotropic.GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT));
				GL11.glTexParameterf(GL11.GL_TEXTURE_2D, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, amount);
			}
			else
			{
				System.out.println("Antisotropic filtering not supported by graphics driver");
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (NullPointerException e)
		{
			System.err.println("'/res/"+fileName+".png' was null");
		}
		int textureID = texture.getTextureID();
		textures.add(textureID);
		return textureID;
	}
	
	public void cleanUp()
	{
		for(int vao:vaos)
		{
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo:vbos)
		{
			GL15.glDeleteBuffers(vbo);
		}
		for(int texture:textures)
		{
			GL11.glDeleteTextures(texture);
		}
	}
	
	public void printData()
	{
		/*
		for (int vao:vaos)
		{
			System.out.println("vaoID: "+vao);
		}
		for (int vbo:vbos)
		{
			System.out.println("vboID: "+vbo);
		}
		for (int texture:textures)
		{
			System.out.println("texID: "+texture);
		}
		*/
		
		System.out.println("vao size: "+vaos.size());
		System.out.println("vbo size: "+vbos.size());
		System.out.println("tex size: "+textures.size());
		System.out.println();
	}
	
	public void dispErrors()
	{
		int var1 = GL11.glGetError();
		if (var1 == GL11.GL_NO_ERROR)
		{
			System.out.println("No Error Yet");
			return;
		}
		
		String var2 = GLU.gluErrorString(var1);
		System.out.println("########## GL ERROR ##########");
		System.out.println(var1 + ": " + var2);
		
		while ((var1 = GL11.glGetError()) != GL11.GL_NO_ERROR)
		{
			var2 = GLU.gluErrorString(var1);
			System.out.println("########## GL ERROR ##########");
			System.out.println(var1 + ": " + var2);
		}
	}
	
	public void deleteVAO(int vaoID)
	{
		GL30.glDeleteVertexArrays(vaoID);
		vaos.remove((Integer)vaoID);
	}
	
	public void deleteVBO(int vboID)
	{
		GL15.glDeleteBuffers(vboID);
		vbos.remove((Integer)vboID);
	}
	
	public void deleteTexture(int texID)
	{
		GL11.glDeleteTextures(texID);
		textures.remove((Integer)texID);
	}
	
	private int createVAO()
	{
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private int storeDataInAttributeList(int attributeNumber, int coordinateSize, float[] data)
	{
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		return vboID;
	}
	
	private void unbindVAO()
	{
		GL30.glBindVertexArray(0);
	}
	
	private int bindIndicesBuffer(int[] indices)
	{
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		
		return vboID;
	}
	
	private IntBuffer storeDataInIntBuffer(int[] data)
	{
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data)
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public void deleteModel(TexturedModel[] models)
	{
		for (TexturedModel tm : models)
		{
			tm.delete();
		}
	}
}
