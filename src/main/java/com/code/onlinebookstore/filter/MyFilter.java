package com.impetus.onlinebookstore.filter;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter class for handling request validation.
 */
public class MyFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(MyFilter.class);

    /**
     * This method intercepts HTTP requests and performs filtering based on the request URI.
     * If the request URI starts with "/auth/login", it allows the request to proceed.
     * If the request URI starts with "/purchase/book/", it extracts the user ID from the URI
     * and checks if it is valid. If valid, it allows the request to proceed; otherwise, it sends
     * an error response indicating an invalid user ID.
     * If the request URI starts with "/purchase/history/", it performs similar validation for the user ID.
     * For any other request URI, it allows the request to proceed.
     *
     * @param request  The ServletRequest object representing the HTTP request.
     * @param response The ServletResponse object representing the HTTP response.
     * @param chain    The FilterChain object used to invoke the next filter in the chain.
     * @throws IOException      If an I/O error occurs while processing the request or response.
     * @throws ServletException If any servlet-related error occurs while processing the request.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String requestURI = httpServletRequest.getRequestURI();

            if (requestURI.startsWith("/auth/login")) {
                chain.doFilter(request, response);
            } else if (requestURI.startsWith("/purchase/book/")) {
                // Extracting userId from the request URI
                String[] parts = requestURI.split("/");
                if (parts.length >= 4) {
                    String userIdParam = parts[parts.length - 1];
                    if (isLongValidAndGreaterThanOne(userIdParam)) {
                        // If valid, continue with the filter chain
                        chain.doFilter(request, response);
                        return; // Stop further processing
                    }
                }
                // Invalid userId parameter, send an error response
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpResponse.getWriter().write("Invalid userId parameter");
                return; // Stop further processing
            } else if (requestURI.startsWith("/purchase/history/")) {
                // Extracting userId from the request URI
                String[] parts = requestURI.split("/");
                if (parts.length >= 4) {
                    String userIdParam = parts[parts.length - 1];
                    if (isLongValidAndGreaterThanOne(userIdParam)) {
                        // If valid, continue with the filter chain
                        chain.doFilter(request, response);
                        return; // Stop further processing
                    }
                }
                // Invalid userId parameter, send an error response
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpResponse.getWriter().write("Invalid userId parameter");
                return; // Stop further processing
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    /**
     * Checks if the given string is a valid long integer and greater than or equal to one.
     *
     * @param userIdParam The string representing the user ID
     * @return true if the user ID is valid and greater than or equal to one, otherwise false
     */
    private boolean isLongValidAndGreaterThanOne(String userIdParam) {
        try {
            if (userIdParam != null) {
                long userId = Long.parseLong(userIdParam);
                return userId>=1;
            }
        } catch (NumberFormatException e) {
            logger.error("Invalid Number Format");
        }
        return false;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}


