package br.ufrj.nce.labase.phidias.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;

import br.ufrj.nce.labase.phidias.communication.container.ActionContainer;
import br.ufrj.nce.labase.phidias.communication.container.ActionResponseContainer;
import br.ufrj.nce.labase.phidias.exception.PhidiasException;

public class CommunicationProtocol {
	public static final String ACTION_PARAMETER = "action";
	public static final String REGISTER_EVENT_ACTION = "registerEvent";
	public static final String REGISTER_SESSION_ACTION = "registerSession";
	public static final String JOIN_SESSION_ACTION = "joinSession";
	public static final String REGISTER_SESSION_END_ACTION = "registerSessionEnd";
	public static final String REGISTER_COMMENT_ACTION = "registerComment";
	public static final String REGISTER_STIMULUS_ACTION = "registerStimulus";
	public static final String GET_NEXT_STIMULUS_ACTION = "getNextStimulus";
	public static final String GET_MOVES_ACTION = "getMoves";

	public static ActionResponseContainer execute(String action, ActionContainer actionData) {
		try {
			// Prepara o HttpClient e o metodo Post para fazer a requisicao;
			HttpClient client = new HttpClient();
			client.getHostConfiguration().setHost("labase.nce.ufrj.br", 80, "http");
			client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);

			PostMethod post = new PostMethod("/CriaContoWeb/ClientServerServlet.do");
			post.addParameter(new NameValuePair(ACTION_PARAMETER, action));

			// Gera parametros do request via metodo post
			Map<String, String> properties = actionData.toPropertyValuesMap();
			Set<String> propertiesKeySet = properties.keySet();
			String propertyValue;
			for (String propriedadeKey : propertiesKeySet) {
				propertyValue = (String) properties.get(propriedadeKey);
				if (propertyValue != null)
					post.addParameter(new NameValuePair(propriedadeKey, propertyValue));
			}

			// Faz a chama ao servlet
			client.executeMethod(post);

			// Obtem resposta do servlet e formata em um objeto de retorno
			ObjectInputStream o = new ObjectInputStream(post.getResponseBodyAsStream());
			ActionResponseContainer response = (ActionResponseContainer) o.readObject();

			// Libera conexao
			post.releaseConnection();

			// Trata geração do server.
			if (!response.isSuccess() || response.getGeneratedException() != null)
				throw response.getGeneratedException();

			return response;
		} catch (IllegalArgumentException e) {
			throw new PhidiasException(e);
		} catch (HttpException e) {
			throw new PhidiasException("Error connecting to URL!!", e);
		} catch (IOException e) {
			throw new PhidiasException("Server is not avaiable!!", e);
		} catch (ClassNotFoundException e) {
			throw new PhidiasException("Error treating class response not found", e);
		}

	}
}
