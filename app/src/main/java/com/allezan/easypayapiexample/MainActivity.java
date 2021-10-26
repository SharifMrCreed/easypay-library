package com.allezan.easypayapiexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.allezan.easypayapi.EasyPayApiConnection;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout tlPhoneNumber, tlReason, tlClientId, tlSecret, tlAmount;
    EditText etPhoneNumber, etReason, etClientId, etSecret, etAmount;
    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tlPhoneNumber = findViewById(R.id.l_phone);
        tlReason = findViewById(R.id.l_reason);
        tlClientId = findViewById(R.id.l_client_id);
        tlAmount = findViewById(R.id.l_amount);
        tlSecret = findViewById(R.id.l_secret);
        btnPay = findViewById(R.id.btn_pay);
        etPhoneNumber = tlPhoneNumber.getEditText();
        etReason = tlReason.getEditText();
        etClientId = tlClientId.getEditText();
        etAmount = tlAmount.getEditText();
        etSecret = tlSecret.getEditText();

        etPhoneNumber.setText(R.string.ug_code);

        btnPay.setOnClickListener(v ->{
            if (areValidEntries()){
                makePayment();
            }
        });

    }

    private void makePayment() {
        String reason = etReason.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();
        String secret = etSecret.getText().toString();
        String clientId = etClientId.getText().toString();
        int amount = Integer.parseInt(etAmount.getText().toString());

        new EasyPayApiConnection().amount(amount)
                .clientId(clientId)
                .secret(secret)
                .reason(reason)
                .orderId(String.valueOf(System.currentTimeMillis()))
                .phoneNumber(phoneNumber)
                .startPayment();
    }

    private boolean areValidEntries() {
        String s = etAmount.getText().toString();
        String reason = etReason.getText().toString();
        String secret = etSecret.getText().toString();
        String clientId = etClientId.getText().toString();
        String pPhoneNumber = etPhoneNumber.getText().toString();
        if (TextUtils.isEmpty(s)){
            tlAmount.setError("Amount must be above 500");
            return false;
        }
        if (TextUtils.isEmpty(secret)){
            tlSecret.setError("your secret is required");
            return false;
        }
        if (TextUtils.isEmpty(clientId)){
            tlClientId.setError("your client id is required");
            return false;
        }
        if (TextUtils.isEmpty(reason)){
            tlReason.setError("add a reason");
            return false;
        }
        int amount = Integer.parseInt(s);
        if (TextUtils.isEmpty(pPhoneNumber) && !pPhoneNumber.contains(getResources().getString(R.string.ug_code))) {
            etPhoneNumber.setText(R.string.ug_code);
            tlPhoneNumber.setError("country code +256 is required");
            return false;
        }

        if (pPhoneNumber.length() != 13) {
            etPhoneNumber.setText(R.string.ug_code);
            tlPhoneNumber.setError("Phone Number is missing some digits");
            return false;
        }
        if (amount<500) {
            tlAmount.setError("Amount must be above 500");
            return false;
        }
        return true;
    }
}