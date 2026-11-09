import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        ConnectionDB.connection();
        EmployeesDAO employeesDAO = new EmployeesDAO();

        MyWindow myWindow = new MyWindow();

    }
}