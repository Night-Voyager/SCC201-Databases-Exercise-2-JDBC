package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbAnswer extends DbBasic{
    private Statement stmt;

    /**
     * Constructor
     * <p>
     * Records a copy of the database name and
     * opens the database for use
     *
     * @param _dbName String holding the name of the database,
     *                for example, C:/directory/subdir/mydb.db
     */
    public DbAnswer(String _dbName) {
        super(_dbName);
        try {
            stmt = con.createStatement();
        } catch (SQLException sqlException) {
            notify(sqlException.getMessage(), sqlException);
            close();
        }
    }

    public void doQuery_1_a() {
        String [] columnLabels = {"c_title"};
        doQuery(
                "SELECT c_title FROM courses WHERE code=361",
                columnLabels,
                "doQuery_1_a: "
        );
    }

    public void doQuery_1_b() {
        String [] columnLabels = {"pos", "qual"};
        doQuery(
                "SELECT pos, qual FROM staff WHERE s_name=\"Davies\"",
                columnLabels,
                "doQuery_1_b: "
        );
    }

    public void doQuery_2_a() {
        String [] columnLabels = {"d_title"};
        doQuery(
                "SELECT d_title FROM department INNER JOIN staff \n" +
                        "ON department.d_id=staff.d_id WHERE staff.s_name=\"Bear\"",
                columnLabels,
                "doQuery_2_a: "
        );
    }

    public void doQuery_2_b() {
        String [] columnLabels = {"code"};
        doQuery(
                "SELECT code FROM courses INNER JOIN department ON \n" +
                        "department.d_id=courses.d_id WHERE d_title=\"Computing\"",
                columnLabels,
                "doQuery_2_b: "
        );
    }

    public void doQuery_3_a() {
        doQuery_3_a("Mariani");
    }

    public void doQuery_3_a(String lecturerName) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT c_title FROM courses NATURAL JOIN \n" +
                    "give_course NATURAL JOIN staff WHERE s_name=?");
            preparedStatement.setString(1, lecturerName);
            ResultSet rs = preparedStatement.executeQuery();
//            ResultSet rs = stmt.executeQuery("SELECT c_title FROM courses NATURAL JOIN \n" +
//                    "give_course NATURAL JOIN staff WHERE s_name=" + lecturerName);
            while (rs.next()) {
                System.out.println(rs.getString("c_title"));
            }
        } catch (SQLException sqlException) {
            notify("doQuery_3_a: ", sqlException);
            close();
        }
        System.out.println();
    }

    public void doQuery_3_b( ) {
        String [] columnLabels = {"s_name", "initials"};
        doQuery(
                "SELECT s_name, initials FROM staff \n" +
                        "NATURAL JOIN work_on NATURAL JOIN projects WHERE p_title=\"COMIC\" ORDER BY s_name",
                columnLabels,
                "doQuery_3_b: "
        );
    }

    public void doQuery_4_a( ) {
        executeUpdate("INSERT INTO department(d_title, location, d_id) " +
                "VALUES (\"Sociology\", \"Cartmel college\", \"SOCIO\");",
                "doQuery_4_a: ");
    }

    public void doQuery_4_b( ) {
        executeUpdate("INSERT INTO staff VALUES (\"JH\", \"J.\", \"Hughes\", \"Professor\", \"PhD\", " +
                "(SELECT d_id FROM department WHERE d_title=\"Sociology\"));",
                "doQuery_4_b: ");
    }

    public void doQuery_4_c( ) {
        executeUpdate("INSERT INTO work_on VALUES (\"JH\", \"COMIC\", 1991, 1994);", "doQuery_4_c: ");
    }

    public void doQuery_5_a( ) {
        String [] columnLabels = {"s_name"};
        doQuery(
                "SELECT s_name FROM staff NATRUAL JOIN work_on \n" +
                        "WHERE p_id=\"COMIC\" AND start_date BETWEEN 1990 AND 1992",
                columnLabels,
                "doQuery_5_a: "
        );
    }

    public void doQuery_5_b( ) {
        String [] columnLabels = {"SUM(funding)", "AVG(funding)"};
        doQuery(
                "SELECT SUM(funding), AVG(funding) FROM projects",
                columnLabels,
                "doQuery_5_b: "
        );
    }

    void doQuery_6( ) {
        String [] columnLabels = {"COUNT(p_id)", "s_name"};
        doQuery(
                "SELECT COUNT(p_id), s_name FROM staff NATURAL JOIN work_on GROUP BY s_name",
                columnLabels,
                "doQuery_6: "
        );
    }

    void execute_7c( ) {
        executeUpdate("DELETE FROM work_on WHERE s_id=\"JH\";", "execute_7c: ");
    }

    void execute_7a( ) {
        executeUpdate("DELETE FROM department WHERE d_id=\"SOCIO\";", "execute_7a: ");
    }

    void execute_7b( ) {
        executeUpdate("DELETE FROM staff WHERE s_id=\"JH\";", "execute_7b: ");
    }

    void doQuery(String sql, String [] columnLabels, String message) {
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                StringBuffer sb = new StringBuffer();
                for (String columnLabel : columnLabels) {
                    sb.append(rs.getString(columnLabel));
                    sb.append('|');
                }
                sb.deleteCharAt(sb.length()-1);
                System.out.println(sb);
            }
        } catch (SQLException sqlException) {
            notify(message, sqlException);
            close();
        }
        System.out.println();
    }

    void executeUpdate(String sql, String message) {
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException sqlException) {
            notify(message, sqlException);
            close();
        }
        System.out.println();
    }
}
