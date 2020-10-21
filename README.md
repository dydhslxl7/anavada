
# Anavada Project

###### 구현 기간 : 20/08/10 ~ 20/08/18
> 지역 주민들 간의 소통과 정보공유를 돕는 사이트입니다.

## 구현 기능

+ 공지사항 게시판
+ 자주 묻는 질문 게시판
+ 문의사항 게시판
+ 관리자용 공지사항, 자주묻는질문, 문의사항 게시판
+ [anavada/src/notice](https://github.com/dydhslxl7/anavada/tree/main/src/notice)
+ [anavada/src/inquiry](https://github.com/dydhslxl7/anavada/tree/main/src/inquiry)
+ [anavada/src/faq](https://github.com/dydhslxl7/anavada/tree/main/src/faq)
+ [anavada/src/admin/notice](https://github.com/dydhslxl7/anavada/tree/main/src/admin/notice)
+ [anavada/web/views/notice](https://github.com/dydhslxl7/anavada/tree/main/web/views/notice)
+ [anavada/web/views/inquiry](https://github.com/dydhslxl7/anavada/tree/main/web/views/inquiry)
+ [anavada/web/views/admin/notice](https://github.com/dydhslxl7/anavada/tree/main/web/views/admin/notice)

## 상세 기능 설명

1. 공지사항 게시판
	+ 메인페이지 : 공지사항 최신순, 인기순 게시글 롤러처리 (Ajax)
	+ 공지사항 페이지 : 최다 조회수 게시글 맨 위 노출
    
2. 자주 묻는 질문 게시판
	+ 자주 묻는 질문 페이지
	  + 탭 메뉴에 따른 해당 게시글 노출
	  + 아코디언 메뉴로 게시글 내용 노출
  
3. 문의사항 게시판
	  + 마이페이지
	    + 해당 유저가 등록한 문의게시글만 노출
	    + 마이페이지 내 검색 기능 구현
	  + 문의사항 페이지
	    + 로그인한 유저만 문의사항 게시판에 접근 가능
	    + 해당 유저가 작성한 글만 열람 가능
	    + 최대 3개의 파일 첨부 기능
	    + 관리자 문의 답변 시 처리완료 아이콘 표시

4. 관리자용 게시판
	  + 공지사항 : 1개의 파일 첨부 기능
	  + 자주 묻는 질문 : 카테고리별 게시글 등록기능
	  + 문의사항
	    + 댓글 형식의 관리자 답변 등록 기능
	    + 관리자 문의 답변 시 처리완료 아이콘 표시

5. 공통 게시판 기능
	+ 페이징 처리
	+ 분류(제목, 내용) 별 검색 기능
	+ 검색 후 페이지 이동 시 검색결과 유지
	+ 게시글 수정 기능
	+ 파일 첨부 게시글은 파일 아이콘 표시
	+ 관리자 페이지
		+ 체크박스 일괄 삭제 기능

## 🛠 개발환경
+ &#128187; Java | JSP
+ &#127760; HTML | CSS | JavaScript | jQery 3.5.1
+ 🛢 Oracle Database 11g | SQL
+ &#128295; Git | SourceTree
+ &#128235; apache-tomcat-8.5.57
