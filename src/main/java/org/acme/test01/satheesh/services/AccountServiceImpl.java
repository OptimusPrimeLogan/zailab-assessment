package org.acme.test01.satheesh.services;

import org.acme.test01.satheesh.services.exception.AccountNotFoundException;
import org.acme.test01.satheesh.services.exception.WithdrawalAmountTooLargeException;

public class AccountServiceImpl implements AccountService{
    /**
     * @param accountId
     * @param amountToDeposit
     */
    @Override
    public void openSavingsAccount(Long accountId, Long amountToDeposit) {

    }

    /**
     * @param accountId
     */
    @Override
    public void openCurrentAccount(Long accountId) {

    }

    /**
     * @param accountId
     * @param amountToWithdraw
     * @throws AccountNotFoundException
     * @throws WithdrawalAmountTooLargeException
     */
    @Override
    public void withdraw(Long accountId, int amountToWithdraw) throws AccountNotFoundException, WithdrawalAmountTooLargeException {

    }

    /**
     * @param accountId
     * @param amountToDeposit
     * @throws AccountNotFoundException
     */
    @Override
    public void deposit(Long accountId, int amountToDeposit) throws AccountNotFoundException {

    }
}
