package com.example.testapplication.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.testapplication.data.Product
import com.example.testapplication.data.ProductLocaleData
import com.example.testapplication.data.ProductViewEvent
import com.example.testapplication.data.ProductViewModel


@Composable
fun FavoritesScreen(
    viewModel: ProductViewModel,
    modifier: Modifier = Modifier,
    onProductClick: (String) -> Unit,
) {
    val favoritesProductsId = viewModel.productsLocal.filter { it.isFavorite }.map { it.id }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(viewModel.product.filter { favoritesProductsId.contains(it.id) }) {
                CatalogScreenCard(
                    it,
                    modifier = Modifier.clickable { onProductClick(it.id) },
                    favorite = viewModel.productsLocal.find { locale -> it.id == locale.id }
                        ?: ProductLocaleData(it.id),
                    onFavoriteClick = {
                        viewModel.handleViewEvent(ProductViewEvent.Update(it))
                    },
                    addToCartClick = {})
            }
        }
    }
}

