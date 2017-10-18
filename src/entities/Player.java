package entities;

import models.TexturedModel;
import particles.Particle;
import particles.ParticleResources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import animation.Body;
import animation.Limb;
import audio.AudioSources;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import toolbox.AudioRes;
import toolbox.ConvenientMethods;
import toolbox.Joystick;
import toolbox.Maths;
import toolbox.ParticleManager;

import collision.CollisionChecker;
import collision.Triangle3D;
import engineTester.MainGameLoop;

public class Player extends Entity
{
	private float moveAcceleration = 0.075f;
	private float moveSpeedMax = 0.9f;
	private float moveSpeedCurrent;
	private float moveAccelerationAir = 0.015f; //old 0.015
	private float moveSpeedAirMax = 0.6f;//old 0.1
	private float moveSpeedAirCurrent;
	private float xVel;
	private float yVel;
	private float zVel;
	private float xVelAir;
	private float zVelAir;
	private float xDisp; //from moving platforms
	private float yDisp; //from moving platforms
	private float zDisp; //from moving platforms
	private float movementInputX;
	private float movementInputY;
	private float movementAngle; // in degrees
	private float cameraInputX;
	private float cameraInputY;
	private float frictionGround = 0.05f;
	private float frictionSlide = 0.055f;//original 0.025 as of 8/10/2016 //0.06
	private float frictionAir = 0.007f;//old 0.005
	private float frictionGroundSlippery = 0.01f;
	private float frictionSlippery = 0.017f;//0.016 perfect!
	private float normalSpeedLimit = 0.9f;
	private float slipperySpeedLimit = 3f;//2.0
	private float airSpeedLimit = 2.5f;//2.5
	private boolean jumpInput;
	private boolean previousJumpInput;
	private boolean actionInput;//action means pretty much the 'B' button
	private boolean previousActionInput;
	private boolean shoulderInput;//shoulder is the 'Z' button on n64 controller, or like L1 on play station
	private boolean previousShoulderInput;
	private boolean selectInput;//start button
	private boolean previousSelectInput;
	private Vector3f cameraPosition;
	private Vector3f newCameraPosition;
	private boolean passedCheck1;
	private boolean passedCheck2;
	private float surfaceTension = 1.4f;//after a successful first collision, how far down the second check checks for another collision
	private float wallHug = 1.0f;//how far away from the plane you get placed when you collide
	private boolean inAir;
	private boolean inAirPrevious;
	private boolean onWall;
	private float gravity = 0.05f;//power of gravity  //0.05 = real
	private float terminalVelocity = 2.3f;//og 2.0
	private float terminalVelocity2 = 3.5f;//og 3.0
	private float pushUpValue;
	private float slopeFooting = 0.75f;//value of a slope's y normal must be above to have footing //0.75
	private float slopeWall = 0.4f;//value of a slope's y normal must be above to not be a wall //0.5
	private float slopeSlipRate = 0.05f;//when you are on a slope that you dont have footing on(and isnt a wall), how fast you slip down the slope
	private int jumpFramesMax = 12;//when you jump, this is how many frames you can hold the jump button for for more height
	private int jumpFrameCurrent;
	private int landTimerMax = 8;//when you land on the ground, how many frames you get to do another jump
	private int landTimerCurrent;
	private int jumpNumber;
	private float tripleJumpSpeed = 0.35f;//speed that you must be moving in order to do a triple jump
	private float headHeight = 9;//height that the camera is above your feet
	private boolean firstPersonView;
	private int skidTimer;
	private int skidTimerMax = 20;
	private boolean isDiving;
	private boolean isHighJumping;
	private boolean isLongJumping;
	private boolean isSideFlipping;
	private boolean isGroundPounding;
	private boolean canSideFlip;
	private float slowDownRate = 0.04f; //how fast you slowdown every frame when youre going faster than max speed //old 0.03
	private float slowDownAirRate = 0.017f; //old = 0.005
	private float inputAngle;
	private Vector3f displayOffset;
	private float modelStandIndex;
	private float modelRunIndex;
	private float modelSwimIndex;
	private float modelSwimStrokeIndex;
	private int groundPoundFrame;
	private int groundPoundFrameMax = 30;
	private int groundPoundRecoveryTimer;
	private boolean isSliding;
	private boolean isRollingOut;
	private int wallKickTimer;
	private int wallKickTimerMax = 11;
	private float hitboxHorizontal = 3;
	private float hitboxVertically = 12;
	private float zoomInput;
	private boolean canSwimUp;
	private int iFrame;
	private float previousSpeed;
	private float previousDirection;
	private float squishAmount;
	
	
	Camera currentCamera;
	private Triangle3D triCol;
	private Vector3f triColPosition;
	private TexturedModel[] previousModel = null;
	private TexturedModel[] modelCrouch = null;
	private TexturedModel[] modelDive = null;
	private TexturedModel[] modelGroundPound = null;
	private TexturedModel[] modelHighJump = null;
	private TexturedModel[] modelJump = null;
	private TexturedModel[] modelLongJump = null;
	private TexturedModel[] modelSideFlip = null;
	private TexturedModel[] modelSkid = null;
	private TexturedModel[] modelStand0 = null;
	private TexturedModel[] modelStand1 = null;
	private TexturedModel[] modelStand2 = null;
	private TexturedModel[] modelStand3 = null;
	private TexturedModel[] modelStand4 = null;
	private TexturedModel[] modelStand5 = null;
	private TexturedModel[] modelStand6 = null;
	private TexturedModel[] modelStand7 = null;
	private TexturedModel[] modelStand8 = null;
	private TexturedModel[] modelStand9 = null;
	private TexturedModel[] modelStand10 = null;
	private TexturedModel[] modelStand11 = null;
	private TexturedModel[] modelStand12 = null;
	private TexturedModel[] modelStand13 = null;
	private TexturedModel[] modelStand14 = null;
	private TexturedModel[] modelStand15 = null;
	private TexturedModel[] modelStand16 = null;
	private TexturedModel[] modelStand17 = null;
	private TexturedModel[] modelStand18 = null;
	private TexturedModel[] modelStand19 = null;
	private TexturedModel[] modelRun0 = null;
	private TexturedModel[] modelRun1 = null;
	private TexturedModel[] modelRun2 = null;
	private TexturedModel[] modelRun3 = null;
	private TexturedModel[] modelRun4 = null;
	private TexturedModel[] modelRun5 = null;
	private TexturedModel[] modelRun6 = null;
	private TexturedModel[] modelRun7 = null;
	private TexturedModel[] modelRun8 = null;
	private TexturedModel[] modelRun9 = null;
	private TexturedModel[] modelRun10 = null;
	private TexturedModel[] modelRun11 = null;
	private TexturedModel[] modelRun12 = null;
	private TexturedModel[] modelRun13 = null;
	private TexturedModel[] modelRun14 = null;
	private TexturedModel[] modelRun15 = null;
	private TexturedModel[] modelRun16 = null;
	private TexturedModel[] modelRun17 = null;
	private TexturedModel[] modelRun18 = null;
	private TexturedModel[] modelRun19 = null;
	private TexturedModel[] modelRollOut = null;
	private TexturedModel[] modelBonk = null;
	private TexturedModel[] modelPose = null;
	private TexturedModel[] modelSwim0 = null;
	private TexturedModel[] modelSwim1 = null;
	private TexturedModel[] modelSwim2 = null;
	private TexturedModel[] modelSwim3 = null;
	private TexturedModel[] modelSwim4 = null;
	private TexturedModel[] modelSwim5 = null;
	private TexturedModel[] modelSwim6 = null;
	private TexturedModel[] modelSwim7 = null;
	private TexturedModel[] modelSwim8 = null;
	private TexturedModel[] modelSwim9 = null;
	private TexturedModel[] modelSwim10 = null;
	private TexturedModel[] modelSwim11 = null;
	private TexturedModel[] modelSwimStroke0 = null;
	private TexturedModel[] modelSwimStroke1 = null;
	private TexturedModel[] modelSwimStroke2 = null;
	private TexturedModel[] modelSwimStroke3 = null;
	private TexturedModel[] modelSwimStroke4 = null;
	private TexturedModel[] modelSwimStroke5 = null;
	private TexturedModel[] modelSwimStroke6 = null;
	private TexturedModel[] modelSwimStroke7 = null;
	private TexturedModel[] modelSwimStroke8 = null;
	private TexturedModel[] modelSwimStroke9 = null;
	private Body myBody;
	private Limb myHead;
	private Limb myLeftHumerus;
	private Limb myLeftForearm;
	private Limb myLeftHand;
	private Limb myLeftThigh;
	private Limb myLeftShin;
	private Limb myLeftFoot;
	private Limb myRightHumerus;
	private Limb myRightForearm;
	private Limb myRightHand;
	private Limb myRightThigh;
	private Limb myRightShin;
	private Limb myRightFoot;
	private int timeTick;
	private int bonkTimer;
	private int bonkTimerMax = 20;
	private boolean canWallKick;
	private boolean actionPreformed;
	private int starGrabTimer;
	private int starGrabTimerMax = 240;
	private float cameraRadius;
	private float cameraRadiusTarget;
	private boolean inCutScene;
	private float myCameraYawTarget;
	public float myCameraPitchTarget;
	private int mousePreviousY;
	private int mousePreviousX;
	private float cameraLaziness = 3;
	private float cameraRadiusZoom = 40;
	private float cameraRadiusZoomOut = 104f;
	private boolean isRunning;
	private boolean isSwimming;
	private boolean isSwimmingPrevious;
	private boolean isGettingExternallyMoved;
	private int timeSinceJump;
	private int timeSinceAction;
	private boolean onPole;
	private Pole grabbedPole;
	private int grabbedPoleSound;
	
	private boolean recordInput;
	private boolean previousRecordInput;
	private boolean loadInput;
	private boolean previousLoadInput;
	
	public Player(TexturedModel[] model, Vector3f position, float rotX, float rotY, float rotZ, float scale, 
			Camera cameraToUse, Body myBody, Limb myHead, Limb myLeftHumerus, 
			Limb myLeftForearm, Limb myLeftHand, Limb myLeftThigh, Limb myLeftShin, Limb myLeftFoot, 
			Limb myRightHumerus, Limb myRightForearm, Limb myRightHand, Limb myRightThigh, Limb myRightShin, 
			Limb myRightFoot, Loader loader)
	{
		super(model, position, rotX, rotY, rotZ, scale);
		super.setY(getY()+32);
		displayOffset = new Vector3f(0,0,0);
		
		timeTick = 0;
		currentCamera = cameraToUse;
		xVel = 0.0f;
		yVel = 0.0f;
		zVel = 0.0f;
		xDisp = 0;
		yDisp = 0;
		zDisp = 0;
		xVelAir = 0.0f;
		zVelAir = 0.0f;
		movementInputX = 0;
		movementInputY = 0;
		movementAngle = 0;
		moveSpeedCurrent = 0;
		cameraInputX = 0;
		cameraInputY = 0;
		jumpInput = false;
		previousJumpInput = false;
		newCameraPosition = new Vector3f(0,0,0);
		triCol = null;
		passedCheck1 = false;
		passedCheck2 = false;
		inAir = true;
		inAirPrevious = true;
		pushUpValue = 0.0f;
		landTimerCurrent = 0;
		jumpNumber = 1;
		onWall = false;
		actionInput = false;
		previousActionInput = false;
		shoulderInput = false;
		previousShoulderInput = false;
		firstPersonView = false;
		skidTimer = 0;
		isDiving = false;
		isHighJumping = false;
		isLongJumping = false;
		isSideFlipping = false;
		isGroundPounding = false;
		canSideFlip = false;
		moveSpeedAirCurrent = 0.0f;
		inputAngle = 0.0f;
		modelStandIndex = 0.0f;
		modelRunIndex = 0.0f;
		modelSwimIndex = 0.0f;
		modelSwimStrokeIndex = 0.0f;
		groundPoundFrame = 0;
		isSliding = false;
		isRollingOut = false;
		bonkTimer = 0;
		canWallKick = false;
		wallKickTimer = 0;
		actionPreformed = false;
		jumpFrameCurrent = 0;
		cameraRadius = 104;
		cameraRadiusTarget = 104;
		inCutScene = false;
		mousePreviousX = Mouse.getX();
		mousePreviousY = Mouse.getY();
		isRunning = false;
		isSwimming = false;
		isSwimmingPrevious = false;
		zoomInput = 0;
		canSwimUp = false;
		timeSinceJump = 0;
		timeSinceAction = 0;
		onPole = false;
		grabbedPole = null;
		iFrame = 0;
		groundPoundRecoveryTimer = 0;
		isGettingExternallyMoved = false;
		previousSpeed = 0;
		previousDirection = 0;
		grabbedPoleSound = 0;
		squishAmount = 1;
		recordInput = false;
		previousRecordInput = false;
		loadInput = false;
		previousLoadInput = false;
		
		/*
		modelStand0 = loadModel("Models/Mario64/", "Mario64Stand0", loader);
		modelStand1 = loadModel("Models/Mario64/", "Mario64Stand1", loader);
		modelStand2 = loadModel("Models/Mario64/", "Mario64Stand2", loader);
		modelStand3 = loadModel("Models/Mario64/", "Mario64Stand3", loader);
		modelStand4 = loadModel("Models/Mario64/", "Mario64Stand4", loader);
		modelStand5 = loadModel("Models/Mario64/", "Mario64Stand5", loader);
		modelStand6 = loadModel("Models/Mario64/", "Mario64Stand6", loader);
		modelStand7 = loadModel("Models/Mario64/", "Mario64Stand7", loader);
		modelStand8 = loadModel("Models/Mario64/", "Mario64Stand8", loader);
		modelStand9 = loadModel("Models/Mario64/", "Mario64Stand9", loader);
		modelStand10 = loadModel("Models/Mario64/", "Mario64Stand10", loader);
		modelStand11 = loadModel("Models/Mario64/", "Mario64Stand11", loader);
		modelStand12 = loadModel("Models/Mario64/", "Mario64Stand12", loader);
		modelStand13 = loadModel("Models/Mario64/", "Mario64Stand13", loader);
		modelStand14 = loadModel("Models/Mario64/", "Mario64Stand14", loader);
		modelStand15 = loadModel("Models/Mario64/", "Mario64Stand15", loader);
		modelStand16 = loadModel("Models/Mario64/", "Mario64Stand16", loader);
		modelStand17 = loadModel("Models/Mario64/", "Mario64Stand17", loader);
		modelStand18 = loadModel("Models/Mario64/", "Mario64Stand18", loader);
		modelStand19 = loadModel("Models/Mario64/", "Mario64Stand19", loader);
		modelRun0 = loadModel("Models/Mario64/", "Mario64Run0", loader);
		modelRun1 = loadModel("Models/Mario64/", "Mario64Run1", loader);
		modelRun2 = loadModel("Models/Mario64/", "Mario64Run2", loader);
		modelRun3 = loadModel("Models/Mario64/", "Mario64Run3", loader);
		modelRun4 = loadModel("Models/Mario64/", "Mario64Run4", loader);
		modelRun5 = loadModel("Models/Mario64/", "Mario64Run5", loader);
		modelRun6 = loadModel("Models/Mario64/", "Mario64Run6", loader);
		modelRun7 = loadModel("Models/Mario64/", "Mario64Run7", loader);
		modelRun8 = loadModel("Models/Mario64/", "Mario64Run8", loader);
		modelRun9 = loadModel("Models/Mario64/", "Mario64Run9", loader);
		modelRun10 = loadModel("Models/Mario64/", "Mario64Run10", loader);
		modelRun11 = loadModel("Models/Mario64/", "Mario64Run11", loader);
		modelRun12 = loadModel("Models/Mario64/", "Mario64Run12", loader);
		modelRun13 = loadModel("Models/Mario64/", "Mario64Run13", loader);
		modelRun14 = loadModel("Models/Mario64/", "Mario64Run14", loader);
		modelRun15 = loadModel("Models/Mario64/", "Mario64Run15", loader);
		modelRun16 = loadModel("Models/Mario64/", "Mario64Run16", loader);
		modelRun17 = loadModel("Models/Mario64/", "Mario64Run17", loader);
		modelRun18 = loadModel("Models/Mario64/", "Mario64Run18", loader);
		modelRun19 = loadModel("Models/Mario64/", "Mario64Run19", loader);
		modelCrouch = loadModel("Models/Mario64/", "Mario64Crouch", loader);
		modelDive = loadModel("Models/Mario64/", "Mario64Dive", loader);
		modelGroundPound = loadModel("Models/Mario64/", "Mario64GroundPound", loader);
		modelHighJump = loadModel("Models/Mario64/", "Mario64HighJump", loader);
		modelJump = loadModel("Models/Mario64/", "Mario64Jump", loader);
		modelLongJump = loadModel("Models/Mario64/", "Mario64LongJump", loader);
		modelSideFlip = loadModel("Models/Mario64/", "Mario64SideFlip", loader);
		modelSkid = loadModel("Models/Mario64/", "Mario64Skid", loader);
		modelRollOut = loadModel("Models/Mario64/", "Mario64RollOut", loader);
		modelBonk = loadModel("Models/Mario64/", "Mario64Bonk", loader);
		modelPose = loadModel("Models/Mario64/", "Mario64Pose", loader);
		modelSwim0 = loadModel("Models/Mario64/", "Mario64Swim0", loader);
		modelSwim1 = loadModel("Models/Mario64/", "Mario64Swim1", loader);
		modelSwim2 = loadModel("Models/Mario64/", "Mario64Swim2", loader);
		modelSwim3 = loadModel("Models/Mario64/", "Mario64Swim3", loader);
		modelSwim4 = loadModel("Models/Mario64/", "Mario64Swim4", loader);
		modelSwim5 = loadModel("Models/Mario64/", "Mario64Swim5", loader);
		modelSwim6 = loadModel("Models/Mario64/", "Mario64Swim6", loader);
		modelSwim7 = loadModel("Models/Mario64/", "Mario64Swim7", loader);
		modelSwim8 = loadModel("Models/Mario64/", "Mario64Swim8", loader);
		modelSwim9 = loadModel("Models/Mario64/", "Mario64Swim9", loader);
		modelSwim10 = loadModel("Models/Mario64/", "Mario64Swim10", loader);
		modelSwim11 = loadModel("Models/Mario64/", "Mario64Swim11", loader);
		modelSwimStroke0 = loadModel("Models/Mario64/", "Mario64SwimStroke0", loader);
		modelSwimStroke1 = loadModel("Models/Mario64/", "Mario64SwimStroke1", loader);
		modelSwimStroke2 = loadModel("Models/Mario64/", "Mario64SwimStroke2", loader);
		modelSwimStroke3 = loadModel("Models/Mario64/", "Mario64SwimStroke3", loader);
		modelSwimStroke4 = loadModel("Models/Mario64/", "Mario64SwimStroke4", loader);
		modelSwimStroke5 = loadModel("Models/Mario64/", "Mario64SwimStroke5", loader);
		modelSwimStroke6 = loadModel("Models/Mario64/", "Mario64SwimStroke6", loader);
		modelSwimStroke7 = loadModel("Models/Mario64/", "Mario64SwimStroke7", loader);
		modelSwimStroke8 = loadModel("Models/Mario64/", "Mario64SwimStroke8", loader);
		modelSwimStroke9 = loadModel("Models/Mario64/", "Mario64SwimStroke9", loader);
		*/
		this.myBody = myBody;
		this.myHead = myHead;
		this.myLeftHumerus = myLeftHumerus;
		this.myLeftForearm = myLeftForearm;
		this.myLeftHand = myLeftHand;
		this.myLeftThigh = myLeftThigh;
		this.myLeftShin = myLeftShin;
		this.myLeftFoot = myLeftFoot;
		this.myRightHumerus = myRightHumerus;
		this.myRightForearm = myRightForearm;
		this.myRightHand = myRightHand;
		this.myRightThigh = myRightThigh;
		this.myRightShin = myRightShin;
		this.myRightFoot = myRightFoot;
		
		this.setLimbsScale(0.1f);
		//respawn();
	}
	
	@Override
	public void step()
	{
		super.increasePosition(-displayOffset.getX(), -displayOffset.getY(), -displayOffset.getZ());
		//System.out.println("X = "+super.getX()+"    Y = "+super.getY()+"    Z = "+super.getZ());
		//System.out.println("yVel = "+yVel);
		//System.out.println(isGroundPounding);
		timeTick++;
		modelStandIndex = timeTick;
		groundPoundFrame = Math.max(0, groundPoundFrame-1);
		wallKickTimer = Math.max(0, wallKickTimer-1);
		groundPoundRecoveryTimer = Math.max(0, groundPoundRecoveryTimer-1);
		iFrame = Math.max(0, iFrame-1);
		previousSpeed = (float)Math.sqrt((xVel+xVelAir)*(xVel+xVelAir)+(zVel+zVelAir)*(zVel+zVelAir));
		previousDirection = getRotY();
		timeSinceJump++;
		timeSinceAction++;
		if(starGrabTimer >= starGrabTimerMax*0.60)
		{
			//cameraRadius += (cameraRadiusZoom-cameraRadius)/20;
			cameraRadiusTarget = cameraRadiusZoom;
		}
		else
		{
			if(starGrabTimer >= starGrabTimerMax*0.40)
			{
				//cameraRadius = cameraRadiusZoom;
			}
			else
			{
				if(starGrabTimer > 0)
				{
					//cameraRadius += (cameraRadiusZoomOut-cameraRadius)/20;
					cameraRadiusTarget = cameraRadiusZoomOut;
				}
			}
		}
		if(starGrabTimer == 1)
		{
			//cameraRadius = cameraRadiusZoomOut;
			cameraRadiusTarget = cameraRadiusZoomOut;
			inCutScene = false;
			setLimbsVisibility(true);
			setVisibility(false);
		}
		cameraRadius += (cameraRadiusTarget-cameraRadius)/20;

		cameraRadiusTarget+=zoomInput;
		cameraRadiusTarget = Math.max(cameraRadiusZoom, cameraRadiusTarget);
		cameraRadiusTarget = Math.min(cameraRadiusZoomOut, cameraRadiusTarget);
		
		
		starGrabTimer = Math.max(0, starGrabTimer-1);
		actionPreformed = false;
		
		isSwimmingPrevious = isSwimming;
		if(super.getY() < -4)
		{
			isSwimming = true;
			isHighJumping = false;
		    isLongJumping = false;
		    isSideFlipping = false;
		    canSideFlip = false;
		    isGroundPounding = false;
		    isDiving = false;
		    isRollingOut = false;
		    isSliding = false;
			bonkTimer = 0;
			skidTimer = 0;
			if(jumpInput == false)
			{
				canSwimUp = true;
			}
		}
		else
		{
			isSwimming = false;
			canSwimUp = false;
		}
		
		if(isSwimmingPrevious && !isSwimming)
		{
			AudioRes.playSound(16, 1, super.getPosition());
		}
		
		if(!isSwimmingPrevious && isSwimming)
		{
			AudioRes.playSound(17, 1, super.getPosition());
			
			/*
			for(int i = 0; i < ((int)Math.abs(yVel*4))+5; i++)
			{
				MainGameLoop.gameEntitiesToAdd.add(new Particle(
						ParticleManager.modelParticleBubble, 
						new Vector3f(super.getX(), super.getY(), super.getZ()), 
						60, 2, (float)Math.random()-0.5f, (float)(Math.random()*0.3+0.2f-yVel*0.3), (float)Math.random()-0.5f, 0.05f, 0));
			}
			*/
			for(int i = 0; i < ((int)Math.abs(yVel*8))+18; i++)
			{
				MainGameLoop.gameEntitiesToAdd.add(new MyParticle(
						ParticleManager.modelParticleBubble, 
						new Vector3f(super.getX(), super.getY(), super.getZ()), 
						60, 2, (float)Math.random()-0.5f+xVel*0.8f, 
						       (float)(Math.random()*0.3+0.2f-yVel*0.3), 
						       (float)Math.random()-0.5f+zVel*0.8f, 
						       0.05f, 0, false));
			}
		}
		
		if(getInCutScene() == false)
		{
			if(!firstPersonView && !MainGameLoop.freeCamera)
			{
				float xDiff = currentCamera.getX()-getX();
				float zDiff = currentCamera.getZ()-getZ();
				float angleRad = (float)Math.toRadians(getRotY());
				float newZ = (float)(-(zDiff)*Math.cos(angleRad) - (xDiff)*Math.sin(angleRad));
				//float newX = (float)((xDiff)*Math.cos(angleRad) - (zDiff)*Math.sin(angleRad));
				
				myCameraYawTarget-=newZ/65;
			}

			
			myCameraPitchTarget+=cameraInputY;
			myCameraYawTarget+=cameraInputX;
			//System.out.println("caminputx = "+cameraInputX+"    camy = "+cameraInputY);
			
			
			//float amount =(float)(Math.sin(Math.toRadians(myCameraYawTarget))-Math.sin(Math.toRadians(super.getRotY())));
			/*
			float amount = (float)(Math.sin(Math.toRadians(myCameraYawTarget-super.getRotY()-180)));
			float sinCam = (float)Math.sin(Math.toRadians(myCameraYawTarget-180));
			float sinPlayer = (float)Math.sin(Math.toRadians(super.getRotY()));
			float diff = sinCam-sinPlayer;
			
			float camAngle = ((-myCameraYawTarget+90)+360) % 360;
			float camPlayer = (super.getRotY()+360) % 360;
			float half = (camPlayer-180+360) % 360;
			
			float adjCam = (float)Math.sin(0.5f*Math.toRadians(camAngle));
			float adjPlayer = (float)Math.sin(0.5f*Math.toRadians(camPlayer));
			float adjDiff =  (float)Math.sin(0.5f*Math.toRadians(camAngle-camPlayer));
			//myCameraYawTarget-=10*(adjPlayer-adjCam);
			//if(amount < 0)
			//System.out.println(amount);
			//System.out.println("camera = "+sinCam+"     player = "+sinPlayer
			//		+"     difference = "+diff);
			//System.out.println(super.getRotY());
			//myCameraYawTarget+=diff;
			
			//System.out.println("player = "+camPlayer+"       Cam = "+camAngle+"     difference = "+(adjPlayer-adjCam));
			//System.out.println(adjDiff);
			if(camAngle > camPlayer)
			{
				//decrease camera target
				//myCameraYawTarget+=1;
			}
			else
			{
				//increase target
				//myCameraYawTarget+=1;
			}
			*/
			//myCameraYawTarget+=adjDiff;
		}

		switch(MainGameLoop.levelID)
		{
			case 0://MainGameLoop.levelIDs.CCM :
				float radius2 = MainGameLoop.snowRadius*2;
				float radius = MainGameLoop.snowRadius;
				float basex = super.getX() - radius;
				float basey = super.getY();
				float basez = super.getZ() - radius;
				int density = MainGameLoop.snowDensity;
				for(int i = 0; i < density; i++)
				{
					new Particle(ParticleResources.textureSnowDrop,
							new Vector3f(basex + radius2*(float)Math.random(),
										 basey + radius*(float)Math.random(),
										 basez + radius2*(float)Math.random()),
							new Vector3f(0, -0.25f, 0), 0, 60, 0, (float)Math.random()+0.75f, -0.03f);//original y vel = -0.5
				}
				break;
		
			default : 
				break;
		}
		
		if(bonkTimer > 0)
		{
			isHighJumping = false;
		    isLongJumping = false;
		    isSideFlipping = false;
		    canSideFlip = false;
		    isGroundPounding = false;
		    isDiving = false;
		    isRollingOut = false;
		    isSliding = false;
		    if(inAir)
		    {
		    	bonkTimer = bonkTimerMax;
		    }
		    else
		    {
		    	if(bonkTimer == 1)
				{
					xVel = 0;
					zVel = 0;//originally yVel? must have been a mistake
				}
		    }
		}
		
		if(wallKickTimer == 0)
		{
			canWallKick = false;
		}
		
		if(!inAir)
		{
			bonkTimer = Math.max(0, bonkTimer-1);
			onPole = false;
		}
		
		
		setMovementInputs();
		
		if(Joystick.joystickExists() && Joystick.getIsButtonPressed(Joystick.BUTTON_RB))
		{
			System.out.println("X = "+super.getX()+"    Y = "+super.getY()+"    Z = "+super.getZ());
		}
		
		if(selectInput && !previousSelectInput)
		{
			if(MainGameLoop.freeMouse)
			{
				//MainGameLoop.freeMouse = false;
				//Mouse.setGrabbed(true);
			}
			else
			{
				//MainGameLoop.freeMouse = true;
				//Mouse.setGrabbed(false);
			}
		}
		
		if(inAir && isSwimming == false)
		{
			AudioRes.stopSound(29);
			skidTimer = 0;
			if(bonkTimer == 0)
			{
				applyFrictionAir();
				moveMeAir();
			}
			limitMovementSpeedAir();
			limitMovementSpeed(airSpeedLimit);
			//limitMovementSpeed(); //test 
			jumpFrameCurrent = Math.max(jumpFrameCurrent-1, 0);//decrease the current jump frame by 1, but no less than 0
			//wallKickTimer = Math.max(wallKickTimer-1, 0);
			if(jumpInput == false)
			{
				jumpFrameCurrent = 0;
			}
			
			if(jumpInput == true && bonkTimer == 0)
			{
				yVel+=(jumpFrameCurrent+0.0/jumpFramesMax)*0.01;
				//yVel = (float)(jumpFrameCurrent+0.0/jumpFramesMax);
			}
			
			yVel -=gravity;
			if(isGroundPounding)
			{
				xVel = 0;
				zVel = 0;
				if(yVel < -terminalVelocity2)
				{
					yVel = -terminalVelocity2;
				}
			}
			else
			{
				if(yVel < -terminalVelocity)
				{
					yVel = -terminalVelocity;
				}
			}
			
			
			if(groundPoundFrame >= 1)
			{
				if(groundPoundFrame == 1)
				{
					yVel = -3;
				}
				else
				{
					yVel = 0;
				}
			}
			
			if(onPole)
			{
				//super.setRotY(calculateAngleDegrees(xVelAir, zVelAir));
				increaseRotation(0, -movementInputX*2, 0);
				isLongJumping = false;
				isDiving = false;
				isGroundPounding = false;
				isSliding = false;
				isRollingOut = false;
				isHighJumping = false;
	            isSideFlipping = false;
				bonkTimer = 0;
				yVel = 0;
				zVel = 0;
				xVel = 0;
				xVelAir = 0;
				zVelAir = 0;
				jumpNumber = 1;
				
				increasePosition(0, -movementInputY*0.5f, 0);
				
				if(Math.abs(movementInputY) > 0.5)
				{
					AudioRes.playSoundLoop(grabbedPoleSound, getPosition());
				}
				else
				{
					AudioRes.stopSound(grabbedPoleSound);
				}
				
				if(getY() > grabbedPole.getY()+grabbedPole.getHeight())
				{
					setY(grabbedPole.getY()+grabbedPole.getHeight());
				}
				if(getY() < grabbedPole.getY()-8)
				{
					onPole = false;
				}
				
				setRotZ(0);
			}
		}
		else
		{
			//wallKickTimer = 0;
			groundPoundFrame = 0;
			if(inAirPrevious == true)
			{
				landTimerCurrent = landTimerMax;
				if(!isGettingExternallyMoved)
				{
					xVel+=xVelAir;
					zVel+=zVelAir;
					xVelAir = 0.0f;
					zVelAir = 0.0f;
				}
			}
			if(!isGettingExternallyMoved)
			{
				xVelAir = 0.0f;
				zVelAir = 0.0f;
			}
			if(groundPoundRecoveryTimer > 0)
			{
				xVel = 0;
				zVel = 0;
			}
			
			if(isSliding)
			{
				//moveMeSlide();
				if(triCol.getNormal().y > slopeFooting && !triCol.isSlippery())
				{
					applyFriction(frictionSlide);
					limitMovementSpeed(normalSpeedLimit);
				}
				else
				{
					applyFriction(frictionSlippery);
					moveMeSlide();
					limitMovementSpeed(slipperySpeedLimit);
				}
				AudioRes.playSoundLoop(29, getPosition());
				//System.out.println("number 1");
				/*
				MainGameLoop.gameEntitiesToAdd.add(new MyParticle(
						ParticleManager.particleSequenceDusts, 
							new Vector3f(getX()+2*(float)Math.random()-1f, 
								         getY()+2*(float)Math.random()-1f, 
								         getZ()+2*(float)Math.random()-1f), 
							4f, 0, 0, 0, 0, false));
							*/
				
				new Particle(ParticleResources.textureDust,
						new Vector3f(getX()+2*(float)Math.random()-1f,
						         getY()+2*(float)Math.random()-1f,
						         getZ()+2*(float)Math.random()-1f), 
						new Vector3f(0, 0, 0), 0, 30, 0, 4+(float)Math.random(), 0);
				
				/*
				MainGameLoop.gameEntitiesToAdd.add(new Particle(
						ParticleManager.particleSequenceDustCloud, 
							new Vector3f(getX()+2*(float)Math.random()-1f, 
								         getY()+2*(float)Math.random()-1f, 
								         getZ()+2*(float)Math.random()-1f), 
							4f, 0, 0, 0, 0, false));
							*/
							
			}
			else
			{
				AudioRes.stopSound(29);
				if(bonkTimer == 0)
				{
					if(isSwimming == false && triCol.isSlippery())
					{
						moveMeSlippery();
						applyFriction(frictionGroundSlippery);
					}
					else
					{
						applyFriction(frictionGround);
						moveMeGround();
					}
					limitMovementSpeed(normalSpeedLimit);
				}
			}
			calculateRotY();
			
			isHighJumping = false;
		    isLongJumping = false;
		    isSideFlipping = false;
		    canSideFlip = false;
		    isGroundPounding = false;
		    isDiving = false;
		    isRollingOut = false;
		    
		    if(bonkTimer == 0 && isSwimming == false && skidTimer == 0)
		    {
		    	checkSideFlip();
		    	if(Math.sqrt((xVel*xVel)+(zVel*zVel)) == 0)
		        {
		        	skidTimer = 0;
		            canSideFlip = false;
		        }
		    }
		    if(skidTimer > 0)
		    {
		    	//System.out.println("skidTimer = "+skidTimer);
		    	skidTimer-=1;
		        canSideFlip = true;
		        if(Math.sqrt((xVel*xVel)+(zVel*zVel)) == 0)
		        {
		        	skidTimer = 0;
		            canSideFlip = false;
		        }
		        //AudioRes.playSoundRepeat(12);
		        //System.out.println("skidTimer = "+skidTimer);
		    }
		    
		    if(isSwimming == false)
		    {
				if(triCol.getNormal().y < 0)
				{
					yVel = -Math.abs(yVel);
				}
				else
				{
					if(triCol.getNormal().y >= slopeWall)
					{
						pushUpValue = -super.getY()+(float)(   ((-triCol.getA()*(super.getX()+xVel))  +  
														(-triCol.getC()*(super.getZ()+zVel))  -  
														(triCol.getD())) / triCol.getB());
						yVel = pushUpValue;
						//System.out.println("pushupval = "+yVel);
					}
					else
					{
						yVel-=gravity;
			            if(yVel < -terminalVelocity)
			            {
			            	yVel = -terminalVelocity;
			            }
					}
				}
		    }
		    else
		    {
		    	//yVel+=0.03f;
		    	yVel = yVel*0.85f;
		    	if(Math.abs(yVel) < 0.01)
		    	{
		    		yVel = 0;
		    	}
		    }
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_J) || selectInput)
		{
			//yVel+=0.1;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_1))
		{
			super.setVisibility(false);
			firstPersonView = true;
			setLimbsVisibility(false);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_3))
		{
			super.setVisibility(true);
			firstPersonView = false;
			setLimbsVisibility(true);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_6))
		{
			//float xOff = 10*(float)Math.cos(Math.toRadians(currentCamera.getRoll()));
			//float zOff = -10*(float)Math.sin(Math.toRadians(currentCamera.getRoll()));
			//float yOff = -10*(float)Math.sin(Math.toRadians(currentCamera.getPitch()));
			float xOff = (float)(Math.cos(Math.toRadians(currentCamera.getYaw()+270+180))*(10*(Math.cos(Math.toRadians(currentCamera.getPitch())))));
			float zOff = (float)(Math.sin(Math.toRadians(currentCamera.getYaw()+270+180))*(10*(Math.cos(Math.toRadians(currentCamera.getPitch())))));
			float yOff = -(float)(Math.sin(Math.toRadians(currentCamera.getPitch()+180))*10);
			
			increasePosition(-xOff, -yOff, -zOff);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_5))
		{
			//float xOff = 10*(float)Math.cos(Math.toRadians(currentCamera.getRoll()));
			//float zOff = -10*(float)Math.sin(Math.toRadians(currentCamera.getRoll()));
			//float yOff = -10*(float)Math.sin(Math.toRadians(currentCamera.getPitch()));
			float xOff = (float)(Math.cos(Math.toRadians(currentCamera.getYaw()+270+180))*(10*(Math.cos(Math.toRadians(currentCamera.getPitch())))));
			float zOff = (float)(Math.sin(Math.toRadians(currentCamera.getYaw()+270+180))*(10*(Math.cos(Math.toRadians(currentCamera.getPitch())))));
			float yOff = -(float)(Math.sin(Math.toRadians(currentCamera.getPitch()+180))*10);
			
			increasePosition(xOff, yOff, zOff);
		}
		
		previousLoadInput = loadInput;
		loadInput = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_L))
		{
			loadInput = true;
		}
		previousRecordInput = recordInput;
		recordInput = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_R))
		{
			recordInput = true;
		}
		
		if(recordInput && !previousRecordInput)
		{
			try
			{
				float scale = 1;
	            File statText = new File("C:/Users/Anders/Desktop/Documents/My Cheat Tables/ScanStart.txt");
	            FileOutputStream is = new FileOutputStream(statText);
	            OutputStreamWriter osw = new OutputStreamWriter(is);    
	            Writer w = new BufferedWriter(osw);
	            System.out.println(""+getX()*scale+" "+getY()*scale+" "+getZ()*scale+" "+currentCamera.getYaw()+" "+currentCamera.getPitch());
	            w.write(""+getX()*scale+" "+getY()*scale+" "+getZ()*scale+" "+currentCamera.getYaw()+" "+currentCamera.getPitch());
	            w.close();
			} 
			catch (IOException e) 
			{
				System.out.println("Error");
			}
		}
		
		if(loadInput && !previousLoadInput)
		{
			MainGameLoop.liveScanPoints = new ArrayList<Entity>();
			try
			{
				File file = new File("C:/Users/Anders/Desktop/Documents/My Cheat Tables/DeathPoints.txt");
				Scanner input = new Scanner(file);
				while (input.hasNextLine()) 
				{
					input.nextInt();
					Ball newBall = new Ball(new Vector3f(input.nextFloat()/1f, input.nextFloat()/1f, input.nextFloat()/1f));
					input.nextLine();
					MainGameLoop.liveScanPoints.add(newBall);
	            }
	            input.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		headHeight = 9;
		float currHeadHeight = headHeight;
		squishAmount = 1;
		if(CollisionChecker.checkCollision(getPosition(), new Vector3f(getPosition().getX(), getPosition().getY()+headHeight, getPosition().getZ())))
		{
			squishAmount = (CollisionChecker.getCollidePosition().y-getY())/headHeight;
		}
		setLimbsScale(squishAmount);
		currHeadHeight = squishAmount*headHeight - 0.5f;
		//currHeadHeight = 0;
		/*
		if(isSwimming && firstPersonView)
		{
			newCameraPosition.set(new Vector3f(getPosition().getX(), getPosition().getY(), getPosition().getZ()));
		}
		else
		{
			newCameraPosition.set(new Vector3f(getPosition().getX(), getPosition().getY()+currHeadHeight, getPosition().getZ()));
		}
		
		currentCamera.setYaw(currentCamera.getYaw()+ (myCameraYawTarget-currentCamera.getYaw())/cameraLaziness);
		currentCamera.setPitch(currentCamera.getPitch()+ (myCameraPitchTarget-currentCamera.getPitch())/cameraLaziness);
		
		if(firstPersonView == false)
		{
			float radius = cameraRadius;
			
			newCameraPosition.x += (float)(Math.cos(Math.toRadians(currentCamera.getYaw()+270+180))*(radius*(Math.cos(Math.toRadians(currentCamera.getPitch())))));
			newCameraPosition.z += (float)(Math.sin(Math.toRadians(currentCamera.getYaw()+270+180))*(radius*(Math.cos(Math.toRadians(currentCamera.getPitch())))));
			newCameraPosition.y -= (float)(Math.sin(Math.toRadians(currentCamera.getPitch()+180))*radius);
			Vector3f norm = null;
			if(triCol == null)
			{
				norm = new Vector3f(0,-1,0);
			}
			else
			{
				norm = triCol.getNormal().negate(null);
			}
			Vector3f newcampos = Maths.mapInputs3((float)Math.toRadians(currentCamera.getYaw()), radius, norm);
			//newCameraPosition.set(newcampos);
			
			if(CollisionChecker.checkCollision(new Vector3f(getPosition().getX(), getPosition().getY()+currHeadHeight, getPosition().getZ()), newCameraPosition) == true)
			{
				newCameraPosition.set(CollisionChecker.getCollidePosition());
				
				Vector3f diff = new Vector3f();
				Vector3f.sub(newCameraPosition, new Vector3f(getX(), getY()+currHeadHeight, getZ()), diff);
				float newLength = diff.length()-3;
				
				Vector3f newDiff = new Vector3f();
				newDiff.x = (float)(Math.cos(Math.toRadians(currentCamera.getYaw()+270+180))*(newLength*(Math.cos(Math.toRadians(currentCamera.getPitch())))));
				newDiff.z = (float)(Math.sin(Math.toRadians(currentCamera.getYaw()+270+180))*(newLength*(Math.cos(Math.toRadians(currentCamera.getPitch())))));
				newDiff.y = -(float)(Math.sin(Math.toRadians(currentCamera.getPitch()+180))*newLength);
				
				newCameraPosition.set(new Vector3f(getX(), getY()+currHeadHeight, getZ()));
				Vector3f.add(new Vector3f(getX(), getY()+currHeadHeight, getZ()), newDiff, newCameraPosition);
			}
		}
		*/
		/* having a fixed camera
		newCameraPosition = new Vector3f(-91, 128, 1165); //fixed camera position
		float xDiff = (super.getX()-newCameraPosition.x);
		float zDiff = (super.getZ()-newCameraPosition.z);
		currentCamera.setYaw((float)Math.toDegrees(Math.atan2(xDiff, -zDiff)));
		currentCamera.setPitch(-(float)Math.toDegrees(Math.atan2((super.getY()-newCameraPosition.y), Math.sqrt(xDiff*xDiff+zDiff*zDiff))));
		myCameraYawTarget = currentCamera.getYaw();
		*/
		//currentCamera.setPosition(newCameraPosition);
		
		landTimerCurrent = Math.max(landTimerCurrent-1, 0);
		
		if(shoulderInput && !inAir && bonkTimer == 0 && !isSwimming && !isSliding)
		{
			xVel = xVel*0.85f;
	        zVel = zVel*0.85f;
		}
		
		if(shoulderInput && !previousShoulderInput && bonkTimer == 0 && !actionPreformed && !isSwimming)
		{
			if(inAir && !onPole)
			{
				if(!isLongJumping && !isDiving && !isGroundPounding && !isSliding && !isRollingOut)
				{
					isGroundPounding = true;
		            isHighJumping = false;
		            isSideFlipping = false;
		            groundPoundFrame = groundPoundFrameMax;
		            yVel = 3;
		            xVel = 0;
		            zVel = 0;
		            xVelAir = 0;
		            zVelAir = 0;
		            actionPreformed = true;
		            //AudioRes.playSound(10, 1, getPosition());
		            AudioRes.playSound(40, 1, getPosition());
				}
			}
		}
		
		if(jumpInput && !previousJumpInput && !actionPreformed && groundPoundRecoveryTimer == 0)
		{
			if(isSwimming == false)
			{
				if(onPole)
				{
					onPole = false;
					float currentDirection = getRotY();
					yVel = 1f;
					jumpNumber = 1;
					jumpFramesMax = 12;
					jumpFrameCurrent = jumpFramesMax;
                    xVel = (float)(Math.cos(Math.toRadians(currentDirection))*1.2);
                    zVel = (float)-(Math.sin(Math.toRadians(currentDirection))*1.2);
                    AudioRes.playSound(10, 1, getPosition());
                    increasePosition((float)(Math.cos(Math.toRadians(currentDirection))*7), 0, (float)-(Math.sin(Math.toRadians(currentDirection))*7));
				}
				else if(canWallKick && inAir)
				{
					bonkTimer = 0;
					yVel = 1.8f;
					xVel*=1.8;
					zVel*=1.8;
					canWallKick = false;
					//System.out.println("KICK");
					jumpNumber = 1;
					actionPreformed = true;
					int index = (int)(Math.random()*3);
	    			AudioRes.playSound(5+index, 1, super.getPosition());
				}
				else
				{
					if(!inAir && bonkTimer == 0)
					{
						timeSinceJump = 0;
						
						if(isSliding)
						{
							isSliding = false;
							isRollingOut = true;
							//xVel+= (Math.cos(Math.toRadians(super.getRotY()))*.50);
		                    //zVel+= -(Math.sin(Math.toRadians(super.getRotY()))*.50);
		                    //yVel+= 1;
		                    slideRollout();
		                    jumpNumber = 1;
		                    landTimerCurrent = 0;
		                    actionPreformed = true;
						}
						else
						{
							if(triCol.getNormal().y > slopeWall)
							{
								if(triCol.getNormal().y > slopeFooting)
								{
									float currentDirection = super.getRotY();
									float currentSpeed = (float)Math.sqrt(xVel*xVel+zVel*zVel);
									if(shoulderInput)
				                    {
				                        if(currentSpeed < 0.25)
				                        {
				                            isHighJumping = true;
				                            //old
				                            //yVel+= 2.0*triCol.getNormal().y*1.25;
				                            //xVel+= -(Math.cos(Math.toRadians(currentDirection))*.50);
				                            //zVel+= (Math.sin(Math.toRadians(currentDirection))*.50);
				                            
				                            //new
				                            yVel+= 2.0*1.35;
				                            xVel+= -(Math.cos(Math.toRadians(currentDirection))*.45);
				                            zVel+= (Math.sin(Math.toRadians(currentDirection))*.45);
				                            jumpNumber = 0;
				                            jumpFramesMax = 7;
				        					jumpFrameCurrent = jumpFramesMax;
				                            //initiateJump();
				                            actionPreformed = true;
				                            AudioRes.playSound(7, 1, super.getPosition());
				                        }
				                        else
				                        {
				                            isLongJumping = true;
				                            //old 
				                            //yVel+= 2.0*(triCol.getNormal().y)*1.1;
				                            //xVel+= (Math.cos(Math.toRadians(currentDirection))*.80);
				                            //zVel+= -(Math.sin(Math.toRadians(currentDirection))*.80);
				                            
				                            //new
				                            xVel+= (Math.cos(Math.toRadians(currentDirection))*0.95);
				                            zVel+= -(Math.sin(Math.toRadians(currentDirection))*0.95);
				                            yVel+= 2.2;
				                            jumpNumber = 0;
				                            jumpFramesMax = 6;
				        					jumpFrameCurrent = jumpFramesMax;
				                            //initiateJump();
				                            actionPreformed = true;
				                			AudioRes.playSound(9, 1, super.getPosition());
				                        }
				                    }
				                    else
				                    {
				                        if(canSideFlip)
				                        {
				                            isSideFlipping = true;
				                            yVel+= 2.0*triCol.getNormal().y*1.25;
				                            currentDirection = inputAngle;
				                            super.setRotY(currentDirection);
				                            xVel = (float)(Math.cos(Math.toRadians(currentDirection))*.950);
				                            zVel = -(float)(Math.sin(Math.toRadians(currentDirection))*.950);
				                            //initiateJump();
				                            jumpFramesMax = 8;
				        					jumpFrameCurrent = jumpFramesMax;
				                            jumpNumber = 1;
				                            actionPreformed = true;
				                            int index = (int)(Math.random()*3);
				                			AudioRes.playSound(5+index, 1, super.getPosition());
				                        }
				                        else
				                        {
				                            initiateJump();
				                            actionPreformed = true;
				                        }
				                    }
								}
								else
								{
									xVel+= 0.5*triCol.getNormal().x;
				                    zVel+= 0.5*triCol.getNormal().z;
				                    initiateJump();
				                    actionPreformed = true;
								}
							}
						}
					}
				}
			}
			else
			{
				modelSwimStrokeIndex = 6.99f;
				//yVel = 0.5f;
				AudioRes.playSound(15, 1, getPosition());
			}
		}
		
		
		if(actionInput && !previousActionInput && bonkTimer == 0 && !actionPreformed && groundPoundRecoveryTimer == 0)
		{
			if(!isSwimming)
			{
				timeSinceAction = 0;
				
				if(inAir)
				{
					if(!isDiving && !isHighJumping && !isLongJumping && !isGroundPounding && !isRollingOut && !isSliding && !onPole)
					{
						isDiving = true;
						isSliding = true;
						initiateDive();
						jumpNumber = 0;
						actionPreformed = true;
					}
				}
				else
				{
					if(!isSliding)
					{
						isSliding = true;
						initiateSlide();
						actionPreformed = true;
					}
				}
			}
			else
			{
				//yVel = -0.5f;
				modelSwimStrokeIndex = 6.99f;
				AudioRes.playSound(15, 1, getPosition());
			}
		}
		
		if(actionInput)
		{
			if(isSwimming)
			{
				yVel-=0.1;
			}
		}
		if(jumpInput)
		{
			if(isSwimming && canSwimUp)
			{
				yVel+=0.175;//old 0.1
			}
		}
		
		
		onWall = false;
		inAirPrevious = inAir;
		//System.out.println("");
		if(CollisionChecker.checkCollision(super.getX(), super.getY(), super.getZ(), super.getX()+xVel+xVelAir+xDisp, super.getY()+yVel+yDisp, super.getZ()+zVel+zVelAir+zDisp) == false)
		{
			super.increasePosition(xVel+xVelAir+xDisp, yVel+yDisp, zVel+zVelAir+zDisp);
			passedCheck1 = true;
			
			if(CollisionChecker.checkCollision(super.getX(), super.getY(), super.getZ(), super.getX(), super.getY()-surfaceTension, super.getZ()) == false)
			{
				passedCheck2 = true;
				inAir = true;
			}
			else
			{
				passedCheck2 = false;
				inAir = false;
				
				if(isGroundPounding)
				{
					groundPoundRecoveryTimer = 14;
					hitGroundPound();
				}
				
				triCol = CollisionChecker.getCollideTriangle();
				triColPosition = CollisionChecker.getCollidePosition();
				
				float colXNormal = triCol.getNormal().x;
				float colYNormal = triCol.getNormal().y;
				float colZNormal = triCol.getNormal().z;
				
				if(colYNormal >= slopeFooting && !triCol.isSlippery())
				{
					super.setX(triColPosition.x);
					super.setY(triColPosition.y+colYNormal*wallHug);
					super.setZ(triColPosition.z);
				}
				else
				{
					//|| triCol.isSlippery()
					if(colYNormal >= slopeWall)
					{
						super.setX(triColPosition.x);
						super.setY(triColPosition.y+colYNormal*wallHug);
						super.setZ(triColPosition.z);
						xVel+=colXNormal*slopeSlipRate;
						zVel+=colZNormal*slopeSlipRate;
					}
					else
					{
						float mySpeed = (float)Math.sqrt(xVel*xVel+zVel*zVel)+(float)Math.sqrt(xVelAir*xVelAir+zVelAir*zVelAir);
						if((mySpeed > 0.95 && bonkTimer == 0) || isDiving || isSliding || isRollingOut || isHighJumping || isLongJumping || isSideFlipping)
						{
							float currentDirection = super.getRotY();
							float planeDirection = calculateAngleDegrees(colXNormal, colZNormal);
							if (planeDirection < 0)
							{
								planeDirection+=360;
							}
							float newDirection = planeDirection+(planeDirection-(currentDirection+180));
							//float newDirection = 0.0f;
							xVel = (float)(0.6*Math.cos(Math.toRadians(newDirection)));//currentDirection+180)));
							zVel = (float)(-0.6*Math.sin(Math.toRadians(newDirection)));//currentDirection+180)));
							bonkTimer = bonkTimerMax;
							if(!isSwimming)
							{
								AudioRes.playSound(11, 1, getPosition());
							}
							jumpNumber = 0;
							if(inAir && !isDiving && !isSliding)
							{
								//System.out.println("planeDir = "+planeDirection + "    curDir = "+currentDirection);
								if(Math.abs(planeDirection-(currentDirection+180)) <= 50 || Math.abs(planeDirection-(currentDirection-180)) <= 50)//original 45
								{
									canWallKick = true;
									wallKickTimer = wallKickTimerMax;
									//System.out.println("canWallKick");
								}
								else
								{
									System.out.println("							BAD ANGLE");
								}
							}
						}
						else
						{
							onWall = true;
							super.setX(triColPosition.x+colXNormal*wallHug);
							super.setY(triColPosition.y+colYNormal*wallHug);
							super.setZ(triColPosition.z+colZNormal*wallHug);
							xVel = 0;
							zVel = 0;
						}
					}
				}
			}
		}
		else
		{
			triCol = CollisionChecker.getCollideTriangle();
			triColPosition = CollisionChecker.getCollidePosition();
			
			float colXNormal = triCol.getNormal().x;
			float colYNormal = triCol.getNormal().y;
			float colZNormal = triCol.getNormal().z;
			
			if(isGroundPounding)
			{
				groundPoundRecoveryTimer = 14;
				hitGroundPound();
			}
			
			//System.out.println("nX = "+colXNormal+"   nY = "+colYNormal+"   nZ = "+colZNormal);
			
			if(colYNormal >= slopeFooting && !triCol.isSlippery())
			{
				super.setX(triColPosition.x);
				super.setY(triColPosition.y+colYNormal*wallHug);
				super.setZ(triColPosition.z);
			}
			else
			{
				//|| triCol.isSlippery()
				if(colYNormal >= slopeWall)
				{
					super.setX(triColPosition.x);
					super.setY(triColPosition.y+colYNormal*wallHug);
					super.setZ(triColPosition.z);
					xVel+=colXNormal*slopeSlipRate;
					zVel+=colZNormal*slopeSlipRate;
				}
				else
				{
					float mySpeed = (float)Math.sqrt(xVel*xVel+zVel*zVel)+(float)Math.sqrt(xVelAir*xVelAir+zVelAir*zVelAir);
					if((mySpeed > 0.95 && bonkTimer == 0) || isDiving || isSliding || isRollingOut || isHighJumping || isLongJumping || isSideFlipping || (inAir && mySpeed > 0.5))
					{
						float currentDirection = super.getRotY();
						float planeDirection = calculateAngleDegrees(colXNormal, colZNormal);
						if (planeDirection < 0)
						{
							planeDirection+=360;
						}
						float newDirection = planeDirection+(planeDirection-(currentDirection+180));
						
						xVel = (float)(0.6*Math.cos(Math.toRadians(newDirection)));//currentDirection+180)));
						zVel = (float)(-0.6*Math.sin(Math.toRadians(newDirection)));//currentDirection+180)));
						bonkTimer = bonkTimerMax;
						if(!isSwimming)
						{
							AudioRes.playSound(11, 1, getPosition());
						}
						jumpNumber = 0;
						if(inAir && !isDiving && !isSliding)
						{
							//System.out.println("planeDir = "+planeDirection + "    curDir = "+currentDirection);
							if(Math.abs(planeDirection-(currentDirection+180)) <= 50 || Math.abs(planeDirection-(currentDirection-180)) <= 50) //original 45
							{
								canWallKick = true;
								wallKickTimer = wallKickTimerMax;
								//System.out.println("canWallKick");
							}
							else
							{
								System.out.println("							BAD ANGLE");
							}
						}
					}
					else
					{
						onWall = true;
						super.setX(triColPosition.x+colXNormal*wallHug);
						super.setY(triColPosition.y+colYNormal*wallHug);
						super.setZ(triColPosition.z+colZNormal*wallHug);
						xVel = 0;
						zVel = 0;
					}
				}
			}
			
			passedCheck1 = false;
			passedCheck2 = false;
			inAir = false;
		}
		
		xDisp = 0;
		yDisp = 0;
		zDisp = 0;
		
		//animation block
		
		float mySpeed = (float)Math.sqrt(xVel*xVel+zVel*zVel);
		
		if(Math.abs(mySpeed) <= 0.05)
		{
			isSliding = false;
		}
		
		float modelIncreaseVal = (mySpeed)*0.3f;
		
		if((modelRunIndex < 10 && modelRunIndex+modelIncreaseVal > 10 ||
		   modelRunIndex < 20 && modelRunIndex+modelIncreaseVal > 20) &&
		   isRunning == true)
		{
			if(getY() < -0.2)
			{
				AudioRes.playSound(37, 1+mySpeed*0.1f, getPosition());
			}
			else
			{
				AudioRes.playSound(triCol.getStepSound(), 0.8f+mySpeed*0.1f+(float)Math.random()*0.4f, getPosition());
				//AudioSources.setPosition(0, getPosition());
				//AudioSources.play(0);
				//AudioSources.play(0, getPosition());
			}
			
			switch(triCol.getParticle())
			{
				case 0: 
					break;
				
				case 1:
					for(int i = 10; i != 0; i--)
					{
						/*
						MainGameLoop.gameEntitiesToAdd.add(new MyParticle(
							ParticleManager.modelParticleSnowball, 
							new Vector3f(super.getX(), super.getY(), super.getZ()), 
							20, (float)Math.random()+0.6f,
							    (float)Math.random()-0.5f,
							    (float)(Math.random()*0.5+0.5f),
							    (float)Math.random()-0.5f, 0.1f, 0, false));
						*/
						new Particle(ParticleResources.textureSnowball, new Vector3f(getPosition()), 
								new Vector3f((float)Math.random()-0.5f, 
										     (float)(Math.random()*0.5+0.5f), 
										     (float)Math.random()-0.5f), 
								0.1f, 20, 0, (float)Math.random()+0.6f, 0);
					}
					break;
					
				case 2:
					for(int i = 10; i != 0; i--)
					{
						new Particle(ParticleResources.textureWaterDrop, 
								new Vector3f(getX(), getY()+2, getZ()), 
								new Vector3f((float)Math.random()-0.5f, 
										     (float)(Math.random()*0.5+0.5f), 
										     (float)Math.random()-0.5f), 
								0.1f, 20, 0, (float)Math.random()+0.6f, 0);
					}
				
				default: 
					break;
			}
		}
		modelRunIndex+=modelIncreaseVal;
		if(modelRunIndex >= 20)
		{
			modelRunIndex=modelRunIndex % 20;
		}
		if(mySpeed == 0)
		{
			modelRunIndex = 0;
		}
		
		mySpeed = (float)Math.sqrt(xVel*xVel+yVel*yVel+zVel*zVel);
		float swimIncreaseVal = (mySpeed)*0.8f;
		
		modelSwimIndex+=swimIncreaseVal;
		if(modelSwimIndex >= 12)
		{
			modelSwimIndex=modelSwimIndex % 12;
			
			if(isSwimming)
			{
				MainGameLoop.gameEntitiesToAdd.add(new MyParticle(
						ParticleManager.modelParticleBubble, 
						new Vector3f(super.getX(), super.getY(), super.getZ()), 
						60, 2, 0, 0.3f, 0, 0.0f, 1, false));
			}
		}
		if(mySpeed == 0)
		{
			modelSwimIndex = 0;
		}
		
		modelSwimStrokeIndex -= 0.3f;
		
		isGettingExternallyMoved = false;
		
		if(iFrame%6 < 3 && !firstPersonView)
		{
			setLimbsVisibility(true);
		}
		else
		{
			setLimbsVisibility(false);
		}
		decideModel();
		super.increasePosition(displayOffset.getX(), displayOffset.getY(), displayOffset.getZ());
	}
	
	private void moveMeGround()
	{
		xVel += (float)(moveSpeedCurrent*Math.cos(Math.toRadians(myCameraYawTarget+movementAngle)));
		zVel += (float)(moveSpeedCurrent*Math.sin(Math.toRadians(myCameraYawTarget+movementAngle)));
	}
	
	private void moveMeSlide()
	{
		xVel += (float)0.2*(moveSpeedCurrent*Math.cos(Math.toRadians(myCameraYawTarget+movementAngle)));//0.2
		zVel += (float)0.2*(moveSpeedCurrent*Math.sin(Math.toRadians(myCameraYawTarget+movementAngle)));//0.2
	}
	
	private void moveMeSlippery()
	{
		xVel += (float)0.5*(moveSpeedCurrent*Math.cos(Math.toRadians(myCameraYawTarget+movementAngle)));
		zVel += (float)0.5*(moveSpeedCurrent*Math.sin(Math.toRadians(myCameraYawTarget+movementAngle)));
	}
	
	private void moveMeAir()
	{
		xVelAir += (float)(moveSpeedAirCurrent*Math.cos(Math.toRadians(myCameraYawTarget+movementAngle)));
		zVelAir += (float)(moveSpeedAirCurrent*Math.sin(Math.toRadians(myCameraYawTarget+movementAngle)));
	}
	
	private void initiateJump()
	{
		float currentSpeed = (float)Math.sqrt((xVel*xVel)+(zVel*zVel));
		
		if(landTimerCurrent > 0)
		{
			jumpNumber+=1;
			if(jumpNumber == 3 && currentSpeed < tripleJumpSpeed)
			{
				jumpNumber = 1;
			}
		}
		else
		{
			jumpNumber = 1;
		}
		
		if(jumpNumber == 4)
		{
			jumpNumber = 1;
		}
		
		if(jumpNumber == 1 || jumpNumber == 0)
		{
			yVel = 0.7f; //0.055f  old
			jumpFramesMax = 13;//originally 12
			jumpFrameCurrent = jumpFramesMax;
			super.setY(super.getY()+1);
			int index = (int)(Math.random()*3);
			AudioRes.playSound(5+index, 1, super.getPosition());
		}
		else
		{
			if(jumpNumber == 2)
			{
				yVel = 1f;
				jumpFramesMax = 13;
				jumpFrameCurrent = jumpFramesMax;
				super.setY(super.getY()+1);
				AudioRes.playSound(8, 1, super.getPosition());
			}
			else
			{
				if(jumpNumber == 3)
				{
					yVel = 1.4f;
					jumpFramesMax = 13;
					jumpFrameCurrent = jumpFramesMax;
					super.setY(super.getY()+1);
					AudioRes.playSound(9, 1, super.getPosition());
				}
			}
		}
		
		yVel+=currentSpeed*0.1f;//Faster you move, higher you jump
	}
	
	private void applyFriction(float frictionToApply)
	{
		float mag = (float)Math.sqrt((xVel*xVel)+(zVel*zVel));
		if (mag!=0)
		{
		    int before = Maths.sign(zVel);
		    zVel-=((frictionToApply)*(zVel))/(mag);
		    int after = Maths.sign(zVel);
		    if(before != after)
		    {
		        zVel = 0;
		    }
		    before = Maths.sign(xVel);
		    xVel-=((frictionToApply)*(xVel))/(mag);
		    after = Maths.sign(xVel);
		    if(before != after)
		    {
		        xVel = 0;
		    }
		}
	}
	
	private void applyFrictionAir()
	{
		float mag = (float)Math.sqrt((xVel*xVel)+(zVel*zVel));
		if(mag!=0)
		{
		    int before = Maths.sign(zVel);
		    zVel-=((frictionAir)*(zVel))/(mag);
		    int after = Maths.sign(zVel);
		    if(before != after)
		    {
		        zVel = 0;
		    }
		    before = Maths.sign(xVel);
		    xVel-=((frictionAir)*(xVel))/(mag);
		    after = Maths.sign(xVel);
		    if(before != after)
		    {
		        xVel = 0;
		    }
		}
		
		mag = (float)Math.sqrt((xVelAir*xVelAir)+(zVelAir*zVelAir));
		if(mag!=0)
		{
		    int before = Maths.sign(zVelAir);
		    zVelAir-=((frictionAir)*(zVelAir))/(mag);
		    int after = Maths.sign(zVelAir);
		    if(before != after)
		    {
		    	zVelAir = 0;
		    }
		    before = Maths.sign(xVelAir);
		    xVelAir-=((frictionAir)*(xVelAir))/(mag);
		    after = Maths.sign(xVelAir);
		    if(before != after)
		    {
		    	xVelAir = 0;
		    }
		}
	}
	
	public void calculateRotY()
	{
		if((zVel!=0) && (xVel!=0))
		{
		    super.setRotY((float)Math.toDegrees(Math.atan2(-1*zVel,xVel)));
		}   
		else
		{
		    if(zVel!=0)
		    {
		        super.setRotY((180+(90*Maths.sign(zVel))));
		    }
		    if(xVel!=0)
		    {
		        super.setRotY((90-(90*Maths.sign(xVel))));
		    }
		}
	}
	
	private float calculateAngleDegrees(float xLength, float yLength)
	{
		if((yLength!=0) && (xLength!=0))
		{
		    return ((float)Math.toDegrees(Math.atan2(-1*yLength,xLength)));
		}   
		else
		{
		    if(yLength!=0)
		    {
		        return (float)((180+(90*Maths.sign(yLength))));
		    }
		    else
		    {
	        	return (float)((90-(90*Maths.sign(xLength))));
		    }
		}
	}
	
	private void limitMovementSpeedOLD()
	{
		float myspeed = (float)Math.sqrt(xVel*xVel+zVel*zVel);
		if(myspeed > moveSpeedMax)
		{
			xVel = xVel*((myspeed-slowDownRate)/(myspeed));
			zVel = zVel*((myspeed-slowDownRate)/(myspeed));
		}
	}
	
	private void limitMovementSpeed(float limit)
	{
		float myspeed = (float)Math.sqrt(xVel*xVel+zVel*zVel);
		if(myspeed > limit)
		{
			xVel = xVel*((myspeed-slowDownRate)/(myspeed));
			zVel = zVel*((myspeed-slowDownRate)/(myspeed));
		}
	}
	
	private void limitMovementSpeedAir()
	{
		float myspeed = (float)Math.sqrt(xVelAir*xVelAir+zVelAir*zVelAir);
		if(myspeed > moveSpeedAirMax)
		{
			xVelAir = xVelAir*((myspeed-slowDownAirRate)/(myspeed));
			zVelAir = zVelAir*((myspeed-slowDownAirRate)/(myspeed));
		}
	}
	
	//uses the ground slow down rate
	private void limitMovementSpeedAir(float limit)
	{
		float myspeed = (float)Math.sqrt(xVelAir*xVelAir+zVelAir*zVelAir);
		if(myspeed > limit)
		{
			xVelAir = xVelAir*((myspeed-slowDownRate)/(myspeed));
			zVelAir = zVelAir*((myspeed-slowDownRate)/(myspeed));
		}
	}
	
	private void setMovementInputs()
	{
		previousJumpInput = jumpInput;
		jumpInput = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) || (Joystick.joystickExists() && Joystick.getIsButtonPressed(Joystick.BUTTON_A)))
		{
			jumpInput = true;
		}
		
		previousActionInput = actionInput;
		actionInput = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_C) || (Joystick.joystickExists() && Joystick.getIsButtonPressed(Joystick.BUTTON_X)))
		{
			actionInput = true;
		}
		
		previousShoulderInput = shoulderInput;
		shoulderInput = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_X) || (Joystick.joystickExists() && Joystick.getIsButtonPressed(Joystick.BUTTON_LB)))
		{
			shoulderInput = true;
		}
		
		previousSelectInput = selectInput;
		selectInput = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_TAB) || (Joystick.joystickExists() && Joystick.getIsButtonPressed(Joystick.BUTTON_SELECT)))
		{
			selectInput = true;
		}
		
		movementInputX = 0;
		movementInputY = 0;
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			movementInputY = -1;
		}
		else
		{
			if(Keyboard.isKeyDown(Keyboard.KEY_S))
			{
				movementInputY = 1;
			}
			else
			{
				movementInputY = 0;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			movementInputX = -1;
		}
		else
		{
			if(Keyboard.isKeyDown(Keyboard.KEY_D))
			{
				movementInputX = 1;
			}
			else
			{
				movementInputX = 0;
			}
		}
		
		if(Math.abs(movementInputX*movementInputY) == 1)
		{
			movementInputX = (float)(movementInputX*0.70710678118);
			movementInputY = (float)(movementInputY*0.70710678118);
		}
		
		if((Joystick.joystickExists() && Joystick.getXLeft() != 0) || (Joystick.joystickExists() && Joystick.getYLeft() != 0))
		{
			movementInputX = Joystick.getXLeft();
			movementInputY = Joystick.getYLeft();
		}
		
		cameraInputY = 0;
		cameraInputX = 0;
		if(MainGameLoop.freeMouse == false)
		{
			cameraInputY = -0.25f*(Mouse.getY()-mousePreviousY);
			cameraInputX = 0.25f*(Mouse.getX()-mousePreviousX);
			Mouse.setCursorPosition(DisplayManager.getWidth()/2, DisplayManager.getHeight()/2);
			mousePreviousX = Mouse.getX();
			mousePreviousY = Mouse.getY();
		}
		
		if((Joystick.joystickExists() && Joystick.getXRight() != 0) || (Joystick.joystickExists() && Joystick.getYRight() != 0))
		{
			//if(Joystick.getXLeft() != 0
			System.out.println(Joystick.getXRight()+""+Joystick.getYRight());
			cameraInputY = 2.5f*(Joystick.getYRight());
			cameraInputX = 2.5f*(Joystick.getXRight());
		}
		
		if(Mouse.hasWheel())
		{
			zoomInput = Mouse.getDWheel()/10;
		}
		if(Joystick.joystickExists())
		{
			zoomInput+=Joystick.getDPadY();
		}
		
		moveSpeedCurrent = moveAcceleration*(float)Math.sqrt(movementInputX*movementInputX + movementInputY*movementInputY);
		moveSpeedAirCurrent = moveAccelerationAir*(float)Math.sqrt(movementInputX*movementInputX + movementInputY*movementInputY);
		movementAngle = (float)Math.toDegrees(Math.atan2(movementInputY, movementInputX));
		//System.out.println(Math.sqrt(movementInputX*movementInputX + movementInputY*movementInputY));
		if(inCutScene == true)
		{
			movementInputX = 0;
			movementInputY = 0;
			cameraInputX = 0;
			cameraInputY = 0;
			moveSpeedCurrent = 0;
			moveSpeedAirCurrent = 0;
			zoomInput = 0;
			jumpInput = false;
			shoulderInput = false;
			actionInput = false;
		}
		if(isGroundPounding || groundPoundRecoveryTimer > 0)
		{
			movementInputX = 0;
			movementInputY = 0;
			moveSpeedCurrent = 0;
			moveSpeedAirCurrent = 0;
			jumpInput = false;
			shoulderInput = false;
			actionInput = false;
		}
	}
	
	private void checkSideFlip()
	{
		float degAngle = movementAngle;
		
		degAngle=(-degAngle-myCameraYawTarget) % 360;

		if(degAngle < 0)
		{
			degAngle+=360;
		}
		
		inputAngle = degAngle;

		float myAngle = super.getRotY();//mod 360;

		if (myAngle < 0)
		{
			myAngle+=360;
		}
		//System.out.println("degAngle = "+degAngle+"   myAngle = "+myAngle);

		if((Math.abs(myAngle-degAngle) <= 220) && Math.abs(myAngle-degAngle) >= 140) //original 220 and 140
		{
			if(movementInputX != 0 || movementInputY != 0)
			{
				skidTimer = skidTimerMax;
			}
		}
	}
	
	private void initiateDive()
	{
		float currentDirection = super.getRotY();
		xVel+= (0.5*Math.cos(Math.toRadians(currentDirection)));
		zVel-= (0.5*Math.sin(Math.toRadians(currentDirection)));
		yVel = yVel*1.25f;
		AudioRes.playSound(8, 1, super.getPosition());
	}
	
	private void initiateSlide()
	{
		float currentDirection = super.getRotY();
		xVel+= (0.6*Math.cos(Math.toRadians(currentDirection)));
		zVel-= (0.6*Math.sin(Math.toRadians(currentDirection)));
		yVel+=1.5f;
		super.setY(super.getY()+0.5f);
		int index = (int)(Math.random()*3);
		AudioRes.playSound(5+index, 1, super.getPosition());
	}
	
	private void slideRollout()
	{
		float currentDirection = super.getRotY();
		xVel+= (0.25*Math.cos(Math.toRadians(currentDirection)));
		zVel-= (0.25*Math.sin(Math.toRadians(currentDirection)));
		yVel+=1.5f;
		super.setY(super.getY()+0.5f);
		int index = (int)(Math.random()*3);
		AudioRes.playSound(5+index, 1, super.getPosition());
		//jumpNumber = 1;
	}
	
	private TexturedModel[] loadModel(String fileLocation, String fileName, Loader loader)
	{
		/*
		RawModel[] tempRawModels = OBJFileLoader.loadOBJ(fileLocation, fileName, loader);
		ModelTexture[] tempModelTextures = OBJFileLoader.getModelTexturesCopy();
		TexturedModel[] tm = new TexturedModel[tempRawModels.length];
		for(int i = 0; i < tm.length; i++)
        {
			tm[i] = new TexturedModel(tempRawModels[i], tempModelTextures[i]);
        }
		return tm;
		*/
		
		return ConvenientMethods.loadModel(fileLocation, fileName);
	}
	
	private void decideModel()
	{
		super.setVisibility(false);
		myBody.setBaseOrientation(this.getPosition(), this.getRotY(), this.getRotZ());
		
		if(!inAir)
		{
			Vector3f currNorm = triCol.normal;
			float ang = (float)Math.atan2(Ball.relativeZVel, Ball.relativeXVel);
			Vector3f ans = Maths.mapInputs3(ang, 10, currNorm.negate(null));
			float yrot = (float)Math.toDegrees(Math.atan2(-ans.z, ans.x));
			float dist = (float)(Math.sqrt(ans.x*ans.x+ans.z*ans.z));
			float zrot = (float)Math.toDegrees(Math.atan2(ans.y, dist));
			myBody.setBaseOrientation(getPosition(), yrot, zrot);
			xVel = Ball.relativeXVel;
			zVel = Ball.relativeZVel;
			yVel = 0;
		}
		//myBody.update(modelRunIndex*10);
		isRunning = false;
		previousModel = super.getModels();
		if(isSwimming)
		{
			if(modelSwimStrokeIndex > 0)
			{
				displayOffset.setY(-1f);
				float mySpeed = (float)Math.sqrt(xVel*xVel+zVel*zVel);
	            super.setRotZ((float)Math.toDegrees(Math.atan2(yVel, mySpeed)));
	            if(mySpeed == 0 && yVel == 0)
	            {
	            	//super.setRotZ(0);
	            }
	            
	            float time = 100-modelSwimStrokeIndex*14.2f;
            	updateLimbs(16, time);
			}
			else
			{
				displayOffset.setY(-1f);
	            //super.setRotY(0);
	            //super.setRotX((float)(10*Math.sin(Math.toRadians(30*modelSwimIndex-180))));
	            float mySpeed = (float)Math.sqrt(xVel*xVel+zVel*zVel);
	            super.setRotZ((float)Math.toDegrees(Math.atan2(yVel, mySpeed)));
	            if(mySpeed == 0 && yVel == 0)
	            {
	            	//super.setRotZ(0);
	            }
	            
	            float time = modelSwimIndex*8.33333f;
            	updateLimbs(15, time);
			}
		}
		else
		{
			if(starGrabTimer > 0)
			{
				displayOffset.setY(-1.0f);
	            super.setRotZ(0);
	            super.setModels(modelPose);
	            //updateLimbs(14, 0);
	            setLimbsVisibility(false);
	    		setVisibility(true);
			}
			else
			{
			    if(inAir)
			    {
			        if(isDiving)
			        {
			            displayOffset.setY(-1.0f+5.5f);
			            //super.setRotZ(0);
			            float mySpeed = (float)Math.sqrt(Math.pow(xVel+xVelAir, 2)+Math.pow(zVel+zVelAir, 2));
			            super.setModels(modelDive);
			            
			            myBody.setBaseRotZ((float)Math.toDegrees(Math.atan2(yVel, mySpeed))-90);
			            float time = timeSinceAction*3f;
	                	updateLimbs(6, time);
			        }
			        else
			        {
			            if(isHighJumping)
			            {
			                displayOffset.setY(-1+4.0f);
			                super.setRotZ(modelStandIndex*8);
				            super.setModels(modelHighJump);
				            
				            //float time = 10 * modelRunIndex * 0.5f;
		                	updateLimbs(13, 0);
			            }
			            else
			            {
			                if(isLongJumping)
			                {
			                    displayOffset.setY(-1);
				                super.setRotZ(0);
					            super.setModels(modelLongJump);
					            
					            float time = timeSinceJump*2.75f;
			                	updateLimbs(10, time);
			                }
			                else
			                {
			                    if(isSideFlipping)
			                    {
			                        displayOffset.setY(-1+4);
					                //super.setRotZ(-modelStandIndex*8);
						            super.setModels(modelSideFlip);
						            
						            super.setRotZ(0);
	    			            	
	    			            	float time = timeSinceJump*2.75f;
				                	updateLimbs(5, time);
			                    }
			                    else
			                    {
			                        if(isGroundPounding)
			                        {
			                            displayOffset.setY(6);
			                            if(groundPoundFrame < groundPoundFrameMax-17) 
			                            {
			                            	super.setRotZ(0);
			                            }
			                            else
			                            {
			                            	super.setRotZ(360-20*(groundPoundFrameMax-groundPoundFrame));
			                            }
			                            
			    			            super.setModels(modelGroundPound);
			    			            
			    			            updateLimbs(9, 0);
			                        }
			                        else
			                        {
			                        	if(isSliding)
			                        	{
			                        		//displayOffset.setY(-4.0f+5.5f);
			                        		displayOffset.setY(1f);
			                	            //super.setRotZ(0);
			                	            //super.setModels(modelDive);
			                	            float mySpeed = (float)Math.sqrt(Math.pow(xVel+xVelAir, 2)+Math.pow(zVel+zVelAir, 2));
			        			            //super.setRotZ((float)Math.toDegrees(Math.atan2(yVel, mySpeed))-90);
			        			            super.setModels(modelDive);
			        			            
			        			            float time = timeSinceAction*3f;
			        			            myBody.setBaseRotZ((float)Math.toDegrees(Math.atan2(yVel, mySpeed))-90);
			        	                	updateLimbs(6, time);
			                        	}
			                        	else
			                        	{
			                        		if(isRollingOut)
			                        		{
			                        			displayOffset.setY(-1);
					    		                super.setRotZ(0);
					    			            super.setModels(modelRollOut);
					    			            updateLimbs(7, 0);
			                        		}
			                        		else
			                        		{
			                        			if(bonkTimer > 0)
		                    					{
			                        				displayOffset.setY(-4);
						    		                super.setRotZ(0);
						    			            super.setModels(modelBonk);
						    			            
						    			            updateLimbs(11, 0);
		                    					}
			                        			else
			                        			{
				                        			if(jumpNumber == 1 || jumpNumber == 0)
				                        			{
				                        				displayOffset.setY(-1);
				                        				super.setRotZ(0);
						    			            	super.setModels(modelJump);
						    			            	
						    			            	float time = timeSinceJump*6f;
						    			            	
						    			            	displayOffset.setY(-1+4.0f);
						    			                super.setRotZ(modelStandIndex*8);
						    			                
									                	updateLimbs(2, time);
				                        			}
				                        			else
				                        			{
				                        				if(jumpNumber == 2)
					                        			{
					                        				displayOffset.setY(4);
					                        				super.setRotZ(0);
							    			            	super.setModels(modelHighJump);
							    			            	
							    			            	float time = timeSinceJump*2.1f;
										                	updateLimbs(3, time);
					                        			}
				                        				else
				                        				{
				                        					displayOffset.setY(0);
				                        					//super.setRotZ(-modelStandIndex*8);
				                        					super.setRotZ(0);
							    			            	super.setModels(modelGroundPound);
							    			            	
							    			            	float time = timeSinceJump*1.6f;
							    			            	//float time = timeSinceJump*0.1f;
										                	updateLimbs(4, time);
										                	//System.out.println("yrot = "+myLeftHumerus.getRotY());
										                	//System.out.println("zrot = "+myLeftHumerus.getRotZ());
				                        				}
				                        			}
			                        			}
			                        		}
			                        	}
			                        }
			                    }
			                }
			            }
			        }
			    }
			    else
			    {
			        if(Math.sqrt((xVel*xVel)+(zVel*zVel)) != 0)
			        {
			        	if(bonkTimer > 0)
						{
		    				displayOffset.setY(-4);
			                super.setRotZ(0);
				            super.setModels(modelBonk);
				            
				            updateLimbs(11, 0);
						}
			        	else
			        	{
				        	if(isSliding)
			            	{
			            		displayOffset.setY(-5.0f+5.5f);
			    	            super.setRotZ(-90);
			    	            super.setModels(modelDive);
        			            
        			            float time = timeSinceAction*3f;
        	                	updateLimbs(6, time);
			            	}
				        	else
				        	{
					            if(shoulderInput)
					            {
					                displayOffset.setY(-1);
					                super.setRotZ(0);
						            super.setModels(modelCrouch);
						            
						            updateLimbs(12, 0); //with walking amonation
						            //updateLimbs(17, 0);
					            }
					            else
					            {
					                if(canSideFlip)
					                {
					                    displayOffset.setY(-1);
						                super.setRotZ(0);
							            super.setModels(modelSkid);
							            float time = (skidTimerMax-skidTimer)*15;
							            updateLimbs(8, time);
					                }
					                else
					                {
					                	isRunning = true;
					                	float time = 10 * modelRunIndex * 0.5f;
					                	updateLimbs(1, time);
					                	
					                	displayOffset.setY(-1);
			    		                super.setRotZ(0);
					                }
					            }
				        	}
			        	}
			        }
			        else
			        {
			        	if(groundPoundRecoveryTimer > 0)
			        	{
			        		 displayOffset.setY(-6);
                            //if(groundPoundFrame < groundPoundFrameMax-17) 
                            //{
                            	super.setRotZ(0);
                            //}
                            //else
                            //{
                            	//super.setRotZ(360-20*(groundPoundFrameMax-groundPoundFrame));
                            //}
                            
    			            //super.setModels(modelGroundPound);
    			            
    			            updateLimbs(9, 0);
			        	}
			        	else
			        	{
				            if(shoulderInput)
				            {
				                //sc_gmmod_draw_multiple_ext(ob_mo_mario_crouch.id,x,y,z-1,0.5,0,mydirection);break;
				                displayOffset.setY(-1);
				                super.setRotZ(0);
					            super.setModels(modelCrouch);
					            
					            updateLimbs(12, 0);
					            //updateLimbs(17, 0);
				            }
				            else
				            {
				                if(canSideFlip)
				                {
				                    //sc_gmmod_draw_multiple_ext(ob_mo_mario_skid.id,x,y,z-1,0.5,0,mydirection);
				                    displayOffset.setY(-1);
					                super.setRotZ(0);
						            super.setModels(modelSkid);
						            
						            float time = (skidTimerMax-skidTimer)*15;
						            updateLimbs(8, time);
				                }
				                else
				                {
				                	float time = modelStandIndex % 100;
				                	updateLimbs(0, time);
				                	
				                	displayOffset.setY(-1);
		    		                super.setRotZ(0);
				                }
				            }
			        	}
			        }
			    }
			}
		}
	}
	
	
	
	public Vector3f getViewPosition()
	{
		return cameraPosition;
	}

	public float getxVel() 
	{
		return xVel;
	}

	public void setxVel(float xVel) 
	{
		this.xVel = xVel;
	}

	public float getyVel() 
	{
		return yVel;
	}

	public void setyVel(float yVel) 
	{
		this.yVel = yVel;
	}

	public float getzVel() 
	{
		return zVel;
	}

	public void setzVel(float zVel) 
	{
		this.zVel = zVel;
	}
	
	public float getxVelAir() 
	{
		return xVelAir;
	}

	public float getzVelAir() 
	{
		return zVelAir;
	}
	
	public float getPreviousSpeed()
	{
		return previousSpeed;
	}
	
	public float getPreviousDirection()
	{
		return previousDirection;
	}
	
	public float getHeadHeight()
	{
		return headHeight;
	}
	
	public float getHitboxHorizontal()
	{
		return hitboxHorizontal;
	}
	
	public float getHitboxVertical()
	{
		return hitboxVertically;
	}
	
	public void grabStar()
	{
		starGrabTimer = starGrabTimerMax;
		//cameraRadius = cameraRadiusZoomOut;
		setxVel(0);
		setzVel(0);
		xVelAir = 0;
		zVelAir = 0;
		setRotY(-myCameraYawTarget+180+68);
		myCameraPitchTarget = 13.5f;
		inCutScene = true;
	}
	
	public boolean getInCutScene()
	{
		return inCutScene;
	}
	
	public boolean getOnPole()
	{
		return onPole;
	}
	
	public void grabPole(Pole newPole, int poleSound)
	{
		onPole = true;
		xVel = 0;
		zVel = 0;
		grabbedPole = newPole;
		grabbedPoleSound = poleSound;
		AudioRes.playSound(51, 1, getPosition());
		setRotZ(0);
	}
	
	public boolean getInAir()
	{
		return inAir;
	}
	
	public boolean getIsGroundPounding()
	{
		return isGroundPounding;
	}
	
	public boolean getIsDiving()
	{
		return isDiving;
	}
	
	public boolean getIsSliding()
	{
		return isSliding;
	}
	
	public void setDisplacement(float x, float y, float z)
	{
		xDisp = x;
		yDisp = y;
		zDisp = z;
	}
	
	//this would be how to make momentum carry over from moving platforms
	//but player always sets air vels to 0 if on the ground 
	//so something in player would need to be altered for it to work
	public void setAirVel(float x, float y, float z)
	{
		xVelAir = x;
		yDisp = y;
		zVelAir = z;
		isGettingExternallyMoved = true;
	}
	
	public void takeDamage(Vector3f damageSource, int amount)
	{
		if(iFrame == 0)
		{
			float xDiff = damageSource.x-getX();
			float zDiff = damageSource.z-getZ();
			double newDirection = Math.atan2(zDiff,  -xDiff);
			xVel = (float)(0.6*Math.cos(newDirection));
			zVel = (float)(-0.6*Math.sin(newDirection));
			bonkTimer = bonkTimerMax;
			AudioRes.playSound(11, 1, getPosition());
			jumpNumber = 0;
			iFrame = 120;
		}
	}
	
	private void hitGroundPound()
	{
		//create dust
		for(int i = 360; i != 0; i-=18)
		{
			new Particle(ParticleResources.textureDustCloud,
				new Vector3f(getX(), getY(), getZ()),
				new Vector3f((float)Math.cos(Math.toRadians(i+Math.random()*30)), 
						     0.1f,
						     (float)Math.sin(Math.toRadians(i+Math.random()*30))), 
				0, 16, 0, 1, 0.5f);
		}
		//create stars
		for(int i = 360; i != 0; i-=45)
		{
			new Particle(ParticleResources.textureStar,
				new Vector3f(getX(), getY(), getZ()),
				new Vector3f(2.5f*((float)Math.cos(Math.toRadians(i))), 
						     0.5f,
						     (2.5f*(float)Math.sin(Math.toRadians(i)))), 
				0, 9, 0, 2.5f, 0f);
		}
		//makes noise
		AudioRes.playSound(41, 1, getPosition());
	}
	
	public boolean isVulnerable()
	{
		return (iFrame == 0);
	}
	
	public void updateLimbs(int animIndex, float time)
	{
		myBody.animationIndex = animIndex;
    	myHead.animationIndex = animIndex;
    	myLeftHumerus.animationIndex = animIndex;
    	myLeftForearm.animationIndex = animIndex;
    	myLeftHand.animationIndex = animIndex;
    	myLeftThigh.animationIndex = animIndex;
    	myLeftShin.animationIndex = animIndex;
    	myLeftFoot.animationIndex = animIndex;
    	myRightHumerus.animationIndex = animIndex;
    	myRightForearm.animationIndex = animIndex;
    	myRightHand.animationIndex = animIndex;
    	myRightThigh.animationIndex = animIndex;
    	myRightShin.animationIndex = animIndex;
    	myRightFoot.animationIndex = animIndex;
    	myBody.update(time);
    	myHead.update(time);
    	myLeftHumerus.update(time);
    	myLeftForearm.update(time);
    	myLeftHand.update(time);
    	myLeftThigh.update(time);
    	myLeftShin.update(time);
    	myLeftFoot.update(time);
    	myRightHumerus.update(time);
    	myRightForearm.update(time);
    	myRightHand.update(time);
    	myRightThigh.update(time);
    	myRightShin.update(time);
    	myRightFoot.update(time);	
	}
	
	public void setLimbsVisibility(boolean visible)
	{
		myBody.setVisibility(visible);
		myHead.setVisibility(visible);
		myLeftHumerus.setVisibility(visible);
    	myLeftForearm.setVisibility(visible);
    	myLeftHand.setVisibility(visible);
    	myLeftThigh.setVisibility(visible);
    	myLeftShin.setVisibility(visible);
    	myLeftFoot.setVisibility(visible);
    	myRightHumerus.setVisibility(visible);
    	myRightForearm.setVisibility(visible);
    	myRightHand.setVisibility(visible);
    	myRightThigh.setVisibility(visible);
    	myRightShin.setVisibility(visible);
    	myRightFoot.setVisibility(visible);
	}
	
	public void setLimbsScale(float newScale)
	{
		myBody.setScale(newScale);
		myHead.setScale(newScale);
		myLeftHumerus.setScale(newScale);
    	myLeftForearm.setScale(newScale);
    	myLeftHand.setScale(newScale);
    	myLeftThigh.setScale(newScale);
    	myLeftShin.setScale(newScale);
    	myLeftFoot.setScale(newScale);
    	myRightHumerus.setScale(newScale);
    	myRightForearm.setScale(newScale);
    	myRightHand.setScale(newScale);
    	myRightThigh.setScale(newScale);
    	myRightShin.setScale(newScale);
    	myRightFoot.setScale(newScale);
	}
	
	public void addLimbsToGame()
	{
		MainGameLoop.gameEntitiesToAdd.add(myBody);
		MainGameLoop.gameEntitiesToAdd.add(myHead);
		MainGameLoop.gameEntitiesToAdd.add(myLeftHumerus);
    	MainGameLoop.gameEntitiesToAdd.add(myLeftForearm);
    	MainGameLoop.gameEntitiesToAdd.add(myLeftHand);
    	MainGameLoop.gameEntitiesToAdd.add(myLeftThigh);
    	MainGameLoop.gameEntitiesToAdd.add(myLeftShin);
    	MainGameLoop.gameEntitiesToAdd.add(myLeftFoot);
    	MainGameLoop.gameEntitiesToAdd.add(myRightHumerus);
    	MainGameLoop.gameEntitiesToAdd.add(myRightForearm);
    	MainGameLoop.gameEntitiesToAdd.add(myRightHand);
    	MainGameLoop.gameEntitiesToAdd.add(myRightThigh);
    	MainGameLoop.gameEntitiesToAdd.add(myRightShin);
    	MainGameLoop.gameEntitiesToAdd.add(myRightFoot);
	}
	
	public void removeLimbsFromGame()
	{
		MainGameLoop.gameEntitiesToDelete.add(myBody);
		MainGameLoop.gameEntitiesToDelete.add(myHead);
		MainGameLoop.gameEntitiesToDelete.add(myLeftHumerus);
    	MainGameLoop.gameEntitiesToDelete.add(myLeftForearm);
    	MainGameLoop.gameEntitiesToDelete.add(myLeftHand);
    	MainGameLoop.gameEntitiesToDelete.add(myLeftThigh);
    	MainGameLoop.gameEntitiesToDelete.add(myLeftShin);
    	MainGameLoop.gameEntitiesToDelete.add(myLeftFoot);
    	MainGameLoop.gameEntitiesToDelete.add(myRightHumerus);
    	MainGameLoop.gameEntitiesToDelete.add(myRightForearm);
    	MainGameLoop.gameEntitiesToDelete.add(myRightHand);
    	MainGameLoop.gameEntitiesToDelete.add(myRightThigh);
    	MainGameLoop.gameEntitiesToDelete.add(myRightShin);
    	MainGameLoop.gameEntitiesToDelete.add(myRightFoot);
	}
	
	public void respawn()
	{
		super.respawn();
		addLimbsToGame();
		timeTick = 0;
		xVel = 0.0f;
		yVel = 0.0f;
		zVel = 0.0f;
		xVelAir = 0.0f;
		zVelAir = 0.0f;
		movementInputX = 0;
		movementInputY = 0;
		movementAngle = 0;
		moveSpeedCurrent = 0;
		jumpInput = false;
		previousJumpInput = false;
		newCameraPosition = new Vector3f(0,0,0);
		triCol = null;
		passedCheck1 = false;
		passedCheck2 = false;
		inAir = true;
		inAirPrevious = true;
		pushUpValue = 0.0f;
		landTimerCurrent = 0;
		jumpNumber = 1;
		onWall = false;
		actionInput = false;
		previousActionInput = false;
		shoulderInput = false;
		previousShoulderInput = false;
		firstPersonView = false;
		skidTimer = 0;
		isDiving = false;
		isHighJumping = false;
		isLongJumping = false;
		isSideFlipping = false;
		isGroundPounding = false;
		canSideFlip = false;
		moveSpeedAirCurrent = 0.0f;
		inputAngle = 0.0f;
		modelStandIndex = 0.0f;
		modelRunIndex = 0.0f;
		groundPoundFrame = 0;
		isSliding = false;
		isRollingOut = false;
		bonkTimer = 0;
		canWallKick = false;
		wallKickTimer = 0;
		actionPreformed = false;
		jumpFrameCurrent = 0;
		inCutScene = false;
		groundPoundRecoveryTimer = 0;
		isGettingExternallyMoved = false;
	}
	
	public void despawn()
	{
		super.despawn();
		removeLimbsFromGame();
	}
}