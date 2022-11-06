package com.yeon.biz.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeon.biz.member.MemberService;
import com.yeon.biz.member.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

//	@Autowired // MemberDAO 타입의 객체가 메모리에 있어야지만 DI(의존성 주입) 가능함!
//	private MemberDAO memberDAO; // 핵심로직을 수행할 객체
	
//	@Autowired // MemberDAO 타입의 객체가 메모리에 있어야지만 DI(의존성 주입) 가능함!
//	private MemberDAO2 memberDAO; // 핵심로직을 수행할 객체
	
	@Autowired // MemberDAO 타입의 객체가 메모리에 있어야지만 DI(의존성 주입) 가능함!
	private MemberDAO3 memberDAO; // 핵심로직을 수행할 객체
	
	@Override
	public void insertMember(MemberVO vo) {
		memberDAO.insertMember(vo);
	}

	@Override
	public void deleteMember(MemberVO vo) {
		memberDAO.deleteMember(vo);
	}
	
	@Override
	public void deleteAdmin(MemberVO vo) {
		memberDAO.deleteAdmin(vo);
	}

//	@Override
//	public void deleteMember_B(MemberVO vo) {
//		memberDAO.deleteMember_B(vo);
//	}
	
	@Override
	public void updateMember(MemberVO vo) {
		memberDAO.updateMember(vo);
	}
	
	@Override
	public void updatePenalty(MemberVO vo) {
		memberDAO.updatePenalty(vo);
	}

	@Override
	public MemberVO selectOneMember(MemberVO vo) {
//		if(vo.getMid().equals("timo")) {
//			throw new IllegalArgumentException("[실행시예외]");
//		}
		return memberDAO.selectOneMemeber(vo);
	}

	@Override
	public List<MemberVO> selectAllMember(MemberVO vo) {
		return (List<MemberVO>) memberDAO.selectOneMemeber(vo); // 일단 안쓰니까 이렇게 변경
	}

	@Override
	public String check(MemberVO vo) {
		// TODO Auto-generated method stub
		return memberDAO.check(vo);
	}

	@Override
	public List<MemberVO> selectPenaltyMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return memberDAO.selectPenaltyMember(vo);
	}

}
