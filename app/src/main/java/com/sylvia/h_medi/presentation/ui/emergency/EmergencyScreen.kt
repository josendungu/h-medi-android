package com.sylvia.h_medi.presentation.ui.emergency

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.sylvia.h_medi.R
import com.sylvia.h_medi.presentation.ui.theme.GreyLine
import com.sylvia.h_medi.presentation.ui.theme.HMediTheme
import com.sylvia.h_medi.presentation.ui.theme.MyBlue
import com.sylvia.h_medi.presentation.ui.theme.Typography

@Composable
fun EmergencyScreen(
    viewModel: EmergencyViewModel = hiltViewModel()
) {

    val state = viewModel.state
    var passwordVisibility by remember { mutableStateOf(false) }

    HMediTheme {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {


            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {
                val (logoContent, mainContent) = createRefs()


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .constrainAs(logoContent) {
                            top.linkTo(parent.top, margin = 20.dp)
                            start.linkTo(parent.start, margin = 10.dp)
                            end.linkTo(parent.end, margin = 10.dp)
                            width = Dimension.fillToConstraints
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painterResource(R.drawable.ic_logo),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(MyBlue),
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp)
                    )

                    Text(
                        text = "H-Medi",
                        style = Typography.h2,
                        color = MyBlue
                    )
                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(mainContent) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start, margin = 40.dp)
                            end.linkTo(parent.end, margin = 40.dp)
                            width = Dimension.fillToConstraints
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "What kind of emergency are you experiencing and how can we help",
                        style = Typography.h5,
                        color = MyBlue,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.padding(10.dp))


                    OutlinedTextField(
                        value = viewModel.emergencyTypeText.value,
                        onValueChange = { viewModel.setType(it) },
                        label = {
                            Text(
                                text = "Emergency Type",
                                style = Typography.h6
                            )
                        },
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth(),
                        textStyle = Typography.h6,
                    )

                    OutlinedTextField(
                        value = viewModel.emergencyDescriptionText.value,
                        onValueChange = { viewModel.setDescription(it) },
                        label = {
                            Text(
                                text = "Description",
                                style = Typography.h6
                            )
                        },
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth(),
                        textStyle = Typography.h6,
                        maxLines = 10
                    )


                    Spacer(modifier = Modifier.padding(15.dp))

                    Button(
                        onClick = { viewModel.handleSubmitClicked() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Send",
                            style = Typography.h4
                        )
                    }

                }

            }


            if (state.value.validateError.isNotBlank()){
                Snackbar(
                    modifier = Modifier.padding(8.dp)
                ) { Text(text = state.value.validateError) }
            }


            if (state.value.error.isNotBlank()) {
                Snackbar(
                    modifier = Modifier.padding(8.dp)
                ) { Text(text = state.value.error) }
            }
            if (state.value.isLoading) {
                Snackbar(
                    modifier = Modifier.padding(8.dp)
                ) { Text(text = "Loading... Please Wait") }
            }

        }

    }

}