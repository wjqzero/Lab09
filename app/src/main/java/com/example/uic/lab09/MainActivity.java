package com.example.uic.lab09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bank model = new Bank("UIC Bank");
        ControllerSimple cs = new ControllerSimple(model);
        ViewSimple vs = (ViewSimple)findViewById(R.id.textViewSimple);
        vs.setMVC(model, cs);
        // Adding two accounts to the model to check that the view
        // correctly displays the result (2500).
        model.addAccount(new CreditAccount("Philippe", 1000));
        try {
            model.addAccount(new StudentAccount("Meunier", 1500));
        } catch(NotEnoughMoneyException ex) {
            // This should never happen!
            Toast.makeText(this, "This should never happen!", Toast.LENGTH_SHORT).show();
        }
        // Add a ViewGetMoney view with a ControllerGetMoney controller
        // and the same model as before.
        ControllerGetMoney cgm = new ControllerGetMoney(model);
        ViewGetMoney vgm = (ViewGetMoney)findViewById(R.id.buttonGM);
        vgm.setMVC(model, cgm);
        // Add a ViewWithdraw view that uses a ControllerWithdraw controller
        // and the same model as before.
        ControllerWithdraw cw = new ControllerWithdraw(model);
        ViewWithdraw vw = (ViewWithdraw)findViewById(R.id.buttonW);
        vw.setMVC(model, cw);
    }
}
