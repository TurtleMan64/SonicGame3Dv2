package objConverter;

public class FakeTexture 
{
	public String name;
	public byte type;
	public int sound;
	public byte particle;
	
	public FakeTexture()
	{
		type = (byte)0;
		sound = -1;
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
	
	public void setSound(int sound)
	{
		this.sound = sound;
	}
	
	public void setParticle(byte particle)
	{
		this.particle = particle;
	}
}
