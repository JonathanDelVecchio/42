package com.sg.flooringmastery;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sg.flooringmastery.controller.Controller;

public class App {

    public static void main(String[] args) {
        
        
        //initializes a Spring ApplicationContext and retrieves a Controller bean from it to run the application.
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Controller controller = ctx.getBean("controller", Controller.class);
        controller.run();
    }
}
