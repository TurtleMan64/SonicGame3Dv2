package entities.BEACH;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.MovingPlatform;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import toolbox.ConvenientMethods;

public class BeachFloatingPlatform extends MovingPlatform
{
	private static TexturedModel[] modelPlatform = null;
	private static CollisionModel cmOriginal = null;
	private float surfaceHeight;
	private static final float maxOffset = 20;
	
	public BeachFloatingPlatform(Vector3f position)
	{
		super(modelPlatform, cmOriginal, position);
		surfaceHeight = position.y;
	}
	
	@Override
	public void step()
	{
		super.step();

		if (collideTransformed.playerIsOn)
		{
			if (getY() > surfaceHeight-maxOffset)
			{
				increasePosition(0, -0.25f, 0);
			}
		}
		else if (getY() < surfaceHeight)
		{
			increasePosition(0, 0.35f, 0);
			if (getY() > surfaceHeight)
			{
				setPosition(new Vector3f(getX(), surfaceHeight, getZ()));
			}
		}
		
		updateCMJustPosition();
	}
	
	public static void allocateStaticModels()
	{
		if (modelPlatform == null)
		{
			modelPlatform = ConvenientMethods.loadModel("Models/EmeraldCoast/", "SinkingPlatform");
		}
		if (cmOriginal == null)
		{
			cmOriginal = OBJFileLoader.loadCollisionOBJ("Models/EmeraldCoast/", "SinkingPlatform");
		}
	}
	
	public static void freeStaticModels()
	{
		if (modelPlatform != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelPlatform);
			modelPlatform = null;
		}
		if (cmOriginal != null)
		{
			cmOriginal = null;
		}
	}
}
