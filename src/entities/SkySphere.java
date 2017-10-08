package entities;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import models.TexturedModel;
import renderEngine.SkyManager;
import toolbox.ConvenientMethods;

public class SkySphere extends Entity
{
	//private TexturedModel[] skyPeachBeach;
	//private TexturedModel[] skyBlack;
	//private TexturedModel[] skyIceMountains;
	//private TexturedModel[] skyIslands;
	//private TexturedModel[] skySkyHigh;
	//private TexturedModel[] skyClouds;
	//private TexturedModel[] skyFireSea;
	//private TexturedModel[] skyGalaxyClouds;
		
	public SkySphere(float scale) 
	{
		super(new Vector3f(0, 0, 0), 0, 0, 0, scale);
		//skyPeachBeach = ConvenientMethods.loadModel("Models/Sky/", "SpherePeachBeach", MainGameLoop.gameLoader);
		//skyBlack = ConvenientMethods.loadModel("Models/Sky/", "SphereBlack", MainGameLoop.gameLoader);
		//skyIceMountains = ConvenientMethods.loadModel("Models/Sky/", "SphereIceMountains", MainGameLoop.gameLoader);
		//skyIslands = ConvenientMethods.loadModel("Models/Sky/", "SphereEmerald", MainGameLoop.gameLoader);
		//skySkyHigh = ConvenientMethods.loadModel("Models/Sky/", "SphereSkyHigh", MainGameLoop.gameLoader);
		//skyClouds = ConvenientMethods.loadModel("Models/Sky/", "SphereClouds", MainGameLoop.gameLoader);
		//skyFireSea = ConvenientMethods.loadModel("Models/Sky/", "SphereFireSea", MainGameLoop.gameLoader);
		//skyGalaxyClouds = ConvenientMethods.loadModel("Models/Sky/", "SphereGalaxyClouds", MainGameLoop.gameLoader);
		
		respawn();
		super.setVisibility(false);
	}
	
	@Override
	public void step()
	{
		setRotY(SkyManager.getTimeOfDay());
		setX(MainGameLoop.gamePlayer.getX());
		setZ(MainGameLoop.gamePlayer.getZ());
	}
	
	@Override
	public void respawn()
	{
		super.respawn();
		//super.setVisibility(false);
		
		switch(MainGameLoop.levelID)
		{
			case MainGameLoop.levelIDs.PSS: 
				//super.setVisibility(false);
				break;
				
			case MainGameLoop.levelIDs.CCMSlide:
				//super.setModels(skyBlack);
				break;
				
			case MainGameLoop.levelIDs.WF:
				//super.setModels(skySkyHigh);
				break;
				
			case MainGameLoop.levelIDs.BOB:
				//super.setModels(skyIslands);
				break;
				
			case MainGameLoop.levelIDs.CCM:
				//super.setModels(skyIceMountains);
				break;
				
			case MainGameLoop.levelIDs.JRB:
				//super.setModels(skyClouds);
				break;
				
			case MainGameLoop.levelIDs.HUB:
				//super.setModels(skyIslands);
				break;
				
			case MainGameLoop.levelIDs.LLL:
				//super.setModels(skyFireSea);
				break;
				
			default:
				//super.setVisibility(false);
				break;
		}
	}
}
