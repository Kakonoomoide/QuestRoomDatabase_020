package com.example.pam_pert9.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.pam_pert9.data.entity.Mahasiswa

//NOTE!!!!
// create update delete menggunakan suspend
// interface == blueprint
@Dao
interface MahasiswaDao {
    @Insert
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    @Delete
    suspend fun deleteMahasiswa (mahasiswa: Mahasiswa)

    @Update
    suspend fun updateMahasiswa (mahasiswa: Mahasiswa)

}