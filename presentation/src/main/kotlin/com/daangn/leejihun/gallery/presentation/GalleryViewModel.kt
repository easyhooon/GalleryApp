package com.daangn.leejihun.gallery.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.daangn.leejihun.gallery.domain.usecase.GetTrackListUseCase
import com.daangn.leejihun.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    getTrackListUseCase: GetTrackListUseCase,
) : ViewModel() {
    val photoList = getTrackListUseCase()
        .map { pagingData ->
            pagingData.map { photo ->
                photo.toUiModel()
            }
        }
        .cachedIn(viewModelScope)
}
