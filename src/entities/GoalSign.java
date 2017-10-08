package entities;

import models.TexturedModel;
import particles.Particle;
import particles.ParticleResources;

import org.lwjgl.util.vector.Vector3f;

import audio.AudioSources;
import collision.CollisionChecker;
import renderEngine.Loader;
import toolbox.AudioRes;
import toolbox.ConvenientMethods;
import toolbox.Maths;
import engineTester.MainGameLoop;
import guis.GuiManager;

public class GoalSign extends Entity
{
	private float hitboxR;
	private float hitboxV;
	private static TexturedModel[] signModel = null;
	private static Ball player;
	private float rotateSpeed;
	
	private static int triggersRemaining;
	
	public GoalSign(Vector3f position) 
	{
		super(signModel, position, 0, 0, 0, 1.5f);
		hitboxR = 6;
		hitboxV = 12;
		rotateSpeed = 0;
		player = MainGameLoop.gamePlayer;
		triggersRemaining = 0;
	}
	
	public void step()
	{
		if (triggersRemaining <= 0)
		{
			if (player.getX() > getX()-hitboxR-player.getHitboxHorizontal() && player.getX() < getX()+hitboxR+player.getHitboxHorizontal() &&
			   player.getZ() > getZ()-hitboxR-player.getHitboxHorizontal() && player.getZ() < getZ()+hitboxR+player.getHitboxHorizontal() &&
			   player.getY() > getY()-player.getHitboxVertical() && player.getY() < getY()+hitboxV)
			{
				if (rotateSpeed == 0)
				{
					AudioSources.play(29, getPosition());
					
					GuiManager.stopTimer();
					
					float speed = player.getSpeed()*2+3;
					rotateSpeed =  Math.min(48, speed);
					
					if (MainGameLoop.levelName.equals("EmeraldCoast.lvl"))
					{
						if (GuiManager.getTotalTimer() < 90 &&
							GuiManager.getRings() >= 200)
						{
							if (MainGameLoop.unlockedSonicDoll == false)
							{
								MainGameLoop.unlockedSonicDoll = true;
								AudioSources.play(35, getPosition());
								MainGameLoop.saveSaveFile();
							}
						}
					}
					else if (MainGameLoop.levelName.equals("GreenHillZone.lvl"))
					{
						if (GuiManager.getTotalTimer() < 40)
						{
							if (MainGameLoop.unlockedMechaSonic == false)
							{
								MainGameLoop.unlockedMechaSonic = true;
								AudioSources.play(35, getPosition());
								MainGameLoop.saveSaveFile();
							}
						}
					}
					else if (MainGameLoop.levelName.equals("MetalHarbor.lvl"))
					{
						if (GuiManager.getTotalTimer() < 90)
						{
							if (MainGameLoop.unlockedDage4 == false)
							{
								MainGameLoop.unlockedDage4 = true;
								AudioSources.play(35, getPosition());
								MainGameLoop.saveSaveFile();
							}
						}
					}
				}
			}
			
			float minDec = (float)Math.max(0.02, rotateSpeed/100);
			rotateSpeed = Math.max(rotateSpeed-minDec, 0);
			
			increaseRotation(0, rotateSpeed, 0);
			setVisibility(true);
		}
		else
		{
			setVisibility(false);
		}
	}
	
	public static void addTrigger()
	{
		triggersRemaining++;
	}
	
	public static void removeTrigger()
	{
		triggersRemaining--;
	}
	
	public static void allocateStaticModels()
	{
		if (signModel == null)
		{
			signModel = ConvenientMethods.loadModel("Models/GoalSign/", "GoalSign");
		}
	}
	
	public static void freeStaticModels()
	{
		if (signModel != null)
		{
			MainGameLoop.gameLoader.deleteModel(signModel);
			signModel = null;
		}
	}
}
