package lingoHigh.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by DuHongcai on 2016/9/18.
 */
public class SetCharacterEncodingFilter implements Filter {

    private FilterConfig filterConfig;
    private String defaultCharset = "UTF-8";
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String charset = filterConfig.getInitParameter("charset");
        if (charset == null){
            charset = defaultCharset;
        }
        request.setCharacterEncoding(charset);
        response.setCharacterEncoding(charset);
        response.setContentType("text/html;charset="+charset);

        CharacterEncodingRequest requestWrapper = new CharacterEncodingRequest(request);
        chain.doFilter(requestWrapper,response);
    }
    public void destroy() {
    }
}
