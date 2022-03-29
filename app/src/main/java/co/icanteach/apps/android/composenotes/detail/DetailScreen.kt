package co.icanteach.apps.android.composenotes.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.icanteach.apps.android.composenotes.R
import co.icanteach.apps.android.composenotes.util.DateFormatter
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel(),
) {

    val pageState = viewModel.pageState.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is DetailViewModel.UiEvent.ShowError -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is DetailViewModel.UiEvent.ClosePage -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        topBar = { DetailScreenTopBar(navController = navController) },
        bottomBar = { DetailScreenBottomBar(pageState = pageState, viewModel = viewModel) },
        scaffoldState = scaffoldState
    ) {
        DetailScreenBody(pageState = pageState, viewModel = viewModel)
    }
}

@Composable
fun DetailScreenTopBar(
    navController: NavController,
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(id = R.string.app_name),
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    )
}

@Composable
fun DetailScreenBottomBar(
    pageState: DetailPageViewState,
    viewModel: DetailViewModel,
) {
    BottomAppBar(
        modifier = Modifier.height(46.dp),
        backgroundColor = MaterialTheme.colors.background,
        contentPadding = PaddingValues(4.dp, 4.dp),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                val lastUpdatedText =
                    stringResource(
                        R.string.last_updated_date_desc,
                        DateFormatter.getFormattedDate(
                            pageState.note.timestamp,
                            DateFormatter.Format.DAY_HOUR_FORMAT
                        )
                    )
                Text(
                    text = lastUpdatedText,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.overline,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                DetailScreenBottomBarIcons(viewModel = viewModel)
            }
        }
    )
}

@Composable
fun DetailScreenBottomBarIcons(
    viewModel: DetailViewModel,
) {
    Row(horizontalArrangement = Arrangement.End) {
        IconButton(onClick = { viewModel.onEvent(DetailPageEvent.SaveNote) }) {
            Icon(
                painterResource(id = R.drawable.ic_check),
                contentDescription = stringResource(id = R.string.app_name),
            )
        }
        IconButton(onClick = { viewModel.onEvent(DetailPageEvent.DeleteNote) }) {
            Icon(
                painterResource(id = R.drawable.ic_trash),
                contentDescription = stringResource(id = R.string.app_name),
            )
        }
    }
}

@Composable
fun DetailScreenBody(
    pageState: DetailPageViewState,
    viewModel: DetailViewModel,
) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = pageState.note.content,
            onValueChange = { newText ->
                viewModel.onEvent(DetailPageEvent.EnteredContent(newText))
            },
            textStyle = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .weight(weight = 1f, fill = true)
                .fillMaxSize()
                .padding(14.dp, 3.dp, 14.dp, 50.dp)
        )
    }
}