package com.example.testapplication.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.testapplication.R
import com.example.testapplication.data.ProductViewModel
import com.example.testapplication.ui.theme.buttonText2
import com.example.testapplication.ui.theme.caption1
import com.example.testapplication.ui.theme.gray
import com.example.testapplication.ui.theme.orange
import com.example.testapplication.ui.theme.pink
import com.example.testapplication.ui.theme.title2

@Composable
fun PersonalScreen(
    onFavoritesClick : ()->Unit,
    viewModel: ProductViewModel
) {
    val user = viewModel.getLogIn().split(" ")
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        accountCard(firstName = user[0], middleName = user[1], phoneNumber = user[2], onExit = {viewModel.userExit()})
        PersonalScreenCard(
            text = "Избранное",
            modifier = Modifier.clickable { onFavoritesClick()},
            icon = R.drawable.type_heart__state_default,
            iconColor = pink
        )
        PersonalScreenCard(
            text = "Магазины",
            modifier = Modifier.clickable { },
            icon = R.drawable.type_shop__state_default,
            iconColor = pink
        )
        PersonalScreenCard(
            text = "Обратная связь",
            modifier = Modifier.clickable { },
            icon = R.drawable.type_feedback__state_default,
            iconColor = orange
        )
        PersonalScreenCard(
            text = "Оферта",
            modifier = Modifier.clickable { },
            icon = R.drawable.type_offer__state_default,
            iconColor = gray
        )
        PersonalScreenCard(
            text = "Возврат товара",
            modifier = Modifier.clickable { },
            icon = R.drawable.type_refund__state_default,
            iconColor = gray
        )
        Spacer(modifier = Modifier.weight(1f))
        Card (Modifier.padding(bottom = 20.dp, start = 10.dp, end = 10.dp)){
            Row (modifier = Modifier.fillMaxWidth().height(55.dp), horizontalArrangement = Arrangement.Absolute.Center, verticalAlignment = Alignment.CenterVertically){
                Text(text = "Выйти", style = buttonText2)
            }
        }
    }
}

@Composable
fun accountCard(firstName: String, middleName: String, phoneNumber: String,onExit:()->Unit) {
    Card(modifier = Modifier.padding(bottom = 20.dp, end = 10.dp, start = 10.dp, top = 10.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp)
                .heightIn(45.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.type_account__state_default),
                contentDescription = "",
                modifier = Modifier.padding(10.dp)
            )
            Column {
                Text(text = "$firstName $middleName", style = title2)
                Text(text = "$phoneNumber", style = caption1)
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.exit_icon),
                contentDescription = "",
                modifier = Modifier.padding(10.dp).clickable { onExit() }
            )
        }
    }
}

@Composable
fun PersonalScreenCard(
    text: String,
    text2: String = "",
    icon: Int,
    iconColor: Color,
    modifier: Modifier = Modifier,

    ) {
    Card(
        modifier.padding(6.dp), colors = CardDefaults.cardColors(
        )
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp)
                .heightIn(45.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.padding(10.dp)
            )
            Column {
                Text(text = text)
                if (text2 != "") {
                    Text(text = text2)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "")

        }
    }
}