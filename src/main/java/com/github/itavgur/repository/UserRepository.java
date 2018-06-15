package com.github.itavgur.repository;

import com.github.itavgur.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

//    @Transactional
//    User save(User user);

//    Optional<User> findById(int id);

//    List<User> findAll();

    Optional<User> findByLogin(String login);

    List<User> findByPhone(String phone);


    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);


}
