package trabalho.almir.telas;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import trabalho.almir.classes.CategoriaDAO;
import trabalho.almir.classes.Conexao;
import trabalho.almir.classes.ProdutoDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class TelaAltProduto extends JPanel {
	private JTextField tfId;
	private JTextField tfNomePro;
	private JTextField tfMarcaPro;
	private JTextField tfValUni;
	private JTextField tfQuant;
	@SuppressWarnings("rawtypes")
	private JComboBox cbCatPro;
	private Conexao conexao;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private JButton btnAlterar, btnLimpar, btnBuscar;
	/**
	 * Create the panel.
	 */
	public TelaAltProduto() {
		
		componetes();
		mostrarCategorias();
		eventos();
		
	}

	@SuppressWarnings("rawtypes")
	private void componetes() {
		setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblId.setBounds(50, 75, 46, 14);
		add(lblId);
		
		tfId = new JTextField();
		tfId.setBounds(50, 95, 174, 30);
		add(tfId);
		tfId.setColumns(10);
		
		JLabel lblNo = new JLabel("NOME DO PRODUTO:");
		lblNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNo.setBounds(50, 145, 200, 30);
		add(lblNo);
		
		tfNomePro = new JTextField();
		tfNomePro.setBounds(50, 175, 566, 30);
		add(tfNomePro);
		tfNomePro.setColumns(10);
		
		JLabel lblMarcaDoProduto = new JLabel("MARCA DO PRODUTO:");
		lblMarcaDoProduto.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMarcaDoProduto.setBounds(50, 220, 272, 30);
		add(lblMarcaDoProduto);
		
		tfMarcaPro = new JTextField();
		tfMarcaPro.setBounds(50, 250, 236, 30);
		add(tfMarcaPro);
		tfMarcaPro.setColumns(10);
		
		JLabel lblCategoriaDoProduto = new JLabel("CATEGORIA DO PRODUTO:");
		lblCategoriaDoProduto.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategoriaDoProduto.setBounds(366, 220, 250, 30);
		add(lblCategoriaDoProduto);
		
		cbCatPro = new JComboBox();
		cbCatPro.setBounds(366, 250, 250, 30);
		add(cbCatPro);
		
		JLabel lblNewLabel = new JLabel("QUANTIDADE:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(50, 305, 133, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("VALOR UNITÁRIO:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(50, 375, 162, 20);
		add(lblNewLabel_1);
		
		tfValUni = new JTextField();
		tfValUni.setBounds(50, 400, 162, 30);
		add(tfValUni);
		tfValUni.setColumns(10);
		
		tfQuant = new JTextField();
		tfQuant.setBounds(50, 325, 200, 30);
		add(tfQuant);
		tfQuant.setColumns(10);
		
		btnAlterar = new JButton("ALTERAR DADOS");
		btnAlterar.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnAlterar.setBounds(171, 479, 200, 53);
		add(btnAlterar);
		
		btnLimpar = new JButton("LIMPAR");
		btnLimpar.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnLimpar.setBounds(403, 479, 150, 53);
		add(btnLimpar);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnBuscar.setBounds(250, 95, 133, 30);
		add(btnBuscar);
		
		JLabel lblAlteraDeProduto = new JLabel("Alteração de Produto");
		lblAlteraDeProduto.setFont(new Font("Dialog", Font.BOLD, 23));
		lblAlteraDeProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlteraDeProduto.setBounds(0, 22, 800, 57);
		add(lblAlteraDeProduto);
	}

	private void eventos() {
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limparDados();
				if(!tfId.getText().trim().isEmpty()){
					ProdutoDAO produtoDAO = new ProdutoDAO();
					CategoriaDAO categoriaDAO = new CategoriaDAO();
					String nomeCategoria = null;
					try{
					produtoDAO.listarPorCodigo(Integer.parseInt(tfId.getText()));
					tfMarcaPro.setText(produtoDAO.produto.getMarca());
					tfNomePro.setText(produtoDAO.produto.getNome());
					tfQuant.setText(String.valueOf(produtoDAO.produto.getQuantidade()));
					tfValUni.setText(String.valueOf(produtoDAO.produto.getVlrUnidade()));
					
					categoriaDAO.listarPorCodigo(produtoDAO.produto.getCodigoCategoria());
					nomeCategoria = categoriaDAO.categoria.getNome();
					cbCatPro.setSelectedItem(String.valueOf(nomeCategoria));
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, "Produto não Encontrado");
					}
					}
			}
		});
		
		btnAlterar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!tfId.getText().trim().isEmpty() || !tfMarcaPro.getText().trim().isEmpty() ||
						!tfNomePro.getText().trim().isEmpty() || !tfQuant.getText().trim().isEmpty() ||
						!tfValUni.getText().trim().isEmpty()){
				CategoriaDAO categoriaDAO = new CategoriaDAO();
				ProdutoDAO produtoDAO = new ProdutoDAO();
				int codigoCategoria = -1;
				try{
					produtoDAO.produto.setCodigo(Integer.parseInt(tfId.getText()));
					produtoDAO.produto.setMarca(tfMarcaPro.getText());
					produtoDAO.produto.setNome(tfNomePro.getText());
					produtoDAO.produto.setQuantidade(Integer.parseInt(tfQuant.getText()));
					produtoDAO.produto.setVlrUnidade(Double.parseDouble(tfValUni.getText()));
					
					
					categoriaDAO.listarPorCodigo(String.valueOf(cbCatPro.getSelectedItem()));
					codigoCategoria = categoriaDAO.categoria.getCodigo();
					
					produtoDAO.produto.setCodigoCategoria(codigoCategoria);
					
					int n = JOptionPane.showConfirmDialog(null, "Deseja Realmente Alterar o Produto: " 
							+ tfNomePro.getText() + " ?" );
							if(n == JOptionPane.YES_NO_OPTION){
								produtoDAO.editar();
								JOptionPane.showMessageDialog(null, tfNomePro.getText() + " Alterado com Sucesso!");
								limparCampos();
							}
					
				}catch(Exception ex){
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao Altetrar o Produto");
					limparCampos();
				}
				
			}
			}
		});
		
		btnLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		
	}
	

	private void limparCampos() {
		tfId.setText("");
		tfMarcaPro.setText("");
		tfNomePro.setText("");
		tfQuant.setText("");
		tfValUni.setText("");
		cbCatPro.setSelectedIndex(0);
	}
	

	private void limparDados() {
		tfMarcaPro.setText("");
		tfNomePro.setText("");
		tfQuant.setText("");
		tfValUni.setText("");
		cbCatPro.setSelectedIndex(0);
	
	}

	@SuppressWarnings("unchecked")
	private void mostrarCategorias() {
		conexao = new Conexao();
		if(conexao.getConnection()){
			try{
				String sql = "SELECT * FROM categoria";
				statement = conexao.connection.prepareStatement(sql);
				resultSet = statement.executeQuery();
				while(resultSet.next()){
					cbCatPro.addItem(resultSet.getString(2));
				}
			}catch(Exception ex){
				//ex.printStackTrace();
				cbCatPro.addItem("Nenhuma Categoria Cadastrada");
			}finally{
				conexao.close();
			}
			
		}
	}
}
