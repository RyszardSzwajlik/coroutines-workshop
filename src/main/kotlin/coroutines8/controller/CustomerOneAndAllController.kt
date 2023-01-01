package coroutines8.controller

import coroutines8.model.Customer
import coroutines8.model.OneAndAll
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/customers/one-and-all")
class CustomerOneAndAllController(
    private val webClient: WebClient,
) {

    @GetMapping("/{customerId}")
    fun getOneAndAll(@PathVariable customerId: Long): Mono<OneAndAll> {
        val one = webClient.get().uri("/customers/$customerId")
            .retrieve()
            .bodyToMono<Customer>()

        val all = webClient.get().uri("/customers")
            .retrieve()
            .bodyToMono<List<Customer>>()

        return Mono.zip(one, all)
            .map {
                OneAndAll(it.t1, it.t2)
            }
    }

}