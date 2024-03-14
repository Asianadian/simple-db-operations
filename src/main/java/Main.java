public class Main {

    public static void main(String[] args) {
       BasicCRUD basicCRUD = new BasicCRUD();

       try {
           basicCRUD.getAllStudents();
           basicCRUD.addStudent("Daniel", "Chen", "da@gmail.com", "2023-08-01");
           basicCRUD.updateStudentEmail(4, "danielchen@gmail.com");
           basicCRUD.deleteStudent(4);

           basicCRUD.closeConn();
       }
       catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
       }
    }
}