package com.metroid.metroid.login_cycle.appuser;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
    @Query(value = "select first_name from app_user where id =?1"
            ,
            nativeQuery = true
    )
    String findFirstName(long id);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);


    @Modifying
    @Query(
            value = "update app_user set password =?1 where email = ?2",
            nativeQuery = true
           )
    int changePassword(String password, String email);
}

