package br.ufrj.nce.labase.criaconto.site.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufrj.nce.labase.phidias.business.PatientBusiness;
import br.ufrj.nce.labase.phidias.communication.bean.PatientBean;

/**
 * Servlet implementation class for Servlet: CtrlPaciente
 * 
 */
public class CtrlPaciente extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CtrlPaciente() {
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
		try {
			PatientBusiness patientBusiness = new PatientBusiness();
			PatientBean patient = new PatientBean();
			patient.setId(request.getParameter("login"));
			patient.setGender(request.getParameter("gender"));
			patient.setName(request.getParameter("name"));
			
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");   
			GregorianCalendar g = new GregorianCalendar();
			g.setTime(formatador.parse(request.getParameter("birthdate")));
			patient.setBirthday(g);
			
			patientBusiness.registerPatient(patient);
			
			request.setAttribute("registrook", "Paciente inserido com sucesso");
			
			RequestDispatcher view = request.getRequestDispatcher("paciente.jsp");
			view.forward(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}