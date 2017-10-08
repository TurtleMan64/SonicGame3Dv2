package animation;

public class Keyframe 
{
	public float time, x, y, z, xRot, yRot, zRot, scale;
	
	public Keyframe(float time, float x, float y, float z, 
			float xRot, float yRot, float zRot, float scale)
	{
		this.time = time;
		this.x = x;
		this.y = y;
		this.z = z;
		this.xRot = xRot;
		this.yRot = yRot;
		this.zRot = zRot;
		this.scale = scale;
	}
}
