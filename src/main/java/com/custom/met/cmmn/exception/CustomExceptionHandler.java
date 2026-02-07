package com.custom.met.cmmn.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.custom.met.cmmn.utils.StringUtils;
import com.custom.met.cmmn.web.CustomController;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class CustomExceptionHandler extends CustomController {

	
	@ExceptionHandler(CustomException.class)
	public Object handleCustomException(HttpServletRequest request, CustomException e) {
		boolean isAjax = StringUtils.NVL(request.getHeader("Content-Type"), "").indexOf("application/json") >= 0
				|| "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		if (isAjax) {
			return getErrorResponse(e);
		} else {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("response", getErrorResponse(e));
			modelAndView.setViewName("cmmn/error-500");
			return modelAndView;
		}
	}
}
