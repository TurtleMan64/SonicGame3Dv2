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

public class BobOmb extends Entity
{
	private static TexturedModel[] modelSquished = null;//ConvenientMethods.loadModel("Models/Goomba/", "Squished", MainGameLoop.gameLoader);
	private static TexturedModel[] modelBody = null;//ConvenientMethods.loadModel("Models/BobOmb/", "Body", MainGameLoop.gameLoader);
	private static TexturedModel[] modelFoot = null;//ConvenientMethods.loadModel("Models/BobOmb/", "Foot", MainGameLoop.gameLoader);
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
	
	public BobOmb(Vector3f position, Player player) 
	{
		super(modelSquished, position);
		this.player = player;
		xVel = 0;
		zVel = 0;
		yVel = 0;
		furyTimer = -1;
		furyTimerMax = 220;
		triCol = null;
		inAir = true;
		clock = 0;
		stompedTimer = -1;
		hitboxH = 4;
		hitboxV = 4;
		myBody = new Body(modelBody);
		myLeftFoot = new Limb(modelFoot, 0, -1.9f*1.3f, 1.2f*1.3f, myBody, null);
		myRightFoot = new Limb(modelFoot, 0, -1.9f*1.3f, -1.2f*1.3f, myBody, null);
		setVisibility(false);
		myBody.setVisibility(true);
		myLeftFoot.setVisibility(true);
		myRightFoot.setVisibility(true);
		AnimationResources.assignAnimationsBobOmb(myBody, myLeftFoot, myRightFoot);
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
		//furyTimer = Math.max(0, furyTimer-1);
		if(furyTimer > 0)
		{
			//stompedTimer-=1;
			furyTimer -= 1;
			//xVel = 0;
			//zVel = 0;
		}
		
		yVel = Math.max(-2, yVel-0.1f);
		
		float xDiff = player.getX()-getX();
		float zDiff = player.getZ()-getZ();
		float angleRad = (float)Math.toRadians(getRotY());
		float newZ = (float)(-(zDiff)*Math.cos(angleRad) - (xDiff)*Math.sin(angleRad));
		float newX = (float)((xDiff)*Math.cos(angleRad) - (zDiff)*Math.sin(angleRad));
		float mag = (float)Math.sqrt((xDiff*xDiff)+(zDiff*zDiff));
		
		applyFriction(1.1f);
		
		if(!inAir && triCol.getNormal().y >= 0.5f)
		{
			float pushUpValue = -getY()+(float)(   ((-triCol.getA()*(getX()+xVel))  +  
											(-triCol.getC()*(getZ()+zVel))  -  
											(triCol.getD())) / triCol.getB());
			yVel = pushUpValue;
		}
		
		if(mag < 48)
		{
			if(furyTimer == -1)
			{
				furyTimer = furyTimerMax;
				setRotY((float)Math.toDegrees(Math.atan2(-zDiff, xDiff)));
				AudioRes.playSoundLoop(32, getPosition()); //bobomb spots mario
				//xVel = 0;
				//zVel = 0;
				//yVel = 1;
				//increasePosition(0, 1, 0);
			}
		}
		else
		{
			if(mag < 500 && furyTimer == -1)
			{
				float ran = (float)Math.random();
				if(ran < 0.01)
				{
					if(ran < 0.003 && clock > 30)
					{
						xVel = 0;
						zVel = 0;
						//yVel = 1;
						setRotY((float)Math.random()*360);
						clock = 0;
					}
					else
					{
						increaseRotation(0, (float)(Math.random()*120-60), 0);
					}
				}
				
				if(clock > 30 && furyTimer == -1)
				{
					moveMeSlow();
				}
			}
		}
		
		if(furyTimer > 0)
		{
			if(newZ >= 5f)
			{
				super.increaseRotation(0, 5f, 0);
			}
			else if(newZ <= -5f)
			{
				super.increaseRotation(0, -5f, 0);
			}
			else if(newX <= 0)
			{
				if(newZ >= 0)
				{
					super.increaseRotation(0, 5f, 0);
				}
				else
				{
					super.increaseRotation(0, -5f, 0);
				}
			}
			moveMe();
		}
		
		if(mag < 500 && furyTimer != 0)
		{
			if(CollisionChecker.checkCollision(getX(), getY(), getZ(), getX()+xVel, getY()+yVel, getZ()+zVel) == false)
			{
				increasePosition(xVel, yVel, zVel);
				inAir = true;
				
				if(CollisionChecker.checkCollision(getX(), getY(), getZ(), getX(), getY()-1f, getZ()) == true)
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
		
		if(getY() < -2)
		{
			AudioRes.playSound(17, 1, getPosition()); //splash
			//AudioRes.playSound(26, getPosition()); //bobomb death
			AudioRes.stopSound(32); //stop fuse sound
			//MainGameLoop.gameEntitiesToDelete.add(this);
			setVisibility(false);
			isDead = true;
			//System.out.println("deleting stuff");
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
		
		if(furyTimer == 0 && isDead == false)
		{
			AudioRes.playSound(33, 1, getPosition()); //bobomb death explosion
			AudioRes.stopSound(32); //stop fuse sound
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
				//		new Vector3f(getX()+40*(float)Math.random()-20f,
				//			         getY()+40*(float)Math.random()-20f,
				//			         getZ()+40*(float)Math.random()-20f),
				//		(float)(Math.random()*6)+1, 0, 0, 0, 0, false));
				
				new Particle(ParticleResources.textureDustCloud, 
						new Vector3f(getX()+40*(float)Math.random()-20f,
						         getY()+40*(float)Math.random()-20f,
						         getZ()+40*(float)Math.random()-20f), new Vector3f(0, 0, 0), 
						0, 60, 0, 15*(float)Math.random()+10, -0.5f);
			}
			
			//now damage player
			float yDiff = player.getY()-getY();
			if(player.isVulnerable() && Math.sqrt((xDiff*xDiff)+(zDiff*zDiff)+(yDiff*yDiff)) < 20)
			{
				player.takeDamage(getPosition(), 1);
			}
		}
		
		if(furyTimer > 0)
		{
			//MainGameLoop.gameEntitiesToAdd.add(new MyParticle(
			//		ParticleManager.particleSequenceDustCloudReverse, 
			//			new Vector3f(getX(), getY()+12, getZ()),
			//			(float)(Math.random()*2)+3, 0, 1.5f, 0, 0, false));
			
			new Particle(ParticleResources.textureDustCloud, 
					new Vector3f(getX(), getY()+12, getZ()), new Vector3f(0, 1f, 0), 
					0, 20, 0, 0.5f*(float)Math.random()+0.75f, 0.75f);
		}
		
		float speed = (float)Math.sqrt((xVel*xVel)+(zVel*zVel));
		float increaseValue = speed*9+1;
		if(animIndex%50 < 25 && (animIndex%50)+increaseValue >= 25)
		{
			AudioRes.playSound(34, 1+speed*0.1f, getPosition()); //bobomb step
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
		xVel += (float)(0.1*Math.cos(Math.toRadians(-getRotY())));
		zVel += (float)(0.1*Math.sin(Math.toRadians(-getRotY())));
	}
	
	private void moveMeSlow()
	{
		xVel += (float)(0.01*Math.cos(Math.toRadians(-getRotY())));
		zVel += (float)(0.01*Math.sin(Math.toRadians(-getRotY())));
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
		furyTimer = -1;
		xVel = 0;
		zVel = 0;
		yVel = 0;
		triCol = null;
		inAir = true;
		clock = 0;
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
