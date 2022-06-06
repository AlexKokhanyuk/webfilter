package ua.kohaniuk.webfilter.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kohaniuk.webfilter.servises.BlackListFileReader;

import javax.servlet.*;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Oleksandr Kokhaniuk
 * @created 5/10/2022, 11:07 PM
 */
@Component
public class WebFilter implements Filter {

    BlackListFileReader blackListFileReader;
    private static final Logger LOGGER = LoggerFactory.getLogger(WebFilter.class);

    @Autowired
    public WebFilter(BlackListFileReader blackListFileReader) {
        this.blackListFileReader = blackListFileReader;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
            throws IOException, ServletException {
        blackListFileReader.toRead();
        if (blackListFileReader.getSetOfIP().contains(request.getRemoteAddr())) {
            System.out.println(request.getRemoteAddr() + ": Access disallowed");
            LOGGER.warn(request.getRemoteAddr() + ": Access disallowed");
        } else {
            filterchain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {
    }
}
