package com.aryanganotra.parkit.Screens;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aryanganotra.parkit.R;
import com.aryanganotra.parkit.Singleton.SingletonClient;
import com.braintreepayments.cardform.view.CardForm;

import org.w3c.dom.Text;

public class PaymentActivity extends AppCompatActivity {

    TextView rec_tv, amt_tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Log.i("Timeee", String.valueOf(SingletonClient.getInstance().getDetails().getFinal_time()));

        rec_tv = findViewById(R.id.rec_tv);
        amt_tv = findViewById(R.id.amt_tv);

        final Dialog dialog = new Dialog(PaymentActivity.this, R.style.Dialog);
        dialog.setContentView(R.layout.details_dialog);
        dialog.setTitle("Receipt");
        TextView amt_tv = dialog.findViewById(R.id.amt_tv);
        TextView gst_tv = dialog.findViewById(R.id.gst_tv);
        TextView total_tv = dialog.findViewById(R.id.total_tv);
        TextView place_tv = dialog.findViewById(R.id.place);
        TextView order_id = dialog.findViewById(R.id.id_tv);
        TextView slot_code = dialog.findViewById(R.id.slot_code);
        TextView time = dialog.findViewById(R.id.time_tv);
        TextView date = dialog.findViewById(R.id.date);
        rec_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dialog.show();
            }
        });



        final CardForm cardForm = (CardForm) findViewById(R.id.card_form);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .cardholderName(CardForm.FIELD_REQUIRED)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .actionLabel("Purchase")
                .setup(this);
        //cardForm.validate();

        Button pay_btn = findViewById(R.id.pay_btn);
        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardForm.isValid()){

                }
            }
        });



    }
}
