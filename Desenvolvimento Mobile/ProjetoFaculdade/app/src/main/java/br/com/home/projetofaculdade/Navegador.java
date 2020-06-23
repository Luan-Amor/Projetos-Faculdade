package br.com.home.projetofaculdade;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Navegador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegador);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#05070D")));

    }

    public void openURL(View view){

        EditText textUrl = (EditText) findViewById(R.id.urlId);
        String site = textUrl.getText().toString();
        Uri url = Uri.parse("https://".concat(site));
        Intent intent = new Intent(Intent.ACTION_VIEW, url);
        startActivity(intent);

    }
}
