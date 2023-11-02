package pin.banking_app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;


@WebServlet("/UpdateCustomer")
public class UpdateCustomer extends HttpServlet {
		
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accno = req.getParameter("accno");
		String name = req.getParameter("name");
		String pincode = req.getParameter("pincode");
		
		try {
			
			  
			if(!name.equals("N/A") || name.equals("n/a")) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank21aug", "root", "Root@123");
				PreparedStatement preparedStatement = connection.prepareStatement("UPDATE CUSTOMER SET NAME=? WHERE ACCNO=?");
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, Integer.parseInt(accno));
				preparedStatement.execute();
				PrintWriter printWriter = resp.getWriter();
				printWriter.println("Name Updated");
			}
			if(pincode.equals("N/A") || pincode.equals("n/a")) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank21aug", "root", "Root@123");
				PreparedStatement preparedStatement = connection.prepareStatement("UPDATE CUSTOMER SET PINCODE=? WHERE ACCNO=?");
				preparedStatement.setInt(1, Integer.parseInt(pincode));
				preparedStatement.setInt(2, Integer.parseInt(accno));
				preparedStatement.execute();
				PrintWriter printWriter = resp.getWriter();
				printWriter.println("Pincode updated");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UpdateCustomer updateCustomer = new UpdateCustomer();
		updateCustomer.doPut(req, resp);
	}
}
