package zRemoteServer;

import java.net.ServerSocket;
import java.net.Socket;

public class xSocketCheck implements Runnable {
	private ServerSocket serverSocket;
	public static final int ServerPort = 5555;
	
	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(ServerPort);

			while (true) {
				Socket client = serverSocket.accept();
				client.close();
			}		
		} catch (Exception e) {	}
	}
}