package trabalho.almir.classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProdutoDAO {

	public Produto produto;
	private PreparedStatement statement;
	private Conexao conexao;
	private ResultSet resultSet;
	private String sql;
	
	public ProdutoDAO(){
		produto = new Produto();
		conexao = new Conexao();
	}
	
	public void salvar() throws Exception{
		if(conexao.getConnection()) {
			sql = "INSERT INTO produto (id, id_categoria, nome,  marca, qtde, valor) VALUES (?,?,?,?,?,?)";
			try{
				statement = conexao.connection.prepareStatement(sql);
				statement.setInt(1, produto.getCodigo());
				statement.setInt(2, produto.getCodigoCategoria());
				statement.setString(3, produto.getNome());
				statement.setString(4, produto.getMarca());
				statement.setInt(5, produto.getQuantidade());
				statement.setDouble(6, produto.getVlrUnidade());
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
			sql = "UPDATE produto SET id=?, id_categoria=?, nome=?, marca=?, qtde=? , valor=? WHERE id=?";
			try{
				statement = conexao.connection.prepareStatement(sql);
				statement.setInt(1, produto.getCodigo());
				statement.setInt(2, produto.getCodigoCategoria());
				statement.setString(3, produto.getNome());
				statement.setString(4, produto.getMarca());
				statement.setInt(5, produto.getQuantidade());
				statement.setDouble(6, produto.getVlrUnidade());
				statement.setInt(7, produto.getCodigo());
				statement.executeUpdate();
			}catch(Exception e){
				throw e;
			}finally{
				conexao.close();
			}
		}
	}
	
	public void excluir() throws Exception{
		if(conexao.getConnection()){
			sql = "DELETE FROM produto WHERE id=?";
			try{
				statement = conexao.connection.prepareStatement(sql);
				statement.setInt(1, produto.getCodigo());
				statement.executeUpdate();
			}catch(Exception e){
				throw e;
			}finally{
				conexao.close();
			}
		}
	}
	
	public void listarPorCodigo(int codigo) throws Exception{
		if(conexao.getConnection()){
			sql = "SELECT * FROM produto WHERE id=?";
			try{
				statement = conexao.connection.prepareStatement(sql);
				statement.setInt(1, codigo);
				resultSet = statement.executeQuery();
				
				resultSet.first();
				produto.setCodigo(resultSet.getInt(1));
				produto.setCodigoCategoria(resultSet.getInt(2));
				produto.setNome(resultSet.getString(3));
				produto.setMarca(resultSet.getString(4));
				produto.setQuantidade(resultSet.getInt(5));
				produto.setVlrUnidade(resultSet.getDouble(6));
			
			}catch(Exception e){
				throw e;
			}finally{
				conexao.close();
			}
		}
	}
	
}
