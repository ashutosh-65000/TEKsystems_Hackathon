import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import db.DbConnect;
import java.io.InputStream;
import javax.servlet.http.Part;

@WebServlet("/Register")
@MultipartConfig
public class Register extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res)throws IOException,ServletException{
        ServletContext application = req.getServletContext();
        DbConnect db = (DbConnect)application.getAttribute("dbc");
        HttpSession session = req.getSession();
        if(db == null){
            db = new DbConnect();
            application.setAttribute("dbc", db);
        }
        String e = req.getParameter("email").toLowerCase();
        String n = req.getParameter("name");
        long ph = Long.parseLong(req.getParameter("phone"));
        String p = req.getParameter("password");
        int a = Integer.parseInt(req.getParameter("age"));
        Part photo=req.getPart("photo");
            InputStream in=null;
            if(photo!=null)
                in=photo.getInputStream();
        String checking = db.validateUser(e);
        if(checking.equals("Done")){
            String s = db.setUser(n,e,ph,a,p);
            if(s.equals("Done")){
                int uid = db.getUidPhoto(e);
                String photouploaded = db.insertPhoto(uid, in);
                if(photouploaded.equals("Success")){
                    session.setAttribute("register","Registration Successful!");
                }
                else{
                    session.setAttribute("register", "Photo could not be uploaded");
                }
                res.sendRedirect("register_response.jsp"); // this gives IOException
            }
            else{
                session.setAttribute("msg", s);
                res.sendRedirect("student-login-failure.jsp"); // this gives IOException
            }
        }
        else{
            session.setAttribute("msg", checking);
            res.sendRedirect("student-login-failure.jsp");
        }
    }
}