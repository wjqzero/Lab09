package com.example.uic.lab09;

/**
 * Created by UIC on 4/10/2017.
 */

public class CreditAccount extends Account{
    public CreditAccount(String name, int money) {
        super(name, money);
    }
    // The withdraw method of the CreditAccount class simply subtracts the
    // amount of money given as argument to the method from the amount of
    // money currently stored in the account. A credit account is allowed to
    // have a negative amount of money in the account, so money can be
    // withdrawn from a credit account even if this makes the amount of money
    // in the credit account become negative.
    // The public getMoney method and the protected setMoney method are
    // inherited from the superclass Account.
    @Override
    public void withdraw(int amount) {
        setMoney(getMoney() - amount);
    }
    public static void TestCreditAccount() {
        CreditAccount c = new CreditAccount("Philippe", 1000);
        System.out.println(c.getName() == "Philippe");
        System.out.println(c.getMoney() == 1000);
        c.setMoney(2000);
        System.out.println(c.getMoney() == 2000);
        c.withdraw(500);
        System.out.println(c.getMoney() == 1500);
        c.withdraw(2000);
        System.out.println(c.getMoney() == -500);
    }
}
