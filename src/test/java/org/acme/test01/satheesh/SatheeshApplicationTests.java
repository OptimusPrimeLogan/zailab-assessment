package org.acme.test01.satheesh;

import org.acme.test01.satheesh.entity.Account;
import org.acme.test01.satheesh.entity.Transaction;
import org.acme.test01.satheesh.services.AccountService;
import org.acme.test01.satheesh.services.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.AssertionErrors;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class SatheeshApplicationTests {

	@Autowired
	AccountServiceImpl accountService;

	@BeforeEach
	void setup(){}

	@Test
	void contextLoads() {
		List<Account> accountList = accountService.listAccount();
		assertTrue(accountList.size()==4);

		List<Transaction> txList = accountService.listTransactions();
		assertTrue(accountList.size()==4);

		assertDoesNotThrow( () -> accountService.withdraw(1L, 1000000000));
	}



}
