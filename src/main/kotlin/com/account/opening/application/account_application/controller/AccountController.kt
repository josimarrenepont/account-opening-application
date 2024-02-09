package com.account.opening.application.account_application.controller

import com.account.opening.application.account_application.dto.AccountDto
import com.account.opening.application.account_application.dto.AmountDto
import com.account.opening.application.account_application.entities.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import com.account.opening.application.account_application.service.AccountService

@RestController
@RequestMapping("/accounts")
class AccountController(
    @Autowired
    private val accountService: AccountService
) {
    @PostMapping
    fun openAccount(@RequestBody @Validated accountDto: AccountDto): ResponseEntity<Account> {
        val savedAccount: Account = this.accountService.save(accountDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount)
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAccount(@PathVariable id: Long) = this.accountService.closeAccount(id)

    @GetMapping
    fun findAll(): ResponseEntity<List<Account>> {
        val account :List<Account> = accountService.findAll()
        return ResponseEntity.ok().body(account)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Account> {
        val account: Account = this.accountService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(account)
    }
    @PutMapping("/update/{id}")
    fun updateAccount(@PathVariable id: Long, @RequestBody accountDto: AccountDto): ResponseEntity<Account> {
        val entity = accountDto.toEntity()

        val account = accountService.updateAccount(id, entity.holderName)
        return ResponseEntity.ok().body(account)
    }

    @PostMapping("/deposit/{id}")
    fun deposit(@PathVariable id: Long, @RequestBody amount: AmountDto): ResponseEntity<Account> {

        val account: Account = this.accountService.deposit(id, amount.value)
        return ResponseEntity.ok(account)
    }

    @PostMapping("/withdraw/{id}")
    fun withdraw(@PathVariable id: Long, @RequestBody amount: AmountDto): ResponseEntity<Account> {
        val account: Account = this.accountService.withdraw(id, amount.value)
        return ResponseEntity.ok(account)
    }
}
