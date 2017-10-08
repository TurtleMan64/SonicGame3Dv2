package audio;

import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;

public class Test 
{
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		AudioMaster.init();
		//AudioMaster.setListenerData(0, 0, 0);
		AL10.alDistanceModel(AL11.AL_LINEAR_DISTANCE_CLAMPED);
		
		//String path = (new File("res/Audio/bounce.wav")).getAbsolutePath();
		//path = "audio/bounce.wav";
		//System.out.println(path);
		//long diff1;
		//long diff2;
		//long diff3;
		//long diff4;
		
		//final int c = 100;
		
		//int buffer = AudioMaster.loadSound(new File("res/Audio/bounce.wav"));
		Source source = new Source(1, 6, 15);
		source.setLooping(false);
		//source.play(buffer);
		
		//int buffer2 = AudioMaster.loadSound(new File("res/Audio/sonic/S1/mono/S1_Jump.wav"));
		Source source2 = new Source(1, 6, 15);
		source2.setLooping(false);
		
		float xPos = 8;
		//source.setPosition(xPos, 0, 0);
		/*
		LinkedList<String> linkedlist=new LinkedList<String>();
		ArrayList<String> arraylist=new ArrayList<String>();
		//for(int i = 10000000; i != 0; i--)
		{
			//linkedlist.add("a");
			//arraylist.add("a");
		}
		
		//arraylist.add("a");
		//arraylist.add("b");
		//arraylist.add("c");
		//arraylist.add("d");
		//arraylist.add("e");
	    
	    
	    System.out.println("**Advanced For loop Linked**");
	    long timestamp = System.currentTimeMillis();
	    for(String str: linkedlist)
	    //for(int i = c; i != 0; i--)
		{
	    	//str;
	    	//linkedlist.add(0, "a");
	    }
	    diff1 = (System.currentTimeMillis()-timestamp);
	    //linkedlist.clear();
	    System.out.println();
	    
	    System.out.println("**Advanced For loop array**");
	    timestamp = System.currentTimeMillis();
	    for(String str: arraylist)
    	//for(int i = c; i != 0; i--)
		{
	    	//str;
    		//arraylist.add(0, "a");
	    }
	    diff2 = (System.currentTimeMillis()-timestamp);
	    //arraylist.clear();
	    System.out.println();
	    
	    System.out.println("**Iterator linked**");
	    Iterator<String> i = linkedlist.iterator();
	    timestamp = System.currentTimeMillis();
	    while (i.hasNext()) 
	    //for(int r = c; r != 0; r--)
	    {
	    	i.next();
	    }
	    diff3 = (System.currentTimeMillis()-timestamp);
	    //linkedlist.clear();
	    System.out.println();
	    
	    System.out.println("**Iterator array**");
	    Iterator<String> i2 = arraylist.iterator();
	    timestamp = System.currentTimeMillis();
	    while (i2.hasNext()) 
	    //for(int r = c; r != 0; r--)
		{
	    	i2.next();
	    }
	    diff4 = (System.currentTimeMillis()-timestamp);
	    //linkedlist.clear();
	    //System.out.println();

		
	    //System.out.print("linked fancy = "+diff1+"   array fancy = "+diff2+"   linked iterator = "+diff3+"   array iterator = "+diff4);
	    */
		
		int count = 0;
		char c = ' ';
		while(c != 'q')
		{
			xPos -= 0.03f;
			source.setPosition(xPos, 0, 0);
			
			//FloatBuffer listenerPos = BufferUtils.createFloatBuffer(3).put(
		                //new float[]{xPos, 0, 0});
	        //listenerPos.flip();
	        //AL10.alListener(AL10.AL_POSITION, listenerPos);
		    count+=1;
		    if(count == 100)
		    {
		    	count = 0;
		    	//source2.play(buffer2);
		    }
		    
		    if(count % 30 == 0)
		    {
		    	//source.play(buffer);
		    }
			//System.out.println(xPos);
			Thread.sleep(10);
		}
		
		//source.delete();
		AudioMaster.cleanUp();
		
	}
}
