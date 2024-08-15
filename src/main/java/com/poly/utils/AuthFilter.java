package com.poly.utils;

import java.io.IOException;

import com.poly.entity.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/manager", "/index/share/*", "/index/myfavorite", "/index/favorite/*"})
public class AuthFilter implements HttpFilter{

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws ServletException, IOException {
		String uri = req.getRequestURI();
		User user = (User) req.getSession().getAttribute("user");
		String error = "";
		if (user == null) {
			error = resp.encodeURL("Please login!");
		}
		else if (!user.getAdmin() && uri.contains("/manager")){
			error = resp.encodeURL("Please login with admin role");
		}
		if (!error.isEmpty()) {
			req.getSession().setAttribute("securi", uri);
			resp.sendRedirect("/ASM/index/login?error=" + resp.encodeURL(error));
		}
		else {
			chain.doFilter(req, resp);
		}
	}
	
}
