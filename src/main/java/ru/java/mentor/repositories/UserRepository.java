package ru.java.mentor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.java.mentor.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //@Query("select user from User user where user.login=:login")
    User findByLogin(@Param("login") String login);

    //@Query("select user from User user where user.id=:id")
    User getById(@Param("id") Integer id);
}
