package audio;

import org.lwjgl.openal.AL10;
import org.lwjgl.util.vector.Vector3f;

public class Source 
{
	private int sourceId;
	//private Vector3f myPos;
	private int bufferID; //buffer id of last played audio
	
	public Source(float rolloff, float referencedist, float max)
	{
		sourceId = AL10.alGenSources();
		AL10.alSourcef(sourceId, AL10.AL_ROLLOFF_FACTOR, rolloff);
		AL10.alSourcef(sourceId, AL10.AL_REFERENCE_DISTANCE, referencedist);
		AL10.alSourcef(sourceId, AL10.AL_MAX_DISTANCE, max);
		//myPos = new Vector3f(0, 0, 0);
		bufferID = -1;
	}
	
	public void play(int buffer)
	{
		stop();
		AL10.alSourcei(sourceId, AL10.AL_BUFFER, buffer);
		bufferID = buffer;
		//System.out.println("buffer "+buffer+" playing");
		continuePlaying();
	}
	
	public void delete()
	{
		stop();
		AL10.alDeleteSources(sourceId);
	}
	
	public void pause()
	{
		AL10.alSourcePause(sourceId);
	}
	
	public void continuePlaying()
	{
		AL10.alSourcePlay(sourceId);
	}
	
	public void stop()
	{
		AL10.alSourceStop(sourceId);
	}
	
	public void setVelocity(float x, float y, float z)
	{
		AL10.alSource3f(sourceId,  AL10.AL_VELOCITY, x, y, z);
	}
	
	public void setVelocity(Vector3f vel)
	{
		AL10.alSource3f(sourceId,  AL10.AL_VELOCITY, vel.x, vel.y, vel.z);
	}
	
	public void setLooping(boolean loop)
	{
		AL10.alSourcei(sourceId, AL10.AL_LOOPING, loop ? AL10.AL_TRUE : AL10.AL_FALSE);
	}
	
	public boolean isPlaying()
	{
		return AL10.alGetSourcei(sourceId,  AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
	}
	
	public void setVolume(float volume)
	{
		AL10.alSourcef(sourceId, AL10.AL_GAIN, volume);
	}
	
	public void setPitch(float pitch)
	{
		AL10.alSourcef(sourceId,  AL10.AL_PITCH, pitch);
	}
	
	public void setPosition(float x, float y, float z)
	{
		AL10.alSource3f(sourceId, AL10.AL_POSITION, x, y, z);
	}
	
	public void setPosition(Vector3f pos)
	{
		AL10.alSource3f(sourceId, AL10.AL_POSITION, pos.x, pos.y, pos.z);
	}
	
	public int getSourceID()
	{
		return sourceId;
	}
	
	public int getLastPlayedBufferID()
	{
		return bufferID;
	}
	
	//public void setPositionFake(Vector3f pos)
	{
		//AL10.alSource3f(sourceId,  AL10.AL_POSITION, 0, 0, 0);
		//myPos.set(pos);
	}
}
