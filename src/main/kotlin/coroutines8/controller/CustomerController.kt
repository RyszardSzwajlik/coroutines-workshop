package coroutines8.controller

import coroutines8.model.Customer
import coroutines8.repository.CustomerRepository
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration


@RestController
@RequestMapping("/customers")
class CustomerController(
    private val customerRepository: CustomerRepository
) {

    @GetMapping("/{customerId}")
    fun getCustomer(@PathVariable customerId: Long): Mono<Customer> {
        return customerRepository.findById(customerId)
            .delayElement(Duration.ofMillis(500))
    }

    @GetMapping
    fun getCustomers(): Flux<Customer> {
        return customerRepository.findAll()
            .delaySequence(Duration.ofMillis(500))
    }

    @PostMapping
    fun createCustomer(@RequestBody customer: Customer): Mono<Customer> {
        return customerRepository.save(customer)
            .delayElement(Duration.ofMillis(500))
    }

}