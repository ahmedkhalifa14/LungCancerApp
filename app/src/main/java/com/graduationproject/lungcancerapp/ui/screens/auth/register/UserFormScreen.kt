package com.graduationproject.lungcancerapp.ui.screens.auth.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.graduationproject.lungcancerapp.R
import com.graduationproject.lungcancerapp.common.utils.EventObserver
import com.graduationproject.lungcancerapp.common.utils.Resource
import com.graduationproject.lungcancerapp.data.model.User
import com.graduationproject.lungcancerapp.ui.composable.CustomBtn
import com.graduationproject.lungcancerapp.ui.composable.CustomTextField
import com.graduationproject.lungcancerapp.ui.graphs.AuthScreen
import com.graduationproject.lungcancerapp.ui.viewmodel.auth.AuthViewModel
import java.util.UUID

@Composable
fun UserFormScreen(
    navHostController: NavHostController,
    backStackEntry: NavBackStackEntry,
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val registerState = authViewModel.registerState.collectAsState()
    val saveUserState = authViewModel.saveUserDataState.collectAsState()
    val context = LocalContext.current

    var isLoading by remember { mutableStateOf(false) }

    val userEmail = backStackEntry.arguments?.getString("userEmail")
    val userPassword = backStackEntry.arguments?.getString("userPassword")

    var userFormData by remember { mutableStateOf<User?>(null) }

    LaunchedEffect(registerState.value) {
        registerState.value.getContentIfNotHandled()?.let { resource ->
            when (resource) {
                is Resource.Loading -> {
                    isLoading = true
                }
                is Resource.Success -> {
                    isLoading =false
                    Toast.makeText(context, "Register success", Toast.LENGTH_SHORT).show()
                    val uid = FirebaseAuth.getInstance().currentUser?.uid ?: ""
                    userFormData?.let { user ->
                        val updatedUser = user.copy(userId = uid)
                        authViewModel.saveUserData(updatedUser)
                    }
                }
                is Resource.Error -> {
                    isLoading = false
                    Toast.makeText(context, resource.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
    }

    val saveUserProfileEventObserver = EventObserver<Unit>(
        onLoading = {
            isLoading = true
        },
        onSuccess = {
            isLoading = false
            Toast.makeText(context, "User data saved successfully", Toast.LENGTH_SHORT).show()
            navHostController.navigate(AuthScreen.Login.route) {
                popUpTo(AuthScreen.UserForm.route) { inclusive = true }
            }
        },
        onError = { errorMessage ->
            isLoading = false
            Toast.makeText(context, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
        }
    )

    LaunchedEffect(saveUserState.value) {
        saveUserProfileEventObserver.emit(saveUserState.value)
    }

    UserFormScreenContent(
        isLoading = isLoading,
        onSaveClick = { user ->
            userFormData = user
            authViewModel.register(email = user.email, password = userPassword ?: "")
        },
        userEmail = userEmail ?: "",
    )
}


@Composable
fun UserFormScreenContent(
    isLoading: Boolean,
    onSaveClick: (User) -> Unit,
    userEmail: String = "",
)  {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }
    var profilePictureLink by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val localContext = LocalContext.current
    val firstNameFocusRequester = remember { FocusRequester() }
    val lastnameFocusRequester = remember { FocusRequester() }
    val phoneNumberFocusRequester = remember { FocusRequester() }
    val locationFocusRequester = remember { FocusRequester() }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.complete_your_profile),
            style = MaterialTheme.typography.headlineSmall
        )

        // First Name Text Field
        CustomTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = stringResource(R.string.first_name),
            placeholder = stringResource(R.string.enter_your_first_name),
            icon = Icons.Default.Person,
            focusRequester = firstNameFocusRequester,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    lastnameFocusRequester.requestFocus()
                }
            )
        )

        // Last Name Text Field
        CustomTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = stringResource(R.string.last_name),
            placeholder = stringResource(R.string.enter_your_last_name),
            icon = Icons.Default.Person,
            focusRequester = lastnameFocusRequester,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    phoneNumberFocusRequester.requestFocus()
                }
            )
        )

        // Email Text Field
        CustomTextField(
            value = userEmail,
            onValueChange = { email = it },
            label = stringResource(R.string.email),
            placeholder = stringResource(R.string.enter_your_email_address),
            icon = Icons.Default.Email,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                }
            )
        )

        // Phone Number Text Field
        CustomTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = stringResource(R.string.phone_number),
            placeholder = stringResource(R.string.enter_your_phone_number),
            icon = Icons.Default.Phone,
            focusRequester = phoneNumberFocusRequester,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    locationFocusRequester.requestFocus()
                }
            )
        )

        CustomTextField(
            value = location,
            onValueChange = { location = it },
            label = stringResource(R.string.location),
            placeholder = stringResource(R.string.enter_your_location),
            icon = Icons.Default.LocationOn,
            focusRequester = locationFocusRequester,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        CustomBtn(
            text = stringResource(R.string.save),

            onClick = {
                if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || location.isEmpty()) {
                    Toast.makeText(localContext, "Please fill all fields", Toast.LENGTH_SHORT).show()
                } else {
                    onSaveClick(
                        User(
                            firstName = firstName,
                            lastName = lastName,
                            email = userEmail,
                            phoneNumber = phoneNumber,
                            location = location,
                            profilePictureLink = profilePictureLink,
                            userId = UUID.randomUUID().toString(),
                            joinedAt = System.currentTimeMillis()
                        )
                    )
                }
            }
        )
    }
}



