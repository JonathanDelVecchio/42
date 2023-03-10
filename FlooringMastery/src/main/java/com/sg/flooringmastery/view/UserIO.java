
package com.sg.flooringmastery.view;


public interface UserIO {
    void print(String msg);
    String readInput(String prompt);
    int readInt(String prompt);
    int readInt(String prompt, int min, int max);
    double readDouble(String prompt);
    double readDouble(String prompt, int min);
    String getUserConfirmation(String prompt);
}
