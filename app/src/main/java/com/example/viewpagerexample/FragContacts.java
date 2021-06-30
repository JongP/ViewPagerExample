package com.example.viewpagerexample;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpagerexample.adapters.ContactsAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragContacts extends Fragment {
    private View view;
    private RecyclerView recyclerView;



    //상태 저장하기
    public static FragContacts newInstance() {
        FragContacts fragContacts = new FragContacts();
        return fragContacts;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_contacts, container, false);

        recyclerView = view.findViewById(R.id.rv_contacts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);






        ContactsAdapter adapter = new ContactsAdapter(getContext(),getContacts());

        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<ContactItem> getContacts(){

        List<ContactItem> list = new ArrayList<>();

        Cursor cursor = getContext().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,
                null, null, null);

        cursor.moveToFirst();

        //if we have no contacts there's error
       do{
            //Log.d("FragContact","read start");



           String id =cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
            //Log.d("FragContact","read name");
            String name =cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY));
            //Log.d("FragContact","read number");
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER));

           if (cursor.getInt(cursor.getColumnIndex(
                   ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
               Cursor pCur = getContext().getContentResolver().query(
                       ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                       null,
                       ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                       new String[]{id}, null);
               while (pCur.moveToNext()) {
                   number = pCur.getString(pCur.getColumnIndex(
                           ContactsContract.CommonDataKinds.Phone.NUMBER));
                   Log.i("GOOD", "Name: " + name);
                   Log.i("GOOD", "Phone Number: " + number);
               }
               pCur.close();
           }


            list.add(new ContactItem(name, number,""));

        } while(cursor.moveToNext());
        cursor.close();


        return list;
    }

}
