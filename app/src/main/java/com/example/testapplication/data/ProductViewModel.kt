package com.example.testapplication.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class ProductViewModel @Inject constructor(
    private val repos: repository,
    private val context: Context,
) :
    ViewModel() {
    var productsLocal = runBlocking { repos.getAllProductsLocale() }
    val product = runBlocking { network().data }
    private val sharedPrefs = context.getSharedPreferences("User", Context.MODE_PRIVATE)

    fun handleViewEvent(viewEvent: ProductViewEvent) {
        when (viewEvent) {
            is ProductViewEvent.Update -> {
                viewModelScope.launch {
                    repos.Update(viewEvent.product)
                    productsLocal = productsLocal.map { product ->
                        if (product.id == viewEvent.product.id) {
                            viewEvent.product
                        } else {
                            product
                        }
                    }
                }

            }
        }
    }

    fun saveUserLogin(string: String) {
        val s = string.split(" ")
        sharedPrefs.edit().apply {
            putString("firstName", s[0])
            putString("middleName", s[1])
            putString("phoneNumber", s[2])
        }
            .apply()
    }

    fun userExit() {
        sharedPrefs.edit().apply {
            putString("firstName", "")
            putString("middleName", "")
            putString("phoneNumber", "")
        }
            .apply()
    }

    fun getLogIn() = "${sharedPrefs.getString("firstName", "")} ${
        sharedPrefs.getString(
            "middleName",
            ""
        )
    } ${sharedPrefs.getString("phoneNumber", "")}"

}

sealed class ProductViewEvent {
    data class Update(val product: ProductLocaleData) : ProductViewEvent()
}

@Module
class ProvideViewModel() {
    @Provides
    fun provideHealthViewModel(
        context: Context,
        repository: repository,
    ): ProductViewModel {
        return ProductViewModel(
            repository,
            context
        )
    }
}