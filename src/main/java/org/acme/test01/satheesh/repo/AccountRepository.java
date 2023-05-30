package org.acme.test01.satheesh.repo;

import org.acme.test01.satheesh.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByCustomerNumber(Long customerNumber);

    Account findAccountByCustomerNumber(Long customerNumber);

}
