package app;

public class Subject {
    public int id;
    public String subjName;
    Subject(int id, String sub){
        this.id = id;
        subjName = sub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjName() {
        return subjName;
    }

    public void setSubjName(String subjName) {
        this.subjName = subjName;
    }
}
