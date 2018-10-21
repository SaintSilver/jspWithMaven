package com.kutar.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.beanutils.BeanUtilsBean;

import com.kutar.support.MyValidatorFactory;

@WebServlet("/users/update")
public class UpdateUserServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String sessionUserId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		if(sessionUserId == null) {
			response.sendRedirect("/");
			return;
		}
		
		//bean mapping
		User user = new User();
		try {
			BeanUtilsBean.getInstance().populate(user, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e1) {
			e1.printStackTrace();
			throw new ServletException(e1);
		}
		
		//로그인한 session과 업데이트하려는 id 일치여부
		if(!user.isSameUser(sessionUserId)) {
			response.sendRedirect("/");
			return;
		}
		
		//유효성 검사
		Validator validator = MyValidatorFactory.createValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		if(constraintViolations.size() > 0) {
			request.setAttribute("isUpdate", true);
			request.setAttribute("user", user); //입력한 데이터 유지
			String errorMessage = constraintViolations.iterator().next().getMessage();
			forwardJSP(request, response, errorMessage);
			return;
		}
		
		UserDAO userDAO = new UserDAO();
		try {
			userDAO.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/");
	}
	
	private void forwardJSP(HttpServletRequest request, HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = request.getRequestDispatcher("/form.jsp");
		rd.forward(request, response);
	}

}
