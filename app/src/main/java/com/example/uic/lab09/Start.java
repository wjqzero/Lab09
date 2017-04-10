package com.example.uic.lab09;

/**
 * Created by UIC on 4/10/2017.
 */

public class Start {
    public static void main(String[] args) {
        Account.TestAccount();
        CreditAccount.TestCreditAccount();
        StudentAccount.TestStudentAccount();
        Bank.TestBank();
        // Testing accounts through the IAccount interface.
        try {
            IAccount c = new CreditAccount("Philippe", 1000);
            System.out.println(c.getName() == "Philippe");
            System.out.println(c.getMoney() == 1000);
            c.withdraw(500);
            System.out.println(c.getMoney() == 500);
            c.withdraw(2000);
            System.out.println(c.getMoney() == -1500);
        } catch(NotEnoughMoneyException ex) {
            // This should never happen!
            System.out.println(false);
        }
        try {
            IAccount s = new StudentAccount("Philippe", 1000);
            System.out.println(s.getName() == "Philippe");
            System.out.println(s.getMoney() == 1000);
            s.withdraw(500);
            System.out.println(s.getMoney() == 500);
            try {
                s.withdraw(2000);
            } catch(NotEnoughMoneyException ex) {
                System.out.println(ex.getMessage().equals("Cannot withdraw 2000 from account, only 500 is available"));
            }
            // The amount of money should not have changed after we tried to
            // withdraw too much money.
            System.out.println(s.getMoney() == 500);
            // Trying to create a student account with a negative amount of money.
            new StudentAccount("Nonono", -100);
        } catch(NotEnoughMoneyException ex) {
            System.out.println(ex.getMessage() == "Cannot create student account with negative amount of money");
        }
    }
}
