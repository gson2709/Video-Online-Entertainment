package com.poly.asm;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.sound.midi.Soundbank;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.xssf.binary.XSSFBHyperlinksTable;

import com.oracle.wls.shaded.org.apache.bcel.generic.Select;
import com.poly.dao.ReportDAO;
import com.poly.dao.UserDAO;
import com.poly.dao.VideoDAO;
import com.poly.entity.LikesCount;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.utils.CookieUtils;
import com.poly.utils.XImage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet({"/manager/user", "/manager/user/edit/*", "/manager/user/update", "/manager/user/delete", "/manager/user/reset", 
			"/manager/video", "/manager/video/edit/*", "/manager/video/first", "/manager/video/prev", "/manager/video/next", "/manager/video/last", "/manager/video/create", "/manager/video/update", "/manager/video/delete", "/manager/video/reset", 
			"/manager/report", "/manager/report/export", "/manager/report1/*", "/manager/report2/*"
})
public class HomeAdminServlet extends HttpServlet{
	
	UserDAO userDAO = new UserDAO();
	VideoDAO videoDAO = new VideoDAO();
	ReportDAO reportDAO = new ReportDAO();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		viewVideos(req);
		String uri = req.getRequestURI();
		if (uri.contains("video")) {
			this.doVideo(req, resp);
		}
		else if (uri.contains("user")) {
			this.doUser(req, resp);
		}
		else if (uri.contains("report")) {
			this.doReport(req, resp);
		}
		req.getRequestDispatcher("/views/admin/index.jsp").forward(req, resp);
	}
	
	private void doUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		fillTableUser(req);
		String method = req.getMethod();
		if (method.equalsIgnoreCase("post")) {
			String uri = req.getRequestURI();
			if (uri.contains("update")) {
				updateUser(req);
			}
			else if (uri.contains("delete")) {
				deleteUser(req);
			}
			else if (uri.contains("reset")) {
				setFormUser(req);
			}
		}
		else {
			setFormUser(req);
		}
		req.setAttribute("chooseAdmin", "/views/admin/users.jsp");
	}
	
	private void doVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		if (method.equalsIgnoreCase("post")) {
			String uri = req.getRequestURI();
			if (uri.contains("create")) {
				insertVideo(req);
			}
			else if (uri.contains("update")) {
				updateVideo(req);
			}
			else if (uri.contains("delete")) {
				deleteVideo(req);
			}
			else if (uri.contains("reset")) {
				setFormVideo(req);
			}
		}
		else {
			fillTableVideo(1, req);
			setFormVideo(req);
			double page = req.getSession().getAttribute("page")!=null?Double.parseDouble(String.valueOf(req.getSession().getAttribute("page"))):1;
			System.out.println("Page: "+page);
//			req.getSession().setAttribute("page", page);
			String uri = req.getRequestURI();
			if (uri.contains("first")) {
				fillTableVideo(1, req);
				req.getSession().setAttribute("page", 1);
			}
			else if (uri.contains("prev")) {
				if (page == 1) {
					page = Math.ceil((double) videoDAO.selectAll().size() / 5.0);
					req.getSession().setAttribute("page", page);
				}
				else {
					page--;
					req.getSession().setAttribute("page", page);
				}
				fillTableVideo(page, req);
			}
			else if (uri.contains("next")) {
				System.out.println("Current page: "+page);
				if (page == Math.ceil((double) videoDAO.selectAll().size() / 5.0)) {
					page = 1;
					req.getSession().setAttribute("page", page);
				}
				else {
					page++;
					req.getSession().setAttribute("page", page);
				}
				fillTableVideo(page, req);
			}
			else if (uri.contains("last")) {
				page = Math.ceil((double) videoDAO.selectAll().size() / 5.0);
				req.getSession().setAttribute("page", page);
				fillTableVideo(page, req);
			}
		}
		req.setAttribute("chooseAdmin", "/views/admin/videos.jsp");
	}
	
	private void doReport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("favoriteCount", reportDAO.getVideoFavoriteCount());
		fillFavoriteUsers(req);
		if (req.getMethod().equalsIgnoreCase("post")) {
			if (req.getRequestURI().contains("export")) {
				File dir = new File(req.getServletContext().getRealPath("/files"));
				if (!dir.exists()) {
					dir.mkdirs();
				}
				Part export = req.getPart("export");
				if (export.getSize() > 0) {
					File excel = new File(dir, export.getSubmittedFileName());
					export.write(excel.getAbsolutePath());
				}
			}
		}
		else {
			req.setAttribute("usersLike", reportDAO.getUsersLikeByVideo(videoDAO.selectAll().get(0).getId()));
			req.setAttribute("senders", reportDAO.getSenderByVideo(videoDAO.selectAll().get(0).getId()));
			String uri = req.getRequestURI();
			String selected1 = "", selected2 = "";
			if (uri.contains("/report1")){
				String videoId = uri.substring(uri.lastIndexOf("/") + 1);
				selected1 = videoId;
				
				req.setAttribute("usersLike", reportDAO.getUsersLikeByVideo(videoId));
			}
			else if (uri.contains("/report2")) {
				String videoId = uri.substring(uri.lastIndexOf("/") + 1);
				selected2 = videoId;
				
				req.setAttribute("senders", reportDAO.getSenderByVideo(videoId));
			}
			req.setAttribute("selected1", selected1);
			req.setAttribute("selected2", selected2);
		}
		req.setAttribute("chooseAdmin", "/views/admin/reports.jsp");
	}
	
	void viewVideos(HttpServletRequest req) {
		req.setAttribute("videos", videoDAO.selectAll());
		User user = userDAO.selectById(CookieUtils.get("username", req));
		req.setAttribute("fullname", "Chào mừng "+user.getFullname());
	}
	void fillTableUser(HttpServletRequest req) {
		List<User> users = userDAO.selectAll();
		req.setAttribute("users", users);
		int userNumber = users.size();
		req.setAttribute("userNumber", userNumber + (userNumber>1?" users":" user"));
	}
	void fillTableVideo(double pos, HttpServletRequest req) {
		List<Video> videos = videoDAO.page(pos, 5);
		req.setAttribute("videos", videos);
		int videoNumber = videos.size();
		req.setAttribute("videoNumber", videoNumber + (videoNumber>1?" videos":" video"));
	} 
	void fillFavoriteUsers(HttpServletRequest req) {
		List<Video> list = videoDAO.selectAll();
		req.setAttribute("videos", list);
		req.getSession().setAttribute("usersLike", reportDAO.getUsersLikeByVideo(list.get(0).getId()));
	}
	
	void saveImage(Part part, HttpServletRequest req) throws IOException {
		File dir = new File(req.getServletContext().getRealPath("/files"));
		if (!dir.exists()) { // tạo nếu chưa tồn tại
			dir.mkdirs();
		}
		File imageFile = new File(dir, part.getSubmittedFileName());
		part.write(imageFile.getAbsolutePath());
		XImage.save(imageFile);
	}
	void insertVideo(HttpServletRequest req) {
		try {
			Video video = new Video();
			BeanUtils.populate(video, req.getParameterMap());
			Part image = req.getPart("poster");
			if (image.getSize() > 0) {
				saveImage(image, req);
				video.setPoster(req.getPart("poster").getSubmittedFileName());
			}
			else {
				System.out.println(req.getParameter("viewImage"));
			}
			videoDAO.insert(video);
			fillTableVideo(1, req);
			setFormVideo(req);
			req.setAttribute("message", "Đã thêm video mới");
		} catch (Exception e) {
			req.setAttribute("message", "Thêm thất bại");
			e.printStackTrace();
		}
	}
	void updateVideo(HttpServletRequest req) {
		Video video = (Video) req.getSession().getAttribute("video");
		if (video!=null) {
			try {
				BeanUtils.populate(video, req.getParameterMap());
				Part image = req.getPart("poster");
				if (image.getSize() > 0) {
					saveImage(image, req);
					video.setPoster(req.getPart("poster").getSubmittedFileName());
					System.out.println(req.getPart("poster").getSubmittedFileName());
				}
				else {
					System.out.println(req.getParameter("viewImage"));
				}
				videoDAO.update(video);
				fillTableVideo(1, req);
				setFormVideo(req);
				req.setAttribute("message", "Đã cập nhật thông tin video");
			} catch (Exception e) {
				req.setAttribute("message", "Cập nhật thất bại");
				e.printStackTrace();
			}
		}
	}
	void deleteVideo(HttpServletRequest req) {
		Video video = (Video) req.getSession().getAttribute("video");
		if (video!=null) {
			try {
				BeanUtils.populate(video, req.getParameterMap());
				videoDAO.delete(video.getId());
				fillTableVideo(1, req);
				setFormVideo(req);
				req.setAttribute("message", "Đã xóa video");
			} catch (Exception e) {
				req.setAttribute("message", "Xóa thất bại");
				e.printStackTrace();
			}
		}
	}
	void setFormVideo(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String videoId = uri.substring(uri.lastIndexOf("/") + 1);
		Video video = videoDAO.selectById(videoId);
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.getSession().setAttribute("video", video);
		double page = req.getSession().getAttribute("page")!=null?Double.parseDouble(String.valueOf(req.getSession().getAttribute("page"))):1;
		fillTableVideo(page, req);
	}
	
	void setFormUser(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String id = uri.substring(uri.lastIndexOf("/") + 1);
		User user = userDAO.selectById(id);
		req.getSession().setAttribute("user", user);	
	}
	void updateUser(HttpServletRequest req) {
		User user = (User) req.getSession().getAttribute("user");
		if (user!=null) {
			try {
				BeanUtils.populate(user, req.getParameterMap());
				userDAO.update(user);
				fillTableUser(req);
				setFormUser(req);
				req.setAttribute("message", "Đã cập nhật thông tin user");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			req.setAttribute("message", "Bạn chưa chọn user cần cập nhật");
		}
	}
	void deleteUser(HttpServletRequest req) {
		User user = (User) req.getSession().getAttribute("user");
		if (user!=null) {
			try {
				BeanUtils.populate(user, req.getParameterMap());
				userDAO.delete(user.getUsername());
				fillTableUser(req);
				setFormUser(req);
				req.setAttribute("message", "Đã xóa user");
			} catch (Exception e) {
				req.setAttribute("message", "Lỗi khi xóa user");
			}
		}
		else {
			req.setAttribute("message", "Bạn chưa chọn user cần xóa");
		}
	}
}
