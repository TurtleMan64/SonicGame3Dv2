package bloom;

import shaders.ShaderProgram;

public class BrightFilterShader extends ShaderProgram
{
	private static final String VERTEX_FILE = "/res/Shaders/bloom/simpleVertex.txt";
	private static final String FRAGMENT_FILE = "/res/Shaders/bloom/brightFilterFragment.txt";
	
	//private int location_glowAmount;
	
	public BrightFilterShader() 
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocation() 
	{	
		//location_glowAmount = super.getUniformLocation("glowAmount");
	}
	
	//public void loadGlow(float glow)
	//{
		//super.loadFloat(location_glowAmount, glow);
	//}

	@Override
	protected void bindAttributes() 
	{
		super.bindAttribute(0, "position");
	}
}
