package app.bus.services.listenerclass;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import app.constant.SystemColors;

public class PanelUserMouseClickListener extends MouseAdapter{
	private JPanel panel;
	public PanelUserMouseClickListener(JPanel panel) {
		this.panel = panel;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		panel.setBackground(SystemColors.MOUSEPRESSED_USER);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		panel.setBackground(SystemColors.MOUSERELEASED_USER);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		panel.setBackground(SystemColors.MOUSEENTERED_USER);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		panel.setBackground(SystemColors.MOUSEEXITED_USER);
	}
}
