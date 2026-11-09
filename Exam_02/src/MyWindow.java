import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static java.lang.String.valueOf;

public class MyWindow extends JFrame implements ActionListener {
    EmployeesDAO employeesDAO = new EmployeesDAO();

    // Создаем поля для ввода
    private JTextArea textArea;
    private JTextField tfFname;
    private JTextField tfLname;
    private JTextField tfChildren;
    private JTextField tfId;
    private JTextField tfminRegdate;
    private JTextField tfmaxRegdate;
    private JTextField tfSalaryMin;
    private JTextField tfSalaryMax;
    private JTextField tfGender;
    private JButton advancedSearchBtn;
    private JButton addBtn = new JButton("Добавить");
    JTextField tfDeleteId = new JTextField(3);
    JButton deleteBtn = new JButton("Удалить по ID");// Заработок





    public MyWindow() throws SQLException {

        employeesDAO.fillEmployees();
        setTitle("Поиск сотрудников");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));


        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 10));


        filterPanel.add(new JLabel("Имя:"));
        tfFname = new JTextField(7);
        filterPanel.add(tfFname);

        filterPanel.add(new JLabel("Фамилия:"));
        tfLname = new JTextField(7);
        filterPanel.add(tfLname);

        filterPanel.add(new JLabel("Дети:"));
        tfChildren = new JTextField(3);
        filterPanel.add(tfChildren);

        filterPanel.add(new JLabel("ID:"));
        tfId = new JTextField(3);
        filterPanel.add(tfId);

        filterPanel.add(new JLabel("Дата от:"));
        tfminRegdate = new JTextField(8);
        filterPanel.add(tfminRegdate);

        filterPanel.add(new JLabel("до:"));
        tfmaxRegdate = new JTextField(8);
        filterPanel.add(tfmaxRegdate);

        filterPanel.add(new JLabel("Зарплата от:"));
        tfSalaryMin = new JTextField(6);
        filterPanel.add(tfSalaryMin);

        filterPanel.add(new JLabel("до:"));
        tfSalaryMax = new JTextField(6);
        filterPanel.add(tfSalaryMax);

        filterPanel.add(new JLabel("Пол (1/2):"));
        tfGender = new JTextField(2);
        filterPanel.add(tfGender);




        advancedSearchBtn = new JButton("Найти по фильтрам");
        advancedSearchBtn.addActionListener(this);
        filterPanel.add(advancedSearchBtn);

        deleteBtn = new JButton("Удалить по ID");
        deleteBtn.addActionListener(this);
        filterPanel.add(tfDeleteId);
        filterPanel.add(deleteBtn);

        addBtn = new JButton("Добавить");
        addBtn.addActionListener(this);
        filterPanel.add(addBtn);


        add(filterPanel, BorderLayout.NORTH);


        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));


        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);


        add(textArea, BorderLayout.CENTER);



        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {



        if (e.getSource() == advancedSearchBtn) {
            try {

                String fname = tfFname.getText().trim();
                String lname = tfLname.getText().trim();
                String children = tfChildren.getText().trim();
                String id = tfId.getText().trim();
                String minRegDate = tfminRegdate.getText().trim();
                String maxRegDate = tfmaxRegdate.getText().trim();
                String salaryMin = tfSalaryMin.getText().trim();
                String salaryMax = tfSalaryMax.getText().trim();
                String gender = tfGender.getText().trim();


                String result = employeesDAO.searchAdvanced(fname, lname, children, id, minRegDate, maxRegDate, salaryMin, salaryMax, gender);


                if (result != null && !result.isEmpty()) {
                    textArea.setText(result);
                } else {
                    textArea.setText("По вашему запросу ничего не найдено.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                textArea.setText("Ошибка при поиске: " + ex.getMessage());
            }
        }
        if (e.getSource() == deleteBtn) {
            try {

                String idText = tfDeleteId.getText().trim();

                if (idText.isEmpty()) {
                    textArea.setText("Ошибка: введите ID сотрудника для удаления!");
                    return;
                }

                int empId = Integer.parseInt(idText);


                boolean success = employeesDAO.deleteEmployee(empId);

                if (success) {
                    textArea.setText("Сотрудник с ID " + empId + " успешно удален из базы данных!");
                    tfDeleteId.setText("");
                } else {
                    textArea.setText("Сотрудник с ID " + empId + " не найден в базе данных.");
                }

            } catch (NumberFormatException ex) {
                textArea.setText("Ошибка: ID должен быть целым числом!");
            } catch (Exception ex) {
                ex.printStackTrace();
                textArea.setText("Ошибка при удалении: " + ex.getMessage());
            }
        }
            if (e.getSource() == addBtn) {
                try {

                    String fname = tfFname.getText().trim();
                    String lname = tfLname.getText().trim();

                    if (fname.isEmpty() || lname.isEmpty()) {
                        textArea.setText("Ошибка: для добавления заполните хотя бы Имя и Фамилию в полях выше!");
                        return;
                    }


                    int children = tfChildren.getText().trim().isEmpty() ? 0 : Integer.parseInt(tfChildren.getText().trim());
                    double salary = tfSalaryMin.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(tfSalaryMin.getText().trim());
                    int gender = tfGender.getText().trim().isEmpty() ? 1 : Integer.parseInt(tfGender.getText().trim());


                    String regDate = tfminRegdate.getText().trim();
                    if (regDate.isEmpty()) {
                        regDate = "2026-11-07 00:00:00";
                    }


                    boolean success = employeesDAO.addEmployee(fname, lname, children, salary, gender, regDate);

                    if (success) {
                        textArea.setText("Сотрудник " + fname + " " + lname + " успешно добавлен в базу данных!");


                        tfFname.setText("");
                        tfLname.setText("");
                        tfChildren.setText("");
                        tfSalaryMin.setText("");
                        tfGender.setText("");
                        tfminRegdate.setText("");
                    } else {
                        textArea.setText("Ошибка при добавлении в базу данных.");
                    }

                } catch (NumberFormatException ex) {
                    textArea.setText("Ошибка: проверьте, что в числовые поля (дети, зарплата, пол) введены цифры!");
                }
            }
        }
    }
