package particles;

import engineTester.MainGameLoop;

public class ParticleResources 
{
	public static ParticleTexture textureDust = null;
	public static ParticleTexture textureSnowDrop = null;
	public static ParticleTexture textureDustCloud = null;
	public static ParticleTexture textureSnowball = null;
	public static ParticleTexture textureStar = null;
	public static ParticleTexture textureSparkleYellow = null;
	public static ParticleTexture textureSparkleRed = null;
	public static ParticleTexture textureSparkleBlue = null;
	public static ParticleTexture textureWaterDrop = null;
	public static ParticleTexture textureLightBlueTrail = null;
	public static ParticleTexture textureBlueTrail = null;
	public static ParticleTexture textureBlackTrail = null;
	public static ParticleTexture textureGrayTrail = null;
	public static ParticleTexture textureSplash = null;
	public static ParticleTexture textureBubble = null;
	public static ParticleTexture textureExplosion1 = null;
	public static ParticleTexture textureExplosion2 = null;
	public static ParticleTexture textureBlackFade = null;
	
	public static void loadParticles()
	{
		textureSnowDrop = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/SnowDropAtlas"), 2, 1.0f, 0);
		textureDustCloud = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/41257828"), 1, 0.2f, 0);
		textureSnowball = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/1F3C3CE8"), 1, 1.0f, 0);
		textureStar = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/Star"), 1, 1.0f, 0);
		textureSparkleYellow = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/Sparkle"), 1, 1.0f, 1);
		textureSparkleRed = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/SparkleRed"), 1, 1.0f, 1);
		textureSparkleBlue = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/SparkleBlue"), 1, 1.0f, 1);
		textureWaterDrop = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/WaterDrop"), 1, 1, 0);
		textureLightBlueTrail = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/SpTrailLightBlue"), 1, 0.1f, 1);
		textureBlueTrail = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/SpTrailBlue"), 1, 0.1f, 1);
		textureBlackTrail = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/SpTrailBlack"), 1, 0.1f, 1);
		textureGrayTrail = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/SpTrailGray"), 1, 0.1f, 1);
		textureDust = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/DustAtlas"), 4, 0.2f, 0);
		textureSplash = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/SplashAtlas"), 4, 0.6f, 0);
		textureBubble = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/BubbleInverseAtlas"), 4, 0.6f, 0);
		textureExplosion1 = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/Explosion1Atlas"), 4, 0.8f, 0);
		textureExplosion2 = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/Explosion2Atlas"), 4, 0.8f, 0);
		textureBlackFade = new ParticleTexture(MainGameLoop.gameLoader.loadTexture("Models/Particle/BlackFadeAtlas"), 2, 1f, 0);
	}
}
