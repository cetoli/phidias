package br.ufrj.nce.labase.phidias.aplicador;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.beanutils.BeanUtils;

import br.ufrj.nce.labase.phidias.persistence.dao.SessionQuestionDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Answer;
import br.ufrj.nce.labase.phidias.persistence.model.Question;

public class QuestionUI extends Question {
	private static final long serialVersionUID = -2299265921407818742L;
	
	private String selectedValue;
	private int sessionId;

	public QuestionUI(int sessionId, Question question) {
		super();
		
		this.sessionId = sessionId;
		
		try {
			BeanUtils.copyProperties(this, question);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		SessionQuestionDAO dao = new SessionQuestionDAO();
		
		Long answerId = dao.findAnswerId(sessionId, question.getPk().getQuestionnaireId(), question.getPk().getQuestionID());
		if (answerId != null) {
			selectedValue = String.valueOf(answerId);
		}
	}

	public List<SelectItem> getAnswersSelectItems() {
		List<SelectItem> listAnswer = new ArrayList<SelectItem>();
		for (Answer answer : this.getAnswerList()) {
			SelectItem si = new SelectItem();
			si.setLabel(answer.getAnswerDesc());
			si.setValue(String.valueOf(answer.getPk().getAnswerId()));
			listAnswer.add(si);
		}

		return listAnswer;
	}

	/**
	 * @return the selectedValue
	 */
	public String getSelectedValue() {
		return selectedValue;
	}

	/**
	 * @param selectedValue the selectedValue to set
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	/**
	 * @return the sessionId
	 */
	public int getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
}
