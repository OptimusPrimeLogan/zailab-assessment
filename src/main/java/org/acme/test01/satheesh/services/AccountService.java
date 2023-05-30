package org.acme.test01.satheesh.services;


import org.acme.test01.satheesh.exception.AccountNotFoundException;
import org.acme.test01.satheesh.exception.WithdrawalAmountTooLargeException;

public interface AccountService {
    public void openSavingsAccount(Long accountId, Long amountToDeposit) throws Exception;
    public void openCurrentAccount(Long accountId);
    public void withdraw(Long accountId, int amountToWithdraw)
            throws AccountNotFoundException, WithdrawalAmountTooLargeException;
    public void deposit(Long accountId, int amountToDeposit)
            throws AccountNotFoundException;
}
