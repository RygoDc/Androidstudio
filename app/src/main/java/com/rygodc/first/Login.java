package com.rygodc.first;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       Button loginButton = findViewById(R.id.LoginButtonTIL);
        TextView loginRegisterText = findViewById(R.id.LoginRegisterText);
        TextInputLayout loginUserNameTIL = findViewById(R.id.loginUsernameTIL);
        TextInputLayout loginPasswordTIL = findViewById(R.id.loginPasswordTIL);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginUserName = String.valueOf(loginUserNameTIL.getEditText().getText());
                String loginPassword = String.valueOf(loginPasswordTIL.getEditText().getText());

                SharedPreferences sharedPreferences = getSharedPreferences("usuario", Context.MODE_PRIVATE);
                String name = sharedPreferences.getString("userName","anónimo");
                String password = sharedPreferences.getString("userPassword","contraseña");

                if(loginUserName.equals(name) && password.equals(loginPassword)){
                    launchMain();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        loginRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchRegister();
            }
        });

        };
    public void launchMain(){
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void launchRegister(){
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }
}