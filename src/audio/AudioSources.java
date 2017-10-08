package audio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.lwjgl.openal.AL10;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;



public class AudioSources 
{
	private static ArrayList<Source> sources = new ArrayList<Source>();
	private static ArrayList<Integer> buffers = new ArrayList<Integer>();
	public static float soundLevel = 1f;
	public static float soundLevelBG = 1f;
	//public static Vector3f listener = new Vector3f(0, 0, 0);
	
	public static void loadSoundBuffers()
	{
		try
		{
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/S1/S1_FootstepDummy1.wav"))); //0
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/S1/S1_FootstepDummy2.wav"))); //1
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/S1/S1_FootstepDummy3.wav"))); //2
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/S1/S1_FootstepDummy4.wav"))); //3
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/S1/S1_FootstepDummy5.wav"))); //4
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/S1/S1_FootstepDummy6.wav"))); //5
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/S1/mono/S1_Jump.wav")));           //6
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/S1/mono/S1_SpindashCharge.wav"))); //7
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/S1/mono/S1_SpindashRelease.wav")));//8
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/S1/mono/S1_Skid.wav")));           //9
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/S1/mono/S1_Spring.wav")));         //10
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/S1/mono/S1_Ring.wav")));           //11
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/SADX/Dashpad.wav")));              //12
			buffers.add(AudioMaster.loadSound(("/res/Audio/chao/040.Synth_b03_wk_btl_j_b03_wk_btl_j003040_wav.wav")));              //13
			buffers.add(AudioMaster.loadSound(("/res/Audio/chao/041.Synth_b03_wk_btl_j_b03_wk_btl_j003041_wav.wav")));              //14
			buffers.add(AudioMaster.loadSound(("/res/Audio/chao/042.Synth_b03_wk_btl_j_b03_wk_btl_j003042_wav.wav")));              //15
			buffers.add(AudioMaster.loadSound(("/res/Audio/chao/047.Synth_b03_wk_btl_j_b03_wk_btl_j003047_wav.wav")));              //16
			buffers.add(AudioMaster.loadSound(("/res/Audio/chao/048.Synth_b03_wk_btl_j_b03_wk_btl_j003048_wav.wav")));              //17
			buffers.add(AudioMaster.loadSound(("/res/Audio/chao/053.Synth_b03_wk_btl_j_b03_wk_btl_j003053_wav.wav")));              //18
			buffers.add(AudioMaster.loadSound(("/res/Audio/chao/060.Synth_b03_wk_btl_j_b03_wk_btl_j003060_wav.wav")));              //19
			buffers.add(AudioMaster.loadSound(("/res/Audio/chao/061.Synth_b03_wk_btl_j_b03_wk_btl_j003061_wav.wav")));              //20
			buffers.add(AudioMaster.loadSound(("/res/Audio/chao/062.Synth_b03_wk_btl_j_b03_wk_btl_j003062_wav.wav")));              //21
			buffers.add(AudioMaster.loadSound(("/res/Audio/chao/067.Synth_b03_wk_btl_j_b03_wk_btl_j003067_wav.wav")));              //22
			buffers.add(AudioMaster.loadSound(("/res/Audio/chao/068.Synth_b03_wk_btl_j_b03_wk_btl_j003068_wav.wav")));              //23
			buffers.add(AudioMaster.loadSound(("/res/Audio/chao/073.Synth_b03_wk_btl_j_b03_wk_btl_j003073_wav.wav")));              //24
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/SA2/SA2_HomingAttack.wav")));                                      //25
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/SA2/SA2_Bounce.wav")));                                            //26
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/Sonic3K/S3K_gethit.wav")));                                        //27
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/Sonic3K/S3K_itembox.wav")));                                       //28
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/S1/S1_Goal.wav")));                                                //29
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/S1/S1_Splash.wav")));                                              //30
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/S1/S1_BigDestroy.wav")));                                          //31
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/SGen/StompInit.wav")));                                            //32
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/SGen/StompLand.wav")));                                            //33
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/S1/S1_Death.wav")));                                               //34
			buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/S1/S1_UnlockSomething.wav")));                                     //35
			//buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/SADX/AzureBlueWorld.ogg")));                                       //36
			//buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/SADX/SpeedHighway.wav")));                                         //37
			//buffers.add(AudioMaster.loadSound(("/res/Audio/sonic/SADX/SandHill.wav")));                                             //38
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.err.println("Error: could not find a sound file");
		}
		
		loadSoundFile();
	}
	
	public static void deleteSources()
	{
		for(Source src: sources)
		{
			src.delete();
		}
	}
	
	public static void deleteBuffers()
	{
		for(Integer buff: buffers)
		{
			AL10.alDeleteBuffers(buff);
		}
	}
	
	public static void createSources()
	{
		sources.add(new Source(1, 100, 600));//sound effect
		sources.add(new Source(1, 100, 600));//sound effect
		sources.add(new Source(1, 100, 600));//sound effect
		sources.add(new Source(1, 100, 600));//sound effect
		sources.add(new Source(1, 100, 600));//sound effect
		sources.add(new Source(1, 100, 600));//sound effect
		//sources.add(new Source(22, 125, 1000000));//sound effect
		sources.add(new Source(1, 100, 600));
		sources.add(new Source(1, 100, 600));
		sources.add(new Source(1, 100, 600));
		sources.add(new Source(1, 100, 600));
		sources.add(new Source(1, 100, 600));
		sources.add(new Source(1, 100, 600));
		sources.add(new Source(1, 100, 600));
		sources.add(new Source(1, 100, 600));
		sources.add(new Source(1, 0, 0));//background music
	}
	
	//public static void updateListenerPosition(Vector3f newPos)
	{
		//listener.set(newPos);
	}
	
	//with position
	public static Source play(int buffer, Vector3f pos)
	{
		return play(buffer, pos, 1, false, new Vector3f(0, 0, 0));
	}
	
	//with position and pitch
	public static Source play(int buffer, Vector3f pos, float pitch)
	{
		return play(buffer, pos, pitch, false, new Vector3f(0, 0, 0));
	}
	
	//with position and pitch and loop
	public static Source play(int buffer, Vector3f pos, float pitch, boolean loop)
	{
		return play(buffer, pos, pitch, loop, new Vector3f(0, 0, 0));
	}
	
	//with everything
	public static Source play(int buffer, Vector3f pos, float pitch, boolean loop, Vector3f vel)
	{
		for(int i = 0; i < 14; i++)
		{
			Source src = sources.get(i);
			if (!src.isPlaying())
			{
				//System.out.println("sound "+buffer+" playing on source "+sources.indexOf(src));
				//src.setVolume(soundLevel*getFactor(pos, listener));
				src.setVolume(1);
				src.setLooping(loop);
				src.setPosition(pos);
				//src.setPosition(new Vector3f(0, 0, 0));
				src.setPitch(pitch);
				src.setVelocity(vel);
				src.play(buffers.get(buffer));
				return src;
			}
		}
		//System.out.println("no sources to play music");
		return null;
	}
	
	public static Source playBGM(int buffer)
	{
		Source src = sources.get(14);
		//System.out.println("playing = "+src.getLastPlayedBufferID());
		//System.out.println("new = "+buffers.get(buffer));
		//System.out.println();
		
		if (!src.isPlaying() || src.getLastPlayedBufferID() != buffers.get(buffer))
		{
			//System.out.println("in here");
			src.setLooping(true);
			src.setVolume(soundLevelBG);
			src.play(buffers.get(buffer));
		}
		return src;
	}
	
	/*
	public static void pause(int i)
	{
		sources.get(i).pause();
	}
	
	public static void continuePlaying(int i)
	{
		sources.get(i).continuePlaying();
	}
	
	public static void stop(int i)
	{
		sources.get(i).stop();
	}
	
	public static void setVelocity(int i, float x, float y, float z)
	{
		sources.get(i).setVelocity(x, y, z);
	}
	
	public static void setLooping(int i, boolean loop)
	{
		sources.get(i).setLooping(loop);
	}
	
	public static boolean isPlaying(int i)
	{
		return sources.get(i).isPlaying();
	}
	
	public static void setVolume(int i, float volume)
	{
		sources.get(i).setVolume(volume);
	}
	
	public static void setPitch(int i, float pitch)
	{
		sources.get(i).setPitch(pitch);
	}
	
	public static void setPosition(int i, Vector3f position)
	{
		sources.get(i).setPosition(position.x, position.y, position.z);
	}
	*/
	
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
			factor = (float)25/(distance-113);  //10/di
			factor = Math.min(factor, 1);
			factor = Math.max(factor, 0);
		}
		
		return factor;
	}
	
	public static Source getSource(int i)
	{
		return sources.get(i);
	}
	
	private static void loadSoundFile()
	{
		try
		{
	    	InputStreamReader isr = null;
	    	try
	        {
	    		String fileName = "Data/AudioSettings.ini";
	            FileInputStream inStream = new FileInputStream(new File(fileName));
	            isr = new InputStreamReader(inStream);
	        }
	        catch (NullPointerException e)
	        {
	        	System.out.println("Couldnt load input stream: 'Data/AudioSettings.ini'");
	            return;
	        }
	    	
	        BufferedReader breader = new BufferedReader(isr);
			
			Scanner reader = new Scanner(breader);
			
			boolean loop = true;
			while (loop)
			{
				if (!reader.hasNextLine())
				{
					loop = false;
					break;
				}
				String line = reader.nextLine();
				if (line == null)
				{
					loop = false;
					break;
				}
				String[] info = line.split(" ");
				if (info.length >= 2)
				{
					if (info[0].equals("SFX_Volume"))
					{
						soundLevel = Float.parseFloat(info[1]);
					}
					else if (info[0].equals("Music_Volume"))
					{
						soundLevelBG = Float.parseFloat(info[1]);
					}
				}
			}
			
			reader.close();
		}
		catch (Exception e)
		{
			System.out.println("Problem when trying to read 'Data/AudioSettings.ini'");
			e.printStackTrace();
		}
	}
}
