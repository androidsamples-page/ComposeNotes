package co.icanteach.apps.android.composenotes.detail.domain

import co.icanteach.apps.android.composenotes.data.ColorGenerator
import co.icanteach.apps.android.composenotes.data.Note
import co.icanteach.apps.android.composenotes.data.NoteMapper
import co.icanteach.apps.android.composenotes.data.NoteRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CreateNoteUseCaseTest {


    private lateinit var useCase: CreateNoteUseCase

    @MockK
    lateinit var repository: NoteRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = CreateNoteUseCase(repository, NoteMapper())

    }

    @Test(expected = IllegalNoteException::class)
    fun `given blank content, should throw IllegalNoteException`() {
        runTest {
            //  Given
            val givenNote =
                Note(color = ColorGenerator.getColor(), content = "", timestamp = 12312312312L)

            // When
            useCase.createNote(givenNote)
        }
    }

    @Test(expected = IllegalNoteException::class)
    fun `given blank content, should not call NoteRepository`() = runTest {

        //  Given
        val givenNote =
            Note(color = ColorGenerator.getColor(), content = "", timestamp = 12312312312L)

        coEvery { repository.insertNote(any()) } just runs

        // When
        useCase.createNote(givenNote)

        coVerify(exactly = 0) { repository.insertNote(any()) }
    }

    @Test
    fun `given proper content, should call NoteRepository`() {
        runTest {
            //  Given
            val givenNote =
                Note(color = ColorGenerator.getColor(),
                    content = "Lorem Ipsum",
                    timestamp = 12312312312L)

            coEvery { repository.insertNote(any()) } just runs

            // When
            useCase.createNote(givenNote)


            coVerify(exactly = 1) { repository.insertNote(any()) }
        }
    }
}