package coroutines8.controller

import coroutines8.model.Customer
import coroutines8.model.OneAndAll
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody


@RestController
@RequestMapping("/customers/one-and-all")
class CustomerOneAndAllController(
    private val webClient: WebClient,
) {

    @GetMapping("/{customerId}")
    suspend fun getOneAndAll(@PathVariable customerId: Long): OneAndAll {
        val one = webClient.get().uri("/customers/$customerId")
            .retrieve()
            .awaitBody<Customer>()

        val all = webClient.get().uri("/customers")
            .retrieve()
            .awaitBody<List<Customer>>()

        return OneAndAll(one, all)
    }

}