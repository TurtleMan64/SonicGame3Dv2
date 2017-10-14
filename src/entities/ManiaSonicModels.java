package entities;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import models.TexturedModel;
import toolbox.ConvenientMethods;

public class ManiaSonicModels extends Entity
{
	private static TexturedModel[] modelDash0 = null;
	private static TexturedModel[] modelDash1 = null;
	private static TexturedModel[] modelDash2 = null;
	private static TexturedModel[] modelDash3 = null;
	private static TexturedModel[] modelDash4 = null;
	private static TexturedModel[] modelDash5 = null;
	private static TexturedModel[] modelDash6 = null;
	private static TexturedModel[] modelDash7 = null;
	private static TexturedModel[] modelDash8 = null;
	private static TexturedModel[] modelDash9 = null;
	private static TexturedModel[] modelDash10 = null;
	private static TexturedModel[] modelDash11 = null;
	
	private static TexturedModel[] modelJump = null;
	
	public ManiaSonicModels()
	{
		super(new Vector3f(0,0,0));
		setModels(modelJump);
		setScale(0.35f);
	}
	
	public void animate(int animIndex, float time)
	{
		switch (animIndex)
		{
			case 1: //run
				int index = (int)(time/8.3333333f);
				switch (index)
				{
					case 0: setModels(modelDash0); break;
					case 1: setModels(modelDash1); break;
					case 2: setModels(modelDash2); break;
					case 3: setModels(modelDash3); break;
					case 4: setModels(modelDash4); break;
					case 5: setModels(modelDash5); break;
					case 6: setModels(modelDash6); break;
					case 7: setModels(modelDash7); break;
					case 8: setModels(modelDash8); break;
					case 9: setModels(modelDash9); break;
					case 10: setModels(modelDash10); break;
					case 11: setModels(modelDash11); break;
					default: break;
				}
				break;
				
			case 12: //jump
				setModels(modelJump);
				break;
				
			default:
				break;
		}
	}
	
	public void setOrientation(Vector3f pos, float yRot, float zRot)
	{
		setPosition(pos);
		setRotY(yRot);
		setRotZ(zRot);
	}

	public static void allocateStaticModels()
	{
		if (modelDash0 == null) { modelDash0 = ConvenientMethods.loadModel("Models/SonicMania/", "Dash0"); }
		if (modelDash1 == null) { modelDash1 = ConvenientMethods.loadModel("Models/SonicMania/", "Dash1"); }
		if (modelDash2 == null) { modelDash2 = ConvenientMethods.loadModel("Models/SonicMania/", "Dash2"); }
		if (modelDash3 == null) { modelDash3 = ConvenientMethods.loadModel("Models/SonicMania/", "Dash3"); }
		if (modelDash4 == null) { modelDash4 = ConvenientMethods.loadModel("Models/SonicMania/", "Dash4"); }
		if (modelDash5 == null) { modelDash5 = ConvenientMethods.loadModel("Models/SonicMania/", "Dash5"); }
		if (modelDash6 == null) { modelDash6 = ConvenientMethods.loadModel("Models/SonicMania/", "Dash6"); }
		if (modelDash7 == null) { modelDash7 = ConvenientMethods.loadModel("Models/SonicMania/", "Dash7"); }
		if (modelDash8 == null) { modelDash8 = ConvenientMethods.loadModel("Models/SonicMania/", "Dash8"); }
		if (modelDash9 == null) { modelDash9 = ConvenientMethods.loadModel("Models/SonicMania/", "Dash9"); }
		if (modelDash10 == null) { modelDash10 = ConvenientMethods.loadModel("Models/SonicMania/", "Dash10"); }
		if (modelDash11 == null) { modelDash11 = ConvenientMethods.loadModel("Models/SonicMania/", "Dash11"); }
		if (modelJump == null) { modelJump = ConvenientMethods.loadModel("Models/SonicMania/", "Jump"); }
	}
	
	public static void freeStaticModels()
	{
		if (modelDash0 != null) { MainGameLoop.gameLoader.deleteModel(modelDash0); modelDash0 = null; }
		if (modelDash1 != null) { MainGameLoop.gameLoader.deleteModel(modelDash1); modelDash1 = null; }
		if (modelDash2 != null) { MainGameLoop.gameLoader.deleteModel(modelDash2); modelDash2 = null; }
		if (modelDash3 != null) { MainGameLoop.gameLoader.deleteModel(modelDash3); modelDash3 = null; }
		if (modelDash4 != null) { MainGameLoop.gameLoader.deleteModel(modelDash4); modelDash4 = null; }
		if (modelDash5 != null) { MainGameLoop.gameLoader.deleteModel(modelDash5); modelDash5 = null; }
		if (modelDash6 != null) { MainGameLoop.gameLoader.deleteModel(modelDash6); modelDash6 = null; }
		if (modelDash7 != null) { MainGameLoop.gameLoader.deleteModel(modelDash7); modelDash7 = null; }
		if (modelDash8 != null) { MainGameLoop.gameLoader.deleteModel(modelDash8); modelDash8 = null; }
		if (modelDash9 != null) { MainGameLoop.gameLoader.deleteModel(modelDash9); modelDash9 = null; }
		if (modelDash10 != null) { MainGameLoop.gameLoader.deleteModel(modelDash10); modelDash10 = null; }
		if (modelDash11 != null) { MainGameLoop.gameLoader.deleteModel(modelDash11); modelDash11 = null; }
		if (modelJump != null) { MainGameLoop.gameLoader.deleteModel(modelJump); modelJump = null; }
	}
}
