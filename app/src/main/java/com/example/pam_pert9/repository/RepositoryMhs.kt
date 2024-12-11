package com.example.pam_pert9.repository

import com.example.pam_pert9.data.entity.Mahasiswa

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
}