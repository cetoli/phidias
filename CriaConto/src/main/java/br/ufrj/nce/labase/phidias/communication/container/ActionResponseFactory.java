package br.ufrj.nce.labase.phidias.communication.container;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.beanutils.BeanUtils;

import br.ufrj.nce.labase.phidias.exception.PhidiasException;

public class ActionResponseFactory {

	public static ActionResponseContainer getActionResponseContainer(String response) {

		StringTokenizer st = new StringTokenizer(response, ";");
		String element, key, value;
		Map<String, String> propertyValue = new HashMap<String, String>();
		while (st.hasMoreTokens()) {
			element = st.nextToken();
			key = element.substring(0, element.indexOf('='));
			value = element.substring(element.indexOf('=') + 1);
			propertyValue.put(key, value);
		}

		try {
			Object actionResponse = Class.forName(propertyValue.get("class")).getConstructors()[0].newInstance((Object[]) null);
			Set<String> propertyKey = propertyValue.keySet();

			for (String keyAux : propertyKey) {
				try {
					BeanUtils.setProperty(actionResponse, keyAux, propertyValue.get(keyAux));
				} catch (IllegalAccessException e) {
					System.out.println("Property cannot be set!");
				} catch (InvocationTargetException e) {
					System.out.println("Property does not exist!");
				}
			}

			return (ActionResponseContainer) actionResponse;
		} catch (ClassNotFoundException e) {
			throw new PhidiasException("Response class not found in classpath!", e);
		} catch (IllegalArgumentException e) {
			throw new PhidiasException("Error instantiating response class!", e);
		} catch (SecurityException e) {
			throw new PhidiasException("Error instantiating response class!", e);
		} catch (InstantiationException e) {
			throw new PhidiasException("Error instantiating response class!", e);
		} catch (IllegalAccessException e) {
			throw new PhidiasException("Error instantiating response class!", e);
		} catch (InvocationTargetException e) {
			throw new PhidiasException("Error instantiating response class!", e);
		}
	}
}
