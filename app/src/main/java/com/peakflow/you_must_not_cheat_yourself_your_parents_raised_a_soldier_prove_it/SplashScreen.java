package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it;

import static kotlinx.coroutines.DelayKt.delay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //
        new Handler(Looper.getMainLooper()).postDelayed(()->{
            Intent i = new Intent(SplashScreen.this, LoginActivity.class);
            startActivity(i);
            finish();
        }, 2000);

        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String uid = prefs.getString("uid", null);

        if (uid != null) {
            // User is logged in, fetch details from Firebase
            fetchUserDetails(uid);
        } else {
            // Redirect to login screen
            startActivity(new Intent(SplashScreen.this, DashboardActivity.class).putExtra("uid", uid));

            finish();
        }

    }
    private void fetchUserDetails(String firebaseUid) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUid);

        userRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                User user = task.getResult().getValue(User.class);
                if (user != null) {
                    // ✅ Successfully fetched user details
                    Toast.makeText(this, "Welcome, " + user.name, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "User not found!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}