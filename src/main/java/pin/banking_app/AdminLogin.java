package pin.banking_app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/AdminLogin")
public class AdminLogin extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank21aug","root", "Root@123");
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ADMIN WHERE EMAIL=? AND PASSWORD=?");
				
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);
				ResultSet resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next()) {

					String emailDb= resultSet.getString("EMAIL");
					String passwordDb= resultSet.getString("PASSWORD");
					
					if(email.equals(emailDb)&& password.equals(passwordDb)) {
						try {
							RequestDispatcher requestDispatcher = req.getRequestDispatcher("Admin-HomePage.html");
							requestDispatcher.forward(req, res);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				else {
					System.out.println("No user found!");
				}
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}

}
