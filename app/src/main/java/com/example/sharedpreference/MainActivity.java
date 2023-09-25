package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username, password;
    private Button saveButton, showButton;
    private TextView results;

    private int counter = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.usernameId);
        password = findViewById(R.id.passwordId);
        saveButton = findViewById(R.id.saveId);
        showButton = findViewById(R.id.showId);

        results = findViewById(R.id.resultsId);

        saveButton.setOnClickListener(this);
        showButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        // Write data
        if (view.getId()==R.id.saveId){
            String usernameString = username.getText().toString();
            String passwordString = password.getText().toString();

            if (usernameString.equals("") && passwordString.equals("")){
                Toast.makeText(this, "Please enter number.", Toast.LENGTH_SHORT).show();
            }else {
                SharedPreferences sharedPreferences = getSharedPreferences("detailsKey", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userKey", usernameString);
                editor.putString("passKey", passwordString);
                editor.commit();
                username.setText("");
                password.setText("");
                Toast.makeText(this, "Saved Successful", Toast.LENGTH_SHORT).show();
            }
        }
        // Red Data
        else if (view.getId()==R.id.showId){
            SharedPreferences sharedPreferences = getSharedPreferences("detailsKey", Context.MODE_PRIVATE);

            if (sharedPreferences.contains("userKey") && sharedPreferences.contains("passKey")){
                String user = sharedPreferences.getString("userKey", "Data Not Found");
                String pass = sharedPreferences.getString("passKey", "Data Not Found");

                results.setText(user+"\n"+pass+("\n"));
            }
            else {
                Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
            }

        }
    }
}