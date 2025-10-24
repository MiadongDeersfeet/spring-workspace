package com.kh.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	// extends 까만줄은 deprecated => 지금은 문제 없는데 버전 올리면 이거 없앨거다. 웬만하면 다른 거 사용해~~~
	
	/*
	 *	Interceptor 
	 * 
	 * RequestHandler가 호출되기 전 또는 수행 후 실행할 내용을 만들어줄 수 있음
	 * 
	 * preHandler(전처리) : 핸들러 수행전 낚아챔
	 * postHandler(후처리) : 핸들러 수행후 낚아챔
	 */
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginMember") != null) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		// throw new AuthenticationException("로그인 하세요");
	}
	
	
	
	
	
}
