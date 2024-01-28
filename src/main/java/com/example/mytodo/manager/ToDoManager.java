package com.example.mytodo.manager;

import com.example.mytodo.db.DBConnectionProvider;
import com.example.mytodo.enums.Status;
import com.example.mytodo.model.ToDo;
import com.example.mytodo.model.User;
import com.example.mytodo.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoManager {

    UserManager userManager = new UserManager();
    Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(ToDo toDo){
        String sql = "insert into todo(title, created_date,finish_date,user_id,status) values(?,?,?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,toDo.getTitle());
            preparedStatement.setString(2, DateUtil.dateToString(toDo.getCreated_date()));
            preparedStatement.setString(3, DateUtil.dateToString(toDo.getFinish_date()));
            preparedStatement.setInt(4, toDo.getUser().getId());
            preparedStatement.setString(5,String.valueOf(toDo.getStatus()));
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()){
                int id = generatedKeys.getInt(1);
                toDo.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(ToDo toDo){
        String sql = "update todo set title = ?,created_date = ?, finish_date = ?,user_id = ?,status = ? where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, toDo.getTitle());
            preparedStatement.setString(2,DateUtil.dateToString(toDo.getCreated_date()));
            preparedStatement.setString(3, DateUtil.dateToString(toDo.getFinish_date()));
            preparedStatement.setInt(4, toDo.getUser().getId());
            preparedStatement.setString(5,String.valueOf(toDo.getStatus()));
            preparedStatement.setInt(6,toDo.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<ToDo> getToDoByUser(User user) {
        List<ToDo> result = new ArrayList<>();
        String sql = "select * from todo where user_id=" + user.getId();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .created_date(resultSet.getDate("created_date"))
                        .finish_date(resultSet.getDate("finish_date"))
                        .user(userManager.getUserById(resultSet.getInt("user_id")))
                        .status(Status.valueOf(resultSet.getString("status")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public void delete(int id) {
        String sql = "delete from todo where id = " + id;
        try(Statement statement = connection.createStatement()){
           statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ToDo getToDoById(int id){
        String sql = "select * from todo where id=" + id;
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                return ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .created_date(resultSet.getDate("created_date"))
                        .finish_date(resultSet.getDate("finish_date"))
                        .user(userManager.getUserById(resultSet.getInt("user_id")))
                        .status(Status.valueOf(resultSet.getString("status")))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
