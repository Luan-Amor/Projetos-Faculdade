package br.com.home.projetofaculdade;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#05070D")));
    }

    public void openCalc(View view){
        Intent intent = new Intent(this, calculadora.class);
        startActivity(intent);
    }

    public void openAgenda(View view) {
        Intent intent = new Intent(this, agenda.class);
        startActivity(intent);
    }

    public void openBrowser(View view){
        Intent intent = new Intent(this, Navegador.class);
        startActivity(intent);
    }

    public void openLocalizacao(View view){
        Intent intent = new Intent(this, Localizacao.class);
        startActivity(intent);
    }


}
