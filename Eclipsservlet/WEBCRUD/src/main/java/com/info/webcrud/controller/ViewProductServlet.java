package com.info.webcrud.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.info.webcrud.dao.ProductDAO;
import com.info.webcrud.model.Product;
@WebServlet(name = "ViewProductServlet", urlPatterns = {"/ViewProductServlet"})
public class ViewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ViewProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ViewProductservlet called...."); 
		PrintWriter out = response.getWriter();
		
		  out.print("<html>");
		  out.print("<body>");
		  out.print(" <a href='add_product.html'>Add product</a>&nbsp;&nbsp;&nbsp;\n"
		  		+ "  <a href='./ViewProductServlet'>View product</a>");
		  out.print("<h1>Product table</h>");
		  ArrayList<Product> list =  ProductDAO.getProductList();
		  out.print("<table border='1' width='100%'>");
		  out.print("<tr>");
		  out.print("<td>Id</td>");
		  out.print("<td>Title</td>");
		  out.print("<td>Brand</td>");
		  out.print("<td>Price</td>");
		  out.print("<td>Edit</td>");
		  out.print("<td>Delete</td>");
		  out.print("</tr>");
		  for(Product p : list) {
			  out.print("<tr>");
			  out.print("<td>"+p.getId()+"</td>");
			  out.print("<td>"+p.getTitle()+"</td>");
			  out.print("<td>"+p.getBrand()+"</td>");
			  out.print("<td>"+p.getPrice()+"</td>");
			  out.print("<td><a href='./DeleteProductServlet?id="+p.getId()+"'><button>Edit</button></a></td>");
			  out.print("<td><a href='./DeleteProductServlet?id="+p.getId()+"'><button>Delete</button></a></td>");
			  out.print("</tr>");
			     
		  }
		  out.print("</table>");
		  out.print("</body>");
		  out.print("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
