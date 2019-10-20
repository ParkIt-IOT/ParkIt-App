package com.aryanganotra.parkit.Screens;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aryanganotra.parkit.R;
import com.aryanganotra.parkit.Singleton.SingletonClient;
import com.braintreepayments.cardform.view.CardForm;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class PaymentActivity extends AppCompatActivity {

    TextView rec_tv, amt_tv;
    String price, price_gst;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Log.i("Timeee", String.valueOf(SingletonClient.getInstance().getDetails().getFinal_time()));

        rec_tv = findViewById(R.id.rec_tv);
        amt_tv = findViewById(R.id.amt_tv);
        double price_amt = SingletonClient.getInstance().getDetails().getFinal_time()/100000;
        double gst_amt = price_amt*0.08;
        price = "Rs "+String.valueOf(Math.round(price_amt));
        price_gst = "Rs "+String.valueOf(Math.round(gst_amt));
        double total = price_amt + gst_amt;
        String total_price = "Rs "+String.valueOf(Math.round(total));


        amt_tv.setText(total_price);


        final Dialog dialog = new Dialog(PaymentActivity.this, R.style.Dialog);
        dialog.setContentView(R.layout.details_dialog);
        dialog.setTitle("Receipt");
        TextView amt_tv_d = dialog.findViewById(R.id.amt_tv);
        TextView gst_tv = dialog.findViewById(R.id.gst_tv);
        TextView total_tv = dialog.findViewById(R.id.total_tv);
        TextView place_tv = dialog.findViewById(R.id.place);
        TextView order_id = dialog.findViewById(R.id.id_tv);
        TextView slot_code = dialog.findViewById(R.id.slot_code);
        TextView time = dialog.findViewById(R.id.time_tv);
        TextView date = dialog.findViewById(R.id.date);
        place_tv.setText(SingletonClient.getInstance().getDetails().getPlace());
        slot_code.setText(SingletonClient.getInstance().getDetails().getSlot_code());
        order_id.setText(SingletonClient.getInstance().getDetails().getId());
        time.setText(changeTimeFormat(SingletonClient.getInstance().getDetails().getFinal_time()));
        date.setText(SingletonClient.getInstance().getDetails().getTime());
        amt_tv_d.setText(price);
        gst_tv.setText(price_gst);
        total_tv.setText(total_price);


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
                .actionLabel("Pay")
                .setup(this);
        //cardForm.validate();

        Button pay_btn = findViewById(R.id.pay_btn);
        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    startActivity(new Intent(PaymentActivity.this, FinalActivity.class));

            }
        });



    }

    private String changeTimeFormat(double millis){

        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours((long)millis),
                TimeUnit.MILLISECONDS.toMinutes((long)millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours((long)millis)),
                TimeUnit.MILLISECONDS.toSeconds((long)millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)millis)));

        return hms;
    }
}
