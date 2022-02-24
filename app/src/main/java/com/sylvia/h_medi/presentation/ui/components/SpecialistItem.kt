package com.sylvia.h_medi.presentation.ui.components


import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sylvia.h_medi.domain.model.Specialist
import com.sylvia.h_medi.presentation.ui.theme.Typography

@Composable
fun SpecialistItem(
    specialist: Specialist,
    modifier: Modifier
) {

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        backgroundColor = Color.White,
        elevation = 5.dp,
    ) {

        Text(
            text = specialist.specialistName,
            modifier = Modifier.padding(20.dp),
            style = Typography.h4
        )

    }


}