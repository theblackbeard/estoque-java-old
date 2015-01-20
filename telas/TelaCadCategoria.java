package trabalho.almir.telas;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import trabalho.almir.classes.CategoriaDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class TelaCadCategoria extends JPanel {
	private JTextField tfId;
	private JTextField tfNomeCat;
	private JButton btnSalvarDados;
	private JButton btnLimpar;
	private JLabel lblCadastroDeCategoria;
	/**
	 * Create the panel.
	 */
	public TelaCadCategoria() {
		componentes();
		eventos();

	}
	private void componentes(){
		setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblId.setBounds(50, 130, 25, 28);
		add(lblId);
		
		tfId = new JTextField();
		tfId.setBounds(50, 155, 255, 40);
		add(tfId);
		tfId.setColumns(10);
		
		JLabel lblNomeDaCategoria = new JLabel("NOME DA CATEGORIA:");
		lblNomeDaCategoria.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomeDaCategoria.setBounds(50, 250, 379, 36);
		add(lblNomeDaCategoria);
		
		tfNomeCat = new JTextField();
		tfNomeCat.setBounds(50, 297, 511, 40);
		add(tfNomeCat);
		tfNomeCat.setColumns(10);
		
		btnSalvarDados = new JButton("SALVAR CATEGORIA");
		btnSalvarDados.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnSalvarDados.setBounds(173, 475, 230, 53);
		add(btnSalvarDados);
		
		btnLimpar = new JButton("LIMPAR");
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLimpar.setBounds(415, 475, 179, 53);
		add(btnLimpar);
		
		lblCadastroDeCategoria = new JLabel("Cadastro de Categoria");
		lblCadastroDeCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeCategoria.setFont(new Font("Dialog", Font.BOLD, 23));
		lblCadastroDeCategoria.setBounds(0, 20, 800, 57);
		add(lblCadastroDeCategoria);
	}
	

	private void eventos() {
		
		btnSalvarDados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!tfId.getText().trim().isEmpty() && !tfNomeCat.getText().trim().isEmpty()){
					CategoriaDAO categoriaDAO = new CategoriaDAO();
					categoriaDAO.categoria.setCodigo(Integer.parseInt(tfId.getText()));
					categoriaDAO.categoria.setNome(tfNomeCat.getText());
					try{
						categoriaDAO.salvar();
						JOptionPane.showMessageDialog(null, tfNomeCat.getText() + " Gravado com Sucesso!");
						limparDados();
					}catch(Exception ex){
						//ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Erro ao Gravar Informações ou Codigo já Existente");
					}
				}
			}
		});
		
		btnLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limparDados();
			}
		});
	}
	
	private void limparDados(){
		tfId.setText("");
		tfNomeCat.setText("");
	}
}
