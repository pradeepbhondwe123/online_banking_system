package pin.banking_app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/CustomerLogin")
public class CustomerLogin extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String pincode = req.getParameter("pincode");
		String id1= id;
		String pincode1=pincode;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank21aug","root", "Root@123");
			
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE ID=? AND PINCODE=?");
			
			preparedStatement.setInt(1, Integer.parseInt(id));
			preparedStatement.setInt(2, Integer.parseInt(pincode));
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {

				String idDb= resultSet.getString("ID");
				String pincodeDb= resultSet.getString("PINCODE");
				
				if(id1.equals(idDb) && pincode1.equals(pincodeDb)) {
//					RequestDispatcher requestDispatcher = req.getRequestDispatcher("Customer-HomePage.html");
//					requestDispatcher.forward(req, res);
					try {
						RequestDispatcher requestDispatcher = req.getRequestDispatcher("Customer-HomePage.html");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
