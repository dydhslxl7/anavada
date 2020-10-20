package member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import member.model.dao.MemberDao;
import member.model.vo.Member;

import static common.JDBCTemp.*;

public class MemberService {
   //의존성 주입(DI)
   public MemberDao mdao = new MemberDao();
         
   public MemberService() {}
   
   public Member loginCheck(String memberId, String memberPwd) {
      Connection conn = getConnection();
      Member member = mdao.loginCheck(conn, memberId, memberPwd);
      close(conn);
      return member;
   }

   public int insertMember(Member member) {
      Connection conn = getConnection();
      int result = mdao.insertMember(conn, member);
      if(result > 0)
         commit(conn);
      else
         rollback(conn);
      close(conn);
      return result;
   }

   public int selectCheckId(String memberId) {
      Connection conn = getConnection();
      int idChk = mdao.selectCheckId(conn, memberId);
      close(conn);
      return idChk;
   }

   public Member selectMyInfo(String memberId) {
      Connection conn = getConnection();
      Member member = mdao.selectMyInfo(conn, memberId);
      close(conn);
      return member;
   }

   public Member selectMember(String memberId) {
      Connection conn = getConnection();
      Member member = mdao.selectMember(conn, memberId);
      close(conn);
      return member;
   }

   public int updateMember(Member member) {
      Connection conn = getConnection();
      int result = mdao.updateMember(conn, member);
      if(result > 0)
         commit(conn);
      else
         rollback(conn);
      close(conn);
      return result;
   }

   public int leaveMember(String memberId) {
      Connection conn = getConnection();
      int result = mdao.leaveMember(conn, memberId);
      if(result > 0)
         commit(conn);
      else
         rollback(conn);
      close(conn);
      return result;
   }

   public int deleteMember(String memberId) {
      Connection conn = getConnection();
      int result = mdao.deleteMember(conn, memberId);
      if(result > 0)
         commit(conn);
      else
         rollback(conn);
      close(conn);
      return result;
   }

   public Member memberFind(String memberEmail, String memberPhone) {
      Connection conn = getConnection();
      Member member = mdao.memberFind(conn, memberEmail, memberPhone);
      close(conn);
      return member;
   }

   public int userPwdUpdate(Member member) {
      Connection conn = getConnection();
      int result = mdao.userPwdUpdate(conn, member);
      if(result > 0)
         commit(conn);
      else
         rollback(conn);
      close(conn);
      return result;
   }

   public int updateMemberPwd(Member member) {
      Connection conn = getConnection();
      int result = mdao.updateMemberPwd(conn, member);
      if(result > 0)
         commit(conn);
      else
         rollback(conn);
      close(conn);
      return result;
   }

   public ArrayList<Member> selectAllList(int currentPage, int limit, String search, String keyword, String secessionOK) {
      Connection conn = getConnection();
      ArrayList<Member> list = mdao.selectAllList(conn, currentPage, limit, search, keyword, secessionOK);
      close(conn);
      return list;
   }

   public int getListCount(String search, String keyword) {
      Connection conn = getConnection();
      int listCount = mdao.getListCount(conn);
      close(conn);
      return listCount;
   }

   public int getSListCount(String search, String keyword) {
      Connection conn = getConnection();
      int listCount = mdao.getSListCount(conn);
      close(conn);
      return listCount;
   }


}