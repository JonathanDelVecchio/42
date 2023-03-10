
package com.sg.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;

public class DaoImpl implements Dao{
    
    private static final String DELIMITER = ",";
    private static final String PRODUCT_FILE = "Data/Products.txt";
    private static final String STATE_FILE = "Data/Taxes.txt";
    private static final String EXPORT_FILE = "Backup/DataExport.txt";
    private Map<Integer, Order> orderMap = new HashMap<Integer, Order>();
    private List<Product> productMasterList = new ArrayList<Product>();
    private List<State> stateList = new ArrayList<State>();
    
    public DaoImpl(){
        try {
            loadProducts();
            loadStates();
        } catch (FileNotFoundException ex) {
            
        }
    }
    
    @Override
    public Order createOrder(String date, String name, String state, String productType, String area)
            throws IOException{
        clearHashMap();
        try{
            loadOrdersFromDate(date);
        }catch(FileNotFoundException e){
            
        }
        int orderNumber;
        if(orderMap.isEmpty()){
            orderNumber = 1;
        } else {
            orderNumber = orderMap.keySet().stream().max((one,two) -> one.compareTo(two)).get() + 1;
        }
        
        State orderState = stateList.stream().filter((stateObj) ->
                stateObj.getName().equalsIgnoreCase(state) || stateObj.getAbbreviation().equalsIgnoreCase(state))
                .findFirst().get();
        Product orderProduct = productMasterList.get(Integer.parseInt(productType) - 1);
        Order order = new Order(orderNumber, name, state, orderState.getTaxRate(),
                orderProduct.getProductName(), new BigDecimal(area),
                orderProduct.getCostPerSquareFoot(), orderProduct.getLaborCostPerSquareFoot());
        order.setDate(date);
        orderMap.put(order.getOrderNumber(), order);
        writeAllOrders(order.getDate());
        return order;
    }
    
    @Override
    public void updateOrder(Order order, String date, String name, String state, String productType, String area)
            throws IOException{
        clearHashMap();
        loadOrdersFromDate(date);
        order.setDate(date);
        order.setCustomerName(name);
        order.setState(state);
        order.setProductType(productType);
        order.setArea(new BigDecimal(area));
        orderMap.remove(order.getOrderNumber());
        orderMap.put(order.getOrderNumber(), order);
        writeAllOrders(order.getDate());
    }
    
    @Override
    public void removeOrder(Order order) throws FileNotFoundException, IOException{
        clearHashMap();
        loadOrdersFromDate(order.getDate());
        orderMap.remove(order.getOrderNumber());
        writeAllOrders(order.getDate());
    }
    
    @Override
    public Order searchOrder(String date, int orderNumber) throws FileNotFoundException{
        clearHashMap();
        loadOrdersFromDate(date);
        Order order = orderMap.get(orderNumber);
        return order;
    }
    
    @Override
    public Map<Integer, Order> getOrdersFromDate(String date) throws FileNotFoundException{
        clearHashMap();
        loadOrdersFromDate(date);
        return orderMap;
    }
    
    @Override
    public boolean canSellInState(String stateString){
        if(stateList.stream().noneMatch((state) -> (state.getName().equalsIgnoreCase(stateString) || state.getAbbreviation().equalsIgnoreCase(stateString))))
            return false;
        return true;
    }
    
    @Override
    public List<Product> getAllProducts(){
        return productMasterList;
    }
    
    @Override
    public void exportAllData() throws IOException{
        PrintWriter out = new PrintWriter(new FileWriter(EXPORT_FILE));
        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,"
                + "CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total,Date");
        out.flush();
        File dir = new File("Orders/");
        File[] orderFiles = dir.listFiles();
        for(File file : orderFiles){
            clearHashMap();
            loadOrdersFromFile(file);
            for(Order order : orderMap.values()){
                String orderInfo = marshallOrder(order);
                out.print(orderInfo);
                out.println("," + order.getDate());
                out.flush();
            }
        }
        out.close();
    }
    
    private String marshallOrder(Order order){
        String orderInfo = String.valueOf(order.getOrderNumber()) + DELIMITER;
        orderInfo += order.getCustomerName() + DELIMITER;
        orderInfo += order.getState() + DELIMITER;
        orderInfo += order.getTaxRate() + DELIMITER;
        orderInfo += order.getProductType() + DELIMITER;
        orderInfo += order.getArea() + DELIMITER;
        orderInfo += order.getCostPerSquareFoot() + DELIMITER;
        orderInfo += order.getLaborCostPerSquareFoot() + DELIMITER;
        orderInfo += order.getMaterialCost() + DELIMITER;
        orderInfo += order.getLaborCost() + DELIMITER;
        orderInfo += order.getTax() + DELIMITER;
        orderInfo += order.getTotal();
        
        return orderInfo;
    }
    
    private Order unmarshallOrder(String line){
        if(line.isBlank())
            return null;
        String[] orderInfo = line.split(DELIMITER);
        Integer orderNumber = Integer.parseInt(orderInfo[0]);
        String customerName = orderInfo[1];
        String state = orderInfo[2];
        BigDecimal taxRate = new BigDecimal(orderInfo[3]);
        String productType = orderInfo[4];
        BigDecimal area = new BigDecimal(orderInfo[5]);
        BigDecimal costPerSquareFoot = new BigDecimal(orderInfo[6]);
        BigDecimal laborCostPerSquareFoot = new BigDecimal(orderInfo[7]);
        
        Order order = new Order(orderNumber, customerName, state, taxRate, productType, area, costPerSquareFoot,
        laborCostPerSquareFoot);
        
        return order;
    }
    
    private Product unmarshallProduct(String line){
        String[] productInfo = line.split(DELIMITER);
        String productName = productInfo[0];
        BigDecimal costPerSquareFoot = new BigDecimal(productInfo[1]);
        BigDecimal laborCostPerSquareFoot = new BigDecimal(productInfo[2]);
        
        Product product = new Product(productName, costPerSquareFoot, laborCostPerSquareFoot);
        return product;
    }
    
    private State unmarshallState(String line){
        String[] stateInfo = line.split(DELIMITER);
        String stateAbbreviation = stateInfo[0];
        String stateName = stateInfo[1];
        String taxRate = stateInfo[2];
        
        State state = new State(stateName, stateAbbreviation, taxRate);
        return state;
    }
    
    private void writeAllOrders(String date) throws IOException{
        String fileName = "Orders/Orders_";
        fileName += date.replaceAll("/", "");
        fileName += ".txt";
        PrintWriter out = new PrintWriter(new FileWriter(fileName));
        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,"
                + "CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
        out.flush();
        for(Order order : orderMap.values()){
            String orderInfo = marshallOrder(order);
            out.println(orderInfo);
            out.flush();
        }
        out.close();
    }
    
    private void loadProducts() throws FileNotFoundException{
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
        String currentLine;
        Product currentProduct;
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentProduct = unmarshallProduct(currentLine);
            productMasterList.add(currentProduct);
        }
        scanner.close();
    }
    
    private void loadStates() throws FileNotFoundException{
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(STATE_FILE)));
        String currentLine;
        State currentState;
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentState = unmarshallState(currentLine);
            stateList.add(currentState);
        }
        scanner.close();
    }
    
    private void loadOrdersFromDate(String date) throws FileNotFoundException{
        String fileName = "Orders/Orders_";
        fileName += date.replaceAll("/", "");
        fileName += ".txt";
        
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
        String currentLine = scanner.nextLine();//Header Row
        Order currentOrder;
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentOrder = unmarshallOrder(currentLine);
            if(currentOrder == null)
                continue;
            currentOrder.setDate(date);
            orderMap.put(currentOrder.getOrderNumber(), currentOrder);
        }
        scanner.close();
    }
    
    private void loadOrdersFromFile(File file) throws FileNotFoundException{
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)));
        String currentLine = scanner.nextLine();//Header Row
        Order currentOrder;
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentOrder = unmarshallOrder(currentLine);
            if(currentOrder == null)
                continue;
            String date = file.getName().substring(7, 9);
            date += "/";
            date += file.getName().substring(9, 11);
            date += "/";
            date += file.getName().substring(11);
            currentOrder.setDate(date);
            orderMap.put(currentOrder.getOrderNumber(), currentOrder);
        }
        scanner.close();
    }
    
    private void clearHashMap(){
        orderMap.clear();
    }
}
