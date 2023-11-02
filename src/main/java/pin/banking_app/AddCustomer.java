package pin.banking_app;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddCustomer")
public class AddCustomer extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Random random = new Random();
		Long minValue = 100000000000000L;
		Long maxValue = 999999999999999L;
		Long randomValue = (long)(random.nextDouble() * (maxValue-minValue));
		String id= req.getParameter("id");
		String name= req.getParameter("name");
		Long accno= randomValue;
		String pincode= req.getParameter("pincode");
		double balance=0.00d;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank21aug","root", "Root@123");
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CUSTOMER(ID, NAME, ACCNO, PINCODE, BALANCE)"
					+ "VALUES(?,?,?,?,?)");
			preparedStatement.setInt(1, Integer.parseInt(id));
			preparedStatement.setString(2, name);
			preparedStatement.setLong(3, accno);
			preparedStatement.setInt(4, Integer.parseInt(pincode));
			preparedStatement.setDouble(5, balance);
			preparedStatement.execute();
			PrintWriter printWriter = resp.getWriter();
			printWriter.println("<h1>Customer Added");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
