package com.transparent.politics.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmberServlet extends HttpServlet {

	private static final long serialVersionUID = -4869033449765247770L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	if (requestURI.startsWith("/dist/index.html") ||requestURI.startsWith("/dist/assets") || requestURI.startsWith("/dist/fonts")) {
    		// Serve static content of the original request
			getServletContext().getNamedDispatcher("default").forward(request, response);
    	} else {
    		// Forward request to the ember html file
    		request.getRequestDispatcher("/dist/index.html").forward(request, response);
    	}
    }
	
}