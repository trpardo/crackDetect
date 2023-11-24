package com.pardo.crackdetect.analysis

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pardo.crackdetect.analysis.nav.AnalysisNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val savePhotoToGalleryUseCase: SavePhotoToGalleryUseCase
) : ViewModel() {
    var navigator : AnalysisNavigator? = null
    private val _state : MutableStateFlow<CameraState> = MutableStateFlow(CameraState())
    val state : StateFlow<CameraState> get() = _state

    fun storePhotoInGallery(bitmap: Bitmap) {
        viewModelScope.launch {
            savePhotoToGalleryUseCase.call(bitmap)
            updateCapturedPhotoState(bitmap)
            navigator?.openPhotoSelector()
        }
    }

    private fun updateCapturedPhotoState(updatedPhoto: Bitmap?) {
        _state.value.capturedImage?.recycle()
        _state.value = state.value.copy(capturedImage = updatedPhoto)
    }

    override fun onCleared() {
        _state.value.capturedImage?.recycle()
        super.onCleared()
    }
}

data class CameraState(
    val capturedImage: Bitmap? = null,
)