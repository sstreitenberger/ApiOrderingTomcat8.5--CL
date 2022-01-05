package source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		
		String ip1 = "172.20.";
		int ip2 = 102;
		String ip3= ".100";
		
		//while (ip2 < 141)
		//{
			;
			String ipaux1 = Integer.toString(ip2);
			String ipaux2 = ip1.concat(ipaux1);
			String ip = ipaux2.concat(ip3);
			
		 Class.forName("com.sybase.jdbc3.jdbc.SybDriver").newInstance();
		 Statement stmt = null;
		 ResultSet rs = null;
		 Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:sybase:Tds:" + ip + ":2638?ServiceName=micros", "custom", "custom");
		
		 stmt = conn.createStatement();
		 
		 String query = "select business_date_start_time , business_date_end_time from  micros.dly_sys_ttl where business_date = '2022-01-05'";
		 rs = stmt.executeQuery(query);
		 while (rs.next())
		 {
			 String fechainicio = rs.getString("business_date_start_time");
			 String fechaFinal = rs.getString("business_date_end_time");
			 System.out.println("Tienda "+ip+":  " +fechainicio + " - "+ fechaFinal);
			 
			 
		 }
		} catch (SQLException e) {
			System.out.println("Sin conexion con tienda: "+ip);
		}  	
    	ip2++;
		//}
	}

}
