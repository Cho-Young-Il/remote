package com.example.apptest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import android.util.Log;

public class KeyboardSend implements Runnable {
	public String Keyboard = null;
	private final int PORT = 50001;
	private OutputStream out;
	private BufferedWriter bw;
	
	public KeyboardSend(String str) {
		this.Keyboard = str;
	}
	
	public void run() {
		try {
			InetAddress serverAddr = InetAddress.getByName(SecondActivity.serverIp);
			Log.d("TCP", "KeyboardSend : Connecting...");
			Socket socket = new Socket(serverAddr, PORT);

			try { 
				//Log.d("TCP", "Keyboard : Sending : ");
				out = socket.getOutputStream();
				bw = new BufferedWriter(new OutputStreamWriter(out, "euc-kr"));
				bw.write(Keyboard);
				bw.flush();
				//Log.d("TCP", "KeyboardSend Comp");

			} catch (Exception e) {
				//Log.e("TCP", "KeyboardSend : ConnectionError", e);
			} finally {
				socket.close();
			}
		} catch (IOException e) {
			//Log.e("TCP", "KeyboardSend : SocketError", e);
		}
	}
}