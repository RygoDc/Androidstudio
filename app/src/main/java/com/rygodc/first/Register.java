package com.rygodc.first;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextInputLayout registerUsernameTIL = findViewById(R.id.registerUsername);
        TextInputLayout registerEmailTIL = findViewById(R.id.registerEmail);

        TextInputLayout registerPasswordTIL = findViewById(R.id.registerPassword);
        TextInputLayout registerPasswordConfirmTIL = findViewById(R.id.registerConfirmPassword);


        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = String.valueOf(registerUsernameTIL.getEditText().getText());
                String userMail = String.valueOf(registerEmailTIL.getEditText().getText());

                String userPassword = String.valueOf(registerPasswordTIL.getEditText().getText());
                String userPasswordConfirm = String.valueOf(registerPasswordConfirmTIL.getEditText().getText());

                if(!userPassword.equals(userPasswordConfirm)){
                    Toast toast = Toast.makeText(getApplicationContext(), "Tus contraseñas no coinciden", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    SharedPreferences preferences = getSharedPreferences("usuario",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("userName", userName);
                    editor.putString("userPassword", userPassword);
                    editor.apply();
                }

                if(userName.equals("") || userMail.equals("") || userPassword.equals("") || userPasswordConfirm.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Tienes campos vacios", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    if (!validate(userMail)){
                        Toast toast = Toast.makeText(getApplicationContext(), "Esto no es un correo", Toast.LENGTH_SHORT);
                        toast.show();
                    }else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Usuario Registrado", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });



        };

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    public void launchMain(){
        Intent intent = new Intent(Register.this, MainActivity.class);
        startActivity(intent);
    }
}