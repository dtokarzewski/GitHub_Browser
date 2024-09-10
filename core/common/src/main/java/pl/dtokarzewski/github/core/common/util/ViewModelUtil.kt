package pl.dtokarzewski.github.core.common.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

fun ViewModel.logViewState(viewState: StateFlow<Any>) {
    viewModelScope.launch {
        viewState
            .onEach { Timber.d("ViewState updated: $it") }
            .collect()
    }
}