package com.example.helen;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class profiled extends AppCompatActivity {
    private TextView named, phlox, usntxt, dobtxt, classtxt, emailtxt;
    ImageView imagev;
    private String email;
    DatabaseReference userRef,rootRef;
    private final String TAG = this.getClass().getName().toUpperCase();
    private static final String PROFILE="profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro2);
        Intent intent=getIntent();
        email=intent.getStringExtra(MainActivity.EMAIL1);

        rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child(PROFILE);
        named = findViewById(R.id.name1_textview);
        usntxt = findViewById(R.id.usn_textview);
        classtxt =findViewById(R.id.class_textview);
        dobtxt = findViewById(R.id.dob_textview);
        phlox = findViewById(R.id.phno_textview);
        emailtxt =findViewById(R.id.email_text);
        imagev=(ImageView)findViewById(R.id.user_imageview1);
        userRef.addValueEventListener(new ValueEventListener() {
            String nam,usn1,class1,dob,phn,ima;

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot keyId: dataSnapshot.getChildren()) {
                    if (Objects.equals(keyId.child("email").getValue(), email)) {
                        nam = keyId.child("name").getValue(String.class);
                        usn1 = keyId.child("usn").getValue(String.class);
                        dob = keyId.child("DOB").getValue(String.class);
                        phn = keyId.child("phone").getValue(String.class);
                        class1 = keyId.child("sem").getValue(String.class);
                        ima=keyId.child("imageuri").getValue(String.class);
                        break;
                    }
                }
                Picasso.get().load(ima).into(imagev);
                named.setText(nam);
                emailtxt.setText(email);
                usntxt.setText(usn1);
                dobtxt.setText(dob);
                phlox.setText(phn);
                classtxt.setText(class1);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }
}
