package zRemoteServer;

public class wWindowsServer {
	public void procedureStart() {
		Thread screenSend = new Thread(new sScreenSend());
		Thread rectSend = new Thread(new wSizeSend());
		Thread sd = new Thread(new wServerDelegate());
		
		screenSend.start();
		rectSend.start();
		sd.start();
	}
}