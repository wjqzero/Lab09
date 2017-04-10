package com.example.uic.lab09;

/**
 * Created by UIC on 4/10/2017.
 */

public abstract class Account implements IAccount {
    // The name instance variable indicates the name of the customer for the
    // bank account. The money instance variable indicates the amount of
    // money which is currently available in the account.
    private String name;
    private int money;
    public Account(String name, int money) {
        this.name = name;
        this.money = money;
    }
    // Getter method for the name instance variable.
    @Override
    public String getName() {
        return name;
    }
    // Getter method for the money instance variable.
    @Override
    public int getMoney() {
        return money;
    }
    // The setMoney method is protected, not public. This means that only
    // subclasses of the Account class can use the setMoney method. All the
    // other classes in the software cannot use the setMoney method, so they
    // cannot change the amount of money in an account. This is good for the
    // security of the banking software!
    protected void setMoney(int money) {
        this.money = money;
    }
    // The purpose of the withdraw method is to withdraw (subtract) the
    // amount of money given as argument to the method from the amount of
    // money currently stored in the account.
    @Override
    public abstract void withdraw(int amount) throws NotEnoughMoneyException;
    // An abstract method cannot be tested because we cannot create
    // any object from that class.
    public static void TestAccount() {
    }

}
