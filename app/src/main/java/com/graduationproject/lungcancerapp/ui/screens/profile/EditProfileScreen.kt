package com.graduationproject.lungcancerapp.ui.screens.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.graduationproject.lungcancerapp.ui.composable.ProfileButton
import com.ahmedkhalifa.careerlinkapp.composable.SpacerHorizontal16
import com.ahmedkhalifa.careerlinkapp.composable.SpacerVertical24
import com.google.firebase.auth.FirebaseAuth
import com.graduationproject.lungcancerapp.R
import com.graduationproject.lungcancerapp.common.utils.EventObserver
import com.graduationproject.lungcancerapp.data.model.User
import com.graduationproject.lungcancerapp.ui.composable.Header
import com.graduationproject.lungcancerapp.ui.composable.InformationCard
import com.graduationproject.lungcancerapp.ui.composable.ProfileAvatarView
import com.graduationproject.lungcancerapp.ui.composable.SpacerVertical32
import com.graduationproject.lungcancerapp.ui.composable.TextButton
import com.graduationproject.lungcancerapp.ui.viewmodel.profile.ProfileViewModel

@Composable
fun EditProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    if (auth.currentUser == null) {
        LaunchedEffect(Unit) {
            Toast.makeText(context,
                context.getString(R.string.you_need_to_register_to_use_the_app), Toast.LENGTH_SHORT)
                .show()

        }
        return
    }

    val state = viewModel.userState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.updateUserInfoState.collect(
            EventObserver(
                onLoading = {

                },
                onError = { message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                },
                onSuccess = {
                    Toast.makeText(context,
                        context.getString(R.string.profile_updated_successfully), Toast.LENGTH_SHORT)
                        .show()
                }
            )
        )
    }

    EditProfileContent(
        state = state.value.user,
        onChangeFirstName = viewModel::onChangeFirstName,
        onChangeLastName = viewModel::onChangeLastName,
        onChangeLocation = viewModel::onChangeLocation,
        onChangeEmail = viewModel::onChangeEmail,
        onChangePhone = viewModel::onChangePhone,
        onSaveUserInfo = viewModel::saveUserInformation
    )

}

@Composable
private fun EditProfileContent(
    state: User,
    onChangeFirstName: (String) -> Unit,
    onChangeLastName: (String) -> Unit,
    onChangeLocation: (String) -> Unit,
    onChangeEmail: (String) -> Unit,
    onChangePhone: (String) -> Unit,
    onSaveUserInfo: () -> Unit,
) {
    val screenBackgroundColor = MaterialTheme.colorScheme.background
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(screenBackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(
                title = stringResource(R.string.account),
                subTitle = stringResource(R.string.edit_and_manage_your_account)
            )
            SpacerVertical32()
            ProfileAvatarView(imageUrl = state.profilePictureLink)
            SpacerVertical24()
            TextButton(text = stringResource(R.string.change_profile_picture)){}

            SpacerVertical32()
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1f)) {
                    InformationCard(
                        title = stringResource(R.string.first_name),
                        info = state.firstName,
                        onChangeFirstName
                    )
                }
                SpacerHorizontal16()
                Box(modifier = Modifier.weight(1f)) {
                    InformationCard(
                        title = stringResource(R.string.second_name),
                        info = state.lastName,
                        onChangeLastName
                    )
                }
            }

            InformationCard(
                title = stringResource(R.string.location),
                info = state.location,
                onChangeLocation
            )
            InformationCard(
                title = stringResource(R.string.email),
                info = state.email,
                onChangeEmail
            )
            InformationCard(
                title = stringResource(R.string.phone),
                info = state.phoneNumber,
                onChangePhone
            )

            Spacer(modifier = Modifier.height(32.dp))
            ProfileButton(
                text = stringResource(R.string.save),
                onClick = onSaveUserInfo,
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    EditProfileScreen()
}