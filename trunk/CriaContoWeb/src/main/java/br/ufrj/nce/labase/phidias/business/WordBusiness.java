package br.ufrj.nce.labase.phidias.business;

import br.ufrj.nce.labase.common.FileUtil;
import br.ufrj.nce.labase.persistence.EntityManagerHelper;
import br.ufrj.nce.labase.phidias.persistence.dao.WordClassificationDAO;
import br.ufrj.nce.labase.phidias.persistence.model.Keyword;
import br.ufrj.nce.labase.phidias.persistence.model.WordClassification;

public class WordBusiness {

	public void importWord(String filePathArquivo) {

		EntityManagerHelper.getInstance().startTransaction();

		String[] lines = FileUtil.readTextAsLines(filePathArquivo);

		String wordClassification = lines[0];
		Integer wordClassficationId = (wordClassification.indexOf("*") > 0 ? new Integer(wordClassification.substring(0, wordClassification.indexOf("*"))) : null);

		WordClassification wordClassfication = new WordClassification();
		if (wordClassficationId != null)
			wordClassfication.setId(wordClassficationId);

		wordClassfication.setClassification(wordClassification);

		Keyword keyword;
		for (int i = 1; i < lines.length; i++) {
			if (lines[i] != null && lines[i].trim().length() > 0) {
				keyword = new Keyword();
				keyword.setDescription(lines[i]);
				wordClassfication.addKeyword(keyword);
			}
		}

		WordClassificationDAO wcDAO = new WordClassificationDAO();
		if (wordClassfication.getId() == null)
			wcDAO.create(wordClassfication);
		else
			wcDAO.update(wordClassfication);

		EntityManagerHelper.getInstance().commitTransaction();
	}
}
