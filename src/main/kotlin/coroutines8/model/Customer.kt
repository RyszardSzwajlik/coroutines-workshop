package coroutines8.model

import org.springframework.data.annotation.Id

data class Customer(
    @Id var id: Long? = null,
    var firstName: String,
    var lastName: String
)
