package fr.eni.yapalQCM.filtre;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class ConnexionFiltre
 */
@WebFilter("/")
public class ConnexionFiltre implements Filter {

    /**
     * Default constructor. 
     */
    public ConnexionFiltre() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("entr�e dans le filtre ConnexionFiltre");
		HttpSession session;
		session = ((HttpServletRequest)request).getSession();
		RequestDispatcher rd;
		if (session.getAttribute("user")!=null) {			
			System.out.println("Sortie filtre utilisateur non null: "+session.getAttribute("user").toString());			
			chain.doFilter(request, response);
		} 
		else{			
			rd=((HttpServletRequest)request).getRequestDispatcher("/index.jsp");
			System.out.println("Sortie filtre utilisateur null: ");
			rd.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
