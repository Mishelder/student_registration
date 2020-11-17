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
import java.util.List;

public class StudentShowListServlet extends HttpServlet {

    @Resource(name="jdbc/web_student_tracker")
    private DataSource dataSource;
    private StudentDbUtil studentDbUtil;

    @Override
    public void init() throws ServletException {
        super.init();
        studentDbUtil = new StudentDbUtil(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student>  studentList = null;
        try {
            studentList = studentDbUtil.getStudentList();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        req.setAttribute("LIST_STUDENT",studentList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("show-list-students.jsp");
        dispatcher.forward(req,resp);
    }
}
