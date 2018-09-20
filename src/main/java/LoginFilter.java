import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpresponse = (HttpServletResponse) servletResponse;
        if(httpRequest.getMethod().equalsIgnoreCase("POST")){
            UserDb userDb=new UserDb();
            HashMap<String,User> users=userDb.getUsers();

            String username = ((HttpServletRequest)servletRequest).getParameter("username");
            String pass = ((HttpServletRequest)servletRequest).getParameter("password");
            String checkbox = ((HttpServletRequest)servletRequest).getParameter("check");
            User user=users.get(username);
            if(user!=null && user.getPassword().equals(pass)){
                httpRequest.getSession().setAttribute("username",user.getUsername());
                    if(checkbox!=null){

                        Cookie cookie= new Cookie("username",user.getUsername());
                        cookie.setMaxAge(2592000);
                        httpresponse.addCookie(cookie);

                    }
                    else if(checkbox==null) {
                        System.out.println("Un checked.......");
                        for (Cookie cookie : httpRequest.getCookies()) {
                            if (cookie.getName().equals("username")) {
                                cookie.setMaxAge(0);
                                httpresponse.addCookie(cookie);


                            }
                        }
                    }

                filterChain.doFilter(servletRequest, httpresponse);




            }
            else{
                httpresponse.sendRedirect("/login");
            }

        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }



    }

    @Override
    public void destroy() {

    }
}
