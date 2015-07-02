package app;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    // http://localhost:8080/schedule
    @RequestMapping("/schedule")
    public Collection<DayOfStudy> getSchedule() throws SQLException, ClassNotFoundException, ParseException {
        DBManager db = new DBManager();
        Collection<DayOfStudy> sh = db.getSchedule();
        return sh;
        //return sh.iterator().next();
    }

    @RequestMapping("/lectors")
    public Collection<Lector> getLector() throws SQLException, ClassNotFoundException, ParseException {
        DBManager db = new DBManager();
        Collection<Lector> lc = db.getLector();
        return lc;
    }

    @RequestMapping("/subjects")
    public Collection<Subject> getSubjects() throws SQLException, ClassNotFoundException, ParseException {
        DBManager db = new DBManager();
        Collection<Subject> sb = db.getSubjects();
        return sb;
    }
}
