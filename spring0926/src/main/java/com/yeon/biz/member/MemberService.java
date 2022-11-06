package com.yeon.biz.member;

import java.util.List;

public interface MemberService {
	void insertMember(MemberVO vo);
	void deleteMember(MemberVO vo);
	void deleteAdmin(MemberVO vo);
//	void deleteMember_B(MemberVO vo);
	void updateMember(MemberVO vo);
	void updatePenalty(MemberVO vo);
	MemberVO selectOneMember(MemberVO vo);
	List<MemberVO> selectAllMember(MemberVO vo);
	List<MemberVO> selectPenaltyMember(MemberVO vo);
	String check(MemberVO vo);
}
