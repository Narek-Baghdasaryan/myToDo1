package com.example.mytodo.servlet;

import com.example.mytodo.enums.Status;
import com.example.mytodo.manager.ToDoManager;
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

@WebServlet(urlPatterns = "/addToDo")
public class AddToDoServlet extends HttpServlet {
    ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String finishDate = req.getParameter("finishDate");
        User user = (User) req.getSession().getAttribute("user");
        try {
            toDoManager.add(ToDo.builder()
                            .title(title)
                            .created_date(new java.util.Date())
                            .finish_date(DateUtil.stringToDate(finishDate))
                            .user(user)
                            .status(Status.NEW)
                    .build());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/home");
    }
}
