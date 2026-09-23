# Minpro-2-PBO-PersonalSubscriptionTracker

```
+-------------------------+
| Nama : Muhammad Fahriel |
| NIM  : 2509116050       |
+-------------------------+
```

Aplikasi berbasis CLI (*Command Line Interface*) yang dibangun menggunakan bahasa pemrograman Java untuk mencatat, mengelola, memantau pengeluaran biaya langganan digital pribadi, dan melakukan simulasi estimasi tagihan. Proyek ini merupakan pengembangan lanjutan dari **Mini Project 1** menuju **Mini Project 2** dengan menerapkan arsitektur **MVC (Model-View-Controller)**, **Inheritance**, **Polymorphism**, **Encapsulation**, **Access Modifiers**, serta **Validasi Input Tangguh**.

---

## Daftar Isi
1. [Deskripsi Program & Arsitektur MVC](#deskripsi-program--arsitektur-mvc)
2. [Penjelasan Struktur Packages](#penjelasan-struktur-packages)
3. [Penerapan Konsep PBO (Object-Oriented Programming)](#penerapan-konsep-pbo-object-oriented-programming)
   - [Access Modifiers](#1-access-modifiers)
   - [Encapsulation (Getter & Setter)](#2-encapsulation-getter--setter)
   - [Inheritance (Pewarisan)](#3-inheritance-pewarisan)
   - [Polymorphism (Overriding & Overloading)](#4-polymorphism-overriding--overloading)
   - [Dummy Data Awal](#5-dummy-data-awal)
   - [Mekanisme Validasi Input & Pencegahan Crash](#6-mekanisme-validasi-input--pencegahan-crash)
4. [Implementasi Fitur & Operasi CRUD](#implementasi-fitur--operasi-crud)
   - [Create (Tambah Langganan Baru)](#1-create-tambah-langganan-baru)
   - [Read (Tampilkan Daftar Langganan)](#2-read-tampilkan-daftar-langganan)
   - [Update (Ubah Data Langganan)](#3-update-ubah-data-langganan)
   - [Delete (Hapus Langganan dengan Konfirmasi)](#4-delete-hapus-langganan-dengan-konfirmasi)
   - [Ringkasan & Total Pengeluaran](#5-ringkasan--total-pengeluaran)
   - [Simulasi Estimasi Biaya (Polymorphism Demo)](#6-simulasi-estimasi-biaya-polymorphism-demo)
5. [Petunjuk Menjalankan Program](#petunjuk-menjalankan-program)

---

## Deskripsi Program & Arsitektur MVC

Proyek ini mengadopsi pola perancangan arsitektur perangkat lunak **Model-View-Controller (MVC)** untuk memisahkan tanggung jawab kode (*Separation of Concerns*) sehingga program lebih terstruktur, modular, mudah dirawat (*maintainable*), dan mudah dikembangkan (*scalable*):

```
       +-----------------------------------------------+
       |                      User                     |
       +-----------------------------------------------+
                             |   ^
                    Input (CLI)  | Output (CLI)
                             v   |
       +-----------------------------------------------+
       |             View (LanggananView)              |
       |  - Tampilan Menu, Tabel, Kartu, & Format Mata |
       |    Uang Rupiah                                |
       +-----------------------------------------------+
                             ^
                             | Memperbarui tampilan
                             v
       +-----------------------------------------------+
       |       Controller (LanggananController)        |
       |  - Logika Bisnis & Alur CRUD                  |
       |  - Menghubungkan Model & View                 |
       |  - Dibantu InputValidator untuk Validasi      |
       +-----------------------------------------------+
                             |
                  Mengelola data entitas
                             v
       +-----------------------------------------------+
       |                     Model                     |
       |  - Langganan (Superclass)                     |
       |    ├── LanggananStreaming (Subclass 1)        |
       |    └── LanggananProduktivitas (Subclass 2)    |
       |  - Layanan (Master Layanan)                   |
       |  - MetodePembayaran (Master Pembayaran)       |
       +-----------------------------------------------+
```

- **Model**: Menyimpan struktur data, logika bisnis internal entitas, enkapsulasi atribut, dan hierarki pewarisan kelas.
- **View**: Bertanggung jawab menyajikan antarmuka kepada pengguna di terminal (menu, kartu detail, ringkasan tabel, dan pesan notifikasi). Tidak menyimpan data dan tidak memproses kalkulasi bisnis.
- **Controller**: Mengatur alur logika program, mengelola koleksi data (`ArrayList`), memproses input pengguna, memanggil kalkulasi polimorfik, dan memperbarui tampilan View.

---

## Penjelasan Struktur Packages

Kode program diorganisasikan ke dalam struktur package yang terpisah sesuai perannya masing-masing:

```
src/main/java/com/pbo/fareru/minpro/pbo/
├── model/
│   ├── Langganan.java                 <- Superclass utama transaksi langganan
│   ├── LanggananStreaming.java        <- Subclass 1 khusus streaming film/musik
│   ├── LanggananProduktivitas.java    <- Subclass 2 khusus cloud & software produktivitas
│   ├── Layanan.java                   <- Kelas master penyimpan data layanan
│   └── MetodePembayaran.java          <- Kelas master instrumen pembayaran
├── view/
│   └── LanggananView.java             <- Antarmuka presentasi CLI
├── controller/
│   ├── LanggananController.java       <- Logika pengendali CRUD, data, dan alur aplikasi
│   └── InputValidator.java            <- Utilitas validasi input scanner anti-crash
└── personalsubscriptiontracker/
    └── Main.java                      <- Entry point utama program
```

### Rincian Tanggung Jawab Setiap Package:
1. **`com.pbo.fareru.minpro.pbo.model`**:
   - Berisi seluruh kelas representasi data entitas.
   - Mengimplementasikan konsep *Inheritance* (Superclass `Langganan` dan Subclass `LanggananStreaming`, `LanggananProduktivitas`).
   - Menerapkan *Encapsulation* penuh dengan *access modifier* `private` serta *getter* dan *setter* berproteksi validasi nilai.
2. **`com.pbo.fareru.minpro.pbo.view`**:
   - Berisi kelas `LanggananView`.
   - Mengelola visualisasi antarmuka terminal: banner utama, format kartu data, format tabel satu baris, dan konversi angka desimal ke format mata uang Rupiah (`formatRupiah`).
3. **`com.pbo.fareru.minpro.pbo.controller`**:
   - Berisi kelas `LanggananController` dan `InputValidator`.
   - `LanggananController` mengelola koleksi dinamis `ArrayList<Langganan>`, `ArrayList<Layanan>`, dan `ArrayList<MetodePembayaran>`, serta inisialisasi *dummy data*.
   - `InputValidator` menyediakan metode pembacaan input terminal yang aman terhadap *crash* (`InputMismatchException`, string kosong, batasan rentang nilai).
4. **`com.pbo.fareru.minpro.pbo.personalsubscriptiontracker`**:
   - Berisi kelas `Main` yang hanya bertugas menginstansiasi View dan Controller, kemudian memulai siklus hidup aplikasi.

---

## Penerapan Konsep PBO (Object-Oriented Programming)

### 1. Access Modifiers
Program menerapkan prinsip kontrol visibilitas (*Access Modifier*) secara konsisten:
- **`private`**:
  - Diterapkan pada seluruh atribut instance di kelas Model (`idSubscription`, `layanan`, `hargaBulanan`, `metode`, `tanggalTagihan`, `status`, `kualitasResolusi`, `batasLayar`, `kapasitasStorage`, `lisensiUser`).
  - Diterapkan pada koleksi data (`listLangganan`, `listLayanan`, `listMetode`) dan metode internal di dalam `LanggananController` (seperti `inisialisasiDummyData()`, `cariLanggananById()`, dsb.).
- **`public`**:
  - Diterapkan pada konstruktor, *getter*, *setter*, serta method publik yang menjadi antarmuka interaksi antar-package (`view.tampilkanDaftarLangganan()`, `controller.jalankanAplikasi()`, dsb.).
- **Pewarisan Super-Sub**: Subclass mengakses data superclass secara aman melalui konstruktor `super(...)` dan method *getter*/*setter* publik tanpa membocorkan akses atribut secara langsung.

### 2. Encapsulation (Getter & Setter)
Seluruh kelas pada paket `model` telah dienkapuslasi secara ketat. Setter dilengkapi proteksi logika internal untuk menjaga integritas data objek:
- `setHargaBulanan(double hargaBulanan)`: Jika input < 0, nilai otomatis diatur ke `0` untuk mencegah nilai negatif.
- `setTanggalTagihan(int tanggalTagihan)`: Memvalidasi rentang hari kalender (1–31). Jika di luar rentang, diatur ke nilai default `1`.
- `setBatasLayar(int batasLayar)`: Memastikan batas layar minimal adalah 1 perangkat.
- `setLisensiUser(int lisensiUser)`: Memastikan lisensi pengguna minimal adalah 1 akun.
- `setIdLayanan`, `setNamaLayanan`, `setKategori`, `setIdMetode`, `setNamaMetode`, `setJenis`: Memeriksa bahwa string input tidak bernilai `null` atau kosong (*blank*).

### 3. Inheritance (Pewarisan)
Penerapan *Inheritance* menggunakan hierarki 1 Superclass dan 2 Subclass yang merefleksikan karakteristik nyata layanan digital:

```
                     +----------------------------+
                     |         Langganan          | (Superclass)
                     +----------------------------+
                     | - idSubscription: String   |
                     | - layanan: Layanan         |
                     | - hargaBulanan: double     |
                     | - metode: MetodePembayaran |
                     | - tanggalTagihan: int      |
                     | - status: String           |
                     +----------------------------+
                                  ▲
                                  │ extends
                 ┌────────────────┴────────────────┐
                 │                                 │
+---------------------------------+  +-------------------------------------+
|       LanggananStreaming        |  |        LanggananProduktivitas       | (Subclasses)
+---------------------------------+  +-------------------------------------+
| - kualitasResolusi: String      |  | - kapasitasStorage: String          |
| - batasLayar: int               |  | - lisensiUser: int                  |
+---------------------------------+  +-------------------------------------+
```

- **Superclass (`Langganan`)**:
  Menyimpan atribut dan perilaku umum yang dimiliki oleh seluruh jenis langganan (ID, relasi ke objek Layanan, tarif bulanan, instrumen pembayaran, tanggal tagihan, dan status).
- **Subclass 1 (`LanggananStreaming`)**:
  Mewarisi `Langganan` dengan menambahkan atribut spesifik streaming hiburan:
  - `kualitasResolusi` (contoh: "4K UHD + HDR", "1080p FHD", "Lossless Audio").
  - `batasLayar` (jumlah layar / perangkat yang dapat memutar bersamaan).
- **Subclass 2 (`LanggananProduktivitas`)**:
  Mewarisi `Langganan` dengan menambahkan atribut spesifik cloud/software produktivitas:
  - `kapasitasStorage` (contoh: "2 TB Google Drive & Photos", "1 TB OneDrive").
  - `lisensiUser` (jumlah lisensi akun anggota/tim).

### 4. Polymorphism (Overriding & Overloading)

#### A. Method Overriding (Dynamic Polymorphism)
Subclass meng-override method dari Superclass untuk memberikan implementasi yang spesifik sesuai konteksnya:
1. **`getTipeLangganan()`**:
   - `Langganan`: Mengembalikan `"Umum"`.
   - `LanggananStreaming`: Meng-override dan mengembalikan `"Streaming (Video/Audio)"`.
   - `LanggananProduktivitas`: Meng-override dan mengembalikan `"Produktivitas & Cloud SaaS"`.
2. **`tampilkanDetail()`**:
   - `Langganan`: Mencetak informasi dasar langganan.
   - `LanggananStreaming`: Memanggil `super.tampilkanDetail()` lalu menambahkan output resolusi dan batas layar.
   - `LanggananProduktivitas`: Memanggil `super.tampilkanDetail()` lalu menambahkan output kapasitas storage dan lisensi user.
3. **`hitungEstimasiBiaya(int bulan)`**:
   - `Langganan`: Menghitung biaya dasar (`hargaBulanan * bulan`).
   - `LanggananStreaming`: Jika durasi `>= 12 bulan`, memberikan potongan loyalitas tahunan streaming sebesar 5%.
   - `LanggananProduktivitas`: Jika durasi `>= 12 bulan`, memberikan diskon komitmen tahunan korporat/cloud sebesar 10%.

#### B. Method Overloading (Static Polymorphism)
Penerapan *overloading* menyediakan beberapa variasi signature method pada kelas:
1. **Pada Model `Langganan`**:
   - `tampilkanDetail()`: Menampilkan format kartu lengkap.
   - `tampilkanDetail(boolean ringkas)`: Menampilkan format ringkas satu baris tabel jika parameter `true`.
   - `hitungEstimasiBiaya(int bulan)`: Menghitung estimasi biaya berdasarkan durasi bulan.
   - `hitungEstimasiBiaya(int bulan, double diskonPersen)`: Menghitung estimasi biaya dengan potongan diskon voucher tambahan (0-100%).
2. **Pada Utility `InputValidator`**:
   - `bacaString(Scanner, String prompt)` vs `bacaString(Scanner, String prompt, boolean allowEmpty)`
   - `bacaInt(Scanner, String prompt)` vs `bacaInt(Scanner, String prompt, int min, int max)`
   - `bacaDouble(Scanner, String prompt)` vs `bacaDouble(Scanner, String prompt, double min)`

### 5. Dummy Data Awal
Program menginisialisasi *dummy data* awal di dalam `ArrayList<Langganan>` pada saat controller pertama kali dibuat. Hal ini memenuhi ketentuan wajib agar fitur **Read (Tampilkan Daftar Langganan)** langsung menampilkan data tanpa perlu input dari awal:

| ID | Layanan | Tipe Langganan | Tarif / Bulan | Tanggal | Atribut Khusus | Status |
|:---|:---|:---|:---|:---:|:---|:---:|
| `SUB01` | Netflix | Streaming (Video/Audio) | Rp 186.000,00 | 15 | Resolusi: 4K UHD + HDR \| Batas Layar: 4 | Aktif |
| `SUB02` | Spotify | Streaming (Video/Audio) | Rp 54.990,00 | 20 | Resolusi: 320 kbps \| Batas Layar: 1 | Aktif |
| `SUB03` | Google One | Produktivitas & Cloud SaaS | Rp 43.000,00 | 05 | Storage: 2 TB \| Lisensi: 5 Akun | Aktif |
| `SUB04` | Microsoft 365 | Produktivitas & Cloud SaaS | Rp 95.990,00 | 28 | Storage: 1 TB \| Lisensi: 1 Akun | Aktif |

### 6. Mekanisme Validasi Input & Pencegahan Crash
Untuk menjamin program tangguh (*robust*):
1. **Pencegahan `InputMismatchException` / Format Angka**:
   Pembacaan angka dilakukan melalui `Integer.parseInt()` dan `Double.parseDouble()` yang dibungkus blok `try-catch` di dalam `InputValidator`. Input karakter huruf tidak akan membuat program keluar secara paksa.
2. **Pembersihan Buffer Input**:
   Setiap pembacaan baris menggunakan `scanner.nextLine()` secara konsisten, sehingga tidak ada karakter newline tertinggal (*buffer ghosting*).
3. **Pencegahan String Kosong & Whitespace**:
   Input teks divalidasi dengan `.trim().isEmpty()` dan akan terus meminta input ulang hingga pengguna memasukkan data yang valid.
4. **Pencegahan ID Duplikat (*Duplicate Check*)**:
   Pendaftaran langganan, layanan, dan metode pembayaran baru memeriksa keunikan ID secara *case-insensitive*.
5. **Validasi Batas Nilai (*Boundary Validation*)**:
   Harga bulanan dibatasi minimal 0 (`harga >= 0`), tanggal jatuh tempo dibatasi pada rentang 1–31, dan pilihan menu dibatasi sesuai opsi yang tersedia.

---

## Implementasi Fitur & Operasi CRUD

### 1. CREATE (Tambah Langganan Baru)
Pengguna dapat menambahkan data langganan baru dengan alur:
1. Menampilkan petunjuk ID terakhir yang terdaftar.
2. Memvalidasi keunikan ID Subscription baru.
3. Memilih tipe langganan:
   - **Tipe 1**: Langganan Streaming.
   - **Tipe 2**: Langganan Produktivitas & Cloud.
4. Memilih layanan eksisting atau mendaftarkan master layanan baru (*inline registration*).
5. Memilih metode pembayaran eksisting atau mendaftarkan metode baru (*inline registration*).
6. Memasukkan harga bulanan dan tanggal tagihan.
7. Memasukkan atribut spesifik sesuai tipe subclass yang dipilih:
   - Jika Streaming: input kualitas resolusi dan batas layar.
   - Jika Produktivitas: input kapasitas cloud storage dan lisensi pengguna.
8. Instansiasi objek subclass yang sesuai dan menyimpannya ke dalam `ArrayList<Langganan>`.

### 2. READ (Tampilkan Daftar Langganan)
Menampilkan seluruh data langganan yang tersimpan. Pengguna dapat memilih 2 format tampilan:
1. **Detail Lengkap (Format Kartu)**: Memanggil method polimorfik `tampilkanDetail()` yang mencetak seluruh data umum beserta data spesifik subclass.
2. **Format Ringkas (Format Tabel)**: Memanggil method *overloaded* `tampilkanDetail(true)` yang mencetak tabel satu baris terstruktur.

### 3. UPDATE (Ubah Data Langganan)
Mengubah data langganan berdasarkan ID:
1. Memasukkan ID langganan yang ingin diedit.
2. Jika ditemukan, menampilkan rincian data saat ini.
3. Memilih atribut yang ingin diubah:
   - Ubah Harga Bulanan.
   - Ubah Tanggal Tagihan.
   - Ubah Status (Aktif / Nonaktif).
   - Ubah Atribut Spesifik Kategori (menggunakan operator `instanceof` untuk mendeteksi apakah objek merupakan `LanggananStreaming` atau `LanggananProduktivitas`, lalu mengedit atribut uniknya).
   - Batal.

### 4. DELETE (Hapus Langganan dengan Konfirmasi)
Fitur penghapusan dilengkapi *Two-Step Confirmation*:
1. Memasukkan ID langganan yang akan dihapus.
2. Menampilkan detail langganan yang ditemukan.
3. Meminta konfirmasi tegas: `Apakah Anda yakin ingin menghapus langganan ini? (y/n): `.
4. Data hanya dihapus dari `ArrayList` jika pengguna mengonfirmasi dengan `y` atau `ya`.

### 5. Ringkasan & Total Pengeluaran
Menu 5 menghitung total pengeluaran bulanan khusus langganan yang berstatus `Aktif`:
- Rincian jumlah dan nominal pengeluaran untuk Kategori Streaming.
- Rincian jumlah dan nominal pengeluaran untuk Kategori Produktivitas.
- Total biaya bulanan akumulatif.
- Analisis ambang batas pengeluaran (peringatan jika pengeluaran > Rp 500.000).

### 6. Simulasi Estimasi Biaya (Polymorphism Demo)
Menu 6 mendemonstrasikan eksekusi polimorfik *runtime* dan *overloading*:
- Pengguna memasukkan proyeksi durasi (misal: 6, 12, atau 24 bulan).
- Pengguna dapat memilih apakah ingin menyertakan voucher diskon tambahan (*method overloading*).
- Program melakukan iterasi pada seluruh langganan aktif dan memanggil `hitungEstimasiBiaya()` yang secara otomatis memperhitungkan diskon tahunan masing-masing subclass.

---

## Petunjuk Menjalankan Program

### Prasyarat
- Java Development Kit (JDK 17 atau versi yang lebih baru).
- Apache Maven atau IDE NetBeans.

### Menjalankan via Terminal / CLI:
1. Masuk ke direktori utama proyek:
   ```bash
   cd Minpro-1-PBO-PersonalSubscriptionTracker
   ```
2. Bersihkan dan kompilasi proyek menggunakan Maven:
   ```bash
   mvn clean compile
   ```
3. Jalankan aplikasi:
   ```bash
   mvn exec:java
   ```
   Atau jalankan langsung melalui Java bytecode:
   ```bash
   java -cp target/classes com.pbo.fareru.minpro.pbo.personalsubscriptiontracker.Main
   ```

### Menjalankan via Apache NetBeans:
1. Buka aplikasi **Apache NetBeans**.
2. Pilih menu **File** -> **Open Project**.
3. Arahkan ke folder proyek `Minpro-1-PBO-PersonalSubscriptionTracker`.
4. Tekan tombol **Run Project (F6)** atau klik kanan nama proyek dan pilih **Run**.
