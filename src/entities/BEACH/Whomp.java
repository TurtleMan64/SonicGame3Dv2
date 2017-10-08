package entities.BEACH;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionChecker;
import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.Ring;
import entities.MovingPlatform;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import particles.Particle;
import particles.ParticleResources;
import toolbox.AudioRes;
import toolbox.ConvenientMethods;

public class Whomp extends MovingPlatform
{
	private static TexturedModel[] modelBody = null;//ConvenientMethods.loadModel("Models/Whomp/", "Whomp", MainGameLoop.gameLoader);
	private static CollisionModel cmOriginal = null;//OBJFileLoader.loadCollisionOBJ("Models/Whomp/", "Collision");
	
	private float distance;
	private Vector3f pos1;
	private Vector3f pos2;
	private float direction;
	private int madTimer;
	private float baseRotY;
	private float rotYOffset;
	private int turnTimer;
	private boolean isDead;
	
	public Whomp(Vector3f position, float yRot) 
	{
		super(modelBody, cmOriginal, position);
		setSpawnRotY(yRot);
		pos1 = new Vector3f(position);
		
		pos2 = new Vector3f(position.x+100*(float)Math.cos(Math.toRadians(yRot)), 
				position.y, 
				position.z-100*(float)Math.sin(Math.toRadians(yRot)));
		
		direction = 1;
		distance = 0;
		madTimer = 0;
		baseRotY = yRot;
		rotYOffset = 0;
		turnTimer = 20;
		isDead = false;
	}
	
	@Override
	public void step()
	{
		if(!isDead)
		{
			super.step();
			
			if(madTimer == 0)
			{
				distance+=direction*0.001f;
				if(distance > 1 || distance < 0)
				{
					direction*=-1;
					baseRotY+=180;
				}
				setX(pos1.x+(pos2.x-pos1.x)*distance);
				setZ(pos1.z+(pos2.z-pos1.z)*distance);
				
				double ang = Math.toRadians(baseRotY);
				Vector3f center = getPosition();
				Vector3f checkPos = new Vector3f(center.x+18*(float)Math.cos(ang), 
												 center.y,
												 center.z-18*(float)Math.sin(ang));
				
				if(Math.sqrt(Math.pow(player.getX()-checkPos.x, 2)+Math.pow(player.getZ()-checkPos.z, 2)) < 13)
				{
					madTimer = 240;
				}
				
				turnTimer++;
				if(turnTimer >= 80)
				{
					turnTimer-=160;
				}
				
				if(turnTimer == 0 || turnTimer == -80)
				{
					AudioRes.playSound(55, 1, getPosition());
				}
				
				if(turnTimer < -40)
				{
					rotYOffset-=0.75f;
					setRotX(getRotX()-0.125f);
				}
				else if(turnTimer >= 0 && turnTimer < 40)
				{
					rotYOffset+=0.75f;
					setRotX(getRotX()+0.125f);
				}
			}
			else
			{
				madTimer-=1;
				rotYOffset = 0;
				turnTimer = 20;
				setRotX(0);
				if(madTimer >= 204)
				{
					increasePosition(0, 0.13f, 0);
				}
				else if(madTimer >= 186)
				{
					setRotZ(getRotZ()-5);
					
					if(madTimer <= 195)
					{
						if(madTimer == 195)
						{
							AudioRes.playSound(43, 1, getPosition());
						}
						
						double ang = Math.toRadians(baseRotY);
						Vector3f center = getPosition();
						Vector3f checkPos = new Vector3f(center.x+18*(float)Math.cos(ang), 
														 center.y,
														 center.z-18*(float)Math.sin(ang));
						
						if(player.getY() > getY()-6 && player.getY() < getY() && Math.sqrt(Math.pow(player.getX()-checkPos.x, 2)+Math.pow(player.getZ()-checkPos.z, 2)) < 13)
						{
							//player.takeDamage(getPosition(), 2);
						}
					}
				}
				else if(madTimer > 30)
				{
					//if(player.getIsGroundPounding() && collideTransformed.playerIsOn && player.getyVel() < 0)
					{
						isDead = true;
						setVisibility(false);
						CollisionChecker.removeCollideModel(collideTransformed);
						AudioRes.playSound(43, 1, getPosition());
						double ang = Math.toRadians(baseRotY);
						Vector3f center = getPosition();
						Vector3f checkPos = new Vector3f(center.x+18*(float)Math.cos(ang), 
								 center.y+1,
								 center.z-18*(float)Math.sin(ang));
						for(int i = 360; i != 0; i-=18)
						{
							new Particle(ParticleResources.textureDustCloud,
								new Vector3f(checkPos.x, checkPos.y, checkPos.z),
								new Vector3f((float)Math.cos(Math.toRadians(i+Math.random()*20)), 
										     0.1f,
										     (float)Math.sin(Math.toRadians(i+Math.random()*20))), 
								0, 20, 0, 12, 0.25f);
						}
						
						for(int i = 5; i != 0; i-=1)
						{
							MainGameLoop.gameEntitiesToAdd.add(
									new Ring(new Vector3f(player.getX(), player.getY()+10, player.getZ()), 
											1.5f*(float)Math.random()-0.75f, 
											1+1.5f*(float)Math.random(), 
											1.5f*(float)Math.random()-0.75f));
						}
					}
				}
				else //if(madTimer <= 30)
				{
					setRotZ(getRotZ()+3);
					increasePosition(0, -0.16f, 0);
					
					if(madTimer == 0)
					{
						setRotZ(0);
						setY(getSpawnPosition().y);
					}
				}
			}
			setRotY(baseRotY+rotYOffset);
			updateCollisionModelWithZ();
		}
	}
	
	@Override
	public void respawn()
	{
		super.respawn();
		distance = 0;
		direction = 1;
		setVisibility(true);
		madTimer = 0;
		baseRotY = getRotY();
		rotYOffset = 0;
		turnTimer = 20;
		isDead = false;
	}
	
	@Override 
	public void despawn()
	{
		if(!isDead)
		{
			CollisionChecker.removeCollideModel(collideTransformed);
		}
	}
}
