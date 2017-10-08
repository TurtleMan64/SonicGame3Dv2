package entities.BEACH;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.RotatingPlatform;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import toolbox.ConvenientMethods;

public class WFGrassPlatform extends RotatingPlatform
{
	private static CollisionModel cmOriginal = null;//OBJFileLoader.loadCollisionOBJ("Models/WF/", "GrassSpin");
	private static TexturedModel[] models =null;// ConvenientMethods.loadModel("Models/WF/", "GrassSpin", MainGameLoop.gameLoader);
	
	public WFGrassPlatform(Vector3f position) 
	{
		super(models, cmOriginal, position, 1);
	}
}
