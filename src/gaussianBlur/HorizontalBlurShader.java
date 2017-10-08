package gaussianBlur;

import shaders.ShaderProgram;

public class HorizontalBlurShader extends ShaderProgram 
{
	private static final String VERTEX_FILE = "/res/Shaders/gaussianBlur/horizontalBlurVertex.txt";
	private static final String FRAGMENT_FILE = "/res/Shaders/gaussianBlur/blurFragment.txt";
	
	private int location_targetWidth;
	
	protected HorizontalBlurShader() 
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	protected void loadTargetWidth(float width)
	{
		super.loadFloat(location_targetWidth, width);
	}
	
	@Override
	protected void getAllUniformLocation() 
	{
		location_targetWidth = super.getUniformLocation("targetWidth");
	}

	@Override
	protected void bindAttributes() 
	{
		super.bindAttribute(0, "position");
	}
}
