package animation;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.TexturedModel;

public class Body extends Entity
{
	float baseX;
	float baseY;
	float baseZ;
	float baseRotY;
	float baseRotZ;
	float baseRotX;
	public int animationIndex;
	public float time;
	private float prevTime;
	public float deltaTime;
	
	ArrayList<Animation> animations;
	
	public Body(TexturedModel[] model) 
	{
		super(model, new Vector3f(0,0,0), 0, 0, 0, 1);
		super.setVisibility(true);
		animations = new ArrayList<Animation>();
		baseX = 0;
		baseY = 0;
		baseZ = 0;
		baseRotY = 0;
		baseRotZ = 0;
		baseRotX = 0;
		animationIndex = 0;
		time = 0;
		prevTime = 0;
		deltaTime = 0;
	}
	
	public void update(float time)
	{
		prevTime = this.time;
		this.time = Math.max(time, 0);
		time = this.time;
		deltaTime = this.time-prevTime;
		boolean inRange = false;
		Keyframe key1 = null;
		Keyframe key2 = null;
		float newX = 0;
		float newY = 0;
		float newZ = 0;
		float newXRot = 0;
		float newYRot = 0;
		float newZRot = 0;
		float newScale = 0;
		
		for(int i = 0; i < animations.get(animationIndex).keyframes.size()-1; i++)
		{
			key1 = animations.get(animationIndex).keyframes.get(i);
			key2 = animations.get(animationIndex).keyframes.get(i+1);
			
			if(time >= key1.time && time <= key2.time)
			{
				inRange = true;
				break;
			}
		}
		
		if(inRange)
		{
			//linear interpolation
			//view key1 as the "base"
			//and add a certain ratio of the difference between
			//key2 and key1 to key1 for the interpolated value
			//float ratio = (time-key1.time)/(key2.time-key1.time);
			float t = (time-key1.time)/(key2.time-key1.time);
			
			//sinusoidal interpolation
			t = 1-((float)(Math.cos(Math.PI*t)+1)*0.5f);
			
			float ratio = t;
			
			newX = key1.x+ratio*(key2.x-key1.x);
			newY = key1.y+ratio*(key2.y-key1.y);
			newZ = key1.z+ratio*(key2.z-key1.z);
			newXRot = key1.xRot+ratio*(key2.xRot-key1.xRot);
			newYRot = key1.yRot+ratio*(key2.yRot-key1.yRot);
			newZRot = key1.zRot+ratio*(key2.zRot-key1.zRot);
			newScale = key1.scale+ratio*(key2.scale-key1.scale);
		}
		else
		{
			newX = key2.x;
			newY = key2.y;
			newZ = key2.z;
			newXRot = key2.xRot;
			newYRot = key2.yRot;
			newZRot = key2.zRot;
			newScale = key2.scale;
		}
		float newXOffset = newX;
		float newZOffset = newZ;
		float angleY = (float)Math.toRadians(baseRotY+newYRot);
		
		newX = (float) (newXOffset * Math.cos(angleY) + newZOffset * Math.sin(angleY));
		newZ = (float) (newZOffset * Math.cos(angleY) - newXOffset * Math.sin(angleY));
		
		super.setX(baseX+newX);
		super.setY(baseY+newY);
		super.setZ(baseZ+newZ);
		super.setRotX(newXRot);
		super.setRotY(baseRotY+newYRot);
		super.setRotZ(baseRotZ+newZRot);
		//super.setScale(newScale);
	}
	
	public void setBaseOrientation(Vector3f basePosition, float rotY, float rotZ)
	{
		baseX = basePosition.x;
		baseY = basePosition.y;
		baseZ = basePosition.z;
		baseRotY = rotY;
		baseRotZ = rotZ;
	}
	
	public void setBaseRotZ(float rotZ)
	{
		baseRotZ = rotZ;
	}
	
	public void addAnimationFrame(int animIndex, float time, float x, float y, float z, float xRot, float yRot, float zRot, float scale)
	{
		while(animations.size() <= animIndex)
		{
			animations.add(new Animation());
		}
		animations.get(animIndex).addKeyframe(time, x, y, z, xRot, yRot, zRot, scale);
	}
}
