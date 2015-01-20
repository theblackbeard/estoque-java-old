package trabalho.almir.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public Connection connection = null;
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String DBNAME = "db_estoque";
	private final String URL = "jdbc:mysql://localhost/" + DBNAME;
	private final String LOGIN = "root";
	private final String SENHA = "";
	
	public boolean getConnection(){
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, LOGIN, SENHA);
			return true;
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado" + e.toString());
			return false;
		} catch(SQLException e){
			System.out.println("Falha ao Conectar" + e.toString());
			return false;
		}
	}
	
	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
