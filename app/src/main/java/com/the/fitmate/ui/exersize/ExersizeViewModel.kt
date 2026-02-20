package com.the.fitmate.ui.exersize

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.the.fitmate.domain.BicepsBrestUseCase
import com.the.fitmate.domain.Exercise
import com.the.fitmate.domain.ExerciseRepository

import com.the.fitmate.domain.ExercisesUiState
import com.the.fitmate.domain.GetAllExerUseCase
import com.the.fitmate.domain.InsertExerUseCase
import com.the.fitmate.data.InsertExersize
import com.the.fitmate.domain.ShoulderLegsUsaCase
import com.the.fitmate.domain.TricBackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


@HiltViewModel
class ExersizeViewModel @Inject constructor(
    private val getAllExUseCase:GetAllExerUseCase,
    private val bicepsBrestUseCase:BicepsBrestUseCase,
    private val tricBackUseCase:TricBackUseCase,
    private val shoulderLegs:ShoulderLegsUsaCase,
    private val repository: ExerciseRepository,
    private val insertExer: InsertExerUseCase
    ): ViewModel() {


    private val _ui = MutableStateFlow(ExercisesUiState())
    val ui: StateFlow<ExercisesUiState> = _ui

    private var syncJob: Job? = null

    fun refresh(week: Int, workout: Int){
        syncJob = viewModelScope.launch{
            val result = when (workout) {
                1 -> getAllExUseCase.invoke()
                2 -> bicepsBrestUseCase.invoke(week)
                3 -> tricBackUseCase.invoke(week)
                4 -> shoulderLegs.invoke(week)
                else -> emptyList()
            }


            _ui.value = _ui.value.copy(items = result)

        }
    }



    private val _doneExercises = MutableStateFlow<Set<String>>(emptySet())
    val doneExercises = _doneExercises.asStateFlow()

    private val _event = MutableSharedFlow<ExerEvent>()
    val event = _event.asSharedFlow()


    fun markDone(name: String) {
        viewModelScope.launch {

            val user = FirebaseAuth.getInstance().currentUser
            if (user == null) {
                _event.emit(ExerEvent.LoginRequired)
                return@launch
            }

            insertExer(InsertExersize(name = name))

            _doneExercises.value = _doneExercises.value + name

            _event.emit(ExerEvent.Saved)
        }
    }

    }