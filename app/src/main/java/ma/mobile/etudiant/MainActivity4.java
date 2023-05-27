package ma.mobile.etudiant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ma.mobile.notebook.R;

public class MainActivity4 extends AppCompatActivity {
    Button tostud;
    Button toprof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        toprof = findViewById(R.id.toprof);
        tostud = findViewById(R.id.tostud);

        toprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity4.this,MainActivity2.class);
                MainActivity4.this.startActivity(intent2);
            }
        });
        tostud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity4.this,MainActivity.class);
                MainActivity4.this.startActivity(intent3);
            }
        });
    }
}