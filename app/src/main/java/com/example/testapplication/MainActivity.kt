package com.example.testapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.testapplication.data.ContextModule
import com.example.testapplication.data.DaggerAppComponent
import com.example.testapplication.data.network
import com.example.testapplication.ui.screens.mainScreen
import com.example.testapplication.ui.theme.TestApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
//        this.deleteDatabase("ProductsDatabase")

        val appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
//        val  network = network()

        setContent {

            TestApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


//                    CatalogScreen(network.data)
//                    RegistrationScreen()
                    mainScreen( viewModel =  appComponent.provideViewModel())

                }
            }
        }
    }
}
