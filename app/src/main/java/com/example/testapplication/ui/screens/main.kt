package com.example.testapplication.ui.screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testapplication.R
import com.example.testapplication.data.Product
import com.example.testapplication.data.ProductLocaleData
import com.example.testapplication.data.ProductViewEvent
import com.example.testapplication.data.ProductViewModel
import com.example.testapplication.ui.theme.elementText
import com.example.testapplication.ui.theme.pink
import com.example.testapplication.ui.theme.title1
import com.example.testapplication.ui.theme.white


@Composable
fun mainScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: ProductViewModel,

    ) {
    val startDestination =  if (viewModel.getLogIn()!="  "){"main_screen"} else{"registration"}

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?:"main_screen"

    val topAppBarText = when (currentScreen) {
        "registration" -> "Вход"
        "catalog" -> "Каталог"
        "personal_area" -> "Личный кабинет"
        "favorites" -> "Избранное"
        "cart" -> "Корзина"
        "main_screen" -> "Главная"
        else -> ""
    }
    Scaffold(
        topBar = {
            topAppBar(text = topAppBarText)
        },
        bottomBar = {
            if (currentScreen != "registration") {
                bottomAppBar(navController, currentScreen)
            }
        }
    )
    { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = "registration") {
                RegistrationScreen(onEnterClick = { navController.navigate("main_screen") }, viewModel = viewModel)
            }
            composable(route = "catalog") {
                CatalogScreen(viewModel = viewModel) {
                    navController.navigate("catalog/{$it}")
                }
            }
            composable(route = "main_screen") {

            }
            composable(route = "catalog/{id}") {
                val current =
                    backStackEntry?.arguments?.getString("id") ?: viewModel.product.first().id
                val currentId = current.replace("{", "").replace("}", "")
                ProductScreen(product = viewModel.product.find { it.id == currentId }
                    ?: viewModel.product[0],
                    localeData = viewModel.productsLocal.find { it.id == currentId }
                        ?: ProductLocaleData(it.id),
                    onFavoriteClick = { viewModel.handleViewEvent(ProductViewEvent.Update(it)) }
                )
            }
            composable(route = "favorites") {
                FavoritesScreen(viewModel = viewModel) {
                }
            }
            composable(route = "cart") {

            }
            composable(route = "personal_area") {
                PersonalScreen(viewModel = viewModel,onFavoritesClick = { navController.navigate("favorites") })
            }

        }
    }
}


@Composable
fun topAppBar(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp), horizontalArrangement = Arrangement.SpaceAround
    ) {
        if (text != "") {
            Text(text = text, style = title1)
        } else {


            Icon(
                painter = painterResource(id = R.drawable.type_left_arrow__state_default),
                contentDescription = "",
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.type_send__state_default),
                contentDescription = "",
            )


        }
    }
}

@Composable
fun bottomAppBar(navController: NavHostController, current: String) {
    BottomAppBar {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            navigationColumn(
                text = "Главная",
                icon = R.drawable.type_sample__state_default,
                modifier = Modifier.clickable { navController.navigate("main_screen") },
                textColor = current == "main_screen"
            )
            navigationColumn(
                text = "Каталог",
                icon = R.drawable.type_catalog__state_default,
                modifier = Modifier.clickable { navController.navigate("catalog") },
                textColor = current == "catalog"
            )
            navigationColumn(
                text = "Корзина",
                icon = R.drawable.type_bag__state_default,
                modifier = Modifier.clickable { navController.navigate("cart") },
                textColor = current == "cart"
            )
            navigationColumn(
                text = "Акции",
                icon = R.drawable.type_discount__state_default,
                modifier = Modifier.clickable { },
                textColor = current == ""
            )
            navigationColumn(
                text = "Профиль",
                icon = R.drawable.type_account__state_default,
                modifier = Modifier.clickable { navController.navigate("personal_area") },
                textColor = current == "personal_area"
            )
        }
    }
}

@Composable
fun navigationColumn(modifier: Modifier = Modifier, text: String, icon: Int, textColor: Boolean) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(painter = painterResource(id = icon), contentDescription = "navigate to " + text)
        Text(
            text = text, style = elementText, color = if (textColor) {
                pink
            } else {
                Color.Black
            }
        )
    }
}