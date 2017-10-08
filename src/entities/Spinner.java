package entities;

import org.lwjgl.util.vector.Vector3f;

import animation.AnimationResources;
import animation.Body;
import animation.Limb;
import audio.AudioSources;
import collision.CollisionChecker;
import collision.Triangle3D;
import engineTester.MainGameLoop;
import guis.GuiManager;
import models.TexturedModel;
import particles.Particle;
import particles.ParticleResources;
import toolbox.AudioRes;
import toolbox.ConvenientMethods;
import toolbox.ParticleManager;

public class Spinner extends Entity
{
	private static TexturedModel[] modelBody = null;
	private static TexturedModel[] modelBlades = null;
	private static Ball player;
	private Entity blades;
	private static float colHorizontal = 6;
	private static float colVertical = 7.3f;
	
	public Spinner(Vector3f position, float yRot) 
	{
		super(modelBody, position, 0, yRot, 0, 1);
		player = MainGameLoop.gamePlayer;
		blades = new Entity(modelBlades, position);
		MainGameLoop.gameEntitiesToAdd.add(blades);
	}
	
	@Override
	public void step()
	{
		if (Math.abs(getX()-MainGameLoop.gameCamera.getX()) > MainGameLoop.ENTITY_RENDER_DIST)
		{
			setVisibility(false);
			blades.setVisibility(false);
		}
		else
		{
			if (Math.abs(getZ()-MainGameLoop.gameCamera.getZ()) > MainGameLoop.ENTITY_RENDER_DIST)
			{
				setVisibility(false);
				blades.setVisibility(false);
			}
			else
			{
				setVisibility(true);
				blades.setVisibility(true);
				if (player.getX() > getX()-colHorizontal-player.getHitboxHorizontal() && player.getX() < getX()+colHorizontal+player.getHitboxHorizontal() &&
					player.getZ() > getZ()-colHorizontal-player.getHitboxHorizontal() && player.getZ() < getZ()+colHorizontal+player.getHitboxHorizontal() &&
					player.getY()+player.getHitboxVertical() >= getY()-colVertical && player.getY()-player.getHitboxVertical() <= getY()+colVertical)
				{
					if (player.isVulnerable())
					{
						player.takeDamage(getPosition());
					}
					else
					{
						die();
						player.rebound(getPosition());
					}
				}
				
				float xDiff = (getX()-MainGameLoop.gamePlayer.getX());
				float zDiff = (getZ()-MainGameLoop.gamePlayer.getZ());
				setRotY((float)Math.toDegrees(Math.atan2(zDiff, -xDiff)));
			}
		}
		
		blades.increaseRotation(0, 25, 0);
	}
	
	
	private void die()
	{
		AudioSources.play(28, getPosition());
		
		MainGameLoop.gameEntitiesToDelete.add(this);
		MainGameLoop.gameEntitiesToDelete.add(blades);
		
		float height = 10f;
		float spread = 20f;
		
		for (int i = 7; i != 0; i--)
		{
			new Particle(ParticleResources.textureExplosion1, 
					new Vector3f(getX()+spread*(float)(Math.random()-0.5),
					         getY()+spread*(float)(Math.random()-0.5)+height,
					         getZ()+spread*(float)(Math.random()-0.5)), new Vector3f(0, 0, 0), 
					0, 45, 0, 3*(float)Math.random()+6, 0);
			/*
			new Particle(ParticleResources.textureExplosion2, 
					new Vector3f(getX()+spread*(float)(Math.random()-0.5),
					         getY()+spread*(float)(Math.random()-0.5)+height,
					         getZ()+spread*(float)(Math.random()-0.5)), new Vector3f(0, 0, 0), 
					0, 100, 0, 6*(float)Math.random()+6, 0);
					*/
		}
		
		new Particle(ParticleResources.textureExplosion2, 
				new Vector3f(getX(),
				         	 getY()+height,
				         	 getZ()), new Vector3f(0, 0, 0), 
				0, 55, 0, 20, 0);
	}
	
	public static void allocateStaticModels()
	{
		if (modelBody == null)
		{
			modelBody = ConvenientMethods.loadModel("Models/Spinner/", "Body");
		}
		if (modelBlades == null)
		{
			modelBlades = ConvenientMethods.loadModel("Models/Spinner/", "Blades");
		}
	}
	
	public static void freeStaticModels()
	{
		if (modelBody != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelBody);
			modelBody = null;
		}
		if (modelBlades != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelBlades);
			modelBlades = null;
		}
	}
}
