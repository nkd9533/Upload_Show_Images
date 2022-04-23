import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import com.oreilly.servlet.*;
import java.sql.*;
@WebServlet("/image12")
public class StoreImageServlet extends HttpServlet
{
public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
{
PrintWriter out=res.getWriter();
String path=getServletContext().getRealPath("/image");
MultipartRequest mpr=new MultipartRequest(req,path,500*1024*1024);
String path1=mpr.getOriginalFileName("file");

String path2=path+"/"+path1;
FileInputStream fin=new FileInputStream(path2);
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
PreparedStatement ps=c.prepareStatement("insert into image values(?,?)");
ps.setBinaryStream(2,fin,fin.available());
ps.setString(1,"me");
ps.executeUpdate();
c.close();
}
catch(Exception e)
{
	e.printStackTrace();
}
out.println("<html><body>");
out.println("image saved successfully<br>");
out.println("<a href='image13'>show the image</a>");

out.println("</body></html>");
}

}