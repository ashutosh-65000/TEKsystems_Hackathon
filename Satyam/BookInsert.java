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

@WebServlet("/BookInsert")
public class BookInsert extends HttpServlet {
public void service(HttpServletRequest req, HttpServletResponse res)throws IOException,ServletException{
        ServletContext application = req.getServletContext();
        DbConnect db = (DbConnect)application.getAttribute("dbc");
        HttpSession session = req.getSession();
        String bname = req.getParameter("name").toLowerCase();
        int count = Integer.parseInt(req.getParameter("count"));
        ResultSet rs = db.checkinStock(bname);
        try{
            if(rs.next()){
                int bid = rs.getInt(1);
                HashMap hm = (HashMap)session.getAttribute("UserDetails");
                String temp1 = db.updateStock(count, bid);
                String temp2 = db.insertinWarehouse(bname, bid, (Integer)hm.get("uid"), count);
                session.setAttribute("donate","Thank you for Donation");
                res.sendRedirect("studentwindow.jsp");
            }
            else{
                HashMap hm = (HashMap)session.getAttribute("UserDetails");
                String temp1 = db.insertinStock(bname, count);
                ResultSet rs2 = db.checkinStock(bname);
                rs2.next();
                int bid = rs2.getInt(1);
                String temp2 = db.insertinWarehouse(bname, bid, (Integer)hm.get("uid"), count);
                session.setAttribute("donate","Thank you for Donation");
                res.sendRedirect("studentwindow.jsp");
            }
        }
        catch(SQLException se){
            se.printStackTrace();
            res.sendRedirect("studentwindow.jsp");
        }
    }
}