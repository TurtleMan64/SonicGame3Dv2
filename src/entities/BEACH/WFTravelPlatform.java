package entities.BEACH;

import org.lwjgl.util.vector.Vector3f;

import audio.AudioSources;
import audio.Source;
import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.MovingPlatform;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import toolbox.AudioRes;
import toolbox.ConvenientMethods;

public class WFTravelPlatform extends MovingPlatform
{
	private static TexturedModel[] modelPost = null;//ConvenientMethods.loadModel("Models/WF/", "TravelPlatform", MainGameLoop.gameLoader);
	private static CollisionModel cmOriginal = null;//OBJFileLoader.loadCollisionOBJ("Models/WF/", "TravelPlatform");
	private static Vector3f center = new Vector3f(0, 0, 0);
	private float timer;
	private float timerOffset;
	private static float width = 12;
	private static float height = 18;
	private int soundTimer;
	//private Source source;
	
	public WFTravelPlatform(Vector3f position, float timerOffset)
	{
		super(modelPost, cmOriginal, position);
		center.set(position);
		timer = timerOffset;
		this.timerOffset = timerOffset;
		soundTimer = 0;
		super.setRotY(0);
		super.setSpawnRotY(45);
	}
	
	@Override
	public void step()
	{
		super.step();
		soundTimer = (soundTimer+1)%8;
		float angle = (float)Math.toRadians(getRotY());
		if(timer < 1)
		{
			Vector3f posBot = new Vector3f(center.x+(float)(width*Math.cos(angle)), center.y-height, center.z+(float)(width*Math.sin(angle)));
			Vector3f posTop = new Vector3f(center.x+(float)(width*Math.cos(angle)), center.y+height, center.z+(float)(width*Math.sin(angle)));
			
			setX(posBot.x);
			setZ(posBot.z);
			setY(posBot.y+(timer)*(posTop.y-posBot.y));
			//setY(posBot.y);
		}
		else if (timer < 2)
		{
			double angTimer = (timer-1)*Math.PI;
			Vector3f posMid = new Vector3f(center.x, center.y+height, center.z);
			Vector3f right = new Vector3f(center.x+(float)(width*Math.cos(angle)), center.y+height, center.z+(float)(width*Math.sin(angle)));
			Vector3f left = new Vector3f(center.x-(float)(width*Math.cos(angle)), center.y+height, center.z-(float)(width*Math.sin(angle)));
			
			float ao = 0.5f+(float)(0.5*Math.sin(((timer-1)*Math.PI)-1.570795));
			setX(right.x+(ao)*(left.x-right.x));
			setZ(right.z+(ao)*(left.z-right.z));
			setY(posMid.y+width*(float)Math.sin(angTimer));
			//setX(right.x);
			//setZ(right.z);
			//setY(right.y);
		}
		else if (timer < 3)
		{
			//Vector3f posBot = new Vector3f(center.x-15, center.y-30, center.z);
			//Vector3f posTop = new Vector3f(center.x-15, center.y+30, center.z);
			Vector3f posBot = new Vector3f(center.x-(float)(width*Math.cos(angle)), center.y-height, center.z-(float)(width*Math.sin(angle)));
			Vector3f posTop = new Vector3f(center.x-(float)(width*Math.cos(angle)), center.y+height, center.z-(float)(width*Math.sin(angle)));
			
			setX(posTop.x);
			setZ(posTop.z);
			setY(posTop.y-(timer-2)*(posTop.y-posBot.y));
			//setY(posTop.y);
		}
		else
		{
			//double angle = (timer-3)*Math.PI+Math.PI;
			//Vector3f posMid = new Vector3f(center.x, center.y-30, center.z);
			//setX(posMid.x+15*(float)Math.cos(angle));
			//setZ(posMid.z);
			//setY(posMid.y+15*(float)Math.sin(angle));
			
			double angTimer = (timer-3)*Math.PI+Math.PI;
			float ao = 0.5f+(float)(0.5*Math.sin(((timer-3)*Math.PI)-1.570795));
			Vector3f posMid = new Vector3f(center.x, center.y-height, center.z);
			Vector3f right = new Vector3f(center.x-(float)(width*Math.cos(angle)), center.y-height, center.z-(float)(width*Math.sin(angle)));
			Vector3f left = new Vector3f(center.x+(float)(width*Math.cos(angle)), center.y-height, center.z+(float)(width*Math.sin(angle)));
			setX(right.x+(ao)*(left.x-right.x));
			setZ(right.z+(ao)*(left.z-right.z));
			setY(posMid.y+width*(float)Math.sin(angTimer));
			//setX(right.x);
			//setZ(right.z);
			//setY(right.y);
		}
		
		//if(timerOffset == 0)
		{
			//AudioRes.playSoundLoop(46, getPosition());
			//System.out.println(getPosition());
			//if(timer == 0)
			{
				//AudioRes.stopSound(46);
				//System.out.println("stopping");
			}
		}
		
		timer-=0.01f;
		if(timer < 0)
		{
			timer+=4;
			if(timerOffset == 0)
			{
				//AudioRes.stopSound(46);
				//System.out.println("stopping");
			}
			//AudioRes.playSound(46, 1, getPosition());
		}
		
		if(timerOffset == 0 && soundTimer == 0)
		{
			AudioRes.playSound(46, 1, getPosition());
		}
		
		updateCollisionModel();
		//AudioSources.setPosition(1, getPosition());
		//if(source != null)
		{
			//source.setPosition(getPosition());
		}
	}
	
	@Override
	public void despawn()
	{
		super.despawn();
		//if(timerOffset == 0)
		{
			//source.stop();
		}
	}
	
	@Override
	public void respawn()
	{
		super.respawn();
		timer = timerOffset;
		//if(timerOffset == 0)
		{
			//source = AudioSources.play(1, center, 1, true);
		}
	}
}
