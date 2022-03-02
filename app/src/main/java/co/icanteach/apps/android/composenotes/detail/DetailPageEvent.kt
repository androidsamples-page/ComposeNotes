package co.icanteach.apps.android.composenotes.detail

import androidx.compose.ui.focus.FocusState

sealed class DetailPageEvent {
    data class EnteredContent(val value: String) : DetailPageEvent()
    data class ChangeContentFocus(val focusState: FocusState) : DetailPageEvent()
    object SaveNote : DetailPageEvent()
    object DeleteNote : DetailPageEvent()
}