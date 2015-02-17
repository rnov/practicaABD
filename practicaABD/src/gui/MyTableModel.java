package gui;


import javax.swing.table.DefaultTableModel;

class MyTableModel extends DefaultTableModel {
    
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construct of the table model
	 * @param data of the table model
	 * @param name colum of the table model
	 */
	public  MyTableModel(Object[][] data, Object[] name) {
		super(data, name);
	}
	
	/**
	 * Make de cells not editable
	 */
	public boolean isCellEditable (int row, int column)	   {
	   return false;
	}
    
}
