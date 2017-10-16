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
	
	private static TexturedModel[] modelJog0 = null;
	private static TexturedModel[] modelJog1 = null;
	private static TexturedModel[] modelJog2 = null;
	private static TexturedModel[] modelJog3 = null;
	private static TexturedModel[] modelJog4 = null;
	private static TexturedModel[] modelJog5 = null;
	private static TexturedModel[] modelJog6 = null;
	private static TexturedModel[] modelJog7 = null;
	private static TexturedModel[] modelJog8 = null;
	private static TexturedModel[] modelJog9 = null;
	private static TexturedModel[] modelJog10 = null;
	private static TexturedModel[] modelJog11 = null;
	private static TexturedModel[] modelJog12 = null;
	private static TexturedModel[] modelJog13 = null;
	private static TexturedModel[] modelJog14 = null;
	private static TexturedModel[] modelJog15 = null;
	private static TexturedModel[] modelJog16 = null;
	private static TexturedModel[] modelJog17 = null;
	
	public ManiaSonicModels()
	{
		super(new Vector3f(0,0,0));
		setModels(modelJump);
		setScale(0.33f);
	}
	
	public void animate(int animIndex, float time)
	{
		int index = 0;
		setScale(0.33f);
		switch (animIndex)
		{
			case 1: //run
				index = (int)(time/8.3333333f);
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
				setScale(0.40f);
				break;
				
			case 15: //jog
				index = (int)(time/5.55555555f);
				switch (index)
				{
					case 0: setModels(modelJog0); break;
					case 1: setModels(modelJog1); break;
					case 2: setModels(modelJog2); break;
					case 3: setModels(modelJog3); break;
					case 4: setModels(modelJog4); break;
					case 5: setModels(modelJog5); break;
					case 6: setModels(modelJog6); break;
					case 7: setModels(modelJog7); break;
					case 8: setModels(modelJog8); break;
					case 9: setModels(modelJog9); break;
					case 10: setModels(modelJog10); break;
					case 11: setModels(modelJog11); break;
					case 12: setModels(modelJog12); break;
					case 13: setModels(modelJog13); break;
					case 14: setModels(modelJog14); break;
					case 15: setModels(modelJog15); break;
					case 16: setModels(modelJog16); break;
					case 17: setModels(modelJog17); break;
					default: break;
				}
				break;
				
			default:
				break;
		}
	}
	
	public void setOrientation(float x, float y, float z, float yRot, float zRot)
	{
		setX(x);
		setY(y);
		setZ(z);
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
		
		if (modelJog0 == null) { modelJog0 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog0"); }
		if (modelJog1 == null) { modelJog1 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog1"); }
		if (modelJog2 == null) { modelJog2 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog2"); }
		if (modelJog3 == null) { modelJog3 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog3"); }
		if (modelJog4 == null) { modelJog4 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog4"); }
		if (modelJog5 == null) { modelJog5 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog5"); }
		if (modelJog6 == null) { modelJog6 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog6"); }
		if (modelJog7 == null) { modelJog7 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog7"); }
		if (modelJog8 == null) { modelJog8 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog8"); }
		if (modelJog9 == null) { modelJog9 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog9"); }
		if (modelJog10 == null) { modelJog10 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog10"); }
		if (modelJog11 == null) { modelJog11 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog11"); }
		if (modelJog12 == null) { modelJog12 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog12"); }
		if (modelJog13 == null) { modelJog13 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog13"); }
		if (modelJog14 == null) { modelJog14 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog14"); }
		if (modelJog15 == null) { modelJog15 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog15"); }
		if (modelJog16 == null) { modelJog16 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog16"); }
		if (modelJog17 == null) { modelJog17 = ConvenientMethods.loadModel("Models/SonicMania/", "Jog17"); }
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
		if (modelJog0 != null) { MainGameLoop.gameLoader.deleteModel(modelJog0); modelJog0 = null; }
		if (modelJog1 != null) { MainGameLoop.gameLoader.deleteModel(modelJog1); modelJog1 = null; }
		if (modelJog2 != null) { MainGameLoop.gameLoader.deleteModel(modelJog2); modelJog2 = null; }
		if (modelJog3 != null) { MainGameLoop.gameLoader.deleteModel(modelJog3); modelJog3 = null; }
		if (modelJog4 != null) { MainGameLoop.gameLoader.deleteModel(modelJog4); modelJog4 = null; }
		if (modelJog5 != null) { MainGameLoop.gameLoader.deleteModel(modelJog5); modelJog5 = null; }
		if (modelJog6 != null) { MainGameLoop.gameLoader.deleteModel(modelJog6); modelJog6 = null; }
		if (modelJog7 != null) { MainGameLoop.gameLoader.deleteModel(modelJog7); modelJog7 = null; }
		if (modelJog8 != null) { MainGameLoop.gameLoader.deleteModel(modelJog8); modelJog8 = null; }
		if (modelJog9 != null) { MainGameLoop.gameLoader.deleteModel(modelJog9); modelJog9 = null; }
		if (modelJog10 != null) { MainGameLoop.gameLoader.deleteModel(modelJog10); modelJog10 = null; }
		if (modelJog11 != null) { MainGameLoop.gameLoader.deleteModel(modelJog11); modelJog11 = null; }
		if (modelJog12 != null) { MainGameLoop.gameLoader.deleteModel(modelJog12); modelJog12 = null; }
		if (modelJog13 != null) { MainGameLoop.gameLoader.deleteModel(modelJog13); modelJog13 = null; }
		if (modelJog14 != null) { MainGameLoop.gameLoader.deleteModel(modelJog14); modelJog14 = null; }
		if (modelJog15 != null) { MainGameLoop.gameLoader.deleteModel(modelJog15); modelJog15 = null; }
		if (modelJog16 != null) { MainGameLoop.gameLoader.deleteModel(modelJog16); modelJog16 = null; }
		if (modelJog17 != null) { MainGameLoop.gameLoader.deleteModel(modelJog17); modelJog17 = null; }
	}
}
