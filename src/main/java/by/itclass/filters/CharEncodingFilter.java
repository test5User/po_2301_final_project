package by.itclass.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

import static by.itclass.constants.ApplicationConstants.*;

@WebFilter(value = {PRINT_ORDER_CONTROLLER, ORDER_CONTROLLER, ORDER_HISTORY_CONTROLLER})
public class CharEncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
