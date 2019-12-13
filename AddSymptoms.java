import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import connection.ConnectionFactory;
//import com.CheckSession;
public class AddSymptoms extends HttpServlet
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
		 
		
		
		String symtom=request.getParameter("Symptoms");
		
		
		
		
		

		String s3=request.getParameter("Submit");
		
	if(s3.equals("Submit"))
		
				{
					
            int id=0;
				    ps1=con.prepareStatement("select max(SID) from SYMPTOMS");
					
					ResultSet rs=ps1.executeQuery();
                    while(rs.next())
					{
					id=rs.getInt(1);
					}
					
		 id=id+1;


		  
		 ps=con.prepareStatement("insert into SYMPTOMS values(?,?)");
		 
		 ps.setInt(1,id);
		
		 ps.setString(2,symtom);
         
		 
	
		int i=ps.executeUpdate();
         
		 
		 
		
		
        rd=request.getRequestDispatcher("success5.jsp");
	    rd.forward(request,response);
 
			}
			
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
