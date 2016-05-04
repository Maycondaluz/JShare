package br.maycon.jshare.menu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.dagostini.exemplos.LeituraEscritaDeArquivos;
import br.dagostini.exemplos.LerIp;
import br.dagostini.exemplos.ListarDiretoriosArquivos;
import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;
import br.maycon.tabela.TableClienteArquivo;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuClienteServ extends JFrame implements IServer {

	private JPanel contentPane;
	private JTextField txt_user;
	private JTextField txt_IPServidor;
	private JTextField txt_portaServidor;
	private JTextField txt_arquivo;
	private JTable table_peguisa;
	private JLabel txt_meuIp;
	private JTextArea txtA_servicoLocal;
	private TableClienteArquivo tableModel;

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
		gbl_contentPane.columnWidths = new int[] { 102, 138, 0, 13, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 15, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
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

		txt_arquivo = new JTextField();
		GridBagConstraints gbc_txt_arquivo = new GridBagConstraints();
		gbc_txt_arquivo.insets = new Insets(0, 0, 5, 0);
		gbc_txt_arquivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_arquivo.gridx = 4;
		gbc_txt_arquivo.gridy = 1;
		contentPane.add(txt_arquivo, gbc_txt_arquivo);
		txt_arquivo.setColumns(10);

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

		bt_pesquisar = new JButton("Pesquisar arquivo");

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

		bt_conectar = new JButton("Conectar no servidor");

		GridBagConstraints gbc_bt_conectar = new GridBagConstraints();
		gbc_bt_conectar.insets = new Insets(0, 0, 5, 5);
		gbc_bt_conectar.gridx = 2;
		gbc_bt_conectar.gridy = 3;
		contentPane.add(bt_conectar, gbc_bt_conectar);

		bt_baixar = new JButton("baixar arquivo");

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

		JScrollPane scrollPaneTable = new JScrollPane();
		GridBagConstraints gbc_table_pesquisa = new GridBagConstraints();
		gbc_table_pesquisa.insets = new Insets(0, 0, 5, 0);
		gbc_table_pesquisa.fill = GridBagConstraints.BOTH;
		gbc_table_pesquisa.gridx = 4;
		gbc_table_pesquisa.gridy = 4;
		contentPane.add(scrollPaneTable, gbc_table_pesquisa);

		table_peguisa = new JTable();
		scrollPaneTable.setViewportView(table_peguisa);

		bt_encerraConComServidor = new JButton("Desconectar do servidor");

		GridBagConstraints gbc_bt_encerraConComServidor = new GridBagConstraints();
		gbc_bt_encerraConComServidor.anchor = GridBagConstraints.EAST;
		gbc_bt_encerraConComServidor.insets = new Insets(0, 0, 0, 5);
		gbc_bt_encerraConComServidor.gridx = 1;
		gbc_bt_encerraConComServidor.gridy = 5;
		contentPane.add(bt_encerraConComServidor, gbc_bt_encerraConComServidor);

		bt_encerraServico = new JButton("Encerrar servi\u00E7o");

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

		configura();
	}

	private static String IPservidorFixo = null;

	private static String PORTAservidorFixo = null;

	private Map<String, Cliente> mapaClientes = new HashMap<>();

	private Map<Cliente, List<Arquivo>> resultMapArquivos = new HashMap<>();

	private IServer servico = null;

	private Cliente cliente = null;

	private int contErros = 0;

	// ============================================================================
	// Métodos criados
	// ============================================================================

	private void mostrarMeuIP() {
		txt_meuIp.setText("Meu IP: " + this.meuIP + " - Portar: " + this.MinhaPorta);
		txt_meuIp.setForeground(Color.BLUE);
	}

	private void configura() {
		mostrarMeuIP();

		bt_conectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conectar(txt_IPServidor.getText().trim(), txt_portaServidor.getText().trim());
			}
		});
		bt_encerraConComServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		bt_encerraServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		bt_pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		bt_baixar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

	protected void conectar(String hostServidor, String portaServidor) {
		try {
			if (!hostServidor.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
				throw new RuntimeException("O endereço de ip parece invalido!");
			}

			if (!portaServidor.matches("[0-9]+") || portaServidor.length() > 5) {
				throw new RuntimeException("A porta deve ser um valor numérico de no máximo 5 dígitos!");
			}
			int porta = Integer.parseInt(portaServidor);
			if (porta < 1024 || porta > 65535) {
				throw new RuntimeException("A porta deve estar entre os valores de 1024 à  65535!");
			}

			instaciarClient();

			servico = (IServer) Naming.lookup("rmi://" + hostServidor + ":" + porta + "/" + IServer.NOME_SERVICO);

			servico.registrarCliente(cliente);
			servico.publicarListaArquivos(cliente, new ListarDiretoriosArquivos().listarArquivos());

			bt_pesquisar.setEnabled(true);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					e.getMessage() + "\n\n-------------------------------------------------------\n"
							+ "ERRO: VERIFIQUE SE O SERVIDOR JÁ NÃO ESTÁ RODANDO, SE A PORTA NÃO ESTÁ SENDO UTILIZADA"
							+ " E SE NÃO HÁ BLOQUEIO DE FIREWALL OU ANTIVIRUS.\n"
							+ "-------------------------------------------------------------------\n\n");
			if (contErros < 2) {
				JOptionPane.showMessageDialog(this, "Reconectando ao servidor");
				conectar(IPservidorFixo, PORTAservidorFixo);
				contErros++;
			} else {
				JOptionPane.showMessageDialog(this, "Não é possivél se reconectando ao servidor");
				contErros = 0;
			}
		}

	}

	private void instaciarClient() throws RuntimeException {
		String user = txt_user.getText().trim();
		if (user.length() == 0) {
			throw new RuntimeException("Digite um nome!");
		}
		if (cliente == null) {
			if (IPservidorFixo == null || PORTAservidorFixo == null) {
				IPservidorFixo = txt_IPServidor.getText();
				PORTAservidorFixo = txt_portaServidor.getText();
			}
			cliente = new Cliente();
			cliente.setNome(user);
			cliente.setIp(this.meuIP);
			cliente.setPorta(this.MinhaPorta);
		} else {
			cliente.setNome(user);
			cliente.setIp(this.meuIP);
			cliente.setPorta(this.MinhaPorta);
		}
	}

	protected void encerrarConexão() {
		try {
			for (int i = 0; i < mapaClientes.size(); i++) {
				desconectar(mapaClientes.get(i));
			}
			if (servico != null)
				servico.desconectar(cliente);
			if (servidorServ != null) {
				encerrarServidor();
			}
		} catch (RemoteException e1) {
			return;
		}
	}

	public void carregarTabela() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				tableModel = new TableClienteArquivo();
				table_peguisa.setModel(tableModel);
			}
		}).start();
	}

	protected void pesquisar() {
		try {
			table_peguisa.setModel(tableModel.atualizarLista(servico.procurarArquivo(txt_arquivo.getText().trim())));
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, "Erro ao pesquisar, ou conexão com o servidor caiu");
			if (contErros < 2) {
				JOptionPane.showMessageDialog(this, "Reconectando ao servidor atual");
				conectar(IPservidorFixo, PORTAservidorFixo);
				contErros++;
			}else{
				JOptionPane.showMessageDialog(this, "Não pode estabeler um conexão com o servidor");
				contErros=0;
			}
		}
	}

	// =============================================================================
	// Inicialização das variavéis para mostrar no console do servidor do
	// cliente
	// =============================================================================

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm:ss:SSS");

	private Map<String, Cliente> mapClientServ = new HashMap<>();

	private Map<Cliente, List<Arquivo>> mapArqServ = new HashMap<>();

	private IServer servidorServ;

	private Registry registryClientServ;
	private JButton bt_conectar;
	private JButton bt_encerraConComServidor;
	private JButton bt_encerraServico;
	private JButton bt_pesquisar;
	private JButton bt_baixar;

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

	protected void messageServidorEncerrado() {
		mostrar("Serviço encerrado, todos desconectados!");
	}

	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		mostrar("Cliente \"" + c.getNome().toUpperCase() + "\", com ip:" + c.getIp() + " se conectou.");
		mapClientServ.put(c.getIp(), c);
	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		for (Arquivo arquivo : lista) {
			mostrar("Cliente:" + c.getNome().toLowerCase() + "\n Publico arq: " + arquivo.getNome() + " : "
					+ arquivo.getTamanho());
		}
		mapArqServ.put(c, lista);
	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		Map<Cliente, List<Arquivo>> resultMapArq = new HashMap<>();
		for (Map.Entry<Cliente, List<Arquivo>> entry : mapArqServ.entrySet()) {
			List<Arquivo> listArq = new ArrayList<>();
			for (Arquivo arq : entry.getValue()) {
				if (arq.getNome().equals(nome)) {
					listArq.add(arq);
				}
			}
			if (listArq.size() > 0) {
				resultMapArq.put(entry.getKey(), listArq);
			}
		}
		return resultMapArq;
	}

	@Override
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException {
		File file = new File(".\\Share\\UpLoad\\" + arq.getNome());
		byte[] dados = new LeituraEscritaDeArquivos().leia(file);
		mostrar("Feito dowload do -> " + arq.getNome());
		return dados;
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		mapClientServ.remove(c);
		mapArqServ.remove(c);
		mostrar("Cliente: " + c.getNome().toUpperCase() + " desconectado!");
	}

}
