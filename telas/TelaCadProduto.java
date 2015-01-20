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
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class TelaCadProduto extends JPanel {
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
	private JButton btnSalvar, btnLimpar;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("rawtypes")
	public TelaCadProduto() {
		setForeground(Color.WHITE);
		setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblId.setBounds(50, 70, 46, 14);
		add(lblId);
		
		tfId = new JTextField();
		tfId.setBounds(50, 90, 174, 30);
		add(tfId);
		tfId.setColumns(10);
		
		JLabel lblNo = new JLabel("NOME DO PRODUTO:");
		lblNo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNo.setBounds(50, 140, 256, 30);
		add(lblNo);
		
		tfNomePro = new JTextField();
		tfNomePro.setBounds(50, 170, 566, 30);
		add(tfNomePro);
		tfNomePro.setColumns(10);
		
		JLabel lblMarcaDoProduto = new JLabel("MARCA DO PRODUTO:");
		lblMarcaDoProduto.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMarcaDoProduto.setBounds(50, 215, 272, 30);
		add(lblMarcaDoProduto);
		
		tfMarcaPro = new JTextField();
		tfMarcaPro.setBounds(50, 245, 236, 30);
		add(tfMarcaPro);
		tfMarcaPro.setColumns(10);
		
		JLabel lblCategoriaDoProduto = new JLabel("CATEGORIA DO PRODUTO:");
		lblCategoriaDoProduto.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategoriaDoProduto.setBounds(366, 215, 286, 30);
		add(lblCategoriaDoProduto);
		
		cbCatPro = new JComboBox();
		cbCatPro.setBounds(366, 245, 250, 30);
		add(cbCatPro);
		
		JLabel lblNewLabel = new JLabel("QUANTIDADE:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(50, 300, 201, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("VALOR UNITÁRIO:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(50, 370, 162, 20);
		add(lblNewLabel_1);
		
		tfValUni = new JTextField();
		tfValUni.setBounds(50, 395, 162, 30);
		add(tfValUni);
		tfValUni.setColumns(10);
		
		tfQuant = new JTextField();
		tfQuant.setBounds(50, 320, 162, 30);
		add(tfQuant);
		tfQuant.setColumns(10);
		
		btnSalvar = new JButton("SALVAR DADOS");
		btnSalvar.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnSalvar.setBounds(210, 479, 201, 53);
		add(btnSalvar);
		
		btnLimpar = new JButton("LIMPAR");
		btnLimpar.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnLimpar.setBounds(435, 479, 150, 53);
		add(btnLimpar);
		
		JLabel lblCadastroDeProduto = new JLabel("Cadastro de Produto");
		lblCadastroDeProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeProduto.setFont(new Font("Dialog", Font.BOLD, 23));
		lblCadastroDeProduto.setBounds(0, 20, 800, 57);
		add(lblCadastroDeProduto);

		mostrarCategorias();
		eventos();
		
	}

	private void eventos() {
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!tfId.getText().trim().isEmpty() && !tfMarcaPro.getText().trim().isEmpty() &&
						!tfNomePro.getText().trim().isEmpty() && !tfQuant.getText().trim().isEmpty() &&
						!tfValUni.getText().trim().isEmpty()){
					CategoriaDAO categoriaDAO = new CategoriaDAO();
					ProdutoDAO produtoDAO = new ProdutoDAO();
					produtoDAO.produto.setCodigo(Integer.parseInt(tfId.getText()));
					produtoDAO.produto.setMarca(tfMarcaPro.getText());
					produtoDAO.produto.setNome(tfNomePro.getText());
					produtoDAO.produto.setQuantidade(Integer.parseInt(tfQuant.getText()));
					produtoDAO.produto.setVlrUnidade(Double.parseDouble(tfValUni.getText()));
					int codigoCategoria = -1;
					try{
						categoriaDAO.listarPorCodigo(String.valueOf(cbCatPro.getSelectedItem()));
						codigoCategoria = categoriaDAO.categoria.getCodigo();
						produtoDAO.produto.setCodigoCategoria(codigoCategoria);
						produtoDAO.salvar();
						JOptionPane.showMessageDialog(null, tfNomePro.getText() + " Cadastrado com Sucesso!");
						limparCampos();
					}catch(Exception ex){
						
						JOptionPane.showMessageDialog(null, "Erro ao Cadastrar o Produto");
						limparCampos();
					}
					
			}else{
				JOptionPane.showMessageDialog(null, "Campos Vazios");
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
