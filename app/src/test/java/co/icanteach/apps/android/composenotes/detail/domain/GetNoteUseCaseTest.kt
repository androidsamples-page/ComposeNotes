package co.icanteach.apps.android.composenotes.detail.domain

import co.icanteach.apps.android.composenotes.data.NoteMapper
import co.icanteach.apps.android.composenotes.data.NoteRepositoryImpl
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class GetNoteUseCaseTest {

    private lateinit var useCase: GetNoteUseCase

    @MockK
    lateinit var repository: NoteRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetNoteUseCase(repository = repository, mapper = NoteMapper())

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given no result from repository, then should return Default Note`() {

        runTest {

            // Given
            coEvery { repository.getNoteById(any()) } returns null

            // When
            val actualResult = useCase.getNote(10)

            // Then
            Truth.assertThat(actualResult.content).isEmpty()

        }
    }
}