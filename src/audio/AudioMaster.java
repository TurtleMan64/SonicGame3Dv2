package audio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.openal.WaveData;
import org.newdawn.slick.openal.OggData;
import org.newdawn.slick.openal.OggDecoder;
import org.newdawn.slick.openal.OggInputStream;

public class AudioMaster 
{
	public static void init()
	{
		try 
		{
			AL.create();
			AL10.alDistanceModel(AL11.AL_LINEAR_DISTANCE_CLAMPED);
			AL10.alListenerf(AL10.AL_GAIN, 0.1f);
		} 
		catch (LWJGLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	public static void setListenerData(Vector3f pos)
	{
		//AL10.alListener3f(AL10.AL_POSITION, pos.x, pos.y, pos.z);
		AL10.alListener3f(AL10.AL_POSITION, 0, 0, 0);
		AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
		AudioSources.updateListenerPosition(pos);
	}
	*/
	
	public static void updateListenerData(Vector3f pos, Vector3f vel, float camYaw, float camPitch)
	{
		float xOff = (float)(Math.cos(Math.toRadians(camYaw))*Math.cos(Math.toRadians(camPitch)));
		float zOff = (float)(-Math.sin(Math.toRadians(camYaw))*Math.cos(Math.toRadians(camPitch)));
		float yOff = (float)(Math.sin(Math.toRadians(camPitch)));
		Vector3f at = new Vector3f(xOff, yOff, zOff);
		at = at.normalise(null);
		
		float xOff2 = (float)(Math.cos(Math.toRadians(camYaw))*Math.cos(Math.toRadians(camPitch+90)));
		float zOff2 = (float)(-Math.sin(Math.toRadians(camYaw))*Math.cos(Math.toRadians(camPitch+90)));
		float yOff2 = (float)(Math.sin(Math.toRadians(camPitch+90)));
		Vector3f up = new Vector3f(xOff2, yOff2, zOff2);
		up = up.normalise(null);
		
        FloatBuffer listenerOri = BufferUtils.createFloatBuffer(6).put(
                new float[]{at.x, at.y, at.z, up.x, up.y, up.z}); // at, up vectors? up second?
        FloatBuffer listenerVel = BufferUtils.createFloatBuffer(3).put(
                new float[]{0, 0, 0});
        FloatBuffer listenerPos = BufferUtils.createFloatBuffer(3).put(
                new float[]{pos.x, pos.y, pos.z});
        listenerPos.flip();
        listenerVel.flip();
        listenerOri.flip();
        //System.out.println("pos = "+listenerPos.get(0)+", "+listenerPos.get(1)+", "+listenerPos.get(2));
        AL10.alListener(AL10.AL_POSITION, listenerPos);
        AL10.alListener(AL10.AL_VELOCITY, listenerVel);
        AL10.alListener(AL10.AL_ORIENTATION, listenerOri);
	}
	
	/*
	public static void setListenerOrientation(Vector3f orien)
	{
		/*
		float[] buff = new float[6];
		buff[0] = orien.x;
		buff[1] = orien.y;
		buff[2] = orien.z;
		buff[3] = 0;
		buff[4] = 1;
		buff[5] = 0;
		*/
		/*
		FloatBuffer listOrien = FloatBuffer.allocate(6);
		listOrien.put(orien.x);
		listOrien.put(orien.y);
		listOrien.put(orien.z);
		listOrien.put(0);
		listOrien.put(1);
		listOrien.put(0);
		//listOrien.rewind();
		listOrien.flip();
		AL10.alListener(AL10.AL_ORIENTATION, listOrien);
		
	}
	*/
	
	/*
	public static int loadSound(String path) throws FileNotFoundException
	{
		int buffer = AL10.alGenBuffers();
		buffers.add(buffer);
		WaveData waveFile = WaveData.create(new BufferedInputStream(new FileInputStream(path)));
		//WaveData waveFile = WaveData.create(path);
		AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
		waveFile.dispose();
		return buffer;
	}
	*/
	
	public static int loadSound(String fileName) throws FileNotFoundException
	{
		InputStream is = null;
		//System.out.println("trying to load '"+fileName+"'");
		try
		{
			//is = Class.class.getResourceAsStream(fileName);
			fileName = fileName.substring(1);
			//is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			is = new FileInputStream(fileName);
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
		
	    if (is == null)
	    {
	    	System.out.println("cant load sound '"+fileName+"'");
	    	return 0;
	    }
	    
	    String type = fileName.substring(fileName.length()-3);
	    
	    if (type.contains("wav"))
	    {
	    
		    InputStream bufferedIn = new BufferedInputStream(is);
		    AudioInputStream audioStream = null;
		    try 
		    {
				audioStream = AudioSystem.getAudioInputStream(bufferedIn);
			} 
		    catch (UnsupportedAudioFileException e) 
		    {
				e.printStackTrace();
			} 
		    catch (IOException e) 
		    {
				e.printStackTrace();
			}
		    
		    int buffer = AL10.alGenBuffers();
		    
		    if(audioStream == null)
		    {
		    	System.out.println("CANT DO THIS MAN '"+fileName+"'");
		    }
	    
		    WaveData waveFile = WaveData.create(audioStream);
		    if(waveFile == null)
		    {
		    	System.out.println("still cant load sound '"+fileName+"'");
		    	return 0;
		    }
		    AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
		    waveFile.dispose();
		    return buffer;
	    }
	    else if (type.contains("ogg"))
	    {
	    	int buffer = AL10.alGenBuffers();
	    	
	    	OggDecoder decoder = new OggDecoder();
	    	OggData oggFile;
			try
			{
				oggFile = decoder.getData(is);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				System.out.println("still cant load sound '"+fileName+"'");
		    	return 0;
			}
		    if (oggFile == null)
		    {
		    	System.out.println("still cant load sound '"+fileName+"'");
		    	return 0;
		    }
		    AL10.alBufferData(buffer, AL10.AL_FORMAT_STEREO16, oggFile.data, oggFile.rate);
		    oggFile = null;
		    return buffer;
	    }
	    
	    return 0;
	}
	
	public static void cleanUp()
	{
		AudioSources.deleteSources();
		AudioSources.deleteBuffers();
		AL.destroy();
	}
}
