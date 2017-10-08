package entities;

import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

import toolbox.AudioRes;

public class MusicPlayer extends Entity
{
	public int musicIndexIntro;
	public int musicIndexLoop;
	
	public MusicPlayer(TexturedModel model, Vector3f position)
	{
		super(model, position, 0, 0, 0, 1);
		super.setVisibility(false);
		musicIndexIntro = -1;
		musicIndexLoop = -1;
	}
	
	@Override
	public void respawn()
	{
		AudioRes.playMusic(musicIndexIntro, false);
	}
	
	public void step()
	{
		if(musicIndexIntro > 0 && AudioRes.gameSounds.size() > musicIndexIntro)
		{
			if(AudioRes.gameSounds.get(musicIndexIntro).isPlaying() == false)
			{
				if(AudioRes.gameSounds.get(musicIndexLoop).isPlaying() == false)
				{
					AudioRes.playMusic(musicIndexLoop, true);
				}
			}
		}
	}
}
