# Indonesia TV Streaming (Android Mobile App)

Aplikasi Android untuk streaming siaran televisi lokal Indonesia secara langsung (*Live Streaming*) berbasis **Kotlin**, **Jetpack Compose (Material 3)**, dan **AndroidX Media3 (ExoPlayer)**.

---

## ✨ Fitur Utama

1. **Pemutar Video HLS (`.m3u8`):**
   * Menggunakan engine terbaru **AndroidX Media3 ExoPlayer**.
   * Mendukung buffering adaptif, auto-reconnect, dan indikator status siaran (*LIVE* badge).
   * Tombol *Coba Lagi* (*retry*) otomatis saat koneksi terputus.

2. **Dua Mode Tampilan (Portrait & Landscape):**
   * **Mode Portrait:** Pemutar video 16:9 di bagian atas layar dan daftar saluran interaktif di bagian bawah.
   * **Mode Landscape (Fullscreen):** Layar penuh dengan tombol sembunyikan bar navigasi (*immersive mode*).

3. **Picture-in-Picture (PiP) & Smart Lifecycle:**
   * Mendukung PiP Android bawaan melalui tombol PiP manual.
   * Siaran TV otomatis langsung dihentikan (*pause*) saat user kembali ke Home atau membuka aplikasi lain agar hemat baterai dan kuota.

4. **Navigasi & Filter Saluran:**
   * Filter saluran berdasarkan Kategori (*Semua, Nasional, Daerah, Olahraga, Internasional*).
   * Kolom pencarian saluran instan (*real-time search*).
   * Pemuat logo saluran menggunakan pustaka **Coil**.

5. **Kelola Saluran (Tekan Lama / Long-Press):**
   * **Edit Saluran:** Mengubah nama, kategori, URL streaming `.m3u8`, dan URL logo.
   * **Hapus Saluran:** Menghapus saluran yang tidak diinginkan dengan konfirmasi.
   * **Penyimpanan Lokal Persisten:** Seluruh perubahan saluran tersimpan otomatis di perangkat.
   * **Reset Saluran:** Kembalikan daftar saluran ke pengaturan awal bawaan kapan saja via menu opsi.

6. **Tambah Saluran Kustom / URL Pribadi:**
   * Dilengkapi dialog untuk menambahkan saluran kustom atau URL `.m3u8` langsung dari dalam aplikasi.

7. **Monetisasi Unity Banner Ads:**
   * Terintegrasi dengan **Unity Ads Android SDK**.
   * Banner iklan diletakkan di bagian bawah layar (*bottomBar*) dalam mode portrait, otomatis disembunyikan saat fullscreen dan PiP mode.
   * Konfigurasi terpusat dan aman di `UnityAdsConfig.kt`.

---

## 🛠️ Tech Stack

* **Bahasa:** Kotlin (2.0.21)
* **UI:** Jetpack Compose (BOM 2024.10.01) + Material 3
* **Media Player:** AndroidX Media3 ExoPlayer (`1.5.0`)
* **Image Loader:** Coil (`2.7.0`)
* **Build System:** Gradle 8.11.1 (AGP 8.7.2)
* **Target OS:** Android 8.0 (API 26) ke atas hingga Android 15 (API 35)

---

## 🚀 Cara Menjalankan di Android Studio

1. Buka **Android Studio**.
2. Pilih **File** > **Open...**
3. Arahkan ke folder:
   ```
   C:\Users\wachi\.gemini\antigravity\scratch\indonesia-tv-stream
   ```
4. Tunggu proses **Gradle Sync** selesai (Android Studio akan otomatis mengunduh dependensi yang diperlukan).
5. Sambungkan HP Android Anda (aktifkan *USB Debugging*) atau jalankan Android Emulator.
6. Klik tombol **Run 'app'** (ikon segitiga hijau ▶️ atau `Shift + F10`).

---

## 📺 Mengubah atau Menambah Daftar Saluran

Daftar saluran bawaan dapat diubah di berkas:
`app/src/main/java/com/id/tvstream/data/SampleChannels.kt`

---

## 💰 Pengaturan Unity Ads (Game ID & Placement)

Konfigurasi iklan dapat disesuaikan di berkas:
`app/src/main/java/com/id/tvstream/ads/UnityAdsManager.kt`

```kotlin
object UnityAdsConfig {
    const val GAME_ID = "5732156"          // Masukkan Game ID dari Unity Dashboard
    const val BANNER_PLACEMENT_ID = "Banner_Android" // Placement ID banner Anda
    const val TEST_MODE = true             // Ubah ke false untuk production
}
```

