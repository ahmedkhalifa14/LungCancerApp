package com.graduationproject.lungcancerapp.ui.screens.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.graduationproject.lungcancerapp.common.utils.Constants.getCategories
import com.graduationproject.lungcancerapp.common.utils.Constants.getDoctorsList
import com.graduationproject.lungcancerapp.data.model.Category
import com.graduationproject.lungcancerapp.ui.composable.CategorySection
import com.graduationproject.lungcancerapp.ui.composable.CategoryStickyHeader
import com.graduationproject.lungcancerapp.ui.composable.DoctorCardSection
import com.graduationproject.lungcancerapp.ui.composable.RecentDoctorSection
import com.graduationproject.lungcancerapp.ui.composable.SearchBar
import com.graduationproject.lungcancerapp.ui.composable.TopBar
import com.graduationproject.lungcancerapp.ui.graphs.Graph

@Composable
fun HomeScreen(
    navigationController: NavController,
    listState: LazyListState,
    isScrollingDown: Boolean
) {
    val context = LocalContext.current
    HomeScreenContent(
        onCategoryCardClick = { item ->
            if (item.id == 1)
                navigationController.navigate(Graph.INITIAL_DIAGNOSIS)
            else
                Toast.makeText(context, "${item.name} feature will coming soon", Toast.LENGTH_LONG)
                    .show()
        }
    )
}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun HomeScreenContent(
    onCategoryCardClick: (Category) -> Unit = {}
) {
    val refreshing = remember { mutableStateOf(false) } // Manage refreshing state
    val pullRefreshState = rememberPullRefreshState(refreshing.value, {
        refreshing.value = true
        refreshing.value = false
    })
    Box(Modifier.pullRefresh(pullRefreshState)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
                .background(MaterialTheme.colorScheme.background)
        ) {
            TopBar()
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                stickyHeader {
                    SearchBar(false, {}, "", {})
                    Spacer(modifier = Modifier.height(16.dp))
                }

                stickyHeader {
                    CategoryStickyHeader("Category")
                }
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        item {
                            CategorySection(
                                categories = getCategories(),
                                isLoading = false,
                                errorMessage = null,
                                onCategoryCardClick = { category ->
                                    Log.d("onCategoryCardClick", "onCategoryCardClicked")
                                    onCategoryCardClick(category)
                                }
                            )
                        }
                    }
                }

                stickyHeader {
                    CategoryStickyHeader("Recommended Doctors")
                }
                item {
                    DoctorCardSection(
                        doctors = getDoctorsList(),
                        isLoading = false,
                        errorMessage = null,
                        onDoctorCardClick = {}
                    )
                }
                stickyHeader {
                    CategoryStickyHeader("Your Recent Doctor")
                }
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        item {
                            RecentDoctorSection(
                                doctors = getDoctorsList(),
                                isLoading = false,
                                errorMessage = null
                            )
                        }
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreenContent()
}