package app;

public class Lection {
    public String getTime() {
        return time;
    }

    public String getSubject() {
        return subject.getSubjName();
    }

    public String getLector() {
        return lector.getName();
    }

    String time;
    Subject subject;
    Lector lector;
    Lection (String t, int idSubj, String subj,int idLec, String lec){
        time = t;
        subject = new Subject(idSubj, subj);
        lector = new Lector(idLec, lec);
    }
}
