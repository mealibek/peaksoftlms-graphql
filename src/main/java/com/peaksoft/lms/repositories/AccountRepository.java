package com.peaksoft.lms.repositories;

import com.peaksoft.lms.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Boolean existsAccountByEmail(String email);

    Optional<Account> findByEmail(String email);

    Optional<Account> findByResetPasswordToken(String resetPasswordToken);

}
