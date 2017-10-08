package entities;

import java.util.LinkedList;

public class EntityGroup
{
	public LinkedList<Entity> entities;
	
	public float minX;
	public float maxX;
	public float minY;
	public float maxY;
	public float minZ;
	public float maxZ;
	
	public EntityGroup()
	{
		entities = new LinkedList<Entity>();
	}
	
	public void generateMinMaxValues()
	{
		if (entities.get(0) == null) return;
		
		minX = entities.get(0).getX();
		maxX = entities.get(0).getX();
		minY = entities.get(0).getY();
		maxY = entities.get(0).getY();
		minZ = entities.get(0).getZ();
		maxZ = entities.get(0).getZ();
		
		for (Entity ent : entities)
		{
			minX = Math.min(minX, ent.getX());
			maxX = Math.max(maxX, ent.getX());
			minY = Math.min(minY, ent.getY());
			maxY = Math.max(maxY, ent.getY());
			minZ = Math.min(minZ, ent.getZ());
			maxZ = Math.max(maxZ, ent.getZ());
		}
	}
}
