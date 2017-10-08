package entities;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import models.TexturedModel;

public class Entity
{
	private TexturedModel[] models;
	private Vector3f position;
	private float rotX, rotY, rotZ;
	private float scale;
	private boolean visible;
	private Vector3f spawnPosition;
	private float spawnRotX, spawnRotY, spawnRotZ;
	private float spawnScale;
	
	public Entity(TexturedModel model, Vector3f position, float rotX,
			float rotY, float rotZ, float scale, Vector3f spawnPosition, float spawnRotX, float spawnRotY, float spawnRotZ, float spawnScale) 
	{
		this.models = new TexturedModel[]{model};
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
		this.visible = true;
		this.spawnPosition = spawnPosition;
		this.spawnRotX = spawnRotX;
		this.spawnRotY = spawnRotY;
		this.spawnRotZ = spawnRotZ;
		this.spawnScale = spawnScale;
	}
	
	public Entity(TexturedModel[] models, Vector3f position, float rotX, float rotY, float rotZ, float scale,
			Vector3f spawnPosition, float spawnRotX, float spawnRotY, float spawnRotZ, float spawnScale) 
	{
		this.models = models;
	    this.position = position;
	    this.rotX = rotX;
	    this.rotY = rotY;
	    this.rotZ = rotZ;
	    this.scale = scale;
	    this.visible = true;
	    this.spawnPosition = spawnPosition;
		this.spawnRotX = spawnRotX;
		this.spawnRotY = spawnRotY;
		this.spawnRotZ = spawnRotZ;
		this.spawnScale = spawnScale;
	}
	
	public Entity(TexturedModel model, Vector3f position, float rotX,
			float rotY, float rotZ, float scale) 
	{
		this.models = new TexturedModel[]{model};
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
		this.visible = true;
		this.spawnPosition = new Vector3f(position.getX(), position.getY(), position.getZ());
		this.spawnRotX = rotX;
		this.spawnRotY = rotY;
		this.spawnRotZ = rotZ;
		this.spawnScale = scale;
	}
	
	public Entity(TexturedModel[] models, Vector3f position, float rotX, float rotY, float rotZ, float scale) 
	{
		this.models = models;
	    this.position = position;
	    this.rotX = rotX;
	    this.rotY = rotY;
	    this.rotZ = rotZ;
	    this.scale = scale;
	    this.visible = true;
	    this.spawnPosition = new Vector3f(position.getX(), position.getY(), position.getZ());
	    this.spawnRotX = rotX;
		this.spawnRotY = rotY;
		this.spawnRotZ = rotZ;
		this.spawnScale = scale;
	}
	 
	public Entity(Vector3f position, float rotX, float rotY, float rotZ, float scale) 
	{
		this.models = null;
	    this.position = position;
	    this.rotX = rotX;
	    this.rotY = rotY;
	    this.rotZ = rotZ;
	    this.scale = scale;
	    this.visible = false;
	    this.spawnPosition = new Vector3f(position.getX(), position.getY(), position.getZ());
	    this.spawnRotX = rotX;
		this.spawnRotY = rotY;
		this.spawnRotZ = rotZ;
		this.spawnScale = scale;
	}
	
	public Entity(TexturedModel[] model, Vector3f position) 
	{
		this.models = model;
	    this.position = position;
	    this.rotX = 0;
	    this.rotY = 0;
	    this.rotZ = 0;
	    this.scale = 1;
	    this.visible = false;
	    this.spawnPosition = new Vector3f(position.getX(), position.getY(), position.getZ());
	    this.spawnRotX = rotX;
		this.spawnRotY = rotY;
		this.spawnRotZ = rotZ;
		this.spawnScale = scale;
	}
	
	public Entity(Vector3f position) 
	{
		this.models = null;
	    this.position = position;
	    this.rotX = 0;
	    this.rotY = 0;
	    this.rotZ = 0;
	    this.scale = 1;
	    this.visible = false;
	    this.spawnPosition = new Vector3f(position.getX(), position.getY(), position.getZ());
	    this.spawnRotX = rotX;
		this.spawnRotY = rotY;
		this.spawnRotZ = rotZ;
		this.spawnScale = scale;
	}
	
	public TexturedModel getModel(int index) 
	{
	    return models[index];
	}
	
	public TexturedModel[] getModels() 
	{
	    return models;
	}
	
	public void setModel(TexturedModel model, int index) 
	{
	    this.models[index] = model;
	}
	
	public void setModels(TexturedModel[] tm)
	{
		//if (models != null)
		{
			//MainGameLoop.gameLoader.deleteModel(models);
			//models = null;
		}
		
		this.models = tm;
	}
	
	//this could also probably be made better?
	public void addModel(TexturedModel model)
	{ 
	    TexturedModel[] temp = new TexturedModel[models.length];
	    for(int i = 0; i < models.length; i++)
	    {
	    	temp[i] = this.models[i];
	    }
	    this.models = java.util.Arrays.copyOfRange(temp, 0, temp.length);
	    models[models.length-1] = model;
	}
	
	public int numberOfModels()
	{
		return this.models.length;
	}
	
	public void step()
	{
		
	}
	
	public void respawn()
	{
		this.position.set(spawnPosition.getX(), spawnPosition.getY(), spawnPosition.getZ());
		this.scale = spawnScale;
		this.rotX = spawnRotX;
		this.rotY = spawnRotY;
		this.rotZ = spawnRotZ;
		visible = true;
		if (models == null)
		{
			visible = false;
		}
	}

	public void increasePosition(float dx, float dy, float dz)
	{
		this.position.x+=dx;
		this.position.y+=dy;
		this.position.z+=dz;
	}
	
	public void increaseRotation(float dx, float dy, float dz)
	{
		this.rotX+=dx;
		this.rotY+=dy;
		this.rotZ+=dz;
	}

	public Vector3f getPosition() 
	{
		return position;
	}
	
	public Vector3f getSpawnPosition()
	{
		return spawnPosition;
	}

	public void setPosition(Vector3f position) 
	{
		this.position.set(position);
	}
	
	public void setX(float newX)
	{
		this.position.setX(newX);
	}
	
	public float getX()
	{
		return this.position.getX();
	}
	
	public void setY(float newY)
	{
		this.position.setY(newY);
	}
	
	public float getY()
	{
		return this.position.getY();
	}
	
	public void setZ(float newZ)
	{
		this.position.setZ(newZ);
	}
	
	public float getZ()
	{
		return this.position.getZ();
	}

	public float getRotX() 
	{
		return rotX;
	}

	public void setRotX(float rotX) 
	{
		this.rotX = rotX;
	}

	public float getRotY() 
	{
		return rotY;
	}

	public void setRotY(float rotY) 
	{
		this.rotY = rotY;
	}

	public float getRotZ()
	{
		return rotZ;
	}

	public void setRotZ(float rotZ) 
	{
		this.rotZ = rotZ;
	}

	public float getScale() 
	{
		return scale;
	}

	public void setScale(float d) 
	{
		this.scale = d;
	}
	
	public boolean getVisibility()
	{
		return visible;
	}
	
	public void setVisibility(boolean visible)
	{
		this.visible = visible;
	}

	public void setSpawnPosition(Vector3f spawnPosition) 
	{
		this.spawnPosition.set(spawnPosition.getX(), spawnPosition.getY(), spawnPosition.getZ());
	}
	
	//sets the spawn position to the entities current position
	public void setSpawnPosition() 
	{
		this.spawnPosition.set(this.getX(), this.getY(), this.getZ());
	}

	public void setSpawnRotX(float spawnRotX) 
	{
		this.spawnRotX = spawnRotX;
	}

	public void setSpawnRotY(float spawnRotY) 
	{
		this.spawnRotY = spawnRotY;
	}

	public void setSpawnRotZ(float spawnRotZ) 
	{
		this.spawnRotZ = spawnRotZ;
	}

	public void setSpawnScale(float spawnScale) 
	{
		this.spawnScale = spawnScale;
	}
	
	public void despawn()
	{
		
	}
}
