package com.example;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SneakerDiscountServlet")
public class SneakerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Base price for one sneaker
    private static final double BASE_PRICE = 100.0;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String quantityStr = request.getParameter("quantity");
        String error = null;

        int quantity = 0;
        double discountRate = 0;
        double finalTotal = 0;

        try {
            quantity = Integer.parseInt(quantityStr);

            if (quantity <= 0) {
                error = "Quantity must be greater than 0.";
            } else {
                // Determine discount
                if (quantity >= 10) {
                    discountRate = 0.10;
                } else if (quantity >= 5) {
                    discountRate = 0.05;
                }

                double total = quantity * BASE_PRICE;
                double discountAmount = total * discountRate;
                finalTotal = total - discountAmount;

                request.setAttribute("quantity", quantity);
                request.setAttribute("price", BASE_PRICE);
                request.setAttribute("discount", discountRate * 100); // for %
                request.setAttribute("finalTotal", String.format("%.2f", finalTotal));
            }

        } catch (NumberFormatException e) {
            error = "Invalid input. Please enter a valid number.";
        }

        if (error != null) {
            request.setAttribute("error", error);
        }

        // Forward to result page
        request.getRequestDispatcher("SneakerResult.jsp").forward(request, response);
    }
}
