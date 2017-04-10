package com.example.uic.lab09;

/**
 * Created by UIC on 4/10/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class ViewWithdraw extends Button implements IModelListener {
    private Bank model;
    private ControllerWithdraw controller;
    public ViewWithdraw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setMVC(Bank md, ControllerWithdraw ct) {
        this.model = md;
        this.controller = ct;
        // The setMVC method registers the view with the model.
        // This is optional since this view does not display any model data
        // but we do it to follow the Model - View - Controller pattern.
        model.addListener(this);
        notifyModelListener();
        // The user can type in the first text field the name of a bank account
        // customer and can type in the second text field an amount of money.
        // When the user then clicks on the button, the action listener of the
        // button reads the name of the customer that was typed in the first text
        // field and the amount of money that was typed in the second text field
        // and calls the withdraw method of the controller with these two strings
        // as argument. The withdraw method of the controller then returns a
        // string as result. If the string returned by the withdraw method is
        // different from the empty string "" then this string should be
        // displayed back to the user using a Toast. If the string returned by
        // the withdraw method is equal to the empty string "" then nothing happens.
        this.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Activity a = (Activity)v.getContext();
                EditText etn = (EditText)a.findViewById(R.id.editTextWName);
                EditText etm = (EditText)a.findViewById(R.id.editTextWAmount);
                String name = etn.getText().toString();
                String amount = etm.getText().toString();
                String result = controller.withdraw(name, amount);
                if(!result.equals("")) {
                    Toast.makeText(v.getContext(), result, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    // Does nothing, because the ViewWithdraw class does not graphically display
    // any data from the bank (the model).
    @Override
    public void notifyModelListener() {
    }
}
