package com.kh.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberRepository;
import com.kh.spring.member.model.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public MemberDTO login(MemberDTO member) {
		//log.info("나 불렀어?");
		// ver 0.1
		return memberRepository.login(sqlSession, member);
	}

	@Override
	public void signUp(MemberDTO member) {
	}

	@Override
	public void update(MemberDTO member) {
	}

	@Override
	public void delete(MemberDTO member) {
	}

}
