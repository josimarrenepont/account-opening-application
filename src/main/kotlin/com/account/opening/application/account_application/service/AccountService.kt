package com.account.opening.application.account_application.service

import com.account.opening.application.account_application.entities.Account
import com.account.opening.application.account_application.exceptions.InsufficientBalanceException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.account.opening.application.account_application.repository.AccountRepository
import java.math.BigDecimal
import javax.security.auth.login.AccountNotFoundException

@Service
class AccountService(
    @Autowired
    private val accountRepository: AccountRepository
) {

   @Override fun save(toEntity: Account): Account {
        println("Saving account: $toEntity")
        val savedAccount = accountRepository.save(toEntity)
        println("Saved account: $savedAccount")
        return savedAccount
    }
    @Override fun closeAccount(accountId: Long){
        accountRepository.deleteById(accountId)
    }
    @Override fun deposit(accountId: Long, amount: BigDecimal): Account {
        val account = findById(accountId)
        account.balance += amount
        return accountRepository.save(account)
    }

    @Override fun withdraw(accountId: Long, amount: BigDecimal): Account {
        val account = findById(accountId)
        if(account.balance < amount) {
            throw InsufficientBalanceException("Insufficient balance")
        }
        account.balance -= amount
        return accountRepository.save(account)
    }

    @Override fun findById(id: Long): Account {
        return accountRepository.findById(id).orElseThrow{ AccountNotFoundException("Account not Found") }
    }
    @Override fun updateAccount(id: Long, holderName: String): Account {
        val account = findById(id)
        account.holderName = holderName
        return accountRepository.save(account)
    }
    @Override fun findAll(): List<Account> {
        return accountRepository.findAll()
    }

}