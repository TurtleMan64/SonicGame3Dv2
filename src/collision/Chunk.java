package collision;

import java.util.LinkedList;

public class Chunk 
{
	LinkedList<Triangle3D> tris;
	public float xMin;
	public float xMax;
	public float zMin;
	public float zMax;
	
	public Chunk()
	{
		tris = new LinkedList<Triangle3D>();
	}
}
