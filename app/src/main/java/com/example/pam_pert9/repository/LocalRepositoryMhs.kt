package com.example.pam_pert9.repository

import com.example.pam_pert9.data.dao.MahasiswaDao
import com.example.pam_pert9.data.entity.Mahasiswa

class LocalRepositoryMhs(private val mahasiswaDao: MahasiswaDao) : RepositoryMhs {
    override suspend fun insertMhs(mahasiswa: Mahasiswa) {
        mahasiswaDao.insertMahasiswa(mahasiswa)
    }
}