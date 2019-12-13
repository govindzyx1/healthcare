

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import connection.ConnectionFactory;


public class ChangePassword extends HttpServlet
{
  Connection con;
  PreparedStatement pstmt1;
  PreparedStatement pstmt2;
  
  RequestDispatcher rd;

  public ChangePassword()
  {
    this.con = null;
    this.pstmt1 = null;
    this.pstmt2 = null;
   
   
  }

  public void init(ServletConfig cg)
  {
    try
    {con=ConnectionFactory.createConnection();
      
      String query = "update  User_Info set PWD = ? where PWD = ? and USER_ID = ?";
      this.pstmt1 = this.con.prepareStatement(query);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
  }

  public void destroy()
  {
    try
    {
      this.con.close();
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
  }

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession(true);
    String userID = (String)session.getAttribute("userID");
    
    
    try
    {
      
      this.pstmt1.setString(1, request.getParameter("npwd"));
      this.pstmt1.setString(2, request.getParameter("opwd"));
      this.pstmt1.setString(3, userID);
      
      int row = this.pstmt1.executeUpdate();
      if (row == 1){
		   rd=request.getRequestDispatcher("success4.jsp");
	    rd.forward(request,response);
        //out.print("<body><h2 align = 'center'>Password changed Successfully.....</h1>");
	  }
      else
		  out.println("<html><body bgcolor=\"pink\"><center><br/><br/><br/><br/><br/><br/><br/><br/><h3><font color=\"red\"><h2>Sorry...... Probelm in changing password [Old password is wrong]</h2></font></center></body></html>");
			response.setHeader("Refresh","2;URL=userhome.html");
	    	 return;
        //out.print("<html><body bgcolor="pink"><h2 align = 'center'>Sorry...... Probelm in changing password [Old password is wrong]</h1>.<a href="userhome.html">back</a><html>");
    }
    catch (Exception e)
    {
      try
      {
        System.out.print(e.getMessage());
        this.con.rollback();
      }
      catch (SQLException ex)
      {
        ex.printStackTrace();
      }
    }
    out.close();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    processRequest(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    processRequest(request, response);
  }

  public String getServletInfo()
  {
    return "Short description";
  }
}