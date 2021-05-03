package id.ac.binus.my_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class Login extends AppCompatActivity {
    EditText username;
    EditText password;
    Button btn_register;
    Button btn_login;
    int userke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_register = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.btn_login);



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User_Helper helper = new User_Helper(getApplicationContext());
                User user = helper.getUser(username.getText().toString(), password.getText().toString());

                if (user!=null){

                    SharedPreferences.Editor sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                    sp.putString("username", user.getUsername());
                    sp.putString("password", user.getPassword());
                    sp.apply();

                    Intent intent = new Intent(getApplicationContext(),HomePage.class);
                    Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Username and Password", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}