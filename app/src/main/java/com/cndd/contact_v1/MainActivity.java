package com.cndd.contact_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> contacts;
    private CustomAdapter<Contact> customAdapter;
    private ListView lvContact;
    public static MyDatabase db;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName = (TextView) findViewById(R.id.tv_name);
        lvContact = (ListView) findViewById(R.id.lv_contact);

        db = new MyDatabase(this);

        //get all contacts
        contacts = db.getAllContact();

        customAdapter = new CustomAdapter(this,R.layout.row_listview,contacts);
        lvContact.setAdapter(customAdapter);

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Contact contact = (Contact) adapterView.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, EditContactActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("contact", contact);
                intent.putExtra("package", bundle);

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchContact(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchContact(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void searchContact(String keyword) {
        List<Contact> contacts = db.search(keyword);
        if (contacts != null) {
            lvContact.setAdapter(new CustomAdapter<>(getApplicationContext(), 1, (ArrayList<Contact>) contacts));
        }
    }

    public void add(View view){
        Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
        startActivity(intent);
    }

//    public void call(View view){
//        lvContact.
//        Contact contact = contacts.get(pos);
//        String phone = contact.getPhone();
//        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(Uri.fromParts("tel", phone, null).toString()));
//        startActivity(intent);
//    }

}
