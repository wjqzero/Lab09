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
public class ViewGetMoney extends Button implements IModelListener {
    private Bank model;
    private ControllerGetMoney controller;
    public ViewGetMoney(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setMVC(Bank md, ControllerGetMoney ct) {
        this.model = md;
        this.controller = ct;
        // The setMVC method registers the view with the model.
        // This is optional since this view does not display any model data
        // but we do it to follow the Model - View - Controller pattern.
        model.addListener(this);
        notifyModelListener();
        // The user can type in the EditText the name of a bank account customer.
        // When the user then clicks on the ViewGetMoney button, the action
        // listener of the button should read the name of the customer that was
        // typed in the EditView and calls the getMoney method of the controller
        // with the name of that customer as argument. The getMoney method of
        // the controller returns a string as result which should then be
        // displayed back to the user using a Toast.
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity a = (Activity)v.getContext();
                EditText et = (EditText)a.findViewById(R.id.editTextGMName);
                String name = et.getText().toString();
                String result = controller.getMoney(name);
                Toast.makeText(v.getContext(), result, Toast.LENGTH_SHORT).show();
            }
        });
    }
    // Does nothing, because the ViewGetMoney class does not graphically display
    // any data from the bank (the model).
    @Override
    public void notifyModelListener() {
    }
}
