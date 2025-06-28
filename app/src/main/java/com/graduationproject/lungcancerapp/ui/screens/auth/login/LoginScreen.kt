package com.graduationproject.lungcancerapp.ui.screens.auth.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.graduationproject.lungcancerapp.R
import com.graduationproject.lungcancerapp.common.utils.Event
import com.graduationproject.lungcancerapp.common.utils.Resource
import com.graduationproject.lungcancerapp.ui.composable.CustomBtn
import com.graduationproject.lungcancerapp.ui.composable.CustomImageButton
import com.graduationproject.lungcancerapp.ui.composable.CustomTextField
import com.graduationproject.lungcancerapp.ui.composable.PasswordTextField
import com.graduationproject.lungcancerapp.ui.graphs.AuthScreen
import com.graduationproject.lungcancerapp.ui.graphs.Graph
import com.graduationproject.lungcancerapp.ui.theme.FacebookIconColor
import com.graduationproject.lungcancerapp.ui.theme.GoogleIconColor
import com.graduationproject.lungcancerapp.ui.theme.Tajawal
import com.graduationproject.lungcancerapp.ui.viewmodel.auth.AuthViewModel
import com.graduationproject.lungcancerapp.ui.viewmodel.settings.SettingsViewModel
import kotlin.let
import kotlin.text.isNotEmpty

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    val loginState = viewModel.loginState.collectAsState(initial = null)
    val context = LocalContext.current
    LaunchedEffect(loginState.value) {
        loginState.value?.getContentIfNotHandled()?.let { resource ->
            when (resource) {
                is Resource.Success -> {
                    settingsViewModel.setUserLogin(true)
                    navController.navigate(Graph.HOME){
                        popUpTo(AuthScreen.Login.route) {
                            inclusive = true
                        }
                    }

                    Toast.makeText(
                        context,
                        context.getString(R.string.login_successful),
                        Toast.LENGTH_SHORT
                    ).show()

                }

                is Resource.Error -> {
                    Toast.makeText(context, resource.message, Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }
    }
    loginState.value?.let {
        LoginScreenContent(
            onForgetClick = { },
            onClickGoogleAuth = {},
            onClickFacebookAuth = {},
            onClickLogin = { email, password ->
                viewModel.login(email, password)
            },
            loginState = it
        )
    }

}

@Composable
fun LoginScreenContent(
    onForgetClick: () -> Unit,
    onClickGoogleAuth: () -> Unit,
    onClickFacebookAuth: () -> Unit,
    onClickLogin: (String, String) -> Unit,
    loginState: Event<Resource<Unit>>
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)

    ) {

        Text(
            text = stringResource(R.string.login_welcome_back),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontFamily = Tajawal,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = stringResource(R.string.login_subtitle),
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Tajawal,
            color = MaterialTheme.colorScheme.onSurface,
            lineHeight = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = stringResource(R.string.email_address),
            placeholder = stringResource(R.string.enter_your_email_address),
            icon = Icons.Default.Email
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextField(
            value = password,
            onValueChange = { password = it },
            label = stringResource(R.string.password),
            placeholder = stringResource(R.string.enter_your_password),
            icon = Icons.Default.Lock,
            isError = password.isNotEmpty() && password.length < 8,
            supportingText = {
                if (password.isNotEmpty() && password.length < 8) {
                    Text(
                        text = stringResource(R.string.password_must_be_at_least_8_characters_long),
                        color = Color.Red
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.forget_password),
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Tajawal,
            color = MaterialTheme.colorScheme.onSurface,
            lineHeight = 18.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomBtn(text = stringResource(R.string.log_in_c), icon = null) {
            onClickLogin(email, password)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.or_continue_with),
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = Tajawal,
            fontWeight = FontWeight.Normal,
            lineHeight = 18.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            CustomImageButton(
                image = painterResource(id = R.drawable.google),
                backgroundColor = GoogleIconColor,
                onClick = { }
            )
            Spacer(modifier = Modifier.width(16.dp))

            CustomImageButton(
                image = painterResource(id = R.drawable.facebook),
                backgroundColor = FacebookIconColor,
                onClick = {}
            )
        }
        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                text = stringResource(R.string.new_user),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = Tajawal,
                fontWeight = FontWeight.Normal,
                lineHeight = 18.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp)

            )
            Text(
                text = stringResource(R.string.create_account),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = Tajawal,
                fontWeight = FontWeight.Bold,
                lineHeight = 18.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )
        }
        when (val resource = loginState.peekContent()) {
            is Resource.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            is Resource.Error -> {
                Text(
                    text = resource.message ?: stringResource(R.string.an_error_occurred),
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            else -> {}
        }
    }
}


@Composable
@Preview(showSystemUi = true, showBackground = true)
fun PreviewLoginScreen() {
    LoginScreen(navController = rememberNavController())
}


