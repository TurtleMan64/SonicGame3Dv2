package models;

import engineTester.MainGameLoop;

public class RawModel 
{
	private int vaoID;
	private int vertexCount;
	private int[] vboIDs;
	
	public RawModel(int vaoID, int vertexCount, int[] vboIDs)
	{
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		this.vboIDs = vboIDs;
	}
	
	public int getVaoID()
	{
		return vaoID;
	}
	
	public int getVertexCount()
	{
		return vertexCount;
	}
	
	/** Deletes this vao and vbos from OpenGL
	 * 
	 */
	public void delete()
	{
		MainGameLoop.gameLoader.deleteVAO(vaoID);
		for (int vbo: vboIDs)
		{
			MainGameLoop.gameLoader.deleteVBO(vbo);
		}
		vboIDs = null;
	}
}
