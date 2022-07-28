package com.metroid.metroid.login_cycle.appuser;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPhoneRepository  extends JpaRepository<UserPhone, Long> {
}
