package app.constant;

import java.awt.Color;

//import javax.swing.JButton;
import javax.swing.UIManager;

public class SystemColors {
	public static final Color MOUSEPRESSED = Color.CYAN;
	public static final Color MOUSERELEASED = Color.BLUE;
	public static final Color MOUSEENTERED = Color.BLUE;
	//public static final Color MOUSEEXITED =  new JButton().getBackground();
	public static final Color MOUSEEXITED =  UIManager.getColor("Panel.background");
}
