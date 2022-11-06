package com.yeon.biz.member.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yeon.biz.member.MemberVO;

//@Repository("memberDAO")
public class MemberDAO2 {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	final String sql_selectOne="SELECT * FROM MEMBER WHERE MID=? AND MPW=?";
	final String sql_selectAll="SELECT * FROM MEMBER";
	final String sql_insert="INSERT INTO MEMBER VALUES(?,?,?,?)";
	final String sql_update="UPDATE MEMBER SET MPW=?, NAME=? WHERE MID=?";
	final String sql_delete="DELETE MEMBER WHERE MID=? AND MPW=?";
	final String sql_deleteAll="DELETE BOARD WHERE MID=? AND MPW=?";
	final String sql_idCheck="SELECT * FROM MEMBER WHERE MID=?";
	
	
	void insertMember(MemberVO vo) {
		jdbcTemplate.update(sql_insert,vo.getMid(),vo.getMpw(),vo.getName(),vo.getRole());
	}
	void deleteMember(MemberVO vo) {
		jdbcTemplate.update(sql_delete,vo.getMid(),vo.getMpw());
	}
	void updateMember(MemberVO vo) {
		jdbcTemplate.update(sql_update,vo.getMpw(),vo.getMid());
	}
	MemberVO selectOneMember(MemberVO vo) {
		System.out.println("DAO2쓰는중");
		Object[] args= {vo.getMid(),vo.getMpw()};
		return jdbcTemplate.queryForObject(sql_selectOne,args,new MemberRowMapper());
	}
	List<MemberVO> selectAllMember(MemberVO vo) {
		return jdbcTemplate.query(sql_selectAll,new MemberRowMapper());
	}
	public String idCheck(MemberVO vo) {
		System.out.println("MDAO2 idCheck 로그1 "+vo.getMid());
		String check="0";
		Object[] args = {vo.getMid()};
		//MemberVO result=jdbcTemplate.query(sql_idCheck,);
		List<MemberVO> result = jdbcTemplate.query(sql_idCheck,args,new MemberRowMapper());
		
		System.out.println("????");
		System.out.println("MDAO2 idCheck 로그2 "+result);
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
}
class MemberRowMapper implements RowMapper<MemberVO> {

	@Override
	public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberVO data=new MemberVO();
		data.setMid(rs.getString("MID"));
		data.setMpw(rs.getString("MPW"));
		data.setName(rs.getString("NAME"));
		data.setRole(rs.getString("ROLE"));
		return data;
	}
	
}