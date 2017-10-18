package renderEngine;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import entities.Entity;
import entities.Light;
import toolbox.Maths;

public class SkyManager 
{
	private static float sunAngle;
	private static float sunRadius;
	private static float timeOfDay; //0 = morning, 360 = next morning
	private static float sunModelRadius;

	private static Vector3f currentFogColourDay;
	private static Vector3f currentFogColourNight;
	private static Vector3f colourFogInterpolated;

	private static Vector3f colourSunNight;
	private static Vector3f colourSunDay;
	private static Vector3f colourSunInterpolated;

	private static Vector3f colourMoonDay;
	private static Vector3f colourMoonNight;
	private static Vector3f colourMoonInterpolated;

	private static Vector3f colourFogDayOutside;
	private static Vector3f colourFogNightOutside;
	
	public static float fogDensity = 0.00005f;
	public static float fogGradient = 2.0f;

	private static float dayFactor;
	private static float nightFactor;

	private static Light lightSun;
	private static Light lightMoon;

	private static Entity entitySun;
	private static Entity entitySkySphere;
	private static Entity centerObject;
	
	private SkyManager()
	{
		
	}
	
	public static void initSkyManager(Light myLightSun, Light myLightMoon, Entity mySun, Entity mySkySphere, Entity center)
	{
		sunAngle = 0.0f;
		lightSun = myLightSun;
		lightMoon = myLightMoon;
		timeOfDay = 0.0f;
		sunRadius = 100000;//1000000.0f;//new 100000 as of 9/16/2015 //old 100000
		sunModelRadius = 7400; //old 5500
		
		colourFogDayOutside = new Vector3f(49/255f, 101/255f, 231/255f); //new Vector3f(0.55f, 0.8f, 1.0f);
		colourFogNightOutside = new Vector3f(0.05f, 0.05f, 0.075f);
		
		currentFogColourNight = colourFogNightOutside;
		currentFogColourDay = colourFogDayOutside;
		//colourFogDay = new Vector3f(0.055f, 0.08f, 0.1f);
		
		colourSunNight = new Vector3f(0.0f, 0.0f, 0.0f);
		colourSunDay = new Vector3f(1.0f, 1.0f, 1.0f);
		
		colourMoonNight = new Vector3f(0.3f, 0.3f, 0.45f);
		colourMoonDay = new Vector3f(0f, 0f, 0f);
		
		colourSunInterpolated = new Vector3f(1,1,1);
		colourFogInterpolated = new Vector3f(1,1,1);
		colourMoonInterpolated = new Vector3f(1,1,1);
		
		dayFactor = 1;
		nightFactor = 1;
		
		entitySun = mySun;
		centerObject = center;
		entitySkySphere = mySkySphere;
		
		timeOfDay = 90;
	}
	
	public static void calculateValues()
	{
		timeOfDay = (timeOfDay+360)%360;
		sunAngle = (float)Math.toRadians(timeOfDay);
		float sunPeakAngle = 60; //70
		float sunVangle = (float)Math.toRadians(sunPeakAngle*Math.sin(sunAngle));
		float sunHangle = (float)Math.toRadians(90*Math.cos(sunAngle));
		float moonVangle = (float)Math.toRadians(sunPeakAngle*Math.sin(sunAngle-Math.PI));
		float moonHangle = (float)Math.toRadians(90*Math.cos(sunAngle));
		
		Vector3f center = centerObject.getPosition();
		Vector3f sunOffset = Maths.spherePositionFromAngles(sunHangle, sunVangle, sunRadius);
		Vector3f moonOffset = Maths.spherePositionFromAngles(moonHangle, moonVangle, sunRadius);
		Vector3f modelOffset = Maths.spherePositionFromAngles(sunHangle, sunVangle, sunModelRadius);
		
		lightSun.setPositionX(center.x+sunOffset.x);
		lightSun.setPositionY(sunOffset.y);
		lightSun.setPositionZ(center.z-sunOffset.z);
		
		entitySun.setX(center.x+modelOffset.x);
		entitySun.setY(modelOffset.y);
		entitySun.setZ(center.z-modelOffset.z);
		
		lightMoon.setPositionX(center.x+moonOffset.x);
		lightMoon.setPositionY(moonOffset.y);
		lightMoon.setPositionZ(center.z-moonOffset.z);
		
		//System.out.println("sunHangle = "+Math.toDegrees(sunHangle));
		//System.out.println("sunVangle = "+Math.toDegrees(sunVangle));
		//System.out.println();
		
		/*
		lightSun.setPositionX(center.x-0.5f*(float)(sunRadius*Math.sin(sunAngle)));
		lightSun.setPositionY((float)(sunRadius*Math.sin(sunAngle)));
		lightSun.setPositionZ(center.z+(float)(sunRadius*Math.cos(sunAngle)));
		
		entitySun.setX(center.x-0.5f*(float)(sunModelRadius*Math.sin(sunAngle)));
		entitySun.setY((float)(sunModelRadius*Math.sin(sunAngle)));
		entitySun.setZ(center.z+(float)(sunModelRadius*Math.cos(sunAngle)));
		
		lightMoon.setPositionX(center.x-0.5f*(float)(sunRadius*Math.sin(sunAngle-(Math.PI))));
		lightMoon.setPositionY((float)(sunRadius*Math.sin(sunAngle-(Math.PI))));
		lightMoon.setPositionZ(center.z+(float)(sunRadius*Math.cos(sunAngle-(Math.PI))));
		*/
		
		if(timeOfDay >= 180)
		{
			colourFogInterpolated.set(currentFogColourNight);
			colourSunInterpolated.set(colourSunNight);
			colourMoonInterpolated.set(colourMoonNight);
		}
		else
		{
			colourFogInterpolated.set(currentFogColourDay);
			colourSunInterpolated.set(colourSunDay);
			colourMoonInterpolated.set(colourMoonDay);
		}
		
		if(Math.sin(sunAngle) <= 0.2 && Math.sin(sunAngle) >= -0.2)//  sunrise / sunset
		{
			dayFactor = (float)((Math.sin(sunAngle)*2.5)+0.5);
			nightFactor = 1-dayFactor;
			
			colourFogInterpolated.x = ((currentFogColourNight.x*nightFactor) + (currentFogColourDay.x*dayFactor));
			colourFogInterpolated.y = ((currentFogColourNight.y*nightFactor) + (currentFogColourDay.y*dayFactor));
			colourFogInterpolated.z = ((currentFogColourNight.z*nightFactor) + (currentFogColourDay.z*dayFactor));
			
			colourSunInterpolated.x = ((colourSunNight.x*nightFactor) + (colourSunDay.x*dayFactor));
			colourSunInterpolated.y = ((colourSunNight.y*nightFactor) + (colourSunDay.y*dayFactor));
			colourSunInterpolated.z = ((colourSunNight.z*nightFactor) + (colourSunDay.z*dayFactor));
			
			colourMoonInterpolated.x = ((colourMoonNight.x*nightFactor) + (colourMoonDay.x*dayFactor));
			colourMoonInterpolated.y = ((colourMoonNight.y*nightFactor) + (colourMoonDay.y*dayFactor));
			colourMoonInterpolated.z = ((colourMoonNight.z*nightFactor) + (colourMoonDay.z*dayFactor));
		}
		
		lightSun.setColour(colourSunInterpolated);
		lightMoon.setColour(colourMoonInterpolated);
	}
	
	public static Vector3f getFogColour()
	{
		return colourFogInterpolated;
	}

	public static float getFogRed() 
	{
		return colourFogInterpolated.x;
	}

	public static float getFogGreen() 
	{
		return colourFogInterpolated.y;
	}

	public static float getFogBlue() 
	{
		return colourFogInterpolated.z;
	}
	
	public static void increaseTimeOfDay(float timeIncrease)
	{
		setTimeOfDay((timeOfDay+timeIncrease)%360);
	}
	
	public static void setTimeOfDay(float timeSet)
	{
		timeOfDay = timeSet;
		calculateValues();
	}
	
	public static float getTimeOfDay()
	{
		return timeOfDay;
	}
	
	public static void setSkySphereVisibility(boolean newVisibility)
	{
		entitySkySphere.setVisibility(newVisibility);
	}
	
	public static float getOverallBrightness()
	{
		float rawVal = (float)Math.sin(sunAngle);
		if(rawVal >= 0)
		{
			//System.out.println(rawVal*0.5+0.5);
			return (rawVal*0.5f+0.5f);
		}
		rawVal = rawVal*-0.3f;
		//System.out.println(rawVal*0.75+0.25f);
		return rawVal*0.75f+0.25f;
	}
	
	/*
	public static void determineFogColours()
	{
		switch(MainGameLoop.levelID)
		{
			case MainGameLoop.levelIDs.LLL:
				currentFogColourDay = colourFogDayLLL;
				currentFogColourNight = colourFogNightLLL;
				break;
				
			case MainGameLoop.levelIDs.BOB:
				currentFogColourDay = colourFogDayBOB;
				currentFogColourNight = colourFogNightBOB;
				break;
				
			case MainGameLoop.levelIDs.PSS:
				currentFogColourDay = colourFogDayPSS;
				currentFogColourNight = colourFogNightPSS;
				break;
				
			default:
				currentFogColourDay = colourFogDayBOB;
				currentFogColourNight = colourFogNightBOB;
				break;
		}
	}
	*/
	
	public static void setFogColours(Vector3f newFogDay, Vector3f newFogNight)
	{
		currentFogColourDay.set(newFogDay);
		currentFogColourNight.set(newFogNight);
	}
	
	public static void setCenterObject(Entity newCenter)
	{
		centerObject = newCenter;
	}
	
	public static void setSunColorDay(Vector3f newSunColor)
	{
		colourSunDay.set(newSunColor);
	}
	
	public static void setSunColorNight(Vector3f newSunColor)
	{
		colourSunNight.set(newSunColor);
	}
	
	public static void setMoonColorDay(Vector3f newSunColor)
	{
		colourMoonDay.set(newSunColor);
	}
	
	public static void setMoonColorNight(Vector3f newSunColor)
	{
		colourMoonNight.set(newSunColor);
	}
}
