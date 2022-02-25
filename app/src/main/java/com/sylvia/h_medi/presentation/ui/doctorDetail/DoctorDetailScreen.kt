package com.sylvia.h_medi.presentation.ui.doctorDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sylvia.h_medi.common.Constants
import com.sylvia.h_medi.common.utils.loadPicture
import com.sylvia.h_medi.presentation.ui.theme.*


@Composable
fun DoctorDetailScreen(
    viewModel: DoctorDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val doctor = viewModel.currentDoctor.value

    HMediTheme {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MyBlue)
        ) {


            if (viewModel.currentDoctor.value != null) {
                val image = loadPicture(
                    url = viewModel.currentDoctor.value!!.imageUrl,
                    defaultImage = Constants.DEFAULT_IMAGE
                ).value

                Column {
                    image?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = "",
                            modifier = Modifier
                                .height(350.dp)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Fit
                        )
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(y = 10.dp),
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp
                        ),
                        backgroundColor = Color.White,
                        elevation = 7.dp,

                    ) {

                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(20.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 5.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    text = "Dr. ${doctor!!.firstName}  ${doctor.lastName}",
                                    style = Typography.h2,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold
                                )


                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {

                                    IconButton(
                                        onClick = {viewModel.emailToClipBoard()},
                                        modifier = Modifier.alpha(ContentAlpha.medium)
                                    ) {
                                        Icon(
                                            Icons.Outlined.Email,
                                            contentDescription = "Search Icon",
                                            tint = Color.Black
                                        )
                                    }

                                    IconButton(
                                        onClick = {viewModel.phoneToClipBoard()},
                                        modifier = Modifier.alpha(ContentAlpha.medium)
                                    ) {
                                        Icon(
                                            Icons.Outlined.Phone,
                                            contentDescription = "Search Icon",
                                            tint = Color.Black
                                        )
                                    }

                                }

                            }

                            Text(
                                text = doctor!!.specialist,
                                style = Typography.h5
                            )


                            Rating(rating = doctor.rating)

                            Divider(
                                color = GreyLine,
                                thickness = 1.dp,
                                modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)
                            )

                            Text(
                                text = "About",
                                style = Typography.h4,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Text(
                                text = doctor.about,
                                style = Typography.body1,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Button(
                                onClick = { viewModel.makeAppointment() },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 30.dp)
                                    .height(50.dp)
                            ) {
                                Text(
                                    text = "Book Appointment",
                                    style = Typography.h4
                                )
                            }
                        }

                    }
                }



            }


            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }


            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

        }
    }

}


@Composable
fun Rating(rating: Int)  {

    Row(modifier = Modifier.fillMaxWidth()) {

        for (i in 1..5){
            if (rating >= i){
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Search Icon",
                    tint = MyBlue,
                    modifier = Modifier
                        .width(13.dp)
                        .height(13.dp)
                )
            } else {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Search Icon",
                    tint = GreyText,
                    modifier = Modifier
                        .width(13.dp)
                        .height(13.dp)
                )
            }
        }
    }

}

