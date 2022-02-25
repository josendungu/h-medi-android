package com.sylvia.h_medi.presentation.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sylvia.h_medi.R
import com.sylvia.h_medi.presentation.ui.theme.HMediTheme
import com.sylvia.h_medi.presentation.ui.theme.MyBlue
import com.sylvia.h_medi.presentation.ui.theme.Typography

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    var passwordVisibility by remember { mutableStateOf(false) }


    HMediTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(20.dp)
        ){

            item {

                Text(
                    text = "Profile",
                    style = Typography.h4
                )


                Text(
                    text = "Personal Information",
                    style = Typography.h4,
                    color = MyBlue,
                    modifier = Modifier.padding(10.dp)
                )


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
                        .padding(horizontal = 10.dp)
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
                        .padding(horizontal = 10.dp)
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
                        .padding(horizontal = 10.dp)
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
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth(),
                    textStyle = Typography.h5
                )

                Spacer(modifier = Modifier.padding(20.dp))

                Text(
                    text = "Password",
                    style = Typography.h4,
                    color = MyBlue,
                    modifier = Modifier.padding(10.dp)
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
                        .padding(horizontal = 10.dp)
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
                        .padding(horizontal = 10.dp)
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
                    onClick = { viewModel.onUpdateButtonClicked() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 50.dp, start = 10.dp, end = 10.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Update",
                        style = Typography.h4
                    )
                }

            }

        }

        if (state.updated) {
            Snackbar(
                modifier = Modifier.padding(8.dp)
            ) { Text(text = "Your profile was successfully updated") }
        }

        if (state.error.isNotBlank()) {
            Snackbar(
                modifier = Modifier.padding(7.dp)
            ) { Text(text = state.error) }
        }

        if (state.validateError.isNotBlank()){
            Snackbar(
                modifier = Modifier.padding(8.dp)
            ) { Text(text = state.validateError) }
        }

        if (state.isLoading){
            Snackbar(
                modifier = Modifier.padding(8.dp)
            ) { Text(text = "Loading... Please wait.") }
        }

    }



}