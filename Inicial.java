package trabalho.almir;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import trabalho.almir.telas.TelaAltCategoria;
import trabalho.almir.telas.TelaAltProduto;
import trabalho.almir.telas.TelaCadCategoria;
import trabalho.almir.telas.TelaCadProduto;
import trabalho.almir.telas.TelaExcCategoria;
import trabalho.almir.telas.TelaExclProduto;
import trabalho.almir.telas.TelaIndex;


@SuppressWarnings("serial")
public class Inicial extends JFrame {

	private Container caixa; //QUADRO INVISIVEL ONDE ABRIGAR� OS COMPONENTES
	private JMenuItem miSair, miCadCat, miEdCat, miExCat; 
	private JMenuItem miCadProduto, miEdProduto, miExProd;

	
	public Inicial(){
		componentes();//INICIALIZA��O DOS COMPONENTES NA TELA
		eventos();//INICIALIZA��O DOS EVENTOS A SEREM EXECUTADOS
	}
	
	private void componentes() {
		setTitle("Gerenciamento de Estoque - Trabalho Prof. Almir");
				//x y  larg alt
		setBounds(0,0, 800, 600);
		setResizable(false);
		caixa = getContentPane();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuArquivo = new JMenu("Arquivo");
		menuArquivo.setMnemonic('A');  //atalho para executar um icone do menu juntamente a tecla Alt
		menuBar.add(menuArquivo);
		
		miSair = new JMenuItem("Sair");
		miSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		miSair.setToolTipText("Sair do Sistema");
		menuArquivo.add(miSair);
		
		JMenu menuCategoria = new JMenu("Categoria");
		menuCategoria.setMnemonic('C');
		menuBar.add(menuCategoria);
		
		miCadCat = new JMenuItem("Cadastrar Categoria");
		miCadCat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		miCadCat.setToolTipText("Cadastra uma nova Categoria no Sistema");
		menuCategoria.add(miCadCat);
		
		miEdCat = new JMenuItem("Editar Categoria");
		miEdCat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		miEdCat.setToolTipText("Editar uma Categoria no Sistema");
		menuCategoria.add(miEdCat);
		
		miExCat = new JMenuItem("Excluir Categoria");
		miExCat.setToolTipText("Excluir uma Categoria do Sistema");
		menuCategoria.add(miExCat);
		
		
		JMenu menuProduto = new JMenu("Produto");
		menuProduto.setMnemonic('P');
		menuBar.add(menuProduto);
		
		
		miCadProduto = new JMenuItem("Cadastrar Produto");
		miCadProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
		miCadProduto.setToolTipText("Cadastrar um novo Produto no Sistema");
		menuProduto.add(miCadProduto);
		
		miEdProduto = new JMenuItem("Editar Produto");
		miEdProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
		miEdProduto.setToolTipText("Editar um Produto no Sistema");
		menuProduto.add(miEdProduto);
		
		miExProd = new JMenuItem("Excluir Produto");
		miExProd.setToolTipText("Excluir um Produto no Sistema");
		menuProduto.add(miExProd);
		
		TelaIndex boasVindas = new TelaIndex();
		caixa.removeAll();
		caixa.add(boasVindas);
		caixa.validate();
		
	}

	private void eventos() {
		miCadCat.addActionListener(new ActionListener() {// � o disparo do click do mouse
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TelaCadCategoria telaCadCategoria = new TelaCadCategoria();
				caixa.removeAll();
				caixa.add(telaCadCategoria);
				caixa.validate();
				
				
			}
		});
		
		miEdCat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TelaAltCategoria altCategoria = new TelaAltCategoria();
				caixa.removeAll();
				caixa.add(altCategoria);
				caixa.validate();
			}
		});
		
		
		miExCat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaExcCategoria telaExcCategoria = new TelaExcCategoria();
				caixa.removeAll();
				caixa.add(telaExcCategoria);
				caixa.validate();
			}
		});
		
		miCadProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TelaCadProduto telaCadProduto = new TelaCadProduto();
				caixa.removeAll();
				caixa.add(telaCadProduto);
				caixa.validate();
			}
		});
		
		miEdProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TelaAltProduto telaAltProduto = new TelaAltProduto();
				caixa.removeAll();
				caixa.add(telaAltProduto);
				caixa.validate(); 
			}
		});
		
		miExProd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaExclProduto telaExclProduto = new TelaExclProduto();
				caixa.removeAll();
				caixa.add(telaExclProduto);
				caixa.validate();
			}
		});
		miSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}
	
	
	 public static void main(String[] args)
     {
		 Inicial frame = new Inicial();
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
         frame.setLocation((tela.width - frame.getSize().width)/2, (tela.height - frame.getSize().height)/2);
         frame.setVisible(true);
          
     }

}
