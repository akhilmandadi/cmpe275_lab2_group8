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
	
//    checking the required parameters for createPlayer
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

//	checking the required parameters for update player API
	@Before("execution(public * com.cmpe275.controller.PlayerController.updatePlayer(..))")
	public void ValidateUpdatePlayerAPI(JoinPoint joinPoint) {
		HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
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

//	checking the required parameters for the create sponsor API
	@Before("execution(public * com.cmpe275.controller.SponsorController.createSponsor(..)) && args(request)")
	public void ValidateCreateSponsorAPI(JoinPoint joinPoint, HttpServletRequest request) {
		if (request.getParameter("name") == null || request.getParameter("name") == "") {
			throw new ValidationException("Sponsor name is missing which is required parameter.");
		}
	}

//	checking the required parameters for the update sponsor API
	@Before("execution(public * com.cmpe275.controller.SponsorController.updateSponsor(..)) ")
	public void ValidateUpdateSponsorAPI(JoinPoint joinPoint) {
		HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
		if (request.getParameter("name") == null || request.getParameter("name") == "") {
			throw new ValidationException("Sponsor name is missing which is required parameter.");
		}
	}

}
