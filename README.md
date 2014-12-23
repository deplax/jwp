2014년 개발 경험 프로젝트
=========

Q. 로컬 개발 환경에 Tomcat 서버를 시작한 후 http://localhost:8080으로 접근하면 질문 목록을 확인할 수 있다. http://localhost:8080으로 접근해서 질문 목록이 보이기까지의 소스 코드의 호출 순서 및 흐름을 설명하라.

* 먼저 서버가 구동된다.
	src/main/java/next/WebServerLauncher.java
	여기에서 톰캣 서버가 구동되는데
	String webappDirLocation = "webapp/";
	tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
	두 줄에 의해 webapp폴더가 /로 지정된다. 

webapp/WEB-INF/web.xml을 읽어 
<welcome-file>index.jsp</welcome-file>
을 통해 index.jsp로 이동한다. 
(이 프로젝트에선 여기를 따라가는게 아니라 톰켓 전체의 설정을 따라가는 것 같다.)

index.jsp에서
response.sendRedirect("/list.next");
에 의해 맵핑 정보를 찾는다.

맵핑 정보는 
src/main/java/core/mvc/RequestMapping.java 
mappings.put("/list.next", new ListController());
에서 ListController 생성된다.

ListController.java
에서 QuestionDao.java의 findAll()이 실행되고
데이터베이스를 불러온다.

불러온 데이터베이스를 questions에 넣어서
list.jsp로 보낸다.
