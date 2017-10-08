package models;

import textures.ModelTexture;

public class TexturedModel 
{
	private RawModel rawModel;
	private ModelTexture texture;
	
	public TexturedModel(RawModel model, ModelTexture texture)
	{
		this.rawModel = model;
		this.texture = texture;
		
	}

	public RawModel getRawModel() 
	{
		return rawModel;
	}

	public ModelTexture getTexture() 
	{
		return texture;
	}
	
	/** Deletes the model and textures from OpenGL
	 * 
	 */
	public void delete()
	{
		if (rawModel != null)
		{
			rawModel.delete();
		}
		
		if (texture != null)
		{
			texture.delete();
		}
		
		//Sets our references to null, but who ever has a reference to us 
		// should set their reference to us to null as well. We are now useless.
		rawModel = null;
		texture = null;
	}
}
