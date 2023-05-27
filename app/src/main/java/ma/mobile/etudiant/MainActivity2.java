package ma.mobile.etudiant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import ma.mobile.notebook.R;

public class MainActivity2 extends AppCompatActivity {
    Button butSit;
    ListView simpleList;
    String countryList[] = {"Mr ENNOUAARI :\n Ingénierie des systèmes\n Cooronateur Cloud-IoT \n  ENNOUAARI@gmail.com",
            "Mr BENSAID :\n Mathématiques discrètes\n Interne\n  BENSAID@gmail.com ",
                    "Mme ZAIDOUNI :\n Techniques de virtualisation\n Interne \n  ZAIDOUNI@gmail.com",
             " Mr IDRISSI :\n Projet personnel et professionnel (PPP)\n Interne \n IDRISSI@gmail.com ",
            "Mme RETBI :\n Techniques de validation et d’assurance qualité\n Externe \n  RETBI@gmail.com "};
    int flags[] = {R.drawable.youcef,R.drawable.ali,R.drawable.meryem, R.drawable.yassine, R.drawable.fatima,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        simpleList = (ListView) findViewById(R.id.simpleListView);
        ////////// listview
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), countryList, flags);
        simpleList.setAdapter(customAdapter);
        butSit = findViewById(R.id.sit);
        butSit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity2.this,MainActivity.class);
                MainActivity2.this.startActivity(intent1);            }
        });
    }
}