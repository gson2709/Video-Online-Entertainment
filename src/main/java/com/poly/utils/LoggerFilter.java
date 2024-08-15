package com.poly.utils;

import java.io.IOException;
import java.util.Date;

import com.poly.entity.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/admin/*")
public class LoggerFilter implements HttpFilter{

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		String uri = req.getRequestURI();
		Date date = new Date();
		chain.doFilter(req, resp);
	}
	
}
