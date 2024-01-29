package service

import entities.Account
import exceptions.InsufficientBalanceException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import repository.AccountRepository
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
        return savedAccount;
    }
    @Override fun closeAccount(accountId: Long){
        accountRepository.deleteById(accountId)
    }
    @Override fun deposit(accountId: Long, amount: BigDecimal): Account {
        val account = getAccountById(accountId)
        account.balance += amount
        return accountRepository.save(account)
    }

    @Override fun withdraw(accountId: Long, amount: BigDecimal): Account {
        val account = getAccountById(accountId)
        if(account.balance < amount) {
            throw InsufficientBalanceException("Insufficient balance")
        }
        account.balance -= amount
        return accountRepository.save(account)
    }

    @Override fun getAccountById(accountId: Long): Account{
        return accountRepository.findById(accountId).orElseThrow{ AccountNotFoundException("Account not Found") }
    }
    @Override fun updateAccount(id: Long, holderName: String): Account {
        val account = getAccountById(id)
        account.holderName = holderName
        return accountRepository.save(account)
    }
    @Override fun getAllAccounts(): List<Account> {
        return accountRepository.findAll()
    }
}