package com.poly.asm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.dao.FavoriteDAO;
import com.poly.dao.ShareDAO;
import com.poly.dao.UserDAO;
import com.poly.dao.VideoDAO;
import com.poly.entity.Favorite;
import com.poly.entity.Share;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.utils.BasicValidation;
import com.poly.utils.CookieUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({ "/index/home", "/index/myfavorite", "/index/favorite/*", "/index/unlike/*", "/index/detail/*",
			"/index/share/*", "/index/login", "/index/forgot", "/index/forgot/enterCode", "/index/forgot/confirm", "/index/signup", "/index/change", "/index/edit-profile",
			"/index/logoff", "/manager" })
public class HomeUserServlet extends HttpServlet{
	
	VideoDAO videoDAO = new VideoDAO();
	UserDAO userDAO = new UserDAO();
	FavoriteDAO favDAO = new FavoriteDAO();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("roleNav", "/views/user/nav.jsp");
		req.setAttribute("choose", "/views/user/home.jsp");
		viewVideos(CookieUtils.get("username", req), req);
		String uri = req.getRequestURI();
		if (uri.contains("/manager")){
			req.setAttribute("roleNav", "/views/admin/nav.jsp");
			req.setAttribute("choose", "/views/user/home.jsp");
		}
		else if (uri.contains("/home")){
//			viewVideos(req);
		}
		else if (uri.contains("/myfavorite")) {
			if (!CookieUtils.get("username", req).equals("")) {
				this.doFavorite(req, resp);
			}
			else {
				req.setAttribute("fullname", "Bạn chưa đăng nhập");
				req.setAttribute("choose", "/views/user/home.jsp");
			}
		}
		else if (uri.contains("/favorite")) {
			req.setAttribute("choose", "/views/user/favorite.jsp");
			this.addFavorite(req);
		}
		else if (uri.contains("/unlike")) {
			req.setAttribute("choose", "/views/user/favorite.jsp");
			this.deleteFavorite(req);
		}
		else if (uri.contains("/detail")) {
			this.doDetail(req, resp);
		}
		else if (uri.contains("/share")) {
			this.doShare(req, resp);
		}
		else if (uri.contains("/login")) {
			this.doLogin(req, resp);
		}
		else if (uri.contains("/forgot")) {
			this.doForgot(req, resp);
		}
		else if (uri.contains("/enterCode")) {
			req.setAttribute("choose", "/views/user/enterCode.jsp");
		}
		else if (uri.contains("/signup")) {
			this.doSignUp(req, resp);
		}
		else if (uri.contains("/change")) {
			this.doChange(req, resp);
		}
		else if (uri.contains("/edit-profile")) {
			this.doEditProfile(req, resp);
		}
		else if (uri.contains("/logoff")) {
			this.logoff(req, resp);
		}
		req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
	}
	
	private void doFavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		viewFavorites(req);
		req.setAttribute("choose", "/views/user/favorite.jsp");
	}
	
	private void doDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("choose", "/views/user/detail.jsp");
		viewDetail(req);
	}
	
	private void doShare(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String videoSelected = req.getRequestURI();
		String videoId = videoSelected.substring(videoSelected.lastIndexOf("/") + 1);
		if (req.getMethod().equalsIgnoreCase("post")) {
			Share share = new Share();
			User user = (User) req.getSession().getAttribute("user");
			System.out.println("Video selected: "+videoSelected);
			Video video = videoDAO.selectById(videoId);
			String email = req.getParameter("email");
			share.setUser(user);
			share.setVideo(video);
			share.setEmail(email);
			ShareDAO shareDAO = new ShareDAO();
			try {
				shareDAO.insert(share);
				req.setAttribute("message", "Đã chia sẻ video");
			} catch (Exception e) {
				req.setAttribute("message", "Video chưa được chia sẻ");
				e.printStackTrace();
			}
			
		}
		req.setAttribute("choose", "/views/user/share.jsp");
	}
	
	private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("choose", "/views/user/login.jsp");
		
		if (req.getMethod().equalsIgnoreCase("post")) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			boolean isRemember = (req.getParameter("isRemember") != null);

			User user = userDAO.selectById(username);
			if (user == null) {
				req.setAttribute("message", "Sai tên đăng nhập");
				req.setAttribute("username", username);
				req.setAttribute("password", password);
			} else if (!user.getPassword().equals(password)) {
				req.setAttribute("message", "Sai mật khẩu");
				req.setAttribute("username", username);
				req.setAttribute("password", password);
			} else {
//				Lưu user đã đăng nhập trong vòng 7 ngày
				CookieUtils.add("username", username, 168, resp);
//				Ghi nhớ tài khoản đã đăng nhập
				int hours = isRemember ? 24 * 7 : 0;
				CookieUtils.add("saveUsername", username, hours, resp);
				CookieUtils.add("savePassword", password, hours, resp);
				
				req.setAttribute("fullname", "Chào mừng "+user.getFullname());
				req.getSession().setAttribute("user", user);
				req.setAttribute("username", username);
				req.setAttribute("password", password);
				req.setAttribute("message", "Đăng nhập thành công");
				
				viewVideos(username, req);
				req.setAttribute("choose", "/views/user/home.jsp");
			}
		}
		else {
			req.setAttribute("username", CookieUtils.get("saveUsername", req));
			req.setAttribute("password", CookieUtils.get("savePassword", req));
		}
	}
	
	private void doForgot(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		String direct = "/views/user/forgot.jsp";
		if (method.equalsIgnoreCase("post")) {
			String username = req.getSession().getAttribute("username") != null?(String) req.getSession().getAttribute("username"):req.getParameter("username");
			String email = req.getSession().getAttribute("email") != null?(String) req.getSession().getAttribute("email"):req.getParameter("email");
			User user = userDAO.selectById(username);
			boolean checkUser = true, checkEmail = true;
			if (username.isEmpty()) {
				req.setAttribute("checkUserExist", "Vui lòng nhập username");
				req.setAttribute("username", username);
				req.setAttribute("email", email);
				checkUser = false;
			} else if (user == null) {
				req.setAttribute("checkUserExist", "Username không tồn tại");
				req.setAttribute("username", username);
				req.setAttribute("email", email);
				checkUser = false;
			} else {
				req.setAttribute("checkUserExist", "");
				req.setAttribute("username", username);
				req.setAttribute("email", email);
				checkUser = true;
			}
			if (email.isEmpty()) {
				req.setAttribute("checkEmailExist", "Vui lòng nhập email");
				req.setAttribute("username", username);
				req.setAttribute("email", email);
				checkEmail = false;
			} else {
				req.setAttribute("checkEmailExist", "");
				req.setAttribute("username", username);
				req.setAttribute("email", email);
				checkEmail = true;
			}
			if (checkUser && checkEmail) {
				if (user.getEmail().equals(email)) {
					req.getSession().setAttribute("username", username);
					req.getSession().setAttribute("email", email);
					direct = "/views/user/enterCode.jsp";
					int codeSent = req.getSession().getAttribute("code")!=null?Integer.parseInt(String.valueOf(req.getSession().getAttribute("code"))):0;
					if (req.getRequestURI().contains("enterCode")) {
						System.out.println("Send code");
						codeSent = generatorCode(email);
						req.getSession().setAttribute("code", codeSent);
					}
					else if (req.getRequestURI().contains("confirm")) {
						System.out.println(codeSent);
						String getCode = "";
						if (req.getParameter("code").equals("")) {
							req.setAttribute("checkCode", "Vui lòng nhập mã xác nhận");
						}
						else {
							getCode = req.getParameter("code");
							int code = Integer.parseInt(getCode);
							if (code == codeSent) {
								req.setAttribute("forgot", true);
								direct = "/views/user/change.jsp";
							} else {
								req.setAttribute("checkCode", "Mã xác nhận không đúng");
							}
						}	
					}
				} else {
					req.setAttribute("checkEmailExist", "Email không trùng khớp");
					req.setAttribute("username", username);
					req.setAttribute("email", email);
				}
			}
		}
		req.setAttribute("choose", direct);
	}
	
	private void doSignUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getMethod().equalsIgnoreCase("post")) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String fullname = req.getParameter("fullname");
			String email = req.getParameter("email");
			boolean checkUser = true, checkPassword = true, checkName = true, checkEmail = true;
			User user = new User();
			if (BasicValidation.checkNull(req.getParameter("username"), "Username", "checkUsername", req)) {
				checkUser = !BasicValidation.checkNull(req.getParameter("username"), "Username", "checkUsername", req);
			}
			else if (BasicValidation.isExist(user, req.getParameter("username"))) {
				req.setAttribute("checkUsername", "Username đã có trong tài khoản khác");
				req.setAttribute("username", username);
				req.setAttribute("password", password);
				req.setAttribute("name", fullname);
				req.setAttribute("email", email);
				checkUser = false;
			}
			if (BasicValidation.checkNull(req.getParameter("password"), "Password", "checkPassword", req)) {
				req.setAttribute("username", username);
				req.setAttribute("password", password);
				req.setAttribute("name", fullname);
				req.setAttribute("email", email);
				checkPassword = !BasicValidation.checkNull(req.getParameter("password"), "Password", "checkPassword", req);
			}
			if (BasicValidation.checkNull(req.getParameter("fullname"), "Fullname", "checkFullname", req)) {
				req.setAttribute("username", username);
				req.setAttribute("password", password);
				req.setAttribute("name", fullname);
				req.setAttribute("email", email);
				checkName = !BasicValidation.checkNull(req.getParameter("fullname"), "Fullname", "checkFullname", req);
			}
			if (BasicValidation.checkNull(req.getParameter("email"), "Email", "checkEmal", req)) {
				req.setAttribute("username", username);
				req.setAttribute("password", password);
				req.setAttribute("name", fullname);
				req.setAttribute("email", email);
				checkEmail = !BasicValidation.checkNull(req.getParameter("email"), "Email", "checkEmail", req);
			}
			if (checkUser && checkPassword && checkName && checkEmail) {
				try {
					BeanUtils.populate(user, req.getParameterMap());
					userDAO.insert(user);
					req.setAttribute("user", user);
					req.setAttribute("message", "Tạo tài khoản thành công");
				} catch (Exception e) {
					req.setAttribute("message", "Đã xảy ra lỗi khi tạo tài khoản");
					e.printStackTrace();
				}
			}
		}
		req.setAttribute("choose", "/views/user/registration.jsp");
	}
	
	private void doChange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean forgot = true;
		User user = new User();
		if (!CookieUtils.get("username", req).equals("")) {
			forgot = false;
			user = userDAO.selectById(CookieUtils.get("username", req));
			req.setAttribute("username", user.getUsername());
		}
		else {
			forgot = true;
			user = userDAO.selectById(String.valueOf(req.getSession().getAttribute("username")));
		}
		req.setAttribute("forgot", forgot);
		if (req.getMethod().equalsIgnoreCase("post")) {
			String curPassword = req.getParameter("password")!=null?req.getParameter("password"):"";
			if (curPassword.isEmpty() && forgot == false) {
				req.setAttribute("message", "Mật khẩu hiện tại không được bỏ trống");
			}
			else if (!user.getPassword().equals(curPassword) && forgot == false) {
				req.setAttribute("password", curPassword);
				req.setAttribute("message", "Mật khẩu hiện tại không đúng");
			}
			else {
				String newPassword = req.getParameter("newPassword");
				String confirm = req.getParameter("confirmPassword");
				if (confirm.isEmpty() || newPassword.isEmpty()) {
					req.setAttribute("password", curPassword);
					req.setAttribute("newPassword", newPassword);
					req.setAttribute("confirmPassword", confirm);
					req.setAttribute("message", "Mật khẩu mới và mật khẩu xác minh không được bỏ trống");
				}
				else if (!confirm.equals(newPassword)) {
					req.setAttribute("password", curPassword);
					req.setAttribute("newPassword", newPassword);
					req.setAttribute("confirmPassword", confirm);
					req.setAttribute("message", "Mật khẩu xác nhận không trùng với mật khẩu mới");
				}
				else {
					user.setPassword(newPassword);
					userDAO.update(user);
					req.setAttribute("message", "Mật khẩu đã được thay đổi");
				}
			}
		}
		req.setAttribute("choose", "/views/user/change.jsp");
	}
	
	private void doEditProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("choose", "/views/user/edit-profile.jsp");
		viewProfile(req);
		if (req.getMethod().equalsIgnoreCase("post")) {
			updateProfile(req);
		}
	}
	
	private void logoff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		CookieUtils.add("username", "", 0, resp);
		req.getSession().setAttribute("user", null);
		req.setAttribute("fullname", "");
		viewVideos(CookieUtils.get("username", req), req);
		req.setAttribute("choose", "/views/user/home.jsp");
	}
	
	void viewVideos(String userLogined, HttpServletRequest req) {
//		for (Favorite fav : likes) {
//			System.out.println(fav.getId()+" "+favDAO.isLiked(fav.getVideo().getId()));
//		}
		VideoDAO vDAO = new VideoDAO();
		if (!userLogined.isEmpty()) {
			User user = userDAO.selectById(userLogined);
			req.getSession().setAttribute("user", user);
			req.setAttribute("fullname", user!=null?"Chào mừng "+user.getFullname():"");
			req.setAttribute("role", user.getAdmin());
			req.setAttribute("isLogin", true);
		}
		else {
			req.setAttribute("isLogin", false);
		}
		List<Video> list = vDAO.selectAll();
		for (Video video : list) {
			if (video.getFavorites()!=null && favDAO.isUserLike(video.getFavorites(), userLogined)==false) {
				video.setFavorites(null);
			}
		}
		req.setAttribute("videos", list);
		
//		for (Video video : videoDAO.selectAll()) {
//			System.out.println(video.getTitle()+" "+video.getFavorites());
//		}
	}
	
	void viewFavorites(HttpServletRequest req) {
		FavoriteDAO fDAO = new FavoriteDAO();
		req.setAttribute("videoFav", fDAO.selectByUser(CookieUtils.get("username", req)));
		req.setAttribute("choose", "/views/user/favorite.jsp");
	}

	void viewDetail(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String id = uri.substring(uri.lastIndexOf("/") + 1);
//		req.getServletContext().getRealPath("/images");
//		System.out.println(id);
//		Video video = videoDAO.selectById(id);
//		File dir = new File(req.getServletContext().getRealPath("/images"));
//		File image = new File("/images/"+video.getPoster());
//		System.out.println(image.getName());
		req.setAttribute("video", videoDAO.selectById(id));
		req.setAttribute("other", videoDAO.selectOther(id));
	}
	
	void viewProfile(HttpServletRequest req) {
		if (!CookieUtils.get("username", req).equals("")) {
			String username = CookieUtils.get("username", req);
			req.getSession().setAttribute("user", userDAO.selectById(username));
		}
	}
	void addFavorite(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String videoId = uri.substring(uri.lastIndexOf("/") + 1);
		String userId = CookieUtils.get("username", req);
		Favorite fav = new Favorite();
		fav.setUser(userDAO.selectById(userId));
		fav.setVideo(videoDAO.selectById(videoId));
		favDAO.insert(fav);
		viewFavorites(req);
	}
	
	void deleteFavorite(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String id = uri.substring(uri.lastIndexOf("/") + 1);
		Long favId = Long.parseLong(id);
		favDAO.delete(favId);
		viewFavorites(req);
	}
	
	void updateProfile(HttpServletRequest req) {
		try {
			User user = (User) req.getSession().getAttribute("user");
			BeanUtils.populate(user, req.getParameterMap());
			userDAO.update(user);
			req.setAttribute("message", "Đã cập nhật thông tin");
		} catch (Exception e) {
			req.setAttribute("message", "Chưa cập nhật");
			e.printStackTrace();
		}
	}
	
	int generatorCode(String email) {
		int code = (int) Math.floor(((Math.random() * 899999) + 100000));

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("truongson9a2nd@gmail.com", "wrcpagihvfkxhatu");
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("truongson9a2nd@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("OTP");
            message.setContent("<html><b>"+code+"</b></html>" + " là mã xác thực mật khẩu của bạn", "text/html; charset=utf-8");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return code;
	}
}
