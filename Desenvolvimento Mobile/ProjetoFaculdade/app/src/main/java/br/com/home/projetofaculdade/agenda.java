package br.com.home.projetofaculdade;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class agenda extends AppCompatActivity {

    private ListView listviewContatos;
    private BancoController db;
    private List<Contatos> contacts;
    private List<Contatos> contactFilter = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#05070D")));

        db = new BancoController(this);

        listviewContatos = (ListView) findViewById(R.id.listViewContatos);

        contacts = db.getAllContact();
        contactFilter.addAll(contacts);


        ArrayAdapter<Contatos> adapterContacts = new ArrayAdapter<Contatos>(this, android.R.layout.simple_list_item_1, contactFilter);
        listviewContatos.setAdapter(adapterContacts);

        listviewContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(agenda.this, EditContact.class);

                intent.putExtra("id", contactFilter.get(position).getId());
                intent.putExtra("nome", contactFilter.get(position).getNome());
                intent.putExtra("sobrenome", contactFilter.get(position).getSobrenome());
                intent.putExtra("telefone", contactFilter.get(position).getTelefone());

                startActivity(intent);
            }
        });


//        Log.d("ID contado", " "+c.getId() + " Nome " + c.getNome() + " Sobrenome " + c.getSobrenome() + " tel " + c.getTelefone());
    }

    public void btnAdicionar(View view){
        Intent intent = new Intent(this, InserirAgenda.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        contacts = db.getAllContact();
        contactFilter.clear();
        contactFilter.addAll(contacts);
        listviewContatos.invalidateViews();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                procuraContato(newText);
                return false;
            }
        });

        return true;
    }


    public void procuraContato(String nome){
        contactFilter.clear();
        for(Contatos c : contacts){
            if(c.getNome().toLowerCase().contains(nome.toLowerCase())){
                contactFilter.add(c);
            }
        }
        listviewContatos.invalidateViews();
    }
}


