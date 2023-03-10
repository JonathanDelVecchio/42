
package com.sg.flooringmastery.view;

import java.util.List;
import java.util.Map;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;

public class View {

    private UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    public int getMenuSelection() {
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("* <<Flooring Program>>");
        io.print("* 1. Display Orders");
        io.print("* 2. Add An Order");
        io.print("* 3. Edit An Order");
        io.print("* 4. Remove An Order");
        io.print("* 5. Export All Data");
        io.print("* 6. Quit");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        return io.readInt("Please enter one of the above choices:", 1, 6);
    }

    public String[] getAddOrderInfo(List<Product> productList) {
        String orderDate = io.readInput("Please enter the order date (Format 'MM/DD/YYYY, must be in the future):");
        String customerName = io.readInput("Please enter the customer name:");
        String state = io.readInput("Please enter the state for the order:");
        int productType = displayProductMenu(productList);
        double area = io.readDouble("Please enter the area of the order (Minimum 100 sq ft)", 100);

        String[] orderInfo = {orderDate, customerName, state, String.valueOf(productType), String.valueOf(area)};
        return orderInfo;
    }

    public String getDateInfo() {
        String orderDate = io.readInput("Please enter order date: (Format:MM/dd/yyyy)");
        return orderDate;
    }

    public String[] getSearchInfo() {
        String orderDate = getDateInfo();
        int orderNumber = io.readInt("Please enter the order#:");

        String[] orderInfo = {orderDate, String.valueOf(orderNumber)};
        return orderInfo;
    }

    public String[] getEditInfo(Order order, List<Product> productList) {
        String customerName = io.readInput("Current Name: " + order.getCustomerName() + ", New Name: ");
        String state = io.readInput("Current State: " + order.getState() + ", New State: ");
        io.print("Current Product: " + order.getProductType() + ", New Product: ");
        int productType = displayProductMenu(productList);
        double area = io.readDouble("Current Area: " + order.getArea() 
                                    + ", New Area: ", 100);

        String[] orderInfo = {order.getDate(), customerName, state, String.valueOf(productType), String.valueOf(area)};
        return orderInfo;
    }

    public String getOrderConfirmation(Order order, String prompt) {
        io.print("Order Date: " + order.getDate() 
                + " " 
                + "Customer Name: " + order.getCustomerName());
        io.print("State: " + order.getState() 
                + " " 
                + "Product Type: " + order.getProductType());
        io.print("Area: " + order.getArea());
        io.print("Material Cost: $" + order.getMaterialCost());
        io.print("Labor Cost: $" + order.getLaborCost());
        io.print("Tax: $" + order.getTax());
        io.print("Total: $" + order.getTotal());

        return io.getUserConfirmation(prompt);
    }

    public void printError(String error) {
        io.print(error);
    }

    public void exportSuccess() {
        io.print("Export Successful!");
    }

    public void displayOrders(Map<Integer, Order> orderMap) {
        int counter = 0;
        for (Order order : orderMap.values()) {
            counter++;
            io.print(counter + ". Order#: " + order.getOrderNumber()
                    + ", Date: " + order.getDate()
                    + ", Customer Name: " + order.getCustomerName()
                    + ", State: " + order.getState()
                    + ", Product: " + order.getProductType()
                    + ", Area: " + order.getArea());
            io.print("Material Cost: $" + order.getMaterialCost()
                    + ", Labor Cost: $" + order.getLaborCost()
                    + ", Tax: $" + order.getTax()
                    + ", Total: $" + order.getTotal());
        }
    }

    private int displayProductMenu(List<Product> productList) {
        int counter = 0;
        for (Product product : productList) {
            counter++;
            io.print(counter + ". " + product.getProductName()
                    + ", Cost Per sqFt: $" + product.getCostPerSquareFoot()
                    + ", LaborCost Per sqFt: $" + product.getLaborCostPerSquareFoot());
        }
        return io.readInt("Please select from one of the above product choices:", 1, counter);
    }
}
