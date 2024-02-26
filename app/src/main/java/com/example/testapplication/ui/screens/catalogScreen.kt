package com.example.testapplication.ui.screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.testapplication.R
import com.example.testapplication.data.Product
import com.example.testapplication.data.ProductLocaleData
import com.example.testapplication.data.ProductViewEvent
import com.example.testapplication.data.ProductViewModel
import com.example.testapplication.ui.theme.buttonText2
import com.example.testapplication.ui.theme.orange
import com.example.testapplication.ui.theme.pink
import com.example.testapplication.ui.theme.text1
import com.example.testapplication.ui.theme.title1
import com.example.testapplication.ui.theme.title2
import com.example.testapplication.ui.theme.white
import kotlin.math.roundToInt

@Composable
fun CatalogScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel,
    onProductClick: (String) -> Unit,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalArrangement = Arrangement.SpaceAround
        ) {
            Icon(
                painterResource(id = R.drawable.type_sort_by__state_default),
                contentDescription = ""
            )
            Text(text = "По популярности")

            Icon(
                painterResource(id = R.drawable.type_down_arrow__state_default),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painterResource(id = R.drawable.type_filter__state_default),
                contentDescription = ""
            )
            Text(text = "Фильтры")
        }

        LazyRow(modifier = Modifier.fillMaxWidth()) {
            item {
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(CircleShape)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {


                        Text(
                            text = "Смотреть все",
                            style = buttonText2,
                            modifier = Modifier.padding(
                                start = 12.dp,
                                end = 12.dp,
                                top = 8.dp,
                                bottom = 8.dp
                            )
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.type_big_close__state_default),
                            contentDescription = "",
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                }
            }
            items(viewModel.product.map { info -> info.info.find { it.title == "Область использования" }?.value }
                .distinct()) { product ->
                if (product != null) {
                    Card(
                        modifier = Modifier
                            .padding(5.dp)
                            .clip(CircleShape)
                    ) {
                        Text(
                            text = "$product",
                            style = buttonText2,
                            modifier = Modifier.padding(
                                start = 12.dp,
                                end = 12.dp,
                                top = 8.dp,
                                bottom = 8.dp
                            )
                        )
                    }
                }
            }
        }
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(viewModel.product) { product ->
                Log.e(TAG,viewModel.product.size.toString())
                CatalogScreenCard(
                    product,
                    favorite = viewModel.productsLocal.find { prod -> product.id == prod.id }
                        ?: ProductLocaleData(product.id),
                    modifier = Modifier.clickable { onProductClick(product.id) },
                    addToCartClick = {},
                    onFavoriteClick = {
                        viewModel.handleViewEvent(ProductViewEvent.Update(it))
                    })
            }
        }
    }
}

@Composable
fun CatalogScreenCard(
    product: Product,
    favorite: ProductLocaleData,
    modifier: Modifier = Modifier,
    addToCartClick: () -> Unit,
    onFavoriteClick: (ProductLocaleData) -> Unit,
) {
    var isfavorite by remember {
        mutableStateOf(favorite.isFavorite) }
    Box(contentAlignment = Alignment.TopEnd) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(5.dp), verticalArrangement = Arrangement.SpaceBetween
        ) {

//            Image(painter = painterResource(id = imageId(product.id)), contentDescription = "")
            HorizontalSwipeableImages(list = imageId(product.id), isBig = false, modifier = Modifier.fillMaxWidth())
            Row {

            }
            Text(
                text = "${product.price.price} ${product.price.unit}",
                textDecoration = TextDecoration.LineThrough
            )
            Row {
                Text(text = "${product.price.priceWithDiscount} ", style = title1)
                Text(
                    text = " -${product.price.discount}% ", modifier = Modifier.background(
                        pink,
                        RoundedCornerShape(5.dp)
                    ), color = white, style = title2
                )
            }
            Text(text = product.title, style = title1)
            Text(
                text = "${product.subtitle}\n\n",
                maxLines = 3,
                style = text1,
            )
            Row {
                for (i in 1..product.feedback.rating.roundToInt()){
                    Icon(painter = painterResource(id = R.drawable.small_star), contentDescription = "", tint = orange)
                }
                Text(text = "${product.feedback.rating} ")
                Text(text = "(${product.feedback.count})")
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .background(
                            color = pink,
                            RoundedCornerShape(bottomEnd = 10.dp, topStart = 10.dp)
                        )
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painterResource(id = R.drawable.type_plus__state_default),
                            contentDescription = "",
                            Modifier.size(50.dp),
                            white
                        )
                    }
                }
            }
        }
        Icon(
            painter = painterResource(
                id = if (isfavorite) {
                    R.drawable.type_heart__state_active
                } else {
                    R.drawable.type_heart__state_default
                }
            ),
            tint = pink,
            contentDescription = "",
            modifier = Modifier
                .clickable {
                    onFavoriteClick(favorite.copy(isFavorite = !favorite.isFavorite))
                    isfavorite = !isfavorite
                }
                .padding(8.dp)
        )
    }
}

