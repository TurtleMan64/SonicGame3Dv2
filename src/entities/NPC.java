package entities;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import audio.AudioSources;
import engineTester.MainGameLoop;
import fontMeshCreator.GUIText;
import guis.GuiManager;
import models.TexturedModel;
import toolbox.ConvenientMethods;

public class NPC extends Entity
{
	private static TexturedModel[] modelWanamaDage = null;
	private String myMessage1;
	private static GUIText messageGUI1 = null;
	private String myMessage2;
	private static GUIText messageGUI2 = null;
	private boolean found;
	
	public NPC(Vector3f position, float yrot, String message) 
	{
		super(modelWanamaDage, position, 0, yrot, 0, 1.0f);
		setVisibility(true);
		String[] msg = message.split("-");
		myMessage1 = "";
		myMessage2 = "";
		if (msg.length == 1)
		{
			myMessage2 = msg[0];
		}
		else if (msg.length > 1)
		{
			myMessage1 = msg[0];
			myMessage2 = msg[1];
		}
		found = false;
	}
	
	@Override
	public void step()
	{
		float xDiff = MainGameLoop.gamePlayer.getX()-getX();
		float zDiff = MainGameLoop.gamePlayer.getZ()-getZ();
		float yDiff = MainGameLoop.gamePlayer.getY()-getY();
		float mag = (float)Math.sqrt((xDiff*xDiff)+(zDiff*zDiff));

		if (mag < 20 && yDiff > -3 && yDiff < 12)
		{
			if (found == false)
			{
				AudioSources.play(35, getPosition());
				found = true;
			}
			
			if (messageGUI1 == null)
			{
				float size = 100f/myMessage1.length();
				size = Math.min(size, 3);
				messageGUI1 = new GUIText(myMessage1, size, GuiManager.fontVip, new Vector2f(0.00f, 0.8f), 1f, true, true);
			}
			
			if (messageGUI2 == null)
			{
				float size = 100f/myMessage2.length();
				size = Math.min(size, 3);
				messageGUI2 = new GUIText(myMessage2, size, GuiManager.fontVip, new Vector2f(0.00f, 0.9f), 1f, true, true);
			}
		}
		else
		{
			if (messageGUI1 != null && messageGUI1.getTextString().equals(myMessage1))
			{
				messageGUI1.delete();
				messageGUI1 = null;
			}
			
			if (messageGUI2 != null && messageGUI2.getTextString().equals(myMessage2))
			{
				messageGUI2.delete();
				messageGUI2 = null;
			}
		}
	}
	
	public static void allocateStaticModels()
	{
		if (modelWanamaDage == null)
		{
			modelWanamaDage = ConvenientMethods.loadModel("Models/WanamaDage/", "WanamaDage");
		}
	}
	
	public static void freeStaticModels()
	{
		if (modelWanamaDage != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelWanamaDage);
			modelWanamaDage = null;
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
