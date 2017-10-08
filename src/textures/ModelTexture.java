package textures;

import engineTester.MainGameLoop;

public class ModelTexture 
{
	private int textureID;
	
	private float shineDamper;
	private float reflectivity;
	
	private boolean hasTransparency;
	private boolean useFakeLighting;
	//private boolean moves;
	private float scrollX;
	private float scrollY;
	private float glowAmount;
	
	public float getGlowAmount() 
	{
		return glowAmount;
	}

	public void setGlowAmount(float glowAmount) 
	{
		this.glowAmount = glowAmount;
	}

	public boolean isUseFakeLighting() 
	{
		return useFakeLighting;
	}

	public ModelTexture setUseFakeLighting(boolean useFakeLighting) 
	{
		this.useFakeLighting = useFakeLighting;
		return this;
	}

	public boolean isHasTransparency() 
	{
		return hasTransparency;
	}

	public ModelTexture setHasTransparency(boolean hasTransparency) 
	{
		this.hasTransparency = hasTransparency;
		return this;
	}
	
	//public ModelTexture setMoves(boolean moves) 
	//{
		//this.moves = moves;
		//return this;
	//}
	
	public ModelTexture setScrollX(float speedX)
	{
		scrollX = speedX;
		return this;
	}
	
	public ModelTexture setScrollY(float speedY)
	{
		scrollY = speedY;
		return this;
	}
	
	public float getScrollX()
	{
		return scrollX;
	}
	
	public float getScrollY()
	{
		return scrollY;
	}
	
	//public boolean hasMovement() 
	//{
		//return moves;
	//}

	public ModelTexture(int id)
	{
		this.textureID = id;
		shineDamper = 1;
		reflectivity = 0;
		hasTransparency = false;
		useFakeLighting = false;
		//moves = false; //not sure if its more efficient to keep a bool for this or just multiply without branching ?
		scrollX = 0;
		scrollY = 0;
		glowAmount = 0.0f;
	}

	public int getID() 
	{
		return textureID;
	}

	public float getShineDamper() 
	{
		return shineDamper;
	}

	public void setShineDamper(float shineDamper) 
	{
		this.shineDamper = shineDamper;
	}

	public float getReflectivity() 
	{
		return reflectivity;
	}

	public void setReflectivity(float reflectivity) 
	{
		this.reflectivity = reflectivity;
	}
	
	/** Deletes this texture from OpenGL
	 * 
	 */
	public void delete()
	{
		MainGameLoop.gameLoader.deleteTexture(textureID);
	}
}
