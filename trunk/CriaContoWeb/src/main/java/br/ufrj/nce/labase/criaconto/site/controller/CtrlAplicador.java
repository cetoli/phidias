package br.ufrj.nce.labase.criaconto.site.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufrj.nce.labase.phidias.business.AttendantBusiness;
import br.ufrj.nce.labase.phidias.communication.bean.AttendantBean;

/**
 * Servlet implementation class for Servlet: CtrlAplicador
 * 
 */
public class CtrlAplicador extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CtrlAplicador() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.execute(request, response);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.execute(request, response);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AttendantBusiness attendantBusiness = new AttendantBusiness();
		AttendantBean attendant = new AttendantBean();
		attendant.setId(request.getParameter("login"));
		attendant.setName(request.getParameter("name"));
		attendant.setInstitution(request.getParameter("institution"));
		attendant.setProfession(request.getParameter("profession"));

		attendantBusiness.registerAttendant(attendant);

		request.setAttribute("registrook", "Aplicador inserido com sucesso!");

		RequestDispatcher view = request.getRequestDispatcher("aplicador.jsp");
		view.forward(request, response);

	}
}