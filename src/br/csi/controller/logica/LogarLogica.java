package br.csi.controller.logica;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.csi.model.Usuario;
import br.csi.model.dao.UsuarioDao;

public class LogarLogica implements Logica{

	@Override
	public String executa(HttpServletRequest rq, HttpServletResponse rp){
		String usuario = rq.getParameter("login");
		String senha = rq.getParameter("senha");
		
		Usuario u = new Usuario();
		
		u.setLogin(usuario);
		u.setSenha(senha);
		
		UsuarioDao uD = new UsuarioDao();
		String pagina = "/index.jsp";
		
		try {
			
			boolean retorno = uD.autenticado(u);
			if(retorno){
				pagina = "/WEB-INF/jsp/principal.jsp";
				rq.setAttribute("usuario", u);
			}else{
				rq.setAttribute("msg", "Problemas ao Logar");
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
			rq.setAttribute("msg", "Problema ao logar");
			return pagina;
		}
			
		return pagina;
	}
}
