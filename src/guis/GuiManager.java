package guis;

import org.lwjgl.util.vector.Vector2f;

import engineTester.MainGameLoop;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;

public class GuiManager
{
	public static FontType fontVip;
	private static GUIText textTimer = null;
	
	private static GUIText text0 = null;
	private static GUIText text1 = null;
	private static GUIText text2 = null;
	private static GUIText text3 = null;
	private static GUIText text4 = null;
	private static GUIText text5 = null;
	private static GUIText text6 = null;
	private static GUIText text7 = null;
	private static GUIText text8 = null;
	private static GUIText text9 = null;
	
	private static boolean timerIsRunning = false;
	private static int centiseconds = 0;
	private static int seconds = 0;
	private static int minutes = 0;
	
	private static int rings = 0;
	private static GUIText textRings = null;
	
	public static void init()
	{
		fontVip = new FontType(MainGameLoop.gameLoader.loadTexture("Fonts/vipnagorgialla"), "Fonts/vipnagorgialla");
		textTimer = new GUIText("0", 1, fontVip, new Vector2f(0.01f, 0.01f), 1f, false, false);
		textRings = new GUIText("0", 1, fontVip, new Vector2f(0.01f, 0.01f), 1f, false, false);
	}
	
	public static void refresh()
	{
		textTimer.delete();
		textTimer = null;
		String partMin = minutes+"";
		if (minutes < 10)
		{
			partMin = "0"+minutes;
		}
		String partSec = seconds+"";
		if (seconds < 10)
		{
			partSec = "0"+seconds;
		}
		String partCen = ((centiseconds*100)/60)+"";
		if ((centiseconds*100)/60 < 10)
		{
			partCen = "0"+((centiseconds*100)/60);
		}
		String timer = partMin+":"+partSec+"."+partCen;
		textTimer = new GUIText(timer, 1.5f, fontVip, new Vector2f(0.01f, 0.01f), 1f, false, true);
		
		textRings.delete();
		textRings = null;
		textRings = new GUIText(""+rings, 1.5f, fontVip, new Vector2f(0.01f, 0.06f), 1f, false, true);
	}
	
	public static void increaseTimer()
	{
		if (timerIsRunning)
		{
			centiseconds++;
			if (centiseconds >= 60)
			{
				centiseconds = 0;
				seconds++;
				if (seconds >= 60)
				{
					seconds = 0;
					minutes++;
					if (minutes >= 99)
					{
						minutes=99;
					}
				}
			}
		}
	}
	
	public static void setTimer(int minutes, int seconds, int centiseconds)
	{
		GuiManager.minutes = minutes;
		GuiManager.seconds = seconds;
		GuiManager.centiseconds = centiseconds;
	}
	
	public static void startTimer()
	{
		timerIsRunning = true;
	}
	
	public static void stopTimer()
	{
		timerIsRunning = false;
	}
	
	public static void setRings(int newRings)
	{
		rings = newRings;
	}
	
	public static int getRings()
	{
		return rings;
	}
	
	public static void incrementRings()
	{
		rings++;
	}
	
	public static int getMinutes()
	{
		return minutes;
	}
	
	public static int getSeconds()
	{
		return seconds;
	}
	
	//Total time on timer in seconds
	public static float getTotalTimer()
	{
		return minutes*60+seconds+centiseconds/60.0f;
	}
}
