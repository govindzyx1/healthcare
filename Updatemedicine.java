import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import connection.ConnectionFactory;
//import com.CheckSession;
public class Updatemedicine extends HttpServlet
{
	Connection con=null;
	String did,med_desc,mname,mmname;
	Statement st=null;
	PreparedStatement ps1=null;
	PrintWriter pw;
	RequestDispatcher rd;
	
	public  void service(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
	    {
		 
		try
			{
		 con=ConnectionFactory.createConnection();
	
		 pw=response.getWriter();
		 
		
		HttpSession session=request.getSession();
		
		mmname=(String)session.getAttribute("mmname");
		 did=request.getParameter("Did");
		mname=request.getParameter("Mname");
       med_desc=request.getParameter("med_desc");
		
		
		

		String s3=request.getParameter("s");
		
		
	if(s3.equals("Update"))
		
				{
					
		  
		 st=con.createStatement();
		
	        

		
		int j=st.executeUpdate("update medicine set mname='"+mname+"',med_desc='"+med_desc+"' where did="+did+"and mname='"+mmname+"'");
		
		//System.out.println("i valuej +++"+j);
		 if(j!=0) {
		 pw.println("<html><center><h1>medicine successfully updated<h1><a href="+"adminhome.html"+">back</a> </center><html>");
		 } 
		 
		       

		
		
      
 
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
			st.close();
			
			con.close();
			
		      }
		catch (Exception e)
		{
System.out.println(e);
		}
		}
	}
}
