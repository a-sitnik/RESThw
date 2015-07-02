package app;

import java.sql.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

public class DBManager {
    private static Connection bd;
    private static Statement st;

    public static SimpleDateFormat fDay = new SimpleDateFormat("EEEE, dd MMMM ");
    public static SimpleDateFormat fHour = new SimpleDateFormat("k:mm");
    public static SimpleDateFormat fParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DBManager() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        bd = DriverManager.getConnection("jdbc:sqlite:Products.db");
        st = bd.createStatement();

        st.execute("create table if not exists `Lectors` (\n" +
                "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`NameF_L`\tTEXT UNIQUE\n" +
                ");");
        st.execute("create table if not exists `Subjects` (\n" +
                "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`SubjName`\tTEXT UNIQUE\n" +
                ");");
        st.execute("create table if not exists `Lec2Subj` (\n" +
                "\t`ID`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`lec_id`\tINTEGER,\n" +
                "\t`sub_id`\tINTEGER\n" +
                ");");
        st.execute("create table if not exists `TimeTable` (\n" +
                "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`idLecSubj`\tINTEGER,\n" +
                "\t`DateT`\tDATETIME UNIQUE\n" +
                ");");
    }

    //// MAIN FUNCTION OF THE APP
    public Collection<DayOfStudy> getSchedule() throws SQLException, ParseException {
        java.util.Date LastUniqueDay = new Date(0);
        int iter = 0;
        ArrayList<DayOfStudy> schl = new ArrayList<DayOfStudy>();
        ArrayList<Lection> lecArr = new ArrayList<Lection>();

        // next time to make descr of my query
        ResultSet rs = st.executeQuery("SELECT DateT, Subjects.id, SubjName, Lectors.id, NameF_L FROM TimeTable," +
                "Lec2Subj, Lectors, Subjects WHERE Lec2Subj.ID = TimeTable.idLecSubj" +
                " and Lec2Subj.lec_id = Lectors.ID and Lec2Subj.sub_id = Subjects.ID");


        //while row
        while (rs.next()) {
            //if it is the first day
            java.util.Date tempdate = fParser.parse(rs.getString("DateT"));
            if (iter == 0) {
                LastUniqueDay = tempdate;
            }
            // if NEW day - constructing OLD day
            if (!fDay.format(tempdate).equals(fDay.format(LastUniqueDay))) {
                schl.add(new DayOfStudy(fDay.format(LastUniqueDay), lecArr));
                lecArr = new ArrayList<Lection>();
                LastUniqueDay = tempdate;
            }
            // if new row is the same day, preparing an array
            lecArr.add(new Lection(fHour.format(tempdate.getTime()), rs.getInt(3), rs.getString("SubjName"), rs.getInt(5), rs.getString("NameF_L")));
            iter++;
        }
        //last day, like finally
        schl.add(new DayOfStudy(fDay.format(LastUniqueDay), lecArr));
        return schl;
    }

    public Collection<Lector> getLector() throws SQLException {
        ResultSet rs = st.executeQuery("SELECT id, NameF_L FROM Lectors");
        ArrayList<Lector> result = new ArrayList<Lector>();
        while (rs.next()) {
            result.add(new Lector(rs.getInt(1), rs.getString(2)));
        }
        return result;
    }

    public Collection<Subject> getSubjects() throws SQLException {
        ResultSet rs = st.executeQuery("SELECT id, SubjName FROM Subjects");
        ArrayList<Subject> result = new ArrayList<Subject>();
        while (rs.next()) {
            result.add(new Subject(rs.getInt(1), rs.getString(2)));
        }
        return result;
    }
}