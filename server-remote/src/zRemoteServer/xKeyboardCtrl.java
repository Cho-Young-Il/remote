package zRemoteServer;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class xKeyboardCtrl {
	public static Robot robot = null;
	public xKeyboardCtrl() {
		try {
			robot = new Robot();
		} catch (AWTException e) { }
	}
	
	public void Key(String word) {
		switch (word) {
		case "Q" : Key_Press_Release(16, 81); break;	case "W" : Key_Press_Release(16, 87); break;
		case "E" : Key_Press_Release(16, 69); break;	case "R" : Key_Press_Release(16, 82); break;
		case "T" : Key_Press_Release(16, 84); break;	case "Y" : Key_Press_Release(16, 89); break;
		case "U" : Key_Press_Release(16, 85); break;	case "I" : Key_Press_Release(16, 73); break;
		case "O" : Key_Press_Release(16, 79); break;	case "P" : Key_Press_Release(16, 80); break;
		case "A" : Key_Press_Release(16, 65); break;	case "S" : Key_Press_Release(16, 83); break;
		case "D" : Key_Press_Release(16, 68); break;	case "F" : Key_Press_Release(16, 70); break;
		case "G" : Key_Press_Release(16, 71); break;	case "H" : Key_Press_Release(16, 72); break;
		case "J" : Key_Press_Release(16, 74); break;	case "K" : Key_Press_Release(16, 75); break;
		case "L" : Key_Press_Release(16, 76); break;	case "Z" : Key_Press_Release(16, 90); break;
		case "X" : Key_Press_Release(16, 88); break;	case "C" : Key_Press_Release(16, 67); break;
		case "V" : Key_Press_Release(16, 86); break;	case "B" : Key_Press_Release(16, 66); break;
		case "N" : Key_Press_Release(16, 78); break;	case "M" : Key_Press_Release(16, 77); break;//
		
		case "q" : Key_Press_Release(81); break;	case "w" : Key_Press_Release(87); break;
		case "e" : Key_Press_Release(69); break;	case "r" : Key_Press_Release(82); break;
		case "t" : Key_Press_Release(84); break;	case "y" : Key_Press_Release(89); break;
		case "u" : Key_Press_Release(85); break;	case "i" : Key_Press_Release(73); break;
		case "o" : Key_Press_Release(79); break;	case "p" : Key_Press_Release(80); break;
		case "a" : Key_Press_Release(65); break;	case "s" : Key_Press_Release(83); break;
		case "d" : Key_Press_Release(68); break;	case "f" : Key_Press_Release(70); break;
		case "g" : Key_Press_Release(71); break;	case "h" : Key_Press_Release(72); break;
		case "j" : Key_Press_Release(74); break;	case "k" : Key_Press_Release(75); break;
		case "l" : Key_Press_Release(76); break;	case "z" : Key_Press_Release(90); break;
		case "x" : Key_Press_Release(88); break;	case "c" : Key_Press_Release(67); break;
		case "v" : Key_Press_Release(86); break;	case "b" : Key_Press_Release(66); break;
		case "n" : Key_Press_Release(78); break;	case "m" : Key_Press_Release(77); break;//
		
		case "1" : Key_Press_Release(49); break;	case "2" : Key_Press_Release(50); break;
		case "3" : Key_Press_Release(51); break;	case "4" : Key_Press_Release(52); break;
		case "5" : Key_Press_Release(53); break;	case "6" : Key_Press_Release(54); break;
		case "7" : Key_Press_Release(55); break;	case "8" : Key_Press_Release(56); break;
		case "9" : Key_Press_Release(57); break;	case "0" : Key_Press_Release(48); break;
		case "-" : Key_Press_Release(45); break;	case "/" : Key_Press_Release(47); break;
		case ";" : Key_Press_Release(59); break;	case "s_j" : Key_Press_Release(92); break;
		case "." : Key_Press_Release(46); break;	case "," : Key_Press_Release(44); break;
		case "'" : Key_Press_Release(222); break;	case "(" : Key_Press_Release(16, 57); break;	
		case ")" : Key_Press_Release(16, 48); break;	case "&" : Key_Press_Release(16, 55); break;	
		case "@" : Key_Press_Release(16, 50); break;	case "s_z" : Key_Press_Release(16, 222); break;
		case "?" : Key_Press_Release(16, 47); break;	case "!" : Key_Press_Release(16, 49); break;
		case ":" : Key_Press_Release(16, 59); break;
		
		case "f1" : Key_Press_Release(112); break;	case "home" : Key_Press_Release(36); break;
		case "f2" : Key_Press_Release(113); break;	case "pgup" : Key_Press_Release(33); break;
		case "f3" : Key_Press_Release(114); break;	case "up" : Key_Press_Release(38); break;
		case "f4" : Key_Press_Release(115); break;	case "end" : Key_Press_Release(35); break;
		case "f5" : Key_Press_Release(116); break;	case "pgdn" : Key_Press_Release(34); break;
		case "f6" : Key_Press_Release(117); break;	case "h_ctrl" : Key_Press_Release(263); break;
		case "f7" : Key_Press_Release(118); break;	case "left" : Key_Press_Release(37); break;
		case "f8" : Key_Press_Release(119); break;	case "down" : Key_Press_Release(40); break;
		case "f9" : Key_Press_Release(120); break;	case "right" : Key_Press_Release(39); break;
		case "f10" : Key_Press_Release(121); break;	case "prtsc" : robot.keyPress(KeyEvent.VK_PRINTSCREEN);
																robot.keyRelease(KeyEvent.VK_PRINTSCREEN);break;
		case "f11" : Key_Press_Release(122); break;	case "f12" : Key_Press_Release(123); break;	
		
		case "[" : Key_Press_Release(91); break;	case "]" : Key_Press_Release(93); break;
		case "{" : Key_Press_Release(16, 91); break;	case "}" : Key_Press_Release(16, 93); break;
		case "#" : Key_Press_Release(16, 51); break;	case "%" : Key_Press_Release(16, 53); break;
		case "^" : Key_Press_Release(16, 54); break;	case "*" : Key_Press_Release(16, 56); break;
		case "+" : Key_Press_Release(16, 61); break;	case "=" : Key_Press_Release(61); break;
		case "_" : Key_Press_Release(16, 45); break;	case "|" : Key_Press_Release(16, 92); break;
		case "~" : Key_Press_Release(16, 192); break;	case "<" : Key_Press_Release(16, 44); break;
		case ">" : Key_Press_Release(16, 46); break;	case "$" : Key_Press_Release(16, 52); break;
		
		case "Esc" : Key_Press_Release(27); break;	case "Window" : Key_Press_Release(524); break;
		case "Ctrl" : Key_Press_Release(17); break;	case "Alt" : Key_Press_Release(18); break;
		
		case "Kor_eng_toggle" : Key_Press_Release(16, 32); break;
		
		case "LongBackSpace" : robot.keyPress(8); break;	case "LongSpace" : robot.keyPress(32); break;
		case "LongReturn" : robot.keyPress(10); break;		case "LongCtrl" : robot.keyPress(17); break;
		case "LongAlt" : robot.keyPress(18); break;			case "LongDel" : robot.keyPress(127); break;
		default : break;
	        
		}
	}

	private void Key_Press_Release(int first) {
		robot.keyPress(first);
		robot.keyRelease(first);
	}
	private void Key_Press_Release(int first, int second) {
		robot.keyPress(first);
		robot.keyPress(second);
		robot.keyRelease(first);
        robot.keyRelease(second);
	}
}