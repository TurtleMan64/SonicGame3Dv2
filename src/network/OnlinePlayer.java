package network;

import java.util.LinkedList;

import org.lwjgl.util.vector.Vector3f;

import animation.AnimationResources;
import animation.Body;
import animation.Limb;
import entities.Entity;
import models.TexturedModel;
import toolbox.ConvenientMethods;

public class OnlinePlayer
{
	//private static TexturedModel[] modelBall;
	
	private static TexturedModel[] modelBody0             = null;
	private static TexturedModel[] modelHead0             = null;
	private static TexturedModel[] modelLeftHumerus0      = null;
	private static TexturedModel[] modelLeftForearm0      = null;
	private static TexturedModel[] modelLeftHand0         = null;
	private static TexturedModel[] modelLeftThigh0        = null;
	private static TexturedModel[] modelLeftShin0         = null;
	private static TexturedModel[] modelLeftFoot0         = null;
	private static TexturedModel[] modelRightHumerus0     = null;
	private static TexturedModel[] modelRightForearm0     = null;
	private static TexturedModel[] modelRightHand0        = null;
	private static TexturedModel[] modelRightThigh0       = null;
	private static TexturedModel[] modelRightShin0        = null;
	private static TexturedModel[] modelRightFoot0        = null;
	
	private static TexturedModel[] modelBody1             = null;
	private static TexturedModel[] modelHead1             = null;
	private static TexturedModel[] modelLeftHumerus1      = null;
	private static TexturedModel[] modelLeftForearm1      = null;
	private static TexturedModel[] modelLeftHand1         = null;
	private static TexturedModel[] modelLeftThigh1        = null;
	private static TexturedModel[] modelLeftShin1         = null;
	private static TexturedModel[] modelLeftFoot1         = null;
	private static TexturedModel[] modelRightHumerus1     = null;
	private static TexturedModel[] modelRightForearm1     = null;
	private static TexturedModel[] modelRightHand1        = null;
	private static TexturedModel[] modelRightThigh1       = null;
	private static TexturedModel[] modelRightShin1        = null;
	private static TexturedModel[] modelRightFoot1        = null;
	
	private static TexturedModel[] modelBody2             = null;
	private static TexturedModel[] modelHead2             = null;
	private static TexturedModel[] modelLeftHumerus2      = null;
	private static TexturedModel[] modelLeftForearm2      = null;
	private static TexturedModel[] modelLeftHand2         = null;
	private static TexturedModel[] modelLeftThigh2        = null;
	private static TexturedModel[] modelLeftShin2         = null;
	private static TexturedModel[] modelLeftFoot2         = null;
	private static TexturedModel[] modelRightHumerus2     = null;
	private static TexturedModel[] modelRightForearm2     = null;
	private static TexturedModel[] modelRightHand2        = null;
	private static TexturedModel[] modelRightThigh2       = null;
	private static TexturedModel[] modelRightShin2        = null;
	private static TexturedModel[] modelRightFoot2        = null;
	
	private static TexturedModel[] modelBody3             = null;
	private static TexturedModel[] modelHead3             = null;
	private static TexturedModel[] modelLeftHumerus3      = null;
	private static TexturedModel[] modelLeftForearm3      = null;
	private static TexturedModel[] modelLeftHand3         = null;
	private static TexturedModel[] modelLeftThigh3        = null;
	private static TexturedModel[] modelLeftShin3         = null;
	private static TexturedModel[] modelLeftFoot3         = null;
	private static TexturedModel[] modelRightHumerus3     = null;
	private static TexturedModel[] modelRightForearm3     = null;
	private static TexturedModel[] modelRightHand3        = null;
	private static TexturedModel[] modelRightThigh3       = null;
	private static TexturedModel[] modelRightShin3        = null;
	private static TexturedModel[] modelRightFoot3        = null;

	//private Entity myBall;
	
	private Body myBody0;
	private Limb myHead0;
	private Limb myLeftHumerus0;
	private Limb myLeftForearm0;
	private Limb myLeftHand0;
	private Limb myLeftThigh0;
	private Limb myLeftShin0;
	private Limb myLeftFoot0;
	private Limb myRightHumerus0;
	private Limb myRightForearm0;
	private Limb myRightHand0;
	private Limb myRightThigh0;
	private Limb myRightShin0;
	private Limb myRightFoot0;
	private float displayHeightOffset0;
	
	private Body myBody1;
	private Limb myHead1;
	private Limb myLeftHumerus1;
	private Limb myLeftForearm1;
	private Limb myLeftHand1;
	private Limb myLeftThigh1;
	private Limb myLeftShin1;
	private Limb myLeftFoot1;
	private Limb myRightHumerus1;
	private Limb myRightForearm1;
	private Limb myRightHand1;
	private Limb myRightThigh1;
	private Limb myRightShin1;
	private Limb myRightFoot1;
	private float displayHeightOffset1;
	
	private Body myBody2;
	private Limb myHead2;
	private Limb myLeftHumerus2;
	private Limb myLeftForearm2;
	private Limb myLeftHand2;
	private Limb myLeftThigh2;
	private Limb myLeftShin2;
	private Limb myLeftFoot2;
	private Limb myRightHumerus2;
	private Limb myRightForearm2;
	private Limb myRightHand2;
	private Limb myRightThigh2;
	private Limb myRightShin2;
	private Limb myRightFoot2;
	private float displayHeightOffset2;
	
	private Body myBody3;
	private Limb myHead3;
	private Limb myLeftHumerus3;
	private Limb myLeftForearm3;
	private Limb myLeftHand3;
	private Limb myLeftThigh3;
	private Limb myLeftShin3;
	private Limb myLeftFoot3;
	private Limb myRightHumerus3;
	private Limb myRightForearm3;
	private Limb myRightHand3;
	private Limb myRightThigh3;
	private Limb myRightShin3;
	private Limb myRightFoot3;
	private float displayHeightOffset3;
	
	
	private LinkedList<Entity> models0;
	private LinkedList<Entity> models1;
	private LinkedList<Entity> models2;
	private LinkedList<Entity> models3;
	
	private LinkedList<Limb> limbs0;
	private LinkedList<Limb> limbs1;
	private LinkedList<Limb> limbs2;
	private LinkedList<Limb> limbs3;
	
	public int modelID;
	public String name;
	public Vector3f pos;
	public float xVel;
	public float yVel;
	public float zVel;
	
	public float yRot;
	public float zRot;
	
	public int animID;
	public float time;
	public float deltaTime;
	
	public static void initModels()
	{
		//modelBall = ConvenientMethods.loadModel("Models/Sonic/", "Ball");
		
		modelBody0 = ConvenientMethods.loadModel("Models/Sonic/", "Body");
		modelHead0 = ConvenientMethods.loadModel("Models/Sonic/", "Head");
		modelLeftHumerus0 = ConvenientMethods.loadModel("Models/Sonic/", "Humerus");
		modelLeftForearm0 = ConvenientMethods.loadModel("Models/Sonic/", "Forearm");
		modelLeftHand0 = ConvenientMethods.loadModel("Models/Sonic/", "LeftHand");
		modelLeftThigh0 = ConvenientMethods.loadModel("Models/Sonic/", "Thigh");
		modelLeftShin0 = ConvenientMethods.loadModel("Models/Sonic/", "Shin");
		modelLeftFoot0 = ConvenientMethods.loadModel("Models/Sonic/", "Foot");
		modelRightHumerus0 = ConvenientMethods.loadModel("Models/Sonic/", "Humerus");
		modelRightForearm0 = ConvenientMethods.loadModel("Models/Sonic/", "Forearm");
		modelRightHand0 = ConvenientMethods.loadModel("Models/Sonic/", "RightHand");
		modelRightThigh0 = ConvenientMethods.loadModel("Models/Sonic/", "Thigh");
		modelRightShin0 = ConvenientMethods.loadModel("Models/Sonic/", "Shin");
		modelRightFoot0 = ConvenientMethods.loadModel("Models/Sonic/", "Foot");
		
		modelBody1 = ConvenientMethods.loadModel("Models/SonicDoll/", "Body");
		modelHead1 = ConvenientMethods.loadModel("Models/SonicDoll/", "Head");
		modelLeftHumerus1 = ConvenientMethods.loadModel("Models/SonicDoll/", "Humerus");
		modelLeftForearm1 = ConvenientMethods.loadModel("Models/SonicDoll/", "Forearm");
		modelLeftHand1 = ConvenientMethods.loadModel("Models/SonicDoll/", "HandL");
		modelLeftThigh1 = ConvenientMethods.loadModel("Models/SonicDoll/", "Thigh");
		modelLeftShin1 = ConvenientMethods.loadModel("Models/SonicDoll/", "Shin");
		modelLeftFoot1 = ConvenientMethods.loadModel("Models/SonicDoll/", "FootL");
		modelRightHumerus1 = ConvenientMethods.loadModel("Models/SonicDoll/", "Humerus");
		modelRightForearm1 = ConvenientMethods.loadModel("Models/SonicDoll/", "Forearm");
		modelRightHand1 = ConvenientMethods.loadModel("Models/SonicDoll/", "HandR");
		modelRightThigh1 = ConvenientMethods.loadModel("Models/SonicDoll/", "Thigh");
		modelRightShin1 = ConvenientMethods.loadModel("Models/SonicDoll/", "Shin");
		modelRightFoot1 = ConvenientMethods.loadModel("Models/SonicDoll/", "FootR");

		modelBody2 = ConvenientMethods.loadModel("Models/SilverSonic/", "Body");
		modelHead2 = ConvenientMethods.loadModel("Models/SilverSonic/", "Head");
		modelLeftHumerus2 = ConvenientMethods.loadModel("Models/SilverSonic/", "Humerus");
		modelLeftForearm2 = ConvenientMethods.loadModel("Models/SilverSonic/", "ForearmL");
		modelLeftHand2 = ConvenientMethods.loadModel("Models/SilverSonic/", "HandL");
		modelLeftThigh2 = ConvenientMethods.loadModel("Models/SilverSonic/", "Thigh");
		modelLeftShin2 = ConvenientMethods.loadModel("Models/SilverSonic/", "ShinL");
		modelLeftFoot2 = ConvenientMethods.loadModel("Models/SilverSonic/", "FootL");
		modelRightHumerus2 = ConvenientMethods.loadModel("Models/SilverSonic/", "Humerus");
		modelRightForearm2 = ConvenientMethods.loadModel("Models/SilverSonic/", "ForearmR");
		modelRightHand2 = ConvenientMethods.loadModel("Models/SilverSonic/", "HandR");
		modelRightThigh2 = ConvenientMethods.loadModel("Models/SilverSonic/", "Thigh");
		modelRightShin2 = ConvenientMethods.loadModel("Models/SilverSonic/", "ShinR");
		modelRightFoot2 = ConvenientMethods.loadModel("Models/SilverSonic/", "FootR");

		modelBody3 = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Body");
		modelHead3 = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Head");
		modelLeftHumerus3 = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Humerus");
		modelLeftForearm3 = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Forearm");
		modelLeftHand3 = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "LeftHand");
		modelLeftThigh3 = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Thigh");
		modelLeftShin3 = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Shin");
		modelLeftFoot3 = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Foot");
		modelRightHumerus3 = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Humerus");
		modelRightForearm3 = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Forearm");
		modelRightHand3 = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "RightHand");
		modelRightThigh3 = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Thigh");
		modelRightShin3 = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Shin");
		modelRightFoot3 = ConvenientMethods.loadModel("Models/Dage4Aquatic/", "Foot");
		
	}
	
	public OnlinePlayer()
	{
		modelID = 0;
		name = "";
		
		animID = 0;
		time = 0;
		deltaTime = 0;
		
		pos = new Vector3f(0,40,0);
		//myBall = new Entity(modelBall, new Vector3f(0,40,0));
		models0 = new LinkedList<Entity>();
		models1 = new LinkedList<Entity>();
		models2 = new LinkedList<Entity>();
		models3 = new LinkedList<Entity>();
		
		limbs0 = new LinkedList<Limb>();
		limbs1 = new LinkedList<Limb>();
		limbs2 = new LinkedList<Limb>();
		limbs3 = new LinkedList<Limb>();
		//models.add(myBall);
		
		displayHeightOffset0 = 0;
		//displayHeightOffset = -2.5f; sanix
		myBody0 =        new Body(modelBody0);
		myHead0 =        new Limb(modelHead0,         0, 1.3f, 0,       myBody0, null);
		myLeftHumerus0 = new Limb(modelLeftHumerus0,  0, 0.9f, -0.9f,   myBody0, null);
		myLeftForearm0 = new Limb(modelLeftForearm0,  1.3f, 0, 0, null, myLeftHumerus0);
		myLeftHand0 =    new Limb(modelLeftHand0,     1.3f, 0, 0, null, myLeftForearm0);
		myLeftThigh0 =   new Limb(modelLeftThigh0,    0, -0.9f, -0.3f,  myBody0, null);
		myLeftShin0 =    new Limb(modelLeftShin0,     1.1f, 0, 0, null, myLeftThigh0);
		myLeftFoot0 =    new Limb(modelLeftFoot0,     1.1f, 0, 0, null, myLeftShin0);
		myRightHumerus0 =new Limb(modelRightHumerus0, 0, 0.9f, 0.9f,    myBody0, null);
		myRightForearm0 =new Limb(modelRightForearm0, 1.3f, 0, 0, null, myRightHumerus0);
		myRightHand0 =   new Limb(modelRightHand0,    1.3f, 0, 0, null, myRightForearm0);
		myRightThigh0 =  new Limb(modelRightThigh0,   0, -0.9f, 0.3f,   myBody0, null);
		myRightShin0 =   new Limb(modelRightShin0,    1.1f, 0, 0, null, myRightThigh0);
		myRightFoot0 =   new Limb(modelRightFoot0,    1.1f, 0, 0, null, myRightShin0);

		displayHeightOffset1 = -0.525f;
		myBody1 =        new Body(modelBody1);
		myHead1 =        new Limb(modelHead1,         0.7f, 1.4f, 0,       myBody1, null);
		myLeftHumerus1 = new Limb(modelLeftHumerus1,  0, 0.9f, -0.9f,   myBody1, null);
		myLeftForearm1 = new Limb(modelLeftForearm1,  0.92f, 0, 0, null, myLeftHumerus1);
		myLeftHand1 =    new Limb(modelLeftHand1,     0.62f, 0, 0, null, myLeftForearm1);
		myLeftThigh1 =   new Limb(modelLeftThigh1,    0, -0.9f, -0.3f,  myBody1, null);
		myLeftShin1 =    new Limb(modelLeftShin1,     1.07f, 0, 0, null, myLeftThigh1);
		myLeftFoot1 =    new Limb(modelLeftFoot1,     1.23f, 0, 0, null, myLeftShin1);
		myRightHumerus1 =new Limb(modelRightHumerus1, 0, 0.9f, 0.9f,    myBody1, null);
		myRightForearm1 =new Limb(modelRightForearm1, 0.92f, 0, 0, null, myRightHumerus1);
		myRightHand1 =   new Limb(modelRightHand1,    0.62f, 0, 0, null, myRightForearm1);
		myRightThigh1 =  new Limb(modelRightThigh1,   0, -0.9f, 0.3f,   myBody1, null);
		myRightShin1 =   new Limb(modelRightShin1,    1.07f, 0, 0, null, myRightThigh1);
		myRightFoot1 =   new Limb(modelRightFoot1,    1.23f, 0, 0, null, myRightShin1);

		displayHeightOffset2 = 0.8f;
		myBody2 =        new Body(modelBody2);
		myHead2 =        new Limb(modelHead2,         0, 1.15f, 0,       myBody2, null);
		myLeftHumerus2 = new Limb(modelLeftHumerus2,  0, 0.9f, -0.9f,   myBody2, null);
		myLeftForearm2 = new Limb(modelLeftForearm2,  1.5f, 0, 0, null, myLeftHumerus2);
		myLeftHand2 =    new Limb(modelLeftHand2,     1.9f, 0, 0, null, myLeftForearm2);
		myLeftThigh2 =   new Limb(modelLeftThigh2,    0, -0.9f, -0.3f,  myBody2, null);
		myLeftShin2 =    new Limb(modelLeftShin2,     1.47f, 0, 0, null, myLeftThigh2);
		myLeftFoot2 =    new Limb(modelLeftFoot2,     1.21f, 0, 0, null, myLeftShin2);
		myRightHumerus2 =new Limb(modelRightHumerus2, 0, 0.9f, 0.9f,    myBody2, null);
		myRightForearm2 =new Limb(modelRightForearm2, 1.5f, 0, 0, null, myRightHumerus2);
		myRightHand2 =   new Limb(modelRightHand2,    1.9f, 0, 0, null, myRightForearm2);
		myRightThigh2 =  new Limb(modelRightThigh2,   0, -0.9f, 0.3f,   myBody2, null);
		myRightShin2 =   new Limb(modelRightShin2,    1.47f, 0, 0, null, myRightThigh2);
		myRightFoot2 =   new Limb(modelRightFoot2,    1.21f, 0, 0, null, myRightShin2);

		displayHeightOffset3 = 0;
		myBody3 =        new Body(modelBody3);
		myHead3 =        new Limb(modelHead3,         0, 1.3f, 0,       myBody3, null);
		myLeftHumerus3 = new Limb(modelLeftHumerus3,  0, 0.9f, -0.9f,   myBody3, null);
		myLeftForearm3 = new Limb(modelLeftForearm3,  1.3f, 0, 0, null, myLeftHumerus3);
		myLeftHand3 =    new Limb(modelLeftHand3,     1.3f, 0, 0, null, myLeftForearm3);
		myLeftThigh3 =   new Limb(modelLeftThigh3,    0, -0.9f, -0.3f,  myBody3, null);
		myLeftShin3 =    new Limb(modelLeftShin3,     1.1f, 0, 0, null, myLeftThigh3);
		myLeftFoot3 =    new Limb(modelLeftFoot3,     1.1f, 0, 0, null, myLeftShin3);
		myRightHumerus3 =new Limb(modelRightHumerus3, 0, 0.9f, 0.9f,    myBody3, null);
		myRightForearm3 =new Limb(modelRightForearm3, 1.3f, 0, 0, null, myRightHumerus3);
		myRightHand3 =   new Limb(modelRightHand3,    1.3f, 0, 0, null, myRightForearm3);
		myRightThigh3 =  new Limb(modelRightThigh3,   0, -0.9f, 0.3f,   myBody3, null);
		myRightShin3 =   new Limb(modelRightShin3,    1.1f, 0, 0, null, myRightThigh3);
		myRightFoot3 =   new Limb(modelRightFoot3,    1.1f, 0, 0, null, myRightShin3);
		
		AnimationResources.assignAnimationsHuman(myBody0, myHead0,
				myLeftHumerus0, myLeftForearm0, myLeftHand0,
				myRightHumerus0, myRightForearm0, myRightHand0,
				myLeftThigh0, myLeftShin0, myLeftFoot0,
				myRightThigh0, myRightShin0, myRightFoot0);
		
		AnimationResources.assignAnimationsHuman(myBody1, myHead1,
				myLeftHumerus1, myLeftForearm1, myLeftHand1,
				myRightHumerus1, myRightForearm1, myRightHand1,
				myLeftThigh1, myLeftShin1, myLeftFoot1,
				myRightThigh1, myRightShin1, myRightFoot1);
		
		AnimationResources.assignAnimationsHuman(myBody2, myHead2,
				myLeftHumerus2, myLeftForearm2, myLeftHand2,
				myRightHumerus2, myRightForearm2, myRightHand2,
				myLeftThigh2, myLeftShin2, myLeftFoot2,
				myRightThigh2, myRightShin2, myRightFoot2);
		
		AnimationResources.assignAnimationsHuman(myBody3, myHead3,
				myLeftHumerus3, myLeftForearm3, myLeftHand3,
				myRightHumerus3, myRightForearm3, myRightHand3,
				myLeftThigh3, myLeftShin3, myLeftFoot3,
				myRightThigh3, myRightShin3, myRightFoot3);
		
		models0.add(myBody0);
		models0.add(myHead0);
		models0.add(myLeftHumerus0);
		models0.add(myLeftForearm0);
		models0.add(myLeftHand0);
		models0.add(myLeftThigh0);
		models0.add(myLeftShin0);
		models0.add(myLeftFoot0);
		models0.add(myRightHumerus0);
		models0.add(myRightForearm0);
		models0.add(myRightHand0);
		models0.add(myRightThigh0);
		models0.add(myRightShin0);
		models0.add(myRightFoot0);
		limbs0.add(myHead0);
		limbs0.add(myLeftHumerus0);
		limbs0.add(myLeftForearm0);
		limbs0.add(myLeftHand0);
		limbs0.add(myLeftThigh0);
		limbs0.add(myLeftShin0);
		limbs0.add(myLeftFoot0);
		limbs0.add(myRightHumerus0);
		limbs0.add(myRightForearm0);
		limbs0.add(myRightHand0);
		limbs0.add(myRightThigh0);
		limbs0.add(myRightShin0);
		limbs0.add(myRightFoot0);
		
		models1.add(myBody1);
		models1.add(myHead1);
		models1.add(myLeftHumerus1);
		models1.add(myLeftForearm1);
		models1.add(myLeftHand1);
		models1.add(myLeftThigh1);
		models1.add(myLeftShin1);
		models1.add(myLeftFoot1);
		models1.add(myRightHumerus1);
		models1.add(myRightForearm1);
		models1.add(myRightHand1);
		models1.add(myRightThigh1);
		models1.add(myRightShin1);
		models1.add(myRightFoot1);
		limbs1.add(myHead1);
		limbs1.add(myLeftHumerus1);
		limbs1.add(myLeftForearm1);
		limbs1.add(myLeftHand1);
		limbs1.add(myLeftThigh1);
		limbs1.add(myLeftShin1);
		limbs1.add(myLeftFoot1);
		limbs1.add(myRightHumerus1);
		limbs1.add(myRightForearm1);
		limbs1.add(myRightHand1);
		limbs1.add(myRightThigh1);
		limbs1.add(myRightShin1);
		limbs1.add(myRightFoot1);
		
		models2.add(myBody2);
		models2.add(myHead2);
		models2.add(myLeftHumerus2);
		models2.add(myLeftForearm2);
		models2.add(myLeftHand2);
		models2.add(myLeftThigh2);
		models2.add(myLeftShin2);
		models2.add(myLeftFoot2);
		models2.add(myRightHumerus2);
		models2.add(myRightForearm2);
		models2.add(myRightHand2);
		models2.add(myRightThigh2);
		models2.add(myRightShin2);
		models2.add(myRightFoot2);
		limbs2.add(myHead2);
		limbs2.add(myLeftHumerus2);
		limbs2.add(myLeftForearm2);
		limbs2.add(myLeftHand2);
		limbs2.add(myLeftThigh2);
		limbs2.add(myLeftShin2);
		limbs2.add(myLeftFoot2);
		limbs2.add(myRightHumerus2);
		limbs2.add(myRightForearm2);
		limbs2.add(myRightHand2);
		limbs2.add(myRightThigh2);
		limbs2.add(myRightShin2);
		limbs2.add(myRightFoot2);
		
		models3.add(myBody3);
		models3.add(myHead3);
		models3.add(myLeftHumerus3);
		models3.add(myLeftForearm3);
		models3.add(myLeftHand3);
		models3.add(myLeftThigh3);
		models3.add(myLeftShin3);
		models3.add(myLeftFoot3);
		models3.add(myRightHumerus3);
		models3.add(myRightForearm3);
		models3.add(myRightHand3);
		models3.add(myRightThigh3);
		models3.add(myRightShin3);
		models3.add(myRightFoot3);
		limbs3.add(myHead3);
		limbs3.add(myLeftHumerus3);
		limbs3.add(myLeftForearm3);
		limbs3.add(myLeftHand3);
		limbs3.add(myLeftThigh3);
		limbs3.add(myLeftShin3);
		limbs3.add(myLeftFoot3);
		limbs3.add(myRightHumerus3);
		limbs3.add(myRightForearm3);
		limbs3.add(myRightHand3);
		limbs3.add(myRightThigh3);
		limbs3.add(myRightShin3);
		limbs3.add(myRightFoot3);
	}
	
	public void animate()
	{
		//System.out.println(pos);
		//myBall.setRotY(yRot);
		//myBall.setRotZ(zRot);
		//myBall.setPosition(pos);
		//myBall.setVisibility(true);
		
		setLimbsVisibility(myBody0, limbs0, false);
		setLimbsVisibility(myBody1, limbs1, false);
		setLimbsVisibility(myBody2, limbs2, false);
		setLimbsVisibility(myBody3, limbs3, false);
		
		Vector3f dispPos = new Vector3f(pos);
		
		switch (modelID)
		{
			case 0:
				setLimbsVisibility(myBody0, limbs0, true);
				dispPos.y+=displayHeightOffset0;
				myBody0.setBaseOrientation(dispPos, yRot, zRot);
				updateLimbs(myBody0, limbs0, animID, time);
				break;
			
			case 1:
				setLimbsVisibility(myBody1, limbs1, true);
				dispPos.y+=displayHeightOffset1;
				myBody1.setBaseOrientation(dispPos, yRot, zRot);
				updateLimbs(myBody1, limbs1, animID, time);
				break;
				
			case 2:
				setLimbsVisibility(myBody2, limbs2, true);
				dispPos.y+=displayHeightOffset2;
				myBody2.setBaseOrientation(dispPos, yRot, zRot);
				updateLimbs(myBody2, limbs2, animID, time);
				break;
				
			case 3:
				setLimbsVisibility(myBody3, limbs3, true);
				dispPos.y+=displayHeightOffset3;
				myBody3.setBaseOrientation(dispPos, yRot, zRot);
				updateLimbs(myBody3, limbs3, animID, time);
				break;
				
			default:
				break;
		}
		
		pos.x+=xVel;
		pos.y+=yVel;
		pos.z+=zVel;

		time+=deltaTime;
	}
	
	public void setLimbsVisibility(Body body, LinkedList<Limb> limbs, boolean newVal)
	{
		body.setVisibility(newVal);
		for (Limb limb: limbs)
		{
			limb.setVisibility(newVal);
		}
	}
	
	public void updateLimbs(Body body, LinkedList<Limb> limbs, int animIndex, float time)
	{
		body.animationIndex = animIndex;
		body.update(time);
		for (Limb limb: limbs)
		{
			limb.animationIndex = animIndex;
			limb.update(time);
		}
	}
	
	public LinkedList<Entity> getEntities()
	{
		switch (modelID)
		{
			case 0:
				return models0;
			
			case 1:
				return models1;
				
			case 2:
				return models2;
				
			case 3:
				return models3;
				
			default:
				break;
		}
		return models0;
	}
}
