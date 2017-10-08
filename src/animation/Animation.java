package animation;

import java.util.ArrayList;

public class Animation 
{
	public ArrayList<Keyframe> keyframes;
	
	public Animation()
	{
		keyframes = new ArrayList<Keyframe>();
	}
	
	public void addKeyframe(float time, float x, float y, float z, 
			float xRot, float yRot, float zRot, float scale)
	{
		Keyframe key = new Keyframe(time, x, y, z, xRot, yRot, zRot, scale);
		
		if(keyframes.size() == 0)
		{
			keyframes.add(key);
		}
		else if(keyframes.size() == 1)
		{
			if(time > keyframes.get(0).time)
			{
				keyframes.add(key);
			}
			else
			{
				keyframes.add(0, key);
			}
		}
		else
		{
			if(time < keyframes.get(0).time)
			{
				keyframes.add(0, key);
			}
			else
			{
				boolean inRange = false;
				for(int i = 0; i < keyframes.size()-1; i++)
				{
					if(time >= keyframes.get(i).time && time < keyframes.get(i+1).time)
					{
						keyframes.add(i+1, key);
						inRange = true;
						break;
					}
				}
				if(inRange == false)
				{
					keyframes.add(key);
				}
			}
		}
	}
	
	public void addKeyframe(float time, float xRot, float yRot, float zRot, float scale)
	{
		this.addKeyframe(time, 0, 0, 0, xRot, yRot, zRot, scale);
	}
}
