package entities;

import org.lwjgl.util.vector.Vector3f;

public class Light 
{
	private Vector3f position;
	private Vector3f colour;
	private Vector3f attenuation = new Vector3f(1, 0, 0);
	
	public Light(Vector3f position, Vector3f colour) 
	{
		this.position = position;
		this.colour = colour;
	}
	
	public Light(Vector3f position, Vector3f colour, Vector3f attenuation) 
	{
		this.position = position;
		this.colour = colour;
		this.attenuation = attenuation;
	}

	public Vector3f getAttenuation() 
	{
		return attenuation;
	}

	public Vector3f getPosition() 
	{
		return position;
	}
	
	public void setAttenuationX(float newX)
	{
		attenuation.setX(newX);
	}

	public void setPosition(Vector3f position) 
	{
		this.position.set(position);
	}
	
	public void setPositionX(float newX)
	{
		this.position.setX(newX);
	}

	public void setPositionY(float newY)
	{
		this.position.setY(newY);
	}
	
	public void setPositionZ(float newZ)
	{
		this.position.setZ(newZ);
	}
	
	public Vector3f getColour() 
	{
		return colour;
	}

	public void setColour(Vector3f colour) 
	{
		this.colour.set(colour);
	} 
	
	public void setColourRed(float newRed)
	{
		this.colour.setX(newRed);
	}	
	
	public void setColourGreen(float newGreen)
	{
		this.colour.setY(newGreen);
	}
	
	public void setColourBlue(float newBlue)
	{
		this.colour.setZ(newBlue);
	}
}
