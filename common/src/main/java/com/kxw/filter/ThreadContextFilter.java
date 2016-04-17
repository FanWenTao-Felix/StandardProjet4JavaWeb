package com.kxw.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kingson.wu on 2015/9/24.
 */
public class ThreadContextFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(ThreadContextFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            ThreadContext.bind();
            filterChain.doFilter(request, response);

        }catch (Exception e) {
            logger.error("ThreadContextFilter error:{}" ,e);
        }finally {
            ThreadContext.unbind();
        }

    }
}
