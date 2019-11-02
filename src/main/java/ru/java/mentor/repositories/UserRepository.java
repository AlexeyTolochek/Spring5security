package ru.java.mentor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.java.mentor.model.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select user from User user left join fetch user.roles where user.login=:login")
    User findByLogin(@Param("login") String login);

    @Query("select user from User user left join fetch user.roles where user.id=:id")
    User getById(@Param("id") Integer id);

    @Query("from User as user left join fetch user.roles ")
    List<User> findAll();
}
