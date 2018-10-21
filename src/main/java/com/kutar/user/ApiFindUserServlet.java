package com.kutar.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/api/users/find")
public class ApiFindUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String userId = request.getParameter("userId");
		if (userId == null) {
			response.sendRedirect("/");
			return;
		}

		UserDAO userDao = new UserDAO();
		User user = userDao.findByUserId(userId);
		if (user == null) {
			return;
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(user);
		PrintWriter out = response.getWriter();
		out.print(jsonData);
	}

}
