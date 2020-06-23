package br.com.home.projetofaculdade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CriarBanco extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "banco.db";

    public static final String TABLE_NAME = "schedule";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_LASTNAME = "lastname";
    public static final String COLUMN_NAME_PHONE = "phone";


    private static final String SQL_CREATE_SCHEDULE =
            "CREATE TABLE " + TABLE_NAME + " ( " +
                    COLUMN_NAME_ID + " integer primary key autoincrement, "+
                    COLUMN_NAME_NAME + " text, "+
                    COLUMN_NAME_LASTNAME + " text, "+
                    COLUMN_NAME_PHONE + " text "+")";

    private static final String SQL_DELETE_SCHEDULE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public CriarBanco(@Nullable Context context) {
        super(context,DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SCHEDULE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_SCHEDULE);
        onCreate(db);
    }
}
