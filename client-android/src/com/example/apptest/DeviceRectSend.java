package com.example.apptest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class DeviceRectSend implements Runnable {
	public String mouseX, mouseY, screenImgWidth, screenImgHeight;
	private final int PORT = 50000;
	private OutputStream out;
	private BufferedWriter bw;
	
	public DeviceRectSend(int mouseX, int mouseY, int screenImgWidth, int screenImgHeight) {
		this.mouseX = Integer.toString(mouseX);
		this.mouseY = Integer.toString(mouseY);
		this.screenImgWidth = Integer.toString(screenImgWidth);
		this.screenImgHeight = Integer.toString(screenImgHeight);
		
	}

	public void run() {
		try {
			InetAddress serverAddr = InetAddress.getByName(SecondActivity.serverIp);
			Socket socket = new Socket(serverAddr, PORT);

			try { 
				out = socket.getOutputStream();
				bw = new BufferedWriter(new OutputStreamWriter(out, "euc-kr"));
				bw.write(mouseX+","+mouseY+","+screenImgWidth+","+screenImgHeight);
				bw.flush();
			} catch (Exception e) { } finally {
				socket.close();
			}
		} catch (IOException e) { }
	}
}