import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

public class Account extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object un= req.getSession().getAttribute("username");

        if(un!=null) {
            PrintWriter out = resp.getWriter();
            out.print("<html><head><title>Test</title></head><body>");

            out.print("<p>Welcome to Your Account</p>");

            out.print("</body></html>");
//        for (Cookie cookie : req.getCookies()) {
////            if (cookie.getName().equals("username")) {
////                String un = cookie.getValue();
////                System.out.println("Session param is" + un);
////            }
////        }

        }
        else {

           resp.sendRedirect("/login");

        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
