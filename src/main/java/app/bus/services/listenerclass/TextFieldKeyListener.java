package app.bus.services.listenerclass;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import app.table.JTableUnEdit;

public class TextFieldKeyListener implements KeyListener{

	private JTextField txt;
	private JTable table;
	public TextFieldKeyListener(JTextField txt, JTable table) {
		this.txt = txt;
		this.table = table;
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((JTableUnEdit) table.getModel())); 
		    sorter.setRowFilter(RowFilter.regexFilter(txt.getText()));

		    table.setRowSorter(sorter);
		}
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
