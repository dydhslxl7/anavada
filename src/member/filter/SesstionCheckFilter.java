package member.filter;

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

import member.model.vo.Member;

/**
 * Servlet Filter implementation class SesstionCheckFilter
 */
@WebFilter("*.ss")
public class SesstionCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SesstionCheckFilter() {
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
		System.out.println("SessionCheckFilter doFilter() run...");
		// 로그인 상태를 확인
		HttpServletRequest hrequest = (HttpServletRequest)request;
		
		Member loginMember = (Member)hrequest.getSession(false).getAttribute("loginMember");
										// getSession(false)새로 만들지 않고 있는 거 가져옴
		if(loginMember == null) {
			RequestDispatcher view = hrequest.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", "필터 : 로그인해야 이용 할 수 있습니다.");
			view.forward(hrequest, response);
		} else {
			//요청한 서블릿으로 넘김
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
