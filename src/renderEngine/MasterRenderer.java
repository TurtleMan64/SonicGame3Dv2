package renderEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import shaders.StaticShader;
import shadows.ShadowMapMasterRenderer;
import shadows2.ShadowMapMasterRenderer2;
import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.Camera;
import entities.Entity;
import entities.Light;

public class MasterRenderer 
{
	public static float FOV = 50;
	public static final float NEAR_PLANE = 0.5f;
	public static final float FAR_PLANE = 15000;
	
	private float RED = 0.9f;
	private float GREEN = 0.95f;
	private float BLUE = 1f;
	
	private Matrix4f projectionMatrix;
	
	private StaticShader shader = new StaticShader();
	private EntityRenderer renderer;
	
	
	private ShadowMapMasterRenderer shadowMapRenderer;
	private ShadowMapMasterRenderer2 shadowMapRenderer2;
	
	//private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Entity> entitiesTransparent = new ArrayList<Entity>();
	
	public MasterRenderer(Camera cam)
	{
		enableCulling();
		createProjectionMatrix();
		renderer = new EntityRenderer(shader, projectionMatrix);
		this.shadowMapRenderer = new ShadowMapMasterRenderer(cam);
		this.shadowMapRenderer2 = new ShadowMapMasterRenderer2(cam);
	}
	
	public Matrix4f getProjectionMatrix()
	{
		return projectionMatrix;
	}
	
	public static void enableCulling()
	{
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	
	public static void disableCulling()
	{
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	
	
	public void render(List<Light> lights, Camera camera, Vector4f clipPlane)
	{
		//good
		prepare();
		//bad
		shader.start();
		shader.loadClipPlane(clipPlane);
		RED = SkyManager.getFogRed();
		GREEN = SkyManager.getFogGreen();
		BLUE = SkyManager.getFogBlue();
		shader.loadSkyColour(RED, GREEN, BLUE);
		shader.loadLights(lights);
		shader.loadViewMatrix(camera);
		shader.connectTextureUnits();
		for(Entity currentEntity: entities)
		{
			//System.out.println("about to render "+currentEntity.getClass().getSimpleName());
			renderer.render(currentEntity, shadowMapRenderer.getToShadowMapSpaceMatrix(), shadowMapRenderer2.getToShadowMapSpaceMatrix());
			//renderer.render(currentEntity, shadowMapRenderer.getToShadowMapSpaceMatrix());
		}
		shader.stop();
		entities.clear();
	}
	
	public void renderTransparent(List<Light> lights, Camera camera, Vector4f clipPlane)
	{
		prepareTransparentRender();
		shader.start();
		shader.loadClipPlane(clipPlane);
		RED = SkyManager.getFogRed();
		GREEN = SkyManager.getFogGreen();
		BLUE = SkyManager.getFogBlue();
		shader.loadSkyColour(RED, GREEN, BLUE);
		shader.loadLights(lights);
		shader.loadViewMatrix(camera);
		shader.connectTextureUnits();
		for (Entity currentEntity: entitiesTransparent)
		{
			renderer.render(currentEntity, shadowMapRenderer.getToShadowMapSpaceMatrix(), shadowMapRenderer2.getToShadowMapSpaceMatrix());
		}
		shader.stop();
		entitiesTransparent.clear();
	}
	
	//puts an entity into the "entities" hashmap, so it can be rendered
	/*
	public void processEntity(Entity entity)
	{
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = entities.get(entityModel);
		if(batch != null)
		{
			batch.add(entity);
		}
		else
		{
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);
		}
	}
	*/
	public void processEntity(Entity thisEntity)
	{
		if(thisEntity.getVisibility())
		{
			//System.out.println("entity: "+thisEntity.getClass().getSimpleName());
			entities.add(thisEntity);
		}
	}
	
	public void processTransparentEntity(Entity thisEntity)
	{
		if(thisEntity.getVisibility())
		{
			//System.out.println("entity: "+thisEntity.getClass().getSimpleName());
			entitiesTransparent.add(thisEntity);
		}
	}
	
	public void prepare()
	{
		//good
		GL11.glEnable(GL13.GL_MULTISAMPLE);
		//good
		//GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
		//bad
		//MainGameLoop.gameLoader.dispErrors();
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(RED, GREEN, BLUE, 1.0f);
		GL13.glActiveTexture(GL13.GL_TEXTURE5);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, getShadowMapTexture());
		GL13.glActiveTexture(GL13.GL_TEXTURE6);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, getShadowMapTexture2());
	}
	
	private void prepareTransparentRender()
	{
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
	}
	
	private void createProjectionMatrix()
	{
		/*//Old version
		float aspectRatio = (float)Display.getWidth()/(float)Display.getHeight();
		float y_scale = (float) ((1f/Math.tan(Math.toRadians(FOV/2f)))*aspectRatio);
		float x_scale = y_scale/aspectRatio;
		float frustrum_length = FAR_PLANE-NEAR_PLANE;
		
		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE+NEAR_PLANE) / frustrum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustrum_length);
		projectionMatrix.m33 = 0;
		*/
		
		if(projectionMatrix == null)
		{
			projectionMatrix = new Matrix4f();
		}
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))));
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;
		
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
	}
	
	public void changeFOV(float change)
	{
		FOV+=change;
		createProjectionMatrix();
		renderer.updateProjectionMatrix(projectionMatrix);
	}
	
	public void setFOV(float newFOV)
	{
		FOV=newFOV;
		createProjectionMatrix();
		renderer.updateProjectionMatrix(projectionMatrix);
	}
	
	public void renderShadowMaps(List<Entity> entityList, Light sun)
	{
		for(Entity entity: entityList)
		{
			processEntity(entity);
		}
		shadowMapRenderer.render(entities, sun);
		shadowMapRenderer2.render(entities, sun);
		entities.clear();
	}
	
	public ShadowMapMasterRenderer getShadowRenderer()
	{
		return shadowMapRenderer;
	}
	
	public int getShadowMapTexture()
	{
		return shadowMapRenderer.getShadowMap();
	}
	
	public int getShadowMapTexture2()
	{
		return shadowMapRenderer2.getShadowMap();
	}
	
	public void cleanUp()
	{
		shader.cleanUp();
		shadowMapRenderer.cleanUp();
		shadowMapRenderer2.cleanUp();
	}
}
