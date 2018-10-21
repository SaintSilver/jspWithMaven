package com.kutar.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/users/updateForm")
public class UpdateFormUserServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(UpdateFormUserServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sessionUserId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		logger.debug("수정하려는 User ID : {} ", sessionUserId);

		if (sessionUserId == null) {
			response.sendRedirect("/");
			return;
		}

		UserDAO userDAO = new UserDAO();

		User user = userDAO.findByUserId(sessionUserId);
		// update인지 판별용
		request.setAttribute("isUpdate", true);
		request.setAttribute("user", user);

		RequestDispatcher rd = request.getRequestDispatcher("/form.jsp");
		rd.forward(request, response);

	}

}
