package com.example.prak8.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prak8.repository.MahasiswaRepository
import com.example.prak8.ui.view.DestinasiEdit
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val mahasiswaRepository: MahasiswaRepository
) : ViewModel() {

    var uiState by mutableStateOf(InsertUiState())
        private set

    val nim: String = checkNotNull(savedStateHandle[DestinasiEdit.nim])

    init {
        viewModelScope.launch {
            uiState = mahasiswaRepository.getMahasiswaByNim(nim).toUiStateMhs()
        }
    }

    fun updateInsertMhsState(insertUiEvent: InsertUiEvent) {
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun editMahasiswa(){
        viewModelScope.launch {
            try {
                mahasiswaRepository.updateMahasiswa(nim, uiState.insertUiEvent.toMhs())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}