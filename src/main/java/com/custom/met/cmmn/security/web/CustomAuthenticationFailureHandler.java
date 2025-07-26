package com.custom.met.cmmn.security.web;

import java.io.IOException;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.custom.met.cmmn.constant.URLConstant;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String redirectUrl = request.getContextPath() + URLConstant.LOGIN_PAGE_URL;
		
		if (exception.getCause() instanceof DisabledException) { // 계정 활성화가 안되었을때
			redirectUrl += "?uneabled&username=" + request.getParameter("username");
		} else { // 아이디 혹은 비밀번호 틀릴 때
			redirectUrl += "?error";
		}
		
		response.sendRedirect(redirectUrl);
	}

	
}
