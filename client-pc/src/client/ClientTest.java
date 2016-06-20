package client;

import java.awt.BorderLayout;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClientTest {
    //Main server frame
    private JFrame frame = new JFrame("Client Program");
    private JDesktopPane desktop = new JDesktopPane();
    private static String ServerIP = null;
    public static void main(String args[]){
        ServerIP = JOptionPane.showInputDialog("Please enter server IP");
        new ClientTest().initialize(ServerIP);
    }

    public void initialize(String ServerIP){
            drawGUI();
            System.out.println("New client Connected to the server");
            new ClientHandler(desktop, ServerIP);  
    }
    public void drawGUI(){
            frame.add(desktop,BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //Show the frame in a maximized state
            frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
    }
}
