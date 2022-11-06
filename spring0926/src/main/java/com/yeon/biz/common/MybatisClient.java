package com.yeon.biz.common;

import java.util.List;

import com.yeon.biz.board.BoardVO;
import com.yeon.biz.board.impl.BoardDAO3;

public class MybatisClient {
   public static void main(String[] args) {

      BoardDAO3 boardDAO=new BoardDAO3();
      
     BoardVO vo=new BoardVO();
      // 글작성
      vo.setTitle("마이바티스2");
      vo.setWriter("작은티모");
      vo.setContent("mybatis");
      boardDAO.insertBoard(vo);
      
//      //하나만 선택해서 지우기
//      vo.setBid(11);
//      boardDAO.deleteBoard(vo);

      
      vo.setSearchCondition("TITLE");
      vo.setSearchContent("");
      List<BoardVO> datas=boardDAO.selectAllBoard(vo);
      for(BoardVO v:datas) {
         System.out.println(v);
      }
      
   }
}