package ma.mobile.notebook;

public class Note {


    String noteID;
    String name;
    String text;
    public Note(String noteID, String name, String text) {
        this.noteID = noteID;
        this.name = name;
        this.text = text;
    }

    public String getNoteID() {
        return noteID;
    }

    public Note(String name) {
        this.name = name;
    }

    public Note() {
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
}
