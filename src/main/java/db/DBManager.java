package db;

import entity.Discipline;
import entity.Group;
import entity.Student;
import entity.Term;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static Statement statement;

    private static final String ID = "id";
    private static final String SURNAME = "surname";
    private static final String NAME = "name";
    private static final String DATE = "date";
    private static final String GROUP_NAME = "group";

    private static final String DISCIPLINE = "discipline";
    private static final String TERM = "term";
    private static final String DURATION = "duration";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_4?user=root&password=65edefid");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Student> getAllStudents() {
        List<Student> result = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("select s.id, surname, name, date, g.group" +
                    " from student as s left join groupp as g on s.id_group = g.id" +
                    " where status = '1';");

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt(ID));
                student.setSurname(resultSet.getString(SURNAME));
                student.setName(resultSet.getString(NAME));
                student.setDate(resultSet.getDate(DATE));

                Group group = new Group();
                group.setName(resultSet.getString(GROUP_NAME));
                student.setGroup(group);

                result.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Discipline> getAllDiscipline() {
        List<Discipline> result = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("select id, discipline from discipline" +
                    " where status = '1'");
            while (resultSet.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(resultSet.getInt(ID));
                discipline.setName(resultSet.getString(DISCIPLINE));

                result.add(discipline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Term> getAllTerms () {
        List<Term> result = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("select t.id, term, duration from term as t " +
                    "where status = '1'");
            while (resultSet.next()) {
                Term term = new Term();
                term.setId(resultSet.getInt(ID));
                term.setDuration(resultSet.getString(DURATION));
                term.setName(resultSet.getString(TERM));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getGroupId(String groupName) {
        int result = 0;
        try {
            ResultSet resultSet = statement.executeQuery(String.format("select id from groupp" +
                    " where groupp.group = '%s';", groupName));
            while (resultSet.next()) {
                return resultSet.getInt(ID);
            }

            statement.execute(String.format("insert into `groupp` (`group`) values('%s');", groupName));
            resultSet = statement.executeQuery(String.format("select id from groupp" +
                    " where groupp.group = '%s';", groupName));

            while (resultSet.next()) {
                result = resultSet.getInt(ID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void createStudent(String surname, String name, int groupId, String date) {
        try {
            statement.execute(String.format("INSERT INTO `student` " +
                    "(`surname`, `name`, `id_group`, `date`) " +
                    "VALUES ('%s', '%s', '%d', '%s');", surname, name, groupId, date));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createDiscipline(String discipline) {
        try {
            statement.execute(String.format("INSERT INTO `discipline` (`discipline`) VALUES ('%s');", discipline));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createTerm(String term, String duration) {
        try {
            statement.execute(String.format("INSERT INTO `term` (`term`, `duration`) VALUES ('$s', '$s');", term, duration));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
