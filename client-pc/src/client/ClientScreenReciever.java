package client;

import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class ClientScreenReciever extends Thread {
	private String ServerIP = null;
	private BufferedOutputStream bos;
	private BufferedInputStream bis = null;
	private JPanel cPanel = null;
	private boolean continueLoop = true;
	private int BUFFER_SIZE = 262144;
	private byte[] buf = new byte[BUFFER_SIZE];
	private String receiveFile = "test.jpg";

	public ClientScreenReciever(BufferedInputStream bis, JPanel p,
			String ServerIP) {
		this.bis = bis;
		cPanel = p;
		this.ServerIP = ServerIP;
		// start the thread and thus call the run method
		start();
	}

	public void run() {
		try {
			while (continueLoop) {
				Socket sc = new Socket(ServerIP, 60010);

				bis = new BufferedInputStream(sc.getInputStream());
				bos = new BufferedOutputStream(new FileOutputStream(
						new File(receiveFile)));

				int readCnt = 0;
				while ((readCnt = bis.read(buf)) > 0) {
					
					bos.write(buf, 0, readCnt);
					bos.flush();
					
				}
				File fImage = new File("test.jpg");
				Image image = ImageIO.read(fImage);
				System.out.println("FileReceive : ���� ���� �Ϸ�.");
				image = image.getScaledInstance(cPanel.getWidth(),
						cPanel.getHeight(), Image.SCALE_FAST);
				
				// Draw the recieved screenshot
				Graphics graphics = cPanel.getGraphics();
				graphics.drawImage(image, 0, 0, cPanel.getWidth(),
						cPanel.getHeight(), cPanel);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}