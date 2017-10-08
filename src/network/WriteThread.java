package network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

import engineTester.MainGameLoop;
import entities.Ball;

public class WriteThread implements Runnable
{
	private Socket socket;
	private DataOutputStream outToServer;
	private Ball localPlayer;
	private int messageNumber;
	
	public WriteThread(Socket socket, DataOutputStream outToServer)
	{
		this.socket = socket;
		this.outToServer = outToServer;
		messageNumber = 0;
	}
	
	@Override
	public void run()
	{
		boolean loop = true;
		
		//initial message
		byte[] initMessage = new byte[47];
		for (int i = 0; i < initMessage.length; i++)
		{
			initMessage[i] = 0;
		}
		String nameRaw = MainGameLoop.multiplayerNickname;
		byte[] nameBytes = nameRaw.getBytes(Charset.forName("US-ASCII"));
		for (int i = 0; i < initMessage.length-1; i++)
		{
			if (i < nameBytes.length)
			{
				initMessage[i+1] = nameBytes[i];
			}
		}
		initMessage[0] = 0; //Nickname message
		initMessage[initMessage.length-1] = 0;
		try
		{
			outToServer.write(initMessage);
		}
		catch (IOException e1)
		{
			//e1.printStackTrace();
			NetworkManager.disconnect();
			loop = false;
		}
		
		while (loop)
		{
			try
			{
			    Thread.sleep(MainGameLoop.multiplayerDelay);
			} 
			catch (InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
			
			try
			{
				if (localPlayer != null)
				{
					byte[] message = new byte[47];
					byte[] x = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putFloat(localPlayer.getX()).array();
					byte[] y = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putFloat(localPlayer.getY()).array();
					byte[] z = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putFloat(localPlayer.getZ()).array();
					byte[] xVel = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putFloat(localPlayer.getxVel()+localPlayer.getXVelAir()).array();
					byte[] yVel = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putFloat(localPlayer.getyVel()).array();
					byte[] zVel = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putFloat(localPlayer.getzVel()+localPlayer.getZVelAir()).array();
					byte[] yRot = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putFloat(localPlayer.getBody().getRotY()).array();
					byte[] zRot = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putFloat(localPlayer.getBody().getRotZ()).array();

					byte[] animTime = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putFloat(localPlayer.getBody().time).array();
					byte[] deltaTime = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putFloat(localPlayer.getBody().deltaTime).array();
					
					byte[] messgNum = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(messageNumber).array();
					
					message[0] = 1; //Regular message
					message[1] = x[0];
					message[2] = x[1];
					message[3] = x[2];
					message[4] = x[3];
					message[5] = y[0];
					message[6] = y[1];
					message[7] = y[2];
					message[8] = y[3];
					message[9] = z[0];
					message[10] = z[1];
					message[11] = z[2];
					message[12] = z[3];
					message[13] = xVel[0];
					message[14] = xVel[1];
					message[15] = xVel[2];
					message[16] = xVel[3];
					message[17] = yVel[0];
					message[18] = yVel[1];
					message[19] = yVel[2];
					message[20] = yVel[3];
					message[21] = zVel[0];
					message[22] = zVel[1];
					message[23] = zVel[2];
					message[24] = zVel[3];
					message[25] = yRot[0];
					message[26] = yRot[1];
					message[27] = yRot[2];
					message[28] = yRot[3];
					message[29] = zRot[0];
					message[30] = zRot[1];
					message[31] = zRot[2];
					message[32] = zRot[3];
					message[33] = (byte)localPlayer.getBody().animationIndex;
					message[34] = animTime[0];
					message[35] = animTime[1];
					message[36] = animTime[2];
					message[37] = animTime[3];
					message[38] = deltaTime[0];
					message[39] = deltaTime[1];
					message[40] = deltaTime[2];
					message[41] = deltaTime[3];
					message[42] = (byte)Ball.characterID;
					message[43] = messgNum[0];
					message[44] = messgNum[1];
					message[45] = messgNum[2];
					message[46] = messgNum[3];
					
					outToServer.write(message);
					messageNumber++;
					//System.out.println("msgNum = "+messageNumber);
				}
			}
			catch (IOException e)
			{
				//e.printStackTrace();
				NetworkManager.disconnect();
				loop = false;
			}
		}
	}
	
	public void setLocalPlayer(Ball localPlayer)
	{
		this.localPlayer = localPlayer;
	}
	
	public void disconnect()
	{
		try
		{
			outToServer.close();
		}
		catch (IOException e)
		{
			//e.printStackTrace();
		}
		
		try
		{
			socket.close();
		}
		catch (IOException e)
		{
			//e.printStackTrace();
		}
	}
}
