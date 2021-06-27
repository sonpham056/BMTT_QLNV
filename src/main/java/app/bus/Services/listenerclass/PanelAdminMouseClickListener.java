package app.bus.services.listenerclass;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import app.constant.SystemColors;

public class PanelAdminMouseClickListener extends MouseAdapter{
	private JPanel panel;
	public PanelAdminMouseClickListener(JPanel panel) {
		this.panel = panel;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		panel.setBackground(SystemColors.MOUSEPRESSED);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		panel.setBackground(SystemColors.MOUSERELEASED);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		panel.setBackground(SystemColors.MOUSEENTERED);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		panel.setBackground(SystemColors.MOUSEEXITED);
	}
}
