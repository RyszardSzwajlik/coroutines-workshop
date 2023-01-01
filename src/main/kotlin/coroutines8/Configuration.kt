package coroutines8

import coroutines8.model.Customer
import coroutines8.repository.CustomerRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import java.time.Duration
import java.util.*

@Configuration
open class Configuration {

    @Bean
    open fun webClient(webClientBuilder: WebClient.Builder): WebClient {
        return webClientBuilder.baseUrl("http://localhost:8080/").build()
    }

    @Bean
    open fun demo(repository: CustomerRepository): CommandLineRunner {
        return CommandLineRunner { args: Array<String?>? ->
            // save a few customers
            repository.saveAll(
                Arrays.asList(
                    Customer(firstName="Jack", lastName = "Bauer"),
                    Customer(firstName="Chloe", lastName = "O'Brian"),
                    Customer(firstName="Kim", lastName = "Bauer"),
                    Customer(firstName="David", lastName = "Palmer"),
                    Customer(firstName="Michelle", lastName = "Dessler")
                )
            )
                .blockLast(Duration.ofSeconds(10))

            // fetch all customers
            println("Customers found with findAll():")
            println("-------------------------------")
            repository.findAll().doOnNext { customer ->
                println(
                    customer.toString()
                )
            }.blockLast(Duration.ofSeconds(10))
            println("")

            // fetch an individual customer by ID
            repository.findById(1L).doOnNext { customer ->
                println("Customer found with findById(1L):")
                println("--------------------------------")
                println(customer.toString())
                println("")
            }.block(Duration.ofSeconds(10))


            // fetch customers by last name
            println("Customer found with findByLastName('Bauer'):")
            println("--------------------------------------------")
            repository.findByLastName("Bauer")!!.doOnNext { bauer ->
                println(
                    bauer.toString()
                )
            }.blockLast(Duration.ofSeconds(10))
            println("")
        }
    }
}