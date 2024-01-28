<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.01.2024
  Time: 1:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.mytodo.model.ToDo" %>
<html>
<head>
    <title>Update</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,600;0,700;0,800;1,300;1,400;1,600;1,700;1,800&amp;display=swap">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootlint/1.1.0/bootlint.min.js"></script>

    <link rel="stylesheet" href="css/style.css">
    <script src="js/app.js"></script>
</head>
<body>
<form method="post" action="/updateToDo">
    <%ToDo toDo = (ToDo) request.getAttribute("toDo");%>
    <input type="hidden" value="<%=toDo.getId()%>" name="id">
    <div class="row m-1 p-3">
        <div class="col col-11 mx-auto">
            <div class="row bg-white rounded shadow-sm p-2 add-todo-wrapper align-items-center justify-content-center">
                <div class="col">

                    <input class="form-control form-control-lg border-0 add-todo-input bg-transparent rounded"
                           type="text" placeholder="update" name="title" value=<%=toDo.getTitle()%>>
                </div>
                <div class="col-auto m-0 px-2 d-flex align-items-center">
                    <input type="date" class="fa fa-calendar my-2 px-1 text-primary btn due-date-button"
                           data-toggle="tooltip"
                           data-placement="bottom" title="Set a Due date" name="finishDate"
                           value=<%=toDo.getFinish_date()%>>
                </div>
                <select name="status">
                    <option>NEW</option>
                    <option>DONE</option>
                </select>
                <div class="col-auto px-0 mx-0 mr-2">
                    <button type="submit" class="btn btn-primary">update</button>
                </div>
            </div>
        </div>
    </div>
</form>
<div class="p-2 mx-4 border-black-25 border-bottom"></div>

</body>
</html>