package toolbox;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import entities.Camera;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import renderEngine.Loader;
import textures.ModelTexture;

public class ConvenientMethods 
{
	public static TexturedModel[] loadModel(String fileLocation, String fileName)
	{
		RawModel[] tempRawModels = OBJFileLoader.loadOBJ(fileLocation, fileName, MainGameLoop.gameLoader);
		if(tempRawModels == null)
		{
			//System.out.println("null model returned");
			return null;
		}
		ModelTexture[] tempModelTextures = OBJFileLoader.getModelTexturesCopy();
		TexturedModel[] tm = new TexturedModel[tempRawModels.length];
		//System.out.println("model textures length: "+tempModelTextures.length);
		for(int i = 0; i < tm.length; i++)
        {
			tm[i] = new TexturedModel(tempRawModels[i], tempModelTextures[i]);
        }
		return tm;
	}
	
	//Ignores any mtl file located inside the obj file, instead
	//  uses the specified mtl file instead
	//Initially made for specifying the texture for the sky sphere
	//  without needing a duplicate model for the sphere each time
	public static TexturedModel[] loadModelWithMTL(String fileLocation, String fileNameOBJ, String fileNameMTL)
	{
		RawModel[] tempRawModels = OBJFileLoader.loadOBJwithMTL(fileLocation, fileNameOBJ, fileNameMTL, MainGameLoop.gameLoader);
		if(tempRawModels == null)
		{
			//System.out.println("null model returned");
			return null;
		}
		ModelTexture[] tempModelTextures = OBJFileLoader.getModelTexturesCopy();
		TexturedModel[] tm = new TexturedModel[tempRawModels.length];
		//System.out.println("model textures length: "+tempModelTextures.length);
		for(int i = 0; i < tm.length; i++)
        {
			tm[i] = new TexturedModel(tempRawModels[i], tempModelTextures[i]);
        }
		return tm;
	}
	
	public static void organizeEntities(ArrayList<Entity> entities, Camera cam)
	{
        int n = entities.size();
        for(int j = 1; j < n; j++) 
        {
            Entity entry = entities.get(j);
            float dist = dist(entry.getPosition(), cam.getPosition());
            int i = j-1;
            while((i > -1) && (dist(entities.get(i).getPosition(), cam.getPosition()) < dist)) 
            {
                entities.set(i+1, entities.get(i));
                i--;
            }
            entities.set(i+1, entry);
        }
        
        //System.out.println();
        //for(Entity entity : entities)
        {
        	//System.out.println(dist(entity.getPosition(), cam.getPosition()));
        }
        //System.out.println();
    }
	
	private static float dist(Vector3f vec1, Vector3f vec2)
	{
		Vector3f diff = new Vector3f();
        Vector3f.sub(vec1, vec2, diff);
        return diff.length();
	}
}
