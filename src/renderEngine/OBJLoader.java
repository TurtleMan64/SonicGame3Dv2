package renderEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import models.RawModel;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.TextureLoader;

import textures.ModelTexture;

import collision.CollisionModel;
import collision.Triangle3D;

public class OBJLoader 
{
	//old
	/*
	public static RawModel loadObjModel(String fileName, Loader loader)
	{
		FileReader fr = null;
		try 
		{
			fr = new FileReader(new File("res/"+fileName+".obj"));
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println("Couldn't load file!");
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(fr);
		
		String line;
		List<Vector3f> vertices = new ArrayList<Vector3f>();
		List<Vector2f>textures = new ArrayList<Vector2f>();
		List<Vector3f>normals = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();
		float[] verticesArray = null;
		float[] textureArray = null;
		float[] normalsArray = null;
		int[] indicesArray = null;
		
		try
		{
			while(true)
			{
				line = reader.readLine();
				//System.out.print("line = "+line);
				String[] currentLine = line.split(" ");
				//System.out.println(Arrays.toString(currentLine));
				if(line.startsWith("v "))
				{
					Vector3f vertex;
					if(currentLine[1].equals(""))
					{
						vertex = new Vector3f(Float.parseFloat(currentLine[2]), 
								Float.parseFloat(currentLine[3]), 
								Float.parseFloat(currentLine[4]));
					}
					else
					{
						vertex = new Vector3f(Float.parseFloat(currentLine[1]), 
								Float.parseFloat(currentLine[2]), 
								Float.parseFloat(currentLine[3]));
					}
					vertices.add(vertex);
				}
				else
				{
					if(line.startsWith("vt "))
					{
						Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]), 
								Float.parseFloat(currentLine[2]));
						textures.add(texture);
					}
					else
					{
						if(line.startsWith("vn "))
						{
							Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]), 
									Float.parseFloat(currentLine[2]), 
									Float.parseFloat(currentLine[3]));
							normals.add(normal);
						}
						else
						{
							if(line.startsWith("f "))
							{
								textureArray = new float[vertices.size()*2];
								normalsArray = new float[vertices.size()*3];
								break;
							}
						}
					}
				}
			}
		
			while(line!=null)
			{
				if(!line.startsWith("f "))
				{
					line = reader.readLine();
					continue;
				}
				String[] currentLine = line.split(" ");
				String[] vertex1 = currentLine[1].split("/");
				String[] vertex2 = currentLine[2].split("/");
				String[] vertex3 = currentLine[3].split("/");
				
				processVertex(vertex1,indices, textures, normals, textureArray, normalsArray);
				processVertex(vertex2,indices, textures, normals, textureArray, normalsArray);
				processVertex(vertex3,indices, textures, normals, textureArray, normalsArray);
				line = reader.readLine();
			}
			reader.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		verticesArray = new float[vertices.size()*3];
		indicesArray = new int[indices.size()];
		
		int vertexPointer = 0;
		for(Vector3f vertex:vertices)
		{
			verticesArray[vertexPointer++] = vertex.x;
			verticesArray[vertexPointer++] = vertex.y;
			verticesArray[vertexPointer++] = vertex.z;
		}
		
		for(int i = 0; i < indices.size(); i++)
		{
			indicesArray[i] = indices.get(i);
		}
		
		
		
		return loader.loadToVAO(verticesArray, textureArray, normalsArray, indicesArray);
	}
	*/
	private static ArrayList<ModelTexture> modelTextures = new ArrayList<ModelTexture>();
	
	public static ModelTexture[] getModelTexturesCopy()
	{
		ModelTexture[] copyArray = new ModelTexture[modelTextures.size()];
		for(int i = 0; i < modelTextures.size(); i++)
		{
			copyArray[i] = modelTextures.get(i);
		}
		return copyArray;
	}
	
	public static RawModel[] loadObjModels(String fileName, Loader loader)
	{
        //FileReader fr = null;
        FileReader frTexture = null;
        ArrayList<String> textureNamesList = new ArrayList<String>();
        ArrayList<ModelTexture> modelTexturesList = new ArrayList<ModelTexture>();
        
        List<RawModel> models = new ArrayList<RawModel>();
       // try 
        {
            //fr = new FileReader(new File("res/" +fileName +".obj"));
        } 
        //catch (FileNotFoundException e) 
        {
            //System.err.println("Couldn't load file!");
            //e.printStackTrace();
        }
        //InputStreamReader isr = new InputStreamReader(Class.class.getResourceAsStream("/res/"+fileName+".obj"));
        //InputStreamReader isr = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("res/"+fileName+".obj"));
        InputStreamReader isr = null;
		try
		{
			isr = new InputStreamReader(new FileInputStream("res/"+fileName+".obj"));
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
        
        BufferedReader reader = new BufferedReader(isr);
        String line = "";
        List<Vector3f> vertices = new ArrayList<Vector3f>();
        List<Vector2f> textures = new ArrayList<Vector2f>();
        List<Vector3f> normals = new ArrayList<Vector3f>();
        List<Integer> indices = new ArrayList<Integer>();
        float[] verticesArray = null;
        float[] normalsArray = null;
        float[] textureArray = null;
        int[] indicesArray = null;
       
        try
        {
        	line = reader.readLine();
            while(line != null)
            {
            	
                //line = reader.readLine();
                //System.out.println(line);
                String[] currentLine = line.split(" ");
                
                if(line.startsWith("mtllib"))
                {
                	try 
                    {
                		frTexture = new FileReader(new File("res/" +currentLine[1]));
                    } 
                    catch (FileNotFoundException e) 
                    {
                        System.err.println("Couldn't load file!");
                        e.printStackTrace();
                    }
                    BufferedReader readerTexture = new BufferedReader(frTexture);
                    String lineTexture = "";
                    modelTextures.clear();
                    
                    //go through mtl file and put the texture names into array
                    lineTexture = readerTexture.readLine();
                    while(lineTexture != null)
                    {
                    	String[] currentLineTexture = lineTexture.split(" ");
                    	//System.out.println(currentLineTexture[0]);
                    	if(lineTexture.startsWith("newmtl"))
                    	{
                    		textureNamesList.add(currentLineTexture[1]);
                    		//System.out.println("adding "+currentLineTexture[1]+" to the textureNamesList");
                    	}
                    	else if(lineTexture.startsWith("	map_Kd"))
                    	{
                    		modelTexturesList.add(new ModelTexture(loader.loadTexture(currentLineTexture[1])));
                    	}
                    	lineTexture = readerTexture.readLine();
                    }
                    
                }
                else if(line.startsWith("v "))
                {
                	Vector3f vertex;
                	if(currentLine[1].equals(""))
					{
						vertex = new Vector3f(Float.parseFloat(currentLine[2]), 
								Float.parseFloat(currentLine[3]), 
								Float.parseFloat(currentLine[4]));
					}
					else
					{
						vertex = new Vector3f(Float.parseFloat(currentLine[1]), 
								Float.parseFloat(currentLine[2]), 
								Float.parseFloat(currentLine[3]));
					}
                    vertices.add(vertex);
                }
                else if(line.startsWith("vt "))
                {
                    Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]),
                                    Float.parseFloat(currentLine[2]));
                    textures.add(texture);
                }
                else if(line.startsWith("vn "))
                {
                    Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]),
                                    Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                    normals.add(normal);
                }
                else if(line.startsWith("usemtl"))
                {
                	currentLine = line.split(" ");
                	for(int i = 0; i < textureNamesList.size(); i++)
                	{
                		String dummy = textureNamesList.get(i);
                		if(dummy.equals(currentLine[1]))
                		{
                			modelTextures.add(modelTexturesList.get(i));
                			//System.out.println("adding "+currentLine[1]+" to the modelTextures");
                			//break;
                		}
                	}
                }
                else if(line.startsWith("f "))
                {
                    textureArray = new float[vertices.size()*2];
                    normalsArray = new float[vertices.size()*3];
                    while((line != null) && line.startsWith("f "))
                    {
                        currentLine = line.split(" ");
                        //System.out.println(line);
                        String[] vertex1 = currentLine[1].split("/");
                        String[] vertex2 = currentLine[2].split("/");
                        String[] vertex3 = currentLine[3].split("/");
                       
                        processVertex(vertex1, indices, textures, normals, textureArray, normalsArray);
                        processVertex(vertex2, indices, textures, normals, textureArray, normalsArray);
                        processVertex(vertex3, indices, textures, normals, textureArray, normalsArray);
                        line = reader.readLine();
                        if(line.startsWith("s "))
                        { //"s " doesen't mean that we should stop!
                            line = reader.readLine();
                        }
                    }
                    if(line != null && line.startsWith("usemtl ") && vertices.size() > 0)
                    { //This could for sure be better!
                    	currentLine = line.split(" ");
                    	for(int i = 0; i < textureNamesList.size(); i++)
                    	{
                    		String dummy = textureNamesList.get(i);
                    		//System.out.println(dummy + " "+ currentLine[1]);
                    		if(dummy.equals(currentLine[1]))
                    		{
                    			modelTextures.add(modelTexturesList.get(i));
                    			//System.out.println("adding "+currentLine[1]+" to the modelTextures");
                    			//break;
                    		}
                    	}
                    	
                        verticesArray = new float[vertices.size()*3];
                        indicesArray = new int[indices.size()];
                       
                        int vertexPointer = 0;
                        for(Vector3f vertex : vertices)
                        {
                            verticesArray[vertexPointer++] = vertex.x;
                            verticesArray[vertexPointer++] = vertex.y;
                            verticesArray[vertexPointer++] = vertex.z;
                        }
                       
                        for(int i = 0; i < indices.size(); i++)
                        {
                            indicesArray[i] = indices.get(i);
                        }
                        indices.clear();
                        models.add(loader.loadToVAO(verticesArray, textureArray, normalsArray, indicesArray));
                    }
                }
                line = reader.readLine();
            }
            reader.close();     
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
       
        //System.out.println("modelTextures size = "+modelTextures.size());
        
        verticesArray = new float[vertices.size()*3];
        indicesArray = new int[indices.size()];
       
        int vertexPointer = 0;
        for(Vector3f vertex : vertices)
        {
            verticesArray[vertexPointer++] = vertex.x;
            verticesArray[vertexPointer++] = vertex.y;
            verticesArray[vertexPointer++] = vertex.z;
        }
       
        for(int i = 0; i < indices.size(); i++)
        {
            indicesArray[i] = indices.get(i);
        }
        models.add(loader.loadToVAO(verticesArray, textureArray, normalsArray, indicesArray));
        RawModel[] modelArray = new RawModel[models.size()];
        for(int i = 0; i < models.size(); i++)
        {
        	modelArray[i] = models.get(i);
        }
        return modelArray;
	}
	
	//legacy
	//dont use anymore - no generateminmax values
	public static CollisionModel loadObjModelOLD(String fileName)
	{
		CollisionModel collisionModel = new CollisionModel();
		FileReader fr = null;
		try 
		{
			fr = new FileReader(new File("res/"+fileName+".obj"));
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println("Couldn't load file!");
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(fr);
		
		String line;
		List<Vector3f> vertices = new ArrayList<Vector3f>();
		List<Vector2f>textures = new ArrayList<Vector2f>();
		List<Vector3f>normals = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();
		float[] verticesArray = null;
		float[] textureArray = null;
		float[] normalsArray = null;
		int[] indicesArray = null;
		
		try
		{
			while(true)
			{
				line = reader.readLine();
				//System.out.print("line = "+line);
				String[] currentLine = line.split(" ");
				//System.out.println(Arrays.toString(currentLine));
				if(line.startsWith("v "))
				{
					Vector3f vertex;
					if(currentLine[1].equals(""))
					{
						vertex = new Vector3f(Float.parseFloat(currentLine[2]), 
								Float.parseFloat(currentLine[3]), 
								Float.parseFloat(currentLine[4]));
					}
					else
					{
						vertex = new Vector3f(Float.parseFloat(currentLine[1]), 
								Float.parseFloat(currentLine[2]), 
								Float.parseFloat(currentLine[3]));
					}
					vertices.add(vertex);
				}
				else
				{
					if(line.startsWith("vt "))
					{
						Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]), 
								Float.parseFloat(currentLine[2]));
						textures.add(texture);
					}
					else
					{
						if(line.startsWith("vn "))
						{
							Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]), 
									Float.parseFloat(currentLine[2]), 
									Float.parseFloat(currentLine[3]));
							normals.add(normal);
						}
						else
						{
							if(line.startsWith("f "))
							{
								textureArray = new float[vertices.size()*2];
								normalsArray = new float[vertices.size()*3];
								break;
							}
						}
					}
				}
			}
		
			while(line!=null)
			{
				if(!line.startsWith("f "))
				{
					line = reader.readLine();
					continue;
				}
				String[] currentLine = line.split(" ");
				String[] vertex1 = currentLine[1].split("/");
				String[] vertex2 = currentLine[2].split("/");
				String[] vertex3 = currentLine[3].split("/");
				
				processVertex(vertex1,indices, textures, normals, textureArray, normalsArray);
				processVertex(vertex2,indices, textures, normals, textureArray, normalsArray);
				processVertex(vertex3,indices, textures, normals, textureArray, normalsArray);
				line = reader.readLine();
				
				float vx1 = vertices.get(Integer.parseInt(vertex1[0])-1).x;
				float vy1 = vertices.get(Integer.parseInt(vertex1[0])-1).y;
				float vz1 = vertices.get(Integer.parseInt(vertex1[0])-1).z;
				
				float vx2 = vertices.get(Integer.parseInt(vertex2[0])-1).x;
				float vy2 = vertices.get(Integer.parseInt(vertex2[0])-1).y;
				float vz2 = vertices.get(Integer.parseInt(vertex2[0])-1).z;
			
				float vx3 = vertices.get(Integer.parseInt(vertex3[0])-1).x;
				float vy3 = vertices.get(Integer.parseInt(vertex3[0])-1).y;
				float vz3 = vertices.get(Integer.parseInt(vertex3[0])-1).z;
				
				
				
				collisionModel.triangles.add(new Triangle3D(
						new Vector3f(vx1,vy1,vz1), 
						new Vector3f(vx2,vy2,vz2), 
						new Vector3f(vx3,vy3,vz3), (byte)0, (byte)38, (byte)0));
						
			}
			reader.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		verticesArray = new float[vertices.size()*3];
		indicesArray = new int[indices.size()];
		
		int vertexPointer = 0;
		for(Vector3f vertex:vertices)
		{
			verticesArray[vertexPointer++] = vertex.x;
			verticesArray[vertexPointer++] = vertex.y;
			verticesArray[vertexPointer++] = vertex.z;
		}
		
		for(int i = 0; i < indices.size(); i++)
		{
			indicesArray[i] = indices.get(i);
		}
		
		
		//collisionModel.generateChunks();
		return collisionModel;
	}
	
	private static void processVertex(String[] vertexData, List<Integer> indices, List<Vector2f> textures, 
			List<Vector3f> normals, float[] textureArray, float[] normalsArray)
	{
		int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
		indices.add(currentVertexPointer);
		Vector2f currentTex = textures.get(Integer.parseInt(vertexData[1])-1);
		textureArray[currentVertexPointer*2] = currentTex.x;
		textureArray[currentVertexPointer*2+1] = 1 - currentTex.y;
		Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2])-1);
		normalsArray[currentVertexPointer*3] = currentNorm.x;
		normalsArray[currentVertexPointer*3+1] = currentNorm.y;
		normalsArray[currentVertexPointer*3+2] = currentNorm.z;
	}
}
