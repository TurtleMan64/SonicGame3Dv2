package objConverter;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 
import models.RawModel;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import collision.CollisionModel;
import collision.Triangle3D;
import renderEngine.Loader;
import textures.ModelTexture;
 
public class OBJFileLoader 
{
    private static final String RES_LOC = "res/";
    private static ArrayList<ModelTexture> modelTextures = new ArrayList<ModelTexture>();
    
    //public static boolean debug = false;
 
    public static ModelTexture[] getModelTexturesCopy()
	{
		ModelTexture[] copyArray = new ModelTexture[modelTextures.size()];
		for(int i = 0; i < modelTextures.size(); i++)
		{
			copyArray[i] = modelTextures.get(i);
		}
		return copyArray;
	}
    
    public static RawModel[] loadOBJwithMTL(String objLocation, String objFileName, String mtlFileName, Loader loader)
    {
    	try
    	{
    		//MTL Part
	    	ArrayList<String> textureNamesList = new ArrayList<String>();
	    	ArrayList<ModelTexture> modelTexturesList = new ArrayList<ModelTexture>();
	    	InputStreamReader isrTexture = null;
	    	try 
	        {
	    		//frTexture = new FileReader(new File("res/" +objLocation+currentLine[1]));
	    		//InputStream isTexture = Class.class.getResourceAsStream("/res/"+objLocation+mtlFileName+".mtl");
	    		//fileName = fileName.substring(1);
				//InputStream isTexture = Thread.currentThread().getContextClassLoader().getResourceAsStream("res/"+objLocation+mtlFileName+".mtl");
				InputStream isTexture = new FileInputStream("res/"+objLocation+mtlFileName+".mtl");
	        	if(isTexture == null)
	        	{
	        		System.out.println("Couldnt load input stream: '/res/"+objLocation+mtlFileName+".mtl'");
	        	}
	    		isrTexture = new InputStreamReader(isTexture);
	        } 
	        catch (NullPointerException e) 
	        {
	            System.out.println("Couldn't load material file!");
	            e.printStackTrace();
	        }
	        BufferedReader readerTexture = new BufferedReader(isrTexture);
	        String lineTexture = "";
	        modelTextures.clear();
	        
	        //go through mtl file and put the texture names into array
	        lineTexture = readerTexture.readLine();
	        float currentShineDamperValue = 0.0f;
	        float currentReflectivityValue = 0.0f;
	        float currentTransparencyValue = 1.0f;
	        float currentFakeLightingValue = 1.0f;
	        float currentGlowAmountValue = 0.0f;
	        float currentScrollXValue = 0.0f;
	        float currentScrollYValue = 0.0f;
	        while(lineTexture != null)
	        {
	        	String[] currentLineTexture = lineTexture.split(" ");
	        	//System.out.println(currentLineTexture[0]);
	        	//if(debug)
	        	{
	        		//System.out.println(lineTexture);
	        	}
	        	
	        	if(lineTexture.startsWith("newmtl"))
	        	{
	        		textureNamesList.add(currentLineTexture[1]);
	        		currentShineDamperValue = 0.0f;
	    	        currentReflectivityValue = 0.0f;
	    	        currentTransparencyValue = 1.0f;
	    	        currentFakeLightingValue = 1.0f;
	    	        currentGlowAmountValue = 0.0f;
	    	        currentScrollXValue = 0.0f;
	    	        currentScrollYValue = 0.0f;
	        		//System.out.println("adding "+currentLineTexture[1]+" to the textureNamesList");
	        	}
	        	else if(lineTexture.startsWith("	map_Kd"))
	        	{
	        		String fileName = currentLineTexture[1].substring(0, currentLineTexture[1].length()-4);
	        		//System.out.println(fileName);
	        		ModelTexture newTexture = new ModelTexture(loader.loadTexture(objLocation + fileName));
	        		modelTexturesList.add(newTexture);
	        		newTexture.setShineDamper(currentShineDamperValue);
	        		newTexture.setReflectivity(currentReflectivityValue);
	        		if(currentTransparencyValue > 0)
	        		{
	        			newTexture.setHasTransparency(true);
	        		}
	        		if(currentFakeLightingValue < 1.0)
	        		{
	        			newTexture.setUseFakeLighting(true);
	        		}
	        		newTexture.setGlowAmount(currentGlowAmountValue);
	        		newTexture.setScrollX(currentScrollXValue);
	        		newTexture.setScrollY(currentScrollYValue);
	        	}
	        	else if(lineTexture.startsWith("	Ns"))
	        	{
	        		currentShineDamperValue = (Float.parseFloat(currentLineTexture[1]));
	        	}
	        	else if(lineTexture.startsWith("	Ni"))
	        	{
	        		currentReflectivityValue = (Float.parseFloat(currentLineTexture[1]));
	        	}
	        	else if(lineTexture.startsWith("	Tr"))
	        	{
	        		currentTransparencyValue = (Float.parseFloat(currentLineTexture[1]));
	        	}
	        	else if(lineTexture.startsWith("	d"))
	        	{
	        		currentFakeLightingValue = (Float.parseFloat(currentLineTexture[1]));
	        	}
	        	else if(lineTexture.startsWith("	glow"))
	        	{
	        		currentGlowAmountValue = (Float.parseFloat(currentLineTexture[1]));
	        	}
	        	else if(lineTexture.startsWith("	scrollX"))
	        	{
	        		currentScrollXValue = (Float.parseFloat(currentLineTexture[1]));
	        	}
	        	else if(lineTexture.startsWith("	scrollY"))
	        	{
	        		currentScrollYValue = (Float.parseFloat(currentLineTexture[1]));
	        	}
	        	
	        	lineTexture = readerTexture.readLine();
	        }
	        
	        isrTexture.close();
	        
	        
	        
	        
	        
	        //OBJ Part
	        InputStreamReader isr = null;
	        List<RawModel> rawModelsList = new ArrayList<RawModel>();
	        try 
	        {
	        	//InputStream is = Class.class.getResourceAsStream("/res/"+objLocation + objFileName + ".obj");
				//InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("res/"+objLocation + objFileName + ".obj");
				InputStream is = new FileInputStream("res/"+objLocation + objFileName + ".obj");
	        	if(is == null)
	        	{
	        		System.out.println("Couldnt load input stream: '/res/" + objLocation + objFileName + ".obj'");
	        	}
	        	isr = new InputStreamReader(is);
	        } 
	        catch (NullPointerException e) 
	        {
	            System.err.println("/res/" + objLocation + objFileName + ".obj"+" not found");
	            RawModel[] mod = new RawModel[1];
	            mod[0] = new RawModel(0,0,null);
	            System.out.println("bad");
	            return mod;
	        }
	        BufferedReader reader = new BufferedReader(isr);
	        String line;
	        List<Vertex> vertices = new ArrayList<Vertex>();
	        List<Vector2f> textures = new ArrayList<Vector2f>();
	        List<Vector3f> normals = new ArrayList<Vector3f>();
	        List<Integer> indices = new ArrayList<Integer>();
	        float[] verticesArray = null;
			float[] texturesArray = null;
			float[] normalsArray = null;
			int[] indicesArray = null;
	        	
        	line = reader.readLine();
            while (line != null) 
            {
                //line = reader.readLine();
            	//System.out.println(line);
                if (line.startsWith("v ")) 
                {
                    String[] currentLine = line.split(" ");
                    Vector3f vertex;
                	if(currentLine[1].equals(""))
					{
						vertex = new Vector3f(Float.parseFloat(currentLine[2]), 
								Float.parseFloat(currentLine[3]), 
								Float.parseFloat(currentLine[4]));
					}
					else
					{
						vertex = new Vector3f(Float.parseFloat(currentLine[1]), 
								Float.parseFloat(currentLine[2]), 
								Float.parseFloat(currentLine[3]));
					}
                    Vertex newVertex = new Vertex(vertices.size(), vertex);
                    vertices.add(newVertex);
 
                } 
                else if (line.startsWith("vt ")) 
                {
                    String[] currentLine = line.split(" ");
                    Vector2f texture = new Vector2f((float) Float.valueOf(currentLine[1]),
                            (float) Float.valueOf(currentLine[2]));
                    textures.add(texture);
                } 
                else if (line.startsWith("vn ")) 
                {
                    String[] currentLine = line.split(" ");
                    Vector3f normal = new Vector3f((float) Float.valueOf(currentLine[1]),
                            (float) Float.valueOf(currentLine[2]),
                            (float) Float.valueOf(currentLine[3]));
                    normals.add(normal);
                } 
                else if (line.startsWith("f ")) 
                {
                	String[] currentLine = line.split(" ");
                	//texturesArray = new float[vertices.size()*2];
                    //normalsArray = new float[vertices.size()*3];
                    while((line != null) && line.startsWith("f "))
                    {
                        currentLine = line.split(" ");
                        //System.out.println(line);
                        String[] vertex1 = currentLine[1].split("/");
                        String[] vertex2 = currentLine[2].split("/");
                        String[] vertex3 = currentLine[3].split("/");
                        processVertex(vertex1, vertices, indices);
                        processVertex(vertex2, vertices, indices);
                        processVertex(vertex3, vertices, indices);
                        line = reader.readLine();
                        if(line!= null && line.startsWith("s "))
                        { //"s " doesen't mean that we should stop!
                            line = reader.readLine();
                        }
                    }
                    if(line != null && line.startsWith("usemtl ") && vertices.size() > 0)
                    { //This could for sure be better!
                    	currentLine = line.split(" ");
                    	for(int i = 0; i < textureNamesList.size(); i++)
                    	{
                    		String dummy = textureNamesList.get(i);
                    		//System.out.println(dummy + " "+ currentLine[1]);
                    		if(dummy.equals(currentLine[1]))
                    		{
                    			//System.out.println("adding "+currentLine[1]+" to the modelTextures");
                    			modelTextures.add(modelTexturesList.get(i));
                    			//System.out.println("adding "+currentLine[1]+" to the modelTextures");
                    			//break;
                    		}
                    	}
                    	
                    	removeUnusedVertices(vertices);
                        verticesArray = new float[vertices.size() * 3];
                        texturesArray = new float[vertices.size() * 2];
                        normalsArray = new float[vertices.size() * 3];
                        float furthest = convertDataToArrays(vertices, textures, normals, verticesArray,
                                texturesArray, normalsArray);
                        indicesArray = convertIndicesListToArray(indices);
                        ModelData data = new ModelData(verticesArray, texturesArray, normalsArray, indicesArray,
                                furthest);
                        rawModelsList.add(loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()));
                        
                        indices.clear();
                    }
                }
                else if(line.startsWith("usemtl "))//first usetml found, before any faces entered
                {
                	//System.out.println("in first usemtl");
                	String[] currentLine = line.split(" ");
                	for(int i = 0; i < textureNamesList.size(); i++)
                	{
                		String dummy = textureNamesList.get(i);
                		//System.out.println(dummy);
                		if(dummy.equals(currentLine[1]))
                		{
                			modelTextures.add(modelTexturesList.get(i));
                			//System.out.println("adding "+currentLine[1]+" to the modelTextures");
                			//break;
                		}
                	}
                }
                line = reader.readLine();
            }
            reader.close();
            
            removeUnusedVertices(vertices);
            verticesArray = new float[vertices.size() * 3];
            texturesArray = new float[vertices.size() * 2];
            normalsArray = new float[vertices.size() * 3];
            float furthest = convertDataToArrays(vertices, textures, normals, verticesArray,
                    texturesArray, normalsArray);
            indicesArray = convertIndicesListToArray(indices);
            ModelData data = new ModelData(verticesArray, texturesArray, normalsArray, indicesArray,
                    furthest);
            rawModelsList.add(loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()));
            
            RawModel[] modelArray = new RawModel[rawModelsList.size()];
            for(int i = 0; i < rawModelsList.size(); i++)
            {
            	modelArray[i] = rawModelsList.get(i);
            }
            //System.out.println("num models = "+modelArray.length);
            //System.out.println("num vertex = "+verticesArray.length);
            //System.out.println("num textur = "+texturesArray.length);
            //System.out.println("num normal = "+normalsArray.length);
            return modelArray;
    	}
    	catch (IOException e) 
        {
            System.out.println("Error reading the file");
            e.printStackTrace();
        }
    	
    	return null;
    }
    
    
    public static RawModel[] loadOBJ(String objLocation, String objFileName, Loader loader) 
    {
    	InputStreamReader isr = null;
    	InputStreamReader isrTexture = null;
        //File objFile = new File(RES_LOC + objLocation + objFileName + ".obj");
        
        ArrayList<String> textureNamesList = new ArrayList<String>();
        ArrayList<ModelTexture> modelTexturesList = new ArrayList<ModelTexture>();
        
        
        List<RawModel> rawModelsList = new ArrayList<RawModel>();
        try 
        {
        	//InputStream is = Class.class.getResourceAsStream("/res/"+objLocation + objFileName + ".obj");
        	//InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("res/"+objLocation + objFileName + ".obj");
        	InputStream is = new FileInputStream("res/"+objLocation + objFileName + ".obj");
        	if(is == null)
        	{
        		System.out.println("Couldnt load input stream: '/res/" + objLocation + objFileName + ".obj'");
        	}
        	isr = new InputStreamReader(is);
        } 
        catch (NullPointerException e) 
        {
            System.err.println("/res/" + objLocation + objFileName + ".obj"+" not found");
            RawModel[] mod = new RawModel[1];
            mod[0] = new RawModel(0,0,null);
            System.out.println("bad");
            return mod;
        }
        catch (FileNotFoundException e)
        {
			e.printStackTrace();
		}
        BufferedReader reader = new BufferedReader(isr);
        String line;
        List<Vertex> vertices = new ArrayList<Vertex>();
        List<Vector2f> textures = new ArrayList<Vector2f>();
        List<Vector3f> normals = new ArrayList<Vector3f>();
        List<Integer> indices = new ArrayList<Integer>();
        float[] verticesArray = null;
		float[] texturesArray = null;
		float[] normalsArray = null;
		int[] indicesArray = null;
        try 
        {
        	
        	line = reader.readLine();
            while (line != null) 
            {
                //line = reader.readLine();
            	//System.out.println(line);
                if (line.startsWith("v ")) 
                {
                    String[] currentLine = line.split(" ");
                    Vector3f vertex;
                	if(currentLine[1].equals(""))
					{
						vertex = new Vector3f(Float.parseFloat(currentLine[2]), 
								Float.parseFloat(currentLine[3]), 
								Float.parseFloat(currentLine[4]));
					}
					else
					{
						vertex = new Vector3f(Float.parseFloat(currentLine[1]), 
								Float.parseFloat(currentLine[2]), 
								Float.parseFloat(currentLine[3]));
					}
                    Vertex newVertex = new Vertex(vertices.size(), vertex);
                    vertices.add(newVertex);
 
                } 
                else if (line.startsWith("vt ")) 
                {
                    String[] currentLine = line.split(" ");
                    Vector2f texture = new Vector2f((float) Float.valueOf(currentLine[1]),
                            (float) Float.valueOf(currentLine[2]));
                    textures.add(texture);
                } 
                else if (line.startsWith("vn ")) 
                {
                    String[] currentLine = line.split(" ");
                    Vector3f normal = new Vector3f((float) Float.valueOf(currentLine[1]),
                            (float) Float.valueOf(currentLine[2]),
                            (float) Float.valueOf(currentLine[3]));
                    normals.add(normal);
                } 
                else if (line.startsWith("f ")) 
                {
                	String[] currentLine = line.split(" ");
                	//texturesArray = new float[vertices.size()*2];
                    //normalsArray = new float[vertices.size()*3];
                    while((line != null) && line.startsWith("f "))
                    {
                        currentLine = line.split(" ");
                        //System.out.println(line);
                        String[] vertex1 = currentLine[1].split("/");
                        String[] vertex2 = currentLine[2].split("/");
                        String[] vertex3 = currentLine[3].split("/");
                        processVertex(vertex1, vertices, indices);
                        processVertex(vertex2, vertices, indices);
                        processVertex(vertex3, vertices, indices);
                        line = reader.readLine();
                        if(line!= null && line.startsWith("s "))
                        { //"s " doesen't mean that we should stop!
                            line = reader.readLine();
                        }
                    }
                    if(line != null && line.startsWith("usemtl ") && vertices.size() > 0)
                    { //This could for sure be better!
                    	currentLine = line.split(" ");
                    	for(int i = 0; i < textureNamesList.size(); i++)
                    	{
                    		String dummy = textureNamesList.get(i);
                    		//System.out.println(dummy + " "+ currentLine[1]);
                    		if(dummy.equals(currentLine[1]))
                    		{
                    			//System.out.println("adding "+currentLine[1]+" to the modelTextures");
                    			modelTextures.add(modelTexturesList.get(i));
                    			//System.out.println("adding "+currentLine[1]+" to the modelTextures");
                    			//break;
                    		}
                    	}
                    	
                    	removeUnusedVertices(vertices);
                        verticesArray = new float[vertices.size() * 3];
                        texturesArray = new float[vertices.size() * 2];
                        normalsArray = new float[vertices.size() * 3];
                        float furthest = convertDataToArrays(vertices, textures, normals, verticesArray,
                                texturesArray, normalsArray);
                        indicesArray = convertIndicesListToArray(indices);
                        ModelData data = new ModelData(verticesArray, texturesArray, normalsArray, indicesArray,
                                furthest);
                        rawModelsList.add(loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()));
                        
                        indices.clear();
                    }
                }
                else if(line.startsWith("usemtl "))//first usetml found, before any faces entered
                {
                	//System.out.println("in first usemtl");
                	String[] currentLine = line.split(" ");
                	for(int i = 0; i < textureNamesList.size(); i++)
                	{
                		String dummy = textureNamesList.get(i);
                		//System.out.println(dummy);
                		if(dummy.equals(currentLine[1]))
                		{
                			//System.out.println("adding "+currentLine[1]+" to the modelTextures");
                			modelTextures.add(modelTexturesList.get(i));
                			//break;
                		}
                	}
                }
                else if(line.startsWith("mtllib"))
                {
                	String[] currentLine = line.split(" ");
                	try 
                    {
                		//frTexture = new FileReader(new File("res/" +objLocation+currentLine[1]));
                		//InputStream isTexture = Class.class.getResourceAsStream("/res/"+objLocation+currentLine[1]);
                		//InputStream isTexture = Thread.currentThread().getContextClassLoader().getResourceAsStream("res/"+objLocation+currentLine[1]);
                		InputStream isTexture = new FileInputStream("res/"+objLocation+currentLine[1]);
                    	if(isTexture == null)
                    	{
                    		System.out.println("Couldnt load input stream: '/res/"+objLocation+currentLine[1]+"'");
                    	}
                		isrTexture = new InputStreamReader(isTexture);
                    } 
                    catch (NullPointerException e) 
                    {
                        System.out.println("Couldn't load material file!");
                        e.printStackTrace();
                    }
                    BufferedReader readerTexture = new BufferedReader(isrTexture);
                    String lineTexture = "";
                    modelTextures.clear();
                    
                    //go through mtl file and put the texture names into array
                    lineTexture = readerTexture.readLine();
                    float currentShineDamperValue = 0.0f;
                    float currentReflectivityValue = 0.0f;
                    float currentTransparencyValue = 1.0f;
                    float currentFakeLightingValue = 1.0f;
                    float currentGlowAmountValue = 0.0f;
                    float currentScrollXValue = 0.0f;
                    float currentScrollYValue = 0.0f;
                    while(lineTexture != null)
                    {
                    	String[] currentLineTexture = lineTexture.split(" ");
                    	//System.out.println(currentLineTexture[0]);
                    	//if(debug)
                    	{
                    		//System.out.println(lineTexture);
                    	}
                    	
                    	if(lineTexture.startsWith("newmtl"))
                    	{
                    		textureNamesList.add(currentLineTexture[1]);
                            currentShineDamperValue = 0.0f;
                            currentReflectivityValue = 0.0f;
                            currentTransparencyValue = 1.0f;
                            currentFakeLightingValue = 1.0f;
                            currentGlowAmountValue = 0.0f;
                            currentScrollXValue = 0.0f;
                            currentScrollYValue = 0.0f;
                    		//System.out.println("adding "+currentLineTexture[1]+" to the textureNamesList");
                    	}
                    	else if(lineTexture.startsWith("	map_Kd"))
                    	{
                    		String fileName = currentLineTexture[1].substring(0, currentLineTexture[1].length()-4);
                    		//System.out.println(fileName);
                    		ModelTexture newTexture = new ModelTexture(loader.loadTexture(objLocation + fileName));
                    		//System.out.println("adding "+fileName+" to the modelTexturesList");
                    		modelTexturesList.add(newTexture);
                    		newTexture.setShineDamper(currentShineDamperValue);
                    		newTexture.setReflectivity(currentReflectivityValue);
                    		if(currentTransparencyValue > 0)
                    		{
                    			newTexture.setHasTransparency(true);
                    		}
                    		if(currentFakeLightingValue < 1.0)
                    		{
                    			newTexture.setUseFakeLighting(true);
                    		}
                    		newTexture.setGlowAmount(currentGlowAmountValue);
                    		newTexture.setScrollX(currentScrollXValue);
                    		newTexture.setScrollY(currentScrollYValue);
                    	}
                    	else if(lineTexture.startsWith("	Ns"))
                    	{
                    		currentShineDamperValue = (Float.parseFloat(currentLineTexture[1]));
                    	}
                    	else if(lineTexture.startsWith("	Ni"))
                    	{
                    		currentReflectivityValue = (Float.parseFloat(currentLineTexture[1]));
                    	}
                    	else if(lineTexture.startsWith("	Tr"))
                    	{
                    		currentTransparencyValue = (Float.parseFloat(currentLineTexture[1]));
                    	}
                    	else if(lineTexture.startsWith("	d"))
                    	{
                    		currentFakeLightingValue = (Float.parseFloat(currentLineTexture[1]));
                    	}
                    	else if(lineTexture.startsWith("	glow"))
                    	{
                    		currentGlowAmountValue = (Float.parseFloat(currentLineTexture[1]));
                    	}
                    	else if(lineTexture.startsWith("	scrollX"))
                    	{
                    		currentScrollXValue = (Float.parseFloat(currentLineTexture[1]));
                    	}
                    	else if(lineTexture.startsWith("	scrollY"))
                    	{
                    		currentScrollYValue = (Float.parseFloat(currentLineTexture[1]));
                    	}
                    	
                    	lineTexture = readerTexture.readLine();
                    }
                    
                    isrTexture.close();
                }
                line = reader.readLine();
            }
            reader.close();
        } 
        catch (IOException e) 
        {
            System.out.println("Error reading the file");
            e.printStackTrace();
        }
        removeUnusedVertices(vertices);
        verticesArray = new float[vertices.size() * 3];
        texturesArray = new float[vertices.size() * 2];
        normalsArray = new float[vertices.size() * 3];
        float furthest = convertDataToArrays(vertices, textures, normals, verticesArray,
                texturesArray, normalsArray);
        indicesArray = convertIndicesListToArray(indices);
        ModelData data = new ModelData(verticesArray, texturesArray, normalsArray, indicesArray,
                furthest);
        rawModelsList.add(loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()));
        
        RawModel[] modelArray = new RawModel[rawModelsList.size()];
        for(int i = 0; i < rawModelsList.size(); i++)
        {
        	modelArray[i] = rawModelsList.get(i);
        }
        //System.out.println("num models = "+modelArray.length);
        //System.out.println("num vertex = "+verticesArray.length);
        //System.out.println("num textur = "+texturesArray.length);
        //System.out.println("num normal = "+normalsArray.length);
        return modelArray;
    }
    
    public static CollisionModel loadCollisionOBJ(String objLocation, String objFileName) 
    {
    	CollisionModel collisionModel = new CollisionModel();
    	InputStreamReader isr = null;
    	InputStreamReader isrTexture = null;
        //File objFile = new File(RES_LOC + objLocation + objFileName + ".obj");
        
        ArrayList<FakeTexture> fakeTextures = new ArrayList<FakeTexture>();
        byte currType = 0;
        int currSound = 0;
        byte currParticle = 0;
        
        try 
        {
        	//isr = new InputStreamReader(Class.class.getResourceAsStream("/res/"+objLocation + objFileName + ".obj"));
        	//isr = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("res/"+objLocation + objFileName + ".obj"));
        	isr = new InputStreamReader(new FileInputStream("res/"+objLocation + objFileName + ".obj"));
        } 
        catch (NullPointerException e) 
        {
            System.err.println("/res/" + objLocation + objFileName + ".obj"+" not found");
            RawModel[] mod = new RawModel[1];
            mod[0] = new RawModel(0,0,null);
            return new CollisionModel();
        }
        catch (FileNotFoundException e)
        {
			e.printStackTrace();
		}
        
        BufferedReader reader = new BufferedReader(isr);
        String line;
        List<Vector3f> vertices = new ArrayList<Vector3f>();
        
        try 
        {
        	
        	line = reader.readLine();
            while (line != null) 
            {
                //line = reader.readLine();
            	//System.out.println(line);
                if (line.startsWith("v ")) 
                {
                    String[] currentLine = line.split(" ");
                    Vector3f vertex;
                	if(currentLine[1].equals(""))
					{
						vertex = new Vector3f(Float.parseFloat(currentLine[2]), 
								Float.parseFloat(currentLine[3]), 
								Float.parseFloat(currentLine[4]));
					}
					else
					{
						vertex = new Vector3f(Float.parseFloat(currentLine[1]), 
								Float.parseFloat(currentLine[2]), 
								Float.parseFloat(currentLine[3]));
					}
                    vertices.add(vertex);
 
                }
                else if (line.startsWith("f ")) 
                {
                	String[] currentLine = line.split(" ");
                	//texturesArray = new float[vertices.size()*2];
                    //normalsArray = new float[vertices.size()*3];
                    while((line != null) && line.startsWith("f "))
                    {
                        currentLine = line.split(" ");
                        //System.out.println(line);
                        String[] vertex1 = currentLine[1].split("/");
                        String[] vertex2 = currentLine[2].split("/");
                        String[] vertex3 = currentLine[3].split("/");
                        
                        Vector3f vert1 = vertices.get(Integer.parseInt(vertex1[0])-1);
                        Vector3f vert2 = vertices.get(Integer.parseInt(vertex2[0])-1);
                        Vector3f vert3 = vertices.get(Integer.parseInt(vertex3[0])-1);
                        
                        float vx1 = vert1.x;
        				float vy1 = vert1.y;
        				float vz1 = vert1.z;
        				
        				float vx2 = vert2.x;
        				float vy2 = vert2.y;
        				float vz2 = vert2.z;
        				
        				float vx3 = vert3.x;
        				float vy3 = vert3.y;
        				float vz3 = vert3.z;
        				
        				collisionModel.triangles.add(new Triangle3D(
        						new Vector3f(vx1,vy1,vz1), 
        						new Vector3f(vx2,vy2,vz2), 
        						new Vector3f(vx3,vy3,vz3), currType, currSound, currParticle));
        				
        				line = reader.readLine();
                        if(line!= null && line.startsWith("s "))
                        { //"s " doesen't mean that we should stop!
                            line = reader.readLine();
                        }
                    }
                    
                    if(line != null && line.startsWith("usemtl ") && vertices.size() > 0)
                    { //This could for sure be better!
                    	currType = (byte)0;
                    	currSound = -1;
                    	currParticle = (byte)0;
                    	currentLine = line.split(" ");
                    	for(int i = 0; i < fakeTextures.size(); i++)
                    	{
                    		FakeTexture dummy = fakeTextures.get(i);
                    		//System.out.println(dummy + " "+ currentLine[1]);
                    		if (dummy.name.equals(currentLine[1]))
                    		{
                    			//System.out.println("adding "+currentLine[1]+" to the modelTextures");
                    			currType = dummy.type;
                    			currSound = dummy.sound;
                    			currParticle = dummy.particle;
                    			//System.out.println("adding "+currentLine[1]+" to the modelTextures");
                    			//break;
                    		}
                    	}
                    }
                }
                else if(line.startsWith("usemtl "))//first usetml found, before any faces entered
                {
                	currType = (byte)0;
                	currSound = -1;
                	currParticle = (byte)0;
                	String[] currentLine = line.split(" ");
                	for(int i = 0; i < fakeTextures.size(); i++)
                	{
                		FakeTexture dummy = fakeTextures.get(i);
                		if(dummy.name.equals(currentLine[1]))
                		{
                			currType = dummy.type;
                			currSound = dummy.sound;
                			currParticle = dummy.particle;
                		}
                	}
                }
                else if(line.startsWith("mtllib"))
                {
                	String[] currentLine = line.split(" ");
                	try 
                    {
                		//isrTexture = new InputStreamReader(Class.class.getResourceAsStream("/res/"+objLocation+currentLine[1]));
                		//isrTexture = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("res/"+objLocation+currentLine[1]));
                		isrTexture = new InputStreamReader(new FileInputStream("res/"+objLocation+currentLine[1]));
                    } 
                    catch (NullPointerException e) 
                    {
                        System.err.println("Couldn't load file!");
                        e.printStackTrace();
                    }
                    BufferedReader readerTexture = new BufferedReader(isrTexture);
                    String lineTexture = "";
                    //ArrayList<String> textureNamesList = new ArrayList<String>();
                    //ArrayList<Byte> modelTexturesTypeList = new ArrayList<Byte>();
                    
                    //go through mtl file and put the texture names into array
                    lineTexture = readerTexture.readLine();
                    while(lineTexture != null)
                    {
                    	String[] currentLineTexture = lineTexture.split(" ");
                    	//System.out.println(currentLineTexture[0]);
                    	if(lineTexture.contains("newmtl"))
                    	{
                    		FakeTexture fktex = new FakeTexture();
                    		fktex.setName(currentLineTexture[1]);
                    		fakeTextures.add(fktex);
                    		//System.out.println("adding "+currentLineTexture[1]+" to the textureNamesList");
                    	}
                    	else if(lineTexture.contains("type"))
                    	{
                    		fakeTextures.get(fakeTextures.size()-1).setType((byte)Math.round(Float.parseFloat(currentLineTexture[1])));
                    	}
                    	else if(lineTexture.contains("sound"))
                    	{
                    		fakeTextures.get(fakeTextures.size()-1).setSound((int)Math.round(Float.parseFloat(currentLineTexture[1])));
                    	}
                    	else if(lineTexture.contains("particle"))
                    	{
                    		fakeTextures.get(fakeTextures.size()-1).setParticle((byte)Math.round(Float.parseFloat(currentLineTexture[1])));
                    	}
                    	
                    	lineTexture = readerTexture.readLine();
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } 
        catch (IOException e) 
        {
            System.err.println("Error reading the file");
        }
        
        //for(int i = 0; i < fakeTextures.size(); i++)
    	//{
    		//FakeTexture dummy = fakeTextures.get(i);
    		//System.out.println("name  "+dummy.name);
			//System.out.println("type  "+dummy.type);
			//System.out.println("sound "+dummy.sound);
			//System.out.println();
    	//}
        
        collisionModel.generateMinMaxValues();

        return collisionModel;
    }
 
    private static void processVertex(String[] vertex, List<Vertex> vertices, List<Integer> indices) 
    {
        int index = Integer.parseInt(vertex[0]) - 1;
        Vertex currentVertex = vertices.get(index);
        int textureIndex = Integer.parseInt(vertex[1]) - 1;
        int normalIndex = Integer.parseInt(vertex[2]) - 1;
        if (!currentVertex.isSet()) 
        {
            currentVertex.setTextureIndex(textureIndex);
            currentVertex.setNormalIndex(normalIndex);
            indices.add(index);
        } 
        else 
        {
            dealWithAlreadyProcessedVertex(currentVertex, textureIndex, normalIndex, indices,
                    vertices);
        }
    }
 
    private static int[] convertIndicesListToArray(List<Integer> indices) 
    {
        int[] indicesArray = new int[indices.size()];
        for (int i = 0; i < indicesArray.length; i++) 
        {
            indicesArray[i] = indices.get(i);
        }
        return indicesArray;
    }
 
    private static float convertDataToArrays(List<Vertex> vertices, List<Vector2f> textures,
            List<Vector3f> normals, float[] verticesArray, float[] texturesArray,
            float[] normalsArray) 
    {
        float furthestPoint = 0;
        for (int i = 0; i < vertices.size(); i++) 
        {
            Vertex currentVertex = vertices.get(i);
            if (currentVertex.getLength() > furthestPoint) 
            {
                furthestPoint = currentVertex.getLength();
            }
            Vector3f position = currentVertex.getPosition();
            Vector2f textureCoord = textures.get(currentVertex.getTextureIndex());
            Vector3f normalVector = normals.get(currentVertex.getNormalIndex());
            verticesArray[i * 3] = position.x;
            verticesArray[i * 3 + 1] = position.y;
            verticesArray[i * 3 + 2] = position.z;
            texturesArray[i * 2] = textureCoord.x;
            texturesArray[i * 2 + 1] = 1 - textureCoord.y;
            normalsArray[i * 3] = normalVector.x;
            normalsArray[i * 3 + 1] = normalVector.y;
            normalsArray[i * 3 + 2] = normalVector.z;
        }
        return furthestPoint;
    }
 
    private static void dealWithAlreadyProcessedVertex(Vertex previousVertex, int newTextureIndex,
            int newNormalIndex, List<Integer> indices, List<Vertex> vertices) 
    {
        if (previousVertex.hasSameTextureAndNormal(newTextureIndex, newNormalIndex)) 
        {
            indices.add(previousVertex.getIndex());
        } 
        else 
        {
            Vertex anotherVertex = previousVertex.getDuplicateVertex();
            if (anotherVertex != null) 
            {
                dealWithAlreadyProcessedVertex(anotherVertex, newTextureIndex, newNormalIndex,
                        indices, vertices);
            } 
            else 
            {
                Vertex duplicateVertex = new Vertex(vertices.size(), previousVertex.getPosition());
                duplicateVertex.setTextureIndex(newTextureIndex);
                duplicateVertex.setNormalIndex(newNormalIndex);
                previousVertex.setDuplicateVertex(duplicateVertex);
                vertices.add(duplicateVertex);
                indices.add(duplicateVertex.getIndex());
            }
        }
    }
     
    private static void removeUnusedVertices(List<Vertex> vertices)
    {
        for(Vertex vertex:vertices)
        {
            if(!vertex.isSet())
            {
                vertex.setTextureIndex(0);
                vertex.setNormalIndex(0);
            }
        }
    }
}