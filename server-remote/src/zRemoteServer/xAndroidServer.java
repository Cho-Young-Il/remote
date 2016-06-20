package zRemoteServer;

public class xAndroidServer {
	public void procedureStart() {
		Thread socketCheck = new Thread(new xSocketCheck());
		Thread screenSend = new Thread(new sScreenSend());
		Thread command = new Thread(new xCommandReceive());
		Thread rectReceive = new Thread(new xDeviceRectReceive());
		Thread keyboardReceive = new Thread(new xKeyboardReceive());
		
		socketCheck.start();
		screenSend.start();
		command.start();
		rectReceive.start();
		keyboardReceive.start();
	}
}