package animation;

import java.util.ArrayList;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import entities.Entity;
import models.TexturedModel;
import toolbox.Maths;

public class Limb extends Entity
{
	Body body;
	Limb limb;
	public int animationIndex;
	float pivotX;
	float pivotY;
	float pivotZ;
	
	ArrayList<Animation> animations;
	
	public Limb(TexturedModel[] model, float pivotX, float pivotY, float pivotZ, Body body, Limb limb) 
	{
		super(model, new Vector3f(0,0,0), 0, 0, 0, 1);
		super.setVisibility(true);
		animations = new ArrayList<Animation>();
		this.pivotX = pivotX;
		this.pivotY = pivotY;
		this.pivotZ = pivotZ;
		this.body = body;
		this.limb = limb;
		animationIndex = 0;
		
		//animations.add(new Animation());
		
		//run animation
		//animations.add(new Animation());
		
		//animations.add(new Animation());
		
		//double jump
		//animations.add(new Animation());
		
		//triple jump
		//animations.add(new Animation());
	}
	
	public void update(float time)
	{
		time = Math.max(time, 0);
		boolean inRange = false;
		Keyframe key1 = null;
		Keyframe key2 = null;
		
		
		float angleX, angleY, angleZ;
		if(body != null)
		{
			angleX = (float)Math.toRadians(body.getRotX());
			angleY = (float)Math.toRadians(body.getRotY());
			angleZ = (float)Math.toRadians(body.getRotZ());
		}
		else
		{
			angleX = (float)Math.toRadians(limb.getRotX());
			angleY = (float)Math.toRadians(limb.getRotY());
			angleZ = (float)Math.toRadians(limb.getRotZ());
		}
		
		
		float newX = pivotX, newY = pivotY, newZ = pivotZ;
		
		//float newX = (float)(pivotZ*Math.sin(angleY) + pivotX*Math.cos(angleY));
		//float newZ = (float)(pivotZ*Math.cos(angleY) - pivotX*Math.sin(angleY));
		//System.out.println("before:  x = "+newX+"     y = "+newY+"     angleZ = "+Math.toDegrees(angleZ));
		
		
		//rotation around x axis
		//newY = (float) (pivotY * Math.cos(angleX) - pivotZ * Math.sin(angleX));
	    //newZ = (float) (pivotY * Math.sin(angleX) + pivotZ * Math.cos(angleX));
		
	    float newPivotX = newX;
	    float newPivotY = newY;
	    float newPivotZ = newZ;
		
	    //rotation around z axis
	    newX = (float) (newPivotX * Math.cos(angleZ) - newPivotY * Math.sin(angleZ));
	    newY = (float) (newPivotX * Math.sin(angleZ) + newPivotY * Math.cos(angleZ));
	    //newY = (float)(pivotY*Math.cos(angleZ) - pivotX*Math.sin(angleZ));
	    
	    //System.out.println("after:  x = "+newX+"     y = "+newY+"     angleZ = "+Math.toDegrees(angleZ));
	    //System.out.println("radius = "+(float)Math.sqrt(Math.pow(newX, 2)+Math.pow(newY, 2)));
	    //System.out.println();
	    
	    newPivotX = newX;
	    newPivotY = newY;
	    newPivotZ = newZ;
	    
	    //rotation around y axis
	    newX = (float) (newPivotX * Math.cos(angleY) + newPivotZ * Math.sin(angleY));
		newZ = (float) (newPivotZ * Math.cos(angleY) - newPivotX * Math.sin(angleY));
		
		/*
		Matrix4f rotationMat = Maths.createTransformationMatrix(new Vector3f(0,0,0), 0, angleY, angleZ, 0);
		Vector4f position = new Vector4f(newX, newY, newZ, 1);
		
		position = Maths.multiplyByMat(position, rotationMat);
		newX = position.x;
		newY = position.y;
		newZ = position.z;
		*/
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
			float t = (time-key1.time)/(key2.time-key1.time);
			
			//sinusoidal interpolation
			t = 1-((float)(Math.cos(Math.PI*t)+1)*0.5f);
			
			float ratio = t;
			
			newXRot = key1.xRot+ratio*(key2.xRot-key1.xRot);
			newYRot = key1.yRot+ratio*(key2.yRot-key1.yRot);
			newZRot = key1.zRot+ratio*(key2.zRot-key1.zRot);
			newScale = key1.scale+ratio*(key2.scale-key1.scale);
		}
		else
		{
			newXRot = key2.xRot;
			newYRot = key2.yRot;
			newZRot = key2.zRot;
			newScale = key2.scale;
		}
		
		if(body != null)
		{
			super.setX(body.getX()+newX);
			super.setY(body.getY()+newY);
			super.setZ(body.getZ()+newZ);
			super.setRotX(body.getRotX()+newXRot);
			super.setRotY(body.getRotY()+newYRot);
			super.setRotZ(body.getRotZ()+newZRot);//pitch
			//super.setScale(newScale);
		}
		else
		{
			super.setX(limb.getX()+newX);
			super.setY(limb.getY()+newY);
			super.setZ(limb.getZ()+newZ);
			super.setRotX(limb.getRotX()+newXRot);
			super.setRotY(limb.getRotY()+newYRot);
			super.setRotZ(limb.getRotZ()+newZRot);//pitch
			//super.setScale(newScale);
		}
	}
	
	public void addAnimationFrame(int animIndex, float time, float xRot, float yRot, float zRot, float scale)
	{
		while(animations.size() <= animIndex)
		{
			animations.add(new Animation());
		}
		animations.get(animIndex).addKeyframe(time, xRot, yRot, zRot, scale);
	}
}
