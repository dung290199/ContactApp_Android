package com.cndd.contact_v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter<C> extends ArrayAdapter<Contact> {
    private Context context;
    private List<Contact> arrContact;
    private int resource;

    public CustomAdapter(Context context, int resource, ArrayList<Contact> arrContact) {
        super(context, resource, arrContact);
        this.resource = resource;
        this.context = context;
        this.arrContact = arrContact;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_listview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Contact contact = arrContact.get(position);
        viewHolder.tvName.setText(contact.getName());
        return convertView;
    }

    public class ViewHolder {
        TextView tvName;
    }
}
