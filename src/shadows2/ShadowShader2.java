package shadows2;

import org.lwjgl.util.vector.Matrix4f;

import shaders.ShaderProgram;

public class ShadowShader2 extends ShaderProgram 
{
	private static final String VERTEX_FILE = "/res/Shaders/shadows2/shadowVertexShader2.txt";
	private static final String FRAGMENT_FILE = "/res/Shaders/shadows2/shadowFragmentShader2.txt";
	
	private int location_mvpMatrix;

	protected ShadowShader2() 
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocation() 
	{
		location_mvpMatrix = super.getUniformLocation("mvpMatrix");
	}
	
	protected void loadMvpMatrix(Matrix4f mvpMatrix)
	{
		super.loadMatrix(location_mvpMatrix, mvpMatrix);
	}

	@Override
	protected void bindAttributes() 
	{
		super.bindAttribute(0, "in_position");
		super.bindAttribute(1, "in_textureCoords");
	}
}
