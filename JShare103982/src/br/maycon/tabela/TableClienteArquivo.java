package br.maycon.tabela;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableClienteArquivo extends AbstractTableModel{

	private List<ObjClienteArquivo> lista = new ArrayList<>();
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch (columnIndex) {
		case 0:
			return "Cliente";
		case 1:
			return "IP";
		case 2:
			return "Porta";
		case 3:
			return "Arquivo";
		case 4:
			return "Tamanho";
		default:
			return"";
		}
	}

	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Cliente";
		case 1:
			return "IP";
		case 2:
			return "Porta";
		case 3:
			return "Arquivo";
		case 4:
			return "Tamanho";
		default:
			return"";
		}
	}
	
	

}
