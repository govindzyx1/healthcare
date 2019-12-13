import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import connection.ConnectionFactory;
//import com.CheckSession;
public class UserReg extends HttpServlet
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
		 
		
		
		String fnam=request.getParameter("fname");
		
		String lname=request.getParameter("lname");


		int age=Integer.parseInt(request.getParameter("age"));
		
		String gen=request.getParameter("gen");
		String addr=request.getParameter("add");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		
		
		

		String s3=request.getParameter("Submit");
		
	if(s3.equals("Submit"))
		
				{
					
            int id=0;
				    ps1=con.prepareStatement("select max(USER_ID) from USER_INFO");
					
					ResultSet rs=ps1.executeQuery();
                    while(rs.next())
					{
					id=rs.getInt(1);
					}
					
		 id=id+1;


		  
		 ps=con.prepareStatement("insert into USER_INFO values(?,?,?,?,?,?,?,?)");
		 
		 ps.setInt(1,id);
		
		 ps.setString(2,fnam);
         ps.setString(3,lname);
		 ps.setInt(4,age);
         ps.setString(5,gen);
		  ps.setString(6,addr);
		 ps.setString(7,name);
         ps.setString(8,password);
		
        
		 
	
		int i=ps.executeUpdate();
         
		 
		 
		
		
        rd=request.getRequestDispatcher("success3.jsp");
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
