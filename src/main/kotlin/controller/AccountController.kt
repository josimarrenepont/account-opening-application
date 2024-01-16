package controllers

import AmountDto
import entities.dto.AccountDto
import entities.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import services.AccountService

@RestController
@RequestMapping("/accounts")
data class AccountController(
    @Autowired
    private val accountService: AccountService
) {
    @GetMapping
    fun findAllAccounts(): ResponseEntity<List<Account>> {
        val account :List<Account> = accountService.getAllAccounts()
        return ResponseEntity.status(HttpStatus.OK).body(account)
    }

    @GetMapping("/{id}")
    fun findAccountById(@PathVariable("id") accountId: Long): ResponseEntity<Account> {
        val account = accountService.getAccountById(accountId)
        return ResponseEntity.status(HttpStatus.OK).body(account)
    }
    @PostMapping
    fun openAccount(@RequestBody accountDto: AccountDto): ResponseEntity<Account> {
        val entity = accountDto.toEntity()
        val savedAccount: Account = this.accountService.save(entity)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount)
    }

    @PutMapping("/update/{id}")
    fun updateAccount(@PathVariable id: Long, @RequestBody accountDto: AccountDto): ResponseEntity<Account> {
        val entity = accountDto.toEntity()
        // Verifique se a entidade não é nula antes de prosseguir
        val account = accountService.updateAccount(id, entity.holderName)
        return ResponseEntity.ok().body(account)
    }

    @PostMapping("/deposit/{id}")
    fun deposit(@PathVariable id: Long, @RequestBody amount: AmountDto): ResponseEntity<Account> {
        // Adicione verificações semelhantes para o objeto AmountDto
        val account: Account = this.accountService.deposit(id, amount.value)
        return ResponseEntity.ok(account)
    }

    @PostMapping("/withdraw/{id}")
    fun withdraw(@PathVariable id: Long, @RequestBody amount: AmountDto): ResponseEntity<Account> {
        // Adicione verificações semelhantes para o objeto AmountDto
        val account: Account = this.accountService.withdraw(id, amount.value)
        return ResponseEntity.ok(account)
    }
}
