package com.example.pam_pert9.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_pert9.data.entity.Mahasiswa
import com.example.pam_pert9.repository.RepositoryMhs
import kotlinx.coroutines.launch

class MahasiswaViewModel (
    private val repositoryMhs: RepositoryMhs
): ViewModel(){
    var uiState by mutableStateOf(MhsUIState())

    // update state berdasarkan input
    fun updateState(mahasiswaEvent: MahasiswaEvent){
        uiState = uiState.copy(
            mahasiswaEvent = mahasiswaEvent,
        )
    }

    //validasi
    private fun validateFields(): Boolean{
        val event = uiState.mahasiswaEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong!!",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong!!",
            jenisKelamin = if (event.jeniskelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong!!",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong!!",
            kelas = if (event.kelas.isNotEmpty()) null else "Kelas tidak boleh kosong!!",
            angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh kosong!!",
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData(){
        val currentEvent = uiState.mahasiswaEvent

        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryMhs.insertMhs(currentEvent.toMahasiswaEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        //reset input and error state
                        mahasiswaEvent = MahasiswaEvent(),
                        isEntryValid = FormErrorState(),
                    )
                }
                catch (e: Exception){
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        }
        else{
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data anda."
            )
        }
    }

    //reset message snackbar
    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}

data class MhsUIState(
    val mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState= FormErrorState(),
    val snackBarMessage: String? = null
)

// input validation
data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null,
){
    fun isValid(): Boolean{
        return nim == null
                && nama == null
                && jenisKelamin == null
                && alamat == null
                && kelas == null
                && angkatan == null
    }
}

// wadah untuk menyimpan data
data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val jeniskelamin: String = "",
    val alamat: String = "",
    val kelas: String = "",
    val angkatan: String = "",
)

fun MahasiswaEvent.toMahasiswaEntity():Mahasiswa = Mahasiswa (
    nim = nim,
    nama = nama,
    jenisKelamin = jeniskelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)