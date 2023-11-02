package pin.banking_app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.util.Random;
@WebServlet("/CustomerServlet")
public class CustomerAccount extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		Random random = new Random();
		Long minLimit = 100000000000000L;
        Long maxLimit = 999999999999999L;
        Long randomValue = minLimit +(long)(random.nextDouble() * (maxLimit - minLimit));
        System.out.println(random.nextDouble());
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		Long accno = randomValue;
		String pincode = req.getParameter("pincode");
		String balance = new String("0.00");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank21aug","root", "Root@123");
			
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CUSTOMER"
					+ "(ID, NAME, ACCNO, PINCODE, BALANCE) VALUES(?,?,?,?,?)");
			
			preparedStatement.setInt(1, Integer.parseInt(id));
			preparedStatement.setString(2, name);
			preparedStatement.setLong(3, accno);
			preparedStatement.setInt(4, Integer.parseInt(pincode));
			preparedStatement.setDouble(5, Double.parseDouble(balance));
			
			preparedStatement.execute();
	
			RequestDispatcher requestDispatcher= req.getRequestDispatcher("CustomerForm.html");
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
