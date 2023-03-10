/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;


public class State {
    private String stateName;
    private String stateAbbreviation;
    private BigDecimal taxRate;
    
    public State(String stateName, String stateAbbreviation, String taxRate){
        this.stateName = stateName;
        this.stateAbbreviation = stateAbbreviation;
        this.taxRate = new BigDecimal(taxRate);
    }
    
    public String getName(){
        return stateName;
    }
    
    public String getAbbreviation(){
        return stateAbbreviation;
    }
    
    public BigDecimal getTaxRate(){
        return taxRate;
    }
    
    public void setName(String name){
        this.stateName = name;
    }
    
    public void setAbbreviation(String abbreviation){
        this.stateAbbreviation = abbreviation;
    }
    
    public void setTaxRate(double taxRate){
        this.taxRate = new BigDecimal(String.valueOf(taxRate));
    }
}
