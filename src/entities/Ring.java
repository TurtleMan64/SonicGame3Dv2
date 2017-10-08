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

public class Ring extends Entity
{
	private float hitboxH;
	private float hitboxV;
	public static TexturedModel[] ringModel = null;
	public static Ball player;
	private float xVel;
	private float yVel;
	private float zVel;
	private boolean moves;
	private boolean created;
	private int grabTimer;
	
	public Ring(Vector3f position) 
	{
		super(ringModel, position, 0, 0, 0, 1);
		hitboxH = 5;
		hitboxV = 5;
		moves = false;
		xVel = 0;
		yVel = 0;
		zVel = 0;
		created = false;
		grabTimer = 0;
		player = MainGameLoop.gamePlayer;
	}
	
	public Ring(Vector3f position, float xVel, float yVel, float zVel) 
	{
		super(ringModel, position, 0, 0, 0, 1);
		hitboxH = 5;
		hitboxV = 5;
		moves = true;
		this.xVel = xVel;
		this.yVel = yVel;
		this.zVel = zVel;
		created = true;
		grabTimer = 60;
		player = MainGameLoop.gamePlayer;
	}
	
	public void step()
	{
		if (Math.abs(getX()-MainGameLoop.gameCamera.getX()) > MainGameLoop.ENTITY_RENDER_DIST)
		{
			setVisibility(false);
		}
		else
		{
			if (Math.abs(getZ()-MainGameLoop.gameCamera.getZ()) > MainGameLoop.ENTITY_RENDER_DIST)
			{
				setVisibility(false);
			}
			else
			{
				setVisibility(true);
			}
		}
		
		
		if (getVisibility() == true)
		{
			if (grabTimer == 0 && 
					player.getX() > getX()-hitboxH-player.getHitboxHorizontal() && player.getX() < getX()+hitboxH+player.getHitboxHorizontal() &&
                    player.getZ() > getZ()-hitboxH-player.getHitboxHorizontal() && player.getZ() < getZ()+hitboxH+player.getHitboxHorizontal() &&
                    player.getY() > getY()-hitboxV-player.getHitboxVertical() && player.getY() < getY()+hitboxV)
			{
				AudioSources.play(11, getPosition());
				
				for(int i = 0; i < 10; i++)
				{
					/*
					new Particle(ParticleResources.textureSparkleYellow, 
							new Vector3f(getX(),getY(),getZ()), 
							new Vector3f((float)Math.random()-0.5f, 
									     (float)(Math.random()*0.3+0.2f), 
									     (float)Math.random()-0.5f), 
							0.05f, 60, 0, 4, 0); */
					
					new Particle(ParticleResources.textureSparkleYellow,
							new Vector3f(getX()+(float)Math.random()*8-4,
									     getY()+(float)Math.random()*8-4,
									     getZ()+(float)Math.random()*8-4),
							//new Vector3f((float)Math.random(), (float)Math.random()+0.4f, (float)Math.random()),
							new Vector3f(0, 0.4f, 0), 
							0.025f, 30, 0, 7, -(7f/30f));
							
					
				}
				
				GuiManager.incrementRings();
				
				MainGameLoop.gameEntitiesToDelete.add(this);
			}
			
			if (moves)
			{
				grabTimer = Math.max(0, grabTimer-1);
				
				yVel -= 0.1f; //gravity
				
				Vector3f velVec = new Vector3f(xVel, yVel, zVel);
				velVec.scale(0.99f); //air friction
				xVel = velVec.x;
				yVel = velVec.y;
				zVel = velVec.z;
				
				if (CollisionChecker.checkCollision(getX(), getY()-5f, getZ(), getX()+xVel, getY()+yVel-5f, getZ()+zVel))
				{
					setPosition(CollisionChecker.getCollidePosition());
					increasePosition(0, 5f, 0);
					//AudioRes.playSound(50, 1, getPosition());
					//AudioSources.play(11, getPosition());
					
					Vector3f normal = CollisionChecker.getCollideTriangle().normal;
					float speed = (float)Math.sqrt(xVel*xVel + yVel*yVel + zVel*zVel);
					
					if (speed > 0.5f)
					{
						Vector3f vel = new Vector3f(xVel, yVel, zVel);
						Vector3f newVel = Maths.bounceVector(vel, normal, 0.7f);
						xVel = newVel.x;
						yVel = newVel.y;
						zVel = newVel.z;
						increasePosition(normal.x*0.01f, normal.y*0.01f, normal.z*0.01f);
					}
					else
					{
						moves = false;
						grabTimer = 0;
					}
				}
				else
				{
					increasePosition(xVel, yVel, zVel);
				}
				
				if (getY() < -1000)
				{
					MainGameLoop.gameEntitiesToDelete.add(this);
				}
			}
		}
		
		increaseRotation(0, 5, 0);
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
	
	public static void setStaticObjects(Ball myPlayer)
	{
		player = myPlayer;
	}
	
	public static void allocateStaticModels()
	{
		if (ringModel == null)
		{
			ringModel = ConvenientMethods.loadModel("Models/Ring/", "Ring");
		}
	}
	
	public static void freeStaticModels()
	{
		if (ringModel != null)
		{
			MainGameLoop.gameLoader.deleteModel(ringModel);
			ringModel = null;
		}
	}
}
