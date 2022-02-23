package com.sylvia.h_medi.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import com.sylvia.h_medi.R


private val Montserrat = FontFamily(
    Font(R.font.montserrat_black, FontWeight.W700),
    Font(R.font.montserrat_bold, FontWeight.W600),
    Font(R.font.montserrat_regular, FontWeight.W400),
    Font(R.font.montserrat_light, FontWeight.W300)
)


val Typography = Typography(

    h1 = TextStyle(
        fontWeight = FontWeight.ExtraBold,
        fontFamily = Montserrat,
        fontSize = 30.sp,
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontFamily = Montserrat,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontFamily = Montserrat,
        fontSize = 20.sp
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontFamily = Montserrat,
        fontSize = 16.sp
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontFamily = Montserrat,
        fontSize = 15.sp
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontFamily = Montserrat,
        fontSize = 12.sp
    ),
    body1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontFamily = Montserrat,
        fontSize = 14.sp
    )

)