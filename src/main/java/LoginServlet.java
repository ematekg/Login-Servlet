import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String un="";
        Object uns= req.getSession().getAttribute("username");
        if(uns!=null){
            req.getSession().removeAttribute("username");
        }

                for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("username")) {
                 un = cookie.getValue();



            }
        }

        PrintWriter out=resp.getWriter();
        out.print("<html><head><title>Test</title></head><body>");
        out.print("<form method='post'>");
        out.print("<p>Login Form</p>");
        out.print("User Name :<input type='text' name='username' value="+un+"><br><br>");
        out.print("Password :<input type='password' name='password'/><br><br>");
        out.print("Remember Me : <input type='checkbox' name='check' value='checked' checked><br><br>");
        out.print("<input type='submit' />");
        out.print("</form>");
        out.print("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        for (Cookie cookie : req.getCookies()) {
           if (cookie.getName().equals("username")) {
                String un = cookie.getValue();
                System.out.println("the cookie now is "+ un);
            }
            else
           {
               System.out.println("no cookie present");
           }
        }

        PrintWriter out = resp.getWriter();
        out.print("<html><head><title>Welcome</title></head><body>");
        out.print("<p>Welcome" + " "+ (String) req.getSession().getAttribute("username") +" </p><br><br>");
        out.print(" <a href='/account'>Go to Your Account:</a><br><br>");
        out.print("<a href='/login'>Log Out</a>");
        out.print("</body></html>");
    }
}
