package co.icanteach.apps.android.composenotes.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.icanteach.apps.android.composenotes.R
import co.icanteach.apps.android.composenotes.Screens
import co.icanteach.apps.android.composenotes.data.Note
import co.icanteach.apps.android.composenotes.util.DateFormatter


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
) {

    val notesState = viewModel.pageState.value

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
        LazyColumn(content = {
            item {
                StaggeredVerticalGrid(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                    maxColumnWidth = 280.dp,
                ) {
                    notesState.notes.forEachIndexed { index, keepNote ->
                        NoteListItem(
                            note = keepNote,
                            onNoteClicked = { noteId ->
                                navController.navigate(
                                    Screens.DetailScreen.route +
                                            "?noteId=${noteId}"
                                )
                            }
                        )
                    }
                }
            }
        })
    }
}

@Composable
fun NoteListItem(
    note: Note,
    onNoteClicked: (Int?) -> Unit,
) {
    val shape = RoundedCornerShape(12.dp)
    Surface(
        shape = shape,
        color = colorResource(
            id = note.color
        ),
        elevation = 0.dp,
        modifier = Modifier
            .padding(bottom = 8.dp, end = 4.dp, start = 4.dp)
            .clip(shape)
            .clickable {
                onNoteClicked(note.id)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {

            Text(
                text = note.content,
                style = MaterialTheme.typography.body2,
            )

            val lastUpdatedText =
                stringResource(R.string.last_updated_date_desc,
                    DateFormatter.getFormattedDate(note.timestamp))

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = lastUpdatedText,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(bottom = 8.dp),
                color = Color.Gray
            )
        }
    }
}