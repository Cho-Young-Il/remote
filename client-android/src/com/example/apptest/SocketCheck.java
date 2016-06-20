package com.example.apptest;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketCheck {
	
	public String IPADDRESS = null;				
	static Socket sock = null;					
	static BufferedReader reader1 = null;
	InetAddress ip = null;						
	
	SocketCheck(String str)	{
		this.IPADDRESS = str;
	}
	
	public boolean SockCheck() {	
		try {
			ip = InetAddress.getByName(IPADDRESS);
			sock = new Socket(ip, 5555);
			sock.close();
			return true;
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}