@file:OptIn(SavedStateHandleSaveableApi::class)

package com.daangn.leejihun.gallery.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import androidx.paging.ItemSnapshotList
import androidx.paging.cachedIn
import androidx.paging.map
import com.daangn.leejihun.gallery.domain.usecase.GetPhotoListUseCase
import com.daangn.leejihun.gallery.presentation.mapper.toUiModel
import com.daangn.leejihun.gallery.presentation.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

data class GalleryUiState(
    val isSearchVisible: Boolean = false,
    val currentPhotoListSnapshot: ImmutableList<Photo> = persistentListOf(),
    val filteredPhotoList: ImmutableList<Photo> = persistentListOf(),
    val isLoading: Boolean = false,
    val error: Throwable? = null,
)

sealed interface GalleryUiEvent {
    data class OnNavigateDetail(val photo: Photo) : GalleryUiEvent
}

@HiltViewModel
class GalleryViewModel @Inject constructor(
    getPhotoListUseCase: GetPhotoListUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(GalleryUiState())
    val uiState: StateFlow<GalleryUiState> = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<GalleryUiEvent>()
    val eventFlow: SharedFlow<GalleryUiEvent> = _eventFlow.asSharedFlow()

    var searchQuery by savedStateHandle.saveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
        private set

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
            it.copy(
                currentPhotoListSnapshot = photoListSnapshot.items.toImmutableList(),
                filteredPhotoList = photoListSnapshot.items.toImmutableList(),
            )
        }
    }

    fun updateSearchQuery(newSearchQuery: TextFieldValue) {
        searchQuery = newSearchQuery
    }

    fun onSearchQuery(searchQuery: TextFieldValue) {
        if (searchQuery.text.isNotEmpty()) {
            _uiState.update {
                it.copy(
                    filteredPhotoList = _uiState.value.currentPhotoListSnapshot
                        .filter { photo ->
                            photo.author.lowercase(Locale.ROOT).contains(searchQuery.text.lowercase(Locale.ROOT))
                        }
                        .toImmutableList(),
                )
            }
        } else {
            _uiState.update {
                it.copy(filteredPhotoList = _uiState.value.currentPhotoListSnapshot)
            }
        }
    }
}
