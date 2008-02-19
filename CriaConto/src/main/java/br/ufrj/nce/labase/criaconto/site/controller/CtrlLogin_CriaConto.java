package main.java.br.ufrj.nce.labase.criaconto.site.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: CtrlLogin_CriaConto
 *
 */
 public class CtrlLogin_CriaConto extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CtrlLogin_CriaConto() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			RequestDispatcher view= request.getRequestDispatcher("login_criaconto.jsp");
			view.forward(request, response);
		
		}
		catch(NullPointerException npe)
		{
			System.out.println("Erro de acesso à memória em "+this.getServletName()+" : "+npe.toString());	
		}
		catch(Exception e)
		{
			System.out.println("Erro em  "+this.getServletName()+" : "+e.toString());
		}
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}