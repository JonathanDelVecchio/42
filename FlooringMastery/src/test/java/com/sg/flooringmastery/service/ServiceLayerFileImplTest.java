package com.sg.flooringmastery.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sg.flooringmastery.dto.Order;

public class ServiceLayerFileImplTest {

    ServiceLayer testService;

    public ServiceLayerFileImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testService = ctx.getBean("testService", ServiceLayerFileImpl.class);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test

    public void testCreateOrder() {

        //Test 1 - Wrong Date Format
        //Test 2 - Empty Customer Name
        //Test 3 - Cannot Sell to State
        System.out.println("""
                           
                                **** Test: Create Order ***
                                Test 1: Wrong Date Format
                                Test 2: Empty Customer Name
                                Test 3: Cannot Sell to State
                           """);

        try {
            testService.createOrder(new String[]{"03-07-2022", "Jackie Robinson", "Texas", "2", "200"});
            fail("Expected Invalid Date Exception was not thrown");
        } catch (InvalidNameException | InvalidStateException e) {
            fail("Expected Invalid Date Exception. Wrong exceptions thrown");
        } catch (InvalidDateException e) {
            System.out.println("Success! Invalid Date Exception thrown: " + e.getMessage());
        }

        try {
            testService.createOrder(new String[]{"08/30/2023", "", "Texas", "2", "200"});
            fail("Expected Invalid Name Exxception");
        } catch (InvalidDateException | InvalidStateException e) {
            fail("Expected Invalid Name Exception. Wrong Exceptions thrown");
        } catch (InvalidNameException e) {
            System.out.println("Success! Invalid Name Exception thrown: " + e.getMessage());
        }

        try {
            testService.createOrder(new String[]{"09/30/2023", "Jackie Robinson", "Michigan", "2", "200"});
            fail("Expected Invalid State Exception");
        } catch (InvalidDateException | InvalidNameException e) {
            fail("Expected Invalid State Exception. Wrong Exceptions thrown");
        } catch (InvalidStateException e) {
            System.out.println("Success! Invalid State Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testGetOrdersFromDate() {

        //Test 1 - Wrong Date Format
        //Test 2 - No file with that date 
        System.out.println("""
                           
                                ***Test: Get Order From Date***
                                Test 1: Wrong Date Format
                                Test 2: No file with that date
                           """);

        try {
            testService.getOrdersFromDate("03-22-2024");
            fail("Expected Invalid Date Exception");
        } catch (InvalidDateException e) {
            System.out.println("Success! Invalid Date Exception thrown: " + e.getMessage());
        }

        try {
            testService.getOrdersFromDate("01/01/1900");
            fail("Expected Invalid Date Exception");
        } catch (InvalidDateException e) {
            System.out.println("Success! Invalid Date Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateOrder() throws InvalidDateException, InvalidNameException, InvalidStateException {
        
        //Base Test - Create Order
        //Test 1 - Wrong Date Format
        //Test 2 - Empty Customer Name
        //Test 3 - Cannot Sell to State
        System.out.println("""
                           
                                **** Test: Update Order ***
                                Base Test: Create Order
                                Test 1: Wrong Date Format
                                Test 3: Cannot Sell to State
                           """);
        

        //Create Successful Order
        Order createdOrder;
        createdOrder = testService.createOrder(new String[]{"03/22/2024", "Jackie Robinson", "Texas", "2", "200"});
        if (createdOrder != null) {
        System.out.println("Success! Order created");
        }
        
        try {
            testService.updateOrder(createdOrder, new String[]{"03-22-2024", "Jackie Robinson", "Texas", "2", "200"});
            fail("Ecpected Invalid Date Exception");
        } catch (InvalidStateException e) {
            fail("Expected Invalid Date Exception. Wrong Error Thrown");
        } catch (InvalidDateException e) {
            System.out.println("Success! Invalid Date Exception thrown: " + e.getMessage());

        }

        try {
            testService.updateOrder(createdOrder, new String[]{"07/30/2023", "Jackie Robinson", "Michigan", "2", "200"});
            fail("Ecpected Invalid State Exception");
        } catch (InvalidDateException e) {
            fail("Expected Invalid State Exception. Wrong Error Thrown");
        } catch (InvalidStateException e) {
            System.out.println("Success! Invalid State Exception thrown: " + e.getMessage());
        }
    }
    
}
