package shaders;

import java.nio.FloatBuffer;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import toolbox.Maths;
import collision.CollisionChecker;
import collision.CollisionModel;
import entities.Camera;
import entities.Light;

public class StaticShader extends ShaderProgram
{
	private static final int MAX_LIGHTS = 6;
	private static final int TRI_NUMBER = 1;
	private static final String VERTEX_FILE = "/res/Shaders/static/vertexShader.txt";
	private static final String FRAGMENT_FILE = "/res/Shaders/static/fragmentShader.txt";

	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_lightPosition[];
	private int location_lightColour[];
	private int location_attenuation[];
	private int location_shineDamper;
	private int location_reflectivity;
	private int location_useFakeLighting;
	private int location_skyColour;
	private int location_clipPlane;
	private int location_toShadowMapSpace;
	private int location_toShadowMapSpace2;
	private int location_shadowMap;
	private int location_shadowMap2;
	private int location_texOffX;
	private int location_texOffY;
	private int location_semiTransparent;
	private int location_glowAmount;
	private int location_fogDensity;
	private int location_fogGradient;
	
	public StaticShader() 
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
	@Override
	protected void bindAttributes()
	{
		super.bindFragOutput(0, "out_Colour");
		super.bindFragOutput(1, "out_BrightColour");
		super.bindAttribute(0,"position");
		super.bindAttribute(1,"textureCoords");
		super.bindAttribute(2,"normal");
	}

	@Override
	protected void getAllUniformLocation() 
	{
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_shineDamper = super.getUniformLocation("shineDamper");
		location_reflectivity = super.getUniformLocation("reflectivity");
		location_useFakeLighting = super.getUniformLocation("useFakeLighting");
		location_skyColour = super.getUniformLocation("skyColour");
		location_clipPlane = super.getUniformLocation("clipPlane");
		location_toShadowMapSpace = super.getUniformLocation("toShadowMapSpace");
		location_toShadowMapSpace2 = super.getUniformLocation("toShadowMapSpace2");
		location_shadowMap = super.getUniformLocation("shadowMap");
		location_shadowMap2 = super.getUniformLocation("shadowMap2");
		location_texOffX = super.getUniformLocation("texOffX");
		location_texOffY = super.getUniformLocation("texOffY");
		location_semiTransparent = super.getUniformLocation("semiTransparent");
		location_glowAmount = super.getUniformLocation("glowAmount");
		location_fogDensity = super.getUniformLocation("fogDensity");
		location_fogGradient = super.getUniformLocation("fogGradient");
		
		location_lightPosition = new int[MAX_LIGHTS];
		location_lightColour = new int[MAX_LIGHTS];
		location_attenuation = new int[MAX_LIGHTS];
		
		//location_MyBlockIndex = super.getUniformBlockIndex("MyBlock");
		
		for(int i = 0; i < MAX_LIGHTS; i++)
		{
			location_lightPosition[i] = super.getUniformLocation("lightPosition[" + i + "]");
			location_lightColour[i] = super.getUniformLocation("lightColour[" + i + "]");
			location_attenuation[i] = super.getUniformLocation("attenuation[" + i + "]");
		}
	}
	
	public void loadClipPlane(Vector4f plane)
	{
		super.loadVector(location_clipPlane, plane);
	}
	
	public void loadToShadowSpaceMatrix(Matrix4f matrix)
	{
		super.loadMatrix(location_toShadowMapSpace, matrix);
	}
	
	public void loadToShadowSpaceMatrix2(Matrix4f matrix)
	{
		super.loadMatrix(location_toShadowMapSpace2, matrix);
	}
	
	public void loadSkyColour(float r, float g, float b)
	{
		super.loadVector(location_skyColour, new Vector3f(r,g,b));
	}
	
	public void loadFogDensity(float density)
	{
		super.loadFloat(location_fogDensity, density);
	}
	
	public void loadFogGradient(float gradient)
	{
		super.loadFloat(location_fogGradient, gradient);
	}
	
	public void loadFakeLightingVariable(boolean useFake)
	{
		super.loadBoolean(location_useFakeLighting, useFake);
	}
	
	public void loadGlowAmount(float glowAmount)
	{
		super.loadFloat(location_glowAmount, glowAmount);
	}
	
	public void loadShineVariables(float damper, float reflectivity)
	{
		super.loadFloat(location_shineDamper, damper);
		super.loadFloat(location_reflectivity, reflectivity);
	}
	
	public void loadTransformationMatrix(Matrix4f matrix)
	{
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadLights(List<Light> lights)
	{
		for(int i = 0; i < MAX_LIGHTS; i++)
		{
			if(i < lights.size())
			{
				super.loadVector(location_lightPosition[i], lights.get(i).getPosition());
				super.loadVector(location_lightColour[i], lights.get(i).getColour());
				super.loadVector(location_attenuation[i], lights.get(i).getAttenuation());
			}
			else
			{
				super.loadVector(location_lightPosition[i], new Vector3f(0, 0, 0));
				super.loadVector(location_lightColour[i], new Vector3f(0, 0, 0));
				super.loadVector(location_attenuation[i], new Vector3f(1, 0, 0));
			}
		}
	}
	
	public void connectTextureUnits()
	{
		super.loadInt(location_shadowMap, 5);
		super.loadInt(location_shadowMap2, 6);
	}
	
	public void loadViewMatrix(Camera camera)
	{
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	
	public void loadProjectionMatrix(Matrix4f projection)
	{
		super.loadMatrix(location_projectionMatrix, projection);
	}
	
	public void loadTextureOffsets(float offX, float offY)
	{
		super.loadFloat(location_texOffX, offX);
		super.loadFloat(location_texOffY, offY);
	}
	
	public void loadSemiTransparency(int semiTransparent)
	{
		super.loadInt(location_semiTransparent, semiTransparent);
	}
}
