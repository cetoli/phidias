package br.ufrj.nce.labase.phidias.common;

import java.util.ArrayList;
import java.util.List;

public class Util {
	private static ArrayList arrayList = null;
	public static ArrayList convertListForArrayList(List list){
		arrayList = new ArrayList();
		if (list != null){			
			for (int i=0; i<list.size(); i++){
				arrayList.add(list.get(i));
			}
		}		
		return arrayList;
	}
}
