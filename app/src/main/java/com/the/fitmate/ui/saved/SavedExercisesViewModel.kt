package com.the.fitmate.ui.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.the.fitmate.domain.GetSavedExercisesUseCase
import com.the.fitmate.data.InsertExersize
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedExercisesViewModel @Inject constructor(
    private val getSavedExercisesUseCase: GetSavedExercisesUseCase
): ViewModel() {
    val savedExercises = getSavedExercisesUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )


}