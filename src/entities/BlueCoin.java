package entities;

import models.TexturedModel;
import particles.Particle;
import particles.ParticleResources;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionChecker;
import renderEngine.Loader;
import toolbox.AudioRes;
import toolbox.ConvenientMethods;

import engineTester.MainGameLoop;

public class BlueCoin extends Entity
{
	private float hitboxH;
	private float hitboxV;
	public static TexturedModel[] coinModel = null;
	public static Player player;
	public static Loader loader;
	private float xVel;
	private float yVel;
	private float zVel;
	private boolean moves;
	private boolean created;
	private int grabTimer;
	
	public BlueCoin(Vector3f position) 
	{
		super(coinModel, position, 0, 0, 0, 1);
		super.setVisibility(true);
		hitboxH = 4.5f;
		hitboxV = 4.5f;
		moves = false;
		xVel = 0;
		yVel = 0;
		zVel = 0;
		created = false;
		grabTimer = 0;
	}
	
	public BlueCoin(Vector3f position, float xVel, float yVel, float zVel) 
	{
		super(coinModel, position, 0, 0, 0, 1);
		super.setVisibility(true);
		hitboxH = 4.5f;
		hitboxV = 4.5f;
		moves = true;
		this.xVel = xVel;
		this.yVel = yVel;
		this.zVel = zVel;
		created = true;
		grabTimer = 30;
	}
	
	public void step()
	{
		//AudioRes.playSoundRepeat(4, super.getPosition());
		if(grabTimer == 0 && super.getVisibility() == true && player.getX() > super.getX()-hitboxH-player.getHitboxHorizontal() && player.getX() < super.getX()+hitboxH+player.getHitboxHorizontal() &&
		   player.getZ() > super.getZ()-hitboxH-player.getHitboxHorizontal() && player.getZ() < super.getZ()+hitboxH+player.getHitboxHorizontal() &&
		   player.getY() > super.getY()-hitboxV-player.getHitboxVertical() && player.getY() < super.getY()+hitboxV)
		{
			super.setVisibility(false);
			//MainGameLoop.gameEntitiesToDelete.add(this);
			AudioRes.playSound(49, 1, getPosition());
			for(int i = 0; i < 10; i++)
			{
				new Particle(ParticleResources.textureSparkleBlue, 
						new Vector3f(getX(),getY(),getZ()), 
						new Vector3f((float)Math.random()-0.5f, 
								     (float)(Math.random()*0.3+0.2f), 
								     (float)Math.random()-0.5f), 
						0.05f, 60, 0, 4, 0);
			}
			
			if(created)
			{
				MainGameLoop.gameEntitiesToDelete.add(this);
			}
		}
		
		if(moves)
		{
			yVel -= 0.1f;
			grabTimer = Math.max(0, grabTimer-1);
			
			if(CollisionChecker.checkCollision(getX(), getY()-4f, getZ(), getX()+xVel, getY()+yVel-4f, getZ()+zVel))
			{
				setPosition(CollisionChecker.getCollidePosition());
				increasePosition(0, 4f, 0);
				AudioRes.playSound(50, 1, getPosition());
				
				Vector3f normal = CollisionChecker.getCollideTriangle().normal;
				if(normal.y < 0.4f)
				{
					xVel*=-1;
					zVel*=-1;
					increasePosition(normal.x, normal.y, normal.z);
				}
				else if(yVel > -0.5f && yVel <= 0)
				{
					moves = false;
				}
				else
				{
					yVel = -yVel*0.5f;
					xVel = xVel*0.5f;
					zVel = zVel*0.5f;
					increasePosition(0, 0.2f, 0);
				}
			}
			else
			{
				increasePosition(xVel, yVel, zVel);
			}
			
			if(getY() < -1000)
			{
				MainGameLoop.gameEntitiesToDelete.add(this);
			}
		}
		
		super.increaseRotation(0, 5, 0);
	}
	
	@Override
	public void respawn()
	{
		super.respawn();
		super.setVisibility(true);
		if(created)
		{
			MainGameLoop.gameEntitiesToDelete.add(this);
		}
	}
	
	@Override
	public void despawn()
	{
		//if(created)
		super.despawn();
	}
	
	public static void setStaticObjects(Player myPlayer)
	{
		player = myPlayer;
	}
}
