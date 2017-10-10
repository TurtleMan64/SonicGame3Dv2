package entities;

import models.TexturedModel;
import particles.Particle;
import particles.ParticleResources;
import renderEngine.DisplayManager;
import toolbox.AudioRes;
import toolbox.ConvenientMethods;
import toolbox.Input;
import toolbox.Joystick;
import toolbox.Maths;
import toolbox.ParticleManager;
import toolbox.RotationMatrixUnnormalizedDirVector;

import java.util.LinkedList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import animation.AnimationResources;
import animation.Body;
import animation.Limb;
import audio.AudioSources;
import audio.Source;
import collision.CollisionChecker;
import collision.Triangle3D;
import engineTester.MainGameLoop;
import guis.GuiManager;

public class Ball extends Entity
{
	//private static TexturedModel[] modelBall = ConvenientMethods.loadModel("Models/Sonic/", "Ball", MainGameLoop.gameLoader);
	//private static TexturedModel[] modelBallRed = ConvenientMethods.loadModel("Models/Sky/", "SphereRed");
	//private static TexturedModel[] modelBallGreen = ConvenientMethods.loadModel("Models/Sky/", "SphereGreen");
	//private static TexturedModel[] modelBallBlue = ConvenientMethods.loadModel("Models/Sky/", "SphereBlue");
	//private static TexturedModel[] modelBallYellow = ConvenientMethods.loadModel("Models/Sky/", "SphereYellow");
	//private static TexturedModel[] modelLimb = ConvenientMethods.loadModel("Models/Limb/", "Limb");
	
	public static int characterID = 0;
	
	
	private static TexturedModel[] modelBody             = null; //ConvenientMethods.loadModel("Models/Sonic/", "Body");
	private static TexturedModel[] modelHead             = null; //ConvenientMethods.loadModel("Models/Sonic/", "Head");
	private static TexturedModel[] modelLeftHumerus      = null; //ConvenientMethods.loadModel("Models/Sonic/", "Humerus");
	private static TexturedModel[] modelLeftForearm      = null; //ConvenientMethods.loadModel("Models/Sonic/", "Forearm");
	private static TexturedModel[] modelLeftHand         = null; //ConvenientMethods.loadModel("Models/Sonic/", "LeftHand");
	private static TexturedModel[] modelLeftThigh        = null; //ConvenientMethods.loadModel("Models/Sonic/", "Thigh");
	private static TexturedModel[] modelLeftShin         = null; //ConvenientMethods.loadModel("Models/Sonic/", "Shin");
	private static TexturedModel[] modelLeftFoot         = null; //ConvenientMethods.loadModel("Models/Sonic/", "Foot");
	private static TexturedModel[] modelRightHumerus     = null; //ConvenientMethods.loadModel("Models/Sonic/", "Humerus");
	private static TexturedModel[] modelRightForearm     = null; //ConvenientMethods.loadModel("Models/Sonic/", "Forearm");
	private static TexturedModel[] modelRightHand        = null; //ConvenientMethods.loadModel("Models/Sonic/", "RightHand");
	private static TexturedModel[] modelRightThigh       = null; //ConvenientMethods.loadModel("Models/Sonic/", "Thigh");
	private static TexturedModel[] modelRightShin        = null; //ConvenientMethods.loadModel("Models/Sonic/", "Shin");
	private static TexturedModel[] modelRightFoot        = null; //ConvenientMethods.loadModel("Models/Sonic/", "Foot");
	
	//private static TexturedModel[] modelBall             = ConvenientMethods.loadModel("Models/Sonic/", "Ball");
	
	
	/*
	private static TexturedModel[] modelBody             = ConvenientMethods.loadModel("Models/Amy/", "Body");
	private static TexturedModel[] modelHead             = ConvenientMethods.loadModel("Models/Amy/", "Head");
	private static TexturedModel[] modelLeftHumerus      = ConvenientMethods.loadModel("Models/Amy/", "Humerus");
	private static TexturedModel[] modelLeftForearm      = ConvenientMethods.loadModel("Models/Amy/", "Forearm");
	private static TexturedModel[] modelLeftHand         = ConvenientMethods.loadModel("Models/Amy/", "LeftHand");
	private static TexturedModel[] modelLeftThigh        = ConvenientMethods.loadModel("Models/Amy/", "Thigh");
	private static TexturedModel[] modelLeftShin         = ConvenientMethods.loadModel("Models/Amy/", "Shin");
	private static TexturedModel[] modelLeftFoot         = ConvenientMethods.loadModel("Models/Amy/", "Foot");
	private static TexturedModel[] modelRightHumerus     = ConvenientMethods.loadModel("Models/Amy/", "Humerus");
	private static TexturedModel[] modelRightForearm     = ConvenientMethods.loadModel("Models/Amy/", "Forearm");
	private static TexturedModel[] modelRightHand        = ConvenientMethods.loadModel("Models/Amy/", "RightHand");
	private static TexturedModel[] modelRightThigh       = ConvenientMethods.loadModel("Models/Amy/", "Thigh");
	private static TexturedModel[] modelRightShin        = ConvenientMethods.loadModel("Models/Amy/", "Shin");
	private static TexturedModel[] modelRightFoot        = ConvenientMethods.loadModel("Models/Amy/", "Foot");
	*/
	
	/*
	private static TexturedModel[] modelBody             = ConvenientMethods.loadModel("Models/SilverSonic/", "Body");
	private static TexturedModel[] modelHead             = ConvenientMethods.loadModel("Models/SilverSonic/", "Head");
	private static TexturedModel[] modelLeftHumerus      = ConvenientMethods.loadModel("Models/SilverSonic/", "Humerus");
	private static TexturedModel[] modelLeftForearm      = ConvenientMethods.loadModel("Models/SilverSonic/", "ForearmL");
	private static TexturedModel[] modelLeftHand         = ConvenientMethods.loadModel("Models/SilverSonic/", "HandL");
	private static TexturedModel[] modelLeftThigh        = ConvenientMethods.loadModel("Models/SilverSonic/", "Thigh");
	private static TexturedModel[] modelLeftShin         = ConvenientMethods.loadModel("Models/SilverSonic/", "ShinL");
	private static TexturedModel[] modelLeftFoot         = ConvenientMethods.loadModel("Models/SilverSonic/", "FootL");
	private static TexturedModel[] modelRightHumerus     = ConvenientMethods.loadModel("Models/SilverSonic/", "Humerus");
	private static TexturedModel[] modelRightForearm     = ConvenientMethods.loadModel("Models/SilverSonic/", "ForearmR");
	private static TexturedModel[] modelRightHand        = ConvenientMethods.loadModel("Models/SilverSonic/", "HandR");
	private static TexturedModel[] modelRightThigh       = ConvenientMethods.loadModel("Models/SilverSonic/", "Thigh");
	private static TexturedModel[] modelRightShin        = ConvenientMethods.loadModel("Models/SilverSonic/", "ShinR");
	private static TexturedModel[] modelRightFoot        = ConvenientMethods.loadModel("Models/SilverSonic/", "FootR");
	*/
	
	/*
	private static TexturedModel[] modelBody             = ConvenientMethods.loadModel("Models/SonicDoll/", "Body");
	private static TexturedModel[] modelHead             = ConvenientMethods.loadModel("Models/SonicDoll/", "Head");
	private static TexturedModel[] modelLeftHumerus      = ConvenientMethods.loadModel("Models/SonicDoll/", "Humerus");
	private static TexturedModel[] modelLeftForearm      = ConvenientMethods.loadModel("Models/SonicDoll/", "Forearm");
	private static TexturedModel[] modelLeftHand         = ConvenientMethods.loadModel("Models/SonicDoll/", "HandL");
	private static TexturedModel[] modelLeftThigh        = ConvenientMethods.loadModel("Models/SonicDoll/", "Thigh");
	private static TexturedModel[] modelLeftShin         = ConvenientMethods.loadModel("Models/SonicDoll/", "Shin");
	private static TexturedModel[] modelLeftFoot         = ConvenientMethods.loadModel("Models/SonicDoll/", "FootL");
	private static TexturedModel[] modelRightHumerus     = ConvenientMethods.loadModel("Models/SonicDoll/", "Humerus");
	private static TexturedModel[] modelRightForearm     = ConvenientMethods.loadModel("Models/SonicDoll/", "Forearm");
	private static TexturedModel[] modelRightHand        = ConvenientMethods.loadModel("Models/SonicDoll/", "HandR");
	private static TexturedModel[] modelRightThigh       = ConvenientMethods.loadModel("Models/SonicDoll/", "Thigh");
	private static TexturedModel[] modelRightShin        = ConvenientMethods.loadModel("Models/SonicDoll/", "Shin");
	private static TexturedModel[] modelRightFoot        = ConvenientMethods.loadModel("Models/SonicDoll/", "FootR");
	*/
	
	/*
	private static TexturedModel[] modelBody             = ConvenientMethods.loadModel("Models/Mario/", "MarioBody", MainGameLoop.gameLoader);
	private static TexturedModel[] modelHead             = ConvenientMethods.loadModel("Models/Mario/", "MarioHead", MainGameLoop.gameLoader);
	private static TexturedModel[] modelLeftHumerus      = ConvenientMethods.loadModel("Models/Mario/", "MarioHumerus", MainGameLoop.gameLoader);
	private static TexturedModel[] modelLeftForearm      = ConvenientMethods.loadModel("Models/Mario/", "MarioForearm", MainGameLoop.gameLoader);
	private static TexturedModel[] modelLeftHand         = ConvenientMethods.loadModel("Models/Mario/", "MarioHand",MainGameLoop.gameLoader);
	private static TexturedModel[] modelLeftThigh        = ConvenientMethods.loadModel("Models/Mario/", "MarioThigh", MainGameLoop.gameLoader);
	private static TexturedModel[] modelLeftShin         = ConvenientMethods.loadModel("Models/Mario/", "MarioShin",  MainGameLoop.gameLoader);
	private static TexturedModel[] modelLeftFoot         = ConvenientMethods.loadModel("Models/Mario/", "MarioFoot",  MainGameLoop.gameLoader);
	private static TexturedModel[] modelRightHumerus     = ConvenientMethods.loadModel("Models/Mario/", "MarioHumerus",  MainGameLoop.gameLoader);
	private static TexturedModel[] modelRightForearm     = ConvenientMethods.loadModel("Models/Mario/", "MarioForearm",  MainGameLoop.gameLoader);
	private static TexturedModel[] modelRightHand        = ConvenientMethods.loadModel("Models/Mario/", "MarioHand", MainGameLoop.gameLoader);
	private static TexturedModel[] modelRightThigh       = ConvenientMethods.loadModel("Models/Mario/", "MarioThigh",  MainGameLoop.gameLoader);
	private static TexturedModel[] modelRightShin        = ConvenientMethods.loadModel("Models/Mario/", "MarioShin", MainGameLoop.gameLoader);
	private static TexturedModel[] modelRightFoot        = ConvenientMethods.loadModel("Models/Mario/", "MarioFoot",  MainGameLoop.gameLoader);
	*/
	
	/*
	private static TexturedModel[] modelBody             = ConvenientMethods.loadModel("Models/Midna/", "Body", MainGameLoop.gameLoader);
	private static TexturedModel[] modelHead             = ConvenientMethods.loadModel("Models/Midna/", "Head", MainGameLoop.gameLoader);
	private static TexturedModel[] modelLeftHumerus      = ConvenientMethods.loadModel("Models/Midna/", "Humerus", MainGameLoop.gameLoader);
	private static TexturedModel[] modelLeftForearm      = ConvenientMethods.loadModel("Models/Midna/", "Forearm", MainGameLoop.gameLoader);
	private static TexturedModel[] modelLeftHand         = ConvenientMethods.loadModel("Models/Midna/", "LeftHand",MainGameLoop.gameLoader);
	private static TexturedModel[] modelLeftThigh        = ConvenientMethods.loadModel("Models/Midna/", "LeftThigh", MainGameLoop.gameLoader);
	private static TexturedModel[] modelLeftShin         = ConvenientMethods.loadModel("Models/Midna/", "LeftShin",  MainGameLoop.gameLoader);
	private static TexturedModel[] modelLeftFoot         = ConvenientMethods.loadModel("Models/Midna/", "LeftFoot",  MainGameLoop.gameLoader);
	private static TexturedModel[] modelRightHumerus     = ConvenientMethods.loadModel("Models/Midna/", "Humerus",  MainGameLoop.gameLoader);
	private static TexturedModel[] modelRightForearm     = ConvenientMethods.loadModel("Models/Midna/", "Forearm",  MainGameLoop.gameLoader);
	private static TexturedModel[] modelRightHand        = ConvenientMethods.loadModel("Models/Midna/", "RightHand", MainGameLoop.gameLoader);
	private static TexturedModel[] modelRightThigh       = ConvenientMethods.loadModel("Models/Midna/", "RightThigh",  MainGameLoop.gameLoader);
	private static TexturedModel[] modelRightShin        = ConvenientMethods.loadModel("Models/Midna/", "RightShin", MainGameLoop.gameLoader);
	private static TexturedModel[] modelRightFoot        = ConvenientMethods.loadModel("Models/Midna/", "RightFoot",  MainGameLoop.gameLoader);
	*/

	
	
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
	
	//private Entity myBall;
	
	public static Camera cam;
	public static Player player;
	
	//public static Entity sphereRed = new Entity(modelBallRed, new Vector3f(0,0,0));
	//public static Entity sphereGreen = new Entity(modelBallGreen, new Vector3f(0,0,0));
	//public static Entity sphereBlue = new Entity(modelBallBlue, new Vector3f(0,0,0));
	//public static Entity sphereYellow = new Entity(modelBallYellow, new Vector3f(0,0,0));
	//public static Entity limb = new Entity(modelLimb, new Vector3f(0,0,0));
	
	public static float relativeXVel;
	public static float relativeZVel;
	
	private float xVel;
	private float yVel;
	private float zVel;
	
	private float xVelAir;
	private float zVelAir;
	
	private Vector3f currNorm;
	
	private Vector3f previousPos;
	
	private int airTimer;
	private boolean onPlane;
	private boolean onPlanePrevious = false;
	private float gravity = 0.08f;
	private float hoverAccel = 0.04f; //0.04f;
	private float jumpPower = 1.7f; //1.7
	
	private int hoverLimit = 60;
	private int hoverCount;
	
	private float moveAcceleration = 0.07f;//0.07
	private float moveSpeedCurrent;
	private float moveAccelerationAir = 0.035f;
	private float moveSpeedAirCurrent;
	
	private float frictionGround = 0.03f;//0.03
	private float frictionAir = 0.01f;
	
	private float normalSpeedLimit = 4.0f;//4.8
	private float airSpeedLimit = 2.5f;
	
	private float slowDownRate = 0.06f; //0.06 //how fast you slowdown every frame when youre going faster than max speed 
	private float slowDownAirRate = 0.025f;
	
	private float spindashPower = 0.15f;//0.13f
	private float spindashFriction = 0.09f;//0.09f
	private int spindashDelay = 0;
	private int spindashTimer;
	private int spindashTimerMax = 30;
	private int spindashReleaseTimer;
	private int spindashReleaseTimerMax = 30;
	
	private int homingAttackTimer = -1; // -1 = can start a homing attack
	private int homingAttackTimerMax = 10;
	
	
	private float surfaceTension = 10f; //3, 10 after new 2nd collision ignore feature
	private float slopeAccel = 0.08f; //0.08f //how much you are influenced by the terrain's slope
	
	private float count = 0;
	
	private Triangle3D triCol;
	private Vector3f colPos;
	
	private float modelRunIndex;
	
	private float movementInputX;
	private float movementInputY;
	private float movementAngle; //in degrees
	private boolean jumpInput;
	private boolean previousJumpInput;
	private boolean actionInput; //action means pretty much the 'B' button
	private boolean previousActionInput;
	private boolean action2Input; //action2 means pretty much the 'X' button
	private boolean previousAction2Input;
	private boolean shoulderInput; //shoulder is the 'Z' button on n64 controller, or like L1 on play station
	private boolean previousShoulderInput;
	private boolean selectInput; //start button
	private boolean previousSelectInput;
	private boolean specialInput; //Y button
	private boolean previousSpecialInput;
	private float zoomInput;
	private float inputAngle;
	private float cameraInputX;
	private float cameraInputY;
	private int mousePreviousY;
	private int mousePreviousX;
	private float cameraRadius;
	private float cameraRadiusTarget;
	private float cameraLaziness = 3;
	private float cameraRadiusZoom = 40;
	private float cameraRadiusZoomOut = 104f;
	private boolean inCutScene;
	private boolean canMove = true;
	private float myCameraYawTarget;
	public float myCameraPitchTarget;
	private float spindashAngle;
	private boolean canStartSpindash;
	
	private boolean isJumping;
	private boolean isSpindashing;
	private boolean isSkidding;
	private boolean isBall;
	private boolean isBouncing;
	private boolean isStomping;
	private boolean isLightdashing;
	
	private float headHeight = 7; //4
	private boolean firstPerson;
	
	private float displayHeightOffset = 0.8f; //How much the model is shifted vertically when displaying
	private Vector3f previousDisplayPos;
	
	private int iFrame = 0;
	private int hitTimer = 0;
	
	private boolean inWater = false;
	private boolean inWaterPrevious = false;
	private float waterHeight = 0;
	
	private boolean isGettingExternallyMoved = false;
	private float xDisp;
	private float yDisp;
	private float zDisp;
	
	//private LinkedList<Ring> closeRings;
	
	Source stompSource = null;
	//boolean shouldStopStomp = false;
	
	public Ball(Vector3f position)
	{
		super(position, 0, 0, 0, 0.00001f);
		relativeXVel = 0;
		relativeZVel = 0;
		xVel = 0;
		yVel = 0;
		zVel = 0;
		xVelAir = 0;
		zVelAir = 0;
		currNorm = new Vector3f(0, 1f, 0);
		previousPos = new Vector3f(0, 0, 0);
		previousDisplayPos = new Vector3f(0,0,0);
		airTimer = 0;
		hoverCount = 0;
		onPlane = false;
		isJumping = false;
		isLightdashing = false;
		isSpindashing = false;
		isSkidding = false;
		isBall = false;
		isBouncing = false;
		isStomping = false;
		spindashTimer = 0;
		spindashReleaseTimer = 0;
		canStartSpindash = false;
		//previousOnPlane = false;
		movementInputX = 0;
		movementInputY = 0;
		movementAngle = 0;
		modelRunIndex = 0;
		firstPerson = false;
		cameraRadius = 104;
		cameraRadiusTarget = 104;
		//MainGameLoop.gameEntitiesToAdd.add(limb);
		//MainGameLoop.gameEntitiesToAdd.add(sphereRed);
		//MainGameLoop.gameEntitiesToAdd.add(sphereGreen);
		//MainGameLoop.gameEntitiesToAdd.add(sphereBlue);
		//MainGameLoop.gameEntitiesToAdd.add(sphereYellow);
		//sphereRed.setVisibility(true);
		//sphereGreen.setVisibility(true);
		//sphereBlue.setVisibility(true);
		//sphereYellow.setVisibility(true);
		//limb.setVisibility(true);
		//sphereRed.setScale(0.02f);
		//sphereGreen.setScale(0.02f);
		//sphereBlue.setScale(0.02f);
		//sphereYellow.setScale(0.02f);
		//limb.setScale(0.2f);
		//setPosition(new Vector3f(278.7f, 205f, 231f));
		//closeRings = new LinkedList<Ring>();
		//myBall = new Entity(modelBall, new Vector3f(0,0,0));
		//MainGameLoop.gameEntitiesToAdd.add(myBall);
		createLimbs();
		respawn();
	}
	
	@Override
	public void step()
	{
		previousPos.set(getPosition());
		//inWaterPrevious = inWater;
		//float time = (float)DisplayManager.getFactor();
		float time = 1.0f;
		count+=1;
		setMovementInputs();
		adjustCamera();
		checkSkid();
		
		iFrame = Math.max(0, iFrame-1);
		hitTimer = Math.max(0, hitTimer-1);
		//inWater = false;
		
		if(jumpInput)
		{
			hoverCount = Math.max(hoverCount-1, 0);
		}
		else
		{
			hoverCount = 0;
		}
		
		if(Joystick.joystickExists() && Joystick.getButtonJustPressed(Joystick.BUTTON_RB))
		{
			//System.out.println("X = "+super.getX()+"    Y = "+super.getY()+"    Z = "+super.getZ()+"    angle = "+myCameraYawTarget);
		}
		
		if(selectInput && !previousSelectInput)
		{
			if(MainGameLoop.freeMouse)
			{
				MainGameLoop.freeMouse = false;
				Mouse.setGrabbed(true);
			}
			else
			{
				MainGameLoop.freeMouse = true;
				Mouse.setGrabbed(false);
			}
		}
		
		if (!onPlane) //in air
		{
			relativeXVel = 0;
			relativeZVel = 0;
			applyFrictionAir(); 
			moveMeAir();
			limitMovementSpeedAir();
			yVel-=gravity*time;
		}
		else //on ground
		{
			hitTimer = 0;
			
			if (onPlanePrevious == false)
			{
				if (isGettingExternallyMoved == false)
				{
					//xVel+=xVelAir;
					//zVel+=zVelAir;
					//xVelAir = 0.0f;
					//zVelAir = 0.0f;
				}
			}
			
			if (isGettingExternallyMoved == false)
			{
				//xVelAir = 0.0f;
				//zVelAir = 0.0f;
			}
			
			xVelAir = 0;
			zVelAir = 0;
			applyFriction(frictionGround);
			moveMeGround(); 
			limitMovementSpeed(normalSpeedLimit); 
		}
		
		if (isBall)
		{
			relativeXVel += currNorm.x*slopeAccel*1.1;
			relativeZVel += currNorm.z*slopeAccel*1.1;
		}
		else
		{
			relativeXVel += currNorm.x*slopeAccel*time;
			relativeZVel += currNorm.z*slopeAccel*time;
		}
		
		float inputX = relativeXVel;
		float inputY = relativeZVel;
		float mag = (float)Math.sqrt(inputX*inputX+inputY*inputY);

		float inputDir = (float)(Math.atan2(inputY, inputX));
		Vector3f mapped = mapInputs3(inputDir, mag, new Vector3f(currNorm.negate(null)));
		float Ax = mapped.x;
		float Ay = mapped.y;
		float Az = mapped.z;
		
		mapInputs3(0, 1, new Vector3f(0, -1, 0));
		mapInputs3(0, 1, new Vector3f(currNorm.negate(null)));
		
		if (onPlane)
		{
			//System.out.println();
			isJumping = false;
			isBouncing = false;
			isStomping = false;
			homingAttackTimer = -1;
			float speed = (float)Math.sqrt(relativeXVel*relativeXVel+relativeZVel*relativeZVel);
			if(currNorm.y <= 0 && speed < 1)
			{
				currNorm = new Vector3f(0, 1, 0);
			}
			xVel = Ax;
			yVel = Ay;
			zVel = Az;
			xVelAir = 0;
			zVelAir = 0;
			hoverCount = hoverLimit;
			
			if(jumpInput && !previousJumpInput)
			{
				increasePosition(currNorm.x*surfaceTension, currNorm.y*surfaceTension, currNorm.z*surfaceTension);
				yVel += currNorm.y*jumpPower;
				xVel += currNorm.x*jumpPower;
				zVel += currNorm.z*jumpPower;
				isJumping = true;
				//AudioRes.playSound(50, 1.0f, getPosition());
				AudioSources.play(6, getPosition());
			}
			
			//System.out.println("speed = "+speed);
			if (speed < 0.35)
			{
				if (isBall)
				{
					spindashReleaseTimer = spindashReleaseTimerMax;
				}
				isBall = false;
			}
			
			
			if (isBall == false)
			{
				canStartSpindash = true;
			}
			else
			{
				canStartSpindash = false;
			}
			
			if (((actionInput && !previousActionInput) || (action2Input && !previousAction2Input))
					&& canStartSpindash)
			{
				isSpindashing = true;
			}
			
			if (!actionInput && !action2Input)
			{
				isSpindashing = false;
			}
			
			if (isSpindashing)
			{
				spindashTimer = Math.min(spindashTimer+1, spindashTimerMax);
				if (spindashTimer == 1)
				{
					//AudioRes.playSound(51, 1.0f, getPosition());
					//AudioRes.stopSound(52);
					AudioSources.play(7, getPosition());
				}
				isSpindashing = true;
				calcSpindashAngle();
				if(spindashTimer > spindashDelay)
				{
					applyFriction(spindashFriction);
				}
			}
			else
			{
				if(spindashTimer > 0)
				{
					spindash(spindashTimer);
					//spindashReleaseTimer = spindashReleaseTimerMax;
				}
				spindashTimer = 0;
			}
			
			if (((actionInput && !previousActionInput) || (action2Input && !previousAction2Input)))
			{
				if (isBall)
				{
					spindashReleaseTimer = spindashReleaseTimerMax;
				}
				
				isBall = false;
			}
		}
		else //In the air
		{
			//"homing attack" (more like flame shield right now)
			if (jumpInput && !previousJumpInput && homingAttackTimer == -1 && (isJumping || isBall || isBouncing))
			{
				homingAttack();
			}
			
			if (actionInput && !previousActionInput && (isJumping || isBall) && !isBouncing && homingAttackTimer == -1 && !isStomping)
			{
				initiateBounce();
			}
			
			if (action2Input && !previousAction2Input && (isJumping || isBall) && !isBouncing && homingAttackTimer == -1 && !isStomping)
			{
				initiateStomp();
			}
			
			isSpindashing = false;
			//isBall = false;
			canStartSpindash = false;
			spindashReleaseTimer = 0;
			spindashTimer =  0;
			if(hoverCount > 0)
			{
				yVel += hoverAccel*time;
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_J))
			{
				yVel+=0.5f*time;
			}
		}
		
		spindashReleaseTimer = Math.max(spindashReleaseTimer-1, 0);
		if (homingAttackTimer > 0)
		{
			homingAttackTimer--;
		}
		
		onPlanePrevious = onPlane;
		CollisionChecker.checkPlayer();
		if (CollisionChecker.checkCollision(getX(), getY(), getZ(), (float)(getX()+(xVel+xVelAir+xDisp)*time), (float)(getY()+(yVel+yDisp)*time), (float)(getZ()+(zVel+zVelAir+zDisp)*time)))
		{
			triCol = CollisionChecker.getCollideTriangle();
			colPos = CollisionChecker.getCollidePosition();
			boolean bonked = false;
			if (onPlane == false) //Transition from in air to on ground
			{
				if (isBouncing)
				{
					bounceOffGround(triCol.normal);
				}
				else
				{
					Vector3f speeds = calculatePlaneSpeed((float)((xVel+xVelAir+xDisp)*time), (float)((yVel+yDisp)*time), (float)(zVel+zVelAir+zDisp*time), CollisionChecker.getCollidePosition(), CollisionChecker.getCollideTriangle().normal);
					relativeXVel = speeds.x;
					relativeZVel = speeds.z;
					isBall = false;
					onPlane = true;
				}
				
				if (isStomping)
				{
					if (stompSource != null)
					{
						stompSource.stop();
					}
					AudioSources.play(33, getPosition());
					
					createStompParticles();
				}
			}
			else //check if you can smoothly transition from previous triangle to this triangle
			{
				float dotProduct = Vector3f.dot(currNorm, CollisionChecker.getCollideTriangle().normal);
				if(dotProduct < 0.6)
				{
					//relativeXVel = relativeXVel*(dotProduct/(dotProduct+0.001f));
					//relativeZVel = relativeZVel*(dotProduct/(dotProduct+0.001f));
					relativeXVel = 0;
					relativeZVel = 0;
					
					//Vector3f wallNorm = CollisionChecker.getCollideTriangle().normal;
					//setPosition(CollisionChecker.getCollidePosition());
					//increasePosition(wallNorm.x*1f, wallNorm.y*1f, wallNorm.z*1f);
					//Vector3f slide = Maths.wallSlide(new Vector3f(xVel, yVel, zVel), wallNorm);
					//increasePosition(slide.x, slide.y, slide.z);
					
					bonked = true;
					currNorm = new Vector3f(0, 1, 0);
				}
				onPlane = true;
			}
			
			if(!bonked)
			{
				setPosition(CollisionChecker.getCollidePosition());
				currNorm = CollisionChecker.getCollideTriangle().normal;
				increasePosition(currNorm.x*1f, currNorm.y*1f, currNorm.z*1f);
			}
			
			airTimer = 0;
		}
		else
		{
			increasePosition((float)((xVel+xVelAir+xDisp)*time), (float)((yVel+yDisp)*time), (float)((zVel+zVelAir+zDisp)*time));
			//Vector3f after = new Vector3f(getPosition());
			
			//Check for if there's just a small gap "below" us (relative to the normal of the triangle)
			//NEW: If the second check does pass but it is a wall, we pretend that the check didnt pass
			//Also, if we are in the air, then we can just ignore this second check altogether
			boolean checkPassed = false;
			CollisionChecker.checkPlayer();
			if (onPlane)
			{
				checkPassed = CollisionChecker.checkCollision(getX(), getY(), getZ(), getX()-currNorm.x*surfaceTension, getY()-currNorm.y*surfaceTension, getZ()-currNorm.z*surfaceTension);
			}
			if (checkPassed)
			{
				float dotProduct = Vector3f.dot(currNorm, CollisionChecker.getCollideTriangle().normal);
				if (dotProduct < 0.6) //It's a wall, pretend the collision check didn't see it
				{
					checkPassed = false;
				}
			}
			
			//Check again
			if (checkPassed)
			{
				triCol = CollisionChecker.getCollideTriangle();
				colPos = CollisionChecker.getCollidePosition();
				
				boolean bonked = false; 
				//check if you can smoothly transition from previous triangle to this triangle
				float dotProduct = Vector3f.dot(currNorm, CollisionChecker.getCollideTriangle().normal);
				if (dotProduct < 0.6)
				{
					//relativeXVel = relativeXVel*(dotProduct/(dotProduct+0.001f));
					//relativeZVel = relativeZVel*(dotProduct/(dotProduct+0.001f));
					relativeXVel = 0;
					relativeZVel = 0;
					
					//Vector3f wallNorm = CollisionChecker.getCollideTriangle().normal;
					//setPosition(CollisionChecker.getCollidePosition());
					//increasePosition(wallNorm.x*1f, wallNorm.y*1f, wallNorm.z*1f);
					//Vector3f slide = Maths.wallSlide(new Vector3f(xVel, yVel, zVel), wallNorm);
					//increasePosition(slide.x, slide.y, slide.z);
					
					bonked = true;
					currNorm = new Vector3f(0, 1, 0);
				}
				
				if (!bonked)
				{
					setPosition(CollisionChecker.getCollidePosition());
					currNorm = CollisionChecker.getCollideTriangle().normal;
					increasePosition(currNorm.x*1f, currNorm.y*1f, currNorm.z*1f);
				}
				airTimer = 0;
				onPlane = true;
			}
			else
			{
				CollisionChecker.falseAlarm();
				airTimer++;
				if (onPlane) //transition from on plane to off plane
				{
					relativeZVel = 0;
					relativeXVel = 0;
					xVelAir = xVel+xDisp;
					zVelAir = zVel+zDisp;
					yVel+=yDisp;
					xVel = 0;
					zVel = 0;
				}
				onPlane = false;
			}
		}
		
		if(airTimer == 1)
		{
			currNorm = new Vector3f(0, 1f, 0);
		}
		
		/* running on water test
		if (getY() < -2 && getY() > -10)
		{
			float totXspd = relativeXVel+xVelAir;
			float totZspd = relativeZVel+zVelAir;
			float spd = (float)Math.sqrt(totXspd*totXspd+totZspd*totZspd);
			
			if (spd > 3)
			{
				yVel *= 0.5;
				increasePosition(0, 0.5f, 0);
				
				if (onPlane)
				{
					relativeZVel = 0;
					relativeXVel = 0;
					xVelAir = xVel;
					zVelAir = zVel;
					xVel = 0;
					zVel = 0;
					onPlane = false;
				}
			}
		}
		*/
		
		if (getY() < -5)
		{
			inWater = true;
			waterHeight = 0;
		}
		
		if (getY() < -100)
		{
			MainGameLoop.shouldRestartLevel = true;
			AudioSources.play(34, getPosition());
			return;
		}
		
		if (!inWater && inWaterPrevious)
		{
			AudioSources.play(30, getPosition());
			new Particle(ParticleResources.textureSplash, new Vector3f(getX(), waterHeight+5, getZ()), new Vector3f(0,0,0), 0, 30, 0, 10, 0);
			yVel+=0.4f;
			
			float totXVel = xVel+xVelAir;
			float totZVel = zVel+zVelAir;
			int numBubbles = ((int)Math.abs(yVel*8))+18;
			for (int i = 0; i < numBubbles; i++)
			{
				float xOff = (float)(7*(Math.random()-0.5));
				float zOff = (float)(7*(Math.random()-0.5));
				new Particle(
						ParticleResources.textureBubble, 
						new Vector3f(getX()+xOff, waterHeight+2, getZ()+zOff), 
						new Vector3f((float)(Math.random()-0.5f+totXVel*0.4f),
									 (float)(Math.random()*0.3+0.2f+yVel*0.3),
									 (float)(Math.random()-0.5f+totZVel*0.4f)),
						0.05f, 60, 0, 4, 0);
			}
		}
		
		//underwater friction
		if (inWater && !inWaterPrevious)
		{
			AudioSources.play(30, getPosition());
			new Particle(ParticleResources.textureSplash, new Vector3f(getX(), waterHeight+5, getZ()), new Vector3f(0,0,0), 0, 30, 0, 10, 0);
			
			float totXVel = xVel+xVelAir;
			float totZVel = zVel+zVelAir;
			int numBubbles = ((int)Math.abs(yVel*8))+18;
			for (int i = 0; i < numBubbles; i++)
			{
				float xOff = (float)(7*(Math.random()-0.5));
				float zOff = (float)(7*(Math.random()-0.5));
				new Particle(
						ParticleResources.textureBubble, 
						new Vector3f(getX()+xOff, waterHeight+2, getZ()+zOff), 
						new Vector3f((float)(Math.random()-0.5f+totXVel*0.4f),
									 (float)(Math.random()*0.3+0.2f-yVel*0.3),
									 (float)(Math.random()-0.5f+totZVel*0.4f)),
						0.05f, 60, 0, 4, 0);
			}
			yVel = Math.max(yVel, -1);
			relativeXVel*=0.75f;
			relativeZVel*=0.75f;
			xVelAir*=0.75f;
			zVelAir*=0.75f;
		}
		
		if (inWater)
		{
			//yVel+=0.5*time;
			//applyFrictionAir();
			//applyFrictionAir();
			//applyFrictionAir();
			//applyFrictionAir();
			relativeXVel*=0.987f;
			relativeXVel*=0.987f;
			xVelAir*=0.987f;
			zVelAir*=0.987f;
		}
		
		if (specialInput && !previousSpecialInput && !isLightdashing)
		{
			isLightdashing = true;
		}
		
		if (isLightdashing)
		{
			attemptLightdash();
		}
		
		isGettingExternallyMoved = false;
		xDisp = 0;
		yDisp = 0;
		zDisp = 0;
		
		animate();
		
		inWaterPrevious = inWater;
		inWater = false;
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
	
	public void setxVelAir(float xVelAir)
	{
		this.xVelAir = xVelAir;
	}
	
	public float getXVelAir()
	{
		return xVelAir;
	}
	
	public void setzVelAir(float zVelAir)
	{
		this.zVelAir = zVelAir;
	}
	
	public float getZVelAir()
	{
		return zVelAir;
	}
	
	public void setHoverCount(int newCount)
	{
		hoverCount = newCount;
	}
	
	public void setOnPlane(boolean on)
	{
		onPlane = on;
	}
	
	/** If canMove is false, then every input from the 
	 * controller is ignored. It is up to whoever sets this 
	 * to false to set it back to true.
	 * 
	 * @param newMove
	 */
	public void setCanMove(boolean newMove)
	{
		canMove = newMove;
		//to prevent auto homing attack off a spring
		//if (newMove == true)
		{
			/*
			previousJumpInput = true;
			jumpInput = true;
			previousActionInput = true;
			actionInput = true;
			previousShoulderInput = true;
			shoulderInput = true;
			previousSelectInput = true;
			selectInput = true;
			specialInput = true;
			previousSpecialInput = true;
			previousAction2Input = true;
			action2Input = true;
			*/
		}
	}
	
	private void moveMeGround()
	{
		//float time = (float)DisplayManager.getFactor();
		float time = 1.0f;
		
		if (isBall == false)
		{
			relativeXVel += (float)(moveSpeedCurrent*Math.cos(Math.toRadians(cam.getYaw()+movementAngle)))*time;
			relativeZVel += (float)(moveSpeedCurrent*Math.sin(Math.toRadians(cam.getYaw()+movementAngle)))*time;
		}
		else
		{
			relativeXVel += (float)(moveSpeedCurrent*Math.cos(Math.toRadians(cam.getYaw()+movementAngle)))*0.8;
			relativeZVel += (float)(moveSpeedCurrent*Math.sin(Math.toRadians(cam.getYaw()+movementAngle)))*0.8;
		}
		
		float currSpeed = (float)Math.sqrt((relativeXVel*relativeXVel)+(relativeZVel*relativeZVel));
		float currDir = (float)Math.toDegrees(Math.atan2(relativeZVel, relativeXVel));
		
		if (moveSpeedCurrent > 0.01 && currSpeed > 0.5)
		{
			float worldSpaceMovementAngle = cam.getYaw()+movementAngle;
			float diff = compareTwoAngles(worldSpaceMovementAngle, currDir);
			
			float newDiff = diff/8;
			
			float newAngle = currDir+newDiff;
			
			if (Math.abs(diff) > 120)
			{
				relativeXVel*=0.97*time;
				relativeZVel*=0.97*time;
			}
			else
			{
				relativeXVel = (float)(currSpeed*Math.cos(Math.toRadians(newAngle)));
				relativeZVel = (float)(currSpeed*Math.sin(Math.toRadians(newAngle)));
				
				//float factor = 1-(Math.abs(diff)/18000);
				//relativeXVel*=factor;
				//relativeZVel*=factor;
			}
		}
	}
	
	private void spindash(int timer)
	{
		float mag = spindashPower*timer;
		relativeXVel += (float)Math.cos(spindashAngle)*mag;
		relativeZVel -= (float)Math.sin(spindashAngle)*mag;
		//AudioRes.playSound(52, 1.0f, getPosition());
		//AudioRes.stopSound(51);
		isBall = true;
		AudioSources.play(8, getPosition());
	}
	
	private void homingAttack()
	{
		if (characterID == 0) //No classic sonic
		{
			//return;
		}
		
		float homingPower = 5.5f;
		float angle = -cam.getYaw()-movementAngle;
		
		if (moveSpeedCurrent < 0.05)
		{
			angle = getRotY();
		}
		
		if (characterID == 2)
		{
			//find nearest ring
			Entity closest = null;
			float dist = 12000; //distance squared
			float myX = getX();
			float myZ = getZ();
			float myY = getY();
			for (Entity e: MainGameLoop.gameEntities)
			{
				if (e instanceof Motobug ||
					e instanceof ItemCapsule ||
					e instanceof RhinoTank ||
					e instanceof Spinner ||
					e instanceof Spring)
				{
					float xDiff = e.getX()-myX;
					float zDiff = e.getZ()-myZ;
					float yDiff = myY-e.getY();
					float newDist = xDiff*xDiff+zDiff*zDiff+yDiff*yDiff;
					if (yDiff > -6 && newDist < dist)
					{
						dist = newDist;
						closest = e;
					}
				}
			}
			
			if (closest != null)
			{
				//double newDirection = Math.atan2(zDiff, -xDiff);
				//xVelAir = (float)(1.5*Math.cos(newDirection));
				//zVelAir = (float)(-1.5*Math.sin(newDirection));
				
				float xDiff = -(myX-closest.getX());
				float zDiff = -(myZ-closest.getZ());
				float yDiff = -(myY-closest.getY());
				
				Vector3f unitDir = new Vector3f(xDiff, yDiff+3.5f, zDiff);
				unitDir = unitDir.normalise(unitDir);
				homingPower = 6.7f;
				
				unitDir.x*= homingPower;
				unitDir.y*= homingPower;
				unitDir.z*= homingPower;
				//angle = (float)(Math.PI+(Math.atan2(closest.getZ()-myZ, -(closest.getX()-myX))));
				
				//xVelAir = (float)Math.cos((angle))*homingPower;
				//zVelAir = -(float)Math.sin((angle))*homingPower;
				
				//float hyp = (float)Math.sqrt(xVelAir*xVelAir+zVelAir*zVelAir);
				
				xVelAir = unitDir.x;
				yVel = unitDir.y;
				zVelAir = unitDir.z;
			}
			else
			{
				xVelAir = (float)Math.cos(Math.toRadians(angle))*homingPower;
				zVelAir = -(float)Math.sin(Math.toRadians(angle))*homingPower;
			}
		}
		else
		{
			xVelAir = (float)Math.cos(Math.toRadians(angle))*homingPower;
			zVelAir = -(float)Math.sin(Math.toRadians(angle))*homingPower;
		}
		
		homingAttackTimer = homingAttackTimerMax;
		isBall = false;
		isJumping = true;
		isBouncing = false;
		isStomping = false;
		AudioSources.play(25, getPosition());
	}
	
	private void initiateBounce()
	{
		if (characterID != 2) //Only Mecha sonic
		{
			//return;
		}
		//xVelAir*=0.99f;
		//zVelAir*=0.99f;
		if (yVel >= -4.5f)
		{
			yVel = -4.5f;
		}
		else
		{
			yVel-=1;
		}
		
		hoverCount = 0;
		
		isBouncing = true;
		isStomping = false;
		isJumping = false;
		isBall = false;
	}
	
	private void initiateStomp()
	{
		if (characterID != 2) //Only Mecha sonic
		{
			//return;
		}

		if (yVel >= -4f)
		{
			yVel = -4f;
		}
		else
		{
			yVel-=2f;
		}
		
		hoverCount = 0;
		
		isBouncing = false;
		isStomping = true;
		isJumping = false;
		isBall = false;
		
		stompSource = AudioSources.play(32, getPosition());
		//shouldStopStomp = true;
	}
	
	//attempt to continue a lightdash
	//if lightdash cant continue, sets isLightdashing
	//to false
	private void attemptLightdash()
	{
		if (MainGameLoop.gameClock%2 != 0)
		{
			return;
		}
		//find nearest ring
		Ring closest = null;
		float dist = 2000; //distance squared
		float myX = getX();
		float myZ = getZ();
		float myY = getY();
		for (Entity e: MainGameLoop.gameEntities)
		{
			if (e instanceof Ring)
			{
				float xDiff = e.getX()-myX;
				float zDiff = e.getZ()-myZ;
				float yDiff = e.getY()-myY;
				float newDist = xDiff*xDiff+zDiff*zDiff+yDiff*yDiff;
				if (newDist < dist)
				{
					dist = newDist;
					closest = (Ring)e;
				}
			}
		}
		
		
		//check nearest distance
		//System.out.println("dist = "+dist);
		//passes threshold?
		if (dist < 2000)
		{
			onPlane = false;

			xVel = 0;
			zVel = 0;
			relativeXVel = 0;
			relativeZVel = 0;
			
			//set xVelAir and zVelAir to the direction to the ring
			Vector3f diff = new Vector3f(closest.getX()-myX, closest.getY()-myY, closest.getZ()-myZ);
			diff = diff.normalise(null);
			
			xVelAir = diff.x*5;
			zVelAir = diff.z*5;
			yVel = diff.y*5;
			//yVel = 0;
			
			//move to ring location
			setX(closest.getX());
			setY(closest.getY());
			setZ(closest.getZ());
			
			//myCameraYawTarget = (float)(Math.toDegrees(Math.atan2(diff.z, diff.x)))+90;
		}
		else
		{
			//doesnt pass threshold?
			isLightdashing = false;
		}
	}
	
	private void bounceOffGround(Vector3f surfaceNormal)
	{
		Vector3f V = new Vector3f(xVelAir, yVel, zVelAir);
		Vector3f N = new Vector3f(surfaceNormal);
		float b = 0.75f;
		
		//Vector3f twoNtimesVdotN = new Vector3f(N);
		//twoNtimesVdotN.scale(-2*Vector3f.dot(V, N));
		
		//Vector3f Vnew = Vector3f.add(twoNtimesVdotN, V, null);
		//Vnew.scale(b);
		Vector3f Vnew = Maths.bounceVector(V, N, b);
		
		xVelAir = Vnew.x;
		yVel    = Vnew.y;
		zVelAir=  Vnew.z;
		
		xVel = 0;
		zVel = 0;
		
		isBall = true;
		isBouncing = false;
		isStomping = false;
		homingAttackTimer = -1;
		hoverCount = 0;
		AudioSources.play(26, getPosition());
	}
	
	private void moveMeAir()
	{
		//float time = (float)DisplayManager.getFactor();
		float time = 1.0f;
				
		xVelAir += (float)(moveSpeedAirCurrent*Math.cos(Math.toRadians(cam.getYaw()+movementAngle)))*time;
		zVelAir += (float)(moveSpeedAirCurrent*Math.sin(Math.toRadians(cam.getYaw()+movementAngle)))*time;
		
		float currSpeed = (float)Math.sqrt((xVelAir*xVelAir)+(zVelAir*zVelAir));
		float currDir = (float)Math.toDegrees(Math.atan2(zVelAir, xVelAir));
		
		if (moveSpeedAirCurrent > 0.01 && currSpeed > 0.5)
		{
			float worldSpaceMovementAngle = cam.getYaw()+movementAngle;
			float diff = compareTwoAngles(worldSpaceMovementAngle, currDir);
			
			float newDiff = diff/8;
			
			float newAngle = currDir+newDiff;

			//if (Math.abs(diff) > 120)
			{
				//xVelAir*=0.97*time;
				//zVelAir*=0.97*time;
			}
			//else
			{
				xVelAir = (float)(currSpeed*Math.cos(Math.toRadians(newAngle)));
				zVelAir = (float)(currSpeed*Math.sin(Math.toRadians(newAngle)));
				
				float factor = 1-(Math.abs(diff)/900);
				//System.out.println(factor);
				xVelAir*=factor*time;
				zVelAir*=factor*time;
			}
		}
	}
	
	private void applyFriction(float frictionToApply)
	{
		//float time = (float)DisplayManager.getFactor();
		float time = 1.0f;
		
		if (isBall)
		{
			frictionToApply*=0.11;
		}
		
		float mag = (float)Math.sqrt((relativeXVel*relativeXVel)+(relativeZVel*relativeZVel));
		if (mag!=0)
		{
		    int before = Maths.sign(relativeZVel);
		    relativeZVel-=(((frictionToApply)*(relativeZVel))/(mag))*time;
		    int after = Maths.sign(relativeZVel);
		    if(before != after)
		    {
		    	relativeZVel = 0;
		    }
		    before = Maths.sign(relativeXVel);
		    relativeXVel-=(((frictionToApply)*(relativeXVel))/(mag))*time;
		    after = Maths.sign(relativeXVel);
		    if(before != after)
		    {
		    	relativeXVel = 0;
		    }
		}
	}
	
	private void applyFrictionAir()
	{
		//float time = (float)DisplayManager.getFactor();
		float time = 1.0f;
				
		float mag = (float)Math.sqrt((relativeXVel*relativeXVel)+(relativeZVel*relativeZVel));
		if(mag!=0)
		{
		    int before = Maths.sign(relativeZVel);
		    relativeZVel-=(((frictionAir)*(relativeZVel))/(mag))*time;
		    int after = Maths.sign(relativeZVel);
		    if(before != after)
		    {
		    	relativeZVel = 0;
		    }
		    before = Maths.sign(relativeXVel);
		    relativeXVel-=(((frictionAir)*(relativeXVel))/(mag))*time;
		    after = Maths.sign(relativeXVel);
		    if(before != after)
		    {
		    	relativeXVel = 0;
		    }
		}
		
		mag = (float)Math.sqrt((xVelAir*xVelAir)+(zVelAir*zVelAir));
		if(mag!=0)
		{
		    int before = Maths.sign(zVelAir);
		    zVelAir-=(((frictionAir)*(zVelAir))/(mag))*time;
		    int after = Maths.sign(zVelAir);
		    if(before != after)
		    {
		    	zVelAir = 0;
		    }
		    before = Maths.sign(xVelAir);
		    xVelAir-=(((frictionAir)*(xVelAir))/(mag))*time;
		    after = Maths.sign(xVelAir);
		    if(before != after)
		    {
		    	xVelAir = 0;
		    }
		}
	}
	
	private void limitMovementSpeed(float limit)
	{
		//float time = (float)DisplayManager.getFactor();
		float time = 1.0f;
		
		if (isBall)
		{
			limit = limit*0.1f;
		}
		
		float myspeed = (float)Math.sqrt(relativeXVel*relativeXVel+relativeZVel*relativeZVel);
		//System.out.print("speed = "+myspeed);
		if(myspeed > limit)
		{
			//System.out.print("   OVER");
			relativeXVel = relativeXVel*((myspeed-slowDownRate)/(myspeed))*time;
			relativeZVel = relativeZVel*((myspeed-slowDownRate)/(myspeed))*time;
		}
		//System.out.println();
	}
	
	private void limitMovementSpeedAir()
	{
		//float time = (float)DisplayManager.getFactor();
		float time = 1.0f;
		
		float myspeed = (float)Math.sqrt(xVelAir*xVelAir+zVelAir*zVelAir);
		if(myspeed > airSpeedLimit)
		{
			xVelAir = (xVelAir*((myspeed-slowDownAirRate)/(myspeed)))*time;
			zVelAir = (zVelAir*((myspeed-slowDownAirRate)/(myspeed)))*time;
		}
	}
	
	public Vector3f mapInputs(float angle, float mag, Vector3f VecC)
	{
		//float inputX = vecA.x;
		//float inputY = vecA.z;
		//float mag = (float)Math.sqrt(inputX*inputX+inputY*inputY);
		//System.out.println(mag);
		
		
		//double inputDir = Math.atan2(inputY, inputX)+Math.toRadians(cam.getYaw());
		
		//Original A
		float Ax = (float)(Math.cos(angle)*mag);
		float Az = (float)(Math.sin(angle)*mag);
		float Ay = 0;
		
		
		//Vector C
		//Vector3f gravity = new Vector3f(currNorm.negate(null));
		float Cx = VecC.x;
		float Cy = VecC.y;
		float Cz = VecC.z;
		
		float CYaw = (float)Math.atan2(Cz, Cx);
		
		
		//1st step
		float tempX = Ax;
	    float tempZ = Az;
		//rotation on the y axis
		Ax = (float) (tempX * Math.cos(-CYaw) + tempZ * Math.sin(-CYaw));
		Az = (float) (tempZ * Math.cos(-CYaw) - tempX * Math.sin(-CYaw));
		
		tempX = Cx;
	    tempZ = Cz;
		//rotation on the y axis
		Cx = (float) (tempX * Math.cos(-CYaw) + tempZ * Math.sin(-CYaw));
		Cz = (float) (tempZ * Math.cos(-CYaw) - tempX * Math.sin(-CYaw));
		
		
		
		float CPitch = (float)(Math.PI/2)+(float)Math.atan2(Cy, Cx);
		//2nd step
		tempX = Ax;
	    float tempY = Ay;
	    //rotation around z axis
	    Ax = (float) (tempX * Math.cos(CPitch) - tempY * Math.sin(CPitch));
	    Ay = (float) (tempX * Math.sin(CPitch) + tempY * Math.cos(CPitch));
				
				
	    //step 3 
	    tempX = Ax;
	    tempZ = Az;
		//rotation on the y axis
		Ax = (float) (tempX * Math.cos(CYaw) + tempZ * Math.sin(CYaw));
		Az = (float) (tempZ * Math.cos(CYaw) - tempX * Math.sin(CYaw));
		
		return new Vector3f(Ax, Ay, Az);
	}
	
	
	public Vector3f mapInputs2(float angle, float mag, Vector3f VecC)
	{
		float roty = (float)Math.atan2(VecC.z, VecC.x);
		float rotz = (float)Math.atan2(VecC.y, Math.sqrt(VecC.x*VecC.x+VecC.z*VecC.z));
		Matrix4f transMat = Maths.createTransformationMatrix(new Vector3f(0,0,0), 0, roty, rotz, 1);
		
		
		float tempx = (float)Math.cos(angle)*mag;
		float tempz = (float)Math.sin(angle)*mag;
		Vector4f ret = Maths.multiplyByMat(new Vector4f(tempx, 0, tempz, 0), transMat);
		return new Vector3f(ret.x, ret.y, ret.z);
	}
	
	public Vector3f mapInputs3(float angle, float mag, Vector3f VecC)
	{
		float invert = 1;
		if(VecC.y > 0) //trying to fix upside down problems just causes new ones
		{
			//invert = -1;
			//VecC = VecC.negate(null);
			//angle+=Math.PI;
			//angle = angle*-1;
		}
		angle = angle % (float)(Math.PI*2);
		float tempx = (float)Math.cos(angle)*mag;
		float tempz = (float)Math.sin(angle)*mag;
		
		float CDir = (float)Math.atan2(VecC.z, VecC.x);
		CDir+=Math.PI/2;
		float Cx = (float)Math.cos(CDir);
		float Cz = (float)Math.sin(CDir);
		
		float CDist = (float)Math.sqrt(VecC.x*VecC.x+VecC.z*VecC.z);
		float CPitch = (float)(Math.PI/2+Math.atan2(VecC.y, CDist));
		
		double[] result = RotationMatrixUnnormalizedDirVector.rotPointFromFormula(0, 0, 0, Cx, 0, Cz, tempx, 0, tempz, CPitch);
		Vector3f res = new Vector3f((float)result[0]*invert, (float)result[1]*invert, (float)result[2]*invert);
		return res;
	}
	
	/**
     * Calculate the x and z speeds relative to a plane based off
     * the previous position you are coming in from
     *
     * @param xspd the x speed that you are going at before collision
     * @param yspd the y speed that you are going at before collision
     * @param zspd the z speed that you are going at before collision
     * @param A the collision point on the triangle
     * @param normal the normal of the triangle
     */
	public Vector3f calculatePlaneSpeed(float xspd, float yspd, float zspd, Vector3f A, Vector3f normal)
	{
		
		//return Maths.wallSlide(new Vector3f(xspd,  yspd, zspd), normal);
		
		
		
		float normY = normal.y;
		if(normY > -0.01 && normY < 0.01)
		{
			normY = 0.01f;
		}
		
		float normX = normal.x;
		if(normX == 0)
		{
			normX = 0.00001f;
		}
		
		float normZ = normal.z;
		if(normZ == 0)
		{
			normZ = 0.00001f;
		}
		
		if (normY < 0)
		{
			//normY = normY*-1;
			//normZ = normZ*-1;
			//normX = normX*-1;
			//xspd*=-1;
			//zspd*=-1;
		}
		
		//find the relative x speed
		float xDiff = xspd;
		float yDiff = yspd;
		float dist = (float)Math.sqrt(xDiff*xDiff+yDiff*yDiff);
		float angle1 = (float)Math.atan2(-normY, -normX);
		float angle2 = (float)Math.atan2(yDiff, xDiff);
		float actualAngle = angle2-angle1;
		float xLength = (float)Math.sin(actualAngle)*dist;
		
		//find the relative z speed
		float zDiff = zspd;//A.z-B.z;
		dist = (float)Math.sqrt(zDiff*zDiff+yDiff*yDiff);
		angle1 = (float)Math.atan2(-normY, -normZ);
		angle2 = (float)Math.atan2(yDiff, zDiff);
		actualAngle = angle2-angle1;
		float zLength = (float)Math.sin(actualAngle)*dist;
		
		return new Vector3f(xLength, 0, zLength);
		
	}
	
	private void adjustCamera()
	{
		//float time = (float)DisplayManager.getFactor();
		float time = 1.0f;
		
		cameraRadius += (cameraRadiusTarget-cameraRadius)/20;

		cameraRadiusTarget+=zoomInput*time;
		cameraRadiusTarget = Math.max(cameraRadiusZoom, cameraRadiusTarget);
		cameraRadiusTarget = Math.min(cameraRadiusZoomOut, cameraRadiusTarget);
		
		
		
		
		if(!firstPerson && !MainGameLoop.freeCamera)
		{
			float xDiff = cam.getX()-getX();
			float zDiff = cam.getZ()-getZ();
			float angleRad = (float)Math.toRadians(getRotY());
			float newZ = (float)(-(zDiff)*Math.cos(angleRad) - (xDiff)*Math.sin(angleRad));
			//float newX = (float)((xDiff)*Math.cos(angleRad) - (zDiff)*Math.sin(angleRad));
			
			myCameraYawTarget-=(newZ/65)*time;
		}

		
		myCameraPitchTarget+=cameraInputY*time;
		myCameraYawTarget+=cameraInputX*time;
		
		
		cam.setYaw(cam.getYaw()+ (myCameraYawTarget-cam.getYaw())/cameraLaziness);
		cam.setPitch(cam.getPitch()+ (myCameraPitchTarget-cam.getPitch())/cameraLaziness);
		
		
		
		float camRadius = cameraRadius;
		Vector3f headPos = new Vector3f(getPosition().getX()+currNorm.x*headHeight, 
										getPosition().getY()+currNorm.y*headHeight, 
										getPosition().getZ()+currNorm.z*headHeight);
		
		Vector3f camPos = new Vector3f(
				headPos.x+(float)(Math.cos(Math.toRadians(cam.getYaw()+90))*(camRadius*(Math.cos(Math.toRadians(cam.getPitch()))))),
				headPos.y-(float)(Math.sin(Math.toRadians(cam.getPitch()+180))*camRadius), 
				headPos.z+(float)(Math.sin(Math.toRadians(cam.getYaw()+90))*(camRadius*(Math.cos(Math.toRadians(cam.getPitch()))))));
		
		if(CollisionChecker.checkCollision(getPosition(), headPos) == true)
		{
			Vector3f colPos = CollisionChecker.getCollidePosition();
			
			Vector3f diff = Vector3f.sub(colPos, getPosition(), null);
			float newHeadHeight = diff.length()-1;
			
			//System.out.println("headhgith = "+newHeadHeight);
			
			headPos = new Vector3f(getPosition().getX()+currNorm.x*newHeadHeight, 
					getPosition().getY()+currNorm.y*newHeadHeight, 
					getPosition().getZ()+currNorm.z*newHeadHeight);
		}
		else if(CollisionChecker.checkCollision(headPos, camPos) == true)
		{
			//System.out.println("collision");
			Vector3f colPos = CollisionChecker.getCollidePosition();
			
			Vector3f diff = Vector3f.sub(colPos, camPos, null);
			float newRadius = (cameraRadius-4)-diff.length();
			
			camPos = new Vector3f(
					headPos.x+(float)(Math.cos(Math.toRadians(cam.getYaw()+90))*(newRadius*(Math.cos(Math.toRadians(cam.getPitch()))))),
					headPos.y-(float)(Math.sin(Math.toRadians(cam.getPitch()+180))*newRadius),
					headPos.z+(float)(Math.sin(Math.toRadians(cam.getYaw()+90))*(newRadius*(Math.cos(Math.toRadians(cam.getPitch()))))));
					
		}
		
		cam.setPosition(camPos);
		
		if(zoomInput > 0)
		{
			//firstPerson = true;
		}
		else if(zoomInput < 0)
		{
			//firstPerson = false;
		}
		
		if(firstPerson == true)
		{
			cam.setPosition(headPos);
		}
	}
	
	private void animate()
	{
		float xrel = relativeXVel+xVelAir;
		float zrel = relativeZVel+zVelAir;
		float ang = (float)Math.atan2(zrel, xrel);
		Vector3f ans = Maths.mapInputs3(ang, 10, currNorm.negate(null));
		float yrot = (float)Math.toDegrees(Math.atan2(-ans.z, ans.x));
		float dist = (float)(Math.sqrt(ans.x*ans.x+ans.z*ans.z));
		float zrot = (float)Math.toDegrees(Math.atan2(ans.y, dist));
		
		Vector3f displayPos = new Vector3f(getX(), getY()+displayHeightOffset, getZ());
		float dispX = displayPos.x;
		float dispY = displayPos.y;
		float dispZ = displayPos.z;
		
		Vector3f displayPosPrev = new Vector3f(previousPos);
		displayPosPrev.y+=displayHeightOffset;
		
		float mySpeed = (float)Math.sqrt(xrel*xrel+zrel*zrel);
		
		if (mySpeed > 0.01)
		{
			myBody.setBaseOrientation(displayPos, yrot, zrot);
			setRotY(yrot);
			setRotZ(zrot);
		}
		else
		{
			myBody.setBaseOrientation(displayPos, getRotY(), getRotZ());
		}
		
		float modelIncreaseVal = (mySpeed)*0.3f;
		modelRunIndex+=modelIncreaseVal;
		if(modelRunIndex >= 20)
		{
			modelRunIndex=modelRunIndex % 20;
		}
		if(mySpeed == 0)
		{
			modelRunIndex = 0;
		}
		
		//myBall.setVisibility(false);
		
		
		if (homingAttackTimer > 0)
		{
			float height = 2;
			Vector3f offset = new Vector3f(currNorm.x*height, currNorm.y*height, currNorm.z*height);
			Vector3f prevPos = new Vector3f(previousDisplayPos);
			Vector3f.add(prevPos, offset, prevPos);
			Vector3f newPos = new Vector3f(displayPos);
			Vector3f.add(newPos, offset, newPos);
			createSpindashTrails(prevPos, newPos, 5, 20);
		}
		
		if (isStomping)
		{
			float h = (float)Math.sqrt(xVelAir*xVelAir+zVelAir*zVelAir);
			float zr = (float)(Math.toDegrees(Math.atan2(yVel, h)));
			myBody.setBaseOrientation(displayPos, getRotY(), zr+90);
			
			float height = 2;
			Vector3f offset = new Vector3f(currNorm.x*height, currNorm.y*height, currNorm.z*height);
			Vector3f prevPos = new Vector3f(previousDisplayPos);
			Vector3f.add(prevPos, offset, prevPos);
			Vector3f newPos = new Vector3f(displayPos);
			Vector3f.add(newPos, offset, newPos);
			createSpindashTrails(prevPos, newPos, 5, 20);
	    	updateLimbs(3, 100);
		}
		else if (isBouncing)
		{
			myBody.setBaseOrientation(displayPos, getRotY(), getRotZ()-count*60);
			float height = 2;
			Vector3f offset = new Vector3f(currNorm.x*height, currNorm.y*height, currNorm.z*height);
			Vector3f prevPos = new Vector3f(previousDisplayPos);
			Vector3f.add(prevPos, offset, prevPos);
			Vector3f newPos = new Vector3f(displayPos);
			Vector3f.add(newPos, offset, newPos);
			createSpindashTrails(prevPos, newPos, 5, 20);
			updateLimbs(12, 0);
		}
		else if (isJumping)
		{
			myBody.setBaseOrientation(displayPos, getRotY(), getRotZ()-count*35);
			//float height = 2;
			//Vector3f pos = new Vector3f(getX()+currNorm.x*height, getY()+currNorm.y*height, getZ()+currNorm.z*height);
			//MainGameLoop.gameEntitiesToAdd.add(new SpindashTrail(pos, 20));
	    	updateLimbs(12, 0);
		}
		else if (isBall)
		{
			myBody.setBaseOrientation(displayPos, getRotY(), getRotZ()-count*70);
			float height = 2;
			Vector3f offset = new Vector3f(currNorm.x*height, currNorm.y*height, currNorm.z*height);
			Vector3f prevPos = new Vector3f(previousDisplayPos);
			Vector3f.add(prevPos, offset, prevPos);
			Vector3f newPos = new Vector3f(displayPos);
			Vector3f.add(newPos, offset, newPos);
			createSpindashTrails(prevPos, newPos, 5, 20);
			updateLimbs(12, 0);
		}
		else if(isSpindashing)
		{
			setRotY((float)Math.toDegrees(spindashAngle));
			float zrotoff = -(spindashTimer*spindashTimer*0.8f); //0.5
			if(spindashTimer >= spindashTimerMax)
			{
				zrotoff = -(count*50);//30
			}
			myBody.setBaseOrientation(displayPos, getRotY(), getRotZ()+zrotoff);
	    	updateLimbs(12, 0);
		}
		else if(spindashReleaseTimer > 0)
		{
			float zrotoff = (spindashReleaseTimer*spindashReleaseTimer*0.8f); //0.5
			//zrotoff = -count*40; //different look, might look better?
			myBody.setBaseOrientation(displayPos, getRotY(), getRotZ()+zrotoff);
	    	updateLimbs(12, 0);
		}
		else if(onPlane && mySpeed < 0.01)
		{
	    	float time =  (count * 1f) % 100;
	    	updateLimbs(0, time);
		}
		else if(isSkidding)
		{
			myBody.setBaseOrientation(displayPos, getRotY(), getRotZ());
	    	updateLimbs(8, 0);
		}
		else if (hitTimer > 0)
		{
			myBody.setBaseOrientation(displayPos, getRotY(), getRotZ());
			updateLimbs(11, 0);
		}
		else
		{
			myBody.setBaseOrientation(displayPos, getRotY(), getRotZ());
	    	float time = 10 * modelRunIndex * 0.5f;
	    	updateLimbs(1, time);
		}
		
		if (firstPerson) //dont do this every frame if you dont have to 
		{
			setLimbsVisibility(false);
		}
		else
		{
			if (iFrame % 4 == 2)
			{
				setLimbsVisibility(false);
			}
			else if (iFrame % 4 == 0)
			{
				setLimbsVisibility(true);
			}
			
			//if ((isJumping || isBall) && MainGameLoop.gameClock % 51 == 13)
			{
				//setLimbsVisibility(false);
				//myBall.setVisibility(true);
			}
			//else if (MainGameLoop.gameClock % 51 == 0)
			{
				//setLimbsVisibility(true);
				//myBall.setVisibility(false);
			}
		}
		
		if ((isJumping || isBall) == false)
		{
			//myBall.setVisibility(false);
		}
		//myBall.setPosition(new Vector3f(getX(), getY()+1.5f, getZ()));
		//myBall.setRotY(getRotY());
		//myBall.setRotZ(MainGameLoop.gameClock*(-30));
		
		previousDisplayPos.set(displayPos);
	}
	
	private void setMovementInputs()
	{
		jumpInput = Input.jumpInput;
		actionInput = Input.actionInput;
		action2Input = Input.action2Input;
		shoulderInput = Input.shoulderInput;
		selectInput = Input.selectInput;
		specialInput = Input.specialInput;
		
		previousJumpInput = Input.previousJumpInput;
		previousActionInput = Input.previousActionInput;
		previousAction2Input = Input.previousAction2Input;
		previousShoulderInput = Input.previousShoulderInput;
		previousSelectInput = Input.previousSelectInput;
		previousSpecialInput = Input.previousSpecialInput;
		
		movementInputX = Input.movementInputX;
		movementInputY = Input.movementInputY;
		cameraInputX = Input.cameraInputX;
		cameraInputY = Input.cameraInputY;
		
		zoomInput = Input.zoomInput;
		
		float inputMag = (float)Math.sqrt(movementInputX*movementInputX + movementInputY*movementInputY);
		moveSpeedCurrent = moveAcceleration*inputMag;
		moveSpeedAirCurrent = moveAccelerationAir*inputMag;
		movementAngle = (float)Math.toDegrees(Math.atan2(movementInputY, movementInputX));
		
		
		if (canMove == false || hitTimer > 0)
		{
			jumpInput = false;
			actionInput = false;
			action2Input = false;
			shoulderInput = false;
			selectInput = false;
			specialInput = false;
			previousJumpInput = false;
			previousActionInput = false;
			previousAction2Input = false;
			previousShoulderInput = false;
			previousSelectInput = false;
			previousSpecialInput = false;
			movementInputX = 0;
			movementInputY = 0;
			//cameraInputY = 0;
			//cameraInputX = 0;
			moveSpeedCurrent = 0;
			moveSpeedAirCurrent = 0;
			movementAngle = 0;
		}
	}
	
	private void calcSpindashAngle()
	{
		float inputMag = (float)Math.sqrt(movementInputX*movementInputX + movementInputY*movementInputY);
		if (inputMag >= 0.3)
		{
			spindashAngle = (float)((Math.toRadians(-cam.getYaw()-movementAngle)));
		}
		else
		{
			spindashAngle = (float)((Math.toRadians(getRotY())));
		}
	}
	
	private void checkSkid()
	{
		boolean prevSkid = isSkidding;
		isSkidding = false;
		if(currNorm.y > 0.8)
		{
			float degAngle = movementAngle;
			
			degAngle=(-degAngle-myCameraYawTarget) % 360;
	
			if(degAngle < 0)
			{
				degAngle+=360;
			}
	
			float myAngle = getRotY();
	
			if(myAngle < 0)
			{
				myAngle+=360;
			}
			
			if((Math.abs(myAngle-degAngle) <= 220) && Math.abs(myAngle-degAngle) >= 140) //original 220 and 140
			{
				if(movementInputX != 0 || movementInputY != 0)
				{
					isSkidding = true;
					float currSpeed = xVel*xVel+zVel*zVel;
					if(!prevSkid && onPlane && currSpeed > 2)
					{
						//AudioRes.playSound(53, 1, getPosition());
						AudioSources.play(9, getPosition());
					}
				}
			}
		}
	}
	
	//Returns the difference between the two angles
	//ang1 and ang2 should be in degrees
	private float compareTwoAngles(float ang1, float ang2)
	{
		ang1 = ((ang1 % 360) + 360) % 360;
		ang2 = ((ang2 % 360) + 360) % 360;
		
		//return 180 - Math.abs(Math.abs(ang1-ang2) - 180);
		
		
		//if(ang1 > ang2)
		//{
			//return 360 - ang2 - ang1;
		//}
		
		//return ang2 - ang1;
		
		float d = Math.abs(ang1 - ang2) % 360; 
		float r = d > 180 ? 360 - d : d;

		//calculate sign 
		int sign = (ang1 - ang2 >= 0 && ang1 - ang2 <= 180) || (ang1 - ang2 <=-180 && ang1- ang2>= -360) ? 1 : -1;
		r *= sign;
		
		return r;
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
    	
    	//MainGameLoop.gameEntitiesToAdd.add(myBall);
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
    	
    	//MainGameLoop.gameEntitiesToDelete.add(myBall);
	}
	
	private void createLimbs()
	{
		if (characterID == 0) //Classic Sonic
		{
			displayHeightOffset = 0;
			//displayHeightOffset = -2.5f; sanix
			myBody =        new Body(modelBody);
			myHead =        new Limb(modelHead,         0, 1.3f, 0,       myBody, null);
			myLeftHumerus = new Limb(modelLeftHumerus,  0, 0.9f, -0.9f,   myBody, null);
			myLeftForearm = new Limb(modelLeftForearm,  1.3f, 0, 0, null, myLeftHumerus);
			myLeftHand =    new Limb(modelLeftHand,     1.3f, 0, 0, null, myLeftForearm);
			myLeftThigh =   new Limb(modelLeftThigh,    0, -0.9f, -0.3f,  myBody, null);
			myLeftShin =    new Limb(modelLeftShin,     1.1f, 0, 0, null, myLeftThigh);
			myLeftFoot =    new Limb(modelLeftFoot,     1.1f, 0, 0, null, myLeftShin);
			myRightHumerus =new Limb(modelRightHumerus, 0, 0.9f, 0.9f,    myBody, null);
			myRightForearm =new Limb(modelRightForearm, 1.3f, 0, 0, null, myRightHumerus);
			myRightHand =   new Limb(modelRightHand,    1.3f, 0, 0, null, myRightForearm);
			myRightThigh =  new Limb(modelRightThigh,   0, -0.9f, 0.3f,   myBody, null);
			myRightShin =   new Limb(modelRightShin,    1.1f, 0, 0, null, myRightThigh);
			myRightFoot =   new Limb(modelRightFoot,    1.1f, 0, 0, null, myRightShin);
		}
		else if (characterID == 1) //Sonic Doll
		{
			displayHeightOffset = -0.525f;
			myBody =        new Body(modelBody);
			myHead =        new Limb(modelHead,         0.7f, 1.4f, 0,       myBody, null);
			myLeftHumerus = new Limb(modelLeftHumerus,  0, 0.9f, -0.9f,   myBody, null);
			myLeftForearm = new Limb(modelLeftForearm,  0.92f, 0, 0, null, myLeftHumerus);
			myLeftHand =    new Limb(modelLeftHand,     0.62f, 0, 0, null, myLeftForearm);
			myLeftThigh =   new Limb(modelLeftThigh,    0, -0.9f, -0.3f,  myBody, null);
			myLeftShin =    new Limb(modelLeftShin,     1.07f, 0, 0, null, myLeftThigh);
			myLeftFoot =    new Limb(modelLeftFoot,     1.23f, 0, 0, null, myLeftShin);
			myRightHumerus =new Limb(modelRightHumerus, 0, 0.9f, 0.9f,    myBody, null);
			myRightForearm =new Limb(modelRightForearm, 0.92f, 0, 0, null, myRightHumerus);
			myRightHand =   new Limb(modelRightHand,    0.62f, 0, 0, null, myRightForearm);
			myRightThigh =  new Limb(modelRightThigh,   0, -0.9f, 0.3f,   myBody, null);
			myRightShin =   new Limb(modelRightShin,    1.07f, 0, 0, null, myRightThigh);
			myRightFoot =   new Limb(modelRightFoot,    1.23f, 0, 0, null, myRightShin);
		}
		else if (characterID == 2) //Silver Sonic II
		{
			displayHeightOffset = 0.8f;
			myBody =        new Body(modelBody);
			myHead =        new Limb(modelHead,         0, 1.15f, 0,       myBody, null);
			myLeftHumerus = new Limb(modelLeftHumerus,  0, 0.9f, -0.9f,   myBody, null);
			myLeftForearm = new Limb(modelLeftForearm,  1.5f, 0, 0, null, myLeftHumerus);
			myLeftHand =    new Limb(modelLeftHand,     1.9f, 0, 0, null, myLeftForearm);
			myLeftThigh =   new Limb(modelLeftThigh,    0, -0.9f, -0.3f,  myBody, null);
			myLeftShin =    new Limb(modelLeftShin,     1.47f, 0, 0, null, myLeftThigh);
			myLeftFoot =    new Limb(modelLeftFoot,     1.21f, 0, 0, null, myLeftShin);
			myRightHumerus =new Limb(modelRightHumerus, 0, 0.9f, 0.9f,    myBody, null);
			myRightForearm =new Limb(modelRightForearm, 1.5f, 0, 0, null, myRightHumerus);
			myRightHand =   new Limb(modelRightHand,    1.9f, 0, 0, null, myRightForearm);
			myRightThigh =  new Limb(modelRightThigh,   0, -0.9f, 0.3f,   myBody, null);
			myRightShin =   new Limb(modelRightShin,    1.47f, 0, 0, null, myRightThigh);
			myRightFoot =   new Limb(modelRightFoot,    1.21f, 0, 0, null, myRightShin);
		}
		else if (characterID == 3) //Dage 4 Aquatic
		{
			displayHeightOffset = 0;
			myBody =        new Body(modelBody);
			myHead =        new Limb(modelHead,         0, 1.3f, 0,       myBody, null);
			myLeftHumerus = new Limb(modelLeftHumerus,  0, 0.9f, -0.9f,   myBody, null);
			myLeftForearm = new Limb(modelLeftForearm,  1.3f, 0, 0, null, myLeftHumerus);
			myLeftHand =    new Limb(modelLeftHand,     1.3f, 0, 0, null, myLeftForearm);
			myLeftThigh =   new Limb(modelLeftThigh,    0, -0.9f, -0.3f,  myBody, null);
			myLeftShin =    new Limb(modelLeftShin,     1.1f, 0, 0, null, myLeftThigh);
			myLeftFoot =    new Limb(modelLeftFoot,     1.1f, 0, 0, null, myLeftShin);
			myRightHumerus =new Limb(modelRightHumerus, 0, 0.9f, 0.9f,    myBody, null);
			myRightForearm =new Limb(modelRightForearm, 1.3f, 0, 0, null, myRightHumerus);
			myRightHand =   new Limb(modelRightHand,    1.3f, 0, 0, null, myRightForearm);
			myRightThigh =  new Limb(modelRightThigh,   0, -0.9f, 0.3f,   myBody, null);
			myRightShin =   new Limb(modelRightShin,    1.1f, 0, 0, null, myRightThigh);
			myRightFoot =   new Limb(modelRightFoot,    1.1f, 0, 0, null, myRightShin);
		}
		
		//myBall = new Entity(modelBall, new Vector3f(0,0,0));
		
		
		//Amy
		/*
		myBody =        new Body(modelBody);
		myHead =        new Limb(modelHead,         0, 1.4f, 0,       myBody, null);
		myLeftHumerus = new Limb(modelLeftHumerus,  0, 1.1f, -0.9f,   myBody, null);
		myLeftForearm = new Limb(modelLeftForearm,  1.3f, 0, 0, null, myLeftHumerus);
		myLeftHand =    new Limb(modelLeftHand,     1.3f, 0, 0, null, myLeftForearm);
		myLeftThigh =   new Limb(modelLeftThigh,    0, -0.9f, -0.3f,  myBody, null);
		myLeftShin =    new Limb(modelLeftShin,     1.1f, 0, 0, null, myLeftThigh);
		myLeftFoot =    new Limb(modelLeftFoot,     1.1f, 0, 0, null, myLeftShin);
		myRightHumerus =new Limb(modelRightHumerus, 0, 1.1f, 0.9f,    myBody, null);
		myRightForearm =new Limb(modelRightForearm, 1.3f, 0, 0, null, myRightHumerus);
		myRightHand =   new Limb(modelRightHand,    1.3f, 0, 0, null, myRightForearm);
		myRightThigh =  new Limb(modelRightThigh,   0, -0.9f, 0.3f,   myBody, null);
		myRightShin =   new Limb(modelRightShin,    1.1f, 0, 0, null, myRightThigh);
		myRightFoot =   new Limb(modelRightFoot,    1.1f, 0, 0, null, myRightShin);
		*/

		
		
		//Mario
		/*
		myBody =        new Body(modelBody);
		myHead =        new Limb(modelHead,         0, 2, 0,       myBody, null);
		myLeftHumerus = new Limb(modelLeftHumerus,  -0.2f, 1.25f, -1.5f,   myBody, null);
		myLeftForearm = new Limb(modelLeftForearm,  1, 0, 0, null, myLeftHumerus);
		myLeftHand =    new Limb(modelLeftHand,     1, 0, 0, null, myLeftForearm);
		myLeftThigh =   new Limb(modelLeftThigh,    -0.35f, -1.25f, -1.0f,  myBody, null);
		myLeftShin =    new Limb(modelLeftShin,     1.7f, 0, 0, null, myLeftThigh);
		myLeftFoot =    new Limb(modelLeftFoot,     1.25f, 0, 0, null, myLeftShin);
		myRightHumerus =new Limb(modelRightHumerus, -0.2f, 1.25f, 1.5f,    myBody, null);
		myRightForearm =new Limb(modelRightForearm, 1, 0, 0, null, myRightHumerus);
		myRightHand =   new Limb(modelRightHand,    1, 0, 0, null, myRightForearm);
		myRightThigh =  new Limb(modelRightThigh,   -0.35f, -1.25f, 1.0f,   myBody, null);
		myRightShin =   new Limb(modelRightShin,    1.7f, 0, 0, null, myRightThigh);
		myRightFoot =   new Limb(modelRightFoot,    1.25f, 0, 0, null, myRightShin);
		*/
		
		//Midna
		/*
		float off = 0.2f;
		myBody =        new Body(modelBody);
		myHead =        new Limb(modelHead,         0, 2.8f-off, 0,           myBody, null);
		myLeftHumerus = new Limb(modelLeftHumerus,  0, 1.63f-off, -1.2f+off,  myBody, null);
		myLeftForearm = new Limb(modelLeftForearm,  1.7f-off, 0, 0, null,     myLeftHumerus);
		myLeftHand =    new Limb(modelLeftHand,     2.47f-off, 0, 0, null,    myLeftForearm);
		myLeftThigh =   new Limb(modelLeftThigh,    0, -2.2f, -0.9f+off,      myBody, null);
		myLeftShin =    new Limb(modelLeftShin,     1.8f-off, 0, 0, null,     myLeftThigh);
		myLeftFoot =    new Limb(modelLeftFoot,     1.65f-off, 0, 0, null,    myLeftShin);
		myRightHumerus =new Limb(modelRightHumerus, 0, 1.63f-off, 1.2f-off,   myBody, null);
		myRightForearm =new Limb(modelRightForearm, 1.7f-off, 0, 0, null,     myRightHumerus);
		myRightHand =   new Limb(modelRightHand,    2.5f-off, 0, 0, null,     myRightForearm);
		myRightThigh =  new Limb(modelRightThigh,   0, -2.15f+off, 0.84f-off, myBody, null);
		myRightShin =   new Limb(modelRightShin,    1.97f-off, 0, 0, null,    myRightThigh);
		myRightFoot =   new Limb(modelRightFoot,    1.65f-off, 0, 0, null,    myRightShin);
		*/
		
		/*
		Body playerBody = new Body(ConvenientMethods.loadModel("Models/Bomberman/", "Body", loader));
		Limb playerHead = new Limb(ConvenientMethods.loadModel("Models/Bomberman/", "Head", loader), 0, 2.6f, 0, playerBody, null);
		Limb playerLeftHumerus = new Limb(ConvenientMethods.loadModel("Models/Bomberman/", "Humerus", loader), 0, 1.5f, -1.3f, playerBody, null);
		Limb playerLeftForearm = new Limb(ConvenientMethods.loadModel("Models/Bomberman/", "Forearm", loader), 1.8f, 0, 0, null, playerLeftHumerus);
		Limb playerLeftHand = new Limb(ConvenientMethods.loadModel("Models/Bomberman/", "Hand", loader), 1.5f, 0, 0, null, playerLeftForearm);
		Limb playerLeftThigh = new Limb(ConvenientMethods.loadModel("Models/Bomberman/", "Thigh", loader), 0, -1.3f, -0.75f, playerBody, null);
		Limb playerLeftShin = new Limb(ConvenientMethods.loadModel("Models/Bomberman/", "Shin", loader), 2.2f, 0, 0, null, playerLeftThigh);
		Limb playerLeftFoot = new Limb(ConvenientMethods.loadModel("Models/Bomberman/", "Foot", loader), 3f, 0, 0, null, playerLeftShin);
		Limb playerRightHumerus = new Limb(ConvenientMethods.loadModel("Models/Bomberman/", "Humerus", loader), 0, 1.5f, 1.3f, playerBody, null);
		Limb playerRightForearm = new Limb(ConvenientMethods.loadModel("Models/Bomberman/", "Forearm", loader), 1.8f, 0, 0, null, playerRightHumerus);
		Limb playerRightHand = new Limb(ConvenientMethods.loadModel("Models/Bomberman/", "Hand", loader), 1.5f, 0, 0, null, playerRightForearm);
		Limb playerRightThigh = new Limb(ConvenientMethods.loadModel("Models/Bomberman/", "Thigh", loader), 0, -1.3f, 0.75f, playerBody, null);
		Limb playerRightShin = new Limb(ConvenientMethods.loadModel("Models/Bomberman/", "Shin", loader), 2.2f, 0, 0, null, playerRightThigh);
		Limb playerRightFoot = new Limb(ConvenientMethods.loadModel("Models/Bomberman/", "Foot", loader), 3f, 0, 0, null, playerRightShin);
		*/
		
		
		AnimationResources.assignAnimationsHuman(myBody, myHead, 
				myLeftHumerus, myLeftForearm, myLeftHand, 
				myRightHumerus, myRightForearm, myRightHand, 
				myLeftThigh, myLeftShin, myLeftFoot, 
				myRightThigh, myRightShin, myRightFoot);
	}
	
	
	public void respawn()
	{
		super.respawn();
		addLimbsToGame();
		xVel = 0.0f;
		yVel = 0.0f;
		zVel = 0.0f;
		xVelAir = 0.0f;
		zVelAir = 0.0f;
		relativeXVel = 0;
		relativeZVel = 0;
		movementInputX = 0;
		movementInputY = 0;
		movementAngle = 0;
		moveSpeedCurrent = 0;
		moveSpeedAirCurrent = 0.0f;
		triCol = null;
	}
	
	public void despawn()
	{
		super.despawn();
		removeLimbsFromGame();
	}
	
	public float getHitboxHorizontal()
	{
		return 6;
	}
	
	public float getHitboxVertical()
	{
		return 6;
	}
	
	public void setCameraAngles(float newYaw, float newPitch)
	{
		myCameraYawTarget = newYaw;
		myCameraPitchTarget = newPitch;
		cam.setYaw(newYaw);
		cam.setPitch(newPitch);
	}
	
	public void stopMoving()
	{
		xVel = 0;
		yVel = 0;
		zVel = 0;
		xVelAir = 0;
		zVelAir = 0;
		relativeXVel = 0;
		relativeZVel= 0;
	}
	
	public float getSpeed()
	{
		float xSpd = xVel+xVelAir+relativeXVel;
		float zSpd = zVel+zVelAir+relativeZVel;
		
		return (float)Math.sqrt(xSpd*xSpd+zSpd*zSpd);
	}
	
	public boolean isVulnerable()
	{
		return !(homingAttackTimer > 0 ||
				isBouncing ||
				isJumping ||
				isBall ||
				isSpindashing ||
				isStomping);
	}
	
	public void takeDamage(Vector3f damageSource)
	{
		if (iFrame == 0)
		{
			float xDiff = damageSource.x-getX();
			float zDiff = damageSource.z-getZ();
			double newDirection = Math.atan2(zDiff, -xDiff);
			xVelAir = (float)(1.5*Math.cos(newDirection));
			zVelAir = (float)(-1.5*Math.sin(newDirection));
			xVel = 0;
			zVel = 0;
			relativeXVel = 0;
			relativeZVel = 0;
			onPlane = false;
			yVel = 1.5f;
			iFrame = 120;
			hitTimer = 120;
			isJumping = false;
			isSpindashing = false;
			isSkidding = false;
			isBall = false;
			isBouncing = false;
			isStomping = false;
			isLightdashing = false;
			spindashReleaseTimer = 0;
			
			int ringsToScatter = GuiManager.getRings();
			GuiManager.setRings(0);
			
			if (ringsToScatter == 0)
			{
				//tell main game loop to restart level
				MainGameLoop.shouldRestartLevel = true;
				AudioSources.play(34, getPosition());
				return;
			}
			
			AudioSources.play(27, getPosition());
			while (ringsToScatter > 0)
			{
				float spoutSpd = 3.5f;
				float anglH = (float)(Math.PI*2*Math.random());
				float anglV = (float)(Math.toRadians(MainGameLoop.rnd.nextGaussian()*42+90));
				
				float yspd = (float)(spoutSpd*Math.sin(anglV));
				float hpt = (float)(spoutSpd*Math.cos(anglV));
				
				float xspd = (float)(hpt*Math.cos(anglH));
				float zspd = (float)(hpt*Math.sin(anglH));
				
				MainGameLoop.gameEntitiesToAdd.add(
						new Ring(new Vector3f(getX(), getY()+5, getZ()), 
								xspd, yspd, zspd));
				
				ringsToScatter--;
			}
		}
	}
	
	public void rebound(Vector3f source)
	{
		if (onPlane == false)
		{
			if (characterID == 2) //Mecha Sonic
			{
				yVel = 2.1f;
				xVelAir = 0;
				zVelAir = 0;
				setX(source.x);
				setZ(source.z);
				setY(source.y+3.5f);
				homingAttackTimer = -1;
				hoverCount = hoverLimit/2;
			}
			else
			{
				if (yVel >= 0) //no rebound
				{
					yVel+=1;
				}
				else if (jumpInput) //rebound
				{
					yVel = 0.2f+yVel*-1;
					if (yVel < 2)
					{
						yVel = 2;
					}
				}
				else //rebound
				{
					yVel = yVel*-1;
					if (yVel > 1)
					{
						yVel = 1;
					}
				}
				homingAttackTimer = -1;
			}
		}
	}
	
	/** places spindash trails 
	 * @param initPos
	 * @param endPos
	 * @param count
	 */
	private void createSpindashTrails(Vector3f initPos, Vector3f endPos, int count, int life)
	{
		Vector3f diff = Vector3f.sub(endPos, initPos, null);
		
		diff.scale(1.0f/(count));
		
		for (int i = 0; i < count; i++)
		{
			Vector3f offset = new Vector3f(diff);
			offset.scale(i);
			Vector3f newPos = Vector3f.add(initPos, offset, null);
			new SpindashTrail(newPos, life, characterID);
		}
	}
	
	private void createStompParticles()
	{
		/*
		Vector3f centerPos = new Vector3f(getPosition());
		float ringRadius = 4;
		int ringCount = 8;
		if (ringCount > 1)
		{
			float degreeSegment = 360.0f/ringCount;
			Vector3f newPoint = new Vector3f(0, centerPos.y, 0);
			
			
			for (int i = 0; i < ringCount; i++)
			{
				newPoint.setX(centerPos.x + ringRadius*(float)Math.cos(Math.toRadians(degreeSegment*i)));
				newPoint.setZ(centerPos.z + ringRadius*(float)Math.sin(Math.toRadians(degreeSegment*i)));
				new SpindashTrail(new Vector3f(newPoint.x, newPoint.y, newPoint.z), newPoint.x, newPoint.y, newPoint.z, 60, 10, characterID);
			}
		}
		*/
		
		/*
		float spoutSpd = 3f;
		
		int count = 5+
		float anglH = (float)(Math.PI*2*Math.random());
		float anglV = (float)(Math.toRadians(MainGameLoop.rnd.nextGaussian()*45+90));
		
		float yspd = (float)(spoutSpd*Math.sin(anglV));
		float hpt = (float)(spoutSpd*Math.cos(anglV));
		
		float xspd = (float)(hpt*Math.cos(anglH));
		float zspd = (float)(hpt*Math.sin(anglH));
		
		new SpindashTrail(new Vector3f(getX(), getY()+2, getZ()), 
						xspd, yspd, zspd, 60, 10, characterID);
		
		*/
		
		float totXVel = xVel+xVelAir;
		float totZVel = zVel+zVelAir;
		int numBubbles = ((int)Math.abs(yVel*10))+18;
		for (int i = 0; i < numBubbles; i++)
		{
			float xOff = (float)(18*(Math.random()-0.5));
			float zOff = (float)(18*(Math.random()-0.5));
			new SpindashTrail(new Vector3f(getX()+xOff, getY()+2, getZ()+zOff), 
					         (float)((Math.random()-0.5f)*3+totXVel*0.8f),
							 (float)(Math.random()*1.2+0.5f),
							 (float)((Math.random()-0.5f)*3+totZVel*0.8f),
					0.08f, 25, 14, characterID);
		}
	}
	
	
	public static void allocateStaticModels()
	{
		if (characterID == 0) //Classic Sonic
		{
			if (modelBody == null) { modelBody = ConvenientMethods.loadModel("Models/Sonic/", "Body"); }
			if (modelHead == null) { modelHead = ConvenientMethods.loadModel("Models/Sonic/", "Head"); }
			if (modelLeftHumerus == null) { modelLeftHumerus = ConvenientMethods.loadModel("Models/Sonic/", "Humerus"); }
			if (modelLeftForearm == null) { modelLeftForearm = ConvenientMethods.loadModel("Models/Sonic/", "Forearm"); }
			if (modelLeftHand == null) { modelLeftHand = ConvenientMethods.loadModel("Models/Sonic/", "LeftHand"); }
			if (modelLeftThigh == null) { modelLeftThigh = ConvenientMethods.loadModel("Models/Sonic/", "Thigh"); }
			if (modelLeftShin == null) { modelLeftShin = ConvenientMethods.loadModel("Models/Sonic/", "Shin"); }
			if (modelLeftFoot == null) { modelLeftFoot = ConvenientMethods.loadModel("Models/Sonic/", "Foot"); }
			if (modelRightHumerus == null) { modelRightHumerus = ConvenientMethods.loadModel("Models/Sonic/", "Humerus"); }
			if (modelRightForearm == null) { modelRightForearm = ConvenientMethods.loadModel("Models/Sonic/", "Forearm"); }
			if (modelRightHand == null) { modelRightHand = ConvenientMethods.loadModel("Models/Sonic/", "RightHand"); }
			if (modelRightThigh == null) { modelRightThigh = ConvenientMethods.loadModel("Models/Sonic/", "Thigh"); }
			if (modelRightShin == null) { modelRightShin = ConvenientMethods.loadModel("Models/Sonic/", "Shin"); }
			if (modelRightFoot == null) { modelRightFoot = ConvenientMethods.loadModel("Models/Sonic/", "Foot"); }
		}
		if (characterID == 1) //Doll Sonic
		{			
			if (modelBody == null) { modelBody = ConvenientMethods.loadModel("Models/SonicDoll/", "Body"); }
			if (modelHead == null) { modelHead = ConvenientMethods.loadModel("Models/SonicDoll/", "Head"); }
			if (modelLeftHumerus == null) { modelLeftHumerus = ConvenientMethods.loadModel("Models/SonicDoll/", "Humerus"); }
			if (modelLeftForearm == null) { modelLeftForearm = ConvenientMethods.loadModel("Models/SonicDoll/", "Forearm"); }
			if (modelLeftHand == null) { modelLeftHand = ConvenientMethods.loadModel("Models/SonicDoll/", "HandL"); }
			if (modelLeftThigh == null) { modelLeftThigh = ConvenientMethods.loadModel("Models/SonicDoll/", "Thigh"); }
			if (modelLeftShin == null) { modelLeftShin = ConvenientMethods.loadModel("Models/SonicDoll/", "Shin"); }
			if (modelLeftFoot == null) { modelLeftFoot = ConvenientMethods.loadModel("Models/SonicDoll/", "FootL"); }
			if (modelRightHumerus == null) { modelRightHumerus = ConvenientMethods.loadModel("Models/SonicDoll/", "Humerus"); }
			if (modelRightForearm == null) { modelRightForearm = ConvenientMethods.loadModel("Models/SonicDoll/", "Forearm"); }
			if (modelRightHand == null) { modelRightHand = ConvenientMethods.loadModel("Models/SonicDoll/", "HandR"); }
			if (modelRightThigh == null) { modelRightThigh = ConvenientMethods.loadModel("Models/SonicDoll/", "Thigh"); }
			if (modelRightShin == null) { modelRightShin = ConvenientMethods.loadModel("Models/SonicDoll/", "Shin"); }
			if (modelRightFoot == null) { modelRightFoot = ConvenientMethods.loadModel("Models/SonicDoll/", "FootR"); }
		}
		if (characterID == 2) //Silver Sonic II
		{
			if (modelBody == null) { modelBody = ConvenientMethods.loadModel("Models/SilverSonic/", "Body"); }
			if (modelHead == null) { modelHead = ConvenientMethods.loadModel("Models/SilverSonic/", "Head"); }
			if (modelLeftHumerus == null) { modelLeftHumerus = ConvenientMethods.loadModel("Models/SilverSonic/", "Humerus"); }
			if (modelLeftForearm == null) { modelLeftForearm = ConvenientMethods.loadModel("Models/SilverSonic/", "ForearmL"); }
			if (modelLeftHand == null) { modelLeftHand = ConvenientMethods.loadModel("Models/SilverSonic/", "HandL"); }
			if (modelLeftThigh == null) { modelLeftThigh = ConvenientMethods.loadModel("Models/SilverSonic/", "Thigh"); }
			if (modelLeftShin == null) { modelLeftShin = ConvenientMethods.loadModel("Models/SilverSonic/", "ShinL"); }
			if (modelLeftFoot == null) { modelLeftFoot = ConvenientMethods.loadModel("Models/SilverSonic/", "FootL"); }
			if (modelRightHumerus == null) { modelRightHumerus = ConvenientMethods.loadModel("Models/SilverSonic/", "Humerus"); }
			if (modelRightForearm == null) { modelRightForearm = ConvenientMethods.loadModel("Models/SilverSonic/", "ForearmR"); }
			if (modelRightHand == null) { modelRightHand = ConvenientMethods.loadModel("Models/SilverSonic/", "HandR"); }
			if (modelRightThigh == null) { modelRightThigh = ConvenientMethods.loadModel("Models/SilverSonic/", "Thigh"); }
			if (modelRightShin == null) { modelRightShin = ConvenientMethods.loadModel("Models/SilverSonic/", "ShinR"); }
			if (modelRightFoot == null) { modelRightFoot = ConvenientMethods.loadModel("Models/SilverSonic/", "FootR"); }
		}
		if (characterID == 3) //Dage 4 Aquatic
		{
			if (modelBody == null) { modelBody = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Body"); }
			if (modelHead == null) { modelHead = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Head"); }
			if (modelLeftHumerus == null) { modelLeftHumerus = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Humerus"); }
			if (modelLeftForearm == null) { modelLeftForearm = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Forearm"); }
			if (modelLeftHand == null) { modelLeftHand = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "LeftHand"); }
			if (modelLeftThigh == null) { modelLeftThigh = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Thigh"); }
			if (modelLeftShin == null) { modelLeftShin = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Shin"); }
			if (modelLeftFoot == null) { modelLeftFoot = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Foot"); }
			if (modelRightHumerus == null) { modelRightHumerus = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Humerus"); }
			if (modelRightForearm == null) { modelRightForearm = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Forearm"); }
			if (modelRightHand == null) { modelRightHand = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "RightHand"); }
			if (modelRightThigh == null) { modelRightThigh = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Thigh"); }
			if (modelRightShin == null) { modelRightShin = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Shin"); }
			if (modelRightFoot == null) { modelRightFoot = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Foot"); }
		}
	}
	
	public static void freeStaticModels()
	{
		if (modelBody != null) { MainGameLoop.gameLoader.deleteModel(modelBody); modelBody = null; }
		if (modelHead != null) { MainGameLoop.gameLoader.deleteModel(modelHead); modelHead = null; }
		if (modelLeftHumerus != null) { MainGameLoop.gameLoader.deleteModel(modelLeftHumerus); modelLeftHumerus = null; }
		if (modelLeftForearm != null) { MainGameLoop.gameLoader.deleteModel(modelLeftForearm); modelLeftForearm = null; }
		if (modelLeftHand != null) { MainGameLoop.gameLoader.deleteModel(modelLeftHand); modelLeftHand = null; }
		if (modelLeftThigh != null) { MainGameLoop.gameLoader.deleteModel(modelLeftThigh); modelLeftThigh = null; }
		if (modelLeftShin != null) { MainGameLoop.gameLoader.deleteModel(modelLeftShin); modelLeftShin = null; }
		if (modelLeftFoot != null) { MainGameLoop.gameLoader.deleteModel(modelLeftFoot); modelLeftFoot = null; }
		if (modelRightHumerus != null) { MainGameLoop.gameLoader.deleteModel(modelRightHumerus); modelRightHumerus = null; }
		if (modelRightForearm != null) { MainGameLoop.gameLoader.deleteModel(modelRightForearm); modelRightForearm = null; }
		if (modelRightHand != null) { MainGameLoop.gameLoader.deleteModel(modelRightHand); modelRightHand = null; }
		if (modelRightThigh != null) { MainGameLoop.gameLoader.deleteModel(modelRightThigh); modelRightThigh = null; }
		if (modelRightShin != null) { MainGameLoop.gameLoader.deleteModel(modelRightShin); modelRightShin = null; }
		if (modelRightFoot != null) { MainGameLoop.gameLoader.deleteModel(modelRightFoot); modelRightFoot = null; }
	}

	public void setDisplacement(float x, float y, float z)
	{
		xDisp = x;
		yDisp = y;
		zDisp = z;
		isGettingExternallyMoved = true;
	}
	
	public void setInWater(float height)
	{
		inWater = true;
		waterHeight = height;
	}
	
	//this would be how to make momentum carry over from moving platforms
	//but player always sets air vels to 0 if on the ground 
	//so something in player would need to be altered for it to work
	//public void setAirVel(float x, float y, float z)
	{
		//xVelAir = x;
		//yDisp = y;
		//zVelAir = z;
		//isGettingExternallyMoved = true;
	}
	
	public void increaseGroundSpeed(float dx, float dz)
	{
		relativeXVel+=dx;
		relativeZVel+=dz;
	}
	
	public Body getBody()
	{
		return myBody;
	}
}
