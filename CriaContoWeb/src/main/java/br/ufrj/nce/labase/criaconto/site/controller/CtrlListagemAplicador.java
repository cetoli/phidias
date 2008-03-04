package br.ufrj.nce.labase.criaconto.site.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufrj.nce.labase.phidias.business.AttendantBusiness;
import br.ufrj.nce.labase.phidias.business.PatientBusiness;
import br.ufrj.nce.labase.phidias.communication.bean.PatientBean;

/**
 * Servlet implementation class for Servlet: CtrlPaciente
 * 
 */
public class CtrlListagemAplicador extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CtrlListagemAplicador() {
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

		request.setAttribute("listagem", attendantBusiness.listAttendant());

		RequestDispatcher view = request.getRequestDispatcher("listagem_aplicador.jsp");
		view.forward(request, response);
	}
}