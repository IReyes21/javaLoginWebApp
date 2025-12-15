package com.example;

import entity.Product;
import entity.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/*")
public class ProductServlet extends HttpServlet {

    private ProductDAO productsDAO;

    @Override
    public void init() {
        productsDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();

        if (action == null) {
            action = "/list";
        }

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    // This should be POST only, redirect to list if accessed via GET
                    response.sendRedirect("list");
                    break;
                case "/delete":
                    deleteProduct(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    // This should be POST only, redirect to list if accessed via GET
                    response.sendRedirect("list");
                    break;
                case "/list":
                default:
                    listProducts(request, response);
                    break;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid product ID format");
            listProducts(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null) {
            action = "/list";
        }

        try {
            switch (action) {
                case "/insert":
                    insertProduct(request, response);
                    break;
                case "/update":
                    updateProduct(request, response);
                    break;
                default:
                    response.sendRedirect("list");
                    break;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid number format");
            response.sendRedirect("list");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("products", productsDAO.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product_list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product.jsp");
        request.setAttribute("isEdit", false);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Product existingProduct = productsDAO.get(id).orElseThrow(() ->
                    new ServletException("Product not found with ID: " + id));

            RequestDispatcher dispatcher = request.getRequestDispatcher("/product.jsp");
            request.setAttribute("product", existingProduct);
            request.setAttribute("isEdit", true);
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid product ID format", e);
        }
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Product product = new Product();
        // DON'T set product ID! It will be auto-generated
        // product.setProductId(...); // REMOVE THIS LINE

        product.setProductName(request.getParameter("name"));
        product.setProductDescription(request.getParameter("description"));
        product.setProductColor(request.getParameter("color"));
        product.setProductSize(request.getParameter("size"));

        try {
            product.setProductPrice(Double.parseDouble(request.getParameter("price")));
        } catch (NumberFormatException e) {
            product.setProductPrice(0.0);
        }

        productsDAO.insert(product);
        response.sendRedirect("list");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = productsDAO.get(id).orElse(new Product());

            product.setProductId(id);
            product.setProductName(request.getParameter("name"));
            product.setProductDescription(request.getParameter("description"));
            product.setProductColor(request.getParameter("color"));
            product.setProductSize(request.getParameter("size"));

            try {
                product.setProductPrice(Double.parseDouble(request.getParameter("price")));
            } catch (NumberFormatException e) {
                product.setProductPrice(0.0);
            }

            productsDAO.update(product);
        } catch (NumberFormatException e) {
            // Log the error
        }
        response.sendRedirect(request.getContextPath() + "/products/list");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = productsDAO.get(id).orElse(null);
            if (product != null) {
                productsDAO.delete(product);
            }
        } catch (NumberFormatException e) {
            // Log the error
        }
        response.sendRedirect("list");
    }
}