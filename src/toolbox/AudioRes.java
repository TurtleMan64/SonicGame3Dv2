package toolbox;

import org.lwjgl.openal.AL10;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import entities.Camera;

import java.io.IOException;
import java.util.ArrayList;

public class AudioRes 
{
	public static ArrayList<Audio> gameSounds = new ArrayList<Audio>();
	public static Camera camera;
	private static int currentMusicIndex;
	public static float soundLevel = 0.3f;
	public static float soundLevelBG = 0.0f;//0.1
	
	public static void loadGameSounds()
	{
		//try
		{
			/*
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/step-floor.wav")));//0
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/step-grass.wav")));//1
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/step-longgrass.wav")));//2
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/step-metalcap.wav")));//3
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/step-wetwood.wav")));//4
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/mario-wa.wav")));//5
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/mario-woo.wav")));//6
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/mario-ya.wav")));//7
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/mario-woohoo.wav")));//8
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/mario-yahoo.wav")));//9
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/mario-yah.wav")));//10
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/mario-ugh.wav")));//11
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/fuse-loop.wav")));//12
			//gameSounds.add(AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("res/Audio/BOBIntro.ogg")));//13
			//gameSounds.add(AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("res/Audio/BOBLoop.ogg")));//14
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/swim-below.wav")));//15
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/swim-above.wav")));//16
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/splash.wav")));//17
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/enter-painting.wav")));//18
			//gameSounds.add(AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("res/Audio/JRBIntro.ogg")));//19
			//gameSounds.add(AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("res/Audio/JRBLoop.ogg")));//20
			//gameSounds.add(AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("res/Audio/CCMIntro.ogg")));//21
			//gameSounds.add(AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("res/Audio/CCMLoop.ogg")));//22
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/coin.wav")));//23
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/star_appears.wav")));//24
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/goomba_flattened.wav")));//25
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/goomba_dead.wav")));//26
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/spotted_by_goomba.wav")));//27
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/goomba_step.wav")));//28
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/mario_slide.wav")));//29
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/step_snow.wav")));//30
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/step_wetrock.wav")));//31
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/fuse-loop.wav")));//32
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/explosion.wav")));//33
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/bobomb_step.wav")));//34
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/step_brick.wav")));//35
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/step_grass.wav")));//36
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/step_water.wav")));//37
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/step_neutral.wav")));//38
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/step_sand.wav")));//39
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/mario_groundpoundflip.wav")));//40
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/mario_groundpoundhit.wav")));//41
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/WF_faceblock.wav")));//42
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/ThwompSound.wav")));//43
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/WF_WoodRot.wav")));//44
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/mario_ClimbPole.wav")));//45
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/WF_LiftPlatform.wav")));//46
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/WF_WoodPlank.wav")));//47
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/TreeClimb.wav")));//48
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/CoinGrab.wav")));//49
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/CoinBounce.wav")));//50
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/mario_GrabPole.wav")));//51
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/CoinGrabRed.wav")));//52
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/WF_TowerLift.wav")));//53
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/WF_FallingBlock.wav")));//54
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/whomp_step.wav")));//55
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/sonic/S1/S1_Jump.wav")));//56  actually 50
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/sonic/S1/S1_SpindashCharge.wav")));//57  actually 51
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/sonic/S1/S1_SpindashRelease.wav")));//58  actually 52
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/sonic/S1/S1_Skid.wav")));//59  actually 53
			gameSounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/Audio/sonic/S1/S1_Spring.wav")));//60  actually 54
			*/
		}
		//catch (IOException e) 
	    {
			//e.printStackTrace();
	    }
	}
	
	public static void setCamera(Camera newCamera)
	{
		camera = newCamera;
	}
	
	public static void playSound(int index, float pitch, Vector3f soundOrigin)
	{
		/*
		if(index >= 0 && index >= 50)
		{
			if(gameSounds.size() > index)
			{
				float factor = getFactor(soundOrigin, camera.getPosition());
				if(factor > 0.02)
				{
					gameSounds.get(index).playAsSoundEffect(pitch, factor*soundLevel, false);
				}
				else
				{
					System.out.println("Soudn too far away");
				}
			}
			else
			{
				System.out.println("Soudn index 2");
			}
		}
		else
		{
			System.out.println("Sound out of index");
		}
		*/
	}
	
	public static void playSoundRepeat(int index, Vector3f soundOrigin)
	{
		if(index >= 0)
		{
			if(gameSounds.size() > index)
			{
				if(gameSounds.get(index).isPlaying() == false)
				{
					float factor = getFactor(soundOrigin, camera.getPosition());
					if(factor > 0.02)
					{
						gameSounds.get(index).playAsSoundEffect(1.0f, factor*soundLevel, false);
					}
				}
			}
		}
	}
	
	public static void playMusic(int index, boolean loop)
	{
		if(index >= 0)
		{
			if(gameSounds.size() > index)
			{
				gameSounds.get(index).playAsSoundEffect(1.0f, soundLevelBG, loop);
				currentMusicIndex = index;
			}
		}
		else
		{
			gameSounds.get(currentMusicIndex).stop();
		}
	}
	
	public static void playSoundLoop(int index, Vector3f soundOrigin)
	{
		if(index >= 0)
		{
			if(gameSounds.size() > index)
			{
				if(gameSounds.get(index).isPlaying() == false)
				{
					float factor = getFactor(soundOrigin, camera.getPosition());
					if(index == 46)
					{
						System.out.println(factor);
					}
					if(factor > 0.02)
					{
						gameSounds.get(index).playAsSoundEffect(1.0f, factor*soundLevel, true);
					}
				}
			}
		}
	}
	
	public static void playSoundLoopPitch(int index, float pitch, Vector3f soundOrigin)
	{
		if(index >= 0)
		{
			if(gameSounds.size() > index)
			{
				if(gameSounds.get(index).isPlaying() == false)
				{
					float factor = getFactor(soundOrigin, camera.getPosition());
					if(factor > 0.02)
					{
						gameSounds.get(index).playAsSoundEffect(pitch, factor*soundLevel, true);
					}
				}
			}
		}
	}
	
	public static void stopSound(int index)
	{
		if(gameSounds.size() > index)
		{
			if(gameSounds.get(index).isPlaying())
			{
				gameSounds.get(index).stop();
			}
		}
	}
	
	public static void stopAllSFX()
	{
		//gameSounds.get(0).getBufferID();
	}
	
	
	public static float getFactor(Vector3f point1, Vector3f point2)
	{
		float factor = 0;
		float distance = (float)Math.sqrt(Math.pow(point1.getX()-point2.getX(),2)+
						 Math.pow(point1.getY()-point2.getY(),2)+
						 Math.pow(point1.getZ()-point2.getZ(),2));
		//System.out.println(distance);
		
		if(distance <= 113)
		{
			factor = 1;
		}
		else
		{
			factor = (float)30/(distance-113);  //10/di
			factor = Math.min(factor, 1);
			factor = Math.max(factor, 0);
		}
		
		return factor;
	}
}
