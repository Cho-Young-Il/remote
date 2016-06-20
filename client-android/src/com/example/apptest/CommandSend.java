package com.example.apptest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import android.util.Log;

public class CommandSend implements Runnable {
	public String Command = null;
	private final int PORT = 60000;
	private OutputStream out;
	private BufferedWriter bw;
	
	CommandSend(String str) {
		this.Command = str;
	}
	
	public void run() {
		try {
			InetAddress serverAddr = InetAddress.getByName(SecondActivity.serverIp);
			Log.d("TCP", "CommandSend : Connecting...");
			Socket socket = new Socket(serverAddr, PORT);

			try { 
				//Log.d("TCP", "System Shutdown Command : Sending : ");
				out = socket.getOutputStream();
				bw = new BufferedWriter(new OutputStreamWriter(out, "euc-kr"));
				bw.write(Command);
				bw.flush();
				//Log.d("TCP", "CommandSend Comp");

			} catch (Exception e) {
				//Log.e("TCP", "CommandSend : ConnectionError", e);
			} finally {
				socket.close();
			}
		} catch (IOException e) {
			//Log.e("TCP", "CommandSend : SocketError", e);
		}
	}
}