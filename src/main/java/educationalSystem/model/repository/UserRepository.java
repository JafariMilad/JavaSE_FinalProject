package educationalSystem.model.repository;

import educationalSystem.model.entity.User;
import educationalSystem.model.tools.ConnectionProvider;
import educationalSystem.model.tools.UserMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final UserMapper userMapper = new UserMapper();

    public UserRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(User user) throws Exception {
        user.setId(ConnectionProvider.getProvider().getNextId("id"));

        preparedStatement = connection.prepareStatement(
                "insert into Users (id, first_name, last_name, email, address, nationality, birth_date)" +
                        "values (?, ?, ?, ?, ?, ?, ?)"
        );

        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setString(5, user.getAddress());
        preparedStatement.setString(6, user.getNationality());
        preparedStatement.setDate(7, Date.valueOf(user.getBirthDate()));
        preparedStatement.execute();
    }

    @Override
    public void edit(User user) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update Users set first_name=?, last_name=?, email=?, address=?, nationality=?, birth_date=? where id=?"
        );
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getAddress());
        preparedStatement.setString(5, user.getNationality());
        preparedStatement.setDate(6, Date.valueOf(user.getBirthDate()));
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from Users where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<User> findAll() throws Exception {
        List<User> userList = new ArrayList<>();

        preparedStatement = connection.prepareStatement(
                "select * from Users order by first_name, last_name"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            User user = userMapper.userMapper(resultSet);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public User findById(Integer id) throws Exception {
        User user = null;

        preparedStatement = connection.prepareStatement(
                "select * from Users where id=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            user = userMapper.userMapper(resultSet);
        }
        return user;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
