package trabalho.almir.telas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class TelaIndex extends JPanel{
	
	public TelaIndex(){
		componentes();
	}

	private void componentes() {
		
		setLayout(null);
		setSize(800, 600);
		JLabel texto1 = new JLabel("Gerenciamento de Estoque");
		texto1.setBounds(200,40, 500,30);
		texto1.setFont(new Font("Arial", Font.BOLD, 30));
		
		JLabel texto2 = new JLabel("Trabalho Final Prof. Almir");
		texto2.setBounds(400,80, 500,30);
		texto2.setFont(new Font("Arial", Font.ITALIC, 20));
		texto2.setForeground(Color.RED);
		
			
		JLabel texto3 = new JLabel("Participante");
		texto3.setBounds(530,410, 500,30);
		texto3.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel texto4 = new JLabel("Carlos Mateus Carvalho - RGM: 24710300");
		texto4.setBounds(530,430, 500,30);
		texto4.setFont(new Font("Arial", Font.ITALIC, 12));
		texto4.setForeground(Color.BLUE);
		
		
		
		JLabel info = new JLabel("Linguagens e Técnicas de Programação II - Trabalho Final Acadêmico - 4° Semestre");
		info.setBounds(170, 525, 500, 30);
		info.setFont(new Font("Arial", Font.PLAIN, 10));
		add(info);
		
		add(texto1);
		add(texto2);
		add(texto3);
		add(texto4);
	
		
	}
	
	
}
