package entities.BEACH;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionChecker;
import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.CollideableObject;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import toolbox.ConvenientMethods;

public class BOBWoodPlank extends CollideableObject
{
	private static CollisionModel cmOriginal = null;
	private static TexturedModel[] models = null;
	private float rotVel;
	
	public BOBWoodPlank(Vector3f position, float yRot) 
	{
		super(models, cmOriginal, position);
		CollisionChecker.addCollideModel(collideTransformed);
		rotVel = 0;
		setRotY(yRot);
	}
	
	@Override
	public void step()
	{
		updateCollisionModelWithZ();

		//if (getRotZ() > 0)
		{
			//rotVel-=0.03f;
		}
		//if (getRotZ() < 0)
		{
			//rotVel+=0.03f;
		}
		rotVel-=getRotZ()*0.003f;
		
		rotVel*=0.9;
		
		if (collideTransformed.playerIsOn)
		{
			
			//calculate x and z displacements from rotation + players distance from center of rotation
			float xDiff = player.getX()-getX();
			float yDiff = player.getY()-getY();
			float zDiff = player.getZ()-getZ();
			
			//if (xDiff >= 0)
			{
				//rotVel -= 0.09f;
			}
			//else
			{
				//rotVel += 0.09f;
			}
			
			rotVel-=xDiff*0.002f;
			
			
			double rotAngle = Math.toRadians(rotVel);
			float newX = (float)((xDiff)*Math.cos(rotAngle) - (yDiff)*Math.sin(rotAngle));
			float newY = -(float)(-(yDiff)*Math.cos(rotAngle) - (xDiff)*Math.sin(rotAngle));
			

			float xdis = xDiff-newX;
			float ydis = yDiff-newY;
			player.setDisplacement(xdis, ydis, 0);
			
			//player.setAirVel(xdis, 0, zdis);
			//player.increaseRotation(0, yRotRate, 0);
			
			//float force = player.getPreviousSpeed()*(player.getY()-getY());
			//System.out.println(force);
			//if(force > 10)
			{
				//rotVel-=force/40;
				//AudioRes.playSound(47, 1+force/150, getPosition());
			}
		}
		
		setRotZ(getRotZ()+rotVel);
	}
	
	public static void allocateStaticModels()
	{
		if (models == null)
		{
			models = ConvenientMethods.loadModel("Models/BOB/", "Plank");
		}
		if (cmOriginal == null)
		{
			cmOriginal = OBJFileLoader.loadCollisionOBJ("Models/BOB/", "Plank");
		}
	}
	
	public static void freeStaticModels()
	{
		if (models != null)
		{
			MainGameLoop.gameLoader.deleteModel(models);
			models = null;
		}
		if (cmOriginal != null)
		{
			cmOriginal = null;
		}
	}
}
