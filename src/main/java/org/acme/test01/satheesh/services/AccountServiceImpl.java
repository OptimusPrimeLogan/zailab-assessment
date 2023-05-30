package org.acme.test01.satheesh.services;

import org.acme.test01.satheesh.entity.Account;
import org.acme.test01.satheesh.entity.AccountEnum;
import org.acme.test01.satheesh.entity.Transaction;
import org.acme.test01.satheesh.entity.TransactionEnum;
import org.acme.test01.satheesh.exception.AccountNotFoundException;
import org.acme.test01.satheesh.exception.WithdrawalAmountTooLargeException;
import org.acme.test01.satheesh.repo.AccountRepository;
import org.acme.test01.satheesh.repo.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final TransactionRepository transactionRepository;

    public AccountServiceImpl(AccountRepository repository, TransactionRepository transactionRepository) {
        this.repository = repository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * @param accountId
     * @param amountToDeposit
     */
    @Override
    public void openSavingsAccount(Long accountId, Long amountToDeposit) throws Exception {
        if (amountToDeposit < 1000) {
            throw new Exception("Deposit Amount too low");
        }
        Account savings = new Account();
        savings.setCustomerNumber(accountId);
        savings.setType(AccountEnum.SAVINGS);
        savings.setBalance(amountToDeposit);
        savings.setOverDraftBalance(0L);
        repository.saveAndFlush(savings);
    }

    /**
     * @param accountId
     */
    @Override
    public void openCurrentAccount(Long accountId) {
        Account current = new Account();
        current.setCustomerNumber(accountId);
        current.setType(AccountEnum.CURRENT);
        current.setBalance(0L);
        current.setOverDraftBalance(0L);
        repository.saveAndFlush(current);
    }

    /**
     * @param accountId
     * @param amountToWithdraw
     * @throws AccountNotFoundException
     * @throws WithdrawalAmountTooLargeException
     */
    @Override
    public void withdraw(Long accountId, int amountToWithdraw) throws AccountNotFoundException, WithdrawalAmountTooLargeException {

        if (!repository.existsByCustomerNumber(accountId)) {
            throw new AccountNotFoundException();
        }

        Account account = repository.findAccountByCustomerNumber(accountId);
        long accountBalance = account.getBalance();
        long overDraftBalance = account.getOverDraftBalance();

        if (account.getType().equals(AccountEnum.SAVINGS) && (accountBalance - amountToWithdraw < 1000)) {
            throw new WithdrawalAmountTooLargeException();
        } else if (account.getType().equals(AccountEnum.CURRENT)) {
            long ledgerBalance = accountBalance - account.getOverDraftBalance();
            long withdrawalLimit = accountBalance - (overDraftBalance - 100000);

            if ((ledgerBalance-amountToWithdraw) < -100000) {
                throw new WithdrawalAmountTooLargeException();
            }else if (withdrawalLimit < amountToWithdraw){
                throw new WithdrawalAmountTooLargeException();
            }
        }else {
            account.setBalance(accountBalance - amountToWithdraw);
            repository.saveAndFlush(account);

            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setType(TransactionEnum.DEPOSIT);
            transaction.setCustomerNumber(accountId);
            transactionRepository.saveAndFlush(transaction);
        }




    }

    /**
     * @param accountId
     * @param amountToDeposit
     * @throws AccountNotFoundException
     */
    @Override
    public void deposit(Long accountId, int amountToDeposit) throws AccountNotFoundException {

        if (!repository.existsByCustomerNumber(accountId)) {
            throw new AccountNotFoundException();
        }

        Account account = repository.findAccountByCustomerNumber(accountId);
        account.setBalance(account.getBalance() + amountToDeposit);
        repository.saveAndFlush(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType(TransactionEnum.DEPOSIT);
        transaction.setCustomerNumber(accountId);
        transactionRepository.saveAndFlush(transaction);
    }

    public List<Account> listAccount() {
        return repository.findAll();
    }

    public List<Transaction> listTransactions() {
        return transactionRepository.findAll();
    }
}
