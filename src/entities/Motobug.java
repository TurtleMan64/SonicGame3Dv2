package entities;

import org.lwjgl.util.vector.Vector3f;

import animation.AnimationResources;
import animation.Body;
import animation.Limb;
import audio.AudioSources;
import collision.CollisionChecker;
import collision.Triangle3D;
import engineTester.MainGameLoop;
import models.TexturedModel;
import particles.Particle;
import particles.ParticleResources;
import toolbox.AudioRes;
import toolbox.ConvenientMethods;
import toolbox.ParticleManager;

public class Motobug extends Entity
{
	private static TexturedModel[] modelBug = null;
	private static Ball player;
	private float xVel;
	private float zVel;
	private float yVel;
	private int furyTimer;
	private int furyTimerMax;
	private Triangle3D triCol;
	private boolean inAir;
	private int clock;
	private int stompedTimer;
	private float hitboxH;
	private float hitboxV;
	private float animIndex;
	
	public Motobug(Vector3f position) 
	{
		super(modelBug, position);
		player = MainGameLoop.gamePlayer;
		xVel = 0;
		zVel = 0;
		yVel = 0;
		furyTimer = 0;
		furyTimerMax = 100;
		triCol = null;
		inAir = true;
		clock = 0;
		stompedTimer = -1;
		hitboxH = 5;
		hitboxV = 10;
		animIndex = 0;
	}
	
	@Override
	public void step()
	{
		clock++;
		furyTimer = Math.max(0, furyTimer-1);
		
		
		yVel = Math.max(-2, yVel-0.1f);
		
		float xDiff = player.getX()-getX();
		float zDiff = player.getZ()-getZ();
		float angleRad = (float)Math.toRadians(getRotY());
		float newZ = (float)((xDiff)*-Math.sin(angleRad) - (zDiff)*Math.cos(angleRad));
		float mag = (float)Math.sqrt((xDiff*xDiff)+(zDiff*zDiff));
		
		//if (mag < 50 && furyTimer == 0)
		{
			//furyTimer = furyTimerMax;
		}
		
		if (furyTimer > 150) //turning fast to see the player
		{
			//compare rotY() to arctan2 to player
			//turn fast to aim at player
			//make noise?
		}
		else if (furyTimer > 1) //charge at player
		{
			//set speed to something
			//turn slowly to aim at player
		}
		else if (furyTimer == 1)
		{
			//stop moving
		}
		
		applyFriction(1.3f);
		
		if(!inAir && triCol.getNormal().y >= 0.5f)
		{
			float pushUpValue = -getY()+(float)(((-triCol.getA()*(getX()+xVel))+
											     (-triCol.getC()*(getZ()+zVel))-
											      (triCol.getD()))/triCol.getB());
			yVel = pushUpValue;
		}
		
		if (mag < 64)
		{
			if(furyTimer == 0)
			{
				furyTimer = furyTimerMax;
				setRotY((float)Math.toDegrees(Math.atan2(-zDiff, xDiff)));
				//AudioRes.playSound(27, 1, getPosition()); //goomba spots mario
				xVel = 0;
				zVel = 0;
				yVel = 0.25f;
				increasePosition(0, 1, 0);
			}
		}
		else
		{
			if (mag < 500 && furyTimer == 0)
			{
				float ran = (float)Math.random();
				if (ran < 0.01)
				{
					if(ran < 0.003 && clock > 30)
					{
						xVel = 0;
						zVel = 0;
						yVel = 1;
						setRotY((float)Math.random()*360);
						clock = 0;
					}
					else
					{
						increaseRotation(0, (float)(Math.random()*120-60), 0);
					}
				}
				
				if(clock > 30 && furyTimer == 0)
				{
					moveMeSlow();
				}
			}
		}
		
		if (furyTimer > 30)
		{
			if (newZ >= 2.8f)
			{
				increaseRotation(0, 2.8f, 0);
			}
			else if (newZ <= -2.8f)
			{
				increaseRotation(0, -2.8f, 0);
			}
			
			if(furyTimer < furyTimerMax-20)
			{
				moveMe();
			}
		}
		
		if (mag < 500)
		{
			if (player.getX() > getX()-hitboxH-player.getHitboxHorizontal() && player.getX() < getX()+hitboxH+player.getHitboxHorizontal() &&
					   player.getZ() > getZ()-hitboxH-player.getHitboxHorizontal() && player.getZ() < getZ()+hitboxH+player.getHitboxHorizontal())
			{
				if (player.getY() > getY()-player.getHitboxVertical() && player.getY() < getY()+hitboxV)
				{
					if (player.isVulnerable() == false)
					{
						xVel = 0;
						zVel = 0;
						//player.setyVel(1.3f);
						player.rebound(getPosition());
						die();
						AudioSources.play(28, getPosition());
					}
					else
					{
						//damage player
						player.takeDamage(getPosition());
					}
				}
			}
			
			if (CollisionChecker.checkCollision(getX(), getY(), getZ(), getX()+xVel, getY()+yVel, getZ()+zVel) == false)
			{
				if (CollisionChecker.checkCollision(getX()+xVel, getY()+yVel, getZ()+zVel, getX()+xVel, getY()+yVel-1f, getZ()+zVel) == true)
				{
					inAir = false;
					triCol = CollisionChecker.getCollideTriangle();
					Vector3f triColPosition = CollisionChecker.getCollidePosition();
					
					//yVel = 0;
					
					float colXNormal = triCol.getNormal().x;
					float colYNormal = triCol.getNormal().y;
					float colZNormal = triCol.getNormal().z;
					
					if(colYNormal >= 0.75f)
					{
						setX(triColPosition.x);
						setY(triColPosition.y+colYNormal*0.5f);
						setZ(triColPosition.z);
					}
					else
					{
						if(colYNormal >= 0.5f)
						{
							setX(triColPosition.x);
							setY(triColPosition.y+colYNormal*0.5f);
							setZ(triColPosition.z);
							xVel+=colXNormal*0.05f;
							zVel+=colZNormal*0.05f;
						}
						else
						{
							setX(triColPosition.x+colXNormal*0.5f);
							setY(triColPosition.y+colYNormal*0.5f);
							setZ(triColPosition.z+colZNormal*0.5f);
							xVel = 0;
							zVel = 0;
							setRotY(getRotY()+180);
						}
					}
				}
				else
				{
					if (inAir)
					{
						increasePosition(xVel, yVel, zVel);
					}
					else if (furyTimer > 0)
					{
						inAir = true;
						increasePosition(xVel, yVel, zVel);
					}
					//else //if we are about to walk off a ledge
					//{
						//turn around 180? doesnt work well
					//}
				}
			}
			else
			{
				inAir = false;
				triCol = CollisionChecker.getCollideTriangle();
				Vector3f triColPosition = CollisionChecker.getCollidePosition();
				
				float colXNormal = triCol.getNormal().x;
				float colYNormal = triCol.getNormal().y;
				float colZNormal = triCol.getNormal().z;
				
				//yVel = 0;
				
				if(colYNormal >= 0.75f)
				{
					setX(triColPosition.x);
					setY(triColPosition.y+colYNormal*0.5f);
					setZ(triColPosition.z);
				}
				else
				{
					if(colYNormal >= 0.5f)
					{
						setX(triColPosition.x);
						setY(triColPosition.y+colYNormal*0.5f);
						setZ(triColPosition.z);
						xVel+=colXNormal*0.05f;
						zVel+=colZNormal*0.05f;
					}
					else
					{
						setX(triColPosition.x+colXNormal*0.5f);
						setY(triColPosition.y+colYNormal*0.5f);
						setZ(triColPosition.z+colZNormal*0.5f);
						xVel = 0;
						zVel = 0;
						setRotY(getRotY()+180);
					}
				}
			}
		}
		
		if (getY() < -50)
		{
			die();
		}
		
		float speed = (float)Math.sqrt((xVel*xVel)+(zVel*zVel));
		float increaseValue = speed*9+1;
		if(animIndex%50 < 25 && (animIndex%50)+increaseValue >= 25)
		{
			//AudioRes.playSound(28, 1+speed*0.1f, getPosition()); //goomba step
		}
		
		animIndex+=increaseValue;
		if(speed == 0)
		{
			animIndex = 25;
		}
	}
	
	private void moveMe()
	{
		xVel += (float)(0.4*Math.cos(Math.toRadians(-getRotY())));
		zVel += (float)(0.4*Math.sin(Math.toRadians(-getRotY())));
	}
	
	private void moveMeSlow()
	{
		xVel += (float)(0.2*Math.cos(Math.toRadians(-getRotY())));
		zVel += (float)(0.2*Math.sin(Math.toRadians(-getRotY())));
	}
	
	private void applyFriction(float frictionToApply)
	{
		xVel = xVel/frictionToApply;
		zVel = zVel/frictionToApply;
	}
	
	@Override
	public void respawn()
	{
		super.respawn();
		stompedTimer = -1;
		furyTimer = 0;
		xVel = 0;
		zVel = 0;
		yVel = 0;
		triCol = null;
		inAir = true;
		clock = 0;

	}
	
	@Override
	public void despawn()
	{
		super.despawn();
	}
	
	private void die()
	{
		MainGameLoop.gameEntitiesToDelete.add(this);
		
		float height= 10f;
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
		if (modelBug == null)
		{
			modelBug = ConvenientMethods.loadModel("Models/Motobug/", "Motobug");
		}
	}
	
	public static void freeStaticModels()
	{
		if (modelBug != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelBug);
			modelBug = null;
		}
	}
}
