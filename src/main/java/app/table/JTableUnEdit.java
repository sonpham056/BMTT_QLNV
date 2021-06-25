package app.table;

import javax.swing.table.DefaultTableModel;

public class JTableUnEdit extends DefaultTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTableUnEdit(Object[] objects, int i) {
		super(objects, i);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
