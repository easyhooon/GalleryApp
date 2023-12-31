package com.kenshi.gallery.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenshi.gallery.domain.usecase.SaveImageFileUseCase
import com.kenshi.gallery.presentation.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class DetailUiState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
)

sealed interface DetailUiEvent {
    data object OnNavigateBack : DetailUiEvent
    data class ShowToast(val message: UiText) : DetailUiEvent
}

// TODO 컴포저블 함수의 뷰모델의 생명주기 관련 학습
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val saveImageFileUseCase: SaveImageFileUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<DetailUiEvent>()
    val eventFlow: SharedFlow<DetailUiEvent> = _eventFlow.asSharedFlow()

    init {
        Timber.d("DetailViewModel ${this.hashCode()}")
    }

    fun saveImageFile(fileName: String, byteArray: ByteArray) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            saveImageFileUseCase(
                fileName = fileName,
                byteArray = byteArray,
            )
            _eventFlow.emit(DetailUiEvent.ShowToast(UiText.StringResource(R.string.photo_save_complete)))
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun onNavigateBack() {
        viewModelScope.launch {
            _eventFlow.emit(DetailUiEvent.OnNavigateBack)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("DetailViewModel ${this.hashCode()}")
        Timber.d("DetailViewModel cleared")
    }
}
