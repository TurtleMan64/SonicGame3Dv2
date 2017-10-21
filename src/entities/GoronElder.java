package entities;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import audio.AudioSources;
import engineTester.MainGameLoop;
import fontMeshCreator.GUIText;
import guis.GuiManager;
import models.TexturedModel;
import toolbox.ConvenientMethods;

public class GoronElder extends Entity
{
	private static TexturedModel[] modelElder = null;
	private String[] myMessage1;
	private String[] myMessage2;
	private static GUIText messageGUI1 = null;
	private static GUIText messageGUI2 = null;
	
	public GoronElder(Vector3f position, float yrot, String messages) 
	{
		super(modelElder, position, 0, yrot, 0, 1.0f);
		setVisibility(true);
		String[] msg = messages.split("/");
		this.myMessage1 = msg[0].split("-");
		this.myMessage2 = msg[1].split("-");
	}
	
	@Override
	public void step()
	{
		float xDiff = MainGameLoop.gamePlayer.getX()-getX();
		float zDiff = MainGameLoop.gamePlayer.getZ()-getZ();
		float yDiff = MainGameLoop.gamePlayer.getY()-getY();
		float mag = (float)Math.sqrt((xDiff*xDiff)+(zDiff*zDiff));

		if (mag < 40 && yDiff > -3 && yDiff < 12)
		{
			String[] messagesToUse = myMessage1;
			if (GoronKid.found)
			{
				messagesToUse = myMessage2;
			}
			
			if (messageGUI1 == null)
			{
				float size = 100f/messagesToUse[0].length();
				size = Math.min(size, 3);
				messageGUI1 = new GUIText(messagesToUse[0], size, GuiManager.fontVip, new Vector2f(0.00f, 0.8f), 1f, true, true);
			}
			
			if (messageGUI2 == null)
			{
				float size = 100f/messagesToUse[1].length();
				size = Math.min(size, 3);
				messageGUI2 = new GUIText(messagesToUse[1], size, GuiManager.fontVip, new Vector2f(0.00f, 0.9f), 1f, true, true);
			}
		}
		else
		{
			if (messageGUI1 != null)
			{
				messageGUI1.delete();
				messageGUI1 = null;
			}
			
			if (messageGUI2 != null)
			{
				messageGUI2.delete();
				messageGUI2 = null;
			}
		}
	}
	
	public static void allocateStaticModels()
	{
		if (modelElder == null)
		{
			modelElder = ConvenientMethods.loadModel("Models/Gorons/", "Elder");
		}
	}
	
	public static void freeStaticModels()
	{
		if (modelElder != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelElder);
			modelElder = null;
		}
		
		if (messageGUI1 != null)
		{
			messageGUI1.delete();
			messageGUI1 = null;
		}
		
		if (messageGUI2 != null)
		{
			messageGUI2.delete();
			messageGUI2 = null;
		}
	}
}
