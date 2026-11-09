import java.time.LocalDate;
import java.time.LocalDateTime;


public class Employees {
    private int id;
    private String fname;
    private String lname;
    private LocalDateTime regdate;
    private int gender;
    private int salary;
    private int numberofchildren;

    public Employees(int id, String fname, String lname, LocalDateTime regdate, int gender, int salary, int numberofchildren) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.regdate = regdate;
        this.gender = gender;
        this.salary = salary;
        this.numberofchildren = numberofchildren;
    }

    public Employees() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public LocalDateTime getRegdate() {
        return regdate;
    }

    public void setRegdate(LocalDateTime regdate) {
        this.regdate = regdate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getNumberofchildren() {
        return numberofchildren;
    }

    public void setNumberofchildren(int numberofchildren) {
        this.numberofchildren = numberofchildren;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", regdate=" + regdate +
                ", gender=" + gender +
                ", salary=" + salary +
                ", numberofchildren=" + numberofchildren +
                '}';
    }
}
