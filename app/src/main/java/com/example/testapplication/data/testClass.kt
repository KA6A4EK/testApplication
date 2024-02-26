package com.example.testapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

class network {
    lateinit var data: List<Product>

    private val BASE_URL =
        "https://run.mocky.io/v3/97e721a7-0a66-4cae-b445-83cc0bcf9010/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    init {
        runBlocking {
            data = retrofitService.getItems().items
        }
    }

}

interface ApiService {
    @GET("items")
    suspend fun getItems(): items
}

@Serializable
data class items(
    val items: List<Product>,
)

@Entity(tableName = "ProductsLocale")
data class ProductLocaleData(
    @PrimaryKey
    val id: String,
    val inCart: Boolean = false,
    val isFavorite: Boolean = false,
)

@Serializable
data class Product(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("price") val price: Price,
    @SerializedName("feedback") val feedback: Feedback,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("available") val available: Int,
    @SerializedName("description") val description: String,
    @SerializedName("info") val info: List<Info>,
    @SerializedName("ingredients") val ingredients: String,
)

@Serializable
data class Price(
    @SerializedName("price") val price: String,
    @SerializedName("discount") val discount: Int,
    @SerializedName("priceWithDiscount") val priceWithDiscount: String,
    @SerializedName("unit") val unit: String,
)

@Serializable
data class Feedback(
    @SerializedName("count") val count: Int,
    @SerializedName("rating") val rating: Double,
)

@Serializable
data class Info(
    @SerializedName("title") val title: String,
    @SerializedName("value") val value: String,
)
