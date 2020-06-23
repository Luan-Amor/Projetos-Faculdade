package br.com.home.projetofaculdade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BancoController {

    private SQLiteDatabase db;
    private CriarBanco banco;

    public BancoController(Context context){
        banco = new CriarBanco(context);
    }

    public String insertData(Contatos contatos){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriarBanco.COLUMN_NAME_NAME, contatos.getNome());
        valores.put(CriarBanco.COLUMN_NAME_LASTNAME, contatos.getSobrenome());
        valores.put(CriarBanco.COLUMN_NAME_PHONE, contatos.getTelefone());

        resultado = db.insert(CriarBanco.TABLE_NAME, null, valores);
        db.close();

        if(resultado == -1){
            return "Erro ao inserir registro";
        }else
            return "Registro salvo com sucesso";
    }

    public void deleteContact(int id){

        String where = CriarBanco.COLUMN_NAME_ID +" = "+ id;

        db = banco.getReadableDatabase();
        db.delete(CriarBanco.TABLE_NAME, where, null);
        db.close();
    }

    public Contatos selectContact(int id){
        db = banco.getReadableDatabase();

        Cursor cursor = db.query(CriarBanco.TABLE_NAME, new String[]{
                CriarBanco.COLUMN_NAME_ID,
                CriarBanco.COLUMN_NAME_NAME,
                CriarBanco.COLUMN_NAME_LASTNAME,
                CriarBanco.COLUMN_NAME_PHONE
        }, CriarBanco.COLUMN_NAME_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Contatos contato = new Contatos(Integer.parseInt(cursor.getString(0)), //ID
                cursor.getString(1), //Nome
                cursor.getString(2), //Sobrenome
                cursor.getString(3)); //Telefone

        return contato;
    }

    public void updateContact(Contatos contato){
        db = banco.getWritableDatabase();

        ContentValues valores;

        valores = new ContentValues();
        valores.put(CriarBanco.COLUMN_NAME_NAME, contato.getNome());
        valores.put(CriarBanco.COLUMN_NAME_LASTNAME, contato.getSobrenome());
        valores.put(CriarBanco.COLUMN_NAME_PHONE, contato.getTelefone());

        db.update(CriarBanco.TABLE_NAME, valores, CriarBanco.COLUMN_NAME_ID + " = ?",
                new String[]{String.valueOf(contato.getId())});
    }

    public List<Contatos> getAllContact(){
        List<Contatos> listaContatos = new ArrayList<Contatos>();
        Cursor c;
        String[] campos = {CriarBanco.COLUMN_NAME_ID, CriarBanco.COLUMN_NAME_NAME, CriarBanco.COLUMN_NAME_LASTNAME, CriarBanco.COLUMN_NAME_PHONE};
        db = banco.getReadableDatabase();

        c = db.query(CriarBanco.TABLE_NAME, campos,
                null, null, null, null, null, null);

        while(c.moveToNext()){
                Contatos contato = new Contatos();

                contato.setId(c.getInt(0));
                contato.setNome(c.getString(1));
                contato.setSobrenome(c.getString(2));
                contato.setTelefone(c.getString(3));

                listaContatos.add(contato);
        }

        db.close();
        return listaContatos;
    }



}
