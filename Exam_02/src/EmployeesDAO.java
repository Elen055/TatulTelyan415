import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class EmployeesDAO {
    List<Employees> employeess = new ArrayList<>();

    public List<Employees> getEmployeess() {
        return employeess;
    }

    public void setEmployeess(List<Employees> employeess) {
        this.employeess = employeess;
    }

    public void fillEmployees() throws SQLException{
        Statement statement = ConnectionDB.connection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from employees");
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String fname = resultSet.getString("first name");
            String sname = resultSet.getString("last name");
            LocalDateTime regdate = resultSet.getObject("regdate", LocalDateTime.class);
            int gender = resultSet.getInt("gender");
            int salary = resultSet.getInt("salary");
            int numberofchildren = resultSet.getInt("numberofchildren");
            Employees employees = new Employees(id,fname,sname,regdate,gender,salary,numberofchildren);
            employeess.add(employees);
        }
    }
    public void print() throws SQLException{
        fillEmployees();
        for (int i = 0; i < employeess.size(); i++) {
            System.out.println(employeess.get(i));
        }
    }
    public String MyPrint() throws SQLException{
        String s = "";
        for (int i = 0; i < employeess.size(); i++) {
            s+=employeess.get(i);
            s+= "\n";
        }
        return s;
    }



    public String searchAdvanced(String fname, String lname, String childrenStr, String idStr,
                                 String minRegdateStr,String maxRegdateStr, String salaryMinStr, String salaryMaxStr, String genderStr) throws SQLException {

        StringBuilder query = new StringBuilder("SELECT * FROM employees WHERE 1=1");

        if (fname != null && !fname.trim().isEmpty()) {
            query.append(" and `first name` LIKE ?");
        }
        if (lname != null && !lname.trim().isEmpty()) {
            query.append(" and `last name` LIKE ?");
        }
        if (childrenStr != null && !childrenStr.trim().isEmpty()) {
            query.append(" and numberofchildren = ?");
        }
        if (idStr != null && !idStr.trim().isEmpty()) {
            query.append(" and id = ?");
        }
        if (minRegdateStr != null && !minRegdateStr.trim().isEmpty()) {
            query.append(" and RegDate >= ?");
        }
        if(maxRegdateStr != null && !maxRegdateStr.trim().isEmpty()){
            query.append(" and RegDate <= ?");
        }
        if (salaryMinStr != null && !salaryMinStr.trim().isEmpty()) {
            query.append(" and salary >= ?"); // Больше или равно минимальной
        }
        if (salaryMaxStr != null && !salaryMaxStr.trim().isEmpty()) {
            query.append(" and salary <= ?"); // Меньше или равно максимальной
        }
        if( genderStr != null && !genderStr.trim().isEmpty()){
            query.append(" and gender = ?");
        }

        String resultString = "";
        System.out.println("Сгенерированный SQL: " + query.toString());



        try (Connection conn = ConnectionDB.connection();
             PreparedStatement pstmt = conn.prepareStatement(query.toString())) {;


            int index = 1;

            // Динамически заполняем знак вопроса (?) только для заполненных полей
            if (fname != null && !fname.trim().isEmpty()) {
                pstmt.setString(index++, "%" + fname.trim() + "%");
            }
            if (lname != null && !lname.trim().isEmpty()) {
                pstmt.setString(index++, "%" + lname.trim() + "%");
            }
            if (childrenStr != null && !childrenStr.trim().isEmpty()) {
                pstmt.setInt(index++, Integer.parseInt(childrenStr.trim()));
            }
            if (idStr != null && !idStr.trim().isEmpty()) {
                pstmt.setInt(index++, Integer.parseInt(idStr.trim()));
            }
            if (minRegdateStr != null && !minRegdateStr.trim().isEmpty()) {
                pstmt.setString(index++, minRegdateStr.trim()); // Без процентов!
            }
            if (maxRegdateStr != null && !maxRegdateStr.trim().isEmpty()) {
                pstmt.setString(index++, maxRegdateStr.trim()); // Без процентов!
            }
            if (salaryMinStr != null && !salaryMinStr.trim().isEmpty()) {
                pstmt.setDouble(index++, Double.parseDouble(salaryMinStr.trim()));
            }
            if (salaryMaxStr != null && !salaryMaxStr.trim().isEmpty()) {
                pstmt.setDouble(index++, Double.parseDouble(salaryMaxStr.trim()));
            }
            if(genderStr != null && !genderStr.trim().isEmpty()){
                pstmt.setDouble(index++, Double.parseDouble((genderStr.trim())));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Считываем нужные поля (подставь свои названия колонок, если они отличаются)
                    String name = rs.getString("first name");
                    String lastname = rs.getString("last name");
                    int id = rs.getInt("id");
                    int gender = rs.getInt("gender");
                    String date = rs.getString("RegDate");
                    int children = rs.getInt("numberofchildren");
                    double salary = rs.getDouble("salary");

                    resultString += "ID:" + id + " | Имя: " + name + " | Фамилия: " + lastname +
                            " | Gender: "+ gender +" | Детей: " + children + " | Зарплата: " + salary + " | RegDate : " + date + "\n";
                }
            }
        }

        if (resultString.isEmpty()) {
            return "Ничего не найдено по заданным критериям.";
        }

        return resultString;


    }
    public boolean deleteEmployee(int id) {
        String query = "DELETE FROM employees WHERE id = ?";

        try (Connection conn = ConnectionDB.connection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // Вернет true, если запись реально нашлась и удалилась

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addEmployee(String fname, String lname, int children, double salary, int gender, String regDate) {
        String query = "INSERT INTO employees (`first name`, `last name`, numberofchildren, salary, gender, RegDate) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionDB.connection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setInt(3, children);
            pstmt.setDouble(4, salary);
            pstmt.setInt(5, gender);
            pstmt.setString(6, regDate);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}































