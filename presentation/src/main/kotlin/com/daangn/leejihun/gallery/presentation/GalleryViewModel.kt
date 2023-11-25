@file:OptIn(ExperimentalCoroutinesApi::class)

package com.daangn.leejihun.gallery.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ItemSnapshotList
import androidx.paging.cachedIn
import androidx.paging.map
import com.daangn.leejihun.gallery.domain.usecase.GetPhotoListUseCase
import com.daangn.leejihun.gallery.presentation.mapper.toUiModel
import com.daangn.leejihun.gallery.presentation.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

data class GalleryUiState(
    val isSearchVisible: Boolean = false,
    val currentPhotoListSnapshot: List<Photo>? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null,
)

sealed interface GalleryUiEvent {
    data class OnNavigateDetail(val photo: Photo) : GalleryUiEvent
}

@HiltViewModel
class GalleryViewModel @Inject constructor(
    getPhotoListUseCase: GetPhotoListUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(GalleryUiState())
    val uiState: StateFlow<GalleryUiState> = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<GalleryUiEvent>()
    val eventFlow: SharedFlow<GalleryUiEvent> = _eventFlow.asSharedFlow()

    val photoList = getPhotoListUseCase()
        .map { pagingData ->
            pagingData.map { photo ->
                photo.toUiModel()
            }
        }
        .cachedIn(viewModelScope)

    fun onNavigateDetail(photo: Photo) {
        viewModelScope.launch {
            _eventFlow.emit(GalleryUiEvent.OnNavigateDetail(photo = photo))
        }
    }

    fun toggleSearchVisibility() {
        _uiState.update {
            it.copy(isSearchVisible = !_uiState.value.isSearchVisible)
        }
    }

    fun getCurrentPhotoListSnapshot(photoListSnapshot: ItemSnapshotList<Photo>) {
        _uiState.update {
            it.copy(currentPhotoListSnapshot = photoListSnapshot.items)
        }
    }
}
