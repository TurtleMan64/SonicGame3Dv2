package objConverter;

public class FakeTexture 
{
	public String name;
	public byte type;
	public byte sound;
	public byte particle;
	
	public FakeTexture()
	{
		type = (byte)0;
		sound = (byte)38;
		particle = (byte)0;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setType(byte type)
	{
		this.type = type;
	}
	
	public void setSound(byte sound)
	{
		this.sound = sound;
	}
	
	public void setParticle(byte particle)
	{
		this.particle = particle;
	}
}
