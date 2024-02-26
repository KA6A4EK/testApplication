package com.example.testapplication.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.testapplication.R
import com.example.testapplication.data.ProductViewModel
import com.example.testapplication.ui.theme.buttonText1
import com.example.testapplication.ui.theme.lightGray
import com.example.testapplication.ui.theme.linkText
import com.example.testapplication.ui.theme.pink

//ввод номера
@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
    onEnterClick: () -> Unit,
    viewModel: ProductViewModel
) {
    var firstName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var firstNameError by remember { mutableStateOf(false) }
    var middleNameError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            registrationTextField(
                textValue = firstName,
                title = "Имя",
                error = firstNameError,
                modifier = modifier
            ) {
                firstName = it
                firstNameError = Regex("[^а-яА-ЯёЁ]+").containsMatchIn(firstName)
            }
            registrationTextField(
                textValue = middleName,
                title = "Фамилия",
                error = middleNameError,
                modifier = modifier
            ) {
                middleName = it
                middleNameError = Regex("[^а-яА-ЯёЁ]+").containsMatchIn(middleName)
            }
            registrationTextField(
                textValue = phoneNumber,
                error = phoneError,
                title = "Номер телефона",
                modifier = modifier
            ) {
                phoneNumber = it
                phoneError = !phoneNumber.isDigitsOnly()
            }


            Button(
                onClick = {
                    phoneError = !phoneNumber.isDigitsOnly() || phoneNumber.isEmpty()
                    firstNameError = Regex("[^а-яА-ЯёЁ]+").containsMatchIn(middleName)|| firstName.isEmpty()
                    middleNameError = Regex("[^а-яА-ЯёЁ]+").containsMatchIn(firstName) || middleName.isEmpty()
                    if (!phoneError && !firstNameError && !middleNameError) {
                        onEnterClick()
                        viewModel.saveUserLogin("$firstName $middleName $phoneNumber")
                    }
                },
                shape = RoundedCornerShape(7.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = pink
                ),
                modifier = modifier.padding(top = 30.dp)
            ) {
                Text(
                    text = "Войти",
                    modifier = modifier,
                    textAlign = TextAlign.Center,
                    style = buttonText1
                )
            }

        }
        Spacer(modifier = Modifier)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 10.dp)
        ) {

            Text(
                text = "Нажимая кнопку Войти, Вы принимаете",
                style = linkText
            )
            Text(
                text = "условия программы лояльности",
                style = linkText,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun registrationTextField(
    modifier: Modifier = Modifier,
    error: Boolean = false,
    textValue: String,
    title: String,
    onClick: (String) -> Unit,
) {
    val color = if (error) {
        Color(255, 1, 60, 163)
    } else {
        lightGray
    }
    Box(contentAlignment = Alignment.CenterEnd) {
        TextField(
            modifier = modifier
                .heightIn(60.dp)
                .clip(RoundedCornerShape(7.dp)),
            value = textValue, onValueChange = { onClick(it) },
            label = { Text(text = title) },
            textStyle = TextStyle(color = Color.Black),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = color,
                focusedIndicatorColor = color, // Цвет полоски при фокусе
                unfocusedIndicatorColor = color,// Цвет полоски при потере фокуса

            ),
        )
        Icon(
            painter = painterResource(id = R.drawable.type_big_close__state_default),
            contentDescription = "",
            modifier = Modifier
                .clickable { onClick("") }
                .padding(8.dp)
        )
    }
}
