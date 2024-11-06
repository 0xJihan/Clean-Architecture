package com.jihan.cleanarchitecture.domain.use_cases

import com.jihan.cleanarchitecture.common.UiState
import com.jihan.cleanarchitecture.domain.model.ImageModel
import com.jihan.cleanarchitecture.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SearchImageUseCase @Inject constructor (private val imageRepository: ImageRepository) {
    operator fun invoke(query: String): Flow<UiState<List<ImageModel>>> = flow {
        emit(UiState.Loading)
        try {
            emit(UiState.Success(imageRepository.searchImage(query)))
        } catch (e: Exception) {
            emit(UiState.Error(e.localizedMessage))
        }
    }
}