package br.com.home.projetofaculdade;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InserirAgenda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_agenda);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#05070D")));

        Button btn = (Button) findViewById(R.id.btn_addContato);

    }

    public void saveContact(View view){
        BancoController db = new BancoController(getBaseContext());
        Contatos contato = new Contatos();

        EditText txtnome = (EditText) findViewById(R.id.textoNome);
        EditText txtsobrenomenome = (EditText) findViewById(R.id.textoSobrenome);
        EditText txttelefone = (EditText) findViewById(R.id.textoPhone);

        contato.setNome(txtnome.getText().toString());
        contato.setSobrenome(txtsobrenomenome.getText().toString());
        contato.setTelefone(txttelefone.getText().toString());

        String resultado = db.insertData(contato);

        Toast.makeText(InserirAgenda.this, resultado, Toast.LENGTH_LONG).show();


        Intent intent = new Intent(this, agenda.class);
        startActivity(intent);
    }
}
