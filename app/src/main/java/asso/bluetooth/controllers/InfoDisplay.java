package asso.bluetooth.controllers;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import asso.bluetooth.R;
import android.view.View;

public class InfoDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_display);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String nome = intent.getStringExtra("nome");
        String mac = intent.getStringExtra("mac");
        String power = intent.getStringExtra("power");

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.nome);
        textView.setText(nome);

        textView = findViewById(R.id.mac);
        textView.setText(mac);

        textView = findViewById(R.id.power);
        textView.setText(power);
    }

    public void returnReply(View view) {

        Intent replyIntent = new Intent();
        replyIntent.putExtra("Reply_OK", "Reply_OK");
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}
