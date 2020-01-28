package indu.com;
import java.util.*;
import java.sql.*;
public class Market {
	public static void main(String args[])throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe","system","system");
		System.out.println("Enter the No of Items Bought.");
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		for(int i=0;i<n;i++) {
			System.out.println("ID:");
			int id=sc.nextInt();
			System.out.println("Item Name:");
			String name =sc.next();
			System.out.println("Item cost:");
			float cost=sc.nextFloat();
			PreparedStatement ps = con.prepareStatement("insert into sales values(?,?,?)");
			ps.setInt(1,id);
			ps.setString(2,name);
			ps.setFloat(3,cost);
			ps.executeUpdate();
		}
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from sales");
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3));
		}
		System.out.println("TOTAL:");
		ResultSet rs1=stmt.executeQuery("select sum(cost) from sales");
		while(rs1.next()) {
			 System.out.println(rs1.getFloat(1));
		}
	}
}

