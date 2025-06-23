package calculatorServlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import cart.Cart;

import java.io.IOException;

@WebServlet("/CalculatorServlet")
public class CalculatorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CalculatorServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Grab raw input
        String noItemsParam = request.getParameter("noItems");
        String priceParam = request.getParameter("price");
        String taxParam = request.getParameter("tax");
        String customerName = request.getParameter("customerName");
        String province = request.getParameter("province");

        // Debug output to console
        System.out.println("DEBUG INPUT:");
        System.out.println("noItems = " + noItemsParam);
        System.out.println("price = " + priceParam);
        System.out.println("tax = " + taxParam);
        System.out.println("customerName = " + customerName);
        System.out.println("province = " + province);

        try {
            // Check for empty/null values before parsing
            if (noItemsParam == null || noItemsParam.isEmpty() ||
                priceParam == null || priceParam.isEmpty() ||
                taxParam == null || taxParam.isEmpty()) {
                throw new NumberFormatException("Missing required numeric input.");
            }

            int noItems = Integer.parseInt(noItemsParam);
            double price = Double.parseDouble(priceParam);

            HttpSession session = request.getSession(true);
            double tax;

            // Get tax rate from form or session or context
            if (taxParam != null && !taxParam.isEmpty()) {
                tax = Double.parseDouble(taxParam);
                session.setAttribute("taxRate", String.valueOf(tax));
            } else {
                String sessionTax = (String) session.getAttribute("taxRate");
                if (sessionTax != null) {
                    tax = Double.parseDouble(sessionTax);
                } else {
                    String defaultTax = getServletContext().getInitParameter("defaultTaxRate");
                    tax = Double.parseDouble(defaultTax);
                }
            }

            // Shipping fee logic
            double shippingFees = (province != null && province.equalsIgnoreCase("Ontario")) ? 0.0 : 7.99;

            // Calculate total
            double total = Cart.calculateTotal(noItems, price, tax, shippingFees);

            // Store total in servlet context
            getServletContext().setAttribute("totalPrice", total);

            // Store values in request scope
            request.setAttribute("customerName", customerName);
            request.setAttribute("province", province);
            request.setAttribute("noItems", noItems);
            request.setAttribute("price", price);
            request.setAttribute("tax", tax);
            request.setAttribute("shippingFees", shippingFees);
            request.setAttribute("total", total);

            // Forward to JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Results.jspx");
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace(); // Log full error
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid numeric input.");
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); // Log full error
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
