package com.example.helen;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Objects;


public class syllabus extends AppCompatActivity{
    ListView lis;
    private TextView named, usntxt;
    String uid;
    private final String TAG = this.getClass().getName().toUpperCase();
    DatabaseReference userRef,rootRef;
    private static final String PROFILE="profile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
        Intent intent=getIntent();
        uid=intent.getStringExtra(MainActivity.UID2);
        lis=(ListView)findViewById(R.id.listview);
        rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child(PROFILE);
        named = findViewById(R.id.name2_text);
        usntxt = findViewById(R.id.usn1_text);
        String[] pdffiles={"Advanced Computer Architecture","Big Data Analytics","Mobile Computing","Advanced DBMS"};
        ArrayAdapter<String> a=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,pdffiles) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView myText = (TextView) view.findViewById(android.R.id.text1);
            return view;
            }
        };
        lis.setAdapter(a);
        lis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item=lis.getItemAtPosition(position).toString();
                Intent s=new Intent(getApplicationContext(),PdfOpener.class);
                s.putExtra("pdffilename",item);
                startActivity(s);
            }
        });

        userRef.addValueEventListener(new ValueEventListener() {
            String nam,usn1;

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot keyId: dataSnapshot.getChildren()) {
                    if (Objects.equals(keyId.getKey(), uid)) {
                        nam = keyId.child("name").getValue(String.class);
                        usn1 = keyId.child("usn").getValue(String.class);

                        break;
                    }
                }

                named.setText(nam);
                usntxt.setText(usn1);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }
}





