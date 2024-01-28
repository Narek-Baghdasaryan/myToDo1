package com.example.mytodo.servlet;


import com.example.mytodo.enums.Status;
import com.example.mytodo.manager.ToDoManager;
import com.example.mytodo.manager.UserManager;
import com.example.mytodo.model.ToDo;
import com.example.mytodo.model.User;
import com.example.mytodo.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(urlPatterns = "/updateToDo")
public class UpdateServlet extends HttpServlet {
    ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ToDo toDoById = toDoManager.getToDoById(id);
        req.setAttribute("toDo", toDoById);
        req.getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String finishDate = req.getParameter("finishDate");
        String s = req.getParameter("status");
        Status status = Status.valueOf(s);
        User user = (User) req.getSession().getAttribute("user");
        ToDo toDoById = toDoManager.getToDoById(Integer.parseInt(id));
        try {
            toDoById.setTitle(title);
            toDoById.setFinish_date(DateUtil.stringToDate(finishDate));
            toDoById.setStatus(status);
            toDoManager.update(toDoById);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/home");
    }
}