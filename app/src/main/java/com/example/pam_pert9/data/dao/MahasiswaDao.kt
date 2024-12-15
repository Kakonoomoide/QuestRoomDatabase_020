package com.example.pam_pert9.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.pam_pert9.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

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

    @Query("SELECT * FROM mahasiswa ORDER BY nama ASC")
    fun getAllMahasiswa(): Flow<List<Mahasiswa>>
}