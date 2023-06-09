package com.example.bookxchange.presentation.info

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    val bookId: Int = savedStateHandle["bookId"]!!
}