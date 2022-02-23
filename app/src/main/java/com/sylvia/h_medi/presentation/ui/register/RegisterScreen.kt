package com.sylvia.h_medi.presentation.ui.register

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
import com.sylvia.h_medi.presentation.ui.theme.MyBlue
import com.sylvia.h_medi.presentation.ui.theme.GreyLine
import com.sylvia.h_medi.presentation.ui.theme.HMediTheme
import com.sylvia.h_medi.presentation.ui.theme.Typography

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel()
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
                val (logoContent, mainContent, loginContent) = createRefs()


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
                        text = "Please fill in the details below to register",
                        style = Typography.h5,
                        color = MyBlue,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.padding(10.dp))


                    OutlinedTextField(
                        value = viewModel.firstNameText.value,
                        onValueChange = { viewModel.setFirstName(it) },
                        label = {
                            Text(
                                text = "First Name",
                                style = Typography.h5
                            )
                        },
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth(),
                        textStyle = Typography.h5,
                    )

                    OutlinedTextField(
                        value = viewModel.lastNameText.value,
                        onValueChange = { viewModel.setLastName(it) },
                        label = {
                            Text(
                                text = "Last Name",
                                style = Typography.h5
                            )
                        },
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth(),
                        textStyle = Typography.h5,
                    )

                    OutlinedTextField(
                        value = viewModel.phoneNumberText.value,
                        onValueChange = { viewModel.setPhoneNumber(it) },
                        label = {
                            Text(
                                text = "Phone Number",
                                style = Typography.h5
                            )
                        },
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth(),
                        textStyle = Typography.h5,
                        placeholder = {
                            Text(
                                text = "254xxxxxxxxx",
                                style = Typography.h5
                            )
                        }
                    )

                    OutlinedTextField(
                        value = viewModel.genderText.value,
                        onValueChange = { viewModel.setGender(it) },
                        label = {
                            Text(
                                text = "Gender",
                                style = Typography.h5
                            )
                        },
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth(),
                        textStyle = Typography.h5
                    )

                    OutlinedTextField(
                        value = viewModel.passwordText.value,
                        onValueChange = { viewModel.setPassword(it) },
                        label = {
                            Text(
                                text = "Password",
                                style = Typography.h5
                            )
                        },
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth(),
                        textStyle = Typography.h5,
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        singleLine = true,
                        trailingIcon = {
                            val image = if (passwordVisibility)
                                painterResource(R.drawable.ic_visibility)
                            else
                                painterResource(R.drawable.ic_visibility_off)


                            IconButton(onClick = {
                                passwordVisibility = !passwordVisibility
                            }) {
                                Icon(painter = image, "")
                            }
                        }
                    )

                    OutlinedTextField(
                        value = viewModel.confirmPasswordText.value,
                        onValueChange = { viewModel.setConfirmPassword(it) },
                        label = {
                            Text(
                                text = "Confirm Password",
                                style = Typography.h5
                            )
                        },
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth(),
                        textStyle = Typography.h5,
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        singleLine = true,
                        trailingIcon = {
                            val image = if (passwordVisibility)
                                painterResource(R.drawable.ic_visibility)
                            else
                                painterResource(R.drawable.ic_visibility_off)


                            IconButton(onClick = {
                                passwordVisibility = !passwordVisibility
                            }) {
                                Icon(painter = image, "")
                            }
                        }
                    )

                    Spacer(modifier = Modifier.padding(15.dp))

                    Button(
                        onClick = { viewModel.onRegisterButtonClicked() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Register",
                            style = Typography.h4
                        )
                    }

                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(loginContent) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start, margin = 10.dp)
                            end.linkTo(parent.end, margin = 10.dp)
                        }
                        .clickable {
                            viewModel.navigateToLogin()
                        },
                    verticalArrangement = Arrangement.Center
                ) {

                    Divider(color = GreyLine, thickness = 1.dp)

                    Text(
                        text = "Already a member? Click to login.",
                        style = Typography.h5,
                        modifier = Modifier
                            .padding(7.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center
                    )

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