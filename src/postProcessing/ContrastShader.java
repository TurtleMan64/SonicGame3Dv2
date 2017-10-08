package postProcessing;

import shaders.ShaderProgram;

public class ContrastShader extends ShaderProgram 
{
	private static final String VERTEX_FILE = "/res/Shaders/postProcessing/contrastVertex.txt";
	private static final String FRAGMENT_FILE = "/res/Shaders/postProcessing/contrastFragment.txt";
	
	public ContrastShader() 
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() 
	{
		super.bindAttribute(0, "position");
	}

	@Override
	protected void getAllUniformLocation() 
	{
		
	}
}
