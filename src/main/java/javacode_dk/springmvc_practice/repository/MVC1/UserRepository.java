package javacode_dk.springmvc_practice.repository.MVC1;

import javacode_dk.springmvc_practice.model.MVC1.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
    @Modifying
    @Query("delete from user_table b where b.email=:email")
    void deleteByEmail(String email);
}
