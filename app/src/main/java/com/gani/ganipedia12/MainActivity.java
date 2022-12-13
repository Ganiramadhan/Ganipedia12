package com.gani.ganipedia12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    ImageButton imageButtonGames;
    ImageButton imageButtonFilm;
    ImageButton imageButtoncode;
    TextView textViewAnswer;
    DatabaseReference MyHobby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButtonGames = findViewById(R.id.buttonGames);
        imageButtonFilm = (ImageButton)findViewById(R.id.buttonFilm);
        imageButtoncode = (ImageButton)findViewById(R.id.buttonCode);
        textViewAnswer = (TextView)findViewById(R.id.textViewAnswer);
        MyHobby = FirebaseDatabase.getInstance().getReference().child("hobby");
    }
    @Override
    protected void onStart() {
        super.onStart();
        MyHobby.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot
                                             dataSnapshot) {
                String myhobby = dataSnapshot.getValue(String.class);
                textViewAnswer.setText(myhobby);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError
                                            databaseError) {
            }
        });
        imageButtonGames.setOnClickListener(view -> MyHobby.setValue("Main Games"));
        imageButtonFilm.setOnClickListener(view -> MyHobby.setValue("Nonton Film"));
        imageButtoncode.setOnClickListener(view -> MyHobby.setValue("Ngoding Dong"));
    }
}