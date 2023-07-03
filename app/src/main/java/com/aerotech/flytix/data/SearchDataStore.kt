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
        private val CHECK_TANGGAL_KEBERANGKATAN =
            booleanPreferencesKey("CHECK_TANGGAL_KEBERANGKATAN")
        private val CHECK_TANGGAL_KEMBALI = booleanPreferencesKey("CHECK_TANGGAL_KEMBALI")
        private val JUMLAH_PENUMPANG = stringPreferencesKey("JUMLAH_PENUMPANG")
        private val JUMLAH_DEWASA = stringPreferencesKey("JUMLAH_DEWASA")
        private val JUMLAH_ANAK = stringPreferencesKey("JUMLAH_ANAK")
        private val JUMLAH_BAYI = stringPreferencesKey("JUMLAH_BAYI")
        private val ARRAY_JUMLAH_PENUMPANG = stringPreferencesKey("ARRAY_JUMLAH_PENUMPANG")
        private val KELAS_KURSI = stringPreferencesKey("KELAS_KURSI")
        private val SATU_PERJALANAN = booleanPreferencesKey("SATU_PERJALANAN")
        private val TIKET_KEBERANGKATAN = stringPreferencesKey("TIKET_KEBERANGKATAN")
        private val TIKET_KEPULANGAN = stringPreferencesKey("TIKET_KEPULANGAN")
        private val Context.dataStore by preferencesDataStore(
            name = NAMA_DATASTORE
        )
    }

    val getjumlahTotalPenumpang: Flow<String> =
        context.dataStore.data.map {
            it[JUMLAH_PENUMPANG] ?: ""
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

    val getIdTiketkeberangkatan: Flow<String> =
        context.dataStore.data.map {
            it[TIKET_KEBERANGKATAN] ?: ""
        }

    val getIdTiketKepulangan: Flow<String> =
        context.dataStore.data.map {
            it[TIKET_KEPULANGAN] ?: ""
        }


    val getArrayJumlahpenumpang: Flow<MutableList<Int>> =
        context.dataStore.data.map { preferences ->
            val serializedList = preferences[ARRAY_JUMLAH_PENUMPANG] ?: ""
            val list = deserializeList(serializedList)
            list ?: mutableListOf()
        }

    suspend fun simpanArrayPenumpang(arrayJumlahpenumpang: MutableList<Int>) {
        val serializedList = serializeList(arrayJumlahpenumpang)
        context.dataStore.edit { preferences ->
            preferences[ARRAY_JUMLAH_PENUMPANG] = serializedList
        }
    }

    private fun serializeList(intArray: MutableList<Int>): String {
        return intArray.joinToString(separator = ",")
    }

    private fun deserializeList(serializedList: String): MutableList<Int>? {
        return if (serializedList.isNotEmpty()) {
            try {
                serializedList.split(",").map { it.toInt() }.toMutableList()
            } catch (e: NumberFormatException) {
                null
            }
        } else {
            null
        }
    }


    val getJumlahPenumpangDewasa: Flow<String> =
        context.dataStore.data.map {
            it[JUMLAH_DEWASA] ?: "1"
        }
    val getJumlahPenumpangAnak: Flow<String> =
        context.dataStore.data.map {
            it[JUMLAH_ANAK] ?: "0"
        }
    val getJumlahPenumpangBayi: Flow<String> =
        context.dataStore.data.map {
            it[JUMLAH_BAYI] ?: "0"
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

    suspend fun simpanTripOneway(isOneway: Boolean) {
        context.dataStore.edit {
            it[SATU_PERJALANAN] = isOneway
        }
    }

    suspend fun simpanIDTiketKeberangkatan(idTiketKeberangkatan: String) {
        context.dataStore.edit {
            it[TIKET_KEBERANGKATAN] = idTiketKeberangkatan
        }
    }
    suspend fun simpanIDTiketKepulangan(idTiketKepulangan: String) {
        context.dataStore.edit {
            it[TIKET_KEPULANGAN] = idTiketKepulangan
        }
    }

    suspend fun simpanValueTanggalKeberangkatan(departureDate: String) {
        context.dataStore.edit {
            it[TANGGAL_KEBERANGKATAN] = departureDate
        }
    }

    suspend fun simpanValueTanggalKembali(returnDate: String) {
        context.dataStore.edit {
            it[TANGGAL_KEMBALI] = returnDate
        }
    }


    suspend fun simpanKeberangkatan(
        kotaKeberangkatan: String,
        kodeKotaKeberangkatan: String,
        checkKeberangkatan: Boolean
    ) {
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

    suspend fun simpanDestinasi(
        kotaDestinasi: String,
        KodeKotaDestinasi: String,
        checkDestinasi: Boolean
    ) {
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

    suspend fun simpanKelasKursi(kelasKursi: String) {
        context.dataStore.edit {
            it[KELAS_KURSI] = kelasKursi
        }
    }

    suspend fun simpanJumlahTotalPenumpang(jumlahTotal: String) {
        context.dataStore.edit {
            it[JUMLAH_PENUMPANG] = jumlahTotal
        }
    }

    suspend fun simpanJumlahPenumpang(
        jumlahDewasa: String,
        jumlahAnak: String,
        jumlahBayi: String
    ) {
        context.dataStore.edit {
            it[JUMLAH_DEWASA] = jumlahDewasa
            it[JUMLAH_ANAK] = jumlahAnak
            it[JUMLAH_BAYI] = jumlahBayi
        }
    }

    suspend fun simpanJumlahDewasa(jumlahDewasa: String) {
        context.dataStore.edit {
            it[JUMLAH_DEWASA] = jumlahDewasa
        }
    }

    suspend fun simpanJumlahAnak(jumlahAnak: String) {
        context.dataStore.edit {
            it[JUMLAH_ANAK] = jumlahAnak
        }
    }

    suspend fun simpanJumlahBayi(jumlahBayi: String) {
        context.dataStore.edit {
            it[JUMLAH_BAYI] = jumlahBayi
        }
    }


    suspend fun hapusKeberangkatan() {
        context.dataStore.edit {
            it.remove(KOTA_KEBERANGKATAN)
            it.remove(KODEKOTA_KEBERANGKATAN)
            it.remove(CHECK_KEBERANGKATAN)
        }
    }

    suspend fun hapusDestinasi() {
        context.dataStore.edit {
            it.remove(KOTA_DESTINASI)
            it.remove(KODEKOTA_DESTINASI)
            it.remove(CHECK_DESTINASI)
        }
    }

    suspend fun hapusTanggalKeberangkatan() {
        context.dataStore.edit {
            it.remove(TANGGAL_KEBERANGKATAN)
        }
    }

    suspend fun hapusTanggalKembali() {
        context.dataStore.edit {
            it.remove(TANGGAL_KEMBALI)
        }
    }

    suspend fun hapusOnewayTrip() {
        context.dataStore.edit {
            it.remove(SATU_PERJALANAN)
        }
    }
}
