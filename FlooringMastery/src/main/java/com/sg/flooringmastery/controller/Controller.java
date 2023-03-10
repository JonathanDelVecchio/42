
package com.sg.flooringmastery.controller;


import java.util.Map;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.InvalidDateException;
import com.sg.flooringmastery.service.InvalidNameException;
import com.sg.flooringmastery.service.InvalidStateException;
import com.sg.flooringmastery.service.NoOrderException;
import com.sg.flooringmastery.service.ServiceLayer;
import com.sg.flooringmastery.view.View;


public class Controller {
    

    private View view;
    private ServiceLayer service;
    
    public Controller(ServiceLayer service, View view){
        this.service = service;
        this.view = view;
    }
    
    
    
    public void run(){
        boolean running = true;
        int menuSelection = 0;
        while(running){

            
            // Rule Switch Main Menu Selection
            menuSelection = getMenuSelection();
            switch(menuSelection){
                case 1 -> displayOrders();
                case 2 -> addOrder();
                case 3 -> editOrder();
                case 4 -> removeOrder();
                case 5 -> exportAll();
                case 6 -> running = false;
            }
        }
    }
    
    //Retrieves Menu Selection from View Object
    private int getMenuSelection(){
        return view.getMenuSelection();
    }
    
    //View object Displays oders, creates a map of orders from Service Layer with specified date
    private void displayOrders(){
        String orderDate = view.getDateInfo();
        try{
            Map<Integer, Order> orderMap = service.getOrdersFromDate(orderDate);
            view.displayOrders(orderMap);
        }catch(InvalidDateException e){
            view.printError(e.getMessage());
        }
    }
    
    private void addOrder(){
        String[] orderInfo = view.getAddOrderInfo(service.getAllProducts());
        Order order;
        try{
            order = service.createOrder(orderInfo);
            String input = view.getOrderConfirmation(order, "Do you want to place this order?: (Yes, Y, No, N)");
            if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no"))
                service.removeOrder(order);
        } catch (InvalidDateException ex) {
            view.printError(ex.getMessage());
        } catch (InvalidNameException ex) {
            view.printError(ex.getMessage());
        } catch (InvalidStateException ex) {
            view.printError(ex.getMessage());
        }
    }
    
    private void editOrder(){
        String[] searchInfo = view.getSearchInfo();
        Order order;
        try {
            order = service.searchOrder(searchInfo);
            String[] editInfo = view.getEditInfo(order, service.getAllProducts());
            try {
                service.updateOrder(order, editInfo);
            } catch (InvalidStateException ex) {
                view.printError(ex.getMessage());
            }
        } catch (InvalidDateException ex) {
            view.printError(ex.getMessage());
        } catch (NoOrderException ex) {
            view.printError(ex.getMessage());
        }
    }
    
    private void removeOrder(){
        String[] searchInfo = view.getSearchInfo();
        try {
            Order order = service.searchOrder(searchInfo);
            service.removeOrder(order);
        } catch (InvalidDateException ex) {
            view.printError(ex.getMessage());
        } catch (NoOrderException ex) {
            view.printError(ex.getMessage());
        }
    }
    
    private void exportAll(){
        service.exportAllData();
        view.exportSuccess();
    }
}
