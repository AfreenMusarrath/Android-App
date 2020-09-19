
package com.example.helen;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



import java.util.Objects;

public class Res2 extends AppCompatActivity {
    private TextView st1,st2,st3,st4;
    private TextView nam1;
    private TextView usn1;
    DatabaseReference userRef,userRef1,rootRef;
    private final String TAG = this.getClass().getName().toUpperCase();
    String getitem;
    String uid1;
    private static final String Per="Performance";
    private static final String PROFILE="profile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res2);
        getitem=getIntent().getStringExtra("spinitem");
        Intent intent=getIntent();
        uid1=intent.getStringExtra("UID3");

        rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child(Per).child(getitem);
        userRef1 = rootRef.child(PROFILE);
        st1=findViewById(R.id.s1);
        st2=findViewById(R.id.s2);
        st3=findViewById(R.id.s3);
        st4=findViewById(R.id.s4);
        nam1=findViewById(R.id.name1);
        usn1=findViewById(R.id.usn1);
        userRef.addValueEventListener(new ValueEventListener() {
            String se1,se2,se3,se4;
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot keyId: dataSnapshot.getChildren()) {
                    if (Objects.equals(keyId.getKey(), uid1)) {
                        se1 = keyId.child("CS8T01").getValue(String.class);
                        se2 = keyId.child("CS8T02").getValue(String.class);
                        se3 = keyId.child("CS8PE311").getValue(String.class);
                        se4 = keyId.child("CS8PE422").getValue(String.class);
                        break;
                    }
                }
                st1.setText(se1);
                st2.setText(se2);
                st3.setText(se3);
                st4.setText(se4);
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
                    if (Objects.equals(keyId.getKey(), uid1)) {
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