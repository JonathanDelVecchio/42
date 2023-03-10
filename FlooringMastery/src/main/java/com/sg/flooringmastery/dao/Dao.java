/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.flooringmastery.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;


public interface Dao {
    Map<Integer, Order> getOrdersFromDate(String date) throws FileNotFoundException;
    boolean canSellInState(String stateString);
    List<Product> getAllProducts();
    Order createOrder(String date, String name, String state, String productType, String area) throws IOException;
    Order searchOrder(String date, int orderNumber) throws FileNotFoundException;
    void updateOrder(Order order, String date, String name, String state, String productType, String area) throws IOException;
    void removeOrder(Order order) throws FileNotFoundException, IOException;
    void exportAllData() throws IOException;
}
