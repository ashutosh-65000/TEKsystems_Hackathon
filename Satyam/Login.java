import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import db.DbConnect;

@WebServlet("/Login")
public class Login extends HttpServlet {
public void service(HttpServletRequest req, HttpServletResponse res)throws IOException,ServletException{
        ServletContext application = req.getServletContext();
        DbConnect db = (DbConnect)application.getAttribute("dbc");
        HttpSession session = req.getSession();
        if(db == null){
            db = new DbConnect();
            application.setAttribute("dbc", db);
        }

        String e = req.getParameter("email").toLowerCase();
        String p = req.getParameter("password");
        String calling = req.getParameter("whocalling");
        ResultSet rs = db.checkUser(e,p);
        try{
            if(rs.next()){
                HashMap hm = new HashMap();
                hm.put("uid", rs.getInt(1));
                hm.put("email",e);
                hm.put("name",rs.getString(5));
                session.setAttribute("UserDetails", hm);
                if(calling.equals("admin_login")){
                    if(rs.getInt(1)==1){
                        session.setAttribute("signedIn", "admin");
                        res.sendRedirect("adminwindow.jsp");
                    }
                    else{
                        session.setAttribute("LoginIn", "Please Check Credentials");
                        res.sendRedirect("admin_login.jsp");
                    }
                }
                else{
                    if(rs.getInt(1)!=1){
                        session.setAttribute("signedIn","student");
                        res.sendRedirect("studentwindow.jsp");
                    }
                    else{
                        session.setAttribute("LoginIn","Please Check Credentials");
                        res.sendRedirect("student_login.jsp");
                    }
                }
            }
            else{
                session.setAttribute("LoginIn", "Please Check Credentials");
                if(calling.equals("admin_login"))
                    res.sendRedirect("admin_login.jsp");
                else if(calling.equals("student_login"))
                    res.sendRedirect("student_login.jsp");
                else if(calling.equals("student-login-failure"))
                    res.sendRedirect("student-login-failure.jsp");
                else if(calling.equals("student-login"))
                    res.sendRedirect("student-login-failure.jsp");
                else
                    res.sendRedirect("login_response.jsp");
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
}