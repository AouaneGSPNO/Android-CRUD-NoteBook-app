package ma.mobile.etudiant;

public class Etudiant {


    String noteID;
    String name;
    String text;

    @Override
    public String toString() {
        return "Etudiant{" +
                "noteID='" + noteID + '\'' +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getNoteID() {
        return noteID;
    }

    public void setNoteID(String noteID) {
        this.noteID = noteID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Etudiant(String noteID, String name, String text) {
        this.noteID = noteID;
        this.name = name;
        this.text = text;
    }

    public Etudiant() {
    }
}
