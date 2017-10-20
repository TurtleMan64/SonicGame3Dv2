package collision;

import org.lwjgl.util.vector.Vector3f;


public class Triangle3D 
{
	//public Vector3f p1;
	public float p1X;
	public float p1Y;
	public float p1Z;
	//public Vector3f p2;
	public float p2X;
	public float p2Y;
	public float p2Z;
	//public Vector3f p3;
	public float p3X;
	public float p3Y;
	public float p3Z;
	public Vector3f normal;
	public double A;
	public double B;
	public double C;
	public double D;
	public byte type;
		//0 = normal
		//1 = slippery
	public int sound;
		//0 = floor step
	    //1 = grass step
		//2 = snow step
		//4 = wet wood
	public byte particle;
		//0 = no particles
		//1 = snowballs
	
	public float maxX;
	public float minX;
	public float maxY;
	public float minY;
	public float maxZ;
	public float minZ;
	
	public Triangle3D(Vector3f newP1, Vector3f newP2, Vector3f newP3, byte type, int sound, byte particle)
	{
		//this.p1 = newP1;
		this.p1X = newP1.x;
		this.p1Y = newP1.y;
		this.p1Z = newP1.z;
		//this.p2 = newP2;
		this.p2X = newP2.x;
		this.p2Y = newP2.y;
		this.p2Z = newP2.z;
		//this.p3 = newP3;
		this.p3X = newP3.x;
		this.p3Y = newP3.y;
		this.p3Z = newP3.z;
		this.type = type;
		this.sound = sound;
		this.particle = particle;
		generateValues();
	}
	
	public void generateValues()
	{
		//A = p1.y*(p2.z - p3.z) + p2.y*(p3.z - p1.z) + p3.y*(p1.z - p2.z);
	    //B = p1.z*(p2.x - p3.x) + p2.z*(p3.x - p1.x) + p3.z*(p1.x - p2.x);
	    //C = p1.x*(p2.y - p3.y) + p2.x*(p3.y - p1.y) + p3.x*(p1.y - p2.y);
	    //D = -1*(p1.x*(p2.y*p3.z - p3.y*p2.z) + p2.x*(p3.y*p1.z - p1.y*p3.z) + p3.x*(p1.y*p2.z - p2.y*p1.z));
	    
	    
	    
	    Vector3f vec1 = new Vector3f(p1X-p3X, p1Y-p3Y, p1Z-p3Z);
	    Vector3f vec2 = new Vector3f(p2X-p3X, p2Y-p3Y, p2Z-p3Z);
	    
	    Vector3f cross = Vector3f.cross(vec1, vec2, null);
	    
	    
	    double newD = cross.x*p3X + cross.y*p3Y + cross.z*p3Z;
	    
	    /*
	    System.out.println("orig A = "+A);
	    System.out.println("new  A = "+cross.x);
	    System.out.println("");
	    System.out.println("orig B = "+B);
	    System.out.println("new  B = "+cross.y);
	    System.out.println("");
	    System.out.println("orig C = "+C);
	    System.out.println("new  C = "+cross.z);
	    System.out.println("");
	    System.out.println("orig D = "+D);
	    System.out.println("new  D = "+newD);
	    System.out.println("");
	    */
	    
	    A = cross.x;
	    B = cross.y;
	    C = cross.z;
	    D = -newD;
	    
	    double mag = (Math.sqrt((A*A)+(B*B)+(C*C)));
	    
	    normal = null;
	    if (mag!= 0)
	    {
	    	normal = new Vector3f((float)(A/mag), (float)(B/mag), (float)(C/mag));
	    }
	    else
	    {
	    	normal = new Vector3f(0,1,0);
	    }
	    
	    maxX = Math.max(p1X, Math.max(p2X, p3X));
	    minX = Math.min(p1X, Math.min(p2X, p3X));
	    maxY = Math.max(p1Y, Math.max(p2Y, p3Y));
	    minY = Math.min(p1Y, Math.min(p2Y, p3Y));
	    maxZ = Math.max(p1Z, Math.max(p2Z, p3Z));
	    minZ = Math.min(p1Z, Math.min(p2Z, p3Z));
	}
	
	public double getA()
	{
		return A;
	}
	
	public double getB()
	{
		return B;
	}
	
	public double getC()
	{
		return C;
	}
	
	public double getD()
	{
		return D;
	}
	
	public Vector3f getNormal()
	{
		return normal;
	}
	
	public boolean isSlippery()
	{
		return (type == 1);
	}
	
	public int getStepSound()
	{
		return sound;
	}
	
	public byte getParticle()
	{
		return particle;
	}
}
