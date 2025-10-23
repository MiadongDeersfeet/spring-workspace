package com.kh.spring.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.board.model.dto.AjaxResponse;
import com.kh.spring.board.model.dto.BoardDTO;
import com.kh.spring.board.model.dto.ReplyDTO;
// RestController == @Controller + @ResponseBody
@RestController
@RequestMapping(value="revol", produces="application/json; charset=UTF-8")
public class RevolutionController {
	
	/*
	 * REST(REpresentational State Transfer)
	 * 전송방식은 아니고 스타일 중에 하나다. 실제 통신은 HTTP 가지고 통신할 때의 스타일이다.
	 * HTTP 포로토콜을 활용한 아키텍처 스타일 중 하나 ==> 제일 잘나감
	 * 로이 필딩
	 * 
	 * 자원(Resource)중심의 URL구조 + 상태없음(Stateless) 통신
	 * 자원 중심의 URL을 만들자 => GET/POST/PUT/DELETE로 요청을 보내는 방법을 보드에서 우리는 이미 알아냈어요~!
	 * 
	 * GET		/boards				==> 게시글 목록 조회
	 * GET		/boards/19			==> 게시글들 중 19번 게시글 조회
	 * GET		/boards/photo/19	==> 게시글들 중 사진게시글들 중 19번 게시글 조회
	 * POST		/boards				==> 새 게시글 생성
	 * PUT		/boards/19			==> 게시글 전체 수정 (타이틀, 컨텐트 등 싹싹)
	 * PATCH	/boards/19			==> 게시글 부분 수정 (like 회원정보 수정 <≠> 비밀번호 수정) 
	 * DELETE 	/boards/19			==> 19번 게시글 삭제
	 * 
	 * 우리가 Boards 구현할 때 위와 같이 한 것 같은데? --> 하지만 우리는 REST를 구현하지 않았다.
	 * 중요한 것은 서버가 클라이언트에게 '응답' 해주는 것이다!
	 * 통신이 어떻게 이루어졌다~! 하는 상태코드
	 * 
	 * HTTP 상태 코드 활용
	 * 
	 * 200 OK				==> 요청이 성공적으로 잘 이루어졌음(GET, DELETE)
	 * 201 CREATED			==> 요청에 의해 데이터가 잘 만들어짐(POST, PUT, PATCH) 
	 * 400 Bad Request		==> 잘못된 요청
	 * 401 Unauthorized		==> 인증 실패(비로그인, 관리자만 접근 가능한데 등...)
	 * 404 Not Found 		==> 없음
	 * 500 Internal Error	==> 서버 터짐
	 */
	
	@GetMapping("/a")
	public BoardDTO a() {
	BoardDTO a = new BoardDTO();
	a.setBoardTitle("a임");
	return a;
	}
	
	@GetMapping("/b")
	public BoardDTO b() {
	BoardDTO b = new BoardDTO();	
	b.setBoardTitle("b임");
	return b;
	}
	
	@PostMapping("/c") // text/html; 얘네 통일할거야
	public AjaxResponse c() {
		String str = "동그라미 김밥";
		AjaxResponse ar = new AjaxResponse();
		ar.setCode("201"); // 얘는 포스트라서 성공하면 201이 돌아가야해
		ar.setData(str); // 실제로 응답할 값
		ar.setMessage("데이터 생성에 성공했습니다.");
		return ar;
	}
	
	@GetMapping("/d") // application/json; 얘네 통일할거야
	public AjaxResponse d() {
		ReplyDTO reply = new ReplyDTO();
		AjaxResponse ar = new AjaxResponse();
		ar.setCode("200");
		ar.setData(reply);
		ar.setMessage("조회에 성공했습니다.");
		return ar;
	}
	
	
	
	
	
	
	
}
