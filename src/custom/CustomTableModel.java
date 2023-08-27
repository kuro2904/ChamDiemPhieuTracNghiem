package custom;

import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 640454475446436926L;

	private Object[][] data;
	private List<String> columnNames;

	public CustomTableModel() {
	}

	public CustomTableModel(Object[][] data, List<String> columnNames) {
		this.data = data;
		this.columnNames = columnNames;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		data[rowIndex][columnIndex] = value;
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	
}
