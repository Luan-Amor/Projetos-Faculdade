package br.com.home.projetofaculdade;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import static android.Manifest.permission.CALL_PHONE;

public class EditContact extends AppCompatActivity {

   private int idContato;
    private EditText nome, sobrenome, telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#05070D")));

        Intent intent = getIntent();
        int paramId = (int) intent.getSerializableExtra("id");
        String paramNome = (String) intent.getSerializableExtra("nome");
        String paramSobrenome = (String) intent.getSerializableExtra("sobrenome");
        String paramTelefone = (String) intent.getSerializableExtra("telefone");

        idContato = paramId;

        nome = (EditText) findViewById(R.id.editTextNome);
        sobrenome = (EditText) findViewById(R.id.editTextSobrenome);
        telefone = (EditText) findViewById(R.id.editTextTelefone);

        nome.setText(paramNome);
        sobrenome.setText(paramSobrenome);
        telefone.setText(paramTelefone);

    }

    public void saveEditContact(View view){
        BancoController db = new BancoController(getBaseContext());
        Contatos contato = new Contatos();


        nome = (EditText) findViewById(R.id.editTextNome);
        sobrenome = (EditText) findViewById(R.id.editTextSobrenome);
        telefone = (EditText) findViewById(R.id.editTextTelefone);

        contato.setId(idContato);
        contato.setNome(nome.getText().toString());
        contato.setSobrenome(sobrenome.getText().toString());
        contato.setTelefone(telefone.getText().toString());

        db.updateContact(contato);

        Toast.makeText(this, "Usuário atualizado.", Toast.LENGTH_SHORT);

        Intent intent = new Intent(this, agenda.class);
        startActivity(intent);
    }

    public void deleteContact(View view){
        BancoController db = new BancoController(getBaseContext());

        db.deleteContact(idContato);

        Toast.makeText(this, "Usuário excluído com sucesso.", Toast.LENGTH_SHORT);

        Intent intent = new Intent(this, agenda.class);
        startActivity(intent);
    }

    public void callContact(View view){
        telefone = (EditText) findViewById(R.id.editTextTelefone);

        Uri url= Uri.parse("tel:" + telefone.getText().toString());
        Intent it = new Intent(Intent.ACTION_CALL,url);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(it);
        } else {
            requestPermissions(new String[]{CALL_PHONE}, 1);
        }
    }
}
