package com.pardo.crackdetect.analysis

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pardo.crackdetct.data.analysis.AnalysisUseCase
import com.pardo.crackdetct.data.analysis.models.CrackAnalysisDomainModel
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

    fun onTakePhoto(bitmap: Bitmap) {
        val updatedForm = viewState.value.form.copy(image = bitmap)
        _viewState.value = AnalysisViewState.CaptureImage(updatedForm)
    }

    fun analyseImage() {
        _viewState.value = AnalysisViewState.Loading(form = viewState.value.form)
        viewModelScope.launch {
            analysisUseCase.analyseImage(viewState.value.form.image.toBase64()).fold(
                foldFailure = ::onAnalysisImageFailure,
                foldSuccess = ::onAnalysisImageSuccess
            )
        }
    }

    private fun onAnalysisImageSuccess(response: CrackAnalysisDomainModel) {
        _viewState.value = AnalysisViewState.ResultSuccess(form = viewState.value.form)
        navigator?.openResult(popUpToRoute = AnalysisDirections.Analysis.route)
    }

    private fun onAnalysisImageFailure(error: Failure) {
        _viewState.value = AnalysisViewState.Error(
            form = viewState.value.form,
            message = error.cause
        )
    }

    fun closeErrorDialog() {
        _viewState.value = AnalysisViewState.CaptureImage(viewState.value.form)
    }
}

private val placeHolderBitmap: Bitmap = BitmapFactory.decodeResource(
    Resources.getSystem(),
    android.R.drawable.ic_menu_camera
)

data class AnalysisForm(
    val image: Bitmap = placeHolderBitmap,
    private val analysis: CrackAnalysisDomainModel? = null
) {
    val type: String get() = analysis?.type ?: "Structural Cracks"
    val severity: String get() = analysis?.type ?: "Severe"
    val description: String get() = "The crack runs diagonally across the wall and through the building's architectural details, indicating a possible structural failure"
}

sealed class AnalysisViewState(
    val form: AnalysisForm = AnalysisForm(),
    state: State<Boolean>
) : ViewState<Boolean>(state = state) {

    object Initial : AnalysisViewState(state = State.Initial())

    class CaptureImage(form: AnalysisForm) : AnalysisViewState(form = form, state = State.Active())

    class Loading(form: AnalysisForm) : AnalysisViewState(form = form, state = State.Loading())

    class Error(
        form: AnalysisForm,
        message: String? = null
    ) : AnalysisViewState(form = form, state = State.Error(message = message))

    class ResultSuccess(form: AnalysisForm) : AnalysisViewState(form = form, state = State.Success())
}
