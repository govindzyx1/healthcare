import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import connection.ConnectionFactory;
//import com.CheckSession;
public class DeleteMedicine extends HttpServlet
{
	Connection con=null;
	String ch[];
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
		 
		
		 
		
		 ch=request.getParameterValues("ch");
		
		
		

		String s3=request.getParameter("Submit");
		System.out.println("s3................."+s3+ch[0]);
		
	if(s3.equals("Remove"))
		
				{
					
		  
		 st=con.createStatement();
		
		 for(int k=0;k<ch.length;k++){
		 String no=ch[0];
	         

		int i=st.executeUpdate("delete from medicine where SYMPTOM= '"+no+"'");
		 if(i!=0) {
		 //pw.println("<html><center><h1>Medicine deleted successfully </h1><a href="+"adminhome.html"+">back</a></center> <html>");

		 pw.println("<html><body bgcolor=\"pink\"><center><br/><br/><br/><br/><br/><br/><br/><br/><h3><font color=\"red\"><h2>Medicine deleted successfully</h2></font></center></body></html>");
			response.setHeader("Refresh","2;URL=adminhome.html");
	    	 return;
		 } 
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
