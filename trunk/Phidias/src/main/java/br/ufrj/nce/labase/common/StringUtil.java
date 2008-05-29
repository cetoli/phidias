package br.ufrj.nce.labase.common;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * @param sentence
	 * @param word
	 * @param caseSensitive
	 * @return
	 */
	public static int countWord(String sentence, String word, boolean caseSensitive) {
		int count = 0;
		if (!caseSensitive) {
			word = word.toUpperCase();
			sentence = sentence.toUpperCase();
		}

		Matcher matcher = Pattern.compile("\\b" + word + "\\b").matcher(sentence);
		while (matcher.find()) {
			count++;
		}

		return count;
	}

	/**
	 * @param sentence
	 * @param words
	 * @param caseSensitive
	 * @return
	 */
	public static Map<String, Integer> countWords(String sentence, String[] words, boolean caseSensitive) {
		Map<String, Integer> wordsMap = new HashMap<String, Integer>();
		for (String word : words) {
			wordsMap.put(word, StringUtil.countWord(sentence, word, caseSensitive));
		}

		return wordsMap;
	}
}
