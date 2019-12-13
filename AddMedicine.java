import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import connection.ConnectionFactory;
//import com.CheckSession;
public class AddMedicine extends HttpServlet
{
	Connection con=null;
	PreparedStatement ps=null;
	PreparedStatement ps1=null;
	PrintWriter pw;
	RequestDispatcher rd;
	
	public  void service(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
	    {
		 
		try
			{
		 Connection con=ConnectionFactory.createConnection();
	
		 pw=response.getWriter();
		 
		String did=request.getParameter("did");
		int id=Integer.parseInt(did);
		String symptom=request.getParameter("symptoms");
		
		String mid=request.getParameter("Dname");
		
		String des=request.getParameter("desc");
		
				
		  
		 ps=con.prepareStatement("insert into MEDICINE values(?,?,?,?)");
		 
		 ps.setInt(1,id);
		ps.setString(2,symptom);
		 ps.setString(3,mid);
         ps.setString(4,des);
		 
	
		int i=ps.executeUpdate();
         
		 
		 
		
		
        rd=request.getRequestDispatcher("success1.jsp");
	    rd.forward(request,response);
 
			
			
			}
			catch(SQLException ea)
			{
			pw.println(ea);
			}


			finally
			{
			try
			{
			ps.close();
			ps1.close();
			con.close();
			
		      }
		catch (Exception e)
		{
System.out.println(e);
		}
		}
	}
}
