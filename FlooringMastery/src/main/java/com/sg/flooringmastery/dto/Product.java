/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;


public class Product {
    
    private String productName;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    
    public Product(String productName, BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot){
        this.productName = productName;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }
    
    public String getProductName(){
        return productName;
    }
    
    public BigDecimal getCostPerSquareFoot(){
        return costPerSquareFoot;
    }
    
    public BigDecimal getLaborCostPerSquareFoot(){
        return laborCostPerSquareFoot;
    }
    
    public void setProductName(String productName){
        this.productName = productName;
    }
    
    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot){
        this.costPerSquareFoot = costPerSquareFoot;
    }
    
    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot){
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }
}
