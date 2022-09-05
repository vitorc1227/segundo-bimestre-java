package br.edu.ifg.luziania.bsi.pw.rest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "*")
public class RESTConfig implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        res.setHeader("Acess-Control-Allow-Origin", "*");
        res.setHeader("Acess-Control-Allow-Credentials", "true");
        res.setHeader("Acess-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        res.setHeader("Acess-Control-Allow-Headers", "accept, content-type, x-requested-with, authorization");

        filterChain.doFilter(req, res);
    }

    @Override
    public void destroy() { }
}
