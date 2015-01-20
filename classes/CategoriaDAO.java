package trabalho.almir.classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CategoriaDAO {

	public Categoria categoria;
	private Conexao conexao;
	private String sql;
	private PreparedStatement statement;
	private ResultSet resultSet;

	
	
	public CategoriaDAO(){
		categoria = new Categoria();
		conexao = new Conexao();
	}
	public void salvar() throws Exception{
		if(conexao.getConnection()){
			sql = "INSERT INTO categoria (id, nome ) VALUES(?,?)";
			try{
				statement = conexao.connection.prepareStatement(sql);
				statement.setInt(1, categoria.getCodigo());
				statement.setString(2, categoria.getNome());
				statement.execute();
			}catch(Exception e){
				throw e;
			}finally{
				conexao.close();
			}
		}
	}
	
	public void editar() throws Exception{
		if(conexao.getConnection()){
			
				sql = "UPDATE categoria SET id=? , nome=? WHERE id=?";
				try{
					statement = conexao.connection.prepareStatement(sql);
					statement.setInt(1, categoria.getCodigo());
					statement.setString(2, categoria.getNome());
					statement.setInt(3, categoria.getCodigo());
					statement.executeUpdate();
				}catch(Exception e){
					//e.printStackTrace();
					throw e;
				}finally{
					conexao.close();
				}
		
		}
	}
	
	public void excluir() throws Exception{
		if(conexao.getConnection()){
			sql = "DELETE FROM categoria WHERE id=?";
			try{
				statement = conexao.connection.prepareStatement(sql);
				statement.setInt(1, categoria.getCodigo());
				statement.executeUpdate();
			}catch(Exception e){
				//e.printStackTrace();
				throw e;
			}finally{
				conexao.close();
			}
		}
	}
	
	public void listarPorCodigo(int codigo) throws Exception{
		if(conexao.getConnection()){
			sql = "SELECT * FROM categoria WHERE id=?";
			try{
				statement = conexao.connection.prepareStatement(sql);
				statement.setInt(1, codigo);
				resultSet = statement.executeQuery();
				resultSet.first();
				categoria.setCodigo(resultSet.getInt(1));
				categoria.setNome(resultSet.getString(2));
			}catch(Exception e){
				//e.printStackTrace();
				throw e;
			}finally{
				conexao.close();
			}
		}
	}
	
	public void listarPorCodigo(String nome) throws Exception{
		if(conexao.getConnection()){
			sql = "SELECT * FROM categoria WHERE nome=?";
			try{
				statement = conexao.connection.prepareStatement(sql);
				statement.setString(1, nome);
				resultSet = statement.executeQuery();
				resultSet.first();
				categoria.setCodigo(resultSet.getInt(1));
				categoria.setNome(resultSet.getString(2));
			}catch(Exception e){
				//e.printStackTrace();
				throw e;
			}finally{
				conexao.close();
			}
		}
	}
	
	public void listarUltimo() throws Exception{
		if(conexao.getConnection()){
			sql = "SELECT * FROM categoria";
			try{
				statement = conexao.connection.prepareStatement(sql);
				resultSet = statement.executeQuery();
				resultSet.last();
				categoria.setCodigo(resultSet.getInt(1));
			}catch(Exception e){
				//e.printStackTrace();
				throw e;
			}finally{
				conexao.close();
			}
		}
	}
	
	
}
