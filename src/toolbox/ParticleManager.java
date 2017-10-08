package toolbox;

import renderEngine.Loader;

import java.util.ArrayList;

import models.TexturedModel;

public class ParticleManager 
{
	public static TexturedModel[] modelParticleBubble;
	public static TexturedModel[] modelParticleSparkle;
	public static TexturedModel[] modelParticleSparkleBlue;
	public static TexturedModel[] modelParticleSparkleRed;
	public static ParticleSequence particleSequenceDusts;
	public static ParticleSequence particleSequenceDustCloud;
	public static TexturedModel[] modelParticleSnowball;
	public static TexturedModel[] modelParticleSnowdrop;
	public static ParticleSequence particleSequenceDustCloudReverse;
	
	public static void loadParticleModels()
	{
		/*
		modelParticleBubble = ConvenientMethods.loadModel("Models/Particle/", "Bubble", loader);
		modelParticleBubble[0].getTexture().setUseFakeLighting(true);
		//modelParticleBubble[0].getTexture().setHasTransparency(true);
		
		modelParticleSparkle = ConvenientMethods.loadModel("Models/Particle/", "Sparkle", loader);
		modelParticleSparkle[0].getTexture().setUseFakeLighting(true);
		
		modelParticleSparkleBlue = ConvenientMethods.loadModel("Models/Particle/", "SparkleBlue", loader);
		modelParticleSparkleBlue[0].getTexture().setUseFakeLighting(true);
		
		modelParticleSparkleRed = ConvenientMethods.loadModel("Models/Particle/", "SparkleRed", loader);
		modelParticleSparkleRed[0].getTexture().setUseFakeLighting(true);
		
		particleSequenceDusts = new ParticleSequence();
		particleSequenceDusts.addModel(ConvenientMethods.loadModel("Models/Particle/", "Dust0", loader), 1);
		particleSequenceDusts.addModel(ConvenientMethods.loadModel("Models/Particle/", "Dust0", loader), 1);
		particleSequenceDusts.addModel(ConvenientMethods.loadModel("Models/Particle/", "Dust1", loader), 1);
		particleSequenceDusts.addModel(ConvenientMethods.loadModel("Models/Particle/", "Dust1", loader), 1);
		particleSequenceDusts.addModel(ConvenientMethods.loadModel("Models/Particle/", "Dust2", loader), 1);
		particleSequenceDusts.addModel(ConvenientMethods.loadModel("Models/Particle/", "Dust2", loader), 1);
		particleSequenceDusts.addModel(ConvenientMethods.loadModel("Models/Particle/", "Dust3", loader), 1);
		particleSequenceDusts.addModel(ConvenientMethods.loadModel("Models/Particle/", "Dust3", loader), 1);
		particleSequenceDusts.addModel(ConvenientMethods.loadModel("Models/Particle/", "Dust4", loader), 1);
		particleSequenceDusts.addModel(ConvenientMethods.loadModel("Models/Particle/", "Dust4", loader), 1);
		particleSequenceDusts.addModel(ConvenientMethods.loadModel("Models/Particle/", "Dust5", loader), 1);
		particleSequenceDusts.addModel(ConvenientMethods.loadModel("Models/Particle/", "Dust5", loader), 1);
		particleSequenceDusts.addModel(ConvenientMethods.loadModel("Models/Particle/", "Dust6", loader), 1);
		particleSequenceDusts.addModel(ConvenientMethods.loadModel("Models/Particle/", "Dust6", loader), 1);
		
		
		particleSequenceDustCloudReverse = new ParticleSequence();
		particleSequenceDustCloudReverse.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 0.1f);
		particleSequenceDustCloudReverse.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 0.2f);
		particleSequenceDustCloudReverse.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 0.35f);
		particleSequenceDustCloudReverse.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 0.45f);
		particleSequenceDustCloudReverse.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 0.7f);
		particleSequenceDustCloudReverse.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 0.9f);
		particleSequenceDustCloudReverse.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 1.1f);
		particleSequenceDustCloudReverse.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 1.25f);
		particleSequenceDustCloudReverse.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 1.35f);
		particleSequenceDustCloudReverse.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 1.5f);
		
		particleSequenceDustCloud = new ParticleSequence();
		particleSequenceDustCloud.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 1.5f);
		particleSequenceDustCloud.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 1.35f);
		particleSequenceDustCloud.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 1.25f);
		particleSequenceDustCloud.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 1.1f);
		particleSequenceDustCloud.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 0.9f);
		particleSequenceDustCloud.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 0.7f);
		particleSequenceDustCloud.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 0.45f);
		particleSequenceDustCloud.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 0.35f);
		particleSequenceDustCloud.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 0.2f);
		particleSequenceDustCloud.addModel(ConvenientMethods.loadModel("Models/Particle/", "DustCloud", loader), 0.1f);
		
		modelParticleSnowball = ConvenientMethods.loadModel("Models/Particle/", "Snowball", loader);
		modelParticleSnowdrop = ConvenientMethods.loadModel("Models/Particle/", "Snowdrop", loader);
		*/
	}
}
