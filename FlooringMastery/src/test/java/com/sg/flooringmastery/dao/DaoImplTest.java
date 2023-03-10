
package com.sg.flooringmastery.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sg.flooringmastery.dto.Order;



public class DaoImplTest {
    
    Dao testDao;
    
    public DaoImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testDao = ctx.getBean("testDao", DaoImplTestFile.class);
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
    public void tearDown() throws IOException {
        File dir = new File("TestOrders/");
        File[] orderFiles = dir.listFiles();
        for (File file : orderFiles) {
            try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
                out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,"
                        + "CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
            }
        }
    }

    @Test
    public void testAddGetOrder() throws IOException {
        
        // Test 1: Create Order manually, w/ manual input of inpute productType, 
        // costPerSquareFoot, laborCostPerSquare foot, others auto calculated
        // Test 2: Create Order with input and all variables handled automatically
        // Test 3: Compare Orders created
        
        System.out.println("""
                           
                           ******** Testing Add Order ********
                           Test 1: Create Order manually, w/ manual input of inpute productType, 
                           costPerSquareFoot, laborCostPerSquare foot, others auto calculated
                           Test 2: Create Order with input and all variables handled automatically
                           Test 3: Compare Orders created 
                           
                           """);

        
        Integer orderNumber = 1;
        String name = "Test Name";
        String state = "Kentucky";
        BigDecimal taxRate = new BigDecimal("6.00");
        String productType = "Laminate";
        BigDecimal area = new BigDecimal(200);
        BigDecimal costPerSquareFoot = new BigDecimal("1.75");
        BigDecimal laborCostPerSquareFoot = new BigDecimal("2.10");
        Order testOrder = new Order(orderNumber, name, state, taxRate, productType, area, costPerSquareFoot, laborCostPerSquareFoot);
        testOrder.setDate("03/22/2023");

        // Create order using TestDaoImpl
        testDao.createOrder("03/22/2023", name, state, "2", area.toString());
        // Get order using Test Dao Impl
        Order retrievedOrder = testDao.searchOrder("03/22/2023", 1);
        
        // Check equalities
        System.out.println("Test Order:  "+ testOrder.toString());
        System.out.println("Retrieved Order: " + retrievedOrder.toString());
        assertTrue(retrievedOrder.equals(testOrder));
    }


    @Test
    public void testGetOrdersFromDate() throws IOException {
        // Create 2 orders
        Order firstOrder = testDao.createOrder("05/22/2023", "Jackie Regan", "Kentucky", "2", "200");
        Order secondOrder = testDao.createOrder("05/22/2023", "Jackie R", "Kentucky", "1", "200");

        // Create order map
        Map<Integer, Order> orderMap = testDao.getOrdersFromDate("05/22/2023");

        // Assert equalities
        assertTrue(orderMap != null);
        assertTrue(orderMap.size() == 2);
        assertTrue(orderMap.get(1).equals(firstOrder));
        assertTrue(orderMap.get(2).equals(secondOrder));
    }
}


