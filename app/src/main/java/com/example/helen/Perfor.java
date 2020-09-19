package com.example.helen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Perfor extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{


    private final String TAG = this.getClass().getName().toUpperCase();
    String i;
    String uid;

    private static final String PROFILE = "profile";
    DatabaseReference userRef, rootRef;
    Spinner s;
    String[] spinlist;
    private TextView name2, usnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfor);
        s=(Spinner)findViewById(R.id.myspinner);
        Intent intent=getIntent();
        uid=intent.getStringExtra(MainActivity.UID2);
       spinlist=getResources().getStringArray(R.array.perfordetails);
        rootRef = FirebaseDatabase.getInstance().getReference();
                userRef = rootRef.child(PROFILE);
                name2= findViewById(R.id.name3_text);
                usnt= findViewById(R.id.usn_text1);
                ArrayAdapter ad=new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinlist);
                ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                s.setAdapter(ad);
                s.setOnItemSelectedListener(this);



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

                        name2.setText(nam);
                        usnt.setText(usn1);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w(TAG, "Failed to read value.", databaseError.toException());
                    }
                });
            }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         i = s.getItemAtPosition(position).toString();
        Intent ss=new Intent(Perfor.this,Res2.class);
        ss.putExtra("spinitem",i);
        ss.putExtra("UID3",uid);
        startActivity(ss);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}






