package com.example.uic.lab09;

/**
 * Created by UIC on 4/10/2017.
 */

public class StudentAccount extends Account {
    // The constructor of the StudentAccount class takes a name and an amount
    // of money as arguments. If the amount of money given as argument is
    // strictly less than zero then the constructor should throw a
    // NotEnoughMoneyException with the message "Cannot create student
    // account with negative amount of money".
    public StudentAccount(String name, int money) throws NotEnoughMoneyException {
        super(name, money);
        if(money < 0) {
            throw new NotEnoughMoneyException("Cannot create student account with negativ e amount of money");
        }
    }
    // The withdraw method of the StudentAccount class subtracts the amount
    // of money given as argument to the method from the amount of money
    // currently stored in the account. A student account is not allowed to
    // have a negative amount of money in the account, so money can be
    // withdrawn from a student account only if the amount of money in the
    // student account will remain positive. If the amount to withdraw is
    // too big then the amount of money in the student account should not
    // change and the withdraw method should throw a NotEnoughMoneyException
    // with the message "Cannot withdraw XXX from account, only YYY is
    // available", where XXX is replaced by the amount of money that was
    // given as argument to the withdraw method and YYY is replaced by the
    // amount of money currently in the student account.
    // The public getMoney method and the protected setMoney method are
    // inherited from the superclass Account.
    @Override
    public void withdraw(int amount) throws NotEnoughMoneyException {
        if(amount > getMoney()) {
            throw new NotEnoughMoneyException("Cannot withdraw " + amount +
                    " from account, only " + getMoney() + " is available");
        } else {
            setMoney(getMoney() - amount);
        }
    }
    public static void TestStudentAccount() {
        try {
            StudentAccount s = new StudentAccount("Philippe", 1000);
            System.out.println(s.getName() == "Philippe");
            System.out.println(s.getMoney() == 1000);
            s.setMoney(2000);
            System.out.println(s.getMoney() == 2000);
            s.withdraw(500);
            System.out.println(s.getMoney() == 1500);
            try {
                s.withdraw(2000);
            } catch(NotEnoughMoneyException ex) {
                System.out.println(ex.getMessage().equals("Cannot withdraw 2000 from account, only 1500 is available"));
            }
            // The amount of money should not have changed after we tried to
            // withdraw too much money.
            System.out.println(s.getMoney() == 1500);
            // Trying to create a student account with a negative amount of money.
            new StudentAccount("Nonono", -100);
        } catch(NotEnoughMoneyException ex) {
            System.out.println(ex.getMessage() == "Cannot create student account with negative amount of money");
        }
    }

}
