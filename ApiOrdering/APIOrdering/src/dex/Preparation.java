package dex;
// annotations

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;



@Path("/Fidelidad/{id}")
public class Preparation {
	
	
	private ConnectionDB con;
	private ArrayList<Orden> orden;
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public String preparationJSON(@PathParam("id") String id) throws SQLException, Exception {
		
		System.out.println(id);
		
		con = new ConnectionDB();
		String respuesta = new String();
		orden = new ArrayList<Orden>();
		orden = con.GetClient(id);
		
		//System.out.println(orden.get(0).getid());
			
		for (int i = 0; i < orden.size(); i++)
		{
			if (i == 0)
			{
				if (orden.size() == 1)
				{
					respuesta = "[{\"id\":\""+orden.get(i).getid()+"\",\"Nombre\":\""+orden.get(i).getname()+"\",\"Empresa\":\""+orden.get(i).getempresa()+"\"}]";				
				}
				else {
					respuesta = "[{\"id\":\""+orden.get(i).getid()+"\",\"Nombre\":\""+orden.get(i).getname()+"\",\"Empresa\":\""+orden.get(i).getempresa()+"\"},";	//,\"Tipo\":\""+orden.get(i).gettipo()+"\"
				}
			}
			else if (i == orden.size()-1)
			{
				respuesta = respuesta.concat("{\"id\":\""+orden.get(i).getid()+"\",\"Nombre\":\""+orden.get(i).getname()+"\",\"Empresa\":\""+orden.get(i).getempresa()+"\"}]");
			}
			else 
			{	
				respuesta = respuesta.concat("{\"id\":\""+orden.get(i).getid()+"\",\"Nombre\":\""+orden.get(i).getname()+"\",\"Empresa\":\""+orden.get(i).getempresa()+"\"},");
			}
		}
		if (respuesta.isEmpty())
		{
			respuesta = "No se encontro cliente con el Rut definido: "+id+"" ;
		}		
		System.out.println(respuesta);
		return respuesta;
	}
}
