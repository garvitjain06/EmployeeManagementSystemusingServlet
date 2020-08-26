import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class Employee extends HttpServlet {
 
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Employee details!</title>");
        out.println("</head>");
        out.println("<body>");
        
        
        out.println("Enter necessary details");
        out.println("<P>");
        out.print("<form action=\"");
        out.print("Employee\" ");
        out.println("method=POST>");
        out.println("eid :");
        out.println("<input type=text size=30 name=eid>");
        out.println("<br>");
        out.println("name :");
        out.println("<input type=text size=30 name=username>");
        out.println("<br>");
        out.println("address :");
        out.println("<input type=text size=30 name=address>");
        out.println("<br>");
        out.println("phnno :");
        out.println("<input type=text size=30 name=phnno>");
        out.println("<br>");
        out.println("salary :");
        out.println("<input type=text size=30 name=salary>");
        out.println("<br>");
        out.println("designation :");
        out.println("<input type=text size=30 name=designation>");
        out.println("<br>");
        out.println("1-for select Employee details");
        out.println("<br>");
	out.println("2-for insert Employee details");
        out.println("<br>");
        out.println("3-for update Employee details");
        out.println("<br>");
        out.println("4-for delete employee details"); 
        out.println("<br>");
        out.println("Enter your choice for the service:");
        out.println("<input type=text size=30 name=choice>");
        out.println("<br>");
                             
        out.println("<input type=submit size=20 value=submit>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
         
                 
                                             
        
    }  
public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Scanner sc=new Scanner(System.in);
        String name = request.getParameter("username");
        String designation = request.getParameter("designation");
        String address = request.getParameter("address");
        String phoneno = request.getParameter("phnno");
        int ch=Integer.parseInt(request.getParameter("choice"));
        int eid=Integer.parseInt(request.getParameter("eid"));
        int salary=Integer.parseInt(request.getParameter("salary"));
        out.println("<html>");
       out.println("<body>");
           try
        {
		 
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gjdb","root","root");
		  
                           
                            
                                             
			
                                               
                                                switch(ch)
                                                {
                                                     case 1: PreparedStatement st=con.prepareStatement("select name,eid from Employee where name=? and eid=?");
  		                                          st.setString(1,name);
                                                        st.setInt(2,eid);
			                                 ResultSet rs=st.executeQuery();
  		                                            while(rs.next())
  		                                      { 
                                                     out.println("<h2>"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getInt(5)+"  "+rs.getString(6)+"</h2>"); 
  		                                      }
  		                                      break;
                                                      
                                                          
                                                     case 2:PreparedStatement st1=con.prepareStatement("insert into Employee(eid,name,address,phno,salary,designation) values(?,?,?,?,?,?)");
                                                            st1.setInt(1,eid);
  		                                             st1.setString(2,name);
  		                                             st1.setString(3,address);
  		                                               st1.setString(4,phoneno);
  		                                                  st1.setInt(5,salary);
                                                              st1.setString(6,designation);
			                                          int rs1=st1.executeUpdate();
  		            
  		                                                    if(rs1 ==1)
  		                                                       { 
                           						   out.println("<h2>New Employee information is successfully inserted</h2>"); 
  		          					   }
  		          					  else
  		         					   {
  		             						   out.println("<h2>Employee information is invalid</h2>");
  		            					}
		                                       
                                                           break;
                                                     case 3:PreparedStatement st2=con.prepareStatement("update Employee set salary=? where eid=?");
  		                                          
                             
  		             out.println("Enter the EmployeeID whose value is to be updated");
  		             int newsalary=Integer.parseInt(request.getParameter("salary"));
                             int scid=Integer.parseInt(request.getParameter("eid"));
                             
			     
                             st2.setInt(1,newsalary);
                   
  		            st2.setInt(2,scid);
			    int rs2=st2.executeUpdate();
			    if(rs2 ==1)
  		            { 
                              out.println("<h2>Employee information is successfully updated</h2>"); 
  		            }
  		            else
  		            {
  		                out.println("<h2>Employee information is invalid</h2>");
  		            }
  		            break;

                                                     case 4:PreparedStatement st3=con.prepareStatement("delete from Employee where eid=?");
                            out.println("<h2>Enter the EmployeeID whose information is to be deleted</h2>");
  		             int dcid=Integer.parseInt(request.getParameter("eid"));
                             st3.setInt(1,dcid);
			    int rs3=st3.executeUpdate();
  		             if(rs3 ==1)
  		            { 
                              System.out.println("<h2>Employee information is successfully deleted</h2>"); 
  		            }
  		            else
  		            {
  		                System.out.println("<h2>Employee information is invalid</h2>");
  		            }
  		            break;
                                                          
                                                     
                                                      default:
                                                                 out.println("<h3>You have entered a Invalid Choice</h3>");
                                                                 break;
                                                    
                                                                
                                                }
                                       
                                                
                                                
  		                
                                                    
                             
               con.close();
        
        }
		catch(Exception e){ out.println(e);} 
	out.println("</body>");
       out.println("</html>");    
    }  


}