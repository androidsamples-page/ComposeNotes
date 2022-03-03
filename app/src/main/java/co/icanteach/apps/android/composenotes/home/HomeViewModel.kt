package co.icanteach.apps.android.composenotes.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.icanteach.apps.android.composenotes.home.domain.FetchHomePageContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNotesUseCase: FetchHomePageContentUseCase,
) : ViewModel() {

    private val _pageState = mutableStateOf(HomePageViewState())
    val pageState: State<HomePageViewState> = _pageState


    init {
        getNotes()
    }

    private fun getNotes() {
        getNotesUseCase.getNotes()
            .onEach { notes ->
                _pageState.value = pageState.value.copy(
                    notes = notes
                )
            }
            .launchIn(viewModelScope)
    }
}