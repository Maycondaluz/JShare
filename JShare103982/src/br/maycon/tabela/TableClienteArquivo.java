package br.maycon.tabela;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;

public class TableClienteArquivo extends AbstractTableModel{

	private List<ObjClienteArquivo> lista = new ArrayList<>();
	
	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ObjClienteArquivo res = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return res.getArquivo().getNome();
		case 1:
			return res.getArquivo().getTamanho();
		case 2:
			return res.getCliente().getNome();
		case 3:
			return res.getCliente().getIp();
		case 4:
			return res.getCliente().getPorta();
		default:
			return"";
		}
	}

	@Override
	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Arquivo";
		case 1:
			return "Tamanho";
		case 2:
			return "Cliente";
		case 3:
			return "IP";
		case 4:
			return "Porta";
		default:
			return"";
		}
	}
	
	public TableClienteArquivo atualizarLista(Map<Cliente, List<Arquivo>> lista){
		this.lista.removeAll(this.lista);
		for(Map.Entry<Cliente, List<Arquivo>> entry: lista.entrySet()){
			for(Arquivo aq : entry.getValue()){
				ObjClienteArquivo obj = new ObjClienteArquivo();
				obj.getCliente().setNome(entry.getKey().getNome());
				obj.getCliente().setIp(entry.getKey().getIp());
				obj.getCliente().setPorta(entry.getKey().getPorta());
				obj.getArquivo().setNome(aq.getNome());
				obj.getArquivo().setTamanho(aq.getTamanho());
				this.lista.add(obj);
			}
		}
		this.fireTableStructureChanged();
		return this;
	}

}
