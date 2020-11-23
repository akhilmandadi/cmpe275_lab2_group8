package com.cmpe275.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

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
	
	@Before("execution(public * com.cmpe275.controller.PlayerController.updatePlayer(..)) && args(request)")
	public void ValidateUpdatePlayerAPI(JoinPoint joinPoint, HttpServletRequest request) {
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
	
	@Before("execution(public * com.cmpe275.controller.SponsorController.createSponsor(..)) && args(request)")
	public void ValidateCreateSponsorAPI(JoinPoint joinPoint, HttpServletRequest request) {
		if (request.getParameter("name") == null || request.getParameter("name") == "") {
			throw new ValidationException("Sponsor name is missing which is required parameter.");
		}
	}
	
	@Before("execution(public * com.cmpe275.controller.SponsorController.updateSponsor(..)) && args(request) && args(id)")
	public void ValidateUpdateSponsorAPI(JoinPoint joinPoint, HttpServletRequest request, @PathVariable("id") long id) {
		if (request.getParameter("name") == null || request.getParameter("name") == "") {
			throw new ValidationException("Sponsor name is missing which is required parameter.");
		}
	}

}
