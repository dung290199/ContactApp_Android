package com.cndd.contact_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddContactActivity extends AppCompatActivity {

    EditText edtName, edtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        edtName = (EditText) findViewById(R.id.edt_name);
        edtPhone = (EditText) findViewById(R.id.edt_phone);

    }

    public void close(View view){
        Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void done(View view){

        Contact contact = new Contact(Contact.ID, edtName.getText().toString(), null, edtPhone.getText().toString());
        MainActivity.db.addContact(contact);

        Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
