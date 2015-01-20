package trabalho.almir.telas;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import trabalho.almir.classes.CategoriaDAO;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class TelaAltCategoria extends JPanel {
	private JTextField tfId;
	private JTextField tfNomeCat;
	private JButton btnPesquisar, btnAltDados, btnLimpar;
	private JLabel lblAlteraoDeCategoria;
	
	/**
	 * Create the panel.
	 */
	public TelaAltCategoria() {
		componentes();
		eventos();

	}

	private void componentes() {
		setLayout(null);
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(50, 129, 46, 14);
		add(lblNewLabel);
		
		tfId = new JTextField();
		tfId.setBounds(60, 145, 152, 40);
		add(tfId);
		tfId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("NOME DA CATEGORIA:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(50, 255, 251, 22);
		add(lblNewLabel_1);
		
		tfNomeCat = new JTextField();
		tfNomeCat.setBounds(60, 280, 314, 40);
		add(tfNomeCat);
		tfNomeCat.setColumns(10);
		
		btnPesquisar = new JButton("PESQUISAR");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPesquisar.setBounds(240, 145, 152, 40);
		add(btnPesquisar);
		
		btnAltDados = new JButton("ALTERAR DADOS");
		
		btnAltDados.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAltDados.setBounds(164, 464, 201, 51);
		add(btnAltDados);
		
		btnLimpar = new JButton("LIMPAR DADOS");
		btnLimpar.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnLimpar.setBounds(391, 464, 201, 51);
		add(btnLimpar);
		
		lblAlteraoDeCategoria = new JLabel("Alteração de Categoria");
		lblAlteraoDeCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlteraoDeCategoria.setFont(new Font("Dialog", Font.BOLD, 23));
		lblAlteraoDeCategoria.setBounds(0, 20, 800, 57);
		add(lblAlteraoDeCategoria);
	}

	private void eventos() {
		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfNomeCat.setText("");
				if(!tfId.getText().trim().isEmpty()){
					
					CategoriaDAO categoriaDAO = new CategoriaDAO();
					try{
						categoriaDAO.listarPorCodigo(Integer.parseInt(tfId.getText()));
						tfNomeCat.setText(categoriaDAO.categoria.getNome());
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, "Codigo não Encontrado!");
						limparDados();
					}
				}
			}

		});
		
		btnAltDados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!tfId.getText().trim().isEmpty() && !tfNomeCat.getText().trim().isEmpty()){
					CategoriaDAO categoriaDAO = new CategoriaDAO();
					try{
						categoriaDAO.categoria.setCodigo(Integer.parseInt(tfId.getText()));
						categoriaDAO.categoria.setNome(tfNomeCat.getText());
						int n = JOptionPane.showConfirmDialog(null, "Deseja Realmente Alterar a Categoria: " 
								+ tfNomeCat.getText() + " ?" );
								if(n == JOptionPane.YES_NO_OPTION){
									categoriaDAO.editar();
									JOptionPane.showMessageDialog(null, "Categoria Alterada com Sucesso!");
									limparDados();
								}
						
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, "Erro ao Alterar Informações ou Codigo já Existente");
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
	
	private void limparDados() {
		tfId.setText("");
		tfNomeCat.setText("");
	}
}
