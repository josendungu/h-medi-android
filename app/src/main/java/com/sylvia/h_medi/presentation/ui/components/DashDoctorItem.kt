package com.sylvia.h_medi.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.sylvia.h_medi.common.Constants
import com.sylvia.h_medi.common.utils.loadPicture
import com.sylvia.h_medi.domain.model.Doctor
import com.sylvia.h_medi.presentation.ui.theme.MyBlue
import com.sylvia.h_medi.presentation.ui.theme.Typography

@Composable
fun DashDoctorItem(
    modifier: Modifier,
    doctor: Doctor
){

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        backgroundColor = MyBlue,
        elevation = 7.dp,
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,

        ) {

            Column (
                modifier = Modifier.padding(20.dp).align(CenterVertically),
            ){

                Text(
                    text = "Dr.",
                    style = Typography.h6
                )

                Text(
                    text = doctor.firstName,
                    style = Typography.h2

                )

                Text(
                    text = doctor.lastName,
                    style = Typography.h2

                )

                Spacer(modifier = Modifier.padding(7.dp))

                Text(
                    text = doctor.specialist,
                    style = Typography.h5
                )

            }

            val image = loadPicture(
                url = doctor.imageUrl,
                defaultImage = Constants.DEFAULT_IMAGE
            ).value


            image?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "",
                    modifier = Modifier
                        .height(200.dp),
                    contentScale = ContentScale.FillBounds
                )
            }


        }


    }


}
