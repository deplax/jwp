package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class SaveController extends AbstractController {
	private QuestionDao questionDao = new QuestionDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String writer = ServletRequestUtils.getStringParameter(request, "writer");
		String title = ServletRequestUtils.getStringParameter(request, "title");
		String contents = ServletRequestUtils.getStringParameter(request, "contents");
		
		Question question = new Question(writer, title, contents);
		questionDao.insert(question);
		
		return new ListController().execute(request, response);
		
		
	}
}
