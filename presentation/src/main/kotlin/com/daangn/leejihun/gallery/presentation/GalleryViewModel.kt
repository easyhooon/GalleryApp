package com.daangn.leejihun.gallery.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.daangn.leejihun.gallery.domain.usecase.GetTrackListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    getTrackListUseCase: GetTrackListUseCase,
): ViewModel() {
    val photoList = getTrackListUseCase().cachedIn(viewModelScope)
}
