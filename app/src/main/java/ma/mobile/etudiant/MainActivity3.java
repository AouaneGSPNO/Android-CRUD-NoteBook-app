package ma.mobile.etudiant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ma.mobile.notebook.R;

public class MainActivity3 extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("1234")) {
                    Toast.makeText(MainActivity3.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity3.this,MainActivity4.class);
                    MainActivity3.this.startActivity(intent);
                } else {
                    Toast.makeText(MainActivity3.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}