package com.example.testapplication.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.testapplication.R

val fontFamilyMedium = FontFamily(
    Font(R.font.sfprodisplaymedium, FontWeight.Normal)
)
val fontFamilyRegular = FontFamily(
    Font(R.font.sfprodisplayregular, FontWeight.Normal)
)
val titleLarge =  TextStyle(
    fontFamily = fontFamilyMedium,
    fontSize = 20*1.33.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.sp
)
val title1 =  TextStyle(
    fontFamily = fontFamilyMedium,
    fontSize =16*1.33.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.sp
)
val title2 =  TextStyle(
    fontFamily = fontFamilyMedium,
    fontSize = 14*1.33.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.sp
)
val title3 =  TextStyle(
    fontFamily = fontFamilyMedium,
    fontSize = 12*1.33.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.sp
)
val title4 =  TextStyle(
    fontFamily = fontFamilyRegular,
    fontSize = 14*1.33.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.sp
)
val text1 =  TextStyle(
    fontFamily = fontFamilyRegular,
    fontSize = 12*1.33.sp,
    lineHeight = 16*1.33.sp,
    letterSpacing = 0.sp
)
val caption1 =  TextStyle(
    fontFamily = fontFamilyRegular,
    fontSize = 10*1.33.sp,
    lineHeight =12*1.33 .sp,
    letterSpacing = 0.sp
)
val buttonText1 =  TextStyle(
    fontFamily = fontFamilyMedium,
    fontSize = 12*1.33.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.sp
)
val buttonText2 =  TextStyle(
    fontFamily = fontFamilyMedium,
    fontSize = 14*1.33.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.sp
)
val elementText =  TextStyle(
    fontFamily = fontFamilyRegular,
    fontSize = 9*1.33.sp,
    lineHeight = 10*1.33.sp,
    letterSpacing = 0.sp
)
val priceText =  TextStyle(
    fontFamily = fontFamilyMedium,
    fontSize = 24*1.33.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.sp
)
val placeholderText =  TextStyle(
    fontFamily = fontFamilyRegular,
    fontSize = 27.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.sp
)
val linkText =  TextStyle(
    fontFamily = fontFamilyRegular,
    fontSize = 10*1.33.sp,
    lineHeight = 14*1.33.sp,
    letterSpacing = 0.sp,
)
// Set of Material typography styles to start with
val Typography = Typography(

    titleLarge = TextStyle(
        fontFamily = fontFamilyMedium,
        fontSize = 27.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),

)
/* Other default text styles to override
labelSmall = TextStyle(
fontFamily = FontFamily.Default,
fontWeight = FontWeight.Medium,
fontSize = 11.sp,
lineHeight = 16.sp,
letterSpacing = 0.5.sp
)
*/
