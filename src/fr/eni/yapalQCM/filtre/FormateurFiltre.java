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
import javax.servlet.http.HttpSession;

import fr.eni.yapalQCM.bo.Utilisateur;

/**
 * Servlet Filter implementation class FormateurFiltre
 */
@WebFilter("/Formateur/")
public class FormateurFiltre implements Filter {

    /**
     * Default constructor. 
     */
    public FormateurFiltre() {
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
		HttpSession session;
		session = ((HttpServletRequest)request).getSession();
		RequestDispatcher rd;
		Utilisateur util = (Utilisateur) session.getAttribute("user");
		if (util.getRole().getName()!="Formateur") {
			rd=((HttpServletRequest)request).getRequestDispatcher(((HttpServletRequest)request).getContextPath()+"/index.jsp");
			rd.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
