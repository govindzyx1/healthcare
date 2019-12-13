import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import connection.ConnectionFactory;
//import com.CheckSession;
public class DeleteDisease extends HttpServlet
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
		
		System.out.println("check box length"+ch.length);
		

		String s3=request.getParameter("Submit");
		System.out.println("s3................."+s3+ch[0]);
		
	if(s3.equals("Remove"))
		
				{
					
		  
		 st=con.createStatement();
		
		 for(int k=0;k<ch.length;k++){
		 int no=Integer.parseInt(ch[0]);
	         
        int j=st.executeUpdate("delete from medicine where did = "+no);
		int i=st.executeUpdate("delete from DISEASE where did = "+no);
		 if(i!=0) {
		 //pw.println("<html><h1>Disease deleted successfully </h1><center><a href="+"adminhome.html"+">back</a></center> <html>");
		 
		 pw.println("<html><body bgcolor=\"pink\"><center><br/><br/><br/><br/><br/><br/><br/><br/><h3><font color=\"red\"><h2>Disease deleted successfully</h2></font></center></body></html>");
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
			//ps1.close();
			con.close();
			
		      }
		catch (Exception e)
		{
System.out.println(e);
		}
		}
	}
}
