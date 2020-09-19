
package com.example.helen;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



import java.util.Objects;

public class Attendance extends AppCompatActivity {

    private TextView s11;
    private TextView s22;
    private TextView s33;
    private TextView s44;
    private TextView nam1;
    private TextView usn1;
    DatabaseReference userRef,userRef1,rootRef;
    private final String TAG = this.getClass().getName().toUpperCase();
    FirebaseAuth firebaseAuth;
    String uid;
    private static final String Attend="Attendance";
    private static final String PROFILE="profile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        Intent intent=getIntent();
        uid=intent.getStringExtra(MainActivity.UID2);

        rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child(Attend);
        userRef1 = rootRef.child(PROFILE);

        s11=findViewById(R.id.sub1);
        s22=findViewById(R.id.sub2);
        s33=findViewById(R.id.sub3);
        s44=findViewById(R.id.sub4);
        nam1=findViewById(R.id.name1_text);
        usn1=findViewById(R.id.usn_text);
        userRef.addValueEventListener(new ValueEventListener() {
            String su1,su2,su3,su4,nam;
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot keyId: dataSnapshot.getChildren()) {
                    if (Objects.equals(keyId.getKey(), uid)) {
                        su1 = keyId.child("CS8T01").getValue(String.class);
                        su2 = keyId.child("CS8T02").getValue(String.class);
                        su3 = keyId.child("CS8PE311").getValue(String.class);
                        su4 = keyId.child("CS8PE422").getValue(String.class);

                        break;
                    }
                }
                s11.setText(su1);
                s22.setText(su2);
                s33.setText(su3);
                s44.setText(su4);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
        userRef1.addValueEventListener(new ValueEventListener() {
            String nam,usn2;

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot keyId: dataSnapshot.getChildren()) {
                    if (Objects.equals(keyId.getKey(), uid)) {
                        nam = keyId.child("name").getValue(String.class);
                        usn2 = keyId.child("usn").getValue(String.class);
                        break;
                    }
                }

                nam1.setText(nam);
                usn1.setText(usn2);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }
}