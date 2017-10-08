package entities;

import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionChecker;

public class ShadowBlob extends Entity
{
	private Vector3f shadowMakerPosition;
	
	public ShadowBlob(TexturedModel model, Vector3f position, Vector3f shadowMakerPosition)
	{
		super(model, position, 0, 0, 0, 1);
		this.shadowMakerPosition = shadowMakerPosition;
	}
	
	@Override
	public void step()
	{
		
		if(CollisionChecker.checkCollision(shadowMakerPosition.getX(), shadowMakerPosition.getY()+5.5f, shadowMakerPosition.getZ(), shadowMakerPosition.getX(), shadowMakerPosition.getY()-500, shadowMakerPosition.getZ()))
		{
			setPosition(CollisionChecker.getCollidePosition());
			super.setVisibility(true);
			Vector3f surfaceNormal = CollisionChecker.getCollideTriangle().getNormal();
			surfaceNormal.normalise();
			float ZAngle = -90+(float)Math.toDegrees(Math.atan2(surfaceNormal.getY(), surfaceNormal.getX()));
			float XAngle = 90-(float)Math.toDegrees(Math.atan2(surfaceNormal.getY(), surfaceNormal.getZ()));
			super.setRotZ(ZAngle);
			super.setRotX(XAngle);
		}
		else
		{
			super.setVisibility(false);
		}
	}
}
