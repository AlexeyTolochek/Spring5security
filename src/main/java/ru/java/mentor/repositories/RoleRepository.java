package ru.java.mentor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.java.mentor.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("from Role where role=:role")
    Role findByRole(@Param("role") String role);

    Role findById(int id);
}
