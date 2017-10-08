package particles;

public class ParticleTexture 
{
	private int textureID;
	private int numberOfRows;
	private float opacity;
	private float glow;
	
	public ParticleTexture(int textureID, int numberOfRows, float opacity, float glow)
	{
		this.textureID = textureID;
		this.numberOfRows = numberOfRows;
		this.opacity = opacity;
		this.glow = glow;
	}

	public int getTextureID() 
	{
		return textureID;
	}

	public int getNumberOfRows() 
	{
		return numberOfRows;
	}
	
	public float getOpacity()
	{
		return opacity;
	}
	
	public float getGlow()
	{
		return glow;
	}
}
