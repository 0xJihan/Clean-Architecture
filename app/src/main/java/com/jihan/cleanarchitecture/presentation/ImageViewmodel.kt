package com.jihan.cleanarchitecture.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jihan.cleanarchitecture.common.UiState
import com.jihan.cleanarchitecture.domain.use_cases.SearchImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewmodel @Inject constructor(private val searchImageUseCase: SearchImageUseCase) : ViewModel() {

    private val _imageState = mutableStateOf(ImageState())
    val imageState : State<ImageState> get() =  _imageState

    private val _query = MutableStateFlow("")


    init {
        searchImage("")
    }


    @OptIn(FlowPreview::class)
    fun updateQuery(query: String){
        _query.value = query
        viewModelScope.launch {
            _query.debounce(1000).collectLatest {
                searchImage(it)
            }
        }
    }


    fun searchImage(query: String) {
        searchImageUseCase(query).onEach {
            when(it){
                is UiState.Error -> _imageState.value = ImageState(error = it.message)
                UiState.Loading -> _imageState.value = ImageState(isLoading = true)
                is UiState.Success -> _imageState.value = ImageState(images = it.data)
            }
        }.launchIn(viewModelScope)
    }




}