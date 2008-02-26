package br.ufrj.nce.labase.controller.action.http;

import java.io.ObjectOutputStream;
import java.util.Map;

public interface IAction {

	public void execute(Map<String, String> requestParameterMap, ObjectOutputStream objectOutputStream);
}
