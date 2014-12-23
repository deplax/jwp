package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class AnswerController extends AbstractController{
	private QuestionDao questionDao = new QuestionDao();
	private AnswerDao answerDao = new AnswerDao();

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		long questionId = ServletRequestUtils.getLongParameter(request, "questionId");
		String writer = ServletRequestUtils.getStringParameter(request, "writer");
		String contents = ServletRequestUtils.getStringParameter(request, "contents");
		
		
		Answer answer = new Answer(writer, contents, questionId);
		answerDao.insert(answer);
		questionDao.increaseComment(questionId);

		return jsonView();
	}
	
}
