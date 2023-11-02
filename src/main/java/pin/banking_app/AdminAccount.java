package pin.banking_app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/AdminServlet")
public class AdminAccount extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank21aug","root", "Root@123");
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ADMIN (ID , NAME ,EMAIL , PASSWORD)"
					+ "VALUES(?,?,?,?)");
			
			preparedStatement.setInt(1, Integer.parseInt(id));
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);
			preparedStatement.execute();
			

//			PrintWriter printWriter = res.getWriter();
//			printWriter.println("<h3> data saved successfully </h3>");
//			
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("AdminForm.html");
			requestDispatcher.forward(req, res);
			
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
