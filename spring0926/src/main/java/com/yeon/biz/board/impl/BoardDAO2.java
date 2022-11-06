package com.yeon.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.yeon.biz.board.BoardVO;
import com.yeon.biz.common.JDBCUtil;

//@Repository("boardDAO")
public class BoardDAO2 {

	@Autowired
	private JdbcTemplate jdbcTemplate;
//	private MultipartFile uploadFile;
//	private String fileName;

	final String sql_selectOne="SELECT * FROM BOARD WHERE BID=?";
	final String sql_selectAll="SELECT * FROM BOARD ORDER BY BID DESC";
	final String sql_insert="INSERT INTO BOARD(BID,TITLE,WRITER,CONTENT,UPLOADFILE) VALUES((SELECT NVL(MAX(BID),0)+1 FROM BOARD),?,?,?,?)";
	final String sql_update="UPDATE BOARD SET TITLE=?,CONTENT=?,UPLOADFILE=? WHERE BID=?";
	final String sql_delete="DELETE BOARD WHERE BID=?";
	final String sql_searchWriter="SELECT * FROM BOARD WHERE WRITER LIKE '%'||?||'%' ORDER BY BID DESC";
	final String sql_searchTitle="SELECT * FROM BOARD WHERE TITLE LIKE '%'||?||'%' ORDER BY BID DESC";
	final String sql_searchContent="SELECT * FROM BOARD WHERE CONTENT LIKE '%'||?||'%' ORDER BY BID DESC";
	final String sql_selectAllMine="SELECT * FROM BOARD WHERE WRITER=? ORDER BY BID DESC";

	void insertBoard(BoardVO vo) {
//		System.out.println(vo.getTitle()+","+vo.getWriter()+","+vo.getContent()+","+vo.getFileName());
//		jdbcTemplate.update(sql_insert,vo.getTitle(),vo.getWriter(),vo.getContent(),vo.getFileName());
//		uploadFile=vo.getUploadFile();
//		fileName=uploadFile.getOriginalFilename();
		System.out.println("BDAO2에서 찍은 로그 "+vo.getTitle()+","+vo.getWriter()+","+vo.getContent()+","+vo.getFileName());
		jdbcTemplate.update(sql_insert,vo.getTitle(),vo.getWriter(),vo.getContent(),vo.getFileName());
//		if(!uploadFile.isEmpty()) {
//			jdbcTemplate.update(sql_insert,vo.getTitle(),vo.getWriter(),vo.getContent(),fileName);
//		}		
//		else {
//			System.out.println(vo.getTitle()+","+vo.getWriter()+","+vo.getContent()+","+vo.getFileName());
//			jdbcTemplate.update(sql_insert,vo.getTitle(),vo.getWriter(),vo.getContent(),"pic01.jpg");
//		}
	}
	void updateBoard(BoardVO vo) {
		jdbcTemplate.update(sql_update,vo.getTitle(),vo.getContent(),vo.getFileName(),vo.getBid());
	}
	void deleteBoard(BoardVO vo) {
		jdbcTemplate.update(sql_delete,vo.getBid());
	}
	
	BoardVO selectOneBoard(BoardVO vo) {
		Object[] args= {vo.getBid()};
		return jdbcTemplate.queryForObject(sql_selectOne,args,new BoardRowMapper());
	}
	
	List<BoardVO> selectAllBoard(BoardVO vo) {
		System.out.println("BoardDAO2 selectAllBoard에서 찍은 로그");
		return jdbcTemplate.query(sql_selectAll,new BoardRowMapper());
	}

	List<BoardVO> search(BoardVO vo) {
		// TODO Auto-generated method stub
		System.out.println("BoardDAO2 search에서 찍은 로그");
		//System.out.println("로그3");
		if(vo.getSearchCondition().equals("TITLE")) {
//			System.out.println("여기들어와야하는 거 아닌가");
			Object[] args= {vo.getSearchContent()};
//			System.out.println("뭐냐고"+vo.getFileName());
			return jdbcTemplate.query(sql_searchTitle,args,new BoardRowMapper());
		}
		else if(vo.getSearchCondition().equals("WRITER")) {
//			System.out.println("여기에 안걸린다고? 왜?");
			Object[] args= {vo.getSearchContent()};
			return jdbcTemplate.query(sql_searchWriter,args,new BoardRowMapper());
		}
		else if(vo.getSearchCondition().equals("CONTENT")){
			Object[] args= {vo.getSearchContent()};
			return jdbcTemplate.query(sql_searchContent,args,new BoardRowMapper());
		}
		else {
//			System.out.println("흠....");
			return jdbcTemplate.query(sql_selectAll,new BoardRowMapper());
		}
//		if(vo.getSearchCondition().equals("WRITER")){
//			System.out.println("로그1");
//			Object[] args= {vo.getSearchContent()};
//			return jdbcTemplate.query(sql_searchWriter,args,new BoardRowMapper());
//		}
//		else if(vo.getSearchCondition().equals("TITLE")){
//			System.out.println("로그2");
//			Object[] args= {vo.getSearchContent()};
//			return jdbcTemplate.query(sql_searchTitle,new BoardRowMapper());
//		}
//		else if(vo.getSearchCondition().equals("CONTENT")){
//			System.out.println("로그3");
//			Object[] args= {vo.getSearchContent()};
//			return jdbcTemplate.query(sql_searchContent,new BoardRowMapper());
//		}
//		System.out.println("로그4");
//		return jdbcTemplate.query(sql_selectAll,new BoardRowMapper());
	}

	class BoardRowMapper implements RowMapper<BoardVO>{

		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardVO data=new BoardVO();
			data.setBid(rs.getInt("BID"));
			data.setContent(rs.getString("CONTENT"));
			data.setTitle(rs.getString("TITLE"));
			data.setWriter(rs.getString("WRITER"));
			data.setRegdate(rs.getString("REGDATE"));
			data.setFileName(rs.getString("UPLOADFILE"));
			System.out.println("BoardDAO2 mapRow에서 찍은 로그 "+rs.getString("UPLOADFILE"));
//			if(data.getFileName()!=null) {
//				System.out.println("여기인가??");
//				data.setFileName(rs.getString("UPLOADFILE"));
//			}
//			else if(data.getFileName()==null){
//				System.out.println("아니면 여기인가??");
//				data.setFileName(rs.getString("pic01.jpg"));
//			}
			return data;
		}

	}

}
