package com.yeon.biz.member.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeon.biz.member.MemberVO;

@Repository("memberDAO")
public class MemberDAO3 {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertMember(MemberVO vo) {
		mybatis.insert("MemberDAO.insertMember", vo);
	}
	
	public void updateMember(MemberVO vo) {
		mybatis.update("MemberDAO.updateMember", vo);
	}
	
	public void updatePenalty(MemberVO vo) {
		mybatis.update("MemberDAO.updatePenalty", vo);
	}
	
	public void deleteMember(MemberVO vo) {
		mybatis.delete("MemberDAO.deleteMember", vo);
	}
	
	public void deleteAdmin(MemberVO vo) {
		mybatis.delete("MemberDAO.deleteAdmin", vo);
	}

//	public void deleteMember_B(MemberVO vo) {
//		mybatis.delete("MemberDAO.deleteMember_B", vo);
//		System.out.println("MemberDAO3 deleteMember_B 로그1");
//	}
	
	public MemberVO selectOneMemeber(MemberVO vo) {
		return mybatis.selectOne("MemberDAO.selectOneMemeber", vo);
	}
	
	public String check(MemberVO vo) {
		System.out.println("MDAO3 check 로그1 "+vo.getMid());
		String check="0";
		List<MemberVO> result = mybatis.selectList("MemberDAO.check", vo);
		
//		System.out.println("????");
//		System.out.println("MDAO2 idCheck 로그2 "+result);
//		System.out.println("MDAO2 idCheck 로그3 "+check);
//		if(result==null) {
//			check = "0";
//			System.out.println(check);
//			return check;
//		}
		if(result.size()!=0) {
			check="1";
			System.out.println(check);
			return check;
		}
		System.out.println(check);
		return check;
	}
	
	public List<MemberVO> selectPenaltyMember(MemberVO vo) {
		return mybatis.selectList("MemberDAO.selectPenaltyMember", vo);
	}
}
