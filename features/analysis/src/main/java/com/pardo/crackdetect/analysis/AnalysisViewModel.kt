package com.pardo.crackdetect.analysis

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pardo.crackdetct.data.analysis.AnalysisUseCase
import com.pardo.crackdetct.data.analysis.CrackAnalysisDomainModel
import com.pardo.crackdetect.analysis.nav.AnalysisDirections
import com.pardo.crackdetect.analysis.nav.AnalysisNavigator
import com.pardo.crackdetect.core.network.Failure
import com.pardo.crackdetect.core.network.fold
import com.pardo.crackdetect.core.utils.State
import com.pardo.crackdetect.core.utils.ViewState
import com.pardo.crackdetect.core.utils.toBase64
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisViewModel @Inject constructor(
    private val analysisUseCase: AnalysisUseCase
) : ViewModel() {
    var navigator: AnalysisNavigator? = null

    private val _viewState: MutableStateFlow<AnalysisViewState> =
        MutableStateFlow(AnalysisViewState.Initial)
    val viewState: StateFlow<AnalysisViewState> get() = _viewState

    fun saveImage(bitmap: Bitmap?) {
        bitmap?.let {
            val updatedForm = viewState.value.form.copy(
                image = bitmap
            )
            _viewState.value = AnalysisViewState.CaptureImage(updatedForm)
        }
    }

    fun startAnalyseImage() {
        _viewState.value = AnalysisViewState.AnalyseImage(form = viewState.value.form)
        navigator?.openResult(popUpToRoute = AnalysisDirections.Analysis.route)
    }

    fun analyseImage() {
        _viewState.value = AnalysisViewState.Loading(form = viewState.value.form)
        viewModelScope.launch {
            analysisUseCase.analyseImage(viewState.value.form.imageBase64).fold(
                foldFailure = ::onAnalysisImageFailure,
                foldSuccess = ::onAnalysisImageSuccess
            )
        }
    }

    private fun onAnalysisImageSuccess(response: CrackAnalysisDomainModel) {
        _viewState.value = AnalysisViewState.ResultSuccess(form = viewState.value.form)
    }

    private fun onAnalysisImageFailure(error: Failure) {
        _viewState.value = AnalysisViewState.Error(
            form = viewState.value.form,
            message = error.cause
        )
    }
}

private val placeHolderBitmap: Bitmap = BitmapFactory.decodeResource(
    Resources.getSystem(),
    android.R.drawable.ic_menu_camera
)

data class AnalysisForm(
    private val image: Bitmap = placeHolderBitmap,
    private val analyisis: CrackAnalysisDomainModel? = null
) {
    val imageBitmap: ImageBitmap get() = image.asImageBitmap()
    val imageBase64: String get() = image.toBase64()

    val type: String get() = analyisis?.type ?: ""
    val severity: String get() = analyisis?.type ?: ""
    val description: String get() = "esto será la descripción"
}

sealed class AnalysisViewState(
    val form: AnalysisForm = AnalysisForm(),
    state: State<Boolean>
) : ViewState<Boolean>(state = state) {

    object Initial : AnalysisViewState(state = State.Initial())

    class CaptureImage(form: AnalysisForm) : AnalysisViewState(form = form, state = State.Active())

    class AnalyseImage(form: AnalysisForm) : AnalysisViewState(form = form, state = State.Loading())

    class Loading(form: AnalysisForm) : AnalysisViewState(form = form, state = State.Loading())

    class Error(
        form: AnalysisForm,
        message: String? = null
    ) : AnalysisViewState(form = form, state = State.Error(message = message))

    class ResultSuccess(form: AnalysisForm) : AnalysisViewState(form = form, state = State.Success())
}
