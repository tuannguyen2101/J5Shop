package com.home.shop.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {
		HttpSession session = request.getSession();

		System.out.println(session.toString());

		if (session.getAttribute("USERNAME") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}

		return true;
	}
}
