package pin.banking_app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.Driver;

@WebServlet("/ViewCustomer")
public class ViewCustomer extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String accno = req.getParameter("accno");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank21aug", "root", "Root@123");
			
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE ACCNO=?");
			preparedStatement.setLong(1, Long.parseLong(accno));
			
			ResultSet resultSet= preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				String customerInfo = "Id: " + resultSet.getInt("id") + "<br>"
						+ "Name: " + resultSet.getString("name") + "<br>"
						+ "Account Number: " + resultSet.getLong("accno") + "<br>"
						+ "Pincode: " + resultSet.getInt("pincode") + "<br>"
						+ "Balance: " + resultSet.getDouble("balance");
				PrintWriter printWriter = resp.getWriter();
				printWriter.println(customerInfo);
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("ViewCustomer.html");
				requestDispatcher.include(req, resp);
				resp.setContentType("text/html");
			}
			else {
				System.out.println("something went wrong...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
