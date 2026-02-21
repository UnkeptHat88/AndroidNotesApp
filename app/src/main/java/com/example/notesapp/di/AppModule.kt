package com.example.notesapp.di

import android.app.Application
import androidx.room.Room
import com.example.notesapp.featurenote.data.datasource.NoteDatabase
import com.example.notesapp.featurenote.data.repository.NoteRepositoryImpl
import com.example.notesapp.featurenote.domain.repository.NoteRepository
import com.example.notesapp.featurenote.domain.usecases.DeleteNoteUseCase
import com.example.notesapp.featurenote.domain.usecases.GetNotesUseCase
import com.example.notesapp.featurenote.domain.usecases.AddNoteUseCase
import com.example.notesapp.featurenote.domain.usecases.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNotesUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository)
        )
    }
}