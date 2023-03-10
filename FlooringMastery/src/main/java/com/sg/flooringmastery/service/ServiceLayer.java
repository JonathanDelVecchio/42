
package com.sg.flooringmastery.service;

import java.util.List;
import java.util.Map;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;


public interface ServiceLayer {
    Map<Integer, Order> getOrdersFromDate(String date) throws InvalidDateException;
    List<Product> getAllProducts();    
    Order createOrder(String[] orderInfo)
            throws InvalidDateException, InvalidNameException, InvalidStateException;   
    Order searchOrder(String[] orderInfo) throws InvalidDateException, NoOrderException;
    void updateOrder(Order order, String[] orderInfo) throws InvalidDateException, InvalidStateException;   
    void removeOrder(Order order); 
    void exportAllData();
}
