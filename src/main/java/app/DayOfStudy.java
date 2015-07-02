package app;

import java.util.Collection;

/**
 * Created by Сыч on 27.11.2014.
 */
public class DayOfStudy {
    public String getDay() {
        return day;
    }

    public Collection<Lection> getLec() {
        return lec;
    }
    String day;
    Collection<Lection> lec;
    DayOfStudy (String d, Collection<Lection> l){
        day = d;
        lec = l;
    }
}
