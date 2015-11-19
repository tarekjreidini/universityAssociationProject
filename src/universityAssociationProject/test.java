package universityAssociationProject;

import java.sql.*;

public class test {

	

	public static void main(String[] args) throws SQLException {
		
		
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from member");
			
			
			while (rs.next()){
				System.out.println(rs.getString(3));
			}
			
		
		
		
		
		
		
	}

}
