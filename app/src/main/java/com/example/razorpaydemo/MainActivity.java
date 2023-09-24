package com.example.razorpaydemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    Button btPay;
    EditText a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btPay = findViewById(R.id.bt_pay);

        a = findViewById(R.id.amt);



        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sAmount = a.getText().toString();

                int amount = Math.round(Float.parseFloat(sAmount)*100);

                Checkout checkout = new Checkout();

                checkout.setKeyID("rzp_test_KXrm6diWTqFhGZ");

                checkout.setImage(R.drawable.download);

                JSONObject obj = new JSONObject();

                try {
                    obj.put("name","SJCE Canteen");

                    obj.put("description","Test Payment");

                    obj.put("theme.color","#0093DD");

                    obj.put("currency","INR");

                    obj.put("amount",amount);

                    obj.put("prefill.contact","1023456789");

                    obj.put("prefill.email","abc@rzp.com");

                    checkout.open(MainActivity.this,obj);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Payment ID");

        builder.setMessage(s);

        builder.show();


    }

    @Override
    public void onPaymentError(int i, String s) {

        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

    }
}