import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import db.DbConnect;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Forgot")
public class Reset extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            HttpSession session=request.getSession();
            String e=request.getParameter("email");
            DbConnect db=new DbConnect();
            String p=db.getUser(e);
            if(p!=null){
                final String SEmail="Your Email ID";
                final String SPass="Your Password";
                final String REmail=e;
                final String Sub="Your Password is Here from UKP!";
                final String Body="Your Email Id: "+e+" and Password: "+p;
                //mail send Code
            Properties props=new Properties();
            props.put("mail.smtp.host","smtp.gmail.com");
            /*props.put("mail.smtp.socketFactory.port","465");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");*/ //kinda of out dated code 
            props.put("mail.smtp.port","465");
            props.put("mail.smtp.ssl.enable","true");
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.port","465");
            Session ses=Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(SEmail,SPass);
                }
            }
            );
            Message message=new MimeMessage(ses);
            message.setFrom(new InternetAddress(SEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(REmail));
            message.setSubject(Sub);
            message.setContent(Body,"text/html" );
            Transport.send(message);
            session.setAttribute("LoginIn","Mail Sent successfully.");
            response.sendRedirect("frgtpswd.jsp");
            }else{
                session.setAttribute("LoginIn", "Wrong Email ID");
                response.sendRedirect("frgtpswd.jsp");
            }
        }catch(Exception ex){
            
        }
        
    }

}
