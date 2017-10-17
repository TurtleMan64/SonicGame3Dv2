package network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import engineTester.MainGameLoop;
import entities.Ball;

public class NetworkManager
{
	private static ReadThread readThread;
	private static WriteThread writeThread;
	private static volatile ArrayList<OnlinePlayer> onlinePlayers = new ArrayList<OnlinePlayer>(8);
	
	public static void init()
	{
		onlinePlayers.add(null);
		onlinePlayers.add(null);
		onlinePlayers.add(null);
		onlinePlayers.add(null);
		onlinePlayers.add(null);
		onlinePlayers.add(null);
		onlinePlayers.add(null);
		onlinePlayers.add(null);
	}
	
	public static void attemptConnection()
	{
		if (readThread != null)
		{
			System.err.println("Already connected");
			return;
		}
		
		boolean successfulConnection = true;
		Socket serverSocket = null;
		try
		{
			serverSocket = new Socket(MainGameLoop.multiplayerServerIP, MainGameLoop.multiplayerPort);
		}
		catch (UnknownHostException e)
		{
			successfulConnection = false;
			System.err.println("Cannot connect to server");
		}
		catch (IOException e)
		{
			successfulConnection = false;
			System.err.println("Cannot connect to server");
		}
		
		if (successfulConnection == false)
		{
			readThread = null;
			return;
		}
		
		DataOutputStream outToServer = null;
		try
		{
			outToServer = new DataOutputStream(serverSocket.getOutputStream());
		}
		catch (IOException e)
		{
			successfulConnection = false;
			System.err.println("Cannot connect to server");
		}
		
		if (successfulConnection == false)
		{
			readThread = null;
			return;
		}
		
		BufferedInputStream inFromServer = null;
		try
		{
			inFromServer = new BufferedInputStream(serverSocket.getInputStream());
			//new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		}
		catch (IOException e)
		{
			successfulConnection = false;
			System.err.println("Cannot connect to server");
		}
		
		if (successfulConnection == false)
		{
			readThread = null;
			return;
		}
		
		System.out.println("Connected to server");
		
		readThread = new ReadThread(serverSocket, inFromServer);
		new Thread(readThread).start();
		
		writeThread = new WriteThread(serverSocket, outToServer);
		new Thread(writeThread).start();
	}
	
	public synchronized static ArrayList<OnlinePlayer> getPlayersList()
	{
		return onlinePlayers;
	}
	
	public synchronized static void updatePlayer(int playerNumber, 
												 float x, float y, float z,
												 float xVel, float yVel, float zVel,
												 float yRot, float zRot, 
												 int modelID, int animID, 
												 float time, float deltaTime)
	{
		OnlinePlayer player = onlinePlayers.get(playerNumber);
		if (player == null)
		{
			player = new OnlinePlayer();
			onlinePlayers.set(playerNumber, player);
		}
		player.name = "Temp";
		player.pos.x = x;
		player.pos.y = y;
		player.pos.z = z;
		player.xVel = xVel;
		player.yVel = yVel;
		player.zVel = zVel;
		player.yRot = yRot;
		player.zRot = zRot;
		player.modelID = modelID;
		player.animID = animID;
		player.time = time;
		player.deltaTime = deltaTime;
	}
	
	public synchronized static void killPlayer(int playerNumber)
	{
		onlinePlayers.set(playerNumber, null);
	}
	
	public static void setLocalPlayer(Ball localPlayer)
	{
		if (writeThread != null)
		{
			writeThread.setLocalPlayer(localPlayer);
		}
	}
	
	public static void disconnect()
	{
		System.out.println("Disconnecting from server");
		if (readThread != null)
		{
			readThread.disconnect();
			readThread = null;
		}
		if (writeThread != null)
		{
			writeThread.disconnect();
			writeThread = null;
		}
		onlinePlayers.set(0, null);
		onlinePlayers.set(1, null);
		onlinePlayers.set(2, null);
		onlinePlayers.set(3, null);
		onlinePlayers.set(4, null);
		onlinePlayers.set(5, null);
		onlinePlayers.set(6, null);
		onlinePlayers.set(7, null);
	}
}
