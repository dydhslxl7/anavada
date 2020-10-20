package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import member.model.vo.Member;
import static common.JDBCTemp.*;

public class MemberDao {

   public MemberDao() {}

   public Member loginCheck(Connection conn, String memberId, String memberPwd) {
      Member member = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String query = "select MEMBER_ID, MEMBER_PWD, SECESSION_OK from member where member_id = ? and member_pwd = ? and SECESSION_OK = 'N'";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, memberId);
         pstmt.setString(2, memberPwd);
         rset = pstmt.executeQuery();
         
         if(rset.next()) {
            member = new Member();
            
            member.setMemberId(memberId);
            member.setMemberPwd(memberPwd);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return member;
   }

   public int insertMember(Connection conn, Member member) {
      int result = 0;
      PreparedStatement pstmt = null;
      
      String query = "insert into member(member_id, member_pwd, member_name, member_email, emailauth, member_phone) values(?, ?, ?, ?, ?, ?)";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, member.getMemberId());
         pstmt.setString(2, member.getMemberPwd());
         pstmt.setString(3, member.getMemberName());
         pstmt.setString(4, member.getMemberEmail());
         pstmt.setString(5, member.getEmailAuth());
         pstmt.setString(6, member.getMemberPhone());
         
         result = pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      return result;
   }

   public int selectCheckId(Connection conn, String memberId) {
      int idChk = 0;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String query = "select count(member_id) from member where member_id = ?";
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, memberId);
         rset = pstmt.executeQuery();
         if(rset.next()) {
            idChk = rset.getInt(1);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return idChk;
   }

   public Member selectMyInfo(Connection conn, String memberId) {
      Member member = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String query = "select * from member where member_id = ?";

      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, memberId);
         rset = pstmt.executeQuery();
         
         if(rset.next()) {
            member = new Member();
            member.setMemberId(memberId);
            member.setMemberName(rset.getString("member_name"));
            member.setMemberEmail(rset.getString("member_email"));
            member.setEmailAuth(rset.getString("emailAuth"));
            member.setMemberPhone(rset.getString("member_phone"));
            member.setJoinDate(rset.getDate("join_date"));
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return member;
   }


   public Member selectMember(Connection conn, String memberId) {
      Member member = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String query = "select * from member where member_id = ?";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, memberId);
         rset = pstmt.executeQuery();
         
         if(rset.next()) {
            member = new Member();
            member.setMemberId(memberId);
            member.setMemberPwd(rset.getString("member_pwd"));
            member.setMemberName(rset.getString("member_name"));
            member.setMemberEmail(rset.getString("member_email"));
            member.setEmailAuth(rset.getString("emailauth"));
            member.setMemberPhone(rset.getString("member_phone"));
            member.setJoinDate(rset.getDate("join_date"));
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return member;
   }

   public int updateMember(Connection conn, Member member) {
      int result = 0;
      PreparedStatement pstmt = null;
      
      String query = "update member set member_pwd = ?, member_name = ?, member_email = ?, emailauth = ?, member_phone = ? where member_id = ?"; 
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, member.getMemberPwd());
         pstmt.setString(2, member.getMemberName());
         pstmt.setString(3, member.getMemberEmail());
         pstmt.setString(4, member.getEmailAuth());
         pstmt.setString(5, member.getMemberPhone());
         pstmt.setString(6, member.getMemberId());
         result = pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      return result;
   }

   public Member memberFind(Connection conn, String memberEmail, String memberPhone) {
      Member member = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String query = "select * from member where member_email like ? and MEMBER_PHONE = ?";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, memberEmail);
         pstmt.setString(2, memberPhone);
         rset = pstmt.executeQuery();
         
         if(rset.next()) {
            member = new Member();
            member.setMemberId(rset.getString("member_id"));
            member.setMemberEmail(rset.getString("member_Email"));
            member.setMemberPhone(rset.getString("member_Phone"));
            
          System.out.println("member : " + member);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return member;
   }

   public int userPwdUpdate(Connection conn, Member member) {
      int result = 0;
      PreparedStatement pstmt = null;
      
      String query = "update member set member_pwd = ? where member_id = ?"; 
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, member.getMemberPwd());
         pstmt.setString(2, member.getMemberId());
         result = pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      return result;
   }

   public int updateMemberPwd(Connection conn, Member member) {
      int result = 0;
      PreparedStatement pstmt = null;
      
      String query = "update member set member_pwd = ? where member_id = ?"; 
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, member.getNewPwdOK());
         pstmt.setString(2, member.getMemberId());
         result = pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      return result;
   }

   public int getListCount(Connection conn) {
      int listCount = 0;
      Statement stmt = null;
      ResultSet rset = null;
      
      String query = "select count(*) from member where SECESSION_OK = 'N'";

      try {
         stmt = conn.createStatement();
         rset = stmt.executeQuery(query);
         
         if(rset.next()) {
            listCount = rset.getInt(1);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(stmt);
      }
      return listCount;
   }

   public int getSListCount(Connection conn) {
      int listCount = 0;
      Statement stmt = null;
      ResultSet rset = null;
      
      String query = "select count(*) from member where SECESSION_OK = 'Y'";

      try {
         stmt = conn.createStatement();
         rset = stmt.executeQuery(query);
         
         if(rset.next()) {
            listCount = rset.getInt(1);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(stmt);
      }
      return listCount;
   }
   
   public ArrayList<Member> selectAllList(Connection conn, int currentPage, int limit, String search, String keyword, String secessionOK) {
      ArrayList<Member> list = new ArrayList<Member>();
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String query = "SELECT ROW_NUMBER() OVER (ORDER BY JOIN_DATE DESC) AS MNUM, MEMBER_ID, MEMBER_NAME, " + 
            "MEMBER_EMAIL, EMAILAUTH, MEMBER_PHONE, JOIN_DATE, LAST_ACCESS_DATE, NVL(DECLARE_OK, ' ') AS DECLARE_OK, " + 
            "SECESSION_OK, SECESSION_DATE " + 
            "FROM (SELECT ROWNUM RNUM, MNUM, MEMBER_ID, MEMBER_PWD, MEMBER_NAME, " + 
            "MEMBER_EMAIL, EMAILAUTH, MEMBER_PHONE, JOIN_DATE, LAST_ACCESS_DATE, SECESSION_OK, SECESSION_DATE " +
            "FROM (SELECT * FROM MEMBER " +
            (secessionOK.equals("Y") ? "WHERE SECESSION_OK = 'Y' " : "WHERE SECESSION_OK = 'N' ") +
            "ORDER BY JOIN_DATE DESC)) " + 
            "LEFT JOIN DECLARE_ADMIN ON (MEMBER_ID = DECLARE_ID) " +
            (search != null && search.equals("userId") ? "WHERE MEMBER_ID LIKE ? " : "") +
            (search != null && search.equals("userName") ? "WHERE MEMBER_NAME LIKE ? " : "") +
            (search != null && search.equals("userEmail") ? "WHERE MEMBER_EMAIL LIKE ? " : "") +
            (search != null && search.equals("userPhone") ? "WHERE MEMBER_PHONE LIKE ? " : "") +
            (search == null ? "WHERE RNUM >= ? AND RNUM <= ?" : "AND RNUM >= ? AND RNUM <= ? ");
      
      int startRow = (currentPage - 1) * limit + 1;
      int endRow = startRow + limit - 1; //51에 -1을 해서 50까지만 보여지게

      
      try {
         int pstmtnum = 1;
         pstmt = conn.prepareStatement(query);
         if ((search != null && search.equals("")) || (search != null && search.equals("userId")) 
             || (search != null && search.equals("userName")) || (search != null &&search.equals("userEmail"))
             || (search != null && search.equals("userPhone")) ) {
             pstmt.setString(pstmtnum++, "%" + keyword + "%");
         }
         pstmt.setInt(pstmtnum++, startRow);
         pstmt.setInt(pstmtnum++, endRow);
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            Member member = new Member();
            member.setmNumber(rset.getString("mnum"));
            member.setMemberId(rset.getString("member_id"));
            member.setMemberName(rset.getString("member_name"));
            member.setMemberEmail(rset.getString("member_email"));
            member.setEmailAuth(rset.getString("emailauth"));
            member.setMemberPhone(rset.getString("member_phone"));
            member.setJoinDate(rset.getDate("join_date"));
            member.setLastAccessDate(rset.getDate("last_access_date"));
            member.setDeclareOK(rset.getString("declare_ok"));
            member.setSecessionOK(rset.getString("secession_ok"));
            member.setSecessionDate(rset.getDate("secession_date"));
            
            list.add(member);            
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return list;
   }

   public int leaveMember(Connection conn, String memberId) {
      int result = 0;
      PreparedStatement pstmt = null;
      
      String query = "update member set secession_ok = 'Y', secession_date = sysdate where member_id = ?";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, memberId);
         result = pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      return result;
   }

   public int deleteMember(Connection conn, String memberId) {
      int result = 0;
      PreparedStatement pstmt = null;
      
      String query = "delete from member where member_id = ?";
      
      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, memberId);
         result = pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      return result;
   }
   
}