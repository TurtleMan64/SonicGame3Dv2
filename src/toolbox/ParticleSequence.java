package toolbox;

import java.util.ArrayList;

import models.TexturedModel;

public class ParticleSequence 
{
	private ArrayList<TexturedModel[]> models;
	private ArrayList<Float> scales;
	
	public ParticleSequence()
	{
		models = new ArrayList<TexturedModel[]>();
		scales = new ArrayList<Float>();
	}
	
	public void addModel(TexturedModel[] model, float scale)
	{
		models.add(model);
		scales.add(scale);
	}
	
	public TexturedModel[] getModel(int index)
	{
		return models.get(index);
	}
	
	public float getScale(int index)
	{
		return scales.get(index);
	}
	
	public int getNumModels()
	{
		return models.size();
	}
}
