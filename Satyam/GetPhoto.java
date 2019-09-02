import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/GetPhoto")
public class GetPhoto extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            ServletContext application = request.getServletContext();
            db.DbConnect db = (db.DbConnect)application.getAttribute("dbc");
            int uid=Integer.parseInt(request.getParameter("uid"));
        byte b[]=db.getphoto(uid);
        if(b==null){
                FileInputStream fin=new FileInputStream("E:\\Umeedo_Ke_Pankh\\xyz.jpg");
                b=new byte[3500];
                fin.read(b);
        }
            response.getOutputStream().write(b);
        
        }catch(Exception ex){
            ex.printStackTrace();
                FileInputStream fin=new FileInputStream("E:\\Umeedo_Ke_Pankh\\xyz.jpg");
                byte[]b=new byte[3500];
                fin.read(b);
                response.getOutputStream().write(b);
        }
        
    }

}
