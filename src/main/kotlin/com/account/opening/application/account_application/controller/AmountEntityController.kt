package com.account.opening.application.account_application.controller

import com.account.opening.application.account_application.entities.AmountEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


import com.account.opening.application.account_application.service.AmountEntityService
import org.springframework.web.bind.annotation.PostMapping

@RestController
@RequestMapping("/amounts")
class AmountEntityController(
    @Autowired
    private val amountEntityService: AmountEntityService
) {
    @GetMapping
    fun findAll(): ResponseEntity<List<AmountEntity>> {
        val amountEntity : List<AmountEntity> = amountEntityService.findAll()
        return ResponseEntity.status(HttpStatus.OK).body(amountEntity)
    }
    @GetMapping("/{id}")
    fun findById(@PathVariable ("id") id: Long): ResponseEntity<AmountEntity> {
        val amountEntity = amountEntityService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(amountEntity)
    }

}