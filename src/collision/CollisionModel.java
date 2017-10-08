package collision;

import java.util.LinkedList;

import org.lwjgl.util.vector.Vector3f;


public class CollisionModel 
{
	//public final int numChunks = 2;
	//public Chunk[][] chunks;
	public LinkedList<Triangle3D> triangles;
	public boolean playerIsOn;
	
	//min and max values of the entire model
	public float minX;
	public float maxX;
	public float minY;
	public float maxY;
	public float minZ;
	public float maxZ;
	
	public CollisionModel()
	{
		triangles = new LinkedList<Triangle3D>();
		playerIsOn = false;
		//chunks = new Chunk[numChunks][numChunks];
	}
	
	public void generateMinMaxValues()
	{
		if (triangles.get(0) == null) return;
		
		minX = triangles.get(0).minX;
		maxX = triangles.get(0).maxX;
		minY = triangles.get(0).minY;
		maxY = triangles.get(0).maxY;
		minZ = triangles.get(0).minZ;
		maxZ = triangles.get(0).maxZ;
		
		for (Triangle3D tri : triangles)
		{
			minX = Math.min(minX, tri.minX);
			maxX = Math.max(maxX, tri.maxX);
			minY = Math.min(minY, tri.minY);
			maxY = Math.max(maxY, tri.maxY);
			minZ = Math.min(minZ, tri.minZ);
			maxZ = Math.max(maxZ, tri.maxZ);
		}
	}
	
	public void offsetModel(Vector3f offset)
	{
		for (Triangle3D tri : triangles)
		{
			tri.p1X+=offset.x;
			tri.p2X+=offset.x;
			tri.p3X+=offset.x;
			tri.p1Y+=offset.y;
			tri.p2Y+=offset.y;
			tri.p3Y+=offset.y;
			tri.p1Z+=offset.z;
			tri.p2Z+=offset.z;
			tri.p3Z+=offset.z;
			
			tri.generateValues();
		}
		
		generateMinMaxValues();
	}
	
	public void rotateModelY(float yRot, Vector3f center)
	{
		//System.out.println("y rot = "+yRot);
		float angleRad = (float)Math.toRadians(yRot);
		float cosAng = (float)Math.cos(angleRad);
		float sinAng = (float)Math.sin(angleRad);
		for(Triangle3D tri : triangles)
		{
			//first calculate the normal before
			//some code
			
			//System.out.println("old x1 "+tri.p1X +"   old z1 "+tri.p1Z);
			float xDiff = tri.p1X-center.x;
			float zDiff = tri.p1Z-center.z;
			tri.p1X = center.x+((xDiff)*cosAng - (zDiff)*sinAng);
			tri.p1Z = center.z-(-(zDiff)*cosAng - (xDiff)*sinAng);
			//System.out.println("new x1 "+tri.p1X +"   new z1 "+tri.p1Z);
			
			//System.out.println("old x2 "+tri.p2X +"   old z2 "+tri.p2Z);
			xDiff = tri.p2X-center.x;
			zDiff = tri.p2Z-center.z;
			tri.p2X = center.x+((xDiff)*cosAng - (zDiff)*sinAng);
			tri.p2Z = center.z-(-(zDiff)*cosAng - (xDiff)*sinAng);
			//System.out.println("new x2 "+tri.p2X +"   new z2 "+tri.p2Z);
			
			//System.out.println("old x3 "+tri.p3X +"   old z3 "+tri.p3Z);
			xDiff = tri.p3X-center.x;
			zDiff = tri.p3Z-center.z;
			tri.p3X = center.x+((xDiff)*cosAng - (zDiff)*sinAng);
			tri.p3Z = center.z-(-(zDiff)*cosAng - (xDiff)*sinAng);
			//System.out.println("new x3 "+tri.p3X +"   new z3 "+tri.p3Z);
			
			//now calculate normal afterwards
			//same code as above but with new verticies
			
			//compare two normals, if they are different, switch the verticies orders
			//edit: actually no need to do that?
			tri.generateValues();
		}
		//System.out.println();
		generateMinMaxValues();
	}
	
	//makes a collision model be the transformed version of this collision model
	public void transformModel(CollisionModel targetModel, Vector3f translate, float yRot, float zRot)
	{
		double angleRad = Math.toRadians(yRot);
		double angleRadZ = Math.toRadians(zRot);
		//System.out.println("arad = "+angleRad);
		double cosAng = Math.cos(angleRad);
		double cosAngZ = Math.cos(angleRadZ);
		//System.out.println("cos = "+cosAng);
		double sinAng = Math.sin(angleRad);
		double sinAngZ = Math.sin(angleRadZ);
		//System.out.println("sin = "+sinAng);
		float offX = translate.x;
		float offY = translate.y;
		float offZ = translate.z;
		targetModel.triangles.clear();
		for(Triangle3D tri : triangles)
		{
			//first calculate the normal before
			//some code
			
			float xDiff = tri.p1X;
			float zDiff = tri.p1Z;
			float yDiff = tri.p1Y;
			float newX = (float)(xDiff*cosAngZ-yDiff*sinAngZ);
			float newY = (float)(yDiff*cosAngZ+xDiff*sinAngZ);
			Vector3f newP1 = new Vector3f(offX+(float)((newX)*cosAng - (zDiff)*sinAng), offY+newY, offZ-(float)(-(zDiff)*cosAng - (newX)*sinAng));
			//tri.p1X =  offX+((xDiff)*cosAng - (zDiff)*sinAng);
			//tri.p1Z =  offZ-((zDiff)*cosAng - (xDiff)*sinAng);
			//tri.p1Y =  offY+tri.p1Y;
			//System.out.println("old x1 "+tri.p1X +"   old y1 "+tri.p1Y+"   old z1 "+tri.p1Z);
			//System.out.println("new x1 "+newp1X +"   new y1 "+newp1Y+"   new z1 "+newp1Z);
			
			//System.out.println("old x2 "+tri.p2X +"   old z2 "+tri.p2Z);
			xDiff = tri.p2X;
			zDiff = tri.p2Z;
			yDiff = tri.p2Y;
			newX = (float)(xDiff*cosAngZ-yDiff*sinAngZ);
			newY = (float)(yDiff*cosAngZ+xDiff*sinAngZ);
			//tri.p2X =  offX+((xDiff)*cosAng - (zDiff)*sinAng);
			//tri.p2Z =  offZ-((zDiff)*cosAng - (xDiff)*sinAng);
			//tri.p2Y =  offY+tri.p2Y;
			Vector3f newP2 = new Vector3f(offX+(float)((newX)*cosAng - (zDiff)*sinAng), offY+newY, offZ-(float)(-(zDiff)*cosAng - (newX)*sinAng));
			//System.out.println("old x2 "+tri.p2X +"   old y2 "+tri.p2Y+"   old z2 "+tri.p2Z);
			//System.out.println("new x2 "+newp2X +"   new y2 "+newp2Y+"   new z2 "+newp2Z);
			
			//System.out.println("old x3 "+tri.p3X +"   old z3 "+tri.p3Z);
			xDiff = tri.p3X;
			zDiff = tri.p3Z;
			yDiff = tri.p3Y;
			newX = (float)(xDiff*cosAngZ-yDiff*sinAngZ);
			newY = (float)(yDiff*cosAngZ+xDiff*sinAngZ);
			//tri.p3X =  offX+((xDiff)*cosAng - (zDiff)*sinAng);
			//tri.p3Z =  offZ-((zDiff)*cosAng - (xDiff)*sinAng);
			//tri.p3Y =  offY+tri.p3Y;
			Vector3f newP3 = new Vector3f(offX+(float)((newX)*cosAng - (zDiff)*sinAng), offY+newY, offZ-(float)(-(zDiff)*cosAng - (newX)*sinAng));
			//System.out.println("old x3 "+tri.p3X +"   old y3 "+tri.p3Y+"   old z3 "+tri.p3Z);
			//System.out.println("new x3 "+newp3X +"   new y3 "+newp3Y+"   new z3 "+newp3Z);
			Triangle3D newTri = new Triangle3D(newP1, newP2, newP3, tri.type, tri.sound, tri.particle);
			//System.out.println("old normal y"+tri.normal.y);
			//System.out.println("new normal y"+newTri.normal.y);
			//System.out.println();
			targetModel.triangles.add(newTri);
			//now calculate normal afterwards
			//same code as above but with new verticies
			
			//compare two normals, if they are different, switch the verticies orders
			//edit: actually no need to do that?
			//tri.generateValues();
		}
		targetModel.generateMinMaxValues();
	}
	
	//makes a collision model be the transformed version of this collision model
	public void transformModel(CollisionModel targetModel, Vector3f translate, float yRot)
	{
		double angleRad = Math.toRadians(yRot);
		//System.out.println("arad = "+angleRad);
		double cosAng = Math.cos(angleRad);
		//System.out.println("cos = "+cosAng);
		double sinAng = Math.sin(angleRad);
		//System.out.println("sin = "+sinAng);
		float offX = translate.x;
		float offY = translate.y;
		float offZ = translate.z;
		targetModel.triangles.clear();
		for(Triangle3D tri : triangles)
		{
			//first calculate the normal before
			//some code
			
			float xDiff = tri.p1X;
			float zDiff = tri.p1Z;
			Vector3f newP1 = new Vector3f(offX+(float)((xDiff)*cosAng - (zDiff)*sinAng), offY+tri.p1Y, offZ-(float)(-(zDiff)*cosAng - (xDiff)*sinAng));
			//tri.p1X =  offX+((xDiff)*cosAng - (zDiff)*sinAng);
			//tri.p1Z =  offZ-((zDiff)*cosAng - (xDiff)*sinAng);
			//tri.p1Y =  offY+tri.p1Y;
			//System.out.println("old x1 "+tri.p1X +"   old y1 "+tri.p1Y+"   old z1 "+tri.p1Z);
			//System.out.println("new x1 "+newp1X +"   new y1 "+newp1Y+"   new z1 "+newp1Z);
			
			//System.out.println("old x2 "+tri.p2X +"   old z2 "+tri.p2Z);
			xDiff = tri.p2X;
			zDiff = tri.p2Z;
			//tri.p2X =  offX+((xDiff)*cosAng - (zDiff)*sinAng);
			//tri.p2Z =  offZ-((zDiff)*cosAng - (xDiff)*sinAng);
			//tri.p2Y =  offY+tri.p2Y;
			Vector3f newP2 = new Vector3f(offX+(float)((xDiff)*cosAng - (zDiff)*sinAng), offY+tri.p2Y, offZ-(float)(-(zDiff)*cosAng - (xDiff)*sinAng));
			//System.out.println("old x2 "+tri.p2X +"   old y2 "+tri.p2Y+"   old z2 "+tri.p2Z);
			//System.out.println("new x2 "+newp2X +"   new y2 "+newp2Y+"   new z2 "+newp2Z);
			
			//System.out.println("old x3 "+tri.p3X +"   old z3 "+tri.p3Z);
			xDiff = tri.p3X;
			zDiff = tri.p3Z;
			//tri.p3X =  offX+((xDiff)*cosAng - (zDiff)*sinAng);
			//tri.p3Z =  offZ-((zDiff)*cosAng - (xDiff)*sinAng);
			//tri.p3Y =  offY+tri.p3Y;
			Vector3f newP3 = new Vector3f(offX+(float)((xDiff)*cosAng - (zDiff)*sinAng), offY+tri.p3Y, offZ-(float)(-(zDiff)*cosAng - (xDiff)*sinAng));
			//System.out.println("old x3 "+tri.p3X +"   old y3 "+tri.p3Y+"   old z3 "+tri.p3Z);
			//System.out.println("new x3 "+newp3X +"   new y3 "+newp3Y+"   new z3 "+newp3Z);
			Triangle3D newTri = new Triangle3D(newP1, newP2, newP3, tri.type, tri.sound, tri.particle);
			//System.out.println("old normal y"+tri.normal.y);
			//System.out.println("new normal y"+newTri.normal.y);
			//System.out.println();
			targetModel.triangles.add(newTri);
			//now calculate normal afterwards
			//same code as above but with new verticies
			
			//compare two normals, if they are different, switch the verticies orders
			//edit: actually no need to do that?
			//tri.generateValues();
		}
		targetModel.generateMinMaxValues();
	}
	
	//makes a collision model be the transformed version of this collision model
	public void transformModel(CollisionModel targetModel, Vector3f translate)
	{
		float offX = translate.x;
		float offY = translate.y;
		float offZ = translate.z;
		targetModel.triangles.clear();
		for(Triangle3D tri : triangles)
		{
			//first calculate the normal before
			//some code

			Vector3f newP1 = new Vector3f(offX+tri.p1X, offY+tri.p1Y, offZ+tri.p1Z);
			//tri.p1X =  offX+((xDiff)*cosAng - (zDiff)*sinAng);
			//tri.p1Z =  offZ-((zDiff)*cosAng - (xDiff)*sinAng);
			//tri.p1Y =  offY+tri.p1Y;
			//System.out.println("old x1 "+tri.p1X +"   old y1 "+tri.p1Y+"   old z1 "+tri.p1Z);
			//System.out.println("new x1 "+newp1X +"   new y1 "+newp1Y+"   new z1 "+newp1Z);
			
			//System.out.println("old x2 "+tri.p2X +"   old z2 "+tri.p2Z);

			//tri.p2X =  offX+((xDiff)*cosAng - (zDiff)*sinAng);
			//tri.p2Z =  offZ-((zDiff)*cosAng - (xDiff)*sinAng);
			//tri.p2Y =  offY+tri.p2Y;
			Vector3f newP2 = new Vector3f(offX+tri.p2X, offY+tri.p2Y, offZ+tri.p2Z);
			//System.out.println("old x2 "+tri.p2X +"   old y2 "+tri.p2Y+"   old z2 "+tri.p2Z);
			//System.out.println("new x2 "+newp2X +"   new y2 "+newp2Y+"   new z2 "+newp2Z);
			
			//System.out.println("old x3 "+tri.p3X +"   old z3 "+tri.p3Z);
			//tri.p3X =  offX+((xDiff)*cosAng - (zDiff)*sinAng);
			//tri.p3Z =  offZ-((zDiff)*cosAng - (xDiff)*sinAng);
			//tri.p3Y =  offY+tri.p3Y;
			Vector3f newP3 = new Vector3f(offX+tri.p3X, offY+tri.p3Y, offZ+tri.p3Z);
			//System.out.println("old x3 "+tri.p3X +"   old y3 "+tri.p3Y+"   old z3 "+tri.p3Z);
			//System.out.println("new x3 "+newp3X +"   new y3 "+newp3Y+"   new z3 "+newp3Z);
			Triangle3D newTri = new Triangle3D(newP1, newP2, newP3, tri.type, tri.sound, tri.particle);
			//System.out.println("old normal y"+tri.normal.y);
			//System.out.println("new normal y"+newTri.normal.y);
			//System.out.println();
			targetModel.triangles.add(newTri);
			//now calculate normal afterwards
			//same code as above but with new verticies
			
			//compare two normals, if they are different, switch the verticies orders
			//edit: actually no need to do that?
			//tri.generateValues();
		}
		targetModel.generateMinMaxValues();
	}
	
	//public void setPosition(Vector3f offset)
	//{
		//position = offset;
	//}
	
	//public void generateChunks()
	//{
		/*
		Triangle3D tempTri = triangles.get(0);
		float totalXMin = Math.min(tempTri.p1X, Math.min(tempTri.p2X, tempTri.p3X));
		float totalXMax = Math.max(tempTri.p1X, Math.max(tempTri.p2X, tempTri.p3X));
		float totalZMin = Math.min(tempTri.p1Z, Math.min(tempTri.p2Z, tempTri.p3Z));
		float totalZMax = Math.max(tempTri.p1Z, Math.min(tempTri.p2Z, tempTri.p3Z));
		for(Triangle3D tri : triangles)
		{
			totalXMin = Math.min(totalXMin, Math.min(tri.p1X, Math.min(tri.p2X, tri.p3X)));
			totalXMax = Math.max(totalXMax, Math.max(tri.p1X, Math.max(tri.p2X, tri.p3X)));
			totalZMin = Math.min(totalZMin, Math.min(tri.p1Z, Math.min(tri.p2Z, tri.p3Z)));
			totalZMax = Math.max(totalZMax, Math.max(tri.p1Z, Math.min(tri.p2Z, tri.p3Z)));
		}
		
		float xDiff = totalXMax-totalXMin;
		float zDiff = totalZMax-totalZMin;
		for(int z = 0; z < numChunks; z++)
		{
			for(int x = 0; x < numChunks; x++)
			{
				chunks[z][x] = new Chunk();
				chunks[z][x].xMin = totalXMin+((x+0.0f)/numChunks)*xDiff;
				chunks[z][x].xMax = totalXMin+((x+1.0f)/numChunks)*xDiff;
				chunks[z][x].zMin = totalZMin+((z+0.0f)/numChunks)*zDiff;
				chunks[z][x].zMax = totalZMin+((z+1.0f)/numChunks)*zDiff;
			}
		}
		Chunk currChunk = chunks[0][0];
		
		while(triangles.size() > 0)
		{
			Triangle3D currTri = triangles.get(0);
			float triXMin = Math.min(currTri.p1X, Math.min(currTri.p2X, currTri.p3X));
			float triXMax = Math.max(currTri.p1X, Math.max(currTri.p2X, currTri.p3X));
			float triZMin = Math.min(currTri.p1Z, Math.min(currTri.p2Z, currTri.p3Z));
			float triZMax = Math.max(currTri.p1Z, Math.max(currTri.p2Z, currTri.p3Z));
			
			//Determine which chunks this triangle should be a part of
			for(int y = 0; y < numChunks; y++)
			{
				for(int x = 0; x < numChunks; x++)
				{
					//now i realize that some maps would add so many triangles to so many chunks
					 * that its not worth it. the only times where you would need to speed
					 * up the system is for large number of triangle maps. that also means 
					 * large amounts of memory being taken up. which means in order to speed
					 * up by a good amount, you need lots of chunks, which means even more memory.
					 * too much of a risk when slow downs start happening at 6GB of RAM.
					 *
					currChunk.tris.add(triangles.get(0));
					triangles.remove(0);
				}
			}
		}
		*/
	//}
	
	//public LinkedList<Triangle3D> getTrianglesToCheck(float x1, float x2, float z1, float z2)
	//{
		//do some stuff to trianglesToCheck
		//LinkedList<Triangle3D> trianglesToCheck = new LinkedList<Triangle3D>();
		//trianglesToCheck.addAll(chunks[0][0].tris);
		//return trianglesToCheck;
	//}
}
