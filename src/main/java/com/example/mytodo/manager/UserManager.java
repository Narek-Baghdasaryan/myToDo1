package com.example.mytodo.manager;

import com.example.mytodo.db.DBConnectionProvider;
import com.example.mytodo.model.User;

import java.sql.*;

public class UserManager {

    Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(User user){
        String sql = "insert into user(name,surname,email,password) values (?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2,user.getSurname());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                int id = generatedKeys.getInt(1);
                user.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String password = resultSet.getString("password");
                return User.builder()
                        .id(id)
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .password(password)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public User getUserById(int id){
    String sql = "select * from user where id =" + id;
    try(Statement statement = connection.createStatement()){
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            User user = User.builder()
                    .id(id)
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .build();
            return user;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
    }
}
