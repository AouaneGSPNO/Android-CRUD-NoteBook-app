package ma.mobile.notebook;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatabaseReference database;

    RecyclerView recyclerView;
    NoteRecyclerAdapter adapter ;
    ArrayList <Note> listNote = new ArrayList();

    Button butADD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butADD = findViewById(R.id.add);

        database = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        butADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ViewDialogAdd viewDialogAdd = new ViewDialogAdd();
               viewDialogAdd.showDialog(MainActivity.this);
            }
        });
        readData();
    }
    private void readData(){

        database.child("NOTE").orderByChild("noteName").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listNote.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                 Note note = dataSnapshot1.getValue(Note.class);
                listNote.add(note);
                }
                adapter = new NoteRecyclerAdapter(MainActivity.this,listNote);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public class ViewDialogAdd {
        public void showDialog(Context context) {
         final Dialog dialog = new Dialog(context);
         dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
         dialog.setCancelable(false);
         dialog.setContentView(R.layout.alert_add_new_note);

            EditText textName = dialog.findViewById(R.id.textNamne);
            EditText textNote = dialog.findViewById(R.id.textNote);

            Button butADD = dialog.findViewById(R.id.addNote);
            Button butEXIT = dialog.findViewById(R.id.exitNote);

            butEXIT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            butADD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DateFormat df = new SimpleDateFormat("d MMM yyyy, HH:mm");
                    String date = df.format(Calendar.getInstance().getTime());

                    String id = "Le "+ date;
                    String name = textName.getText().toString();
                    String text_note = textNote.getText().toString();

                    if(name.isEmpty() || text_note.isEmpty()){
                        Toast.makeText(MainActivity.this, " Veuillez Saisir les données ..", Toast.LENGTH_SHORT).show();
                    }else {
                        database.child("NOTE").child(id).setValue(new Note(id,name,text_note));
                        Toast.makeText(MainActivity.this, "Note Ajouteé avec succes ..", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }
}