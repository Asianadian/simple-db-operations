import java.sql.*;

public class BasicCRUD {
    Connection conn;

    public BasicCRUD() {
        initConn();
    }

    private void initConn() {
        // JDBC & Database credentials
        String url = "jdbc:postgresql://localhost:5432/Test";

        String user = "postgres";
        String password = "postgres";

        try { // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");
            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connected to PostgreSQL successfully!");
            } else {
                System.out.println("Failed to establish connection.");
            } // Close the connection (in a real scenario, do this in a finally
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConn() throws SQLException {
        conn.close();
    }

    public void getAllStudents() throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM students");

        while (resultSet.next()) {
            System.out.println(rowToString(resultSet));
        }
    }

    private String rowToString(ResultSet resultSet) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int numColums = resultSetMetaData.getColumnCount();

        String row = "";

        //Make a string from tuple
        for (int i = 1; i <= numColums; i++) {
            row += resultSet.getString(i) + (i != numColums ? "," : "");
        }

        return String.format("(%s)", row);
    }

    public void addStudent(String first_name, String last_name, String email, String enrollment_date) throws SQLException {
        Statement statement = conn.createStatement();
        //Insert student into db
        statement.executeUpdate(
                String.format("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES ('%s', '%s', '%s', '%s');", first_name, last_name, email, enrollment_date));
    }

    public void updateStudentEmail(int student_id, String new_email) throws SQLException {
        Statement statement = conn.createStatement();
        //Update student with given student_id's email
        statement.executeUpdate(
                String.format("UPDATE students SET email = '%s' WHERE student_id = %d", new_email, student_id));
    }

    public void deleteStudent(int student_id) throws SQLException {
        Statement statement = conn.createStatement();
        //Delete student with given student_id
        statement.executeUpdate(String.format("DELETE FROM students WHERE student_id = %d", student_id));
    }
}
