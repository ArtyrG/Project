package authdemo;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import authdemo.hibernate.HibernateUtil;
import authdemo.hibernate.entity.UserEntity;
import authdemo.validEmail.ValidEmail;


public class RegServlet extends HttpServlet {
	
	ValidEmail validEmail = new ValidEmail();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
					
		String name = request.getParameter("paramNameReg");
		String password = request.getParameter("paramPasswordReg");
		String email = request.getParameter("paramEmailReg");
		
		System.out.println(name);
		System.out.println(password);
		System.out.println(email);
		
		if (name.isEmpty() || password.isEmpty() || email.isEmpty() ){
			response.getWriter().println("Все поля обязательны для заполнения!");
		}else{
			if(inspectorName(name)){
				if(inspectorEmail(email)){
					if(validEmail.validate(email)){
						response.getWriter().print("Успешная регистрация!");
						entryBase(name,password,email);
						response.sendRedirect(request.getContextPath() + "/Profile");		
						return;
					}else{
						response.getWriter().print("Email не существует!");
					}
				}else{
					response.getWriter().print("Email существует, введите другой Email!");
				}
			}else{
				response.getWriter().print("Имя существует, введите другое имя!");
			}
		}
		
	}

	private void entryBase(String name, String password, String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();		
		UserEntity serent = new UserEntity();
		serent.setName(name);
		serent.setPassword(password);
		serent.setEmail(email);
		java.util.Date date = new java.util.Date();
		serent.setDate(date);
		session.save(serent);
		session.getTransaction().commit();
		
	}

	private boolean inspectorEmail(String email) {
		UserEntity userEntity = new UserEntity();
        Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();	
		String queryEmail = "SELECT email FROM users";
		List driversEmail = new ArrayList();
		driversEmail = (List) session.createSQLQuery(queryEmail).list();
		
		if(driversEmail.contains(email)){
			return false;
		}
		return true;			
	}

	private boolean inspectorName(String name) {
		UserEntity userEntity = new UserEntity();
        Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();	
		String queryName = "SELECT name FROM users";
		List driversName = new ArrayList();
		driversName = (List) session.createSQLQuery(queryName).list();
		
		if(driversName.contains(name)){
			return false;
		}
		return true;			
	}

}
