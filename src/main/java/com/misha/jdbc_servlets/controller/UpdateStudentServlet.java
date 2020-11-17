package com.misha.jdbc_servlets.controller;

import com.misha.jdbc_servlets.model.Student;
import com.misha.jdbc_servlets.util_db.StudentDbUtil;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateStudentServlet extends HttpServlet {

    @Resource(name = "jdbc/web_student_tracker")
    private DataSource dataSource;
    private StudentDbUtil studentDbUtil;

    @Override
    public void init() throws ServletException {
        super.init();
        studentDbUtil = new StudentDbUtil(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        Student student = new Student(id,firstName,lastName,email);
        try {
            studentDbUtil.updateStudent(student);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/");
        dispatcher.forward(req,resp);
    }
}
