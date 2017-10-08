package particles;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import shaders.ShaderProgram;

public class ParticleShader extends ShaderProgram 
{
	private static final String VERTEX_FILE = "/res/Shaders/particles/particleVShader.txt";
	private static final String FRAGMENT_FILE = "/res/Shaders/particles/particleFShader.txt";
	
	private int location_modelViewMatrix;
	private int location_projectionMatrix;
	private int location_texOffset1;
	private int location_texOffset2;
	private int location_texCoordInfo;
	private int location_brightness;
	private int location_opacity;
	private int location_glow;
	
	public ParticleShader() 
	{
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
	@Override
	protected void getAllUniformLocation() 
	{
		location_modelViewMatrix = super.getUniformLocation("modelViewMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_texOffset1 = super.getUniformLocation("texOffset1");
		location_texOffset2 = super.getUniformLocation("texOffset2");
		location_texCoordInfo = super.getUniformLocation("texCoordInfo");
		location_brightness = super.getUniformLocation("brightness");
		location_opacity = super.getUniformLocation("opacity");
		location_glow = super.getUniformLocation("glow");
	}

	@Override
	protected void bindAttributes() 
	{
		super.bindAttribute(0, "position");
	}
	
	protected void loadTextureCoordInfo(Vector2f offset1, Vector2f offset2, float numRows, float blend)
	{
		super.load2DVector(location_texOffset1, offset1);
		super.load2DVector(location_texOffset2, offset2);
		super.load2DVector(location_texCoordInfo, new Vector2f(numRows, blend));
	}
	
	protected void loadBrightness(float brightness)
	{
		super.loadFloat(location_brightness, brightness);
	}
	
	protected void loadOpacity(float opacity)
	{
		super.loadFloat(location_opacity, opacity);
	}
	
	protected void loadGlow(float glow)
	{
		super.loadFloat(location_glow, glow);
	}

	protected void loadModelViewMatrix(Matrix4f modelView) 
	{
		super.loadMatrix(location_modelViewMatrix, modelView);
	}

	protected void loadProjectionMatrix(Matrix4f projectionMatrix) 
	{
		super.loadMatrix(location_projectionMatrix, projectionMatrix);
	}
}
