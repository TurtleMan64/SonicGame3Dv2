package entities;

import java.util.ArrayList;

import models.TexturedModel;
import renderEngine.SkyManager;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionChecker;
import collision.CollisionModel;
import engineTester.MainGameLoop;

public class LoadingZone extends Entity
{
	private TexturedModel[] modelToLoad;
	private CollisionModel collisionModel;
	private Entity modelLoadTarget;
	private ArrayList<Entity> levelEntities;
	private Player player;
	private Vector3f playerSpawnPoint;
	private int musicIndexIntro;
	private int musicIndexLoop;
	public static MusicPlayer musicPlayer;
	public static SkyManager skyManager;
	private int newLevelID;
	private boolean shouldRespawn;
	
	public LoadingZone(TexturedModel[] model, Vector3f position, float rotY, float scale, Entity levelTarget, 
			TexturedModel[] targetModel, CollisionModel collideModel, ArrayList<Entity> levelEntities, 
			Player player, Vector3f playerSpawnPoint, int musicIndexIntro, int musicIndexLoop, int newLevelID) 
	{
		super(model, position, 0, rotY, 0, scale);
		super.setVisibility(false);
		this.modelLoadTarget = levelTarget;
		this.modelToLoad = targetModel;
		this.levelEntities = levelEntities;
		this.collisionModel = collideModel;
		this.player = player;
		this.playerSpawnPoint = playerSpawnPoint;
		this.musicIndexLoop = musicIndexLoop;
		this.musicIndexIntro = musicIndexIntro;
		this.newLevelID = newLevelID;
		shouldRespawn = false;
	}
	
	@Override
	public void step()
	{
		float rad = getScale()/2;
		if(shouldRespawn)
		{
			loadZone();
			shouldRespawn = false;
		}
		else if(player.getX() > getX()-rad && player.getX() < getX() + rad &&
		        player.getZ() > getZ()-rad && player.getZ() < getZ() + rad &&
		        player.getY() > getY()     && player.getY() < getY() + rad+rad)
		{
			unloadZone();
			shouldRespawn = true;
		}
	}
	
	public void unloadZone()
	{
		for(Entity currentEntity: MainGameLoop.gameEntities)
		{
			currentEntity.despawn();
		}
	}
	
	public void loadZone()
	{
		CollisionChecker.clearCollideModels();
		CollisionChecker.addCollideModel(collisionModel);
		//modelLoadTarget.setModels(modelToLoad);
		player.setSpawnPosition(playerSpawnPoint);
		musicPlayer.musicIndexIntro = this.musicIndexIntro;
		musicPlayer.musicIndexLoop = this.musicIndexLoop;
		SkyManager.calculateValues();
		MainGameLoop.levelID = newLevelID;
		MainGameLoop.gameEntities = levelEntities;
		//skyManager.determineFogColours();
		for(Entity currentEntity: levelEntities)
		{
			currentEntity.respawn();
		}
	}
	
	public void respawn()
	{
		super.respawn();
		setVisibility(false);
	}
}
