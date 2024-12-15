package com.example.pam_pert9.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.pam_pert9.data.entity.Mahasiswa
import com.example.pam_pert9.repository.RepositoryMhs

class UpdateMhsViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs
) : ViewModel(){

}

fun Mahasiswa.toUIStateMhs() : MhsUIState = MhsUIState(
    mahasiswaEvent = this.toDetailUiEvent(),
)