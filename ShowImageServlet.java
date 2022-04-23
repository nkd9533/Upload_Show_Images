import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.oreilly.servlet.*;
import java.sql.*;

public class ShowImageServlet extends GenericServlet
{
InputStream f=null;
public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
{
res.setContentType("text/html");
PrintWriter out=res.getWriter();

try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
PreparedStatement ps=c.prepareStatement("Select * from image");
//ps.setString(1,"5076");
ResultSet rs=ps.executeQuery();
String path=getServletContext().getRealPath("/");
while(rs.next())
{

f=rs.getBinaryStream("image1");
FileOutputStream fos=new FileOutputStream(path+"\\"+rs.getString(1)+".jpg");
int i=0;
while((i=f.read())!=-1)
{
fos.write(i);
}
out.println("<img src='"+rs.getString(1)+".jpg' width='160' height='170'>");
}
}
catch(Exception e)
{
out.println(e);
}
}

}