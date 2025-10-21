package com.kh.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.dto.MemberDTO;
import com.kh.spring.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	/*
	@RequestMapping("login")
	public void login(Member member) {
		
		// 1. 값뽑기
		// 2. 데이터 가공
		System.out.println(member);
	
	}
	*/
	
	/*
	@RequestMapping("login")
	public String login(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		System.out.printf("id : %s , pwd : %s", userId, userPwd);
		
		return "main";
	}
	*/
	
	/*
	@RequestMapping("login")
	public String login(@RequestParam(value="userId", defaultValue="whatisthis?") String id, @RequestParam(value="userPwd") String pwd) {
		// 메서드에 매개변수 두 개를 만들어 놓는다. -- 값을 두 개 받아야하니까~!
		
		System.out.printf("이렇게 하면 될까요?? id : %s, pwd : %s", id, pwd);
		
		return "main";
	}
	*/
	
	/*
	@RequestMapping("login")
	public String login(String userId, String userPwd) {
		
		System.out.println("ID : " + userId + ", PWD : " + userPwd);
		
		return "main";	
	}
	*/
	
	/*
	 * HandlerAdapter의 판단 방법 :
	 * 
	 *  1. 매개변수 자리에 기본타입(int, boolean, String, Date...)이 있거나
	 *  	RequestParam 애노테이션이 존재하는 경우 == RequestParam으로 인식
	 *  
	 *  2. 매개변수 자리에 사용자 정의 클래스(MemberDTO, Board, Reply...)이 있거나
	 *     ModelAttribute애노테이션이 존재하는 경우 == 커맨드 객체 방식으로 인식
	 *     
	 * 커맨드 객체 방식
	 * 
	 * 스프링에서 해당 객체를 기본생성자를 이용해서 생성한 후 내부적으로 Setter 메서드를 찾아서
	 * 요청 시 전달값을 해당 필드에 대입해주는 방식 == 커맨드 객체 방식
	 * 
	 * 1. 매개변수 자료형에 반드시 기본생성자가 존재할 것
	 * 2. 전달되는 키 값과 객체의 필드명이 동일할 것
	 * 3. Setter 메서드가 반드시 존재할 것
	 * 
	 */
	//@Autowired
	private final MemberService memberService; // = new MemberService();
	
	/*
	@Autowired
	public void setMemberService(MemberServcie memberService) {
		this.memberService = memberService;
	} 둘다 안씁니다. => 권장하는 방법이 따로 있어요.
	*/
	
	@Autowired /* ☆ 권장 방법 ★ */
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	/*
	@RequestMapping("login")
	public String login(MemberDTO member, //HttpServletRequest request,
						HttpSession session, Model model) {
		//System.out.println("로그인 시 입력한 정보 : " + member);
		log.info("Member객체 필드값 확인 ~ {}", member);
		MemberDTO loginMember = memberService.login(member);
		
		if(loginMember != null) {
			log.info("로그인 성공");
		} else {
			log.info("실패");
		}
		
		if(loginMember != null) { //로그인에 성공
			// sessionScope에 로그인 된 사용자의 정보를 담아줌
			// HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginMember);
			// 포워딩 방식 보다는 -> sendRedirect
			// localhost/spring
			// 원래는 redirect 하려면 response 사용했는데 이제는 그럴 필요가 없어요~~~
			
			return "redirect:/"; // (문자열은 뷰에 들어감)
		} else { // 실패했을 때
			
			// error_page -> 포워딩
			// requestScope에 msg라는 키값으로 로그인 실패입니다 ~~ 담아서 포워딩 // 모델이 들어가야함. (모델 앤드 뷰)
			// Spring에서는 Model타입을 이용해서 RequestScope에 값을 담음
			model.addAttribute("msg", "로그인 실패!");
			
			// Forwarding (디스패처 서블릿에서 뷰 리졸버로 간다구요~~~)
			// /WEB-INF/views/
			// .jsp
			
			// /WEB-INF/views/include/error_page.jsp
			
			return "include/error_page";
			
		}
		
		//return "main";
	}
	*/
	
	// 두 번째 방법 : 반환타입 ModelAndView타입으로 반환
	
	@PostMapping("/login") // GetMapping 만약에 겟으로 오면~!
	public ModelAndView login(MemberDTO member, HttpSession session, ModelAndView mv) {
		
		MemberDTO loginMember = memberService.login(member);
		
		if(loginMember != null) {
			session.setAttribute("loginMember", loginMember);
			mv.setViewName("redirect:/");
		} else {
			mv.addObject("msg", "아이디 또는 비밀번호를 확인해주세요").setViewName("include/error_page");
		}
		
		return mv;
	}
	
	// CRUD
	// INSERT --> POST ------> /member
	// SELECT --> GET -------> 
	
	// UPDATE
	// DELETE
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginMember");
		return "redirect:/";
	}
	
	// 회원가입
	@GetMapping("join")
	public String joinForm() {
		// 포워딩할 JSP파일의 논리적인 경로가 필요합니다.
		// /WEB-INF/views/member/signup.jsp (논리적인 경로 == webapp 부터 시작 --> webapp 은 / 슬래쉬로 치환)
		
		return "member/signup";
	}
	
	@PostMapping("signup")
	public String signup(MemberDTO member, HttpServletRequest request) {
		// 아이디, 비밀번호, 이름, 이메일
		/*
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		*/
		log.info("{}", member);
		memberService.signup(member);
		return "main";
	}
	
	@GetMapping("mypage")
	public String myPage() {
		return "member/my_page";
	}
	
	@PostMapping("edit")
	public String edit(MemberDTO member, HttpSession session) {
		/*
		 * 1_1) 404 발생 == 매핑값 오타 (form action="" 또는 @PostMapping(""))
		 * org.springframework.web.servlet.PageNotFound
		 * 
		 * 1_2) 405 발생 == (form method="get") 요청전송방식을 GET/POST를 앞단<->뒷단 서로 안맞을 때
		 * Request method "GET" not supported
		 * 
		 * 1_3) 필드에 값이 잘 들어왔나?? - Key값 확인
		 */
		log.info("값 찍어보기 : {}", member);
		
		/*
		 * 2. SQL문
		 * UPDATE => MEMBER -> 특정 한 명의 정보를 수정하는 것이기에 누구에게 WHERE 조건절을 달아야하는지 생각해야한다. 보편적으로 PK를 떠올려야한다.
		 * ID PWD NAME EMAIL ENROLLDATE => 지금은 ID, NAME, EMAIL 만 값이 넘어오는데 NAME, EMAIL만 수정 가능함.
		 * 
		 * 2_1) 매개변수 MemberDTO타입의 memberId필드값을 조건으로 써야겠구나~!
		 * UPDATE MEMBER SET USER_NAME = 입력한 값, EMAIL = 입력한 값
		 *  WHERE USER_ID = 넘어온 아이디
		 */
		
		/*
		 * Best Practice
		 * 
		 * 실무 권장
		 * 
		 * 컨트롤러에서 세션관리를 담당
		 * 서비스에는 순수 비즈니스 로직만 구현
		 * 서비스에서 HttpSession이 필요하다면 인자로 전달
		 */
		
		memberService.update(member, session);
		
		return "redirect:mypage";
	}
	
	@PostMapping("delete")
	public String delete(@RequestParam(value="userPwd") String userPwd, HttpSession session) {
		
		memberService.delete(userPwd, session);
		session.removeAttribute("loginMember");
		return "redirect:/";
	}
	
	
}
