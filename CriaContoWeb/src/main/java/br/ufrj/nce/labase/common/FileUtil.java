package br.ufrj.nce.labase.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtil {
	public static String[] readTextAsLines(String filePath) {
		try {
			java.io.FileReader fileReader = new java.io.FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			ArrayList<String> linhas = new ArrayList<String>();
			String linha;
			while ((linha = bufferedReader.readLine()) != null) {
				linhas.add(linha);
			}
			bufferedReader.close();
			fileReader.close();
			
			String[] linhasStr = new String[linhas.size()];
			linhas.toArray(linhasStr);
			return linhasStr;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
