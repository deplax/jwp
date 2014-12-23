2014년 개발 경험 프로젝트
=========

Q. 로컬 개발 환경에 Tomcat 서버를 시작한 후 http://localhost:8080으로 접근하면 질문 목록을 확인할 수 있다. http://localhost:8080으로 접근해서 질문 목록이 보이기까지의 소스 코드의 호출 순서 및 흐름을 설명하라.

* 먼저 서버가 구동되어 있다.
	* src/main/java/next/WebServerLauncher.java
	여기에서 톰캣 서버가 구동되는데
		* String webappDirLocation = "webapp/";
		* tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
	* 두 줄에 의해 webapp폴더가 /로 지정된다. 

* 구동된 서버에서 http://localhost:8080로 접근.
	* webapp/WEB-INF/web.xml을 읽어 
	* <welcome-file>index.jsp</welcome-file>
	* 을 통해 index.jsp로 이동한다. 
	* (이 프로젝트에선 여기를 따라가는게 아니라 톰켓 전체의 설정을 따라가는 것 같다.)
	
* @Weblistener가 동작하여 init
	* JwpContextLoaderListender에서 초기화

* 맵핑에 의해 ListController가 생성.
	* index.jsp에서
	* response.sendRedirect("/list.next");
	* 에 의해 맵핑 정보를 찾는다.
	* 맵핑 정보는 아래에 있다.
		* src/main/java/core/mvc/RequestMapping.java 
		* mappings.put("/list.next", new ListController());
		* 에서 ListController 생성된다.

* ListController에서 DAO를 통해 쿼리를 보낸다.
	* ListController.java
	* 에서 QuestionDao.java의 findAll()이 실행되고
	* 데이터베이스를 불러온다.
	
* 불러온 DB를 questions리스트에 넣고 ModelAndView를 생성한다.

* View인터페이스를 따르는 JstlView에 구현된 render에 리다이렉트 기능이 있다.
	* 이 기능은 AbstractController상속받는 ForwardController로 의해 들어가게 되고
	결과적으로 jstlView("list.jsp"); 에서 list.jsp로 이동하게 된다.
	* 추가적으로 이전의 questions리스트가 전달된다.
	
* 전달된 questions 오브젝트는 forEach jstl에 의해 화면에 뿌려진다.
