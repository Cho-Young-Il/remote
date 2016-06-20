package client;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.beans.PropertyVetoException;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;


class ClientHandler extends Thread {
	private String ServerIP = null;
	private int port = 60011;
    private JDesktopPane desktop = null;
    private Socket cSocket = null;
    private BufferedInputStream bis;
    private JInternalFrame interFrame = new JInternalFrame("Client Screen",
                                                            true, true, true);
    private JPanel cPanel = new JPanel();
    
    public ClientHandler(JDesktopPane desktop, String ServerIP) {
        this.desktop = desktop;
        this.ServerIP = ServerIP;
        start();
    }

    /*
     * Draw GUI per each connected client
     */
    public void drawGUI(){
        interFrame.setLayout(new BorderLayout());
        interFrame.getContentPane().add(cPanel,BorderLayout.CENTER);
        interFrame.setSize(100,100);
        desktop.add(interFrame);
        try {
        	
            //Initially show the internal frame maximized
            interFrame.setMaximum(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
        //this allows to handle KeyListener events
        cPanel.setFocusable(true);
        interFrame.setVisible(true);
    }

    public void run(){

        //used to represent client screen size
    	ObjectInputStream ois = null;
        Rectangle clientScreenDim = null;
        InputStream in = null;
        //Used to read screenshots and client screen dimension
        
        //start drawing GUI
        drawGUI();

        try{
            //Read client screen dimension
        	cSocket = new Socket(ServerIP, port);
	        in = cSocket.getInputStream();
			bis = new BufferedInputStream(in);
				
            ois = new ObjectInputStream(cSocket.getInputStream());
            clientScreenDim =(Rectangle) ois.readObject();
				//clientScreenDim = RectangleReceive.clientScreenDim;
        }catch(Exception ex){
            ex.printStackTrace();
        }/*catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }*/
        //Start recieveing screenshots
        
        new ClientScreenReciever(bis,cPanel,ServerIP);
        new ClientCommandsSender(ServerIP, cPanel, clientScreenDim);
        //Start sending events to the client
    }
}
