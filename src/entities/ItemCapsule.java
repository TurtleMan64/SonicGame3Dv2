package entities;

import org.lwjgl.util.vector.Vector3f;

import audio.AudioSources;
import engineTester.MainGameLoop;
import guis.GuiManager;
import models.TexturedModel;
import particles.Particle;
import particles.ParticleResources;
import toolbox.AudioRes;
import toolbox.ConvenientMethods;

public class ItemCapsule extends Entity
{
	private static TexturedModel[] modelSolid = null;
	private static TexturedModel[] modelTrans = null;
	private static TexturedModel[] modelItem = null;
	private static Ball player;
	private Entity transGlass;
	private Entity item;
	private float colHorizontal;
	private float colHeight;
	private int itemType;
	
	public ItemCapsule(Vector3f position, float rotX, float rotY, float rotZ, int type)
	{
		super(modelSolid, position, rotX, rotY, rotZ, 1);
		player = MainGameLoop.gamePlayer;
		colHorizontal = 6.5f;
		colHeight = 15;
		this.itemType = type;
		transGlass = new Entity(modelTrans, position, rotX, rotY, rotZ, 1);
		this.item = new Entity(modelItem, position, rotX, rotY, rotZ, 1);
		MainGameLoop.gameTransparentEntities.add(this.transGlass);
		MainGameLoop.gameEntitiesToAdd.add(this.item);
	}
	
	@Override
	public void step()
	{
		if (Math.abs(getX()-MainGameLoop.gameCamera.getX()) > MainGameLoop.ENTITY_RENDER_DIST)
		{
			setVisibility(false);
			transGlass.setVisibility(false);
			item.setVisibility(false);
		}
		else
		{
			if (Math.abs(getZ()-MainGameLoop.gameCamera.getZ()) > MainGameLoop.ENTITY_RENDER_DIST)
			{
				setVisibility(false);
				transGlass.setVisibility(false);
				item.setVisibility(false);
			}
			else
			{
				setVisibility(true);
				transGlass.setVisibility(true);
				item.setVisibility(true);
				if (player.getX() > getX()-colHorizontal-player.getHitboxHorizontal() && player.getX() < getX()+colHorizontal+player.getHitboxHorizontal() &&
					player.getZ() > getZ()-colHorizontal-player.getHitboxHorizontal() && player.getZ() < getZ()+colHorizontal+player.getHitboxHorizontal() &&
					player.getY()+player.getHitboxVertical() >= getY() && player.getY()-player.getHitboxVertical() <= getY()+colHeight)
				{
					AudioSources.play(28, getPosition());
					
					MainGameLoop.gameEntitiesToDelete.add(this);
					MainGameLoop.gameEntitiesToDelete.add(item);
					MainGameLoop.gameTransparentEntities.remove(transGlass);
					
					float height = 6f;
					float spread = 10f;
					
					for (int i = 4; i != 0; i--)
					{
						new Particle(ParticleResources.textureExplosion1, 
								new Vector3f(getX()+spread*(float)(Math.random()-0.5),
								         getY()+spread*(float)(Math.random()-0.5)+height,
								         getZ()+spread*(float)(Math.random()-0.5)), new Vector3f(0, 0, 0), 
								0, 45, 0, 3*(float)Math.random()+6, 0);
					}
					
					player.rebound(getPosition());
					GuiManager.setRings(GuiManager.getRings()+5);
				}
			}
		}
		
		item.increaseRotation(0, 5, 0);
	}
	
	public static void allocateStaticModels()
	{
		if (modelSolid == null)
		{
			modelSolid = ConvenientMethods.loadModel("Models/ItemCapsule/", "CapsuleSolid");
		}
		if (modelTrans == null)
		{
			modelTrans = ConvenientMethods.loadModel("Models/ItemCapsule/", "CapsuleTransparent");
		}
		if (modelItem == null)
		{
			modelItem = ConvenientMethods.loadModel("Models/ItemCapsule/", "CapsuleContent");
		}
	}
	
	public static void freeStaticModels()
	{
		if (modelSolid != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelSolid);
			modelSolid = null;
		}
		if (modelTrans != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelTrans);
			modelTrans = null;
		}
		if (modelItem != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelItem);
			modelItem = null;
		}
	}
}
