package com.daangn.leejihun.gallery.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.daangn.leejihun.gallery.domain.usecase.GetPhotoListUseCase
import com.daangn.leejihun.gallery.domain.usecase.SaveImageFileUseCase
import com.daangn.leejihun.gallery.presentation.mapper.toUiModel
import com.daangn.leejihun.gallery.presentation.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUiState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
)

sealed interface DetailUiEvent {
    data class ShowToast(val message: UiText) : DetailUiEvent
}

@HiltViewModel
class GalleryViewModel @Inject constructor(
    getPhotoListUseCase: GetPhotoListUseCase,
    private val saveImageFileUseCase: SaveImageFileUseCase,
) : ViewModel() {
    private val _detailUiState = MutableStateFlow(DetailUiState())
    val detailUiState: StateFlow<DetailUiState> = _detailUiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<DetailUiEvent>()
    val eventFlow: SharedFlow<DetailUiEvent> = _eventFlow.asSharedFlow()

    val photoList = getPhotoListUseCase()
        .map { pagingData ->
            pagingData.map { photo ->
                photo.toUiModel()
            }
        }
        .cachedIn(viewModelScope)

    fun saveImageFile(fileName: String, byteArray: ByteArray) {
        viewModelScope.launch {
            _detailUiState.update {
                it.copy(isLoading = true)
            }
            saveImageFileUseCase(
                fileName = fileName,
                byteArray = byteArray,
            )
            _eventFlow.emit(DetailUiEvent.ShowToast(UiText.StringResource(R.string.photo_save_complete)))
            _detailUiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}
