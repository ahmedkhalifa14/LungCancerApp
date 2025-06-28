package com.graduationproject.lungcancerapp.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.graduationproject.lungcancerapp.ui.composable.ProfileCard
import com.ahmedkhalifa.careerlinkapp.composable.SpacerVertical24
import com.graduationproject.lungcancerapp.R
import com.graduationproject.lungcancerapp.data.model.User
import com.graduationproject.lungcancerapp.ui.composable.CircularImage
import com.graduationproject.lungcancerapp.ui.composable.Header
import com.graduationproject.lungcancerapp.ui.graphs.Graph
import com.graduationproject.lungcancerapp.ui.graphs.ProfileGraph
import com.graduationproject.lungcancerapp.ui.viewmodel.auth.AuthViewModel
import com.graduationproject.lungcancerapp.ui.viewmodel.profile.ProfileViewModel
import com.graduationproject.lungcancerapp.ui.viewmodel.settings.SettingsViewModel


@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel()

) {
    val state = profileViewModel.userState.collectAsState()
    ProfileScreenContent(
        state.value.user,
        onClickEditProfile = {
            navController.navigate(ProfileGraph.EditProfile.route)
        },
        onClickHistory = {
        },
        onClickNotificationSettings = {
        },
        onClickSavedDoctors = {
        },
        onClickLogOut = {
            authViewModel.logout()
            settingsViewModel.setUserLogin(false)
            navController.navigate(Graph.AUTHENTICATION) {
                popUpTo(Graph.HOME) {
                    inclusive = true
                }
            }

            navController.addOnDestinationChangedListener { _, destination, _ ->
            }


        }

    )
}

@Composable
fun ProfileScreenContent(
    state: User,
    onClickEditProfile: () -> Unit = {},
    onClickHistory: () -> Unit = {},
    onClickNotificationSettings: () -> Unit = {},
    onClickSavedDoctors: () -> Unit = {},
    onClickLogOut: () -> Unit = {}
) {
    val screenBackgroundColor = MaterialTheme.colorScheme.background
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(screenBackgroundColor)
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        CircularImage(imageUrl = state.profilePictureLink)

        SpacerVertical24()
        Header(
            title = state.firstName + " " + state.lastName,
            subTitle = state.email
        )
        SpacerVertical24()
        ProfileCard(
            painter = painterResource(R.drawable.profile_filled_icon),
            cardTitle = stringResource(R.string.edit_profile),
            onClick = {
                onClickEditProfile()
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ProfileCard(
            painter = painterResource(R.drawable.application_ic),
            cardTitle = stringResource(R.string.history),
            onClick = {
                onClickHistory()
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        ProfileCard(
            painter = painterResource(R.drawable.setting_ic),
            cardTitle = stringResource(R.string.notification_settings),
            onClick = {
                onClickNotificationSettings()
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ProfileCard(
            painter = painterResource(R.drawable.favourite_save_icon),
            cardTitle = stringResource(R.string.saved_doctors),
            onClick = {
                onClickSavedDoctors()
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        ProfileCard(
            painter = painterResource(R.drawable.logout_icon),
            cardTitle = stringResource(R.string.log_out),
            onClick = {
                onClickLogOut()
            }
        )
        Spacer(modifier = Modifier.height(56.dp))


    }
}
