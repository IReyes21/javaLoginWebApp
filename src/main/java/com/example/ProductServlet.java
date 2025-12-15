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

    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            action = "/list";
        }

        switch (action) {
            case "/new":
                showCreateForm(request, response);
                break;

            case "/edit":
                showUpdateForm(request, response);
                break;

            case "/delete":
                showDeleteForm(request, response);
                break;

            case "/list":
            default:
                listProducts(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo();

        if (action == null) {
            action = "/list";
        }

        switch (action) {
            case "/insert":
                insertProduct(request, response);
                break;

            case "/update":
                updateProduct(request, response);
                break;

            case "/delete":
                deleteProduct(request, response);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/products/list");
                break;
        }
    }

    // READ (LIST)
    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("products", productDAO.getAll());
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/product_read.jsp");
        dispatcher.forward(request, response);
    }

    // CREATE (FORM)
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/product_create.jsp");
        dispatcher.forward(request, response);
    }


    // CREATE (SUBMIT)
    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Product product = new Product();

        product.setProductName(request.getParameter("name"));
        product.setProductDescription(request.getParameter("description"));
        product.setProductColor(request.getParameter("color"));
        product.setProductSize(request.getParameter("size"));

        try {
            product.setProductPrice(Double.parseDouble(request.getParameter("price")));
        } catch (NumberFormatException e) {
            product.setProductPrice(0.0);
        }

        productDAO.insert(product);
        response.sendRedirect(request.getContextPath() + "/products/list");
    }


    // UPDATE (FORM
    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.get(id)
                .orElseThrow(() -> new ServletException("Product not found"));

        request.setAttribute("product", product);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/product_update.jsp");
        dispatcher.forward(request, response);
    }

    // UPDATE (SUBMIT)
    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.get(id).orElse(new Product());

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

        productDAO.update(product);
        response.sendRedirect(request.getContextPath() + "/products/list");
    }

    // DELETE (CONFIRM FORM)
    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.get(id)
                .orElseThrow(() -> new ServletException("Product not found"));

        request.setAttribute("product", product);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/product_delete.jsp");
        dispatcher.forward(request, response);
    }


    // DELETE (SUBMIT)
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.delete(productDAO.get(id).orElse(null));
        response.sendRedirect(request.getContextPath() + "/products/list");
    }
}
