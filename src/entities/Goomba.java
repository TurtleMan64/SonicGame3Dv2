package entities;

import org.lwjgl.util.vector.Vector3f;

import animation.AnimationResources;
import animation.Body;
import animation.Limb;
import collision.CollisionChecker;
import collision.Triangle3D;
import engineTester.MainGameLoop;
import models.TexturedModel;
import particles.Particle;
import particles.ParticleResources;
import toolbox.AudioRes;
import toolbox.ConvenientMethods;
import toolbox.ParticleManager;

public class Goomba extends Entity
{
	private static TexturedModel[] modelSquished = null;//ConvenientMethods.loadModel("Models/Goomba/", "Squished", MainGameLoop.gameLoader);
	private static TexturedModel[] modelBody = null;//ConvenientMethods.loadModel("Models/Goomba/", "Body", MainGameLoop.gameLoader);
	private static TexturedModel[] modelFoot = null;//ConvenientMethods.loadModel("Models/Goomba/", "Foot", MainGameLoop.gameLoader);
	private Player player;
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
	private boolean isDead;
	
	private Body myBody;
	private Limb myLeftFoot;
	private Limb myRightFoot;
	
	public Goomba(Vector3f position, Player player) 
	{
		super(modelSquished, position);
		this.player = player;
		xVel = 0;
		zVel = 0;
		yVel = 0;
		furyTimer = 0;
		furyTimerMax = 160;
		triCol = null;
		inAir = true;
		clock = 0;
		stompedTimer = -1;
		hitboxH = 3.2f*1.3f;
		hitboxV = 6f*1.3f;
		myBody = new Body(modelBody);
		myLeftFoot = new Limb(modelFoot, -1f, 0.75f, -1, myBody, null);
		myRightFoot = new Limb(modelFoot, -1f, 0.75f, 1, myBody, null);
		setVisibility(false);
		myBody.setVisibility(true);
		myLeftFoot.setVisibility(true);
		myRightFoot.setVisibility(true);
		AnimationResources.assignAnimationsGoomba(myBody, myLeftFoot, myRightFoot);
		animIndex = 0;
		isDead = false;
	}
	
	@Override
	public void step()
	{
		if(isDead)
		{
			//System.out.println("ded");
			return;
		}
		
		clock++;
		furyTimer = Math.max(0, furyTimer-1);
		if(stompedTimer > 0)
		{
			stompedTimer-=1;
			furyTimer = -1;
			//xVel = 0;
			//zVel = 0;
		}
		
		yVel = Math.max(-2, yVel-0.1f);
		
		float xDiff = player.getX()-getX();
		float zDiff = player.getZ()-getZ();
		float angleRad = (float)Math.toRadians(getRotY());
		float newZ = (float)((xDiff)*-Math.sin(angleRad) - (zDiff)*Math.cos(angleRad));
		float mag = (float)Math.sqrt((xDiff*xDiff)+(zDiff*zDiff));
		
		applyFriction(1.06f);
		
		if(!inAir && triCol.getNormal().y >= 0.5f)
		{
			float pushUpValue = -getY()+(float)(   ((-triCol.getA()*(getX()+xVel))  +  
											(-triCol.getC()*(getZ()+zVel))  -  
											(triCol.getD())) / triCol.getB());
			yVel = pushUpValue;
		}
		
		if(mag < 64)
		{
			if(furyTimer == 0)
			{
				furyTimer = furyTimerMax;
				setRotY((float)Math.toDegrees(Math.atan2(-zDiff, xDiff)));
				AudioRes.playSound(27, 1, getPosition()); //goomba spots mario
				xVel = 0;
				zVel = 0;
				yVel = 1;
				increasePosition(0, 1, 0);
			}
		}
		else
		{
			if(mag < 500 && furyTimer == 0)
			{
				float ran = (float)Math.random();
				if(ran < 0.01)
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
		
		if(furyTimer > 30)
		{
			if(newZ >= 2.8f)
			{
				super.increaseRotation(0, 2.8f, 0);
			}
			else if(newZ <= -2.8f)
			{
				super.increaseRotation(0, -2.8f, 0);
			}
			
			if(furyTimer < furyTimerMax-30)
			{
				moveMe();
			}
		}
		
		if(stompedTimer > 0 && !getVisibility())
		{
			increasePosition(xVel, yVel, zVel);
		}
		
		if(mag < 500 && stompedTimer == -1)
		{
			if(player.getX() > getX()-hitboxH-player.getHitboxHorizontal() && player.getX() < getX()+hitboxH+player.getHitboxHorizontal() &&
					   player.getZ() > getZ()-hitboxH-player.getHitboxHorizontal() && player.getZ() < getZ()+hitboxH+player.getHitboxHorizontal())
			{
				if(player.getY() > getY()-player.getHitboxVertical() && player.getY() < getY()+hitboxV ||
				  (player.getIsGroundPounding() && player.getY() > getY()-(0.5f*player.getHitboxVertical()) && player.getY() < getY()+hitboxV+(0.5f*player.getHitboxVertical())))
				{
					if((player.getyVel() < 0 && player.getInAir() == true && !player.getIsDiving() && !player.getIsSliding()) || player.getIsGroundPounding())
					{
						if(player.isVulnerable())
						{
							stompedTimer = 30;
							xVel = 0;
							zVel = 0;
							AudioRes.playSound(25, 1, getPosition()); //goomba stomped
							if(!player.getIsGroundPounding())
							{
								player.setyVel(1.3f);
							}
							setVisibility(true);
							myBody.setVisibility(false);
							myLeftFoot.setVisibility(false);
							myRightFoot.setVisibility(false);
						}
					}
					else if(player.getIsDiving() || player.getIsSliding())
					{
						if(player.isVulnerable())
						{
							stompedTimer = 30;
							AudioRes.playSound(25, 1, getPosition()); //goomba stomped
							//setVisibility(true);
							yVel = 2f;
							increasePosition(0, 1, 0);
							xVel = (float)(3*Math.random()-1.5f);
							zVel = (float)(3*Math.random()-1.5f);
							//myBody.setVisibility(false);
							//myLeftFoot.setVisibility(false);
							//myRightFoot.setVisibility(false);
						}
					}
					else
					{
						//damage player
						player.takeDamage(getPosition(), 1);
					}
				}
			}
			
			if(CollisionChecker.checkCollision(getX(), getY(), getZ(), getX()+xVel, getY()+yVel, getZ()+zVel) == false)
			{
				//increasePosition(xVel, yVel, zVel);
				//inAir = true;
				
				if(CollisionChecker.checkCollision(getX()+xVel, getY()+yVel, getZ()+zVel, getX()+xVel, getY()+yVel-1f, getZ()+zVel) == true)
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
						super.setX(triColPosition.x);
						super.setY(triColPosition.y+colYNormal*0.5f);
						super.setZ(triColPosition.z);
					}
					else
					{
						if(colYNormal >= 0.5f)
						{
							super.setX(triColPosition.x);
							super.setY(triColPosition.y+colYNormal*0.5f);
							super.setZ(triColPosition.z);
							xVel+=colXNormal*0.05f;
							zVel+=colZNormal*0.05f;
						}
						else
						{
							super.setX(triColPosition.x+colXNormal*0.5f);
							super.setY(triColPosition.y+colYNormal*0.5f);
							super.setZ(triColPosition.z+colZNormal*0.5f);
							xVel = 0;
							zVel = 0;
							setRotY(getRotY()+180);
						}
					}
				}
				else
				{
					if(inAir)
					{
						increasePosition(xVel, yVel, zVel);
					}
					else if(furyTimer > 0)
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
		
		if(getY() < -6)
		{
			AudioRes.playSound(17, 1, getPosition()); //splash
			AudioRes.playSound(26, 1, getPosition()); //goomba death
			//MainGameLoop.gameEntitiesToDelete.add(this);
			setVisibility(false);
			isDead = true;
			System.out.println("deleting stuff");
			MainGameLoop.gameEntitiesToDelete.add(myBody);
			MainGameLoop.gameEntitiesToDelete.add(myLeftFoot);
			MainGameLoop.gameEntitiesToDelete.add(myRightFoot);
			
			//make splash
			for(int i = 0; i < 18; i++)
			{
				MainGameLoop.gameEntitiesToAdd.add(new MyParticle(
						ParticleManager.modelParticleBubble, 
						new Vector3f(getX(), getY(), getZ()), 
						60, 2, (float)Math.random()-0.5f, 
						       (float)(Math.random()), 
						       (float)Math.random()-0.5f, 
						       0.05f, 0, false));
			}
		}
		
		if(stompedTimer == 0 && isDead == false)
		{
			AudioRes.playSound(26, 1, getPosition()); //goomba death
			//MainGameLoop.gameEntitiesToDelete.add(this);
			setVisibility(false);
			isDead = true;
			//System.out.println("deleting stuff");
			MainGameLoop.gameEntitiesToDelete.add(myBody);
			MainGameLoop.gameEntitiesToDelete.add(myLeftFoot);
			MainGameLoop.gameEntitiesToDelete.add(myRightFoot);
			//create some dust
			for(int i = 30; i != 0; i--)
			{
				//MainGameLoop.gameEntitiesToAdd.add(new MyParticle(
				//	ParticleManager.particleSequenceDustCloud, 
				//		new Vector3f(getX()+20*(float)Math.random()-10f,
				//			         getY()+20*(float)Math.random()-10f,
				//			         getZ()+20*(float)Math.random()-10f),
				//		(float)(Math.random()*6)+1, 0, 0, 0, 0, false));
				
				new Particle(ParticleResources.textureDustCloud, 
						new Vector3f(getX()+20*(float)Math.random()-10f,
						         getY()+20*(float)Math.random()-10f,
						         getZ()+20*(float)Math.random()-10f), new Vector3f(0, 0, 0), 
						0, 60, 0, 10*(float)Math.random()+2, -0.5f);
			}
		}
		
		float speed = (float)Math.sqrt((xVel*xVel)+(zVel*zVel));
		float increaseValue = speed*9+1;
		if(animIndex%50 < 25 && (animIndex%50)+increaseValue >= 25)
		{
			AudioRes.playSound(28, 1+speed*0.1f, getPosition()); //goomba step
		}
		
		animIndex+=increaseValue;
		if(speed == 0)
		{
			animIndex = 25;
		}
		myBody.setBaseOrientation(getPosition(), getRotY(), getRotZ()-20*speed);
		updateLimbs(0, animIndex%100);
	}
	
	private void moveMe()
	{
		xVel += (float)(0.058*Math.cos(Math.toRadians(-getRotY())));
		zVel += (float)(0.058*Math.sin(Math.toRadians(-getRotY())));
	}
	
	private void moveMeSlow()
	{
		xVel += (float)(0.004*Math.cos(Math.toRadians(-getRotY())));
		zVel += (float)(0.004*Math.sin(Math.toRadians(-getRotY())));
	}
	
	private void applyFriction(float frictionToApply)
	{
		xVel = xVel/frictionToApply;
		zVel = zVel/frictionToApply;
	}
	
	public void updateLimbs(int animIndex, float time)
	{
		myBody.animationIndex = animIndex;
    	myLeftFoot.animationIndex = animIndex;
    	myRightFoot.animationIndex = animIndex;
    	myBody.update(time);
    	myLeftFoot.update(time);
    	myRightFoot.update(time);
	}
	
	@Override
	public void respawn()
	{
		super.respawn();
		setVisibility(false);
		myBody.setVisibility(true);
		myLeftFoot.setVisibility(true);
		myRightFoot.setVisibility(true);
		isDead = false;
		stompedTimer = -1;
		furyTimer = 0;
		xVel = 0;
		zVel = 0;
		yVel = 0;
		triCol = null;
		inAir = true;
		clock = 0;
		/*
		for(int i = 0; i < MainGameLoop.gameEntities.size(); i++)
		{
			Entity currEntity = MainGameLoop.gameEntities.get(i);
			if(currEntity == myBody || currEntity == myLeftFoot || currEntity == myRightFoot)
			{
				MainGameLoop.gameEntities.remove(i);
				System.out.println("extra limb, deleting");
				i--;
			}
		}
		*/
		MainGameLoop.gameEntitiesToAdd.add(myBody);
		MainGameLoop.gameEntitiesToAdd.add(myLeftFoot);
		MainGameLoop.gameEntitiesToAdd.add(myRightFoot);
	}
	
	@Override
	public void despawn()
	{
		MainGameLoop.gameEntitiesToDelete.add(myBody);
		MainGameLoop.gameEntitiesToDelete.add(myLeftFoot);
		MainGameLoop.gameEntitiesToDelete.add(myRightFoot);
	}
}
