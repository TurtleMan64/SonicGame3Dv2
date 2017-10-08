package network;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ReadThread implements Runnable
{
	private Socket socket;
	private BufferedInputStream inFromServer;
	
	public ReadThread(Socket socket, BufferedInputStream inFromServer)
	{
		this.socket = socket;
		this.inFromServer = inFromServer;
	}
	
	@Override
	public void run()
	{
		int index = 0;
		int next;
		byte[] data = new byte[44];
		
        try
        {
        	//System.out.println("Going to start reading data");
			while ((next = inFromServer.read()) != -1)
			{
				//System.out.println("Recieved byte");
				data[index] = (byte)next;
				index++;
				index = index % 44;
				
				//System.out.println("next = "+index);
				
				if (index == 0) //Complete packet
				{
					//System.out.println("Finished recieving complete packet");
					byte messageType = data[0];
					byte playerID = data[1]; //ID of player from the server
					
					if (messageType == 0) //Update Player
					{
						byte[] bytes = new byte[4];
							bytes[0] = data[2];
							bytes[1] = data[3];
							bytes[2] = data[4];
							bytes[3] = data[5];
						float playerX = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();
						
							bytes[0] = data[6];
							bytes[1] = data[7];
							bytes[2] = data[8];
							bytes[3] = data[9];
						float playerY = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();
						
							bytes[0] = data[10];
							bytes[1] = data[11];
							bytes[2] = data[12];
							bytes[3] = data[13];
						float playerZ = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();
						
							bytes[0] = data[14];
							bytes[1] = data[15];
							bytes[2] = data[16];
							bytes[3] = data[17];
						float playerXVel = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();
						
							bytes[0] = data[18];
							bytes[1] = data[19];
							bytes[2] = data[20];
							bytes[3] = data[21];
						float playerYVel = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();
						
							bytes[0] = data[22];
							bytes[1] = data[23];
							bytes[2] = data[24];
							bytes[3] = data[25];
						float playerZVel = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();
						
							bytes[0] = data[26];
							bytes[1] = data[27];
							bytes[2] = data[28];
							bytes[3] = data[29];
						float playerYRot = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();
			
							bytes[0] = data[30];
							bytes[1] = data[31];
							bytes[2] = data[32];
							bytes[3] = data[33];
						float playerZRot = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();
						
						byte playerModelID = data[34];
						byte playerAnimID = data[35];
						
							bytes[0] = data[36];
							bytes[1] = data[37];
							bytes[2] = data[38];
							bytes[3] = data[39];
						float playerAnimTime = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();
						
							bytes[0] = data[40];
							bytes[1] = data[41];
							bytes[2] = data[42];
							bytes[3] = data[43];
						float playerDeltaTime = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();
						
						NetworkManager.updatePlayer(playerID,
													playerX, playerY, playerZ,
													playerXVel, playerYVel, playerZVel,
													playerYRot, playerZRot,
													playerModelID, playerAnimID,
													playerAnimTime, playerDeltaTime);
					}
					else if (messageType == 1) //Player Disconnected
					{
						NetworkManager.killPlayer(playerID);
					}
				}
			}
		}
        catch (IOException e)
        {
        	e.printStackTrace();
			NetworkManager.disconnect();
		}
	}
	
	public void disconnect()
	{
		try
		{
			inFromServer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			socket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
