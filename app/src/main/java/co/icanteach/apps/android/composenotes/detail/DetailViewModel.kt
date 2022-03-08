package co.icanteach.apps.android.composenotes.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.icanteach.apps.android.composenotes.data.ColorGenerator
import co.icanteach.apps.android.composenotes.data.Note
import co.icanteach.apps.android.composenotes.detail.domain.CreateNoteUseCase
import co.icanteach.apps.android.composenotes.detail.domain.DeleteNoteUseCase
import co.icanteach.apps.android.composenotes.detail.domain.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _pageState = mutableStateOf(DetailPageViewState(note = Note.Default))
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    val pageState: State<DetailPageViewState> = _pageState

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            getNote(noteId)
        }
    }

    private fun getNote(noteId: Int) {
        viewModelScope.launch {
            val result = getNoteUseCase.getNote(noteId)
            _pageState.value = pageState.value.copy(note = result)
        }
    }

    fun onEvent(event: DetailPageEvent) {
        when (event) {

            is DetailPageEvent.EnteredContent -> {
                val oldNote = pageState.value.note
                _pageState.value = pageState.value.copy(
                    note = oldNote.copy(content = event.value)
                )
            }
            is DetailPageEvent.ChangeContentFocus -> {
            }

            is DetailPageEvent.SaveNote -> {
                saveNote()
            }

            is DetailPageEvent.DeleteNote -> {
                deleteNote()
            }
        }
    }

    private fun deleteNote() {
        viewModelScope.launch {
            try {
                deleteNoteUseCase.deleteNote(note = pageState.value.note)
                _eventFlow.emit(UiEvent.ClosePage)
            } catch (e: Exception) {
                _eventFlow.emit(
                    UiEvent.ShowError(
                        message = e.message ?: "Couldn't save note"
                    )
                )
            }
        }
    }

    private fun saveNote() {
        viewModelScope.launch {
            try {
                createNoteUseCase.createNote(
                    note = pageState.value.note.copy(
                        content = pageState.value.note.content,
                        timestamp = System.currentTimeMillis(),
                        color = ColorGenerator.getColor()
                    )
                )
                _eventFlow.emit(UiEvent.ClosePage)
            } catch (e: Exception) {
            }
        }
    }

    sealed class UiEvent {
        data class ShowError(val message: String) : UiEvent()
        object ClosePage : UiEvent()
    }
}
