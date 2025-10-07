package educationalSystem.model.tools;

import educationalSystem.model.entity.User;

import java.sql.ResultSet;

public class UserMapper {
    public User userMapper(ResultSet resultSet) throws Exception{
        return User
                .builder()
                .id(resultSet.getInt("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .address(resultSet.getString("address"))
                .nationality(resultSet.getString("nationality"))
                .birthDate(resultSet.getDate("birth_date").toLocalDate())
                .build();
    }
}
