package com.example.notesapp.featurenote.presentation.notes

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.notesapp.core.util.TestTags
import com.example.notesapp.di.AppModule
import com.example.notesapp.featurenote.presentation.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotesEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()


    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun saveNewNote_editAfterwards() {
        composeRule.onNodeWithContentDescription("Add Note").performClick()

        composeRule.onNodeWithTag(TestTags.ADD_NOTE_TITLE_FIELD).performTextInput("Title text")
        composeRule.onNodeWithTag(TestTags.ADD_NOTE_CONTENT_FIELD).performTextInput("Content text")

        composeRule.onNodeWithContentDescription("Save Note").performClick()

        composeRule.onNodeWithText("Title text").assertIsDisplayed()
        composeRule.onNodeWithText("Title text").performClick()

        composeRule.onNodeWithTag(TestTags.ADD_NOTE_TITLE_FIELD).assertTextEquals("Title text")
        composeRule.onNodeWithTag(TestTags.ADD_NOTE_CONTENT_FIELD).assertTextEquals("Content text")

        composeRule.onNodeWithTag(TestTags.ADD_NOTE_TITLE_FIELD).performClick().performTextInput(" part 2")
        composeRule.onNodeWithTag(TestTags.ADD_NOTE_CONTENT_FIELD).performClick().performTextInput("part 2")

        composeRule.onNodeWithContentDescription("Save Note").performClick()

        composeRule.onNodeWithText("Title text part 2").assertIsDisplayed()
    }
}