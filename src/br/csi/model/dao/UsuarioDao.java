package br.csi.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.csi.model.Usuario;
import br.csi.model.util.conectaBDPostgres;
import jdk.management.resource.internal.inst.StaticInstrumentation;

public class UsuarioDao {

	public static void main(String args[]){
		
		Usuario u = new Usuario();
		u.setLogin("michel");
		u.setSenha("123");
		
		try {
			System.out.println("autenticado?"+new UsuarioDao().autenticado(u));
			new UsuarioDao().autenticado(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean autenticado(Usuario u) throws SQLException{
		
		boolean autenticado = false;
		
		Connection c = conectaBDPostgres.getConexao();
		Statement stmt = c.createStatement();
		String sql ="Select * from usuario where login = '"+u.getLogin()+"' and senha='"+u.getSenha()+"';";
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			
			long id = rs.getLong("id");
			String login = rs.getString("login");
			String senha = rs.getString("senha");
			
			autenticado=true;
			
		}
		
		return autenticado;
		
	}
	
}
