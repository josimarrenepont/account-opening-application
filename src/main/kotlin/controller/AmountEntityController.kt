package controller

import AmountEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


import service.AmountEntityService

@RestController
@RequestMapping("/amounts")
class AmountEntityController(
    @Autowired
    private val amountEntityService: AmountEntityService
) {
    @GetMapping
    fun findAllAmount(): ResponseEntity<List<AmountEntity>> {
        val amountEntity : List<AmountEntity> = amountEntityService.getAllAmount()
        return ResponseEntity.status(HttpStatus.OK).body(amountEntity)
    }
    @GetMapping("/{id}")
    fun findByIdAmount(@PathVariable ("id") amountId: Long): ResponseEntity<AmountEntity> {
        val amountEntity = amountEntityService.getAmountEntity(amountId)
        return ResponseEntity.status(HttpStatus.OK).body(amountEntity)
    }
}