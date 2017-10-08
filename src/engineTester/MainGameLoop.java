package engineTester;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.util.Random;

import models.RawModel;
import models.TexturedModel;
import network.NetworkManager;
import network.OnlinePlayer;
import objConverter.OBJFileLoader;
import particles.Particle;
import particles.ParticleMaster;
import particles.ParticleResources;
import particles.ParticleTexture;
import postProcessing.Fbo;
import postProcessing.PostProcessing;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.newdawn.slick.openal.SoundStore;

import animation.AnimationResources;
import animation.Body;
import animation.Limb;
import audio.AudioMaster;
import audio.AudioSources;
import audio.Source;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.SkyManager;
import textures.ModelTexture;
//import toolbox.AudioRes;
import toolbox.CoinManager;
import toolbox.ConvenientMethods;
import toolbox.Joystick;
import toolbox.LevelLoader;
import toolbox.ParticleManager;
import toolbox.RotationMatrixUnnormalizedDirVector;
import water.WaterFrameBuffers;
import water.WaterRenderer;
import water.WaterShader;
import water.WaterTile;
import collision.CollisionChecker;
import collision.CollisionModel;
import entities.Ball;
import entities.BlueCoin;
import entities.BobOmb;
import entities.Camera;
import entities.ChainChomp;
import entities.Cheese;
import entities.Ring;
import entities.CollideableObject;
import entities.Dashpad;
import entities.DrakoPointsList;
import entities.Entity;
import entities.Goomba;
import entities.Light;
import entities.LoadingZone;
import entities.MovingPlatform;
import entities.MusicPlayer;
import entities.MyParticle;
import entities.PauseScreen;
import entities.Player;
import entities.Pole;
import entities.RedCoin;
import entities.RedCoinStar;
import entities.RotatingPlatform;
import entities.ShadowBlob;
import entities.SkySphere;
import entities.Star;
import entities.WoodPost;
import entities.BEACH.Thwomp;
import entities.BEACH.BeachWaterfall;
import entities.BEACH.WFChip;
import entities.BEACH.WFFaceBlockSmall;
import entities.BEACH.WFFaceBlockWide;
import entities.BEACH.WFFallingBlock;
import entities.BEACH.WFGrassPlatform;
import entities.BEACH.WFMovingFloor;
import entities.BEACH.WFTowerBlock;
import entities.BEACH.BeachFloatingPlatform;
import entities.BEACH.WFTravelPlatform;
import entities.BEACH.BOBWoodPlank;
import entities.BEACH.WFWoodPlatform;
import entities.BEACH.Whomp;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import entities.Spring;
import guis.GuiManager;
import guis.GuiRenderer;
import guis.GuiTexture;

public class MainGameLoop 
{
	public static ArrayList<Entity> gameEntities;
	public static ArrayList<Entity> gameEntitiesToAdd;
	public static ArrayList<Entity> gameEntitiesToDelete;
	
	public static LinkedList<Entity> gameTransparentEntities;
	
	public static ArrayList<Entity> levelHUBEntities;
	public static ArrayList<Entity> levelBOBEntities;
	public static ArrayList<Entity> levelWFEntities;
	public static ArrayList<Entity> levelCCMEntities;
	public static ArrayList<Entity> levelCCMSlideEntities;
	public static ArrayList<Entity> levelJRBEntities;
	public static ArrayList<Entity> levelPSSEntities;
	public static ArrayList<Entity> levelLLLEntities;
	public static ArrayList<Entity> liveScanPoints;
	public static Loader gameLoader;
	public static boolean[] starList;
	public static boolean freeMouse;
	public static List<Light> gameLights;
	public static List<GuiTexture> gameGuis = new ArrayList<GuiTexture>();
	public static int gameClock;
	public static int gameState;
	public static Camera gameCamera;
	public static Ball gamePlayer;
	public static float snowRadius;
	public static int snowDensity;
	public static int levelID;
	public static boolean freeCamera;
	public static Entity gameStage;
	public static SkySphere gameSkySphere;
	public static String levelName = "";
	public static int bufferTime = -1;
	public static boolean shouldRestartLevel = false;
	
	public static boolean unlockedSonicDoll = false;
	public static boolean unlockedMechaSonic = false;
	public static boolean unlockedDage4 = false;
	
	public static Random rnd = new Random();
	
	public static final float ENTITY_RENDER_DIST = 1500;
	
	public static boolean multiplayerEnable = false;
	public static String multiplayerNickname = "";
	public static String multiplayerServerIP = "localhost";
	public static int multiplayerDelay = 100;
	
	public final static class gameStates
	{
		public static final int paused = 0,
				                running = 1,
				                exiting = 2,
				                cutscene = 3,
				                title = 4;
	}
	
	public final static class levelIDs
	{
		public static final int HUB = 0,
				                BOB = 1,
				                WF = 2,
				                JRB = 3,
				                CCM = 4,
				                CCMSlide = 5,
				                PSS = 6, 
				                LLL = 7;
	}
	
	public static void main(String[] args) 
	{
		long timeStamp = System.currentTimeMillis();
		int frameCount = 0;
		gameEntities = new ArrayList<Entity>();
		gameEntitiesToAdd = new ArrayList<Entity>();
		gameEntitiesToDelete = new ArrayList<Entity>();
		gameTransparentEntities = new LinkedList<Entity>();
		levelHUBEntities = new ArrayList<Entity>();
		levelBOBEntities = new ArrayList<Entity>();
		levelWFEntities = new ArrayList<Entity>();
		levelCCMEntities = new ArrayList<Entity>();
		levelCCMSlideEntities = new ArrayList<Entity>();
		levelJRBEntities = new ArrayList<Entity>();
		levelPSSEntities = new ArrayList<Entity>();
		levelLLLEntities = new ArrayList<Entity>();
		liveScanPoints = new ArrayList<Entity>();
		starList = new boolean[120];
		gameClock = 0;
		gameState = 1;
		snowRadius = 100;
		snowDensity = 10;//10
		levelID = 0;
		freeCamera = true;
		
		Mouse.setGrabbed(false);
		freeMouse = true;
		
		loadMultiplayerFile();
		setupControllers();
		Joystick.readSetupFile();
		loadSaveFile();
		
		DisplayManager.createDisplay();
		
		NetworkManager.init();
		
		Loader loader = new Loader();
		gameLoader = loader;
		AudioMaster.init();
		AudioSources.loadSoundBuffers();
		AudioSources.createSources();
		
		TextMaster.init();
		PauseScreen.init();
		GuiManager.init();
		ParticleResources.loadParticles();
		
		//AudioRes.loadGameSounds();
		//AudioSources.createSources();
		//AudioSources.loadSoundBuffers();
		
		//AudioMaster.setListenerData(0, 0, 0);
		//AL10.alDistanceModel(AL10.AL_INVERSE_DISTANCE_CLAMPED);
		
		MusicPlayer musicPlayer = new MusicPlayer(null, new Vector3f(0,0,0));
		LoadingZone.musicPlayer = musicPlayer;
		//ParticleManager.loadParticleModels(loader);
		AnimationResources.createAnimations();
		
		
		//TexturedModel[] HUBTexturedModels = ConvenientMethods.loadModel("Models/Terrain/", "Sphere", loader);
		//CollisionModel HUBCollideModel = OBJFileLoader.loadCollisionOBJ("Models/Terrain/", "Sphere");
		//TexturedModel[] HUBTexturedModels = ConvenientMethods.loadModel("Models/EmeraldCoast/", "EAll3", loader);
		//CollisionModel HUBCollideModel = OBJFileLoader.loadCollisionOBJ("Models/EmeraldCoast/", "EAll3");
		
		//OBJFileLoader.debug = false;
		//TexturedModel[] HUBWaterTexturedModels = ConvenientMethods.loadModel("Models/EmeraldCoast/", "WaterfallDouble");
		//OBJFileLoader.debug = false;
		
	
		
		//TexturedModel[] pipeTexturedModels = ConvenientMethods.loadModel("Models/Pipe/", "Pipe", loader);
		//TexturedModel[] pipeTexturedModels = ConvenientMethods.loadModel("Models/Unagi/", "Unagi", loader);
		TexturedModel[] sunTexturedModels = ConvenientMethods.loadModel("Models/Sky/", "SunFlip");
		
		TexturedModel[] modelBall = ConvenientMethods.loadModel("Models/Ring/", "Ring");
		Entity myBall = new Entity(modelBall, new Vector3f(0,40,0));
		myBall.setVisibility(true);
		//TexturedModel[] eggTexturedModels = ConvenientMethods.loadModel("Models/EmuEgg/", "EmuEgg", loader);
		//TexturedModel[] measureCubeTexturedModels = ConvenientMethods.loadModel("Models/Box/", "MeasureCube", loader);
		
		
		//Entity entityKillplane = new Entity(killplaneTexturedModels, new Vector3f(0, 0, 0), 0, 0, 0, 1);
		
		gameStage = new Entity(new Vector3f(0, 0, 0), 0, 0, 0, (1/1.0f));
        //Entity entityStageWater1 = new Entity(HUBWaterTexturedModels, new Vector3f(-366, 1200, 4630), 0, -100, 0, 60);
        //Entity entityStageWater2 = new Entity(HUBWaterTexturedModels, new Vector3f(78, 1020, 4893), 0, -140, 0, 60);
        //Entity entityStageWater3 = new Entity(HUBWaterTexturedModels, new Vector3f(198, 980, 5054), 0, -160, 0, 60);
        //Entity entityWFWater = new Entity(WFWaterTexturedModels, new Vector3f(0, 0, 0), 0, 0, 0, 1);
        //HUBWaterTexturedModels[0].getTexture().setMoves(true);
        //WFWaterTexturedModels[0].getTexture().setMoves(true);
        //HUBWaterTexturedModels[0].getTexture().setHasTransparency(true);
        //HUBWaterTexturedModels[1].getTexture().setMoves(true);
        //HUBWaterTexturedModels[1].getTexture().setHasTransparency(true);
        
        
        //TexturedModel[] goalTexturedModels = ConvenientMethods.loadModel("Models/GoalSign/", "GoalSign");
        //Entity entityGoalSign = new Entity(goalTexturedModels, new Vector3f(7723, 0, 4526), 0, 0, 0, 1.5f);
        
        gameSkySphere = new SkySphere(8);
        
        Entity entitySun = new Entity(sunTexturedModels, new Vector3f(16, 24, 0), 0, 0, 0, 6);
        //sunTexturedModels[0].getTexture().setUseFakeLighting(true);
        
        //Entity grass = new Entity(grassTexturedModels, new Vector3f())
        
        //Entity PipeBOB = new Entity(pipeTexturedModels, new Vector3f(-429, 160, 451), 0, 0, 0, 1); //y = 108
        //Entity PipeCCM = new Entity(pipeTexturedModels, new Vector3f(-192+6,508+8,122+6), 0, 0, 0, 1);
        //Entity PipeWF = new Entity(pipeTexturedModels, new Vector3f(212, 13, 370), 0, 0, 0, 1);
        //Entity PipeJRB = new Entity(pipeTexturedModels, new Vector3f(-119+6,-3+8,-44+6), 0, 0, 0, 1);
		
		Light lightSun = new Light(new Vector3f(0,100000,-500000), new Vector3f(1.0f,0.75f,0.375f));
		Light lightMoon = new Light(new Vector3f(0,100000,-500000), new Vector3f(1.0f,0.75f,0.375f));
		//Light lightStar0 = new Light(new Vector3f(0,-1000,0), new Vector3f(0.8f, 0.9f, 1)); //For testign out the mario galaxy type lighting effect
		Light lightStar0 = new Light(new Vector3f(0,-1000,0), new Vector3f(2,2,0), new Vector3f(1, 0.001f, 0.001f));
		Light lightStar1 = new Light(new Vector3f(0,-1000,0), new Vector3f(2,2,0), new Vector3f(1, 0.001f, 0.001f));
		Light lightStar2 = new Light(new Vector3f(0,-1000,0), new Vector3f(2,2,0), new Vector3f(1, 0.001f, 0.001f));
		Light lightStar3 = new Light(new Vector3f(0,-1000,0), new Vector3f(2,2,0), new Vector3f(1, 0.001f, 0.001f));//original 0.01, 0.002
		
		gameLights = new ArrayList<Light>();
		gameLights.add(lightSun);
		gameLights.add(lightMoon);
		gameLights.add(lightStar0);
		gameLights.add(lightStar1);
		gameLights.add(lightStar2);
		gameLights.add(lightStar3);
		
		Ball.allocateStaticModels();
		gamePlayer = new Ball(new Vector3f(0, 750, 0));

		CollisionChecker.initChecker();

		Camera camera = new Camera();
		gameCamera = camera;

		SkyManager.initSkyManager(lightSun, lightMoon, entitySun, gameSkySphere, gamePlayer);
		SkyManager.setTimeOfDay(155.5f); //63 //155.5 for title screen
		camera.setYaw(180);


		//AudioRes.setCamera(camera);
		MyParticle.setCamera(camera);
		Pole.setCamera(camera);
		//Pole.setPlayer(player);
	
		//levelHUBEntities.add(new Cheese(new Vector3f(0, 10, 0)));
		Ball.cam = camera;
		//Ball.player = player;
		
		
		MasterRenderer renderer = new MasterRenderer(camera);
		
		
		WaterFrameBuffers fbos = new WaterFrameBuffers();
		
		
		
		WaterShader waterShader = new WaterShader();
		WaterRenderer waterRenderer = new WaterRenderer(loader, waterShader, renderer.getProjectionMatrix(), renderer.getShadowRenderer(), fbos);
		List<WaterTile> waters = new ArrayList<WaterTile>();
		for(int r = -18; r < 18; r++) //-16 , 16
		{
			for(int c = -18; c < 18; c++) //-16  16
			{
				waters.add(new WaterTile(r*1000, c*1000, 0f));
			}
		}
		//waters.add(new WaterTile(912, 6083, 589));
		
		Fbo multisampleFbo = new Fbo(Display.getWidth(), Display.getHeight());
		Fbo outputFbo = new Fbo(Display.getWidth(), Display.getHeight(), Fbo.DEPTH_TEXTURE);
		Fbo outputFbo2 = new Fbo(Display.getWidth(), Display.getHeight(), Fbo.DEPTH_TEXTURE);
		PostProcessing.init(loader);
		
		//ParticleTexture particleTexture = new ParticleTexture(loader.loadTexture("Models\\Particle\\DustAtlas"), 4);
		ParticleMaster.init(loader, renderer.getProjectionMatrix());
		
		//GuiTexture refraction = new GuiTexture(fbos.getRefractionTexture(), new Vector2f(0.5f, 0.5f), new Vector2f(0.25f, 0.25f));
		//GuiTexture reflection = new GuiTexture(fbos.getReflectionTexture(), new Vector2f(-0.5f, 0.5f), new Vector2f(0.25f, 0.25f));
		//GuiTexture shadowMap = new GuiTexture(renderer.getShadowMapTexture2(), new Vector2f(-.05f, 0.5f), new Vector2f(0.25f, 0.25f));
		//guis.add(refraction);
		//guis.add(reflection);
		//guis.add(shadowMap);
		
		GuiRenderer guiRenderer = new GuiRenderer(loader);
		
		
		//Texture refractionTexture = fbos.getRefractionTexture();
		
		//skySphere.setVisibility(true);
		//gameEntities = levelHUBEntities;
		
		
		//LevelLoader.loadLevel("SpeedHighway.lvl");
		
		//FontType font = new FontType(loader.loadTexture("Fonts/candara"), "Fonts/candara");
		
		//GUIText text = new GUIText("This is a test text!", 3, font, new Vector2f(0, 0.4f), 1f, true);
		
		//text.delete();
		//font.delete();
		
		PauseScreen.pause();
		
		OnlinePlayer.initModels();
		
		if (multiplayerEnable)
		{
			NetworkManager.attemptConnection();
		}

		//gameLoader.dispErrors();
		while (gameState != gameStates.exiting)
		{
			//xPos += 0.01f;
			//AudioSources.getSource(0).setPosition(0, xPos, 0);
			//dashpad1.setY(xPos);
			
			//FloatBuffer listenerPos = BufferUtils.createFloatBuffer(3).put(
		                //new float[]{xPos, 0, 0});
	        //listenerPos.flip();
	        //AL10.alListener(AL10.AL_POSITION, listenerPos);
	        
	        
			if(Joystick.joystickExists())
			{
				Joystick.poll();
			}
			
			if (bufferTime >= 0)
			{
				bufferTime-=1;
			}
			if (bufferTime == 0)
			{
				GuiManager.startTimer();
				gamePlayer.setCanMove(true);
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_9))
			{
				//renderer.changeFOV(-0.5f);
				//waterRenderer.updateProjectionMatrix(renderer.getProjectionMatrix());
				SkyManager.increaseTimeOfDay(0.5f);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_0))
			{
				//renderer.changeFOV(0.5f);
				//waterRenderer.updateProjectionMatrix(renderer.getProjectionMatrix());
				SkyManager.increaseTimeOfDay(-0.5f);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_1))
			{
				renderer.changeFOV(-0.5f);
				waterRenderer.updateProjectionMatrix(renderer.getProjectionMatrix());
				ParticleMaster.updateProjectionMatrix(renderer.getProjectionMatrix());
				//System.out.println(camera.getPitch());
				
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_2))
			{
				renderer.changeFOV(0.5f);
				waterRenderer.updateProjectionMatrix(renderer.getProjectionMatrix());
				ParticleMaster.updateProjectionMatrix(renderer.getProjectionMatrix());
				//System.out.println(camera.getPitch());
				
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_8))
			{
				//renderer.changeFOV(0.5f);
				//waterRenderer.updateProjectionMatrix(renderer.getProjectionMatrix());
				//new Particle(ParticleResources.textureDust, new Vector3f(player.getPosition()), new Vector3f(0, 3, 0), 0.1f, 240, 0, 6, 0);
				//MainGameLoop.gameEntitiesToAdd.add(
				//		new Ring(new Vector3f(player.getX(), player.getY()+10, player.getZ()), 
				//				1.5f*(float)Math.random()-0.75f, 
				//				1+1.5f*(float)Math.random(), 
				//				1.5f*(float)Math.random()-0.75f));
				
				/*
				float spoutSpd = 3f;
				float anglH = (float)(Math.PI*2*Math.random());
				float anglV = (float)(Math.toRadians(rnd.nextGaussian()*42+90));
				
				float yspd = (float)(spoutSpd*Math.sin(anglV));
				float hpt = (float)(spoutSpd*Math.cos(anglV));
				
				float xspd = (float)(hpt*Math.cos(anglH));
				float zspd = (float)(hpt*Math.sin(anglH));
				
				MainGameLoop.gameEntitiesToAdd.add(
						new Ring(new Vector3f(gamePlayer.getX(), gamePlayer.getY()+10, gamePlayer.getZ()), 
								xspd, yspd, zspd));
								*/
			}
			
			if (Joystick.joystickExists() && Joystick.getButtonJustPressed(Joystick.BUTTON_RB))
			{
				gameLoader.dispErrors();
				System.out.println("Time of day: "+SkyManager.getTimeOfDay());
				System.out.println(gamePlayer.getPosition());
				System.out.println("player rot = "+gamePlayer.getRotY());
				System.out.println("cam yaw: "+gameCamera.getYaw()+"   cam pitch: "+gameCamera.getPitch());
				System.out.println();
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_4))
			{
				//LevelLoader.loadTitle();
				//Joystick.
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_5))
			{
				//gameEntities.retainAll(null);
				//loader.deleteModel(gameStage.getModels()); //clear huge level data from memory
				//LevelLoader.loadLevel("EmeraldCoast.lvl");
				

			}
			if (Keyboard.isKeyDown(Keyboard.KEY_6))
			{

				//loader.deleteModel(gameStage.getModels()); //clear huge level data from memory
				//LevelLoader.loadLevel("SpeedHighway.lvl");
				
				//gameStage.setModels(newModel);
				//gameEntities.add(skySphere);
				//gameEntities.add(entitySun);
				//gameEntities.add(gameStage);
				//gameEntities.add(musicPlayer);
				//gameEntities.add(gamePlayer);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_7))
			{
				//gameEntities.retainAll(null);

				//loader.deleteModel(gameStage.getModels()); //clear huge level data from memory
				//LevelLoader.loadLevel("GreenHillZone.lvl");

			}
			
			if (shouldRestartLevel)
			{
				shouldRestartLevel = false;
				LevelLoader.loadLevel(levelName, false);
			}
			
			
			//float newFOV = 50;
			//float playerSpeed = (float)Math.sqrt(Math.pow(player.getxVel(), 2)+Math.pow(player.getzVel(), 2));
			//if(playerSpeed > 1)
			{
				//newFOV = 50+playerSpeed*5;
			}
			//renderer.setFOV(newFOV);
			//waterRenderer.updateProjectionMatrix(renderer.getProjectionMatrix());
			
			//gameSkySphere.setRotY(SkyManager.getTimeOfDay());
			//gameSkySphere.setX(gamePlayer.getX());
			//gameSkySphere.setZ(gamePlayer.getZ());
			
			//System.out.println(gameEntities.size());
			
			gameEntities.addAll(gameEntities.size(), gameEntitiesToAdd);
			gameEntitiesToAdd.clear();
			Iterator<Entity> del = gameEntitiesToDelete.iterator();
			while(del.hasNext())
			{
				gameEntities.remove(del.next());
			}
			gameEntitiesToDelete.clear();
			
			
			PauseScreen.step();
			SoundStore.get().poll(0);
			//Vector3f listenerPos = camera.getPosition();
			//AudioMaster.setListenerData(listenerPos);
			//AudioMaster.setListenerOrientation(new Vector3f((float)Math.cos(Math.toRadians(camera.getYaw())),
			//												0, 
			//												(float)Math.sin(Math.toRadians(camera.getYaw()))));
			
			
			switch (gameState)
			{
				case gameStates.running:
					//skyManager.increaseTimeOfDay(0.005f);
					camera.step();
					ParticleMaster.update(camera);
					GuiManager.increaseTimer();
					Iterator<Entity> g = gameEntities.iterator();
					while(g.hasNext())
					{
						g.next().step();
					}
					for (Entity e: MainGameLoop.gameTransparentEntities)
					{
						e.step();
					}
					for (OnlinePlayer player: NetworkManager.getPlayersList())
					{
						if (player != null)
						{
							player.animate();
						}
					}
					gameClock++;
					break;
					
				case gameStates.cutscene:
					camera.step();
					ParticleMaster.update(camera);
					break;
					
				case gameStates.title:
					
					break;
					
				case gameStates.paused:
					break;
			}
			
			SkyManager.calculateValues();
			
			Light lightToCast = gameLights.get(0);
			if(gameLights.get(0).getPosition().y < 0)
			{
				lightToCast = gameLights.get(1);//switch to the moon casting the shadow
			}
			gameEntities.remove(gameSkySphere);
			gameEntities.remove(entitySun);
			//gameEntities.remove(myBall);
			renderer.renderShadowMaps(gameEntities, lightToCast);
			gameEntities.add(gameSkySphere);
			gameEntities.add(entitySun);
			//gameEntities.add(myBall);
			//for (OnlinePlayer player: NetworkManager.getPlayersList())
			{
				//if (player != null)
				{
					//myBall.setPosition(player.pos);
				}
			}
			//set up for water renderings
			
			GL11.glEnable(GL30.GL_CLIP_DISTANCE0);
			boolean aboveWater = false;
			if(camera.getPosition().getY() > waters.get(0).getHeight())
			{
				aboveWater = true;
			}
			
			Iterator<Entity> g = gameEntities.iterator();
			while (g.hasNext())
			{
				renderer.processEntity(g.next());
			}
			for (Entity e: MainGameLoop.gameTransparentEntities)
			{
				renderer.processTransparentEntity(e);
			}
			for (OnlinePlayer player: NetworkManager.getPlayersList())
			{
				if (player != null)
				{
					for (Entity e: player.getEntities())
					{
						renderer.processEntity(e);
					}
				}
			}
			
			//good
			
			
			//reflection render
			fbos.bindReflectionFrameBuffer();
			float distance = 2*(camera.getPosition().y-waters.get(0).getHeight());
			//good
			if (aboveWater)
			{
				camera.getPosition().y -= distance;
				camera.invertPitch();
				//good
				renderer.render(gameLights, camera, new Vector4f(0, 1, 0, -waters.get(0).getHeight()+0.3f)); //0.3f normal, 3f to work better for vertical walls
				//bad
				renderer.renderTransparent(gameLights, camera, new Vector4f(0, 1, 0, -waters.get(0).getHeight()+0.3f));
				//bad
				ParticleMaster.renderParticles(camera, SkyManager.getOverallBrightness(), 1);
				camera.getPosition().y += distance;
				camera.invertPitch();
				//bad
			}
			else
			{
				camera.getPosition().y -= distance;
				camera.invertPitch();
				renderer.render(gameLights, camera, new Vector4f(0, -1, 0, -waters.get(0).getHeight()+0.3f));
				renderer.renderTransparent(gameLights, camera, new Vector4f(0, -1, 0, -waters.get(0).getHeight()+0.3f));
				ParticleMaster.renderParticles(camera, SkyManager.getOverallBrightness(), -1);
				camera.getPosition().y += distance;
				camera.invertPitch();
			}
			
			fbos.unbindCurrentFrameBuffer();
			
			//bad
			
			//refraction render
			Iterator<Entity> g2 = gameEntities.iterator();
			while (g2.hasNext())
			{
				renderer.processEntity(g2.next());
			}
			for (Entity e: MainGameLoop.gameTransparentEntities)
			{
				renderer.processTransparentEntity(e);
			}
			for (OnlinePlayer player: NetworkManager.getPlayersList())
			{
				if (player != null)
				{
					for (Entity e: player.getEntities())
					{
						renderer.processEntity(e);
					}
				}
			}
			
			fbos.bindRefractionFrameBuffer();
			
			if (aboveWater)
			{
				renderer.render(gameLights, camera, new Vector4f(0, -1, 0, waters.get(0).getHeight()+0.3f));
				renderer.renderTransparent(gameLights, camera, new Vector4f(0, -1, 0, waters.get(0).getHeight()+0.3f));
				ParticleMaster.renderParticles(camera, SkyManager.getOverallBrightness(), -1);
			}
			else
			{
				renderer.render(gameLights, camera, new Vector4f(0, 1, 0, waters.get(0).getHeight()+0.3f));
				renderer.renderTransparent(gameLights, camera, new Vector4f(0, 1, 0, waters.get(0).getHeight()+0.3f));
				ParticleMaster.renderParticles(camera, SkyManager.getOverallBrightness(), 1);
			}
			
			fbos.unbindCurrentFrameBuffer();
			GL11.glDisable(GL30.GL_CLIP_DISTANCE0);
			
			//bad
			
			//binding post processing fbo
			multisampleFbo.bindFrameBuffer();
			
			//normal render
			Iterator<Entity> g3 = gameEntities.iterator();
			while (g3.hasNext())
			{
				renderer.processEntity(g3.next());
			}
			for (Entity e: MainGameLoop.gameTransparentEntities)
			{
				renderer.processTransparentEntity(e);
			}
			for (OnlinePlayer player: NetworkManager.getPlayersList())
			{
				//System.out.println("checking player");
				if (player != null)
				{
					//System.out.println("good player");
					for (Entity e: player.getEntities())
					{
						//System.out.println(e.getPosition());
						//gamePlayer.setPosition(e.getPosition());
						//myBall.setPosition(e.getPosition());
						//myBall.setPosition(player.pos);
						renderer.processEntity(e);
						//renderer.processEntity(myBall);
					}
				}
			}
			//normal render of additional points
			//Iterator<Entity> g4 = liveScanPoints.iterator();
			//while(g4.hasNext())
			//{
				//renderer.processEntity(g4.next());
			//}
			renderer.render(gameLights, camera, new Vector4f(0, 1, 0, 1000));
			//renderer.renderTransparent(gameLights, camera, new Vector4f(0, 1, 0, 1000));
			
			//water render
			//GL11.glDepthMask(false); //do this if you want to have particles render ignoring the water
			//ParticleMaster.renderParticles(camera, SkyManager.getOverallBrightness(), 0);
			waterRenderer.render(waters, camera, lightToCast);
			renderer.renderTransparent(gameLights, camera, new Vector4f(0, 1, 0, 1000));
			ParticleMaster.renderParticles(camera, SkyManager.getOverallBrightness(), 0);
			//GL11.glDepthMask(true);
			
			
			//bad
			
			multisampleFbo.unbindFrameBuffer();
			multisampleFbo.resolveToFbo(GL30.GL_COLOR_ATTACHMENT0, outputFbo);
			multisampleFbo.resolveToFbo(GL30.GL_COLOR_ATTACHMENT1, outputFbo2);
			PostProcessing.doPostProcessing(outputFbo.getColourTexture(), outputFbo2.getColourTexture());
			
			//gui render
			GuiManager.refresh();
			guiRenderer.render(gameGuis);
			TextMaster.render();
			
			DisplayManager.updateDisplay();
			
			AudioMaster.updateListenerData(camera.getPosition(), camera.calcVelocity(), camera.getYaw(), camera.getPitch());
			
			frameCount+=1;
			//gameClock++;
			//GuiManager.increaseTimer();
			if(System.currentTimeMillis() - timeStamp >= 1000)
			{
				//System.out.println(frameCount);
				frameCount = 0;
				timeStamp = System.currentTimeMillis();
			}
			
			if(Display.isCloseRequested() || (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)))
			{
				gameState = gameStates.exiting;
			}
		}
		

		NetworkManager.disconnect();
		Mouse.setGrabbed(false);
		AudioMaster.cleanUp();
		//AL.destroy();
		guiRenderer.cleanUp();
		ParticleMaster.cleanUp();
		fbos.cleanUp();
		multisampleFbo.cleanUp();
		outputFbo.cleanUp();
		outputFbo2.cleanUp();
		PostProcessing.cleanUp();
		waterShader.cleanUp();
		renderer.cleanUp();
		loader.cleanUp();
		TextMaster.cleanUp();
		DisplayManager.closeDisplay();
		
		System.exit(0);
	}
	
	private static void setupControllers()
	{
		Controller joystick = null;
		try
		{
			Controllers.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
		Controllers.poll();
		int maxNum = Controllers.getControllerCount();
		
		System.out.println("Controllers: ");
		String[] joysticks = new String[maxNum];
		for (int i = 0; i < maxNum; i++)
		{
			if (Controllers.getController(i).getAxisCount() >= 4 &&
				Controllers.getController(i).getButtonCount() >= 7)
			{
				joysticks[i] = ""+i+": "+Controllers.getController(i).getName();
				System.out.println(Controllers.getController(i).getName());
			}
			else
			{
				joysticks[i] = "";
			}
		}
		
		String joyToUse = null;
		//while (joyToUse == null)
		{
			joyToUse = (String) JOptionPane.showInputDialog(null, 
		        "Select controller:",
		        "Controller select",
		        JOptionPane.QUESTION_MESSAGE, 
		        null,
		        joysticks,
		        joysticks[0]);
			
			if (joyToUse != null && joyToUse.equals(""))
			{
				joyToUse = null;
			}
		}
		
		if (joyToUse != null)
		{
			for (int i = 0; i < maxNum; i++)
			{
				if (joyToUse.equals(""+i+": "+Controllers.getController(i).getName()))
				{
					joystick = Controllers.getController(i);
				}
			}
			Joystick.setJoystick(joystick);
		}
	}
	
	private static void loadSaveFile()
	{
		InputStreamReader isr = null;
    	try
        {
    		String fileName = "Data/Save.txt";
            FileInputStream inStream = new FileInputStream(new File(fileName));
            isr = new InputStreamReader(inStream);
        }
        catch (NullPointerException e)
        {
        	System.out.println("Couldnt load input stream: 'Data/Save.txt'");
            return;
        } 
    	catch (FileNotFoundException e)
    	{
    		System.out.println("Couldnt load input stream: 'Data/Save.txt'");
            return;
		}
    	
        BufferedReader breader = new BufferedReader(isr);
		
		Scanner in = new Scanner(breader);
    	
        int sonicDoll = in.nextInt();
        if (sonicDoll == 3678453) unlockedSonicDoll = true;
        
        int mechaSonic = in.nextInt();
        if (mechaSonic == 3295697) unlockedMechaSonic = true;
        
        int dage4 = in.nextInt();
        if (dage4 == 93389897) unlockedDage4 = true;
        
        in.close();
	}
	
	public static void saveSaveFile()
	{
		BufferedWriter bw = null;
		FileWriter fw = null;

		try
		{
			fw = new FileWriter("Data/Save.txt");
			bw = new BufferedWriter(fw);
			if (unlockedSonicDoll)
				bw.write("3678453 ");
			else
				bw.write("0 ");
			
			if (unlockedMechaSonic)
				bw.write("3295697 ");
			else
				bw.write("0 ");
			
			if (unlockedDage4)
				bw.write("93389897 ");
			else
				bw.write("0 ");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	private static void loadMultiplayerFile()
	{
		BufferedReader breader = null;
		Scanner reader = null;
		try
		{
	    	InputStreamReader isr = null;
	    	try
	        {
	    		String fileName = "Data/MultiplayerSettings.ini";
	            FileInputStream inStream = new FileInputStream(new File(fileName));
	            isr = new InputStreamReader(inStream);
	        }
	        catch (NullPointerException e)
	        {
	        	System.out.println("Couldnt load input stream: 'Data/MultiplayerSettings.ini'");
	        	e.printStackTrace();
	            return;
	        }
	    	
	        breader = new BufferedReader(isr);
			
			reader = new Scanner(breader);
			
			boolean loop = true;
			while (loop)
			{
				if (!reader.hasNextLine())
				{
					loop = false;
					break;
				}
				String line = reader.nextLine();
				if (line == null)
				{
					loop = false;
					break;
				}
				String[] info = line.split(" ");
				if (info.length >= 2)
				{
					if (info[0].equals("Multiplayer_Enable"))
					{
						if (info[1].equals("on"))
						{
							multiplayerEnable = true;
						}
						else
						{
							multiplayerEnable = false;
						}
					}
					else if (info[0].equals("Nickname"))
					{
						multiplayerNickname = info[1];
					}
					else if (info[0].equals("IP"))
					{
						multiplayerServerIP = info[1];
					}
					else if (info[0].equals("Message_Delay"))
					{
						multiplayerDelay = Integer.parseInt(info[1]);
						multiplayerDelay = Math.max(1, multiplayerDelay);
						multiplayerDelay = Math.min(1000, multiplayerDelay);
					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Problem when trying to read 'Data/DisplaySettings.ini'");
			e.printStackTrace();
		}
		finally
		{
			reader.close();
		}
	}
}
