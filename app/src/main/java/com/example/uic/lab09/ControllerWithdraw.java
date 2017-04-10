package com.example.uic.lab09;

/**
 * Created by UIC on 4/10/2017.
 */

public class ControllerWithdraw {
    private Bank model;
    public ControllerWithdraw(Bank m) {
        this.model = m;
    }
    // The withdraw method takes the name of a bank customer and an amount of
    // money (as a string) as arguments.
    public String withdraw(String name, String amount) {
        // Transforms the amount of money from a string to an integer.
        // (we assume the user typed a number for the amount!)
        int money = Integer.parseInt(amount);
        String result;
        try {
            // Calls the withdraw method of the bank to withdraw that amount of money
            // from the amount of money currently stored in the bank account that
            // belongs to that customer.
            model.withdraw(name, money);
            // If no exception occurs then the withdraw method returns the empty string.
            result = "";
        } catch(UnknownCustomerException ex) {
            // If the withdraw method of the bank throws an UnknownCustomerException
            // then the withdraw method of the controller should catch this exception
            // and return as result the error message from the exception object.
            result = ex.getMessage();
        } catch(NotEnoughMoneyException ex) {
            // If the withdraw method of the bank throws an NotEnoughMoneyException
            // then the withdraw method of the controller should catch this exception
            // and return as result the error message from the exception object.
            result = ex.getMessage();
        }
        return result;
    }
}

