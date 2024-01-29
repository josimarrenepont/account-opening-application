package com.account.opening.application.account_application

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan
class AccountApplication

fun main(args: Array<String>) {
	SpringApplication.run(AccountApplication::class.java, *args)
}
