package toolbox;

import java.util.ArrayList;
import org.lwjgl.util.vector.Vector3f;
import engineTester.MainGameLoop;
import entities.BlueCoin;
import entities.Ring;
import entities.Entity;
import entities.RedCoin;

public class CoinManager 
{
	private static ArrayList<Entity> currentLevel;
	
	public static void loadCoinsToLevels()
	{
		currentLevel = MainGameLoop.levelHUBEntities;
		
		//currentLevel.add(new Coin(new Vector3f(10, 10, 10)));
		//currentLevel.add(new Coin(new Vector3f(20, 10, 10)));
		//currentLevel.add(new Coin(new Vector3f(30, 10, 10)));
		//currentLevel.add(new Coin(new Vector3f(40, 10, 10)));
		//currentLevel.add(new Coin(new Vector3f(50, 10, 10)));
		
		//generateLine(2, new Vector3f(10, 20, 10), new Vector3f(50, 20, 10));
		//generateCircle(10, 36, new Vector3f(50, 30, 50));
		
		currentLevel = MainGameLoop.levelWFEntities;
		
		generateLine(5, new Vector3f(-182, 85, 258), new Vector3f(-217, 85, 258));
		generateLine(5, new Vector3f(30, 208, 130), new Vector3f(-13, 208, 130));
		generateLine(5, new Vector3f(184, 97, 196), new Vector3f(184, 115, 140));
		generateLine(5, new Vector3f(220, 53, 186), new Vector3f(220, 69, 131));
		generateCircle(8, 20, new Vector3f(45, 74, 126));
		generateCircle(8, 20, new Vector3f(280, 20, -44));
		generateCircle(8, 20, new Vector3f(-287, 140, -75));
		generateCircle(8, 20, new Vector3f(167, 268, -197));
		
		currentLevel.add(new RedCoin(new Vector3f(221, 109, -1)));
		currentLevel.add(new RedCoin(new Vector3f(42, 198+8, -61)));
		currentLevel.add(new RedCoin(new Vector3f(16, 198+8, 49)));
		currentLevel.add(new RedCoin(new Vector3f(-109, 145, 122)));
		currentLevel.add(new RedCoin(new Vector3f(127, 285, -126)));
		currentLevel.add(new RedCoin(new Vector3f(54, 283, -309)));
		currentLevel.add(new RedCoin(new Vector3f(169, 251, -161)));
		currentLevel.add(new Ring(new Vector3f(-145, 206, 174)));
		currentLevel.add(new Ring(new Vector3f(-125, 206, 183)));
		currentLevel.add(new RedCoin(new Vector3f(-107.5f, 206, 186)));
		currentLevel.add(new Ring(new Vector3f(-90, 206, 183)));
		currentLevel.add(new Ring(new Vector3f(-70, 206, 174)));
		currentLevel.add(new Ring(new Vector3f(-1.58f, 288, -251)));
		currentLevel.add(new Ring(new Vector3f(6.4f, 288, -260)));
		currentLevel.add(new Ring(new Vector3f(14, 288, -268)));
		currentLevel.add(new Ring(new Vector3f(17, 288, -258)));
		currentLevel.add(new Ring(new Vector3f(3.59f, 288, -272)));
		currentLevel.add(new BlueCoin(new Vector3f(281f, 30, 345)));
		currentLevel.add(new BlueCoin(new Vector3f(58f, 208, -63)));
		currentLevel.add(new BlueCoin(new Vector3f(-32f, 208, 92)));
		currentLevel.add(new BlueCoin(new Vector3f(-293f, 40, -130)));
	}
	
	private static void generateLine(int coinCount, Vector3f point1, Vector3f point2)
	{
		float xDiff = point2.getX()-point1.getX();
		float yDiff = point2.getY()-point1.getY();
		float zDiff = point2.getZ()-point1.getZ();
		//float totalDistance = (float)Math.sqrt(xDiff*xDiff+yDiff*yDiff+zDiff*zDiff);
		
		if(coinCount > 1)
		{
			for(int i = 0; i < coinCount; i++)
			{
				currentLevel.add(new Ring(new Vector3f(point1.getX() + i*(xDiff/(coinCount-1)),
													   point1.getY() + i*(yDiff/(coinCount-1)),
													   point1.getZ() + i*(zDiff/(coinCount-1)))));
			}
		}
		else
		{
			currentLevel.add(new Ring(point1));
		}
	}
	
	private static void generateCircle(int coinCount, float radius, Vector3f centerPoint)
	{
		if(coinCount > 1)
		{
			float degreeSegment = 360.0f/coinCount;
			Vector3f newPoint = new Vector3f(0,centerPoint.getY(),0);
			
			
			for(int i = 0; i < coinCount; i++)
			{
				newPoint.setX(centerPoint.getX() + radius*(float)Math.cos(Math.toRadians(degreeSegment*i)));
				newPoint.setZ(centerPoint.getZ() + radius*(float)Math.sin(Math.toRadians(degreeSegment*i)));
				currentLevel.add(new Ring(new Vector3f(newPoint.x, newPoint.y, newPoint.z)));
			}
		}
		else
		{
			currentLevel.add(new Ring(centerPoint));
		}
	}
	
	private static void generateHalfCircle(int coinCount, float radius, Vector3f centerPoint)
	{
		if(coinCount > 1)
		{
			float degreeSegment = 180.0f/coinCount;
			Vector3f newPoint = new Vector3f(0,centerPoint.getY(),0);
			
			
			for(int i = 0; i < coinCount; i++)
			{
				newPoint.setX(centerPoint.getX() + radius*(float)Math.cos(Math.toRadians(degreeSegment*i)));
				newPoint.setZ(centerPoint.getZ() + radius*(float)Math.sin(Math.toRadians(degreeSegment*i)));
				currentLevel.add(new Ring(new Vector3f(newPoint.x, newPoint.y, newPoint.z)));
			}
		}
		else
		{
			currentLevel.add(new Ring(centerPoint));
		}
	}
}
