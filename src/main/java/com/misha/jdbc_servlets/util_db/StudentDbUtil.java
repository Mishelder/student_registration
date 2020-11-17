package com.misha.jdbc_servlets.util_db;

import com.misha.jdbc_servlets.model.Student;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDbUtil {

    private DataSource dataSource;

    private void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if(connection != null)
            connection.close();
        if(statement != null)
            statement.close();
        if(resultSet != null)
            resultSet.close();
    }

    public StudentDbUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Student loadStudent(int id) throws SQLException {
        if(dataSource == null)
            throw new NullPointerException("dataSource is null");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Student student = null;
        try{
            String sql = "select * from students where id = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                student = new Student(resultSet.getInt("id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("email"));
            }else {
                throw new IllegalArgumentException("Not found student with id "+id);
            }
            return student;
        }finally {
            close(connection,statement,resultSet);
        }
    }

    public void updateStudent(Student student) throws SQLException {
        if(dataSource == null)
            throw new NullPointerException("dataSource is null");
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            String sql = "UPDATE students SET first_name = ?,last_name = ?,email = ? WHERE id = ?";
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,student.getFirstName());
            statement.setString(2,student.getLastName());
            statement.setString(3,student.getEmail());
            statement.setInt(4,student.getId());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(connection,statement,null);
        }
    }

    public void addStudent(Student student) throws SQLException {
        if(dataSource == null)
            throw new NullPointerException("dataSource is null");
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO students (first_name,last_name,email)"
                                                    +"VALUES(?,?,?)");
            statement.setString(1,student.getFirstName());
            statement.setString(2,student.getLastName());
            statement.setString(3,student.getEmail());
            statement.execute();
        }finally {
            close(connection,statement,null);
        }
    }

    public void deleteStudent(int id) throws SQLException {
        if(dataSource == null)
            throw new NullPointerException("dataSource is null");
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("DELETE FROM students WHERE id = ?");
            statement.setInt(1,id);
            statement.execute();
        }finally {
            close(connection,statement,null);
        }
    }

    public List<Student> getStudentList() throws SQLException {
        if(dataSource == null)
            throw new NullPointerException("dataSource is null");
        List<Student> studentList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            String sql = "SELECT * FROM students ORDER BY first_name,last_name";
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                Student temp = new Student(id,firstName,lastName,email);
                studentList.add(temp);
            }
            return studentList;
        }finally {
            close(connection,statement,resultSet);
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StudentDbUtil{");
        sb.append("dataSource=").append(dataSource);
        sb.append('}');
        return sb.toString();
    }



}
