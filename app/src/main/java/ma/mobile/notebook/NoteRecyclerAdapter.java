package ma.mobile.notebook;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<Note> lnote;
    DatabaseReference database;

    public NoteRecyclerAdapter(Context context, ArrayList<Note> lnote) {
        this.context = context;
        this.lnote = lnote;
        database = FirebaseDatabase.getInstance().getReference();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView noteName;
        TextView noteText,date;
        Button delBut;
        Button upBut;
        public ViewHolder( View itemView) {
            super(itemView);
            this.noteName = (TextView) itemView.findViewById(R.id.tvn1);
            this.noteText = (TextView) itemView.findViewById(R.id.tvn2);
            this.date = (TextView) itemView.findViewById(R.id.date);

            this.delBut= itemView.findViewById(R.id.delButton);
            this.upBut= itemView.findViewById(R.id.updButton);


        }
    }
    @Override
    public NoteRecyclerAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listItem= layoutInflater.inflate(R.layout.note1, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( NoteRecyclerAdapter.ViewHolder holder, int position) {
     Note note = lnote.get(position);
     holder.date.setText(note.getNoteID());
     holder.noteName.setText(note.getName());
     holder.noteText.setText(note.getText());

     holder.upBut.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             ViewDialogUpdate viewDialogUpdate = new ViewDialogUpdate();
             viewDialogUpdate.showDialog(context, note.getNoteID(), note.getName(),note.getText());
         }
     });
     holder.delBut.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             ViewDialogConfirmDelete viewDialogConfirmDelete = new ViewDialogConfirmDelete();
             viewDialogConfirmDelete.showDialog(context, note.getNoteID());
         }
     });
    }

    @Override
    public int getItemCount() {
        return lnote.size();
    }
    public class ViewDialogUpdate {
        public void showDialog(Context context, String id, String name, String text) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_add_new_note);

            EditText textName = dialog.findViewById(R.id.textNamne);
            EditText textNote = dialog.findViewById(R.id.textNote);

            textName.setText(name);
            textNote.setText(text);

            Button buttonUpdate = dialog.findViewById(R.id.addNote);
            Button buttonCancel = dialog.findViewById(R.id.exitNote);

            buttonUpdate.setText("MODIFIER");

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DateFormat df = new SimpleDateFormat("d MMM yyyy, HH:mm");
                    String date = df.format(Calendar.getInstance().getTime());
                    String  newid = "Le" +date;
                    String newName = textName.getText().toString();
                    String newText_Note = textNote.getText().toString();

                    if (name.isEmpty() || text.isEmpty()) {
                        Toast.makeText(context, "Entrer les données...", Toast.LENGTH_SHORT).show();
                    } else {

                        if (newName.equals(name) && newText_Note.equals(text) ) {
                            Toast.makeText(context, "Vous n'avez rien changé !!", Toast.LENGTH_SHORT).show();
                        } else {
                            database.child("NOTE").child(id).setValue(new Note(newid, newName, newText_Note));
                            Toast.makeText(context, "Note modifiée avec succes !!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }


                    }
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }

    public class ViewDialogConfirmDelete {
        public void showDialog(Context context, String id) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.view_dialog_confirm_delete);

            Button buttonDelete = dialog.findViewById(R.id.buttonDelete);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    database.child("NOTE").child(id).removeValue();
                    Toast.makeText(context, "Note supprimée avec succes !!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }

}
