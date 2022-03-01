package co.icanteach.apps.android.composenotes.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.icanteach.apps.android.composenotes.data.Note
import co.icanteach.apps.android.composenotes.detail.domain.CreateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val createNoteUseCase: CreateNoteUseCase,
) : ViewModel() {

    private val _noteContent = mutableStateOf(NoteTextFieldState(
        hint = "Enter some content"
    ))

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val noteContent: State<NoteTextFieldState> = _noteContent

    fun onEvent(event: DetailPageEvent) {
        when (event) {

            is DetailPageEvent.EnteredContent -> {
                _noteContent.value = _noteContent.value.copy(
                    text = event.value
                )
            }
            is DetailPageEvent.ChangeContentFocus -> {
                _noteContent.value = _noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _noteContent.value.text.isBlank()
                )
            }

            is DetailPageEvent.SaveNote -> {
                saveNote()
            }

            is DetailPageEvent.DeleteNote -> {

            }
        }
    }

    private fun saveNote() {
        viewModelScope.launch {
            try {
                createNoteUseCase.createNote(
                    Note(
                        content = noteContent.value.text,
                        timestamp = System.currentTimeMillis(),
                        id = 1
                    )
                )
                _eventFlow.emit(UiEvent.SaveNote)
            } catch (e: Exception) {
                _eventFlow.emit(
                    UiEvent.ShowError(
                        message = e.message ?: "Couldn't save note"
                    )
                )
            }
        }
    }

    sealed class UiEvent {
        data class ShowError(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }
}
