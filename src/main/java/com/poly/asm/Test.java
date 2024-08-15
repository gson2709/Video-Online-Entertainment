package com.poly.asm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.poly.dao.UserDAO;
import com.poly.entity.User;
import com.poly.utils.ExcelUtis;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet({"/importExcel", "/importExcel/import", "/importExcel/view"})
public class Test extends HttpServlet{
	UserDAO userDAO = new UserDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("fileSize", 0);
		req.setAttribute("users", userDAO.selectAll());
		req.getRequestDispatcher("/importExcel.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		List<User> list = new ArrayList<User>();
		if (uri.contains("import")) {
			Part file = req.getPart("excel");
			if (file.getSize() > 0) {
				long fileSize = file.getSize();
				req.setAttribute("fileSize", fileSize);
				File dir = new File(req.getServletContext().getRealPath("/files"));
				if (!dir.exists()) { // tạo nếu chưa tồn tại
					dir.mkdirs();
				}
				File excelFile = new File(dir, file.getSubmittedFileName());
				file.write(excelFile.getAbsolutePath());
				try {
					list = ExcelUtis.getData(excelFile);
					for (User user : list) {
						if (userDAO.selectById(user.getUsername()) == null) {
							userDAO.insert(user);
						}
					}
					req.setAttribute("message", "Import excel file successfully");
					req.setAttribute("users", userDAO.selectAll());
				} catch (Exception e) {
					req.setAttribute("message", "Error while read file");
					e.printStackTrace();
				}
			}
			else {
				req.setAttribute("message", "Please select excel file");
			}
		}
		else if (uri.contains("view")) {
			req.setAttribute("users", userDAO.selectAll());
		}
		req.getRequestDispatcher("/importExcel.jsp").forward(req, resp);
	}
}
