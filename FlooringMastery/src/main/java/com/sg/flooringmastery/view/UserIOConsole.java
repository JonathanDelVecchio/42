
package com.sg.flooringmastery.view;

import java.util.Scanner;


public class UserIOConsole implements UserIO{
    final private Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public String readInput(String prompt) {
        print(prompt);
        return scanner.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        while(true){
            try{
                return Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException e) {
                print(prompt);
            }
        }
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int input;
        do {
            input = readInt(prompt);
        } while(input < min || input > max);
        return input;
    }
    
    @Override
    public double readDouble(String prompt){
        print(prompt);
        while(true){
            try{
                return Double.parseDouble(scanner.nextLine());
            }catch(NumberFormatException e){
                print(prompt);
            }
        }
    }
    
    @Override
    public double readDouble(String prompt, int min){
        double input;
        do{
            input = readDouble(prompt);
        }while(input < min);
        return input;
    }
    
    @Override
    public String getUserConfirmation(String prompt){
        String input;
        do{
            input = readInput(prompt);
        }while(!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no") && !input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));
        return input;
    }
}
