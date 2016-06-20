package zRemoteServer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class xKeyboardReceive implements Runnable {
	private ServerSocket serverSocket;
	public static final int ServerPort = 50001;
	public static String Keyboard;
	private InputStream in;
	private BufferedReader br;

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(ServerPort);
			while (true) {
				Socket client = serverSocket.accept();
				
				try { 
					in = client.getInputStream();
					br = new BufferedReader(new InputStreamReader(in, "euc-kr"));

					Keyboard = br.readLine(); //switch - case 처리할수 있겟다...
					
					xKeyboardCtrl keyboardCtrl = new xKeyboardCtrl();
					keyboardCtrl.Key(Keyboard);
				
				} catch (Exception e) {	} finally {
					client.close();
				}
			}
		} catch (Exception e) {	}
		
	}
}