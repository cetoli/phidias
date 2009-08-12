package br.ufrj.nce.labase.phidias.aplicador;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.beanutils.BeanUtils;

import br.ufrj.nce.labase.phidias.persistence.model.Answer;
import br.ufrj.nce.labase.phidias.persistence.model.Question;

public class QuestionUI extends Question {

	public QuestionUI(Question question) {
		super();
		try {
			BeanUtils.copyProperties(this, question);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}
