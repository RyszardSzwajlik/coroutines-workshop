package coroutines8.controller

import coroutines8.model.Customer
import coroutines8.repository.CustomerRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/customers")
class CustomerController(
    private val customerRepository: CustomerRepository
) {

    @GetMapping("/{customerId}")
    suspend fun getCustomer(@PathVariable customerId: Long): Customer {
        delay(500)
        return customerRepository.findById(customerId).awaitSingle()
    }

    @GetMapping
    suspend fun getCustomers(): Flow<Customer> {
        delay(500)
        return customerRepository.findAll().asFlow()
    }

    @PostMapping
    suspend fun createCustomer(@RequestBody customer: Customer): Customer {
        delay(500)
        return runBlocking {
            async { customerRepository.save(customer).block() }
        }.await()!!
    }

}