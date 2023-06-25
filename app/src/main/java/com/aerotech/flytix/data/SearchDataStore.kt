package com.aerotech.flytix.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//Data Store untuk Search Action
class SearchDataStore(@ApplicationContext val context: Context) {

    //membuat Object
    companion object {
        private const val NAMA_DATASTORE = "KOTA_DATASTORE"
        private val KOTA_DESTINASI = stringPreferencesKey("KOTA_DESTINASI")
        private val KODEKOTA_DESTINASI = stringPreferencesKey("KODEKOTA_DESTINASI")
        private val KOTA_KEBERANGKATAN = stringPreferencesKey("KOTA_KEBERANGKATAN")
        private val KODEKOTA_KEBERANGKATAN = stringPreferencesKey("KODEKOTA_KEBERANGKATAN")
        private val CHECK_KEBERANGKATAN = booleanPreferencesKey("CHECK_KEBERANGKATAN")
        private val CHECK_DESTINASI = booleanPreferencesKey("CHECK_DESTINASI")
        private val TANGGAL_KEBERANGKATAN = stringPreferencesKey("TANGGAL_KEBERANGKATAN")
        private val TANGGAL_KEMBALI = stringPreferencesKey("TANGGAL_KEMBALI")
        private val CHECK_TANGGAL_KEBERANGKATAN = booleanPreferencesKey("CHECK_TANGGAL_KEBERANGKATAN")
        private val CHECK_TANGGAL_KEMBALI = booleanPreferencesKey("CHECK_TANGGAL_KEMBALI")
        private val KELAS_KURSI = stringPreferencesKey("KELAS_KURSI")
        private val SATU_PERJALANAN = booleanPreferencesKey("SATU_PERJALANAN")
        private val Context.dataStore by preferencesDataStore(
            name = NAMA_DATASTORE
        )
    }

    val getKotaKeberangkatan: Flow<String> =
        context.dataStore.data.map {
            it[KOTA_KEBERANGKATAN] ?: ""
        }

    val getKodeKotaKeberangkatan: Flow<String> =
        context.dataStore.data.map {
            it[KODEKOTA_KEBERANGKATAN] ?: ""
        }

    val getKotaDestinasi: Flow<String> =
        context.dataStore.data.map {
            it[KOTA_DESTINASI] ?: ""
        }

    val getKodeKotaDestinasi: Flow<String> =
        context.dataStore.data.map {
            it[KODEKOTA_DESTINASI] ?: ""
        }

    val getTanggalKeberangkatan: Flow<String> =
        context.dataStore.data.map {
            it[TANGGAL_KEBERANGKATAN] ?: ""
        }

    val getTanggalKembali: Flow<String> =
        context.dataStore.data.map {
            it[TANGGAL_KEMBALI] ?: ""
        }
    val getKelasKursi: Flow<String> =
        context.dataStore.data.map {
            it[KELAS_KURSI] ?: ""
        }

    val getKeyKeberangkatan: Flow<Boolean> =
        context.dataStore.data.map {
            it[CHECK_KEBERANGKATAN] ?: false
        }

    val getKeyDestinasi: Flow<Boolean> =
        context.dataStore.data.map {
            it[CHECK_DESTINASI] ?: false
        }

    val getValueTanggalKeberangkatan: Flow<Boolean> =
        context.dataStore.data.map {
            it[CHECK_TANGGAL_KEBERANGKATAN] ?: false
        }

    val getValueTanggalKembali: Flow<Boolean> =
        context.dataStore.data.map {
            it[CHECK_TANGGAL_KEMBALI] ?: false
        }

    val getValueTripOneway: Flow<Boolean> =
        context.dataStore.data.map {
            it[SATU_PERJALANAN] ?: false
        }

    suspend fun simpanTripOneway(isOneway: Boolean){
        context.dataStore.edit {
            it[SATU_PERJALANAN] = isOneway
        }
    }

    suspend fun simpanValueTanggalKeberangkatan(departureDate: String){
        context.dataStore.edit {
            it[TANGGAL_KEBERANGKATAN] = departureDate
        }
    }

    suspend fun simpanValueTanggalKembali(returnDate: String){
        context.dataStore.edit {
            it[TANGGAL_KEMBALI] = returnDate
        }
    }





    suspend fun simpanKeberangkatan(kotaKeberangkatan: String, kodeKotaKeberangkatan: String, checkKeberangkatan: Boolean) {
        context.dataStore.edit {
            it[KOTA_KEBERANGKATAN] = kotaKeberangkatan
            it[KODEKOTA_KEBERANGKATAN] = kodeKotaKeberangkatan
            it[CHECK_KEBERANGKATAN] = checkKeberangkatan
        }
    }

    suspend fun simpanKotaKeberangkatan(kotaKeberangkatan: String) {
        context.dataStore.edit {
            it[KOTA_KEBERANGKATAN] = kotaKeberangkatan
        }
    }

    suspend fun simpanDestinasi(kotaDestinasi: String, KodeKotaDestinasi: String, checkDestinasi: Boolean) {
        context.dataStore.edit {
            it[KOTA_DESTINASI] = kotaDestinasi
            it[KODEKOTA_DESTINASI] = KodeKotaDestinasi
            it[CHECK_DESTINASI] = checkDestinasi
        }
    }

    suspend fun simpanKotaDestinasi(kotaDestinasi: String) {
        context.dataStore.edit {
            it[KOTA_DESTINASI] = kotaDestinasi
        }
    }

    suspend fun simpanKelasKursi(kelasKursi: String){
        context.dataStore.edit {
            it[KELAS_KURSI] = kelasKursi
        }
    }

    suspend fun hapusKeberangkatan(){
        context.dataStore.edit {
            it.remove(KOTA_KEBERANGKATAN)
            it.remove(KODEKOTA_KEBERANGKATAN)
            it.remove(CHECK_KEBERANGKATAN)
        }
    }

    suspend fun hapusDestinasi(){
        context.dataStore.edit {
            it.remove(KOTA_DESTINASI)
            it.remove(KODEKOTA_DESTINASI)
            it.remove(CHECK_DESTINASI)
        }
    }

    suspend fun hapusTanggalKeberangkatan(){
        context.dataStore.edit {
            it.remove(TANGGAL_KEBERANGKATAN)
        }
    }

    suspend fun hapusTanggalKembali(){
        context.dataStore.edit {
            it.remove(TANGGAL_KEMBALI)
        }
    }

    suspend fun hapusOnewayTrip(){
        context.dataStore.edit {
            it.remove(SATU_PERJALANAN)
        }
    }
}