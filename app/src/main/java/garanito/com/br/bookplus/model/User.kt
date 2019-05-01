package garanito.com.br.bookplus.model

data class User(
        val name: String,
        val email: String,
        val zipCode: Double?,
        val city: String,
        val positionX: Double,
        val positionY: Double
)
