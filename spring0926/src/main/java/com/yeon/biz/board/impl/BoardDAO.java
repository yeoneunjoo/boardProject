package com.yeon.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yeon.biz.board.BoardVO;
import com.yeon.biz.common.JDBCUtil;

//@Repository("boardDAO")
public class BoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	
	final String sql_selectOne="SELECT * FROM BOARD WHERE BID=?";
	final String sql_selectAll="SELECT * FROM BOARD ORDER BY BID DESC";
	final String sql_insert="INSERT INTO BOARD(BID,TITLE,WRITER,CONTENT) VALUES((SELECT NVL(MAX(BID),0)+1 FROM BOARD),?,?,?)";
	final String sql_update="UPDATE BOARD SET TITLE=?,CONTENT=? WHERE BID=?";
	final String sql_delete="DELETE BOARD WHERE BID=?";
	final String sql_searchWriter="SELECT * FROM BOARD WHERE WRITER LIKE '%'||?||'%' ORDER BY BID DESC";
	final String sql_searchTitle="SELECT * FROM BOARD WHERE TITLE LIKE '%'||?||'%' ORDER BY BID DESC";
	final String sql_searchContent="SELECT * FROM BOARD WHERE CONTENT LIKE '%'||?||'%' ORDER BY BID DESC";
	final String sql_selectAllMine="SELECT * FROM BOARD WHERE WRITER=? ORDER BY BID DESC";
	
	public void insertBoard(BoardVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_insert);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}
	public void updateBoard(BoardVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_update);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3,vo.getBid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}
	public void deleteBoard(BoardVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_delete);
			pstmt.setInt(1,vo.getBid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
	}
	public BoardVO selectOneBoard(BoardVO vo) {
		BoardVO data=new BoardVO();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectOne);
			pstmt.setInt(1, vo.getBid());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				data.setBid(rs.getInt("BID"));
				data.setContent(rs.getString("CONTENT"));
				data.setTitle(rs.getString("TITLE"));
				data.setWriter(rs.getString("WRITER"));
				data.setRegdate(rs.getString("REGDATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return data;
	}
	public List<BoardVO> selectAllBoard(BoardVO vo) {
		List<BoardVO> datas=new ArrayList<BoardVO>();
		conn=JDBCUtil.connect();
		try {
			System.out.println("DAO로그vo.getWriter()		"+vo.getWriter());
			if(vo.getWriter()!=null) {
				//System.out.println("내글만 보기");
				pstmt=conn.prepareStatement(sql_selectAllMine);
				pstmt.setString(1, vo.getWriter());
				ResultSet rs=pstmt.executeQuery();
			}
			else {
				//System.out.println("메인에서 전부출력");
				pstmt=conn.prepareStatement(sql_selectAll);
			}
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardVO data=new BoardVO();
				data.setBid(rs.getInt("BID"));
				data.setContent(rs.getString("CONTENT"));
				data.setTitle(rs.getString("TITLE"));
				data.setWriter(rs.getString("WRITER"));
				data.setRegdate(rs.getString("REGDATE"));
				datas.add(data);
				System.out.println(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		System.out.println("BoardDAO가 실행되고 있다는 확인용 로그");
		return datas;
	}
	
	public List<BoardVO> search(BoardVO vo){
		List<BoardVO> datas=new ArrayList<BoardVO>();
		conn=JDBCUtil.connect();
		String sql_selcetAll = null;
		//System.out.println("로그3");
		try {
			if(vo.getSearchCondition()==null) {
				System.out.println("여기들어와야하는 거 아닌가");
				sql_selcetAll=sql_searchTitle;
			}
			else if(vo.getSearchCondition().equals("WRITER")){
				sql_selcetAll=sql_searchWriter;
			}
			else if(vo.getSearchCondition().equals("TITLE")){
				sql_selcetAll=sql_searchTitle;
			}
			else if(vo.getSearchCondition().equals("CONTENT")){
				sql_selcetAll=sql_searchContent;
			}
			
			System.out.println("로그 : "+ vo.getSearchContent());
			System.out.println("로그 : "+ sql_selcetAll);
			
			pstmt=conn.prepareStatement(sql_selcetAll);
			pstmt.setString(1, vo.getSearchContent());
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO data=new BoardVO();
				data.setBid(rs.getInt("BID"));
				data.setContent(rs.getString("CONTENT"));
				data.setTitle(rs.getString("TITLE"));
				data.setWriter(rs.getString("WRITER"));
				datas.add(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}		
		return datas;
	}
}
