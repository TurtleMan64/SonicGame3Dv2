package particles;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;

public class Particle 
{
	private Vector3f position;
	private Vector3f velocity;
	private float gravityEffect;
	private int lifeLength;
	private float rotation;
	private float scale;
	private float scaleChange;
	private float distance;
	
	private ParticleTexture texture;
	
	private Vector2f texOffset1 = new Vector2f();
	private Vector2f texOffset2 = new Vector2f();
	private float blend;
	
	private int elapsedTime = 0;

	/**
	 * @param texture
	 * @param position
	 * @param velocity
	 * @param gravityEffect
	 * @param lifeLength
	 * @param rotation
	 * @param scale
	 * @param scaleChange
	 */
	public Particle(ParticleTexture texture, Vector3f position, Vector3f velocity, float gravityEffect, 
			int lifeLength, float rotation, float scale, float scaleChange)
	{
		this.texture = texture;
		this.position = position;
		this.velocity = velocity;
		this.gravityEffect = gravityEffect;
		this.lifeLength = lifeLength;
		this.rotation = rotation;
		this.scale = scale;
		this.scaleChange = scaleChange;
		ParticleMaster.addParticle(this);
	}

	public float getDistance() 
	{
		return distance;
	}

	protected ParticleTexture getTexture() 
	{
		return texture;
	}

	protected Vector3f getPosition() 
	{
		return position;
	}

	protected float getRotation()
	{
		return rotation;
	}

	protected float getScale() 
	{
		return scale;
	}
	
	protected Vector2f getTexOffset1() 
	{
		return texOffset1;
	}

	protected Vector2f getTexOffset2() 
	{
		return texOffset2;
	}
	
	public float getBlend() 
	{
		return blend;
	}

	protected boolean update(Camera camera)
	{
		velocity.y -= gravityEffect;
		scale = Math.max(0, scale+scaleChange);
		Vector3f change = new Vector3f(velocity);
		Vector3f.add(change, position, position);
		distance = Vector3f.sub(camera.getPosition(), position, null).lengthSquared();
		updateTextureCoordInfo();
		elapsedTime += 1;
		return elapsedTime < lifeLength;
	}
	
	private void updateTextureCoordInfo()
	{
		float lifeFactor = elapsedTime / (float)(lifeLength);
		int stageCount = texture.getNumberOfRows() * texture.getNumberOfRows();
		float atlasProgression = lifeFactor * stageCount;
		int index1 = (int)atlasProgression;
		int index2 = index1 < stageCount - 1 ? index1 + 1 : index1;
		this.blend = atlasProgression % 1;
		setTextureOffset(texOffset1, index1);
		setTextureOffset(texOffset2, index2);
	}
	
	private void setTextureOffset(Vector2f offset, int index)
	{
		int column = index % texture.getNumberOfRows();
		int row = index / texture.getNumberOfRows();
		offset.x = (float) column / texture.getNumberOfRows();
		offset.y = (float) row / texture.getNumberOfRows();
	}
}
