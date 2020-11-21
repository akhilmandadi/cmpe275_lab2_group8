package com.cmpe275.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.cmpe275.Exception.ValidationException;

@Aspect
@Order(1)
@Component
public class APIValidation {

	@Before("execution(public * com.cmpe275.controller.PlayerController.createPlayer(..)) && args(request)")
	public void ValidateCreatePlayerAPI(JoinPoint joinPoint, HttpServletRequest request) {
		if (request.getParameter("email") == null || request.getParameter("email") == "") {
			throw new ValidationException("Email is missing which is required parameter.");
		}
		if (request.getParameter("firstname") == null || request.getParameter("firstname") == "") {
			throw new ValidationException("Firstname is missing which is required parameter.");
		}
		if (request.getParameter("lastname") == null || request.getParameter("lastname") == "") {
			throw new ValidationException("Lastname is missing which is required parameter.");
		}
	}

}
