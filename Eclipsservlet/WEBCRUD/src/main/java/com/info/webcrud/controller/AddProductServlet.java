package com.info.webcrud.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.info.webcrud.dao.ProductDAO;
import com.info.webcrud.model.Product;

//@WebServlet(description = "for handing product", urlPatterns = { "/AddProductServlet" })
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String price = request.getParameter("price");
		String brand = request.getParameter("brand");

		Product p = new Product();
		p.setTitle(title);
		p.setPrice(Integer.parseInt(price));
		p.setBrand(brand);

		if (ProductDAO.persist(p))
			response.sendRedirect("add_product.html");
		else
			response.sendRedirect("errot.html");
	}

}
