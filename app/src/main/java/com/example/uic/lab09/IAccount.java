package com.example.uic.lab09;

/**
 * Created by UIC on 4/10/2017.
 */

public interface IAccount {
    String getName();
    int getMoney();
    void withdraw(int amount) throws NotEnoughMoneyException;

}
