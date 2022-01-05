package dex;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.*;




public class ConnectionDB {
	public static Connection cn;
	static String thisIp = new String ();
	
	public static Connection getConnection() throws Exception, SQLException
	{
		try 
		{
			thisIp = "192.168.245.2";
			System.out.println("IP:"+thisIp);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		try
		{		
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			cn = DriverManager.getConnection("jdbc:sqlserver://192.168.198.129:1433;databaseName=Fidelidad-CL;user=fidews;password=AlseA.2021"); //Esta es la entrada a la base de datos con la IP sel servidor
			
			System.out.println("Conecto");
			}
		catch(Exception e)
		{	
			cn = null;
			System.out.println("No conecto:" + e);
		}
		return cn;	
	}

	public  ArrayList<Orden> GetClient(String id) throws Exception, SQLException
	{	
		ArrayList<Orden> orden = new ArrayList<Orden>();
		cn = ConnectionDB.getConnection();
		if (cn != null) 
		{		
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery("select id, nombre, empresa  from Empleados where id = '"+id+"'");
		
			while(rs.next())
			{
				Orden o = new Orden();
				o.setid(rs.getString("id"));
				o.setname(rs.getString("nombre"));
				o.setempresa(rs.getString("empresa"));		
				orden.add(o);
			}
			for (int i = 0; i < orden.size(); i++) {
			System.out.println(orden.get(i).getname());
			}
		}
		return orden;
	}
	
		
}
