package collision;


import java.util.Iterator;
import java.util.LinkedList;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;

public class CollisionChecker 
{
	//private static CollisionModel collideModel;
	private static Vector3f collidePosition;
	private static Triangle3D collideTriangle;
	private static LinkedList<CollisionModel> collideModels;
	public static boolean debug = false;
	private static boolean checkPlayer = false;
	
	public static void initChecker()
	{
		//collideModel = model;
		collidePosition = new Vector3f(0, 0, 0);
		collideTriangle = null;
		collideModels = new LinkedList<CollisionModel>();
	}
	
	/**
	 *  Makes the next collision check set which collision
	 *  model the player has collided with, and sets that model
	 *  to touching the player.
	 */
	public static void checkPlayer()
	{
		checkPlayer = true;
	}
	
	/**
	 *  Sets all collision models to not have the player on them
	 */
	public static void falseAlarm()
	{
		for (CollisionModel cm : collideModels)
		{
			cm.playerIsOn = false;
		}
	}
	
	public static boolean checkCollision(Vector3f startPoint, Vector3f endPoint)
	{
		return checkCollision(startPoint.x, startPoint.y, startPoint.z, endPoint.x, endPoint.y, endPoint.z);
	}
	
	//checks for a collision
	//sets the field "collidePosition" to the coordinates of the collision
	
	//takes in a starting position and an ending position
	
	//returns whether or not there was a collision (true if there was)
	public static boolean checkCollision(float px1, float py1, float pz1, float px2, float py2, float pz2)
	{
		
		//var tridex,px1,py1,pz1,px2,py2,pz2,firstabove,secondabove,trianglecollide,
		//i,tx1,ty1,tz1,tx2,ty2,tz2,tx3,ty3,tz3,A,B,C,D,cix,ciy,ciz,numerator,
		//denominator,u,normalx,normaly,normalz,checktriangle;
		//p1Collide.set(startPoint);
		//p2Collide.set(endPoint);
		//int triIndex;
		//float px1 = startPoint.x, py1 = startPoint.y, pz1 = startPoint.z;
		//float px2 = endPoint.x, py2 = endPoint.y, pz2 = endPoint.z;
		boolean triangleCollide = false;
		//collideModel.playerIsOn = false;
		CollisionModel finalModel = null;
		double firstAbove;
		double secondAbove;
		//Triangle3D testTri = null;
		double tx1 = 0, tx2 = 0, tx3 = 0, ty1 = 0, ty2 = 0, ty3 = 0, tz1 = 0, tz2 = 0, tz3 = 0;
		//float tx1, tx2, tx3, ty1, ty2, ty3, tz1, tz2, tz3;
		double A, B, C, D;
		//Point3D collisionPoint = null;
		double numerator, denominator, u;
		
		
		//boolean checkTriangle;
		firstAbove = -2;
		secondAbove = -2;
		double minDist = -1;
		
		double checkRadius = 8+Math.sqrt(Math.pow(px2-px1, 2)+Math.pow(py2-py1, 2)+Math.pow(pz2-pz1, 2));
		
		
		//LinkedList<Triangle3D> trianglesToCheck = collideModel.triangles;
		//int countX = 0;
		//int countY = 0;
		//int countZ = 0;
		/*
		Iterator<Triangle3D> it = collideModels.triangles.iterator();
		while (it.hasNext())
		{
			Triangle3D currTriangle = it.next();
		    boolean checkTriangle = false;
			
		    if (px1-checkRadius <= currTriangle.maxX && px1+checkRadius >= currTriangle.minX)
		    {
		    	if (pz1-checkRadius <= currTriangle.maxZ && pz1+checkRadius >= currTriangle.minZ)
		    	{
		    		checkTriangle = (py1-checkRadius <= currTriangle.maxY && py1+checkRadius >= currTriangle.minY);
		    	}
		    }
		    
		    /*
		    if (py1-checkRadius <= currTriangle.maxY && py1+checkRadius >= currTriangle.minY)
		    {
		    	checkTriangle = false;
		    	countY++;
		    }
		    
		    if (px1-checkRadius <= currTriangle.maxX && px1+checkRadius >= currTriangle.minX)
	        {
		    	checkTriangle = false;
		    	countX++;
	        }
		    
		    if (pz1-checkRadius <= currTriangle.maxZ && pz1+checkRadius >= currTriangle.minZ)
	        {
			    checkTriangle = false;
			    countZ++;
	        }
	        */
		    /*
		    if (checkTriangle)
		    {
		    	tx1 = currTriangle.p1X;
			    tx2 = currTriangle.p2X;
			    tx3 = currTriangle.p3X;
			    ty1 = currTriangle.p1Y;
			    ty2 = currTriangle.p2Y;
			    ty3 = currTriangle.p3Y;
			    tz1 = currTriangle.p1Z;
			    tz2 = currTriangle.p2Z;
			    tz3 = currTriangle.p3Z;
		    	A = currTriangle.A;
		    	B = currTriangle.B;
		    	C = currTriangle.C;
		    	D = currTriangle.D;
		    	//tryPrintln("A = "+A);
		    	//tryPrintln("B = "+B);
		    	//tryPrintln("C = "+C);
		    	//tryPrintln("D = "+D);
		        
		        numerator = (A*px1+B*py1+C*pz1+D);
		        denominator = (A*(px1-px2)+B*(py1-py2)+C*(pz1-pz2));
		        
		        //tryPrintln("numerator = "+numerator);
		        //tryPrintln("denominator = "+denominator);
		        
		        if(denominator != 0)
		        {
		            u = (numerator/denominator);
		            //tryPrintln("u = "+u);
		            double cix = px1+u*(px2-px1);
		            double ciy = py1+u*(py2-py1);
		            double ciz = pz1+u*(pz2-pz1);
		            //tryPrintln("collision = "+cix+", "+ciy+", "+ciz);
		            
		            if(B != 0)
		            {
		            	double planey1 = (((-A*px1) + (-C*pz1)-D)/B);
		            	double planey2 = (((-A*px2) + (-C*pz2)-D)/B);
		                firstAbove = Math.signum(py1-planey1);
		                secondAbove = Math.signum(py2-planey2);
		            }
		            else if(A != 0)
		            {
		            	double planex1 = (((-B*py1) + (-C*pz1)-D)/A);
		            	double planex2 = (((-B*py2) + (-C*pz2)-D)/A);
		                firstAbove = Math.signum(px1-planex1);
		                secondAbove = Math.signum(px2-planex2);
		            }
		            else if(C != 0)
		            {
		            	double planez1 = (((-B*py1) + (-A*px1)-D)/C);
		            	double planez2 = (((-B*py2) + (-A*px2)-D)/C);
		                firstAbove = Math.signum(pz1-planez1);
		                secondAbove = Math.signum(pz2-planez2);
		            }
		            
		            if(secondAbove != firstAbove && 
			        		checkPointInTriangle3D(cix,ciy,ciz,tx1,ty1,tz1,tx2,ty2,tz2,tx3,ty3,tz3, 
			        				Math.abs(currTriangle.getNormal().getX()), 
			        				Math.abs(currTriangle.getNormal().getY()), 
			        				Math.abs(currTriangle.getNormal().getZ())))
			        {
	                    //what is the distance to the triangle? set it to maxdist
	                	triangleCollide = true;
	                	double thisDist = (Math.sqrt(Math.abs((cix-px1)*(cix-px1)+(ciy-py1)*(ciy-py1)+(ciz-pz1)*(ciz-pz1))));
	                    if(minDist == -1 || thisDist < minDist)
	                    {
	                    	collideTriangle = currTriangle;
	                        collidePosition.x = (float)cix;
	                        collidePosition.y = (float)ciy;
	                        collidePosition.z = (float)ciz;
	                        minDist = thisDist;
	                        finalModel = collideModel;
	                    }
			        }
		        }
		    }
		}
		*/
		
		for (CollisionModel cm : collideModels)
		{
			if (checkPlayer)
			{
				cm.playerIsOn = false;
			}
			
			//Bounds check on entire model
			if (px1-checkRadius <= cm.maxX && px1+checkRadius >= cm.minX &&
				pz1-checkRadius <= cm.maxZ && pz1+checkRadius >= cm.minZ &&
				py1-checkRadius <= cm.maxY && py1+checkRadius >= cm.minY)
			{
				Iterator<Triangle3D> it2 = cm.triangles.iterator();
				while (it2.hasNext())
				{
					Triangle3D currTriangle = it2.next();
					
					//Bounds check on individual triangle
				    if (px1-checkRadius <= currTriangle.maxX && px1+checkRadius >= currTriangle.minX &&
				    	pz1-checkRadius <= currTriangle.maxZ && pz1+checkRadius >= currTriangle.minZ &&
				    	py1-checkRadius <= currTriangle.maxY && py1+checkRadius >= currTriangle.minY)
				    {
				    	tx1 = currTriangle.p1X;
					    tx2 = currTriangle.p2X;
					    tx3 = currTriangle.p3X;
					    ty1 = currTriangle.p1Y;
					    ty2 = currTriangle.p2Y;
					    ty3 = currTriangle.p3Y;
					    tz1 = currTriangle.p1Z;
					    tz2 = currTriangle.p2Z;
					    tz3 = currTriangle.p3Z;
				    	A = currTriangle.A;
				    	B = currTriangle.B;
				    	C = currTriangle.C;
				    	D = currTriangle.D;
				        
				        numerator = (A*px1+B*py1+C*pz1+D);
				        denominator = (A*(px1-px2)+B*(py1-py2)+C*(pz1-pz2));
				        
				        if(denominator != 0)
				        {
				            u = (numerator/denominator);
				            double cix = px1+u*(px2-px1);
				            double ciy = py1+u*(py2-py1);
				            double ciz = pz1+u*(pz2-pz1);
				            
				            if(B != 0)
				            {
				            	double planey1 = (((-A*px1) + (-C*pz1)-D)/B);
				            	double planey2 = (((-A*px2) + (-C*pz2)-D)/B);
				                firstAbove = Math.signum(py1-planey1);
				                secondAbove = Math.signum(py2-planey2);
				            }
				            else if(A != 0)
				            {
				            	double planex1 = (((-B*py1) + (-C*pz1)-D)/A);
				            	double planex2 = (((-B*py2) + (-C*pz2)-D)/A);
				                firstAbove = Math.signum(px1-planex1);
				                secondAbove = Math.signum(px2-planex2);
				            }
				            else if(C != 0)
				            {
				            	double planez1 = (((-B*py1) + (-A*px1)-D)/C);
				            	double planez2 = (((-B*py2) + (-A*px2)-D)/C);
				                firstAbove = Math.signum(pz1-planez1);
				                secondAbove = Math.signum(pz2-planez2);
				            }
				            
				            if(secondAbove != firstAbove && 
					        		checkPointInTriangle3D(cix,ciy,ciz,tx1,ty1,tz1,tx2,ty2,tz2,tx3,ty3,tz3, 
					        				Math.abs(currTriangle.getNormal().getX()), 
					        				Math.abs(currTriangle.getNormal().getY()), 
					        				Math.abs(currTriangle.getNormal().getZ())))
					        {
			                    //what is the distance to the triangle? set it to maxdist
			                	triangleCollide = true;
			                	double thisDist = (Math.sqrt(Math.abs((cix-px1)*(cix-px1)+(ciy-py1)*(ciy-py1)+(ciz-pz1)*(ciz-pz1))));
			                    if(minDist == -1 || thisDist < minDist)
			                    {
			                    	collideTriangle = currTriangle;
			                        collidePosition.x = (float)cix;
			                        collidePosition.y = (float)ciy;
			                        collidePosition.z = (float)ciz;
			                        minDist = thisDist;
			                        finalModel = cm;
			                    }
					        }
				        }
				    }
				}
			}
		}

		if (checkPlayer && finalModel != null)
		{
			finalModel.playerIsOn = true;
		}
		checkPlayer = false;
		
		return triangleCollide;
	}
	
	
	
	
	
	public static boolean checkPointInTriangle3D(
			double checkx, double checky, double checkz, 
			double cx1, double cy1, double cz1,
			double cx2, double cy2, double cz2,
			double cx3, double cy3, double cz3, 
			double nX, double nY, double nZ)
	{
		//this checks the point from all sides
		/*
		if(Math.abs(nY) > 0.33)
		{
			//from the top
			return (checkPointInTriangle2D(checkx, checkz, cx1, cz1, cx2, cz2, cx3, cz3));
		}
		else if(Math.abs(nX) > 0.33)
		{
			//from the left
			return (checkPointInTriangle2D(checkz, checky, cz1, cy1, cz2, cy2, cz3, cy3));
		}

		//from the front
		return (checkPointInTriangle2D(checkx, checky, cx1, cy1, cx2, cy2, cx3, cy3));
		*/
		
		if(nY > nX && nY > nZ)
		{
			//from the top
			return (checkPointInTriangle2D(checkx, checkz, cx1, cz1, cx2, cz2, cx3, cz3));
		}
		else if(nX > nZ)
		{
			//from the left
			return (checkPointInTriangle2D(checkz, checky, cz1, cy1, cz2, cy2, cz3, cy3));
		}

		//from the front
		return (checkPointInTriangle2D(checkx, checky, cx1, cy1, cx2, cy2, cx3, cy3));
		
		//return (checkPointInTriangle2D(checkx, checky, cx1, cy1, cx2, cy2, cx3, cy3) &&
		//		checkPointInTriangle2D(checkx, checkz, cx1, cz1, cx2, cz2, cx3, cz3) &&
		//		checkPointInTriangle2D(checkz, checky, cz1, cy1, cz2, cy2, cz3, cy3));
		
		//this checks top down and side view
		//return (checkPointInTriangle2D(checkx, checkz, cx1, cz1, cx2, cz2, cx3, cz3) &&
		//		checkPointInTriangle2D(checkz, checky, cz1, cy1, cz2, cy2, cz3, cy3));

		//this just checks the point from the top down view
		
		//return (checkPointInTriangle2D(checkx, checkz, cx1, cz1, cx2, cz2, cx3, cz3));
	}
	
	public static boolean checkPointInTriangle2D(double x, double y, double x1, double y1, double x2, double y2, double x3, double y3)
	{
		double denominator = ((y2-y3)*(x1-x3)+(x3-x2)*(y1-y3));
		double a = ((y2-y3)*(x-x3)+(x3-x2)*(y-y3))/denominator;
		double b = ((y3-y1)*(x-x3)+(x1-x3)*(y-y3))/denominator;
		double c = 1-a-b;
		
		return (0 <= a && a <= 1 && 0 <= b && b <= 1 && 0 <= c && c <= 1);
	}
	
	//public static CollisionModel getCollideModel()
	//{
		//return collideModel;
	//}
	
	//public static void setCollideModel(CollisionModel newCollideModel)
	//{
		//collideModel = newCollideModel;
	//}
	
	public static void clearCollideModels()
	{
		collideModels = new LinkedList<CollisionModel>();
		//System.out.println("all gone "+collideModels.size());
	}
	
	public static void removeCollideModel(CollisionModel cm)
	{
		collideModels.remove(cm);
		//System.out.println("bye "+collideModels.size());
	}
	
	public static void addCollideModel(CollisionModel cm)
	{
		collideModels.add(cm);
		//System.out.println("adding col model "+collideModels.size());
	}
	
	/*
	 * based off of the last collision check
	 */
	public static Triangle3D getCollideTriangle()
	{
		return collideTriangle;
	}
	
	public static Vector3f getCollidePosition() 
	{
		return collidePosition;
	}
	
	public static void tryPrintln(String line)
	{
		if(debug)
		{
			System.out.println(line);
		}
	}
}
