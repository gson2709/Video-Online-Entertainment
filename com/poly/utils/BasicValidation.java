package com.poly.utils;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import jakarta.servlet.http.HttpServletRequest;

public class BasicValidation {
	
	static EntityManager em = JpaUtils.getEntityManager();
	
	public static boolean checkNull(String input, String inputName, String message, HttpServletRequest req) {
		if (input.length() == 0) {
			req.setAttribute(message, inputName + " không được để trống");
			return true;
		}
		else {
			req.setAttribute(message, "");
			return false;
		}
	}
	public static boolean isExist(Object entity, String id) {
		return em.find(entity.getClass(), id) != null;
	}
}
