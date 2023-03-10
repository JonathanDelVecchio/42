package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.util.Objects;


public class Order {
    
    private Integer orderNumber;
    private String customerName;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;
    private String state;
    private String date;
    
    public Order(Integer orderNumber, String customerName,
            String state, BigDecimal taxRate,
            String productType, BigDecimal area,
            BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot){
        

        
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.state = state;
        this.taxRate = taxRate;
        this.productType = productType;
        this.area = area;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
        
        
        this.materialCost = area.multiply(costPerSquareFoot);
        this.laborCost = area.multiply(laborCostPerSquareFoot);
        this.tax = (materialCost.add(laborCost)).add((taxRate.divide(BigDecimal.valueOf(100.00))));
        this.total = materialCost.add(laborCost).add(tax);
    }
    
    public Integer getOrderNumber(){
        return this.orderNumber;
    }
    
    public String getCustomerName(){
        return this.customerName;
    }
    
    public BigDecimal getTaxRate(){
        return this.taxRate;
    }
    
    public String getProductType(){
        return this.productType;
    }
    
    public BigDecimal getArea(){
        return this.area;
    }
    
    public BigDecimal getCostPerSquareFoot(){
        return this.costPerSquareFoot;
    }
    
    public BigDecimal getLaborCostPerSquareFoot(){
        return this.laborCostPerSquareFoot;
    }
    
    public BigDecimal getMaterialCost(){
        return this.materialCost;
    }
    
    public BigDecimal getLaborCost(){
        return this.laborCost;
    }
    
    public BigDecimal getTax(){
        return this.total;
    }
    
    public BigDecimal getTotal(){
        return this.total;
    }
    
    public void setOrderNumber(Integer orderNumber){
        this.orderNumber = orderNumber;
    }
    
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }
    
    public void setTaxRate(BigDecimal taxRate){
        this.taxRate = taxRate;
    }
    
    public void setProductType(String productType){
        this.productType = productType;
    }
    
    public void setArea(BigDecimal area){
        this.area = area;
    }
    
    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot){
        this.costPerSquareFoot = costPerSquareFoot;
    }
    
    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot){
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }
    
    public void setState(String state){
        this.state = state;
    }
    
    public String getState(){
        return state;
    }
    
    public String getDate(){
        return date;
    }
    
    public void setDate(String date){
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" + "orderNumber=" + orderNumber + ", customerName=" + customerName + ", taxRate=" + taxRate + ", productType=" + productType + ", area=" + area + ", costPerSquareFoot=" + costPerSquareFoot + ", laborCostPerSquareFoot=" + laborCostPerSquareFoot + ", materialCost=" + materialCost + ", laborCost=" + laborCost + ", tax=" + tax + ", total=" + total + ", state=" + state + ", date=" + date + '}';
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.orderNumber);
        hash = 73 * hash + Objects.hashCode(this.customerName);
        hash = 73 * hash + Objects.hashCode(this.taxRate);
        hash = 73 * hash + Objects.hashCode(this.productType);
        hash = 73 * hash + Objects.hashCode(this.area);
        hash = 73 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 73 * hash + Objects.hashCode(this.laborCostPerSquareFoot);
        hash = 73 * hash + Objects.hashCode(this.materialCost);
        hash = 73 * hash + Objects.hashCode(this.laborCost);
        hash = 73 * hash + Objects.hashCode(this.tax);
        hash = 73 * hash + Objects.hashCode(this.total);
        hash = 73 * hash + Objects.hashCode(this.state);
        hash = 73 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.orderNumber, other.orderNumber)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.costPerSquareFoot, other.costPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.laborCostPerSquareFoot, other.laborCostPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        return Objects.equals(this.total, other.total);
    }
    
    
}
