# Tiviku - TV Streaming App (Android Mobile App)

Aplikasi Android untuk streaming siaran televisi Indonesia & seluruh dunia secara langsung (*Live Streaming*) berbasis **Kotlin**, **Jetpack Compose (Material 3)**, dan **AndroidX Media3 (ExoPlayer)**.

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

4. **Navigasi & Filter Saluran Seluruh Dunia (190+ Negara):**
   * Filter saluran berdasarkan Negara dengan bendera (190+ negara bersumber dari [iptv-org](https://github.com/iptv-org/iptv)).
   * Saluran Indonesia dan negara populer ditempatkan di awal.
   * Kolom pencarian saluran instan (*real-time search*) lintas saluran global.
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

8. **Penyaringan Saluran Hidup & Smart Detection:**
   * Seluruh 9.200+ saluran bawaan telah diverifikasi aktif (200 OK) dan tautan mati telah dibersihkan.
   * Dilengkapi deteksi otomatis: jika suatu siaran offline/gagal, aplikasi menandainya dengan label 'Mati'.
   * Terdapat opsi menu 'Sembunyikan Saluran Mati' untuk menyaring hanya saluran yang hidup.
   * Tombol 'Saluran Lain ▶' otomatis muncul saat siaran bermasalah untuk beralih ke saluran berikutnya.

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

## 📺 Dukungan Android TV, Google TV & TV Box

Aplikasi **Tiviku** telah dioptimalkan secara universal untuk **HP, Tablet, dan Android TV / Google TV / Android TV Box**:
- **Non-Touchscreen Ready:** Tidak mewajibkan layar sentuh (`android.hardware.touchscreen = false`).
- **Tampil di Beranda TV:** Ikon otomatis muncul di halaman utama launcher TV via `LEANBACK_LAUNCHER`.
- **Navigasi Remote Control (D-Pad):** Sorotan fokus putih menyala saat kursor remote TV diarahkan ke saluran, tekan tombol **OK** untuk memutar.
- **Kunci Lanskap Otomatis:** Otomatis mengunci rasio layar penuh lanskap saat mendeteksi perangkat TV.

### Cara Instal di Android TV:
1. Unduh berkas `app-debug.apk` dari tab **Actions** di repositori GitHub ini.
2. Salin file `.apk` ke Flashdisk USB dan tancapkan ke TV / TV Box.
3. Gunakan aplikasi pengelola berkas di TV (seperti *X-plore* atau *File Commander*) untuk membuka dan menginstal `.apk`.
4. Buka aplikasi **Tiviku** langsung dari layar utama Android TV Anda!

---

## 📺 Sumber Data Saluran (Seluruh Dunia)

Daftar saluran global (13.000+ saluran dari 190 negara) bersumber dari repository [iptv-org/iptv](https://github.com/iptv-org/iptv) dan disimpan di berkas:
`app/src/main/assets/channels.json`

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

