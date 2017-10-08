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
	private String myMessage;
	private static GUIText messageGUI = null;
	private boolean found;
	
	public NPC(Vector3f position, float yrot, String message) 
	{
		super(modelWanamaDage, position, 0, yrot, 0, 1.0f);
		setVisibility(true);
		this.myMessage = message;
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
			if (messageGUI == null)
			{
				float size = 100f/myMessage.length();
				size = Math.min(size, 3);
				messageGUI = new GUIText(myMessage, size, GuiManager.fontVip, new Vector2f(0.00f, 0.9f), 1f, true, true);
				if (found == false)
				{
					AudioSources.play(35, getPosition());
					found = true;
				}
			}
		}
		else
		{
			if (messageGUI != null && messageGUI.getTextString().equals(myMessage))
			{
				messageGUI.delete();
				messageGUI = null;
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
		
		if (messageGUI != null)
		{
			messageGUI.delete();
			messageGUI = null;
		}
	}
}
