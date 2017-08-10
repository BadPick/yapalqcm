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
 * Servlet Filter implementation class CandidatFiltre
 */
@WebFilter("/Candidatsdfsdf/*")
public class CandidatFiltre implements Filter {

    /**
     * Default constructor. 
     */
    public CandidatFiltre() {
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
		if (util != null && util.getRole()!=null && !util.getRole().getName().equals("Candidat")) {
			rd=((HttpServletRequest)request).getRequestDispatcher(((HttpServletRequest)request).getContextPath()+"/index.jsp");
			rd.forward(request, response);
		}else{
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
