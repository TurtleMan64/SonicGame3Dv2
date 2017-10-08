package entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;

public class DrakoPointsList extends Entity
{
	ArrayList<ArrayList<Ball>> listOfLists;
	int currentListIndex;
	
	boolean upInput;
	boolean previousUpInput;
	
	boolean downInput;
	boolean previousDownInput;
	
	public DrakoPointsList()
	{
		super(new Vector3f(0,0,0));
		super.setVisibility(false);
		
		listOfLists = new ArrayList<ArrayList<Ball>>();
		currentListIndex = 0;
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("SA2Drako.txt"));
			String line;
			ArrayList<Ball> currentList = null;
			
			while((line = reader.readLine()) != null)
			{
				String[] data = line.split(" ");
				if(data[0].equals("Start"))
				{
					currentList = new ArrayList<Ball>();
					listOfLists.add(currentList);
				}
				else if(data[0].equals("30"))
				{
					currentList.add(new Ball(new Vector3f(Float.parseFloat(data[1])/1f,
											  	          Float.parseFloat(data[2])/1f,
											              Float.parseFloat(data[3])/1f)));
				}
			}
			reader.close();
		}
		catch(IOException e)
		{
			System.err.println("Could not read SA2Drako.txt");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void step()
	{
		previousUpInput = upInput;
		upInput = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_T))
		{
			upInput = true;
		}
		previousDownInput = downInput;
		downInput = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_G))
		{
			downInput = true;
		}
		
		if(upInput && !previousUpInput)
		{
			ArrayList<Ball> currList = listOfLists.get(currentListIndex);
			for(Ball ball : currList)
			{
				MainGameLoop.gameEntitiesToDelete.add(ball);
			}
			
			if(currentListIndex > 0)
			{
				currentListIndex-=1;
				currList = listOfLists.get(currentListIndex);
				for(Ball ball : currList)
				{
					MainGameLoop.gameEntitiesToAdd.add(ball);
				}
			}
		}
		
		if(downInput && !previousDownInput)
		{
			ArrayList<Ball> currList = listOfLists.get(currentListIndex);
			for(Ball ball : currList)
			{
				MainGameLoop.gameEntitiesToDelete.add(ball);
			}
			
			if(currentListIndex < listOfLists.size()-1)
			{
				currentListIndex+=1;
				currList = listOfLists.get(currentListIndex);
				for(Ball ball : currList)
				{
					MainGameLoop.gameEntitiesToAdd.add(ball);
				}
			}
		}
	}
}
