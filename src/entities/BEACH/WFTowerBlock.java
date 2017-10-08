package entities.BEACH;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.MovingPlatform;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import toolbox.ConvenientMethods;
public class WFTowerBlock extends MovingPlatform
{
	private static TexturedModel[] modelPost = null;//ConvenientMethods.loadModel("Models/WF/", "TowerBlock", MainGameLoop.gameLoader);
	private static CollisionModel cmOriginal = null;//OBJFileLoader.loadCollisionOBJ("Models/WF/", "TowerBlock");
	private int timer;
	private int xSign;
	private int zSign;
	
	public WFTowerBlock(Vector3f position, float angle, int xSign, int zSign)
	{
		super(modelPost, cmOriginal, position);
		timer = 0;
		this.xSign = xSign;
		this.zSign = zSign;
		super.setRotY(angle);
		super.setSpawnRotY(angle);
	}
	
	@Override
	public void step()
	{
		super.step();
		if(timer == 300)
		{
			timer = -300;
		}
		increasePosition(0.0625f*xSign*Math.signum(timer), 0, 0.0625f*zSign*Math.signum(timer));
		timer++;
		updateCollisionModel();
	}
	
	@Override
	public void respawn()
	{
		super.respawn();
		timer = 0;
	}
}
