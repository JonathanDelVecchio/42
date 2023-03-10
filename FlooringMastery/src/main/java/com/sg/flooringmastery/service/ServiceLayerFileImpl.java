
package com.sg.flooringmastery.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import com.sg.flooringmastery.dao.Dao;
import java.util.Map;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;

public class ServiceLayerFileImpl implements ServiceLayer {

    private Dao dao;

    public ServiceLayerFileImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public Map<Integer, Order> getOrdersFromDate(String date) throws InvalidDateException {
        if (!isValidDate(date)) {
            throw new InvalidDateException("Invalid Date Entered");
        }
        try {
            return dao.getOrdersFromDate(date);
        } catch (FileNotFoundException e) {
            throw new InvalidDateException("Could not find file with date entered");
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return dao.getAllProducts();
    }

    @Override
    public Order createOrder(String[] orderInfo) throws InvalidDateException, InvalidNameException, InvalidStateException {
        String orderDate = orderInfo[0];
        String customerName = orderInfo[1];
        String state = orderInfo[2];
        String productType = orderInfo[3]; //Input cannot be invalid to begin with since enforced in view
        String area = orderInfo[4]; //Input cannot be invalid to begin with since enforced in view

        if (!isValidDate(orderDate)) {
            throw new InvalidDateException("Invalid date entered");
        }
        if (!isInFuture(orderDate)) {
            throw new InvalidDateException("Date must be in future");
        }
        if (customerName.isBlank()) {
            throw new InvalidNameException("No value entered for name");
        }
        if (!customerName.matches("[a-zA-Z0-9.,\\s]+")) {
            throw new InvalidNameException("Invalid characters in name");
        }
        if (!dao.canSellInState(state)) {
            throw new InvalidStateException("We currently cannot sell to " + state);
        }

        try {
            return dao.createOrder(orderDate, customerName, state, productType, area);
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public Order searchOrder(String[] orderInfo) throws InvalidDateException, NoOrderException {
        String date = orderInfo[0];
        int orderNumber = Integer.parseInt(orderInfo[1]);

        if (!isValidDate(date)) {
            throw new InvalidDateException("Invalid date entered");
        }
        try {
            Order order = dao.searchOrder(date, orderNumber);
            if (order == null) {
                throw new NoOrderException("No such order filed on given date");
            }
            return order;
        } catch (FileNotFoundException e) {
            throw new NoOrderException("No such order filed on given date");
        }
    }

    @Override
    public void updateOrder(Order order, String[] orderInfo) throws InvalidDateException, InvalidStateException {
        String orderDate = orderInfo[0];
        String customerName = orderInfo[1];
        String state = orderInfo[2];
        String productType = orderInfo[3]; //Input cannot be invalid to begin with since enforced in view
        String area = orderInfo[4]; //Input cannot be invalid to begin with since enforced in view

        if (!orderDate.isBlank()) {
            if (!isValidDate(orderDate)) {
                throw new InvalidDateException("Invalid date entered");
            }
            if (!isInFuture(orderDate)) {
                throw new InvalidDateException("Order already fulfilled");
            }
        } else {
            orderDate = order.getDate();
        }

        if (!state.isBlank()) {
            if (!dao.canSellInState(state)) {
                throw new InvalidStateException("We currently cannot sell to " + state);
            }
        } else {
            state = order.getState();
        }

        if (customerName.isBlank()) {
            customerName = order.getCustomerName();
        }

        try {
            dao.updateOrder(order, orderDate, customerName, state, productType, area);
        } catch (IOException ex) {

        }
    }

    public void removeOrder(Order order) {
        try {
            dao.removeOrder(order);
        } catch (IOException ex) {

        }
    }

    public void exportAllData() {
        try {
            dao.exportAllData();
        } catch (IOException ex) {

        }
    }

    private boolean isValidDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate ld = LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean isInFuture(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate ld = LocalDate.parse(date, formatter);
            LocalDate today = LocalDate.now();
            if (ld.isAfter(today)) {
                return true;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
        return false;
    }
}
