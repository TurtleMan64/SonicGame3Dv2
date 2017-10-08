package entities;

import models.TexturedModel;
import toolbox.ParticleManager;
import toolbox.ParticleSequence;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;


public class MyParticle extends Entity
{
	public static Camera camera;
	public float gravity;
	public int destructTimer;
	public float xVel;
	public float yVel;
	public float zVel;
	public int special;
	private boolean isSequence;
	private int counter;
	private ParticleSequence sequence;
	private float scale;
	private boolean semiTransparent;
	
	public MyParticle(TexturedModel[] model, Vector3f position, int destructTimer, float scale, float xVel, float yVel, float zVel, float gravity, int special, boolean semiTransparent)
	{
		super(model, new Vector3f(position), 0, -camera.getYaw()+90, -camera.getPitch(), scale);
		this.destructTimer = destructTimer;
		this.xVel = xVel;
		this.yVel = yVel;
		this.zVel = zVel;
		this.gravity = gravity;
		this.special = special;
		isSequence = false;
		counter = 0;
		this.scale = scale;
		this.semiTransparent = semiTransparent;
	}
	
	public MyParticle(ParticleSequence sequence, Vector3f position, float scale, float xVel, float yVel, float zVel, float gravity, boolean semiTransparent)
	{
		super(sequence.getModel(0), new Vector3f(position), 0, -camera.getYaw()+90, -camera.getPitch(), 0);
		this.destructTimer = sequence.getNumModels();
		this.xVel = xVel;
		this.yVel = yVel;
		this.zVel = zVel;
		this.gravity = gravity;
		this.special = 0;
		isSequence = true;
		counter = 0;
		this.sequence = sequence;
		this.scale = scale;
		this.semiTransparent = semiTransparent;
	}
	
	@Override
	public void step()
	{
		super.setRotY(-camera.getYaw()+90);
		super.setRotZ(-camera.getPitch());
		
		if(destructTimer <= 0)
		{
			//if(semiTransparent)
			{
				//MainGameLoop.gameTransEntitiesToDelete.add(this);
			}
			//else
			{
				MainGameLoop.gameEntitiesToDelete.add(this);
			}
		}
		else
		{
			if(isSequence)
			{
				super.setModels(sequence.getModel(counter));
				super.setScale(scale*sequence.getScale(counter));
			}
			
			yVel-=gravity;
			
			super.increasePosition(xVel, yVel, zVel);
			
			if(super.getModels().equals(ParticleManager.modelParticleBubble))
			{
				if(special == 1 && super.getY() > 0 )
				{
					super.setY(0);
				}
			}
		}
		
		destructTimer = Math.max(destructTimer-1,  0);
		counter++;
	}
	
	public static void setCamera(Camera newCamera)
	{
		camera = newCamera;
	}
}
