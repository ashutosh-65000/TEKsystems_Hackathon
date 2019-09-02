import db.DbConnect;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Distribute")
public class Distribute extends HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        ServletContext application = request.getServletContext();
        HttpSession session = request.getSession();
        DbConnect db = (DbConnect)application.getAttribute("dbc");
        int bid = Integer.parseInt(request.getParameter("bid"));
        int num = Integer.parseInt(request.getParameter("num"));
        int stock = db.getStock(bid);
        if(stock>-1 && stock>=num){
           db.updateStock(-num, bid);
           session.setAttribute("updateStock", "Updated Successfuly");
           response.sendRedirect("adminwindow.jsp");
        }
        else if(stock == -1){
            session.setAttribute("updateStock", "Wrong Book ID, Please try Again");
            response.sendRedirect("adminwindow.jsp");
        }
        else if(stock < num){
            session.setAttribute("updateStock", "Stock is not enough");
            response.sendRedirect("adminwindow.jsp");
        }
        else{
            response.sendRedirect("adminwindow.jsp");
        }
    }
}
