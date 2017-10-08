package gaussianBlur;

import shaders.ShaderProgram;

public class VerticalBlurShader extends ShaderProgram
{
	private static final String VERTEX_FILE = "/res/Shaders/gaussianBlur/verticalBlurVertex.txt";
	private static final String FRAGMENT_FILE = "/res/Shaders/gaussianBlur/blurFragment.txt";
	
	private int location_targetHeight;
	
	protected VerticalBlurShader() 
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
	protected void loadTargetHeight(float height)
	{
		super.loadFloat(location_targetHeight, height);
	}

	@Override
	protected void getAllUniformLocation() 
	{	
		location_targetHeight = super.getUniformLocation("targetHeight");
	}

	@Override
	protected void bindAttributes() 
	{
		super.bindAttribute(0, "position");
	}
}
