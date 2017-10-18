package toolbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import org.lwjgl.util.vector.Vector3f;

import audio.AudioSources;
import collision.CollisionChecker;
import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.Ball;
import entities.Dashpad;
import entities.Entity;
import entities.GoalSign;
import entities.GoalTrigger;
import entities.ItemCapsule;
import entities.ManiaSonicModels;
import entities.Motobug;
import entities.NPC;
import entities.Palmtree;
import entities.RhinoTank;
import entities.Ring;
import entities.SkySphere;
import entities.SpeedRamp;
import entities.Spinner;
import entities.Spring;
import entities.TeleportZone;
import entities.BEACH.BOBWoodPlank;
import entities.BEACH.BeachFlatWater;
import entities.BEACH.BeachFloatingPlatform;
import entities.BEACH.BeachUmbrella;
import entities.BEACH.BeachWaterfall;
import entities.BEACH.KoopaBeachWaterfall;
import entities.BEACH.PeachCastleWaterfall;
import entities.GHILL.GHillFallingPlatform;
import entities.GHILL.GHillFloatingPlatform;
import entities.GHILL.GHillFlower;
import entities.GHILL.GHillGrass;
import entities.GHILL.GHillRock;
import entities.GHILL.GHillSunflower;
import entities.GHILL.GHillTotem;
import entities.GHILL.GHillTotemWings;
import entities.GHILL.GHillTree;
import guis.GuiManager;
import models.RawModel;
import models.TexturedModel;
import network.NetworkManager;
import objConverter.OBJFileLoader;
import particles.Particle;
import particles.ParticleResources;
import renderEngine.SkyManager;

public class LevelLoader
{
	private LevelLoader()
	{
		
	}
	
	public static void loadTitle()
	{
		if (MainGameLoop.gameStage != null && MainGameLoop.gameStage.getModels() != null)
		{
			//clear huge level data from memory
			MainGameLoop.gameLoader.deleteModel(MainGameLoop.gameStage.getModels());
		}
		
		freeAllStaticModels();
		
		MainGameLoop.gameTransparentEntities.clear();
		MainGameLoop.gameTransparentEntities = null;
		MainGameLoop.gameTransparentEntities = new LinkedList<Entity>();
		 
		MainGameLoop.gameEntities.clear();
		MainGameLoop.gameEntities = null;
		MainGameLoop.gameEntities = new ArrayList<Entity>();
		
		MainGameLoop.gameEntitiesToAdd.clear();
		MainGameLoop.gameEntitiesToAdd = null;
		MainGameLoop.gameEntitiesToAdd = new ArrayList<Entity>();
		
		MainGameLoop.gameEntitiesToDelete.clear();
		MainGameLoop.gameEntitiesToDelete = null;
		MainGameLoop.gameEntitiesToDelete = new ArrayList<Entity>();
	}
	
	/**
	 * @param levelFilename level file to look for
	 * @param model 
	 * @param colModel
	 * @param entities
	 */
	public static void loadLevel(String levelFilename, boolean resetTimeOfDay)
	{
		if (levelFilename == null)
		{
			return;
		}
		if (levelFilename.endsWith(".lvl") == false)
		{
			return;
		}
		
		MainGameLoop.levelName = levelFilename;
		if (MainGameLoop.gameStage != null && MainGameLoop.gameStage.getModels() != null)
		{
			//clear huge level data from memory
			MainGameLoop.gameLoader.deleteModel(MainGameLoop.gameStage.getModels());
		}
		
		freeAllStaticModels();
		
		MainGameLoop.gameTransparentEntities.clear();
		MainGameLoop.gameTransparentEntities = null;
		MainGameLoop.gameTransparentEntities = new LinkedList<Entity>();
		 
		MainGameLoop.gameEntities.clear();
		MainGameLoop.gameEntities = null;
		MainGameLoop.gameEntities = new ArrayList<Entity>();
		
		MainGameLoop.gameEntitiesToAdd.clear();
		MainGameLoop.gameEntitiesToAdd = null;
		MainGameLoop.gameEntitiesToAdd = new ArrayList<Entity>();
		
		MainGameLoop.gameEntitiesToDelete.clear();
		MainGameLoop.gameEntitiesToDelete = null;
		MainGameLoop.gameEntitiesToDelete = new ArrayList<Entity>();
		
		InputStream is = null;
    	InputStreamReader isr = null;
    	try
        {
        	//is = Class.class.getResourceAsStream("/res/Data/" + levelFilename);
    		//is = Thread.currentThread().getContextClassLoader().getResourceAsStream("res/Data/" + levelFilename);
    		is = new FileInputStream("res/Data/" + levelFilename);
        	if (is == null)
        	{
        		System.out.println("Couldnt load input stream: '"+("/res/Data/" + levelFilename)+"'");
        	}
        	isr = new InputStreamReader(is);
        } 
        catch (NullPointerException e) 
        {
        	System.out.println("Couldnt load input stream: '"+("/res/Data/" + levelFilename)+"'");
            e.printStackTrace();
            return;
        }
    	catch (FileNotFoundException e)
    	{
			e.printStackTrace();
		}
    	
        BufferedReader reader = new BufferedReader(isr);
        Scanner input = new Scanner(reader);
        

        //try
        {
        	String modelFLoc = input.nextLine();
        	//System.out.println("modelFLoc = '"+modelFLoc+"'");
        	String modelFName = input.nextLine();
        	//System.out.println("modelFName = '"+modelFName+"'");
        	String colFLoc = input.nextLine();
        	//System.out.println("colFLoc = '"+colFLoc+"'");
        	
        	CollisionChecker.clearCollideModels();
        	int numChunks = input.nextInt();
        	//System.out.println("numChunks = '"+numChunks+"'");
        	input.nextLine();
        	
        	while (numChunks > 0)
        	{
        		String colFilename = input.nextLine();
        		//System.out.println("colFilename = '"+colFilename+"'");
        		CollisionModel colModel = OBJFileLoader.loadCollisionOBJ("Models/"+colFLoc+"/", colFilename);
                CollisionChecker.addCollideModel(colModel);
                numChunks--;
        	}
        	
        	SkyManager.setSunColorDay(new Vector3f(nextFloat(input), nextFloat(input), nextFloat(input)));
        	SkyManager.setSunColorNight(new Vector3f(nextFloat(input), nextFloat(input), nextFloat(input)));
        	SkyManager.setMoonColorDay(new Vector3f(nextFloat(input), nextFloat(input), nextFloat(input)));
        	SkyManager.setMoonColorNight(new Vector3f(nextFloat(input), nextFloat(input), nextFloat(input)));

        	Vector3f fogDay = new Vector3f(nextFloat(input), nextFloat(input), nextFloat(input));
        	Vector3f fogNight = new Vector3f(nextFloat(input), nextFloat(input), nextFloat(input));
        	
        	SkyManager.fogDensity = nextFloat(input);
        	SkyManager.fogGradient = nextFloat(input);
        	
        	float timeOfDay = nextFloat(input);//input.nextFloat();
        	//System.out.println("timeOfDay = '"+timeOfDay+"'");
        	
        	SkyManager.setSkySphereVisibility(false);
        	if (resetTimeOfDay)
        	{
        		SkyManager.setTimeOfDay(timeOfDay);
        	}
        	SkyManager.setFogColours(fogDay, fogNight);
        	SkyManager.calculateValues();
        	
        	float camYaw = nextFloat(input);//input.nextFloat();
        	float camPitch = nextFloat(input);//input.nextFloat();
        	MainGameLoop.gameCamera.setYaw(camYaw);
        	MainGameLoop.gameCamera.setPitch(camPitch);
        	
        	while (input.hasNextLine())
            {
        		//Poll the controller to not miss events (Buttons get stuck)
        		if (Joystick.joystickExists())
    			{
    				Joystick.poll();
    			}
        		
        		processLine(input.nextInt(), input, MainGameLoop.gameEntities);
            }
            
            input.close();
            try
            {
				reader.close();
				is.close();
	            isr.close();
			}
            catch (IOException e)
            {
				e.printStackTrace();
			}
            
            TexturedModel[] model = ConvenientMethods.loadModel("Models/"+modelFLoc+"/", modelFName);
            
            MainGameLoop.gameStage.setModels(model);
            MainGameLoop.gameEntities.add(MainGameLoop.gameStage);
            
            for (Entity entity : MainGameLoop.gameEntities)
            {
            	entity.respawn();
            }
            
            Joystick.clearControllerButtons();
            
            GuiManager.setTimer(0, 0, 0);
            GuiManager.stopTimer();
            GuiManager.setRings(0);
            MainGameLoop.gamePlayer.setCanMove(false);
            MainGameLoop.bufferTime = 60;
            
            
            new Particle(ParticleResources.textureBlackFade, MainGameLoop.gameCamera.getFadePosition(), new Vector3f(0,0,0), 0, 60, 0, 400, 0);
            
            if (levelFilename.equals("EmeraldCoast.lvl"))
            {
            	//AudioSources.playBGM(36);
            }
            else if (levelFilename.equals("SpeedHighway.lvl"))
            {
            	//AudioSources.playBGM(37);
            }
            else if (levelFilename.equals("SandHill.lvl"))
            {
            	//AudioSources.playBGM(38);
            }
            else
            {
            	//AudioSources.getSource(14).stop();
            }
        }
        //catch (Exception e)
        {
            //e.printStackTrace();
        }
	}
	
	
	private static void processLine(int index, Scanner in, ArrayList<Entity> entities)
	{
		switch (index)
		{
			case 0: //Ring
				Ring.allocateStaticModels();
				entities.add(new Ring(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in))));
				return;
				
			case 1: //Spring
				Spring.allocateStaticModels();
				entities.add(new Spring(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), //position
								  nextFloat(in), nextFloat(in), nextFloat(in),                     //rotation
								  nextFloat(in), nextFloat(in), nextFloat(in),                     //scale, collision, power
								  MainGameLoop.gamePlayer));
				return;
				
			case 2: //Dashpad
				Dashpad.allocateStaticModels();
				entities.add(new Dashpad(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), //position
								  nextFloat(in), nextFloat(in), nextFloat(in),                      //rotation
								  nextFloat(in), nextFloat(in), nextFloat(in),                      //scale, collision, power
								  MainGameLoop.gamePlayer));
				return;
				
			case 3: //Line of Rings
				Ring.allocateStaticModels();
				Vector3f pos1 = new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in));
				Vector3f pos2 = new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in));
				int numRings = in.nextInt();
				float xDiff = pos2.getX()-pos1.getX();
				float yDiff = pos2.getY()-pos1.getY();
				float zDiff = pos2.getZ()-pos1.getZ();
				if (numRings > 1)
				{
					for(int i = 0; i < numRings; i++)
					{
						entities.add(new Ring(new Vector3f(pos1.getX() + i*(xDiff/(numRings-1)),
														   pos1.getY() + i*(yDiff/(numRings-1)),
														   pos1.getZ() + i*(zDiff/(numRings-1)))));
					}
				}
				else
				{
					entities.add(new Ring(pos1));
				}
				return;
				
			case 4: //Circle of Rings
				Ring.allocateStaticModels();
				Vector3f centerPos = new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in));
				float ringRadius = nextFloat(in);
				int ringCount = in.nextInt();
				if (ringCount > 1)
				{
					float degreeSegment = 360.0f/ringCount;
					Vector3f newPoint = new Vector3f(0, centerPos.y, 0);
					
					
					for(int i = 0; i < ringCount; i++)
					{
						newPoint.setX(centerPos.x + ringRadius*(float)Math.cos(Math.toRadians(degreeSegment*i)));
						newPoint.setZ(centerPos.z + ringRadius*(float)Math.sin(Math.toRadians(degreeSegment*i)));
						entities.add(new Ring(new Vector3f(newPoint.x, newPoint.y, newPoint.z)));
					}
				}
				else
				{
					entities.add(new Ring(centerPos));
				}
				return;
				
			case 5: //Half-Circle of Rings
				Ring.allocateStaticModels();
				Vector3f midPos = new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in));
				int ringSize = in.nextInt();
				int ringNum = in.nextInt();
				if (ringNum > 1)
				{
					float degreeSegment = 180.0f/(ringNum-1);
					Vector3f newPoint = new Vector3f(0, midPos.y, 0);
					
					for(int i = 0; i < ringNum; i++)
					{
						newPoint.setX(midPos.x + ringSize*(float)Math.cos(Math.toRadians(degreeSegment*i)));
						newPoint.setZ(midPos.z + ringSize*(float)Math.sin(Math.toRadians(degreeSegment*i)));
						entities.add(new Ring(new Vector3f(newPoint.x, newPoint.y, newPoint.z)));
					}
				}
				else
				{
					entities.add(new Ring(midPos));
				}
				return;
				
			case 6: //Sonic
				Ball.allocateStaticModels();
				Ball newPlayer = new Ball(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)));
				entities.add(newPlayer);
				MainGameLoop.gamePlayer = newPlayer;
				SkyManager.setCenterObject(newPlayer);
				newPlayer.setCameraAngles(MainGameLoop.gameCamera.getYaw(), MainGameLoop.gameCamera.getPitch());
				NetworkManager.setLocalPlayer(newPlayer);
				return;
				
			case 7: //Sky Sphere
				TexturedModel[] newSphereModels = ConvenientMethods.loadModelWithMTL("Models/"+in.next()+"/", in.next(), in.next());
				if (MainGameLoop.gameSkySphere != null && MainGameLoop.gameSkySphere.getModels() != null)
					MainGameLoop.gameLoader.deleteModel(MainGameLoop.gameSkySphere.getModels());
				MainGameLoop.gameSkySphere.setModels(newSphereModels);
				MainGameLoop.gameSkySphere.setScale(nextFloat(in));
				MainGameLoop.gameSkySphere.setVisibility(true);
				entities.add(MainGameLoop.gameSkySphere);
				return;
				
			case 8: //Teleport Zone
				Vector3f telStart = new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in));
				Vector3f telEnd = new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in));
				entities.add(new TeleportZone(telStart, telEnd, nextFloat(in), nextFloat(in), nextFloat(in)));
				return;
				
			case 9: //Goal Sign
				GoalSign.allocateStaticModels();
				entities.add(new GoalSign(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in))));
				return;
				
			case 10: //Palm Tree
				Palmtree.allocateStaticModels();
				entities.add(new Palmtree(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), 
										  nextFloat(in), nextFloat(in), nextFloat(in), nextFloat(in)));
				return;
				
			case 11: //Rhino Tank
				RhinoTank.allocateStaticModels();
				entities.add(new RhinoTank(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in))));
				return;
				
			case 12: //Emerald Coast Sinking Platform
				BeachFloatingPlatform.allocateStaticModels();
				BeachFloatingPlatform beachPlatform = new BeachFloatingPlatform(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)));
				entities.add(beachPlatform);
				CollisionChecker.addCollideModel(beachPlatform.collideTransformed);
				return;
				
			case 13: //Emerald Coast Double-Waterfall
				BeachWaterfall.allocateStaticModels();
				MainGameLoop.gameTransparentEntities.add(new BeachWaterfall(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), nextFloat(in), nextFloat(in)));
				return;
				
			case 14: //Emerald Coast Flat Water
				BeachFlatWater.allocateStaticModels();
				MainGameLoop.gameTransparentEntities.add(new BeachFlatWater());
				return;
				
			case 15: //Motobug
				Motobug.allocateStaticModels();
				entities.add(new Motobug(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in))));
				return;
				
			case 16: //Green Hill Rock
				GHillRock.allocateStaticModels();
				entities.add(new GHillRock(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), nextFloat(in), 1));
				return;
				
			case 17: //Green Hill Tree
				GHillTree.allocateStaticModels();
				entities.add(new GHillTree(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), nextFloat(in), nextFloat(in)));
				return;
				
			case 18: //Green Hill Sunflower
				GHillSunflower.allocateStaticModels();
				entities.add(new GHillSunflower(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), nextFloat(in), 1));
				return;
				
			case 19: //Green Hill Flower
				GHillFlower.allocateStaticModels();
				entities.add(new GHillFlower(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), nextFloat(in), 1));
				return;
				
			case 20: //Green Hill Totem
				GHillTotem.allocateStaticModels();
				entities.add(new GHillTotem(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), nextFloat(in), 1));
				return;
				
			case 21: //Green Hill Totem Wings
				GHillTotemWings.allocateStaticModels();
				entities.add(new GHillTotemWings(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), nextFloat(in), 1));
				return;
				
			case 22: //Green Hill Grass
				GHillGrass.allocateStaticModels();
				entities.add(new GHillGrass(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), nextFloat(in), 1));
				return;
				
			case 23: //Green Hill Falling Platform
				GHillFallingPlatform.allocateStaticModels();
				entities.add(new GHillFallingPlatform(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), nextFloat(in), 1));
				return;
				
			case 24: //Green Hill Floating Platform
				GHillFloatingPlatform.allocateStaticModels();
				entities.add(new GHillFloatingPlatform(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), nextFloat(in), 1));
				return;
				
			case 25: //Goal Sign Trigger
				entities.add(new GoalTrigger(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), nextFloat(in)));
				return;
				
			case 26: //Umbrella
				BeachUmbrella.allocateStaticModels();
				entities.add(new BeachUmbrella(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), 
											   nextFloat(in), nextFloat(in), nextFloat(in), nextFloat(in)));
				return;
				
			case 27: //Item Capsule
				ItemCapsule.allocateStaticModels();
				entities.add(new ItemCapsule(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), 
							                 nextFloat(in), nextFloat(in), nextFloat(in), in.nextInt()));
				return;
				
			case 28: //Spinner
				Spinner.allocateStaticModels();
				entities.add(new Spinner(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), 
							                 nextFloat(in)));
				return;
				
			case 29: //Speed Ramp
				SpeedRamp.allocateStaticModels();
				entities.add(new SpeedRamp(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)), 
							                 nextFloat(in), nextFloat(in), nextFloat(in)));
				return;
				
			case 30: //Koopa Troopa Beach Waterfall
				KoopaBeachWaterfall.allocateStaticModels();
				MainGameLoop.gameTransparentEntities.add(new KoopaBeachWaterfall());
				return;
				
			case 31: //Peach Castle Galaxy Water
				PeachCastleWaterfall.allocateStaticModels();
				MainGameLoop.gameTransparentEntities.add(new PeachCastleWaterfall());
				return;
				
			case 32: //NPC
				NPC.allocateStaticModels();
				entities.add(new NPC(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)),
														 nextFloat(in), in.nextLine()));
				return;
				
			case 33: //BOB turning plank
				BOBWoodPlank.allocateStaticModels();
				entities.add(new BOBWoodPlank(new Vector3f(nextFloat(in), nextFloat(in), nextFloat(in)),
														 nextFloat(in)));
				return;
				
			default:
				break;
		}
	}
	
	private static void freeAllStaticModels()
	{
		Ring.freeStaticModels();
		Spring.freeStaticModels();
		Dashpad.freeStaticModels();
		GoalSign.freeStaticModels();
		Ball.freeStaticModels();
		Palmtree.freeStaticModels();
		RhinoTank.freeStaticModels();
		BeachFloatingPlatform.freeStaticModels();
		BeachWaterfall.freeStaticModels();
		BeachFlatWater.freeStaticModels();
		Motobug.freeStaticModels();
		GHillRock.freeStaticModels();
		GHillTree.freeStaticModels();
		GHillSunflower.freeStaticModels();
		GHillFlower.freeStaticModels();
		GHillTotem.freeStaticModels();
		GHillTotemWings.freeStaticModels();
		GHillGrass.freeStaticModels();
		GHillFallingPlatform.freeStaticModels();
		GHillFloatingPlatform.freeStaticModels();
		BeachUmbrella.freeStaticModels();
		ItemCapsule.freeStaticModels();
		Spinner.freeStaticModels();
		SpeedRamp.freeStaticModels();
		KoopaBeachWaterfall.freeStaticModels();
		NPC.freeStaticModels();
		BOBWoodPlank.freeStaticModels();
		ManiaSonicModels.freeStaticModels();
	}
	
	private static float nextFloat(Scanner input)
	{
		String next = input.next();
    	//System.out.println("Going to try to parse '"+next+"' as a float");
    	float val = 0;
    	try
    	{
    		val = Float.parseFloat(next);
    		//System.out.println("val = '"+val+"'");
    	}
    	catch (NumberFormatException e)
    	{
    		System.out.println("Couldn't parse '"+next+"' as a float");
    		e.printStackTrace();
    	}
    	
    	return val;
	}
}
