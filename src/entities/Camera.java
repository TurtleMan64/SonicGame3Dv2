package entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import toolbox.Maths;


public class Camera
{
	private Vector3f position = new Vector3f(0, 20, 0);
	private Vector3f prevPos = new Vector3f(0, 20, 0);
	private float pitch;
	private float yaw;
	private float roll;
	private int mousePreviousX;
	private int mousePreviousY;
	private Player player;
	private int cutsceneTimer;
	private Vector3f cutscenePosition;
	private Vector3f cutsceneTarget;
	private float preCutscenePitch;
	private float preCutsceneYaw;
	private float preCutsceneRoll;
	private Vector3f preCutscenePosition;
	Entity cutsceneEntity;
	private Vector3f fadePosition;
	
	public Camera()
	{
		mousePreviousX = Mouse.getX();
		mousePreviousY = Mouse.getY();
		cutsceneTimer = 0;
		cutsceneTarget = new Vector3f();
		preCutscenePosition = new Vector3f();
		cutscenePosition = new Vector3f();
		preCutscenePitch = 0;
		preCutsceneYaw = 0;
		preCutsceneRoll = 0;
		fadePosition = new Vector3f(0,0,0);
	}
	
	public void step()
	{
		/*
		if(player.getInCutScene() == false)
		{
			this.pitch+=-0.5*(Mouse.getY()-mousePreviousY);
			this.yaw+=0.5*(Mouse.getX()-mousePreviousX);
			
			if(Joystick.joystickExists())
			{
				this.pitch+=2.5*(Joystick.getYRight());
				this.yaw+=2.5*(Joystick.getXRight());
				//System.out.println(this.pitch);
			}
			
			
			Mouse.setCursorPosition(DisplayManager.getWidth()/2, DisplayManager.getHeight()/2);
			mousePreviousX = Mouse.getX();
			mousePreviousY = Mouse.getY();
		}
		*/
		//position.x -= 0.1*(position.x-cutsceneTarget.x);
		//position.y -= 0.1*(position.y-cutsceneTarget.y);
		//position.z -= 0.1*(position.z-cutsceneTarget.z);
		
		/*
		cutsceneEntity.step();
		float xDiff = (cutsceneTarget.x-position.x);
		float zDiff = (cutsceneTarget.z-position.z);
		
		yaw = (float)Math.toDegrees(Math.atan2(xDiff, -zDiff));
		pitch = -(float)Math.toDegrees(Math.atan2((cutsceneTarget.y-position.y), Math.sqrt(xDiff*xDiff+zDiff*zDiff)));
		
		if(cutsceneTimer <= 0)
		{
			MainGameLoop.gameState = MainGameLoop.gameStates.running;
			position.set(preCutscenePosition);
			pitch = preCutscenePitch;
			yaw = preCutsceneYaw;
			roll = preCutsceneRoll;
		}
		cutsceneTimer-=1;
		*/
		//System.out.println(yaw);
		//System.out.println(pitch);
		//System.out.println();
		Vector3f orig = position;
		//Vector3f offset = Maths.spherePositionFromAngles((float)Math.toRadians(yaw), (float)Math.toRadians(pitch), 60);
		//fadePosition.x = MainGameLoop.gamePlayer.getPosition().x+offset.x;
		//fadePosition.y = MainGameLoop.gamePlayer.getPosition().y+offset.y;
		//fadePosition.z = MainGameLoop.gamePlayer.getPosition().z+offset.z;
		
		float radius = 5;
		Vector3f newPos = new Vector3f(
				orig.x-(float)(Math.cos(Math.toRadians(yaw+90))*(radius*(Math.cos(Math.toRadians(pitch))))),
				orig.y+(float)(Math.sin(Math.toRadians(pitch+180))*radius),
				orig.z-(float)(Math.sin(Math.toRadians(yaw+90))*(radius*(Math.cos(Math.toRadians(pitch))))));
		
		fadePosition.x = newPos.x;
		fadePosition.y = newPos.y;
		fadePosition.z = newPos.z;
	}
	
	public Vector3f getPosition() 
	{
		return position;
	}
	
	public Vector3f calcVelocity()
	{
		Vector3f diff = Vector3f.sub(getPosition(), prevPos, null);
		prevPos.set(getPosition());
		//System.out.println("cam vel = "+diff.length());
		return diff;
	}
	
	public float getX()
	{
		return position.x;
	}
	
	public float getY()
	{
		return position.y;
	}
	
	public float getZ()
	{
		return position.z;
	}
	
	public void setPosition(Vector3f position)
	{
		this.position.set(position);
	}
	
	public void increasePosition(float dx, float dy, float dz)
	{
		this.position.set(position.getX()+dx, position.getY()+dy, position.getZ()+dz);
	}

	public float getPitch() 
	{
		return pitch;
	}

	public float getYaw() 
	{
		return yaw;
	}

	public float getRoll() 
	{
		return roll;
	}
	
	public void setPitch(float newPitch)
	{
		this.pitch = newPitch;
	}
	
	public void setYaw(float newYaw)
	{
		this.yaw = newYaw;
	}
	
	public void invertPitch()
	{
		this.pitch = -pitch;
	}
	
	public void setPlayer(Player targetPlayer)
	{
		this.player = targetPlayer;
	}
	
	public void startCutscene(Entity entityTarget, int timer, Vector3f newPosition, Vector3f target)
	{
		preCutscenePosition.set(position);
		preCutscenePitch = pitch;
		preCutsceneYaw = yaw;
		preCutsceneRoll = roll;
		cutsceneTimer = timer;
		cutscenePosition.set(newPosition);
		cutsceneTarget = target;
		MainGameLoop.gameState = MainGameLoop.gameStates.cutscene;
		position.set(newPosition);
		cutsceneEntity = entityTarget;
	}
	
	public Vector3f getFadePosition()
	{
		return fadePosition;
	}
}
