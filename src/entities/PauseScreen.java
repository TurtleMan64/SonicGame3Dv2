package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import engineTester.MainGameLoop;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import guis.GuiTexture;
import toolbox.AudioRes;
import toolbox.Input;
import toolbox.Joystick;
import toolbox.LevelLoader;

public class PauseScreen 
{
	//public static int textureCursor = MainGameLoop.gameLoader.loadTexture("Models\\GUI\\Cursor");
	/*
	public static GuiTexture guiTextureCursor = new GuiTexture(MainGameLoop.gameLoader.loadTexture("Models/GUI/Cursor"), 
															new Vector2f(-.2f, 0.5f), 
															new Vector2f(128f/1280, 128f/720));

	public static GuiTexture guiTextureResume0 = new GuiTexture(MainGameLoop.gameLoader.loadTexture("Models/GUI/Resume0"), 
															new Vector2f(0/640f, 0/360f), 
															new Vector2f(128f/1280, 128f/720));
	public static GuiTexture guiTextureResume1 = new GuiTexture(MainGameLoop.gameLoader.loadTexture("Models/GUI/Resume1"), 
															new Vector2f(128/640f, 0/360f), 
															new Vector2f(128f/1280, 128f/720));
	public static GuiTexture guiTextureResume2 = new GuiTexture(MainGameLoop.gameLoader.loadTexture("Models/GUI/Resume2"), 
															new Vector2f(256/640f, 0/360f), 
															new Vector2f(128f/1280, 128f/720));
	public static GuiTexture guiTextureResume3 = new GuiTexture(MainGameLoop.gameLoader.loadTexture("Models/GUI/Resume3"), 
															new Vector2f(384/640f, 0/360f), 
															new Vector2f(128f/1280, 128f/720));
	
	public static GuiTexture guiTextureQuit0 = new GuiTexture(MainGameLoop.gameLoader.loadTexture("Models/GUI/Quit0"), 
															new Vector2f(0/640f, -256/360f), 
															new Vector2f(128f/1280, 128f/720));
	public static GuiTexture guiTextureQuit1 = new GuiTexture(MainGameLoop.gameLoader.loadTexture("Models/GUI/Quit1"), 
															new Vector2f(128/640f, -256/360f), 
															new Vector2f(128f/1280, 128f/720));
															*/
	private static int menuSelection = 0;
	private static int menuSelectionMAX = 4;
	private static int menuDisplayID = 0;
	private static final int ROOT = 0;
	private static final int LEVEL_SELECT = 1;
	private static final int CHAR_SELECT = 2;
	
	private static int moveYPrevious = 0;
	private static boolean selectInputPrevious = false;
	private static boolean backInputPrevious = false;
	
	public static FontType font;
	
	private static GUIText textCursor = null;
	private static GUIText textResume = null;
	private static GUIText textRestart = null;
	private static GUIText textLevelSelect = null;
	private static GUIText textCharSelect = null;
	private static GUIText textQuit = null;
	
	private static GUIText textEmeraldCoast = null;
	private static GUIText textGreenHillZone = null;
	private static GUIText textSpeedHighway = null;
	private static GUIText textWuhuIsland = null;
	private static GUIText textPeachCastle = null;
	private static GUIText textSandHill = null;
	private static GUIText textKoopaBeach = null;
	private static GUIText textOutsetIsland = null;
	private static GUIText textWeaponsBed = null;
	private static GUIText textMetalHarbor = null;
	private static GUIText textBOB = null;
	private static GUIText textRainbowRoad = null;
	
	private static GUIText textClassicSonic = null;
	private static GUIText textDollSonic = null;
	private static GUIText textMechaSonic = null;
	private static GUIText textDage4Aquatic = null;
	private static GUIText textManiaSonic = null;
	
	private static boolean shouldPause = false;
	
	public static void init()
	{
		font = new FontType(MainGameLoop.gameLoader.loadTexture("Fonts/vipnagorgialla"), "Fonts/vipnagorgialla");
		textCursor = new GUIText(">", 3, font, new Vector2f(0.47f, 0.25f), 1f, false, false);
	}
	
	public static void step()
	{
		if (shouldPause == true)
		{
			shouldPause = false;
			if(MainGameLoop.gameState == MainGameLoop.gameStates.paused)
			{
				unpause();
			}
			else if(MainGameLoop.gameState == MainGameLoop.gameStates.running)
			{
				pause();
			}
		}
		
		if (Input.startInput && !Input.previousStartInput)
		{
			shouldPause = true;
		}
		
	    if (MainGameLoop.gameState == MainGameLoop.gameStates.paused)
		{
			boolean selectInput = false;
			boolean backInput = false;
			int moveY = 0;
			
			if(Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP))
			{
				moveY = -1;
			}
			else if(Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			{
				moveY = 1;
			}
			if(Joystick.joystickExists() && Joystick.getYAxisApproxClicked() != 0)
			{
				moveY = Joystick.getYAxisApproxClicked();
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			{
				selectInput = true;
			}
			if(Joystick.joystickExists() && Joystick.getButtonJustPressed(Joystick.BUTTON_A))
			{
				selectInput = true;
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_BACK) || Keyboard.isKeyDown(Keyboard.KEY_C))
			{
				backInput = true;
			}
			if(Joystick.joystickExists() && Joystick.getButtonJustPressed(Joystick.BUTTON_B))
			{
				backInput = true;
			}
			

			if(moveYPrevious != moveY)
			{
				menuSelection+=moveY;
				menuSelection = Math.max(0, Math.min(menuSelectionMAX, menuSelection));
			}
			
			switch (Ball.characterID)
			{
				case 0:
					textClassicSonic.setColour(1f, 1f, 1f);
					textDollSonic.setColour(0.6f, 0.6f, 0.6f);
					textMechaSonic.setColour(0.6f, 0.6f, 0.6f);
					textDage4Aquatic.setColour(0.6f, 0.6f, 0.6f);
					textManiaSonic.setColour(0.6f, 0.6f, 0.6f);
					break;
					
				case 1:
					textClassicSonic.setColour(0.6f, 0.6f, 0.6f);
					textDollSonic.setColour(1f, 1f, 1f);
					textMechaSonic.setColour(0.6f, 0.6f, 0.6f);
					textDage4Aquatic.setColour(0.6f, 0.6f, 0.6f);
					textManiaSonic.setColour(0.6f, 0.6f, 0.6f);
					break;
					
				case 2:
					textClassicSonic.setColour(0.6f, 0.6f, 0.6f);
					textDollSonic.setColour(0.6f, 0.6f, 0.6f);
					textMechaSonic.setColour(1f, 1f, 1f);
					textDage4Aquatic.setColour(0.6f, 0.6f, 0.6f);
					textManiaSonic.setColour(0.6f, 0.6f, 0.6f);
					break;
					
				case 3:
					textClassicSonic.setColour(0.6f, 0.6f, 0.6f);
					textDollSonic.setColour(0.6f, 0.6f, 0.6f);
					textMechaSonic.setColour(0.6f, 0.6f, 0.6f);
					textDage4Aquatic.setColour(1f, 1f, 1f);
					textManiaSonic.setColour(0.6f, 0.6f, 0.6f);
					break;
					
				case 4:
					textClassicSonic.setColour(0.6f, 0.6f, 0.6f);
					textDollSonic.setColour(0.6f, 0.6f, 0.6f);
					textMechaSonic.setColour(0.6f, 0.6f, 0.6f);
					textDage4Aquatic.setColour(0.6f, 0.6f, 0.6f);
					textManiaSonic.setColour(1f, 1f, 1f);
					break;
			}
			
			if (selectInput && !selectInputPrevious)
			{
				switch (menuDisplayID)
				{
					case ROOT:
						switch (menuSelection)
						{
							case 0:
								//unpause();
								shouldPause = true;
								break;
								
							case 1:
								LevelLoader.loadLevel(MainGameLoop.levelName, false);
								unpause();
								break;
								
							case 2:
								menuDisplayID = LEVEL_SELECT;
								menuSelectionMAX = 11;
								menuSelection = 0;
								textResume.setVisibility(false);
								textRestart.setVisibility(false);
								textQuit.setVisibility(false);
								textLevelSelect.setVisibility(false);
								textCharSelect.setVisibility(false);
								
								textEmeraldCoast.setVisibility(true);
								textGreenHillZone.setVisibility(true);
								textSpeedHighway.setVisibility(true);
								textWuhuIsland.setVisibility(true);
								textPeachCastle.setVisibility(true);
								textSandHill.setVisibility(true);
								textKoopaBeach.setVisibility(true);
								textOutsetIsland.setVisibility(true);
								textWeaponsBed.setVisibility(true);
								textMetalHarbor.setVisibility(true);
								textBOB.setVisibility(true);
								textRainbowRoad.setVisibility(true);
								
								textClassicSonic.setVisibility(false);
								textDollSonic.setVisibility(false);
								textMechaSonic.setVisibility(false);
								textDage4Aquatic.setVisibility(false);
								textManiaSonic.setVisibility(false);
								break;
								
							case 3:
								menuDisplayID = CHAR_SELECT;
								menuSelectionMAX = 4;
								menuSelection = 0;
								textResume.setVisibility(false);
								textRestart.setVisibility(false);
								textQuit.setVisibility(false);
								textLevelSelect.setVisibility(false);
								textCharSelect.setVisibility(false);
								
								textEmeraldCoast.setVisibility(false);
								textGreenHillZone.setVisibility(false);
								textSpeedHighway.setVisibility(false);
								textWuhuIsland.setVisibility(false);
								textPeachCastle.setVisibility(false);
								textSandHill.setVisibility(false);
								textKoopaBeach.setVisibility(false);
								textOutsetIsland.setVisibility(false);
								textWeaponsBed.setVisibility(false);
								textMetalHarbor.setVisibility(false);
								textBOB.setVisibility(false);
								textRainbowRoad.setVisibility(false);
								
								textClassicSonic.setVisibility(true);
								textDollSonic.setVisibility(true);
								textMechaSonic.setVisibility(true);
								textDage4Aquatic.setVisibility(true);
								textManiaSonic.setVisibility(true);
								break;
								
							case 4:
								MainGameLoop.gameState = MainGameLoop.gameStates.exiting;
								break;
								
							default:
								break;
						}
						break;
						
					case LEVEL_SELECT:
						switch (menuSelection)
						{
							case 0:
								MainGameLoop.levelID = MainGameLoop.levelIDs.EC;
								LevelLoader.loadLevel("Snowhead.lvl", true);
								unpause();
								break;
								
							case 1:
								MainGameLoop.levelID = MainGameLoop.levelIDs.SH;
								LevelLoader.loadLevel("SpeedHighway.lvl", true);
								unpause();
								break;
								
							case 2:
								MainGameLoop.levelID = MainGameLoop.levelIDs.GHZ;
								LevelLoader.loadLevel("GreenHillZone.lvl", true);
								unpause();
								break;
								
							case 3:
								MainGameLoop.levelID = MainGameLoop.levelIDs.WI;
								LevelLoader.loadLevel("WuhuIsland.lvl", true);
								unpause();
								break;
								
							case 4:
								MainGameLoop.levelID = MainGameLoop.levelIDs.PC;
								LevelLoader.loadLevel("PeachCastle.lvl", true);
								unpause();
								break;
								
							case 5:
								MainGameLoop.levelID = MainGameLoop.levelIDs.SHL;
								LevelLoader.loadLevel("SandHill.lvl", true);
								unpause();
								break;
								
							case 6:
								MainGameLoop.levelID = MainGameLoop.levelIDs.KB;
								LevelLoader.loadLevel("KoopaTroopaBeach.lvl", true);
								unpause();
								break;
								
							case 7:
								MainGameLoop.levelID = MainGameLoop.levelIDs.OI;
								LevelLoader.loadLevel("OutsetIsland.lvl", true);
								unpause();
								break;
								
							case 8:
								MainGameLoop.levelID = MainGameLoop.levelIDs.WB;
								LevelLoader.loadLevel("WeaponsBed.lvl", true);
								unpause();
								break;
								
							case 9:
								MainGameLoop.levelID = MainGameLoop.levelIDs.MH;
								LevelLoader.loadLevel("MetalHarbor.lvl", true);
								unpause();
								break;
								
							case 10:
								MainGameLoop.levelID = MainGameLoop.levelIDs.BOB;
								LevelLoader.loadLevel("BobOmbBattlefield.lvl", true);
								unpause();
								break;
								
							case 11:
								MainGameLoop.levelID = MainGameLoop.levelIDs.RR;
								LevelLoader.loadLevel("RainbowRoad.lvl", true);
								unpause();
								break;
								
						}
						break;
						
					case CHAR_SELECT:
						switch (menuSelection)
						{
							case 0:
								Ball.characterID = 0;
								break;
								
							case 1:
								if (MainGameLoop.unlockedSonicDoll)
								{
									Ball.characterID = 1;
								}
								break;
								
							case 2:
								if (MainGameLoop.unlockedMechaSonic)
								{
									Ball.characterID = 2;
								}
								break;
								
							case 3:
								if (MainGameLoop.unlockedDage4)
								{
									Ball.characterID = 3;
								}
								break;
								
							case 4:
								if (MainGameLoop.unlockedManiaSonic)
								{
									Ball.characterID = 4;
								}
								break;
						}
						break;
				
					default:
						break;
				}
			}
			
			if (backInput && !backInputPrevious)
			{
				if (menuDisplayID == ROOT)
				{
					shouldPause = true;
					//unpause();
				}
				else if (menuDisplayID != ROOT)
				{
					menuDisplayID = ROOT;
					menuSelection = 0;
					menuSelectionMAX = 4;
					textResume.setVisibility(true);
					textRestart.setVisibility(true);
					textQuit.setVisibility(true);
					textLevelSelect.setVisibility(true);
					textCharSelect.setVisibility(true);
					
					textEmeraldCoast.setVisibility(false);
					textGreenHillZone.setVisibility(false);
					textSpeedHighway.setVisibility(false);
					textWuhuIsland.setVisibility(false);
					textPeachCastle.setVisibility(false);
					textSandHill.setVisibility(false);
					textKoopaBeach.setVisibility(false);
					textOutsetIsland.setVisibility(false);
					textWeaponsBed.setVisibility(false);
					textMetalHarbor.setVisibility(false);
					textBOB.setVisibility(false);
					textRainbowRoad.setVisibility(false);
					
					textClassicSonic.setVisibility(false);
					textDollSonic.setVisibility(false);
					textMechaSonic.setVisibility(false);
					textDage4Aquatic.setVisibility(false);
					textManiaSonic.setVisibility(false);
				}
			}
			
			switch (menuDisplayID)
			{
				case ROOT:
					switch (menuSelection)
					{
						case 0: textCursor.getPosition().y = 0.3f; break;
							
						case 1: textCursor.getPosition().y = 0.4f; break;
							
						case 2: textCursor.getPosition().y = 0.5f; break;
						
						case 3: textCursor.getPosition().y = 0.6f; break;
						
						case 4: textCursor.getPosition().y = 0.7f; break;

						default: break;
					}
					break;
					
				case LEVEL_SELECT:
					float spacing = 1/12.0f;
					switch (menuSelection)
					{
						case 0: textCursor.getPosition().y = 0.0f; break;
							
						case 1: textCursor.getPosition().y = spacing; break;
							
						case 2: textCursor.getPosition().y = spacing*2; break;
							
						case 3: textCursor.getPosition().y = spacing*3; break;
							
						case 4: textCursor.getPosition().y = spacing*4; break;
						
						case 5: textCursor.getPosition().y = spacing*5; break;
						
						case 6: textCursor.getPosition().y = spacing*6; break;
						
						case 7: textCursor.getPosition().y = spacing*7; break;
						
						case 8: textCursor.getPosition().y = spacing*8; break;
						
						case 9: textCursor.getPosition().y = spacing*9; break;
						
						case 10: textCursor.getPosition().y = spacing*10; break;
						
						case 11: textCursor.getPosition().y = spacing*11; break;
					}
					break;
					
				case CHAR_SELECT:
					switch (menuSelection)
					{
						case 0: textCursor.getPosition().y = 0.3f; break;
							
						case 1: textCursor.getPosition().y = 0.4f; break;
							
						case 2: textCursor.getPosition().y = 0.5f; break;
						
						case 3: textCursor.getPosition().y = 0.6f; break;
						
						case 4: textCursor.getPosition().y = 0.7f; break;
						
					}
					break;
			
				default:
					break;
			}
			
			moveYPrevious = moveY;
			selectInputPrevious = selectInput;
			backInputPrevious = backInput;
		}
	}
	
	private static void unpause()
	{
		MainGameLoop.gameState = MainGameLoop.gameStates.running;
		//MainGameLoop.gameGuis.remove(guiTextureCursor);
		//MainGameLoop.gameGuis.remove(guiTextureResume0);
		//MainGameLoop.gameGuis.remove(guiTextureResume1);
		//MainGameLoop.gameGuis.remove(guiTextureResume2);
		//MainGameLoop.gameGuis.remove(guiTextureResume3);
		//MainGameLoop.gameGuis.remove(guiTextureQuit0);
		//MainGameLoop.gameGuis.remove(guiTextureQuit1);
		if (textCursor != null)
		{
			//textCursor.delete();
			//textCursor = null;
			textCursor.setVisibility(false);
		}
		if (textResume != null)
		{
			textResume.delete();
			textResume = null;
		}
		if (textRestart != null)
		{
			textRestart.delete();
			textRestart = null;
		}
		if (textQuit != null)
		{
			textQuit.delete();
			textQuit = null;
		}
		if (textLevelSelect != null)
		{
			textLevelSelect.delete();
			textLevelSelect = null;
		}
		if (textCharSelect != null)
		{
			textCharSelect.delete();
			textCharSelect = null;
		}
		
		if (textEmeraldCoast != null)
		{
			textEmeraldCoast.delete();
			textEmeraldCoast = null;
		}
		if (textGreenHillZone != null)
		{
			textGreenHillZone.delete();
			textGreenHillZone = null;
		}
		if (textSpeedHighway != null)
		{
			textSpeedHighway.delete();
			textSpeedHighway = null;
		}
		if (textWuhuIsland != null)
		{
			textWuhuIsland.delete();
			textWuhuIsland = null;
		}
		if (textPeachCastle != null)
		{
			textPeachCastle.delete();
			textPeachCastle = null;
		}
		if (textSandHill != null)
		{
			textSandHill.delete();
			textSandHill = null;
		}
		if (textKoopaBeach != null)
		{
			textKoopaBeach.delete();
			textKoopaBeach = null;
		}
		if (textOutsetIsland != null)
		{
			textOutsetIsland.delete();
			textOutsetIsland = null;
		}
		if (textWeaponsBed != null)
		{
			textWeaponsBed.delete();
			textWeaponsBed = null;
		}
		if (textMetalHarbor != null)
		{
			textMetalHarbor.delete();
			textMetalHarbor = null;
		}
		if (textBOB != null)
		{
			textBOB.delete();
			textBOB = null;
		}
		if (textRainbowRoad != null)
		{
			textRainbowRoad.delete();
			textRainbowRoad = null;
		}
		
		if (textClassicSonic != null)
		{
			textClassicSonic.delete();
			textClassicSonic = null;
		}
		if (textDollSonic != null)
		{
			textDollSonic.delete();
			textDollSonic = null;
		}
		if (textMechaSonic != null)
		{
			textMechaSonic.delete();
			textMechaSonic = null;
		}
		if (textDage4Aquatic != null)
		{
			textDage4Aquatic.delete();
			textDage4Aquatic = null;
		}
		if (textManiaSonic != null)
		{
			textManiaSonic.delete();
			textManiaSonic = null;
		}
	}
	
	public static void pause()
	{
		MainGameLoop.gameState = MainGameLoop.gameStates.paused;
		menuSelection = 0;
		menuDisplayID = 0;
		menuSelectionMAX = 4;
		//MainGameLoop.gameGuis.add(guiTextureCursor);
		//MainGameLoop.gameGuis.add(guiTextureResume0);
		//MainGameLoop.gameGuis.add(guiTextureResume1);
		//MainGameLoop.gameGuis.add(guiTextureResume2);
		//MainGameLoop.gameGuis.add(guiTextureResume3);
		//MainGameLoop.gameGuis.add(guiTextureQuit0);
		//MainGameLoop.gameGuis.add(guiTextureQuit1);
		//textCursor = new GUIText("->", 3, font, new Vector2f(0.3f, 0.5f), 1f, false, true);
		textCursor.setVisibility(true);
		textResume = new GUIText("Resume", 3, font, new Vector2f(0.5f, 0.3f), 1f, false, true);
		textRestart = new GUIText("Restart", 3, font, new Vector2f(0.5f, 0.4f), 1f, false, true);
		textLevelSelect = new GUIText("Level Select", 3, font, new Vector2f(0.5f, 0.5f), 1f, false, true);
		textCharSelect = new GUIText("Character Select", 3, font, new Vector2f(0.5f, 0.6f), 1f, false, true);
		textQuit = new GUIText("Quit Game", 3, font, new Vector2f(0.5f, 0.7f), 1f, false, true);
		
		float spacing = 1/12.0f;
		
		textEmeraldCoast = new GUIText("Emerald Coast", 3, font, new Vector2f(0.5f, 0.0f), 1f, false, false);
		textSpeedHighway = new GUIText("Speed Highway", 3, font, new Vector2f(0.5f, spacing), 1f, false, false);
		textGreenHillZone = new GUIText("Green Hill Zone", 3, font, new Vector2f(0.5f, spacing*2), 1f, false, false);
		textWuhuIsland = new GUIText("Wuhu Island", 3, font, new Vector2f(0.5f, spacing*3), 1f, false, false);
		textPeachCastle = new GUIText("Peach's Castle", 3, font, new Vector2f(0.5f, spacing*4), 1f, false, false);
		textSandHill = new GUIText("Sand Hill", 3, font, new Vector2f(0.5f, spacing*5), 1f, false, false);
		textKoopaBeach = new GUIText("Koopa Beach", 3, font, new Vector2f(0.5f, spacing*6), 1f, false, false);
		textOutsetIsland = new GUIText("Outset Island", 3, font, new Vector2f(0.5f, spacing*7), 1f, false, false);
		textWeaponsBed = new GUIText("Weapons Bed", 3, font, new Vector2f(0.5f, spacing*8), 1f, false, false);
		textMetalHarbor = new GUIText("Metal Harbor", 3, font, new Vector2f(0.5f, spacing*9), 1f, false, false);
		textBOB = new GUIText("Bob-omb Btlfld", 3, font, new Vector2f(0.5f, spacing*10), 1f, false, false);
		textRainbowRoad = new GUIText("Rainbow Road", 3, font, new Vector2f(0.5f, spacing*11), 1f, false, false);
		
		textClassicSonic = new GUIText("Classic Sonic", 3, font, new Vector2f(0.5f, 0.3f), 1f, false, false);
		textDollSonic = new GUIText("Sonic Doll", 3, font, new Vector2f(0.5f, 0.4f), 1f, false, false);
		textMechaSonic = new GUIText("Mecha Sonic", 3, font, new Vector2f(0.5f, 0.5f), 1f, false, false);
		textDage4Aquatic = new GUIText("Dage4 Aquatic", 3, font, new Vector2f(0.5f, 0.6f), 1f, false, false);
		textManiaSonic = new GUIText("Mania Sonic", 3, font, new Vector2f(0.5f, 0.7f), 1f, false, false);
	}
}
