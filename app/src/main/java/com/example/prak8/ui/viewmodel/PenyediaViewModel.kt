package com.example.prak8.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.prak8.MahasiswaApplications

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomeViewModel(mahasiswaApp().container.mahasiswaRepository) }
        initializer { InsertViewModel(mahasiswaApp().container.mahasiswaRepository) }
        initializer { DetailViewModel(createSavedStateHandle(), mahasiswaRepository = mahasiswaApp().container.mahasiswaRepository) }
        initializer { EditViewModel(createSavedStateHandle(), mahasiswaRepository = mahasiswaApp().container.mahasiswaRepository) }
    }
}

fun CreationExtras.mahasiswaApp(): MahasiswaApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApplications)