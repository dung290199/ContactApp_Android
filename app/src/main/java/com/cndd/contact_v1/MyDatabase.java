package com.cndd.contact_v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "Contact_Manager";


    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "CREATE TABLE Contact(id INTEGER primary key,"
                                                + "Name TEXT,"
                                                + "Phone TEXT)";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", contact.getName());
        values.put("phone", contact.getPhone());
        db.insert("Contact", null, values);
        db.close();
    }

    public ArrayList<Contact> getAllContact(){
        ArrayList<Contact> contacts = new ArrayList<>();
        String script = "Select * from contact";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(script, null);
        while (cursor.moveToNext()){
            Contact contact = new Contact();
            contact.setId(cursor.getInt(0));
            contact.setName(cursor.getString(1));
            contact.setPhone(cursor.getString(2));
            contacts.add(contact);
        }
        return contacts;
    }

    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Contact", "id=?", new String[]{String.valueOf(contact.getId())});
    }

    public void update(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Name",contact.getName());
        values.put("Phone", contact.getPhone());

        db.update("Contact",values,"id=?",new String[] { String.valueOf(contact.getId())});
    }

    public List<Contact> search(String keyword) {
        List<Contact> contacts = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + "Contact" + " where " + "Name" + " like ?", new String[] { "%" + keyword + "%" });
            if (cursor.moveToFirst()) {
                contacts = new ArrayList<Contact>();
                do {
                    Contact contact = new Contact();
                    contact.setId(cursor.getInt(0));
                    contact.setName(cursor.getString(1));
                    contact.setPhone(cursor.getString(2));
                    contacts.add(contact);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            contacts = null;
        }
        return contacts;
    }
}
