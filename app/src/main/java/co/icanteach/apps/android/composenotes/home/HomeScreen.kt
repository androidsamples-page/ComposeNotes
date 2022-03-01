package co.icanteach.apps.android.composenotes.home

import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.icanteach.apps.android.composenotes.R
import co.icanteach.apps.android.composenotes.Screens

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(id = R.string.app_name)) },
                backgroundColor = MaterialTheme.colors.primary)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text =
                { Text(text = "NEW NOTE") },
                icon = { Icon(Icons.Filled.Add, "") },
                onClick = {
                    navController.navigate(Screens.DetailScreen.route)
                })
        }
    ) {
    }
}
