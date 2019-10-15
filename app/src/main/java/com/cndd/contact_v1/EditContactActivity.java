package com.cndd.contact_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditContactActivity extends AppCompatActivity {

    EditText edtName, edtPhone;
    TextView tvID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        edtName = (EditText) findViewById(R.id.edt_name);
        edtPhone = (EditText) findViewById(R.id.edt_phone);
        tvID = (TextView) findViewById(R.id.tv_id);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("package");
            Contact contact = (Contact) bundle.getSerializable("contact");
                edtName.setText(contact.getName());
                edtPhone.setText(contact.getPhone());
                tvID.setText(String.valueOf(contact.getId()));
        }
    }

    public void close(View view){
        Intent intent = new Intent(EditContactActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void done(View view){
        Contact contact = new Contact(Integer.parseInt(tvID.getText().toString()), edtName.getText().toString(), null, edtPhone.getText().toString());
        MainActivity.db.update(contact);
        Intent intent = new Intent(EditContactActivity.this, MainActivity.class);
        startActivity(intent);
    }

//    public void call(View view){
//        String phone = edtPhone.getText().toString();
//        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, findViewById(R.id.btn_call).toString()));
//        startActivity(intent);
//    }
}
