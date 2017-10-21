package entities;

import org.lwjgl.util.vector.Vector3f;

import audio.AudioSources;
import engineTester.MainGameLoop;

public class KillBox extends Entity
{
	private float x1;
	private float y1;
	private float z1;
	
	private float x2;
	private float y2;
	private float z2;
	
	public KillBox(float x1, float y1, float z1,
				   float x2, float y2, float z2)
	{
		super(new Vector3f(0, 0, 0));
		setVisibility(false);
		
		this.x1 = Math.min(x1,  x2);
		this.y1 = Math.min(y1,  y2);
		this.z1 = Math.min(z1,  z2);
		
		this.x2 = Math.max(x1,  x2);
		this.y2 = Math.max(y1,  y2);
		this.z2 = Math.max(z1,  z2);
	}
	
	@Override
	public void step()
	{
		Ball player = MainGameLoop.gamePlayer;
		if(player.getX() > x1 && player.getX() < x2 &&
		   player.getZ() > z1 && player.getZ() < z2 &&
		   player.getY() > y1 && player.getY() < y2)
		{
			MainGameLoop.shouldRestartLevel = true;
			AudioSources.play(34, getPosition());
		}
	}
}
