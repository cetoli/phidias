package br.ufrj.nce.labase.controller.action.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAction {

	public void execute(HttpServletRequest request, HttpServletResponse response);
}
