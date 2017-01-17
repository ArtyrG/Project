package authdemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import authdemo.hibernate.HibernateUtil;
import authdemo.hibernate.entity.UserEntity;
import authdemo.model.DAO;
import authdemo.model.impl.DAOimlStub;
import authdemo.model.impl.DAOimplHibernate;

public class AuthServlet extends HttpServlet {

	DAO dao = new DAOimlStub();


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("paramName");
		String password = request.getParameter("paramPassword");
		
		if(inspection(name,password)){
		
			response.sendRedirect(request.getContextPath() + "/Profile");		
			return;
						
//			Session session = HibernateUtil.getSessionFactory().openSession();
//			session.beginTransaction();		
//			UserEntity serent = new UserEntity();
//			serent.setName(name);
//			serent.setPassword(password);
//			session.save(serent);
//			session.getTransaction().commit();
			
			
			
		} else {
			response.getWriter().println("Password or Name is incorrect!");
		}
	}

	private boolean login(String name, String pass) {
		String passFrombase = dao.getPassword(name);	
		if(pass.equals(passFrombase)) {
			return true;
		} else {
			return false;
		}

	}
	
	
	   private boolean inspection(String name, String password)
	    {
		    UserEntity userEntity = new UserEntity();
	        Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();		
			
			String driversPass = null;
			String queryName = "SELECT name FROM users";
			String queryPassword = "SELECT password FROM users";
			List driversName = new ArrayList();
			driversName = (List) session.createSQLQuery(queryName).list();
			List driversPassword = new ArrayList();
			driversPassword = (List) session.createSQLQuery(queryPassword).list();
			int indexName = driversName.indexOf(name);
			if (indexName >= 0){
				
				driversPass = (String) driversPassword.get(indexName);
				System.out.println(driversPass);
			
			}
			System.out.println(driversName);
			System.out.println(indexName);
			if(driversName.contains(name)){
				if(driversPass.equals(password)){
					return true;
				} else{
					return false;
				}
			}else {
				return false;
			}
	    }
			   	
}
