package br.ufrj.nce.labase.phidias.communication.container;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;


public class AbstractActionContainer implements ActionContainer {

	public Map<String, String> toPropertyValuesMap() {
		Map properties = null;
		try {
			properties = BeanUtils.describe(this);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}

	public void loadPropertyValues(Map<String, String> propertyValues) {
		Set<String> propertyKey = propertyValues.keySet();
		for (String key : propertyKey) {
			try {
				BeanUtils.setProperty(this, key, propertyValues.get(key));
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				System.out.println("Property does not exist!");
			}
		}
	}
}
