package com.sylvia.h_medi.presentation.ui.home.customBottomNav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sylvia.h_medi.presentation.Screen

@Composable
fun CustomBottomNavigation(
    currentScreenRoute: String,
    onItemSelected: (BottomNavScreen)->Unit
) {
    
    val items = BottomNavScreen.Items.list
    
    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(7.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        
        items.forEach { item  -> 
            CustomBottomNavItem(item = item, isSelected = item.route == currentScreenRoute) {
                onItemSelected(item)
            }
        }

    }

}

@Composable
fun CustomBottomNavItem(item: BottomNavScreen, isSelected: Boolean, onClick: ()->Unit) {

    val background = if (isSelected) MaterialTheme.colors.primary else Color.Transparent
    val contentColor = if (isSelected) Color.White else MaterialTheme.colors.onBackground

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = onClick)
    ) {

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Icon(imageVector = item.icon, contentDescription = null, tint = contentColor)
            
            AnimatedVisibility(visible = isSelected) {
                Text(text = item.title, color = contentColor)
            }
        }

    }

}

