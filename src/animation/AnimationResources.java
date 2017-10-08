package animation;

import java.util.ArrayList;

public class AnimationResources 
{
	//Human animations
	private static ArrayList<Animation> animsHumanBody;
	private static ArrayList<Animation> animsHumanHead;
	private static ArrayList<Animation> animsHumanLeftHumerus;
	private static ArrayList<Animation> animsHumanLeftForearm;
	private static ArrayList<Animation> animsHumanLeftHand;
	private static ArrayList<Animation> animsHumanRightHumerus;
	private static ArrayList<Animation> animsHumanRightForearm;
	private static ArrayList<Animation> animsHumanRightHand;
	private static ArrayList<Animation> animsHumanLeftThigh;
	private static ArrayList<Animation> animsHumanLeftShin;
	private static ArrayList<Animation> animsHumanLeftFoot;
	private static ArrayList<Animation> animsHumanRightThigh;
	private static ArrayList<Animation> animsHumanRightShin;
	private static ArrayList<Animation> animsHumanRightFoot;
	
	//Goomba animations
	private static ArrayList<Animation> animsGoombaBody;
	private static ArrayList<Animation> animsGoombaLeftFoot;
	private static ArrayList<Animation> animsGoombaRightFoot;
	
	//Goomba animations
	private static ArrayList<Animation> animsBobOmbBody;
	private static ArrayList<Animation> animsBobOmbLeftFoot;
	private static ArrayList<Animation> animsBobOmbRightFoot;
	
	public static void createAnimations()
	{
		//Human Animations
		int animCount = 18;
		animsHumanBody = new ArrayList<Animation>();
			while(animsHumanBody.size() < animCount) animsHumanBody.add(new Animation());
		animsHumanHead = new ArrayList<Animation>();
			while(animsHumanHead.size() < animCount) animsHumanHead.add(new Animation());
		animsHumanLeftHumerus = new ArrayList<Animation>();
			while(animsHumanLeftHumerus.size() < animCount) animsHumanLeftHumerus.add(new Animation());
		animsHumanLeftForearm = new ArrayList<Animation>();
			while(animsHumanLeftForearm.size() < animCount) animsHumanLeftForearm.add(new Animation());
		animsHumanLeftHand = new ArrayList<Animation>();
			while(animsHumanLeftHand.size() < animCount) animsHumanLeftHand.add(new Animation());
		animsHumanRightHumerus = new ArrayList<Animation>();
			while(animsHumanRightHumerus.size() < animCount) animsHumanRightHumerus.add(new Animation());
		animsHumanRightForearm = new ArrayList<Animation>();
			while(animsHumanRightForearm.size() < animCount) animsHumanRightForearm.add(new Animation());
		animsHumanRightHand = new ArrayList<Animation>();
			while(animsHumanRightHand.size() < animCount) animsHumanRightHand.add(new Animation());
		animsHumanLeftThigh = new ArrayList<Animation>();
			while(animsHumanLeftThigh.size() < animCount) animsHumanLeftThigh.add(new Animation());
		animsHumanLeftShin = new ArrayList<Animation>();
			while(animsHumanLeftShin.size() < animCount) animsHumanLeftShin.add(new Animation());
		animsHumanLeftFoot = new ArrayList<Animation>();
			while(animsHumanLeftFoot.size() < animCount) animsHumanLeftFoot.add(new Animation());
		animsHumanRightThigh = new ArrayList<Animation>();
			while(animsHumanRightThigh.size() < animCount) animsHumanRightThigh.add(new Animation());
		animsHumanRightShin = new ArrayList<Animation>();
			while(animsHumanRightShin.size() < animCount) animsHumanRightShin.add(new Animation());
		animsHumanRightFoot = new ArrayList<Animation>();
			while(animsHumanRightFoot.size() < animCount) animsHumanRightFoot.add(new Animation());
		
		//stand animation
		animsHumanBody.get(0).addKeyframe(0, 0, 3.35f, 0, 0, 0, 0, 1);
		animsHumanBody.get(0).addKeyframe(50, 0, 3.7f, 0, 0, 0, 0, 1);
		animsHumanBody.get(0).addKeyframe(100, 0, 3.35f, 0, 0, 0, 0, 1);
		
		//run animation
		animsHumanBody.get(1).addKeyframe(0, 1, 3.7f, 0, 0, 0, -15, 1);
		animsHumanBody.get(1).addKeyframe(25, 0, 3.3f, 0, 0, 10, -15, 1);
		animsHumanBody.get(1).addKeyframe(50, 1, 3.7f, 0, 0, 0, -15, 1);
		animsHumanBody.get(1).addKeyframe(75, 0, 3.3f, 0, 0, -10, -15, 1);
		animsHumanBody.get(1).addKeyframe(100, 1, 3.7f, 0, 0, 0, -15, 1);
		
		//jump animation
		animsHumanBody.get(2).addKeyframe(0, 0, 3.7f, 0, 0, 0, 0, 1);
		animsHumanBody.get(2).addKeyframe(100, 0, 3.7f, 0, 0, 45, 0, 1);
		
		//double jump , now a stomp at time == 100
		animsHumanBody.get(3).addKeyframe(0, 0, 3.7f, 0, 0, 0, 0, 1);
		animsHumanBody.get(3).addKeyframe(100, 0, 3.7f, 0, 0, 0, 0, 1);
		
		//triple jump animation
		animsHumanBody.get(4).addKeyframe(0, 0, 3.7f, 0, 0, 0, 0, 1);
		animsHumanBody.get(4).addKeyframe(100, 0, 3.7f, 0, 0, 0, -720, 1);
		
		//side flip animation
		animsHumanBody.get(5).addKeyframe(0, 0, 3.7f, 0, 0, 0, 0, 1);
		animsHumanBody.get(5).addKeyframe(100, 0, 3.7f, 0, 0, 0, -360, 1);
		
		//dive animation
		animsHumanBody.get(6).addKeyframe(0, 0, 0f, 0, 0, 0, 0, 1);
		animsHumanBody.get(6).addKeyframe(100, 0, 0f, 0, 0, 0, 0, 1);
		
		//rollout animation
		animsHumanBody.get(7).addKeyframe(0, 0, 0f, 0, 0, 0, 45, 1);
		animsHumanBody.get(7).addKeyframe(100, 0, 0f, 0, 0, 0, 45, 1);
		
		//skid animation
		animsHumanBody.get(8).addKeyframe(0, 0, 3.7f, 0, 0, 0, 0, 1);
		animsHumanBody.get(8).addKeyframe(100, 0, 3f, 0, 0, 0, 0, 1);
		
		//ground pound animation
		animsHumanBody.get(9).addKeyframe(0, 0, 1f, 0, 0, 0, 0, 1);//3.7
		animsHumanBody.get(9).addKeyframe(100, 0, 1f, 0, 0, 0, 0, 1);//3.7
		
		//long jump animation
		animsHumanBody.get(10).addKeyframe(0, 0, 3.7f, 0, 0, 0, 0, 1);
		animsHumanBody.get(10).addKeyframe(100, 0, 3.7f, 0, 0, 0, 0, 1);
		
		//long jump animation
		animsHumanBody.get(11).addKeyframe(0, 0, 1f, 0, 0, 180, 0, 1);
		animsHumanBody.get(11).addKeyframe(100, 0, 1f, 0, 0, 180, 0, 1);
		
		//crouch animation
		animsHumanBody.get(12).addKeyframe(0, 0, 2f, 0, 0, 0, -30, 1);
		animsHumanBody.get(12).addKeyframe(100, 0, 2f, 0, 0, 0, -30, 1);
		
		//high jump animation
		animsHumanBody.get(13).addKeyframe(0, 0, 2f, 0, 0, 0, 0, 1);
		animsHumanBody.get(13).addKeyframe(100, 0, 2f, 0, 0, 0, 0, 1);
		
		//pose animation
		animsHumanBody.get(14).addKeyframe(0, 0, 3f, 0, 15, 0, 0, 1);
		animsHumanBody.get(14).addKeyframe(100, 0, 3f, 0, 15, 0, 0, 1);
		
		//swim animation
		animsHumanBody.get(15).addKeyframe(0, 0, 0f, 0, 0, 0, -90, 1);
		animsHumanBody.get(15).addKeyframe(100, 0, 0f, 0, 0, 0, -90, 1);
		
		//swim stroke animation
		animsHumanBody.get(16).addKeyframe(0, 0, 0f, 0, 0, 0, -90, 1);
		animsHumanBody.get(16).addKeyframe(100, 0, 0f, 0, 0, 0, -90, 1);
		
		//t pose animation
		animsHumanBody.get(17).addKeyframe(0, 0, 4f, 0, 0, 0, 0, 1);
		animsHumanBody.get(17).addKeyframe(100, 0, 4f, 0, 0, 0, 0, 1);
		
		animsHumanHead.get(0).addKeyframe(0, 0, 0, 0, 1);
		animsHumanHead.get(0).addKeyframe(33, 0, 60, 10, 1);
		animsHumanHead.get(0).addKeyframe(66, 0, -60, 10, 1);
		animsHumanHead.get(0).addKeyframe(100, 0, 0, 0, 1);
		animsHumanHead.get(1).addKeyframe(0, 0, 0, 20, 1);
		animsHumanHead.get(1).addKeyframe(25, 0, 0, 15, 1);
		animsHumanHead.get(1).addKeyframe(50, 0, 0, 20, 1);
		animsHumanHead.get(1).addKeyframe(75, 0, 0, 15, 1);
		animsHumanHead.get(1).addKeyframe(100, 0, 0, 20, 1);
		animsHumanHead.get(2).addKeyframe(0, 0, 0, 0, 1);
		animsHumanHead.get(2).addKeyframe(100, 0, -40, 20, 1);
		animsHumanHead.get(3).addKeyframe(0, 0, 0, 20, 1);
		animsHumanHead.get(3).addKeyframe(75, 0, 0, 40, 1);
		animsHumanHead.get(3).addKeyframe(100, 0, 0, -45, 1);//edit
		animsHumanHead.get(4).addKeyframe(0, 0, 0, -30, 1);
		animsHumanHead.get(4).addKeyframe(100, 0, 0, 20, 1);
		animsHumanHead.get(5).addKeyframe(0, 0, 0, -30, 1);
		animsHumanHead.get(5).addKeyframe(100, 0, 0, 20, 1);
		animsHumanHead.get(6).addKeyframe(0, 0, 0, 70, 1);
		animsHumanHead.get(6).addKeyframe(100, 0, 0, 90, 1);
		animsHumanHead.get(7).addKeyframe(0, 0, 0, -30, 1);
		animsHumanHead.get(7).addKeyframe(100, 0, 0, -30, 1);
		animsHumanHead.get(8).addKeyframe(0, 0, 0, 0, 1);
		animsHumanHead.get(8).addKeyframe(100, 0, 0, -20, 1);
		animsHumanHead.get(9).addKeyframe(0, 0, 0, -30, 1);
		animsHumanHead.get(9).addKeyframe(100, 0, 0, -30, 1);
		animsHumanHead.get(10).addKeyframe(0, 0, 0, 10, 1);
		animsHumanHead.get(10).addKeyframe(100, 0, 0, -20, 1);
		animsHumanHead.get(11).addKeyframe(0, 0, 0, -40, 1);
		animsHumanHead.get(11).addKeyframe(100, 0, 0, -40, 1);
		animsHumanHead.get(12).addKeyframe(0, 0, 0, 5, 1);
		animsHumanHead.get(12).addKeyframe(100, 0, 0, 5, 1);
		animsHumanHead.get(13).addKeyframe(0, 0, 0, 45, 1);
		animsHumanHead.get(13).addKeyframe(100, 0, 0, 45, 1);
		animsHumanHead.get(14).addKeyframe(0, 0, 0, 45, 1);
		animsHumanHead.get(14).addKeyframe(100, 0, 0, 45, 1);
		animsHumanHead.get(15).addKeyframe(0, 0, 0, 70, 1);
		animsHumanHead.get(15).addKeyframe(100, 0, 0, 70, 1);
		animsHumanHead.get(16).addKeyframe(0, 0, 0, 70, 1);
		animsHumanHead.get(16).addKeyframe(100, 0, 0, 70, 1);
		animsHumanHead.get(17).addKeyframe(0, 0, 0, 0, 1);
		animsHumanHead.get(17).addKeyframe(100, 0, 0, 0, 1);
		
		animsHumanLeftHumerus.get(0).addKeyframe(0, 0, 90+20, -25, 1);//stand
		animsHumanLeftHumerus.get(0).addKeyframe(50, 0, 90+10, -35, 1);
		animsHumanLeftHumerus.get(0).addKeyframe(100, 0, 90+20, -25, 1);
		animsHumanLeftHumerus.get(1).addKeyframe(0, 0, 165, -25, 1);//run//-25 //45z for close
		animsHumanLeftHumerus.get(1).addKeyframe(50, 0, 80, -55, 1);
		animsHumanLeftHumerus.get(1).addKeyframe(100, 0, 165, -25, 1);
		animsHumanLeftHumerus.get(2).addKeyframe(0, 0, 90, -50, 1);
		animsHumanLeftHumerus.get(2).addKeyframe(100, 0, 90, -80, 1);
		animsHumanLeftHumerus.get(3).addKeyframe(0, 0, 90, -75, 1);
		animsHumanLeftHumerus.get(3).addKeyframe(75, 0, 90, -75, 1);
		animsHumanLeftHumerus.get(3).addKeyframe(100, 0, 140, 0, 1); //edit 25
		animsHumanLeftHumerus.get(4).addKeyframe(0, 0, 0, -70, 1);
		animsHumanLeftHumerus.get(4).addKeyframe(100, 0, 0, 60, 1);
		animsHumanLeftHumerus.get(5).addKeyframe(0, 0, 0, -70, 1);
		animsHumanLeftHumerus.get(5).addKeyframe(100, 0, 0, 60, 1);
		animsHumanLeftHumerus.get(6).addKeyframe(0, 0, 0, 30, 1);
		animsHumanLeftHumerus.get(6).addKeyframe(100, 0, 0, 90, 1);
		animsHumanLeftHumerus.get(7).addKeyframe(0, 0, -20, 135, 1);
		animsHumanLeftHumerus.get(7).addKeyframe(100, 0, -20, 135, 1);
		animsHumanLeftHumerus.get(8).addKeyframe(0, 0, 170, 5, 1);
		animsHumanLeftHumerus.get(8).addKeyframe(100, 0, 155, 15, 1);
		animsHumanLeftHumerus.get(9).addKeyframe(0, 0, 135, 45, 1);
		animsHumanLeftHumerus.get(9).addKeyframe(100, 0, 135, 45, 1);
		animsHumanLeftHumerus.get(10).addKeyframe(0, 0, 135, -10, 1);
		animsHumanLeftHumerus.get(10).addKeyframe(100, 0, 20, -20, 1);
		animsHumanLeftHumerus.get(11).addKeyframe(0, 0, 10, -10, 1);
		animsHumanLeftHumerus.get(11).addKeyframe(100, 0, 10, -10, 1);
		animsHumanLeftHumerus.get(12).addKeyframe(0, 0, 45, 35, 1);
		animsHumanLeftHumerus.get(12).addKeyframe(100, 0, 45, 35, 1);
		animsHumanLeftHumerus.get(13).addKeyframe(0, 0, 5, -130, 1);
		animsHumanLeftHumerus.get(13).addKeyframe(100, 0, 5, -130, 1);
		animsHumanLeftHumerus.get(14).addKeyframe(0, 0, 5, -130, 1);
		animsHumanLeftHumerus.get(14).addKeyframe(100, 0, 5, -130, 1);
		animsHumanLeftHumerus.get(15).addKeyframe(0, 0, -30, -90, 1);
		animsHumanLeftHumerus.get(15).addKeyframe(100, 0, -30, -90, 1);
		animsHumanLeftHumerus.get(16).addKeyframe(0, 0, -150, -90, 1);
		animsHumanLeftHumerus.get(16).addKeyframe(50, 0, -30, -90, 1);
		animsHumanLeftHumerus.get(16).addKeyframe(100, 0, -30, -90, 1);
		animsHumanLeftHumerus.get(17).addKeyframe(0, 0, 90, 0, 1);
		animsHumanLeftHumerus.get(17).addKeyframe(100, 0, 90, 0, 1);
		
		animsHumanRightHumerus.get(0).addKeyframe(0, 0, -(90+20), -25, 1);
		animsHumanRightHumerus.get(0).addKeyframe(50, 0, -(90+10), -35, 1);
		animsHumanRightHumerus.get(0).addKeyframe(100, 0, -(90+20), -25, 1);
		animsHumanRightHumerus.get(1).addKeyframe(0, 0, -80, -55, 1);
		animsHumanRightHumerus.get(1).addKeyframe(50, 0, -165, -25, 1);
		animsHumanRightHumerus.get(1).addKeyframe(100, 0, -80, -55, 1);
		animsHumanRightHumerus.get(2).addKeyframe(0, 0, -90, 50, 1);
		animsHumanRightHumerus.get(2).addKeyframe(100, 0, -90, 80, 1);
		animsHumanRightHumerus.get(3).addKeyframe(0, 0, -90, -75, 1);
		animsHumanRightHumerus.get(3).addKeyframe(75, 0, -90, -75, 1);
		animsHumanRightHumerus.get(3).addKeyframe(100, 0, -140, 0, 1); //edit
		animsHumanRightHumerus.get(4).addKeyframe(0, 0, 0, -70, 1);
		animsHumanRightHumerus.get(4).addKeyframe(100, 0, 0, 60, 1);
		animsHumanRightHumerus.get(5).addKeyframe(0, 0, 0, -70, 1);
		animsHumanRightHumerus.get(5).addKeyframe(100, 0, 0, 60, 1);
		animsHumanRightHumerus.get(6).addKeyframe(0, 0, 0, 30, 1);
		animsHumanRightHumerus.get(6).addKeyframe(100, 0, 0, 90, 1);
		animsHumanRightHumerus.get(7).addKeyframe(0, 0, 20, 135, 1);
		animsHumanRightHumerus.get(7).addKeyframe(100, 0, 20, 135, 1);
		animsHumanRightHumerus.get(8).addKeyframe(0, 0, -170, 5, 1);
		animsHumanRightHumerus.get(8).addKeyframe(100, 0, -155, 15, 1);
		animsHumanRightHumerus.get(9).addKeyframe(0, 0, -135, 45, 1);
		animsHumanRightHumerus.get(9).addKeyframe(100, 0, -135, 45, 1);
		animsHumanRightHumerus.get(10).addKeyframe(0, 0, -135, -10, 1);
		animsHumanRightHumerus.get(10).addKeyframe(100, 0, -20, -20, 1);
		animsHumanRightHumerus.get(11).addKeyframe(0, 0, -10, -10, 1);
		animsHumanRightHumerus.get(11).addKeyframe(100, 0, -10, -10, 1);
		animsHumanRightHumerus.get(12).addKeyframe(0, 0, -45, 35, 1);
		animsHumanRightHumerus.get(12).addKeyframe(100, 0, -45, 35, 1);
		animsHumanRightHumerus.get(13).addKeyframe(0, 0, -5, -130, 1);
		animsHumanRightHumerus.get(13).addKeyframe(100, 0, -5, -130, 1);
		animsHumanRightHumerus.get(14).addKeyframe(0, 0, -5, -130, 1);
		animsHumanRightHumerus.get(14).addKeyframe(100, 0, -5, -130, 1);
		animsHumanRightHumerus.get(15).addKeyframe(0, 0, 30, -90, 1);
		animsHumanRightHumerus.get(15).addKeyframe(100, 0, 30, -90, 1);
		animsHumanRightHumerus.get(16).addKeyframe(0, 0, 150, -90, 1);
		animsHumanRightHumerus.get(16).addKeyframe(50, 0, 30, -90, 1);
		animsHumanRightHumerus.get(16).addKeyframe(100, 0, 30, -90, 1);
		animsHumanRightHumerus.get(17).addKeyframe(0, 0, -90, 0, 1);
		animsHumanRightHumerus.get(17).addKeyframe(100, 0, -90, 0, 1);
		
		animsHumanLeftForearm.get(0).addKeyframe(0, 0, -120, -25, 1);//stand
		animsHumanLeftForearm.get(0).addKeyframe(50, 0, -120, 0, 1);
		animsHumanLeftForearm.get(0).addKeyframe(100, 0, -120, -25, 1);
		animsHumanLeftForearm.get(1).addKeyframe(0, 0, 0, -20, 1);//run//-20 //-90z for close
		animsHumanLeftForearm.get(1).addKeyframe(50, 0, -80, 90, 1);
		animsHumanLeftForearm.get(1).addKeyframe(100, 0, 0, -20, 1);
		animsHumanLeftForearm.get(2).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftForearm.get(2).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftForearm.get(3).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftForearm.get(3).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftForearm.get(4).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftForearm.get(4).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftForearm.get(5).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftForearm.get(5).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftForearm.get(6).addKeyframe(0, 0, 0, 90, 1);
		animsHumanLeftForearm.get(6).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftForearm.get(7).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftForearm.get(7).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftForearm.get(8).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftForearm.get(8).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftForearm.get(9).addKeyframe(0, 0, -90, -90, 1);
		animsHumanLeftForearm.get(9).addKeyframe(100, 0, -90, -90, 1);
		animsHumanLeftForearm.get(10).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftForearm.get(10).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftForearm.get(11).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftForearm.get(11).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftForearm.get(12).addKeyframe(0, 0, -20, 20, 1);
		animsHumanLeftForearm.get(12).addKeyframe(100, 0, -20, 20, 1);
		animsHumanLeftForearm.get(13).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftForearm.get(13).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftForearm.get(14).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftForearm.get(14).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftForearm.get(15).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftForearm.get(15).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftForearm.get(16).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftForearm.get(16).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftForearm.get(17).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftForearm.get(17).addKeyframe(100, 0, 0, 0, 1);
		
		animsHumanRightForearm.get(0).addKeyframe(0, 0, -(-120), -25, 1);
		animsHumanRightForearm.get(0).addKeyframe(50, 0, -(-120), 0, 1);
		animsHumanRightForearm.get(0).addKeyframe(100, 0, -(-120), -25, 1);
		animsHumanRightForearm.get(1).addKeyframe(0, 0, 80, 90, 1);//run//-20 //-90z for close
		animsHumanRightForearm.get(1).addKeyframe(50, 0, 0, -20, 1);
		animsHumanRightForearm.get(1).addKeyframe(100, 0, 80, 90, 1);
		animsHumanRightForearm.get(2).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightForearm.get(2).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightForearm.get(3).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightForearm.get(3).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightForearm.get(4).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightForearm.get(4).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightForearm.get(5).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightForearm.get(5).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightForearm.get(6).addKeyframe(0, 0, 0, 90, 1);
		animsHumanRightForearm.get(6).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightForearm.get(7).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightForearm.get(7).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightForearm.get(8).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightForearm.get(8).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightForearm.get(9).addKeyframe(0, 0, 90, -90, 1);
		animsHumanRightForearm.get(9).addKeyframe(100, 0, 90, -90, 1);
		animsHumanRightForearm.get(10).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightForearm.get(10).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightForearm.get(11).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightForearm.get(11).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightForearm.get(12).addKeyframe(0, 0, 20, 20, 1);
		animsHumanRightForearm.get(12).addKeyframe(100, 0, 20, 20, 1);
		animsHumanRightForearm.get(13).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightForearm.get(13).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightForearm.get(14).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightForearm.get(14).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightForearm.get(15).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightForearm.get(15).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightForearm.get(16).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightForearm.get(16).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightForearm.get(17).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightForearm.get(17).addKeyframe(100, 0, 0, 0, 1);
		
		animsHumanLeftHand.get(0).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftHand.get(0).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftHand.get(1).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftHand.get(1).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftHand.get(2).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftHand.get(2).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftHand.get(3).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftHand.get(3).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftHand.get(4).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftHand.get(4).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftHand.get(5).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftHand.get(5).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftHand.get(6).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftHand.get(6).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftHand.get(7).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftHand.get(7).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftHand.get(8).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftHand.get(8).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftHand.get(9).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftHand.get(9).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftHand.get(10).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftHand.get(10).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftHand.get(11).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftHand.get(11).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftHand.get(12).addKeyframe(0, 0, 10, 30, 1);
		animsHumanLeftHand.get(12).addKeyframe(100, 0, 10, 30, 1);
		animsHumanLeftHand.get(13).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftHand.get(13).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftHand.get(14).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftHand.get(14).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftHand.get(15).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftHand.get(15).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftHand.get(16).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftHand.get(16).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftHand.get(17).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftHand.get(17).addKeyframe(100, 0, 0, 0, 1);
		
		
		animsHumanRightHand.get(0).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightHand.get(0).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightHand.get(1).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightHand.get(1).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightHand.get(2).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightHand.get(2).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightHand.get(3).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightHand.get(3).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightHand.get(4).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightHand.get(4).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightHand.get(5).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightHand.get(5).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightHand.get(6).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightHand.get(6).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightHand.get(7).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightHand.get(7).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightHand.get(8).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightHand.get(8).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightHand.get(9).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightHand.get(9).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightHand.get(10).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightHand.get(10).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightHand.get(11).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightHand.get(11).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightHand.get(12).addKeyframe(0, 0, 10, 30, 1);
		animsHumanRightHand.get(12).addKeyframe(100, 0, 10, 30, 1);
		animsHumanRightHand.get(13).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightHand.get(13).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightHand.get(14).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightHand.get(14).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightHand.get(15).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightHand.get(15).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightHand.get(16).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightHand.get(16).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightHand.get(17).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightHand.get(17).addKeyframe(100, 0, 0, 0, 1);
		
		animsHumanLeftThigh.get(0).addKeyframe(0, 0, 50, -55, 1);
		animsHumanLeftThigh.get(0).addKeyframe(50, 0, 50, -70, 1);
		animsHumanLeftThigh.get(0).addKeyframe(100, 0, 50, -55, 1);
		animsHumanLeftThigh.get(1).addKeyframe((0+50) % 100, 0, 0, -135, 1);//run
		animsHumanLeftThigh.get(1).addKeyframe((5+50) % 100, 0, 0, -130, 1);
		animsHumanLeftThigh.get(1).addKeyframe((10+50) % 100, 0, 0, -125, 1);
		animsHumanLeftThigh.get(1).addKeyframe((15+50) % 100, 0, 0, -115, 1);
		animsHumanLeftThigh.get(1).addKeyframe((20+50) % 100, 0, 0, -70, 1);
		animsHumanLeftThigh.get(1).addKeyframe((25+50) % 100, 0, 0, -10, 1);
		animsHumanLeftThigh.get(1).addKeyframe((30+50) % 100, 0, 0, 10, 1);
		animsHumanLeftThigh.get(1).addKeyframe((35+50) % 100, 0, 0, 10, 1);
		animsHumanLeftThigh.get(1).addKeyframe((40+50) % 100, 0, 0, 10, 1);
		animsHumanLeftThigh.get(1).addKeyframe((45+50) % 100, 0, 0, 12, 1);
		animsHumanLeftThigh.get(1).addKeyframe((50+50) % 100, 0, 0, 15, 1);
		animsHumanLeftThigh.get(1).addKeyframe((55+50) % 100, 0, 0, 5, 1);
		animsHumanLeftThigh.get(1).addKeyframe((60+50) % 100, 0, 0, -50, 1);
		animsHumanLeftThigh.get(1).addKeyframe((65+50) % 100, 0, 0, -55, 1);
		animsHumanLeftThigh.get(1).addKeyframe((70+50) % 100, 0, 0, -60, 1);
		animsHumanLeftThigh.get(1).addKeyframe((75+50) % 100, 0, 0, -90, 1);
		animsHumanLeftThigh.get(1).addKeyframe((80+50) % 100, 0, 0, -100, 1);
		animsHumanLeftThigh.get(1).addKeyframe((85+50) % 100, 0, 0, -120, 1);
		animsHumanLeftThigh.get(1).addKeyframe((90+50) % 100, 0, 0, -130, 1);
		animsHumanLeftThigh.get(1).addKeyframe((95+50) % 100, 0, 0, -130, 1);
		animsHumanLeftThigh.get(1).addKeyframe((100+50) % 100, 0, 0, -135, 1);
		animsHumanLeftThigh.get(2).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftThigh.get(2).addKeyframe(100, 0, -20, 20, 1);
		animsHumanLeftThigh.get(3).addKeyframe(0, 0, 0, -80, 1);
		animsHumanLeftThigh.get(3).addKeyframe(75, 0, 0, -80, 1);
		animsHumanLeftThigh.get(3).addKeyframe(100, 0, 0, -45, 1);
		animsHumanLeftThigh.get(4).addKeyframe(0, 0, 0, 45, 1);
		animsHumanLeftThigh.get(4).addKeyframe(100, 0, 0, -90, 1);
		animsHumanLeftThigh.get(5).addKeyframe(0, 0, 0, 45, 1);
		animsHumanLeftThigh.get(5).addKeyframe(100, 0, 0, -90, 1);
		animsHumanLeftThigh.get(6).addKeyframe(0, 0, 0, -45, 1);
		animsHumanLeftThigh.get(6).addKeyframe(100, 0, 0, -90, 1);
		animsHumanLeftThigh.get(7).addKeyframe(0, 0, 0, -90, 1);
		animsHumanLeftThigh.get(7).addKeyframe(100, 0, 0, -90, 1);
		animsHumanLeftThigh.get(8).addKeyframe(0, 0, 0, -45, 1);
		animsHumanLeftThigh.get(8).addKeyframe(100, 0, 0, -10, 1);
		animsHumanLeftThigh.get(9).addKeyframe(0, 0, 0, 20, 1);
		animsHumanLeftThigh.get(9).addKeyframe(100, 0, 0, 20, 1);
		animsHumanLeftThigh.get(10).addKeyframe(0, 0, 0, -10, 1);
		animsHumanLeftThigh.get(10).addKeyframe(100, 0, 0, -30, 1);
		animsHumanLeftThigh.get(11).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftThigh.get(11).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftThigh.get(12).addKeyframe(0, 0, 20, 0, 1);
		animsHumanLeftThigh.get(12).addKeyframe(100, 0, 20, 0, 1);
		animsHumanLeftThigh.get(13).addKeyframe(0, 0, 0, -130, 1);
		animsHumanLeftThigh.get(13).addKeyframe(100, 0, 0, -130, 1);
		animsHumanLeftThigh.get(14).addKeyframe(0, 0, 0, -130, 1);
		animsHumanLeftThigh.get(14).addKeyframe(100, 0, 0, -130, 1);
		animsHumanLeftThigh.get(15).addKeyframe(0, 0, 0, -70, 1);
		animsHumanLeftThigh.get(15).addKeyframe(50, 0, 0, -110, 1);
		animsHumanLeftThigh.get(15).addKeyframe(100, 0, 0, -70, 1);
		animsHumanLeftThigh.get(16).addKeyframe(0, 0, 0, -70, 1);
		animsHumanLeftThigh.get(16).addKeyframe(50, 0, 0, -110, 1);
		animsHumanLeftThigh.get(16).addKeyframe(100, 0, 0, -110, 1);
		animsHumanLeftThigh.get(17).addKeyframe(0, 0, 0, -90, 1);
		animsHumanLeftThigh.get(17).addKeyframe(100, 0, 0, -90, 1);
		
		animsHumanRightThigh.get(0).addKeyframe(0, 0, -50, -55, 1);
		animsHumanRightThigh.get(0).addKeyframe(50, 0, -50, -70, 1);
		animsHumanRightThigh.get(0).addKeyframe(100, 0, -50, -55, 1);
		animsHumanRightThigh.get(1).addKeyframe(0, 0, 0, -135, 1);//run
		animsHumanRightThigh.get(1).addKeyframe(5, 0, 0, -130, 1);
		animsHumanRightThigh.get(1).addKeyframe(10, 0, 0, -125, 1);
		animsHumanRightThigh.get(1).addKeyframe(15, 0, 0, -115, 1);
		animsHumanRightThigh.get(1).addKeyframe(20, 0, 0, -70, 1);
		animsHumanRightThigh.get(1).addKeyframe(25, 0, 0, -10, 1);
		animsHumanRightThigh.get(1).addKeyframe(30, 0, 0, 10, 1);
		animsHumanRightThigh.get(1).addKeyframe(35, 0, 0, 10, 1);
		animsHumanRightThigh.get(1).addKeyframe(40, 0, 0, 10, 1);
		animsHumanRightThigh.get(1).addKeyframe(45, 0, 0, 12, 1);
		animsHumanRightThigh.get(1).addKeyframe(50, 0, 0, 15, 1);
		animsHumanRightThigh.get(1).addKeyframe(55, 0, 0, 5, 1);
		animsHumanRightThigh.get(1).addKeyframe(60, 0, 0, -50, 1);
		animsHumanRightThigh.get(1).addKeyframe(65, 0, 0, -55, 1);
		animsHumanRightThigh.get(1).addKeyframe(70, 0, 0, -60, 1);
		animsHumanRightThigh.get(1).addKeyframe(75, 0, 0, -90, 1);
		animsHumanRightThigh.get(1).addKeyframe(80, 0, 0, -100, 1);
		animsHumanRightThigh.get(1).addKeyframe(85, 0, 0, -120, 1);
		animsHumanRightThigh.get(1).addKeyframe(90, 0, 0, -130, 1);
		animsHumanRightThigh.get(1).addKeyframe(95, 0, 0, -130, 1);
		animsHumanRightThigh.get(1).addKeyframe(100, 0, 0, -135, 1);
		animsHumanRightThigh.get(2).addKeyframe(0, 0, -20, -90, 1);
		animsHumanRightThigh.get(2).addKeyframe(100, 0, -50, -90, 1);
		animsHumanRightThigh.get(3).addKeyframe(0, 0, 0, -95, 1);
		animsHumanRightThigh.get(3).addKeyframe(75, 0, 0, -95, 1);
		animsHumanRightThigh.get(3).addKeyframe(100, 0, 0, -80, 1);
		animsHumanRightThigh.get(4).addKeyframe(0, 0, 0, 45, 1);
		animsHumanRightThigh.get(4).addKeyframe(100, 0, 0, -90, 1);
		animsHumanRightThigh.get(5).addKeyframe(0, 0, 0, 45, 1);
		animsHumanRightThigh.get(5).addKeyframe(100, 0, 0, -90, 1);
		animsHumanRightThigh.get(6).addKeyframe(0, 0, 0, -45, 1);
		animsHumanRightThigh.get(6).addKeyframe(100, 0, 0, -90, 1);
		animsHumanRightThigh.get(7).addKeyframe(0, 0, 0, -90, 1);
		animsHumanRightThigh.get(7).addKeyframe(100, 0, 0, -90, 1);
		animsHumanRightThigh.get(8).addKeyframe(0, 0, 0, -45, 1);
		animsHumanRightThigh.get(8).addKeyframe(100, 0, 0, -10, 1);
		animsHumanRightThigh.get(9).addKeyframe(0, 0, 0, 20, 1);
		animsHumanRightThigh.get(9).addKeyframe(100, 0, 0, 20, 1);
		animsHumanRightThigh.get(10).addKeyframe(0, 0, 0, -10, 1);
		animsHumanRightThigh.get(10).addKeyframe(100, 0, 0, -30, 1);
		animsHumanRightThigh.get(11).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightThigh.get(11).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightThigh.get(12).addKeyframe(0, 0, -20, 0, 1);
		animsHumanRightThigh.get(12).addKeyframe(100, 0, -20, 0, 1);
		animsHumanRightThigh.get(13).addKeyframe(0, 0, 0, -130, 1);
		animsHumanRightThigh.get(13).addKeyframe(100, 0, 0, -130, 1);
		animsHumanRightThigh.get(14).addKeyframe(0, 0, 0, -130, 1);
		animsHumanRightThigh.get(14).addKeyframe(100, 0, 0, -130, 1);
		animsHumanRightThigh.get(15).addKeyframe(0, 0, 0, -110, 1);
		animsHumanRightThigh.get(15).addKeyframe(50, 0, 0, -70, 1);
		animsHumanRightThigh.get(15).addKeyframe(100, 0, 0, -110, 1);
		animsHumanRightThigh.get(16).addKeyframe(0, 0, 0, -70, 1);
		animsHumanRightThigh.get(16).addKeyframe(50, 0, 0, -110, 1);
		animsHumanRightThigh.get(16).addKeyframe(100, 0, 0, -110, 1);
		animsHumanRightThigh.get(17).addKeyframe(0, 0, 0, -90, 1);
		animsHumanRightThigh.get(17).addKeyframe(100, 0, 0, -90, 1);
		
		animsHumanLeftShin.get(0).addKeyframe(0, 0, 0, -35*2, 1);
		animsHumanLeftShin.get(0).addKeyframe(50, 0, 0, -20*2, 1);
		animsHumanLeftShin.get(0).addKeyframe(100, 0, 0, -35*2, 1);
		animsHumanLeftShin.get(1).addKeyframe((0+50) % 100, 0, 0, -20, 1);//run
		animsHumanLeftShin.get(1).addKeyframe((5+50) % 100, 0, 0, -25, 1);
		animsHumanLeftShin.get(1).addKeyframe((10+50) % 100, 0, 0, -30, 1);
		animsHumanLeftShin.get(1).addKeyframe((15+50) % 100, 0, 0, -45, 1);
		animsHumanLeftShin.get(1).addKeyframe((20+50) % 100, 0, 0, -90, 1);
		animsHumanLeftShin.get(1).addKeyframe((25+50) % 100, 0, 0, -125, 1);
		animsHumanLeftShin.get(1).addKeyframe((30+50) % 100, 0, 0, -140, 1);
		animsHumanLeftShin.get(1).addKeyframe((35+50) % 100, 0, 0, -135, 1);
		animsHumanLeftShin.get(1).addKeyframe((40+50) % 100, 0, 0, -115, 1);
		animsHumanLeftShin.get(1).addKeyframe((45+50) % 100, 0, 0, -80, 1);
		animsHumanLeftShin.get(1).addKeyframe((50+50) % 100, 0, 0, -40, 1);
		animsHumanLeftShin.get(1).addKeyframe((55+50) % 100, 0, 0, -30, 1);
		animsHumanLeftShin.get(1).addKeyframe((60+50) % 100, 0, 0, -20, 1);
		animsHumanLeftShin.get(1).addKeyframe((65+50) % 100, 0, 0, -17, 1);
		animsHumanLeftShin.get(1).addKeyframe((70+50) % 100, 0, 0, -17, 1);
		animsHumanLeftShin.get(1).addKeyframe((75+50) % 100, 0, 0, -5, 1);
		animsHumanLeftShin.get(1).addKeyframe((80+50) % 100, 0, 0, 0, 1);
		animsHumanLeftShin.get(1).addKeyframe((85+50) % 100, 0, 0, 0, 1);
		animsHumanLeftShin.get(1).addKeyframe((90+50) % 100, 0, 0, 0, 1);
		animsHumanLeftShin.get(1).addKeyframe((95+50) % 100, 0, 0, -15, 1);
		animsHumanLeftShin.get(1).addKeyframe((100+50) % 100, 0, 0, -20, 1);
		animsHumanLeftShin.get(2).addKeyframe(0, 0, 0, -90, 1);
		animsHumanLeftShin.get(2).addKeyframe(100, 0, 0, -90, 1);
		animsHumanLeftShin.get(3).addKeyframe(0, 0, 0, -20, 1);
		animsHumanLeftShin.get(3).addKeyframe(75, 0, 0, -20, 1);
		animsHumanLeftShin.get(3).addKeyframe(100, 0, 0, -90, 1);
		animsHumanLeftShin.get(4).addKeyframe(0, 0, 0, -130, 1);
		animsHumanLeftShin.get(4).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftShin.get(5).addKeyframe(0, 0, 0, -130, 1);
		animsHumanLeftShin.get(5).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftShin.get(6).addKeyframe(0, 0, 0, -90, 1);
		animsHumanLeftShin.get(6).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftShin.get(7).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftShin.get(7).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftShin.get(8).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftShin.get(8).addKeyframe(100, 0, 0, -60, 1);
		animsHumanLeftShin.get(9).addKeyframe(0, 0, 0, -10, 1);
		animsHumanLeftShin.get(9).addKeyframe(100, 0, 0, -10, 1);
		animsHumanLeftShin.get(10).addKeyframe(0, 0, 0, -10, 1);
		animsHumanLeftShin.get(10).addKeyframe(100, 0, 0, -10, 1);
		animsHumanLeftShin.get(11).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftShin.get(11).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftShin.get(12).addKeyframe(0, 0, 0, -120, 1);
		animsHumanLeftShin.get(12).addKeyframe(100, 0, 0, -120, 1);
		animsHumanLeftShin.get(13).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftShin.get(13).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftShin.get(14).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftShin.get(14).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftShin.get(15).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftShin.get(15).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftShin.get(16).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftShin.get(16).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftShin.get(17).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftShin.get(17).addKeyframe(100, 0, 0, 0, 1);
		
		animsHumanRightShin.get(0).addKeyframe(0, 0, 0, -35*2, 1);
		animsHumanRightShin.get(0).addKeyframe(50, 0, 0, -20*2, 1);
		animsHumanRightShin.get(0).addKeyframe(100, 0, 0, -35*2, 1);
		animsHumanRightShin.get(1).addKeyframe(0, 0, 0, -20, 1);//run
		animsHumanRightShin.get(1).addKeyframe(5, 0, 0, -25, 1);
		animsHumanRightShin.get(1).addKeyframe(10, 0, 0, -30, 1);
		animsHumanRightShin.get(1).addKeyframe(15, 0, 0, -45, 1);
		animsHumanRightShin.get(1).addKeyframe(20, 0, 0, -90, 1);
		animsHumanRightShin.get(1).addKeyframe(25, 0, 0, -125, 1);
		animsHumanRightShin.get(1).addKeyframe(30, 0, 0, -140, 1);
		animsHumanRightShin.get(1).addKeyframe(35, 0, 0, -135, 1);
		animsHumanRightShin.get(1).addKeyframe(40, 0, 0, -115, 1);
		animsHumanRightShin.get(1).addKeyframe(45, 0, 0, -80, 1);
		animsHumanRightShin.get(1).addKeyframe(50, 0, 0, -40, 1);
		animsHumanRightShin.get(1).addKeyframe(55, 0, 0, -30, 1);
		animsHumanRightShin.get(1).addKeyframe(60, 0, 0, -20, 1);
		animsHumanRightShin.get(1).addKeyframe(65, 0, 0, -17, 1);
		animsHumanRightShin.get(1).addKeyframe(70, 0, 0, -17, 1);
		animsHumanRightShin.get(1).addKeyframe(75, 0, 0, -5, 1);
		animsHumanRightShin.get(1).addKeyframe(80, 0, 0, 0, 1);
		animsHumanRightShin.get(1).addKeyframe(85, 0, 0, 0, 1);
		animsHumanRightShin.get(1).addKeyframe(90, 0, 0, 0, 1);
		animsHumanRightShin.get(1).addKeyframe(95, 0, 0, -15, 1);
		animsHumanRightShin.get(1).addKeyframe(100, 0, 0, -20, 1);
		animsHumanRightShin.get(2).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightShin.get(2).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightShin.get(3).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightShin.get(3).addKeyframe(75, 0, 0, 0, 1);
		animsHumanRightShin.get(3).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightShin.get(4).addKeyframe(0, 0, 0, -130, 1);
		animsHumanRightShin.get(4).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightShin.get(5).addKeyframe(0, 0, 0, -130, 1);
		animsHumanRightShin.get(5).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightShin.get(6).addKeyframe(0, 0, 0, -90, 1);
		animsHumanRightShin.get(6).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightShin.get(7).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightShin.get(7).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightShin.get(8).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightShin.get(8).addKeyframe(100, 0, 0, -60, 1);
		animsHumanRightShin.get(9).addKeyframe(0, 0, 0, -10, 1);
		animsHumanRightShin.get(9).addKeyframe(100, 0, 0, -10, 1);
		animsHumanRightShin.get(10).addKeyframe(0, 0, 0, -10, 1);
		animsHumanRightShin.get(10).addKeyframe(100, 0, 0, -10, 1);
		animsHumanRightShin.get(11).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightShin.get(11).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightShin.get(12).addKeyframe(0, 0, 0, -120, 1);
		animsHumanRightShin.get(12).addKeyframe(100, 0, 0, -120, 1);
		animsHumanRightShin.get(13).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightShin.get(13).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightShin.get(14).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightShin.get(14).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightShin.get(15).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightShin.get(15).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightShin.get(16).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightShin.get(16).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightShin.get(17).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightShin.get(17).addKeyframe(100, 0, 0, 0, 1);
		
		animsHumanLeftFoot.get(0).addKeyframe(0, 0, 0, 55+35*2, 1);
		animsHumanLeftFoot.get(0).addKeyframe(50, 0, 0, 70+20*2, 1);
		animsHumanLeftFoot.get(0).addKeyframe(100, 0, 0, 55+35*2, 1);
		animsHumanLeftFoot.get(1).addKeyframe((0+50) % 100, 0, 0, -10, 1);//run
		animsHumanLeftFoot.get(1).addKeyframe((5+50) % 100, 0, 0, 15, 1);
		animsHumanLeftFoot.get(1).addKeyframe((10+50) % 100, 0, 0, 30, 1);//was 60
		animsHumanLeftFoot.get(1).addKeyframe((15+50) % 100, 0, 0, 45, 1);
		animsHumanLeftFoot.get(1).addKeyframe((20+50) % 100, 0, 0, 30, 1);
		animsHumanLeftFoot.get(1).addKeyframe((25+50) % 100, 0, 0, 10, 1);
		animsHumanLeftFoot.get(1).addKeyframe((30+50) % 100, 0, 0, 0, 1);
		animsHumanLeftFoot.get(1).addKeyframe((35+50) % 100, 0, 0, 30, 1);
		animsHumanLeftFoot.get(1).addKeyframe((40+50) % 100, 0, 0, 120, 1);
		animsHumanLeftFoot.get(1).addKeyframe((45+50) % 100, 0, 0, 115, 1);
		animsHumanLeftFoot.get(1).addKeyframe((50+50) % 100, 0, 0, 80, 1);
		animsHumanLeftFoot.get(1).addKeyframe((55+50) % 100, 0, 0, 80, 1);
		animsHumanLeftFoot.get(1).addKeyframe((60+50) % 100, 0, 0, 95, 1);
		animsHumanLeftFoot.get(1).addKeyframe((65+50) % 100, 0, 0, 95, 1);
		animsHumanLeftFoot.get(1).addKeyframe((70+50) % 100, 0, 0, 90, 1);
		animsHumanLeftFoot.get(1).addKeyframe((75+50) % 100, 0, 0, 80, 1);
		animsHumanLeftFoot.get(1).addKeyframe((80+50) % 100, 0, 0, 60, 1);
		animsHumanLeftFoot.get(1).addKeyframe((85+50) % 100, 0, 0, 30, 1);
		animsHumanLeftFoot.get(1).addKeyframe((90+50) % 100, 0, 0, 10, 1);
		animsHumanLeftFoot.get(1).addKeyframe((95+50) % 100, 0, 0, 0, 1);
		animsHumanLeftFoot.get(1).addKeyframe((100+50) % 100, 0, 0, -10, 1);
		animsHumanLeftFoot.get(2).addKeyframe(0, 0, 0, 45, 1);
		animsHumanLeftFoot.get(2).addKeyframe(100, 0, 0, 45, 1);
		animsHumanLeftFoot.get(3).addKeyframe(0, 0, 0, 90, 1);
		animsHumanLeftFoot.get(3).addKeyframe(75, 0, 0, 90, 1);
		animsHumanLeftFoot.get(3).addKeyframe(100, 0, 0, 90, 1);
		animsHumanLeftFoot.get(4).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftFoot.get(4).addKeyframe(100, 0, 0, 30, 1);
		animsHumanLeftFoot.get(5).addKeyframe(0, 0, 0, 0, 1);
		animsHumanLeftFoot.get(5).addKeyframe(100, 0, 0, 30, 1);
		animsHumanLeftFoot.get(6).addKeyframe(0, 0, 0, 45, 1);
		animsHumanLeftFoot.get(6).addKeyframe(100, 0, 0, 0, 1);
		animsHumanLeftFoot.get(7).addKeyframe(0, 0, 0, 70, 1);
		animsHumanLeftFoot.get(7).addKeyframe(100, 0, 0, 70, 1);
		animsHumanLeftFoot.get(8).addKeyframe(0, 0, 0, 90, 1);
		animsHumanLeftFoot.get(8).addKeyframe(100, 0, 0, 120, 1);
		animsHumanLeftFoot.get(9).addKeyframe(0, 0, 0, 60, 1);
		animsHumanLeftFoot.get(9).addKeyframe(100, 0, 0, 60, 1);
		animsHumanLeftFoot.get(10).addKeyframe(0, 0, 0, 90, 1);
		animsHumanLeftFoot.get(10).addKeyframe(100, 0, 0, 80, 1);
		animsHumanLeftFoot.get(11).addKeyframe(0, 0, 0, 120, 1);
		animsHumanLeftFoot.get(11).addKeyframe(100, 0, 0, 120, 1);
		animsHumanLeftFoot.get(12).addKeyframe(0, 0, 0, 150, 1);
		animsHumanLeftFoot.get(12).addKeyframe(100, 0, 0, 150, 1);
		animsHumanLeftFoot.get(13).addKeyframe(0, 0, 0, 30, 1);
		animsHumanLeftFoot.get(13).addKeyframe(100, 0, 0, 30, 1);
		animsHumanLeftFoot.get(14).addKeyframe(0, 0, 0, 30, 1);
		animsHumanLeftFoot.get(14).addKeyframe(100, 0, 0, 30, 1);
		animsHumanLeftFoot.get(15).addKeyframe(0, 0, 0, 30, 1);
		animsHumanLeftFoot.get(15).addKeyframe(100, 0, 0, 30, 1);
		animsHumanLeftFoot.get(16).addKeyframe(0, 0, 0, 30, 1);
		animsHumanLeftFoot.get(16).addKeyframe(100, 0, 0, 30, 1);
		animsHumanLeftFoot.get(17).addKeyframe(0, 0, 0, 90, 1);
		animsHumanLeftFoot.get(17).addKeyframe(100, 0, 0, 90, 1);
		
		animsHumanRightFoot.get(0).addKeyframe(0, 0, 0, 55+35*2, 1);
		animsHumanRightFoot.get(0).addKeyframe(50, 0, 0, 70+20*2, 1);
		animsHumanRightFoot.get(0).addKeyframe(100, 0, 0, 55+35*2, 1);
		animsHumanRightFoot.get(1).addKeyframe(0, 0, 0, -10, 1);//run
		animsHumanRightFoot.get(1).addKeyframe(5, 0, 0, 15, 1);
		animsHumanRightFoot.get(1).addKeyframe(10, 0, 0, 30, 1);//was 60
		animsHumanRightFoot.get(1).addKeyframe(15, 0, 0, 45, 1);
		animsHumanRightFoot.get(1).addKeyframe(20, 0, 0, 30, 1);
		animsHumanRightFoot.get(1).addKeyframe(25, 0, 0, 10, 1);
		animsHumanRightFoot.get(1).addKeyframe(30, 0, 0, 0, 1);
		animsHumanRightFoot.get(1).addKeyframe(35, 0, 0, 30, 1);
		animsHumanRightFoot.get(1).addKeyframe(40, 0, 0, 120, 1);
		animsHumanRightFoot.get(1).addKeyframe(45, 0, 0, 115, 1);
		animsHumanRightFoot.get(1).addKeyframe(50, 0, 0, 80, 1);
		animsHumanRightFoot.get(1).addKeyframe(55, 0, 0, 80, 1);
		animsHumanRightFoot.get(1).addKeyframe(60, 0, 0, 95, 1);
		animsHumanRightFoot.get(1).addKeyframe(65, 0, 0, 95, 1);
		animsHumanRightFoot.get(1).addKeyframe(70, 0, 0, 90, 1);
		animsHumanRightFoot.get(1).addKeyframe(75, 0, 0, 80, 1);
		animsHumanRightFoot.get(1).addKeyframe(80, 0, 0, 60, 1);
		animsHumanRightFoot.get(1).addKeyframe(85, 0, 0, 30, 1);
		animsHumanRightFoot.get(1).addKeyframe(90, 0, 0, 10, 1);
		animsHumanRightFoot.get(1).addKeyframe(95, 0, 0, 0, 1);
		animsHumanRightFoot.get(1).addKeyframe(100, 0, 0, -10, 1);
		animsHumanRightFoot.get(2).addKeyframe(0, 0, 0, 30, 1);
		animsHumanRightFoot.get(2).addKeyframe(100, 0, 0, 30, 1);
		animsHumanRightFoot.get(3).addKeyframe(0, 0, 0, 30, 1);
		animsHumanRightFoot.get(3).addKeyframe(75, 0, 0, 30, 1);
		animsHumanRightFoot.get(3).addKeyframe(100, 0, 0, 30, 1);
		animsHumanRightFoot.get(4).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightFoot.get(4).addKeyframe(100, 0, 0, 30, 1);
		animsHumanRightFoot.get(5).addKeyframe(0, 0, 0, 0, 1);
		animsHumanRightFoot.get(5).addKeyframe(100, 0, 0, 30, 1);
		animsHumanRightFoot.get(6).addKeyframe(0, 0, 0, 45, 1);
		animsHumanRightFoot.get(6).addKeyframe(100, 0, 0, 0, 1);
		animsHumanRightFoot.get(7).addKeyframe(0, 0, 0, 70, 1);
		animsHumanRightFoot.get(7).addKeyframe(100, 0, 0, 70, 1);
		animsHumanRightFoot.get(8).addKeyframe(0, 0, 0, 90, 1);
		animsHumanRightFoot.get(8).addKeyframe(100, 0, 0, 120, 1);
		animsHumanRightFoot.get(9).addKeyframe(0, 0, 0, 60, 1);
		animsHumanRightFoot.get(9).addKeyframe(100, 0, 0, 60, 1);
		animsHumanRightFoot.get(10).addKeyframe(0, 0, 0, 90, 1);
		animsHumanRightFoot.get(10).addKeyframe(100, 0, 0, 80, 1);
		animsHumanRightFoot.get(11).addKeyframe(0, 0, 0, 120, 1);
		animsHumanRightFoot.get(11).addKeyframe(100, 0, 0, 120, 1);
		animsHumanRightFoot.get(12).addKeyframe(0, 0, 0, 150, 1);
		animsHumanRightFoot.get(12).addKeyframe(100, 0, 0, 150, 1);
		animsHumanRightFoot.get(13).addKeyframe(0, 0, 0, 30, 1);
		animsHumanRightFoot.get(13).addKeyframe(100, 0, 0, 30, 1);
		animsHumanRightFoot.get(14).addKeyframe(0, 0, 0, 30, 1);
		animsHumanRightFoot.get(14).addKeyframe(100, 0, 0, 30, 1);
		animsHumanRightFoot.get(15).addKeyframe(0, 0, 0, 30, 1);
		animsHumanRightFoot.get(15).addKeyframe(100, 0, 0, 30, 1);
		animsHumanRightFoot.get(16).addKeyframe(0, 0, 0, 30, 1);
		animsHumanRightFoot.get(16).addKeyframe(100, 0, 0, 30, 1);
		animsHumanRightFoot.get(17).addKeyframe(0, 0, 0, 90, 1);
		animsHumanRightFoot.get(17).addKeyframe(100, 0, 0, 90, 1);
		
		
		//Goomba animations
		animCount = 1;
		animsGoombaBody = new ArrayList<Animation>();
			while(animsGoombaBody.size() < animCount) animsGoombaBody.add(new Animation());
		animsGoombaLeftFoot = new ArrayList<Animation>();
			while(animsGoombaLeftFoot.size() < animCount) animsGoombaLeftFoot.add(new Animation());
		animsGoombaRightFoot = new ArrayList<Animation>();
			while(animsGoombaRightFoot.size() < animCount) animsGoombaRightFoot.add(new Animation());
		
		animsGoombaBody.get(0).addKeyframe(0, 0, -0.5f, 0, -5, 13, 0, 1);
		animsGoombaBody.get(0).addKeyframe(50, 0, -0.5f, 0, 5, -13, 0, 1);
		animsGoombaBody.get(0).addKeyframe(100, 0, -0.5f, 0, -5, 13, 0, 1);
		
		animsGoombaRightFoot.get(0).addKeyframe(0, 0, -32, -20, 1);
		animsGoombaRightFoot.get(0).addKeyframe(50, 0, -32, 20, 1);
		animsGoombaRightFoot.get(0).addKeyframe(100, 0, -32, -20, 1);
		
		animsGoombaLeftFoot.get(0).addKeyframe(0, 0, 32, 20, 1);
		animsGoombaLeftFoot.get(0).addKeyframe(50, 0, 32, -20, 1);
		animsGoombaLeftFoot.get(0).addKeyframe(100, 0, 32, 20, 1);
		
		
		//BobOmb animations
		animCount = 1;
		animsBobOmbBody = new ArrayList<Animation>();
			while(animsBobOmbBody.size() < animCount) animsBobOmbBody.add(new Animation());
		animsBobOmbLeftFoot = new ArrayList<Animation>();
			while(animsBobOmbLeftFoot.size() < animCount) animsBobOmbLeftFoot.add(new Animation());
		animsBobOmbRightFoot = new ArrayList<Animation>();
			while(animsBobOmbRightFoot.size() < animCount) animsBobOmbRightFoot.add(new Animation());
		
		animsBobOmbBody.get(0).addKeyframe(0, 0, 3.7f*1.3f, 0, -5, 0, 0, 1);
		animsBobOmbBody.get(0).addKeyframe(50, 0, 3.7f*1.3f, 0, 5, 0, 0, 1);
		animsBobOmbBody.get(0).addKeyframe(100, 0, 3.7f*1.3f, 0, -5, 0, 0, 1);
		
		animsBobOmbLeftFoot.get(0).addKeyframe(0, 0, -23, -20, 1);
		animsBobOmbLeftFoot.get(0).addKeyframe(50, 0, -23, 40, 1);
		animsBobOmbLeftFoot.get(0).addKeyframe(100, 0, -23, -20, 1);
		
		animsBobOmbRightFoot.get(0).addKeyframe(0, 0, 23, 40, 1);
		animsBobOmbRightFoot.get(0).addKeyframe(50, 0, 23, -20, 1);
		animsBobOmbRightFoot.get(0).addKeyframe(100, 0, 23, 40, 1);
	}
	
	public static void assignAnimationsHuman(Body body, Limb head, 
			Limb leftHumerus, Limb leftForearm, Limb leftHand, 
			Limb rightHumerus, Limb rightForearm, Limb rightHand, 
			Limb leftThigh, Limb leftShin, Limb leftFoot, 
			Limb rightThigh, Limb rightShin, Limb rightFoot)
	{
		body.animations = AnimationResources.animsHumanBody;
		head.animations = AnimationResources.animsHumanHead;
		leftHumerus.animations = AnimationResources.animsHumanLeftHumerus;
		leftForearm.animations = AnimationResources.animsHumanLeftForearm;
		leftHand.animations = AnimationResources.animsHumanLeftHand;
		rightHumerus.animations = AnimationResources.animsHumanRightHumerus;
		rightForearm.animations = AnimationResources.animsHumanRightForearm;
		rightHand.animations = AnimationResources.animsHumanRightHand;
		leftThigh.animations = AnimationResources.animsHumanLeftThigh;
		leftShin.animations = AnimationResources.animsHumanLeftShin;
		leftFoot.animations = AnimationResources.animsHumanLeftFoot;
		rightThigh.animations = AnimationResources.animsHumanRightThigh;
		rightShin.animations = AnimationResources.animsHumanRightShin;
		rightFoot.animations = AnimationResources.animsHumanRightFoot;
	}
	
	public static void assignAnimationsGoomba(Body body, Limb leftFoot, Limb rightFoot)
	{
		body.animations = AnimationResources.animsGoombaBody;
		leftFoot.animations = AnimationResources.animsGoombaLeftFoot;
		rightFoot.animations = AnimationResources.animsGoombaRightFoot;
	}
	
	public static void assignAnimationsBobOmb(Body body, Limb leftFoot, Limb rightFoot)
	{
		body.animations = AnimationResources.animsBobOmbBody;
		leftFoot.animations = AnimationResources.animsBobOmbLeftFoot;
		rightFoot.animations = AnimationResources.animsBobOmbRightFoot;
	}
}
