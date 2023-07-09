package web.dao;

import web.model.User;
import java.util.List;

public interface UserDao {
    List<User> readAllUsers();
    void create(User user);

    User update(int id);

    void delete(int id);
}
