package com.kh.spring.ajax.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.spring.board.model.dto.BoardDTO;
import com.kh.spring.board.model.dto.ReplyDTO;
import com.kh.spring.board.model.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AjaxController {
	
	/*
	 * 응답할 데이터를 문자열로 반환
	 * ModelAndView의 viewName필드에 return한 문자열값이 대입
	 * => DispatcherServlet
	 * => ViewResolver
	 * -- 이건 우리가 원하는 게 아니야! 우린 .jsp 가 아니라 순수하게 데이터를 응답하고 싶은 거야! => response.setContentType("text/html;charset:UTF-8")
	 * 																		        PrintWriter pw = response.getWriter();
	 * 																		        pw.print(보낼거);
	 * 반환하는 String타입의 값이 View의 정보가 아닌 응답데이터라는 것을 명시해서
	 * => MessageConverter라는 빈으로 이동하게끔 만들어주어야 하는데 이럴 때 스프링에선 보통 애노테이션 쓰죠.
	 * 
	 * @ResponseBody
	 */
	@ResponseBody
	@GetMapping(value="test", produces="text/html; charset=UTF-8")
	public String ajaxReturn(@RequestParam(name="input") String value) {
		log.info("잘넘어옴? {}", value);
		// DB에 잘 다녀왔다고 가정
		// 오늘 점심은 짬뽕이다! ==> 조회해옴
		String lunchMenu = "오늘 점심은 짬뽕이다!";
		return lunchMenu;
	}
	
	private final BoardService boardService;
	
	@Autowired
	public AjaxController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@ResponseBody // 나 jsp 가는 거 아니다~~
	@PostMapping(value="replies", produces="text/html; charset=UTF-8")
	public String insertReply(ReplyDTO reply, HttpSession session) {
		log.info("{}", reply);
		int result = boardService.insertReply(reply, session);
		return result > 0 ? "success" : "fail";
	}
	
	@ResponseBody
	@GetMapping(value="board/{num}", produces="application/json; charset=UTF-8")
	public BoardDTO detail(@PathVariable(value="num") Long boardNo) {
		log.info("게시글 번호 잘 오나요ㅎ : {}", boardNo);
		BoardDTO board = boardService.findByBoardNo(boardNo);
		log.info("혹시 모르니 찍어봄 : {}", board);
		/* 아 편하다~
		 * {
		 * "boardNo" : 17,
		 * "boardTitle : "내이름은우영우똑바로읽어도거꾸로읽어도우영우",
		 * ...
		 * replies : [
		 *   {
		 * 			"replyNo" : 5,
		 * 			"replyContent" : "ㅎㅎ"
		 * 			...
		 * 	 },
		 *   {
		 *   }
		 *  ] 
		 * }
		 */
		
		//return new Gson().toJson(board); //<- 원래 이렇게 하는 거잖셈
		return board;
	}
}
