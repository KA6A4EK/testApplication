package com.example.testapplication.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.testapplication.R
import com.example.testapplication.data.Product
import com.example.testapplication.data.ProductLocaleData
import com.example.testapplication.ui.theme.buttonText1
import com.example.testapplication.ui.theme.buttonText2
import com.example.testapplication.ui.theme.caption1
import com.example.testapplication.ui.theme.gray
import com.example.testapplication.ui.theme.orange
import com.example.testapplication.ui.theme.pink
import com.example.testapplication.ui.theme.priceText
import com.example.testapplication.ui.theme.text1
import com.example.testapplication.ui.theme.title1
import com.example.testapplication.ui.theme.titleLarge
import kotlin.math.roundToInt

fun imageId(id: String) = when (id) {
    "cbf0c984-7c6c-4ada-82da-e29dc698bb50" -> listOf(
        R.drawable.acbf0c9847c6c4ada82dae29dc698bb50,
        R.drawable.a75c8440752e14ccea73aff2d3ac031b3
    )

    "54a876a5-2205-48ba-9498-cfecff4baa6e" -> listOf(
        R.drawable.a54a876a5220548ba9498cfecff4baa6e,
        R.drawable.a16f88865ae744b7c9d85b68334bb97db,
        R.drawable.a15f88865ae744b7c9d81b78334bb97db
    )

    "75c84407-52e1-4cce-a73a-ff2d3ac031b3" -> listOf(
        R.drawable.a75c8440752e14ccea73aff2d3ac031b3,
        R.drawable.a26f88856ae744b7c9d85b68334bb97db
    )

    "16f88865-ae74-4b7c-9d85-b68334bb97db" -> listOf(
        R.drawable.a16f88865ae744b7c9d85b68334bb97db,
        R.drawable.acbf0c9847c6c4ada82dae29dc698bb50
    )

    "26f88856-ae74-4b7c-9d85-b68334bb97db" -> listOf(
        R.drawable.a26f88856ae744b7c9d85b68334bb97db,
        R.drawable.a15f88865ae744b7c9d81b78334bb97db
    )

    "15f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(
        R.drawable.a15f88865ae744b7c9d81b78334bb97db,
        R.drawable.a55f58865ae744b7c9d81b78334bb97db
    )

    "55f58865-ae74-4b7c-9d81-b78334bb97db" -> listOf(
        R.drawable.a55f58865ae744b7c9d81b78334bb97db,
        R.drawable.acbf0c9847c6c4ada82dae29dc698bb50
    )

    "88f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(
        R.drawable.a88f88865ae744b7c9d81b78334bb97db,
        R.drawable.a75c8440752e14ccea73aff2d3ac031b3
    )

    else -> listOf(R.drawable.ic_launcher_foreground)
}

@Composable
fun ProductScreen(
    product: Product,
    localeData: ProductLocaleData,
    onFavoriteClick: (ProductLocaleData) -> Unit,
) {
    var isfavorite by remember { mutableStateOf(localeData.isFavorite) }
    Box(contentAlignment = Alignment.BottomCenter) {
        LazyColumn(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {


            item {
                Box(contentAlignment = Alignment.TopEnd) {


//                Image(
//                    painter = painterResource(id = imageId(product.id)),
//                    contentDescription = "",
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    contentScale = ContentScale.Crop
//                )
                    HorizontalSwipeableImages(
                        list = imageId(product.id),
                        isBig = true,
                        modifier = Modifier.fillMaxWidth()
                    )

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
                                onFavoriteClick(localeData.copy(isFavorite = !localeData.isFavorite))
                                isfavorite = !isfavorite
                            }
                            .padding(8.dp)
                    )
                }

                Text(text = product.title, style = title1, color = gray)
                Text(text = product.subtitle, style = titleLarge)
                Text(
                    text = "Доступно для заказа ${product.available} штук",
                    style = text1,
                    color = gray
                )
                Divider()
                Row {
                    for (i in 1..product.feedback.rating.roundToInt()) {
                        Icon(
                            painter = painterResource(id = R.drawable.small_star),
                            contentDescription = "",
                            tint = orange
                        )
                    }
                    Text(
                        text = "${product.feedback.rating} ${product.feedback.count} отзыва",
                        style = text1
                    )
                }
                Row {
                    Text(
                        text = "${product.price.priceWithDiscount} ",
                        style = priceText
                    )
                    Text(
                        text = "${product.price.price} ",
                        style = priceText
                    )
                    Text(
                        text = "-${product.price.discount}",
                        style = priceText
                    )
                } //добавить зачеркнутую цену
                var descriptionExpanded by remember {
                    mutableStateOf(true)
                }
                Text(text = "Описание", style = title1)
                if (descriptionExpanded) {
                    Text(text = product.title)
                    Text(text = product.description, style = text1)
                }
//            ??Text(text = "скрыть", style = buttonText1, modifier = Modifier.clickable {  })

                Text(
                    text = if (!descriptionExpanded) {
                        "подробнее"
                    } else {
                        "скрыть"
                    },
                    style = buttonText1,
                    modifier = Modifier.clickable { descriptionExpanded = !descriptionExpanded })

                Text(text = "характеристики", style = title1)
                for (i in product.info) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "${i.title}")
                        Text(text = "${i.value}")

                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "состав", style = title1)
                }
                var ingridientsExpanded by remember {
                    mutableStateOf(false)
                }
                Text(
                    text = "${product.ingredients}",
                    style = text1,
                    maxLines = if (ingridientsExpanded) {
                        10
                    } else {
                        2
                    }
                )

                Text(
                    text = if (!ingridientsExpanded) {
                        "подробнее"
                    } else {
                        "скрыть"
                    },
                    style = buttonText1,
                    modifier = Modifier.clickable { ingridientsExpanded = !ingridientsExpanded })
                Spacer(modifier = Modifier.height(60.dp))

            }
        }
        Button(
            onClick = {},
            shape = RoundedCornerShape(7.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = pink
            ),
            modifier = Modifier
                .padding(10.dp)
                .heightIn(50.dp)
        ) {
            Text(
                text = "${product.price.priceWithDiscount} ",
                style = buttonText2
            )
            Text(
                text = "${product.price.price} ",
                style = caption1,
                textDecoration = TextDecoration.LineThrough
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Добавить корзину")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalSwipeableImages(list: List<Int>, modifier: Modifier, isBig: Boolean) {
    val icon = if (isBig) {
        listOf(R.drawable.ellipse_big, R.drawable.ellipse_active_big)
    } else {
        listOf(R.drawable.ellipse_small, R.drawable.ellipse_active_smal)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val pagerState = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f
        ) {
            list.size
        }
        HorizontalPager(
            state = pagerState,
            key = { it }
        ) {
            Image(
                modifier = modifier,
                painter = painterResource(id = list[it]),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
            for (i in 0..list.size - 1) {
                if (pagerState.currentPage == i) {
                    Icon(painter = painterResource(icon[1]), contentDescription = "", tint = pink)
                } else {
                    Icon(painter = painterResource(icon[0]), contentDescription = "")
                }
            }
        }
    }
}