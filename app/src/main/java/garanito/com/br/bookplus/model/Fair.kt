package garanito.com.br.bookplus.model

import com.google.gson.annotations.SerializedName

data class FairResponse(val content: List<Fair>)

data class Fair(
        @SerializedName("Name") val Name: String,
        @SerializedName("Description") val Description: String,
        @SerializedName("InitialDate") val InitialDate: String,
        @SerializedName("FinalDate") val FinalDate: String,
        @SerializedName("InitialHour") val InitialHour: String,
        @SerializedName("FinalHour") val FinalHour: String,
        @SerializedName("imageURL") val imagem: String
)