package com.example.uic.lab09;

/**
 * Created by UIC on 4/10/2017.
 */

import java.util.ArrayList;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


public class Bank extends SQLiteOpenHelper {

    private String name;
    //private ArrayList<IAccount> accounts;
    private ArrayList<IModelListener> listeners;
    private final String tablename = "mytable";
    private final String col1 = "name";
    private final String col2 = "balance";
    private final String col3 = "type";

    public Bank(String name, Context context) {
        super(context, "mydb.db", null, 1);
        this.name = name;
        // When a bank is created, it has an arraylist of accounts but the
        // arraylist is empty (the arraylist does not contain any bank account).
        //this.accounts = new ArrayList<IAccount>();
        // When a bank is created, it has an empty arraylist of listeners.
        this.listeners = new ArrayList<IModelListener>();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Be careful about spaces and commas when creating a table!
        db.execSQL("CREATE TABLE " + tablename + "(_id INTEGER PRIMARY KEY, " + col1 + " TEXT," + col2 + " INTEGER," + col3 + " INTEGER)");
        // Values to be put in one row of one table of the database.
        ContentValues values = new ContentValues();
        //values.put(columnname, 0); // Initialize counter to 0.
        // Insert initial row into table of database.
        //db.insert(tablename, null, values);
    }

    // The addListener method takes a listener as argument and adds the
    // listener to the arraylist of listeners for the bank.
    public void addListener(IModelListener listener) {
        listeners.add(listener);
    }
    // Notify all listeners that the bank data has changed. Once they
    // have been notified, each listener will then use the appropriate
    // methods of the bank to get all the new values that the listener
    // needs to display.
    private void notifyAllModelListeners(){
        for(IModelListener listener : listeners){
            listener.notifyModelListener();
        }
    }
    // The addAccount method takes an account as argument and adds the
    // account to the arraylist of accounts for the bank.
    public void addAccount(IAccount account) {
        //accounts.add(account);

        // Notify all the listeners that a change has occurred with the bank’s data.
        notifyAllModelListeners();
    }
    // The totalMoney method returns as result the total amount of money in
    // all the bank accounts of the bank.
    public int totalMoney() {
        int total = 0;
        // We loop over all the accounts in the arraylist and add the amount of
        // money in each account to the total.
        for(IAccount a : accounts) {
            total += a.getMoney();
        }
        return total;
    }
    // The getMoney method takes as argument the name of a customer and
    // returns as result the amount of money currently stored in the bank
    // account that belongs to that customer. If the customer does not have
    // a bank account in the bank then the getMoney method should throw an
    // UnknownCustomerException with the message "Customer XXX unknown", where
    // XXX is replaced with the name of the customer.
    public int getMoney(String name) throws UnknownCustomerException {
        // We loop over all the accounts in the arraylist, looking for the
        // account with the correct customer name.
        for(IAccount a : accounts) {
            if(a.getName().equals(name)) {
                // We have found the account with the correct customer name.
                // So we return as result the amount of money in that account.
                return a.getMoney();
            }
        }
        // If we reach this point in the code, then it means we have looped
        // over all the accounts in the arraylist without finding an account
        // with the correct customer name. Therefore this customer does not
        // have an account in the bank and we throw an exception.
        throw new UnknownCustomerException("Customer " + name + " unknown");
    }
    // The withdraw method takes as argument the name of a customer and an
    // amount of money and withdraws that amount of money from the amount of
    // money currently stored in the bank account that belongs to that
    // customer. If the customer does not have a bank account in the bank
    // then the withdraw method should throw an UnknownCustomerException with
    // the message "Customer XXX unknown", where XXX is replaced with the
    // name of the customer.
    public void withdraw(String name, int amount) throws UnknownCustomerException, NotEnoughMoneyException {
        // We loop over all the accounts in the arraylist, looking for the
        // account with the correct customer name.
        for(IAccount a : accounts) {
            if(a.getName().equals(name)) {
                // We have found the account with the correct customer name.
                // So we withdraw the amount of money from that account, and
                // end the function by returning nothing.
                a.withdraw(amount);
                // Notify all the listeners that a change has occurred with the bank’s data.
                        notifyAllModelListeners();
                return;
            }
        }
        // If we reach this point in the code, then it means we have looped
        // over all the accounts in the arraylist without finding an account
        // with the correct customer name. Therefore this customer does not
        // have an account in the bank and we throw an exception.
        throw new UnknownCustomerException("Customer " + name + " unknown");
    }
    public static void TestBank() {
        Bank b = new Bank("UIC Bank");
        System.out.println(b.totalMoney() == 0);
        b.addAccount(new CreditAccount("Philippe", 1000));
        try {
            System.out.println(b.getMoney("Philippe") == 1000);
            System.out.println(b.totalMoney() == 1000);
            b.addAccount(new StudentAccount("Meunier", 1500));
            System.out.println(b.getMoney("Philippe") == 1000);
            System.out.println(b.getMoney("Meunier") == 1500);
            System.out.println(b.totalMoney() == 2500);
            b.getMoney("Ms. Park");
        } catch(UnknownCustomerException ex) {
            System.out.println(ex.getMessage().equals("Customer Ms. Park unknown"));
        } catch(NotEnoughMoneyException ex) {
            // This should never happen!
            System.out.println(false);
        }
        try {
            b.withdraw("Philippe", 500);
            System.out.println(b.getMoney("Philippe") == 500);
            System.out.println(b.getMoney("Meunier") == 1500);
            b.withdraw("Ms. Park", 1);
        } catch(UnknownCustomerException ex) {
            System.out.println(ex.getMessage().equals("Customer Ms. Park unknown"));
        } catch(NotEnoughMoneyException ex) {
            // This should never happen!
            System.out.println(false);
        }
        try {
            b.withdraw("Meunier", 2000);
        } catch(NotEnoughMoneyException ex) {
            System.out.println(ex.getMessage().equals("Cannot withdraw 2000 from account, only 1500 is available"));
        } catch(UnknownCustomerException ex) {
            // This should never happen!
            System.out.println(false);
        }
        try {
            System.out.println(b.getMoney("Philippe") == 500);
            System.out.println(b.getMoney("Meunier") == 1500);
        } catch(UnknownCustomerException ex) {
            // This should never happen!
            System.out.println(false);
        }
    }
}