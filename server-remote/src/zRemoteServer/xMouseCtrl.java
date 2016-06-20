package zRemoteServer;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class xMouseCtrl {
	String Click = null;
	Robot robot = null;
	public xMouseCtrl() {
		try {
			robot = new Robot();
		} catch (AWTException e) { }
	}
	
	public xMouseCtrl(String Click) {
		this.Click = Click;
		try {
			this.robot = new Robot();
		} catch (AWTException e) { }
	}
	
	public void MouseMove() {
		double xScale = sScreenCapture.screen.getWidth()/xDeviceRectReceive.screenImgWidth;
	    double yScale = sScreenCapture.screen.getHeight()/xDeviceRectReceive.screenImgHeight;
		
	    int MousePositionX = (int) ((xDeviceRectReceive.mouseX+10) * xScale);
		int MousePositionY = (int) ((xDeviceRectReceive.mouseY+2) * yScale);
	    
	    robot.mouseMove(MousePositionX, MousePositionY);
	}
	
	public void MouseClick() {
		if(Click == "LClick") {
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}
		if(Click == "RClick") {
			robot.mousePress(InputEvent.BUTTON3_MASK);
			robot.mouseRelease(InputEvent.BUTTON3_MASK);
		}
		if(Click == "WheelUp") {
			robot.mouseWheel(-2);
		}
		if(Click == "WheelDown") {
			robot.mouseWheel(2);
		}
		if(Click == "DoubleClick") {
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}
		if(Click == "Drag") {
			robot.mousePress(InputEvent.BUTTON1_MASK);
		}
		if(Click == "DragEnd") {
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}
	}
}