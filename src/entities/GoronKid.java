package entities;

import org.lwjgl.util.vector.Vector3f;

import audio.AudioSources;
import audio.Source;
import engineTester.MainGameLoop;
import models.TexturedModel;
import particles.Particle;
import particles.ParticleResources;
import particles.ParticleTexture;
import toolbox.ConvenientMethods;

public class GoronKid extends Entity
{
	private static TexturedModel[] modelGoron = null;
	public static boolean found;
	private int cryTimer = 0;
	private Source crySource;
	
	private static final float modelScale = 1.5f;
	
	public GoronKid(Vector3f position) 
	{
		super(modelGoron, position, 0, 0, 0, modelScale);
		setVisibility(true);
		found = false;
		crySource = null;
		GoalSign.addTrigger();
	}
	
	@Override
	public void step()
	{
		if (found == false)
		{
			float xDiff = MainGameLoop.gamePlayer.getX()-getX();
			float zDiff = MainGameLoop.gamePlayer.getZ()-getZ();
			float yDiff = MainGameLoop.gamePlayer.getY()-getY();
			float mag = (float)Math.sqrt((xDiff*xDiff)+(zDiff*zDiff)+(yDiff*yDiff));
			
			cryTimer = (cryTimer+1) % 255;
			
			if (mag < 1000)
			{
				ParticleTexture tex = ParticleResources.textureTear1;
				if (Math.random() >= 0.5)
				{
					tex = ParticleResources.textureTear2;
				}
				
				new Particle(tex, 
						new Vector3f(getX()+1.77f*modelScale, getY()+7.236f*modelScale, getZ()+0.632f*modelScale),
						new Vector3f((float)(0.5*(Math.random()-0.5)), 1+(float)(0.5*(Math.random()-0.5)), 0.5f+(float)(0.5*(Math.random()-0.5))),
						0.08f, 120, 0, (float)Math.random()+0.6f, 0);
				
				new Particle(tex, 
						new Vector3f(getX()+1.77f*modelScale, getY()+7.236f*modelScale, getZ()-0.632f*modelScale),
						new Vector3f((float)(0.5*(Math.random()-0.5)), 1+(float)(0.5*(Math.random()-0.5)), -0.5f+(float)(0.5*(Math.random()-0.5))),
						0.08f, 120, 0, (float)Math.random()+0.6f, 0);
				
				if (cryTimer == 0)
				{
					int index = (int)(Math.random()*3);
					crySource = AudioSources.play(36+index, getPosition());
				}
				
				if (mag < 10)
				{
					if (crySource != null && crySource.isPlaying())
					{
						if (crySource.getLastPlayedBufferID() == 36 ||
							crySource.getLastPlayedBufferID() == 37 ||
							crySource.getLastPlayedBufferID() == 38)
						{
							crySource.stop();
						}
					}
					
					AudioSources.play(39, getPosition());
					
					found = true;
					setVisibility(false);
					GoalSign.removeTrigger();
				}
			}
		}
	}
	
	public static void allocateStaticModels()
	{
		if (modelGoron == null)
		{
			modelGoron = ConvenientMethods.loadModel("Models/Gorons/", "Kid");
		}
	}
	
	public static void freeStaticModels()
	{
		if (modelGoron != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelGoron);
			modelGoron = null;
		}
	}
}
