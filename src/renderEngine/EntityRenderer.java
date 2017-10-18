package renderEngine;

import java.util.List;
import java.util.Map;

import models.RawModel;
import models.TexturedModel;

//import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import collision.CollisionChecker;
import engineTester.MainGameLoop;
import shaders.StaticShader;
import textures.ModelTexture;
import toolbox.Maths;
import entities.Entity;

public class EntityRenderer 
{
	private StaticShader shader;
	
	public EntityRenderer(StaticShader shader, Matrix4f projectionMatrix)
	{
		this.shader = shader;
		//shader.loadDepthMap();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	public void render(Map<TexturedModel, List<Entity>> entities)
	{
		for(TexturedModel model : entities.keySet())
		{
			prepareTexturedModel(model);
			List<Entity> batch = entities.get(model);
			for(Entity entity:batch)
			{
				prepareInstance(entity);
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), 
						GL11.GL_UNSIGNED_INT, 0);
			}
			unbindTexturedModel();
		}
	}
	
	private void prepareTexturedModel(TexturedModel model)
	{
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		ModelTexture texture = model.getTexture();
		if(texture.isHasTransparency())
		{
			MasterRenderer.disableCulling();
		}
		shader.loadFakeLightingVariable(texture.isUseFakeLighting());
		shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
		
	}
	
	private void unbindTexturedModel()
	{
		MasterRenderer.enableCulling();
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}
	
	private void prepareInstance(Entity entity)
	{
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(),  
				entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
	}
	
	
	//renders a single entity
	//multi textured models
	
	public void render(Entity entity, Matrix4f toShadowSpace, Matrix4f toShadowSpace2)
	{
		if (entity.getVisibility() == false)
		{
			return;
		}
		
		shader.loadToShadowSpaceMatrix(toShadowSpace);
		shader.loadToShadowSpaceMatrix2(toShadowSpace2);
		float clockTime = (MainGameLoop.gameClock/60.0f); //old method = ((MainGameLoop.gameClock%60)/60.0f); gone because scroll can not sync on 1.5 for example
		
		shader.loadFogDensity(SkyManager.fogDensity);
		shader.loadFogGradient(SkyManager.fogGradient);
		
		int numModels = entity.numberOfModels();
		for (int i = 0; i < numModels; i++)
		{
            TexturedModel model = entity.getModel(i);
            RawModel rawModel = model.getRawModel();
            GL30.glBindVertexArray(rawModel.getVaoID());
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            GL20.glEnableVertexAttribArray(2);
            
            //shader.loadTransparency();
            //float clock = 0;
            int semiTransparent = 1;
            
            ModelTexture texture = model.getTexture();
            MasterRenderer.disableCulling();
    		if(texture.isHasTransparency())
    		{
    			//MasterRenderer.disableCulling();
    			semiTransparent = 0;
    		}
    		//if(texture.hasMovement())
    		//{
    			//clock = clockTime;
    		//}
    		shader.loadTextureOffsets(clockTime*texture.getScrollX(), clockTime*texture.getScrollY());
    		shader.loadSemiTransparency(semiTransparent);
    		shader.loadFakeLightingVariable(texture.isUseFakeLighting());
    		shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
    		shader.loadGlowAmount(texture.getGlowAmount());
    		
            Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(),
                    entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
            shader.loadTransformationMatrix(transformationMatrix);
            
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
            //GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
            //GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
    		//System.out.println("vcount = "+rawModel.getVertexCount());
            GL11.glDrawElements(GL11.GL_TRIANGLES, rawModel.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            
            //MasterRenderer.enableCulling();
            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL20.glDisableVertexAttribArray(2);
            GL30.glBindVertexArray(0);
		}
		/*
		TexturedModel model = entity.getModel();
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(),  
				entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
		ModelTexture texture = model.getTexture();
		shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, rawModel.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
		*/
	}
	
	public void updateProjectionMatrix(Matrix4f projectionMatrix)
	{
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
}
