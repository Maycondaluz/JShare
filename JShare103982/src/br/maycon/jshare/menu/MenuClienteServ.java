package br.maycon.jshare.menu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.dagostini.exemplos.LerIp;
import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTable;
import java.awt.Font;

public class MenuClienteServ extends JFrame implements IServer{

	private JPanel contentPane;
	private JTextField txt_user;
	private JTextField txt_IPServidor;
	private JTextField txt_portaServidor;
	private JTextField textField_3;
	private JTable table;
	
	private final String meuIP = new LerIp().retornarIp();
	private final int MinhaPorta = 1818;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuClienteServ frame = new MenuClienteServ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuClienteServ() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 812, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{102, 138, 0, 13, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 15, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		txt_meuIp = new JLabel("New label");
		txt_meuIp.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_txt_meuIp = new GridBagConstraints();
		gbc_txt_meuIp.gridwidth = 3;
		gbc_txt_meuIp.insets = new Insets(0, 0, 5, 5);
		gbc_txt_meuIp.gridx = 0;
		gbc_txt_meuIp.gridy = 0;
		contentPane.add(txt_meuIp, gbc_txt_meuIp);
		
		JLabel lblDigiteONome = new JLabel("Digite o nome do arquivo:");
		lblDigiteONome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_lblDigiteONome = new GridBagConstraints();
		gbc_lblDigiteONome.anchor = GridBagConstraints.WEST;
		gbc_lblDigiteONome.insets = new Insets(0, 0, 5, 0);
		gbc_lblDigiteONome.gridx = 4;
		gbc_lblDigiteONome.gridy = 0;
		contentPane.add(lblDigiteONome, gbc_lblDigiteONome);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		GridBagConstraints gbc_lblUsurio = new GridBagConstraints();
		gbc_lblUsurio.anchor = GridBagConstraints.EAST;
		gbc_lblUsurio.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsurio.gridx = 0;
		gbc_lblUsurio.gridy = 1;
		contentPane.add(lblUsurio, gbc_lblUsurio);
		
		txt_user = new JTextField();
		GridBagConstraints gbc_txt_user = new GridBagConstraints();
		gbc_txt_user.insets = new Insets(0, 0, 5, 5);
		gbc_txt_user.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_user.gridx = 1;
		gbc_txt_user.gridy = 1;
		contentPane.add(txt_user, gbc_txt_user);
		txt_user.setColumns(10);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 4;
		gbc_textField_3.gridy = 1;
		contentPane.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblIpDoServidor = new JLabel("IP do servidor:");
		GridBagConstraints gbc_lblIpDoServidor = new GridBagConstraints();
		gbc_lblIpDoServidor.anchor = GridBagConstraints.EAST;
		gbc_lblIpDoServidor.insets = new Insets(0, 0, 5, 5);
		gbc_lblIpDoServidor.gridx = 0;
		gbc_lblIpDoServidor.gridy = 2;
		contentPane.add(lblIpDoServidor, gbc_lblIpDoServidor);
		
		txt_IPServidor = new JTextField();
		GridBagConstraints gbc_txt_IPServidor = new GridBagConstraints();
		gbc_txt_IPServidor.insets = new Insets(0, 0, 5, 5);
		gbc_txt_IPServidor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_IPServidor.gridx = 1;
		gbc_txt_IPServidor.gridy = 2;
		contentPane.add(txt_IPServidor, gbc_txt_IPServidor);
		txt_IPServidor.setColumns(10);
		
		JButton bt_pesquisar = new JButton("Pesquisar arquivo");
		GridBagConstraints gbc_bt_pesquisar = new GridBagConstraints();
		gbc_bt_pesquisar.fill = GridBagConstraints.HORIZONTAL;
		gbc_bt_pesquisar.insets = new Insets(0, 0, 5, 0);
		gbc_bt_pesquisar.gridx = 4;
		gbc_bt_pesquisar.gridy = 2;
		contentPane.add(bt_pesquisar, gbc_bt_pesquisar);
		
		JLabel lblPortaDoServidor = new JLabel("Porta do servidor:");
		GridBagConstraints gbc_lblPortaDoServidor = new GridBagConstraints();
		gbc_lblPortaDoServidor.anchor = GridBagConstraints.EAST;
		gbc_lblPortaDoServidor.insets = new Insets(0, 0, 5, 5);
		gbc_lblPortaDoServidor.gridx = 0;
		gbc_lblPortaDoServidor.gridy = 3;
		contentPane.add(lblPortaDoServidor, gbc_lblPortaDoServidor);
		
		txt_portaServidor = new JTextField();
		GridBagConstraints gbc_txt_portaServidor = new GridBagConstraints();
		gbc_txt_portaServidor.insets = new Insets(0, 0, 5, 5);
		gbc_txt_portaServidor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_portaServidor.gridx = 1;
		gbc_txt_portaServidor.gridy = 3;
		contentPane.add(txt_portaServidor, gbc_txt_portaServidor);
		txt_portaServidor.setColumns(10);
		
		JButton bt_conectar = new JButton("Conectar no servidor");
		GridBagConstraints gbc_bt_conectar = new GridBagConstraints();
		gbc_bt_conectar.insets = new Insets(0, 0, 5, 5);
		gbc_bt_conectar.gridx = 2;
		gbc_bt_conectar.gridy = 3;
		contentPane.add(bt_conectar, gbc_bt_conectar);
		
		JButton bt_baixar = new JButton("baixar arquivo");
		bt_baixar.setToolTipText("selecione um linha da pesquisa pra download");
		GridBagConstraints gbc_bt_baixar = new GridBagConstraints();
		gbc_bt_baixar.fill = GridBagConstraints.HORIZONTAL;
		gbc_bt_baixar.insets = new Insets(0, 0, 5, 0);
		gbc_bt_baixar.gridx = 4;
		gbc_bt_baixar.gridy = 3;
		contentPane.add(bt_baixar, gbc_bt_baixar);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		txtA_servicoLocal = new JTextArea();
		scrollPane.setViewportView(txtA_servicoLocal);
		
		JSeparator separator = new JSeparator();
		separator.setRequestFocusEnabled(false);
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridheight = 5;
		gbc_separator.insets = new Insets(0, 0, 0, 5);
		gbc_separator.gridx = 3;
		gbc_separator.gridy = 1;
		contentPane.add(separator, gbc_separator);
		
		JScrollPane table_pesquisa = new JScrollPane();
		GridBagConstraints gbc_table_pesquisa = new GridBagConstraints();
		gbc_table_pesquisa.insets = new Insets(0, 0, 5, 0);
		gbc_table_pesquisa.fill = GridBagConstraints.BOTH;
		gbc_table_pesquisa.gridx = 4;
		gbc_table_pesquisa.gridy = 4;
		contentPane.add(table_pesquisa, gbc_table_pesquisa);
		
		table = new JTable();
		table_pesquisa.setViewportView(table);
		
		JButton bt_encerraConComServidor = new JButton("Desconectar do servidor");
		GridBagConstraints gbc_bt_encerraConComServidor = new GridBagConstraints();
		gbc_bt_encerraConComServidor.anchor = GridBagConstraints.EAST;
		gbc_bt_encerraConComServidor.insets = new Insets(0, 0, 0, 5);
		gbc_bt_encerraConComServidor.gridx = 1;
		gbc_bt_encerraConComServidor.gridy = 5;
		contentPane.add(bt_encerraConComServidor, gbc_bt_encerraConComServidor);
		
		JButton bt_encerraServico = new JButton("Encerrar servi\u00E7o");
		GridBagConstraints gbc_bt_encerraServico = new GridBagConstraints();
		gbc_bt_encerraServico.anchor = GridBagConstraints.EAST;
		gbc_bt_encerraServico.insets = new Insets(0, 0, 0, 5);
		gbc_bt_encerraServico.gridx = 2;
		gbc_bt_encerraServico.gridy = 5;
		contentPane.add(bt_encerraServico, gbc_bt_encerraServico);
		
		JButton bt_sair = new JButton("Sair");
		GridBagConstraints gbc_bt_sair = new GridBagConstraints();
		gbc_bt_sair.anchor = GridBagConstraints.EAST;
		gbc_bt_sair.gridx = 4;
		gbc_bt_sair.gridy = 5;
		contentPane.add(bt_sair, gbc_bt_sair);
		
		mostrarMeuIP();
	}

	private void mostrarMeuIP(){
		txt_meuIp.setText("Meu IP: " + this.meuIP + " - Portar: " + this.MinhaPorta);
		txt_meuIp.setForeground(Color.BLUE);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//=============================================================================
	//Inicialização das variavéis para mostrar no console do servidor do cliente
	//=============================================================================
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm:ss:SSS");

	private Map<String, Cliente> mapClientServ = new HashMap<>();

	private Map<Cliente, List<Arquivo>> mapArqServ = new HashMap<>();

	private IServer servidorServ;

	private Registry registryClientServ;
	private JLabel txt_meuIp;
	private JTextArea txtA_servicoLocal;
	
	protected void iniciarServidor() {
		try {
			servidorServ = (IServer) UnicastRemoteObject.exportObject(this, 0);
			registryClientServ = LocateRegistry.createRegistry(this.MinhaPorta);
			registryClientServ.rebind(IServer.NOME_SERVICO, servidorServ);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage()
					+ "\n\nErro ao iniciar serviço, verifique se a porta, já está sendo usada ou se firewall está bloqueando.");
		}
	}

	protected void encerrarServidor() {
		try {
			UnicastRemoteObject.unexportObject(this, true);
			UnicastRemoteObject.unexportObject(registryClientServ, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void mostrar(String string) {
		txtA_servicoLocal.append(sdf.format(new Date()));
		txtA_servicoLocal.append(" -> ");
		txtA_servicoLocal.append(string);
		txtA_servicoLocal.append("\n");
	}
	
	protected void messageServidorEncerrado(){
		mostrar("Serviço encerrado, todos desconectados!");
	}
	
	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		mostrar("Cliente \""+c.getNome().toUpperCase() + "\", com ip:" + c.getIp() + " se conectou.");
		mapClientServ.put(c.getIp(), c);
	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		for (Arquivo arquivo : lista) {
			mostrar("Cliente:" + c.getNome().toLowerCase() + "\n Publico arq: " + arquivo.getNome() + " : " + arquivo.getTamanho());
		}
		mapArqServ.put(c, lista);
	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	
}
