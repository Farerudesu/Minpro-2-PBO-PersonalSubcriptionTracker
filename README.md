# Minpro-2-PBO-PersonalSubscriptionTracker

```
+------------------------------------------+
| Nama : Muhammad Fahriel                  |
| NIM  : 2509116050                        |
| Tema : Personal Subscription Tracker     |
+------------------------------------------+
```

Aplikasi berbasis terminal (CLI) menggunakan bahasa Java untuk mencatat, mengelola, dan memantau pengeluaran biaya langganan digital pribadi. Proyek ini merupakan pengembangan dari **Mini Project 1** menuju **Mini Project 2** dengan menerapkan prinsip PBO seperti **Encapsulation**, **Inheritance**, **Access Modifier**, **Validasi Input**, serta nilai tambah berupa arsitektur **MVC** dan **Polymorphism**.

---

## Daftar Isi
1. [Deskripsi Singkat Program](#1-deskripsi-singkat-program)
2. [Penjelasan Alur Program](#2-penjelasan-alur-program)
3. [Implementasi & Contoh Kode Fitur CRUD](#3-implementasi--contoh-kode-fitur-crud)
   - [A. Create (Tambah Data)](#a-create-tambah-data)
   - [B. Read (Tampilkan Data & Dummy Data Awal)](#b-read-tampilkan-data--dummy-data-awal)
   - [C. Update (Ubah Data dengan Setter)](#c-update-ubah-data-dengan-setter)
   - [D. Delete (Hapus Data dengan Konfirmasi Opsi Kedua)](#d-delete-hapus-data-dengan-konfirmasi-opsi-kedua)
4. [Penerapan Encapsulation dan Inheritance](#4-penerapan-encapsulation-dan-inheritance)
   - [A. Encapsulation & Access Modifier](#a-encapsulation--access-modifier)
   - [B. Inheritance (Pewarisan)](#b-inheritance-pewarisan)
5. [Penjelasan Letak Nilai Tambah](#5-penjelasan-letak-nilai-tambah)
   - [A. Arsitektur MVC & Penjelasan Struktur Packages](#a-arsitektur-mvc--penjelasan-struktur-packages)
   - [B. Polymorphism (Overriding & Overloading)](#b-polymorphism-overriding--overloading)
6. [Mekanisme Validasi Input & Standar Kode](#6-mekanisme-validasi-input--standar-kode)
   - [Validasi Input Anti-Crash](#validasi-input-anti-crash)
   - [Konsistensi Penamaan Method (camelCase)](#konsistensi-penamaan-method-camelcase)

---

## 1. Deskripsi Singkat Program

**Personal Subscription Tracker** adalah aplikasi yang membantu pengguna mencatat dan mengawasi pengeluaran langganan digital berbayar (seperti Netflix, Spotify, Google One, Microsoft 365, dll).

Fitur utama aplikasi:
- **Kategorisasi Langganan**: Membedakan langganan hiburan (Streaming) dan langganan kerja/cloud (Produktivitas).
- **Manajemen Data (CRUD)**: Menambah, melihat, mengedit, dan menghapus data langganan secara dinamis menggunakan `ArrayList`.
- **Ringkasan Finansial**: Menghitung total biaya bulanan seluruh langganan aktif beserta rincian per kategori.
- **Simulasi Biaya Cerdas**: Menghitung proyeksi pengeluaran tahunan dengan diskon otomatis.

---

## 2. Penjelasan Alur Program

Berikut alur jalannya program dari awal hingga selesai:

```
[Start Program] -> [Inisialisasi Controller & View] -> [Isi Dummy Data ke ArrayList]
       |
       v
+-------------------------------+
|       TAMPIL MENU UTAMA       | <-------------+
+-------------------------------+               |
| 1. Tampilkan Langganan (Read) |               |
| 2. Tambah Langganan (Create)  |               |
| 3. Edit Langganan (Update)    |               |
| 4. Hapus Langganan (Delete)   |               |
| 5. Ringkasan Pengeluaran      |               |
| 6. Simulasi Estimasi Biaya    |               |
| 7. Keluar                     |               |
+-------------------------------+               |
       |                                        |
       +---> Pilih 1-6 -> Proses Fitur --------->
       |
       +---> Pilih 7   -> Tampilkan Pesan Selesai -> [Program Berhenti]
```

1. **Aplikasi Dimulai (`Main.java`)**:
   Objek `LanggananView` dan `LanggananController` dibuat. Konstruktor Controller langsung mengisi **4 data dummy awal** ke dalam `ArrayList` agar data langsung siap dibaca.
2. **Menampilkan Menu Utama**:
   Program menampilkan 7 opsi menu kepada pengguna melalui antarmuka terminal.
3. **Eksekusi Fitur Sesuai Pilihan Pengguna**:
   - **Menu 1 (Read)**: Menampilkan seluruh data langganan dalam bentuk kartu detail atau tabel ringkas.
   - **Menu 2 (Create)**: Menginput data langganan baru (Streaming / Produktivitas) dan menyimpannya ke `ArrayList`.
   - **Menu 3 (Update)**: Mengubah atribut langganan (harga, tanggal, status, metode pembayaran, atau atribut kategori).
   - **Menu 4 (Delete)**: Menghapus langganan yang dipilih dengan konfirmasi opsi kedua (`y/n`).
   - **Menu 5 (Ringkasan Pengeluaran)**: Menghitung total tagihan bulanan dari seluruh langganan yang aktif.
   - **Menu 6 (Simulasi Biaya)**: Melakukan kalkulasi biaya jangka panjang dengan diskon loyalitas polimorfik.
   - **Menu 7 (Keluar)**: Mengakhiri perulangan program.
4. **Perulangan Program**:
   Setelah suatu aksi selesai diproses, program menggunakan loop `while` untuk kembali menampilkan Menu Utama hingga pengguna memilih menu 7 (Keluar).

---

## 3. Implementasi & Contoh Kode Fitur CRUD

### A. Create (Tambah Data)
Pengguna memilih jenis langganan, kemudian sistem membuat objek dari subclass yang sesuai (`LanggananStreaming` atau `LanggananProduktivitas`) dan memasukkannya ke dalam `ArrayList<Langganan>`.

```java
// Contoh potongan kode saat menambah langganan baru di LanggananController.java
if (tipe == 1) {
    String resolusi = InputValidator.bacaString(scanner, "Kualitas Streaming: ");
    int layar = InputValidator.bacaInt(scanner, "Batas Layar: ", 1, 100);

    LanggananStreaming streaming = new LanggananStreaming(
            id, layanan, harga, metode, tglTagihan, resolusi, layar);
    listLangganan.add(streaming); // Menambahkan objek ke ArrayList
} else {
    String storage = InputValidator.bacaString(scanner, "Kapasitas Storage: ");
    int lisensi = InputValidator.bacaInt(scanner, "Jumlah Lisensi: ", 1, 1000);

    LanggananProduktivitas prod = new LanggananProduktivitas(
            id, layanan, harga, metode, tglTagihan, storage, lisensi);
    listLangganan.add(prod); // Menambahkan objek ke ArrayList
}
```

---

### B. Read (Tampilkan Data & Dummy Data Awal)
Data dibaca langsung dari `ArrayList<Langganan>`. Karena sudah ada **dummy data awal**, pengguna bisa langsung melihat daftar langganan tanpa harus menginput data dari awal.

```java
// Contoh potongan kode saat menampilkan seluruh data di LanggananView.java
public void tampilkanDaftarLangganan(ArrayList<Langganan> list) {
    for (int i = 0; i < list.size(); i++) {
        System.out.println("Data #" + (i + 1));
        list.get(i).tampilkanDetail(); // Polimorfik: otomatis menampilkan detail sesuai tipe subclass
        tampilkanGaris();
    }
}
```

Tabel data dummy awal yang langsung tersedia saat program dibuka:

| ID | Layanan | Tipe Langganan | Harga / Bulan | Tgl Tagihan | Info Khusus | Status |
| :---: | :--- | :--- | :---: | :---: | :--- | :---: |
| `SUB01` | Netflix | Streaming (Video/Audio) | Rp 186.000,00 | 15 | 4K UHD + HDR (4 Layar) | Aktif |
| `SUB02` | Spotify | Streaming (Video/Audio) | Rp 54.990,00 | 20 | 320 kbps (1 Layar) | Aktif |
| `SUB03` | Google One | Produktivitas & Cloud SaaS | Rp 43.000,00 | 05 | 2 TB Storage (5 Akun) | Aktif |
| `SUB04` | Microsoft 365 | Produktivitas & Cloud SaaS | Rp 95.990,00 | 28 | 1 TB Storage (1 Akun) | Aktif |

---

### C. Update (Ubah Data dengan Setter)
Fitur edit mencari objek langganan berdasarkan ID, kemudian mengubah nilai atributnya menggunakan method **setter**. Semua setter aktif digunakan dan berfungsi memvalidasi perubahan nilai.

```java
// Contoh potongan kode proses update data di LanggananController.java
Langganan langganan = cariLanggananById(idCari);

// 1. Mengubah harga bulanan via setter
double hargaBaru = InputValidator.bacaDouble(scanner, "Masukkan Harga Baru: ", 0.0);
langganan.setHargaBulanan(hargaBaru);

// 2. Mengubah status via setter
langganan.setStatus("Aktif");

// 3. Mengubah atribut spesifik subclass via setter
if (langganan instanceof LanggananStreaming) {
    LanggananStreaming str = (LanggananStreaming) langganan;
    str.setKualitasResolusi("1080p FHD");
    str.setBatasLayar(2);
}
```

---

### D. Delete (Hapus Data dengan Konfirmasi Opsi Kedua)
Untuk mencegah data terhapus secara tidak sengaja, sistem menampilkan detail langganan yang dipilih lalu meminta **konfirmasi opsi kedua (`y/n`)** sebelum menghapus dari `ArrayList`.

```java
// Contoh potongan kode hapus data dengan konfirmasi di LanggananController.java
Langganan langganan = cariLanggananById(idHapus);

// Tampilkan detail data yang dipilih
langganan.tampilkanDetail();

// Konfirmasi opsi kedua sebelum eksekusi penghapusan
boolean konfirmasi = InputValidator.bacaKonfirmasi(scanner, "Apakah Anda yakin ingin menghapus langganan ini?");
if (konfirmasi) {
    listLangganan.remove(langganan); // Dihapus hanya jika pengguna memilih 'y' atau 'ya'
    view.tampilkanPesanSukses("Langganan berhasil dihapus dari sistem!");
} else {
    view.tampilkanPesan("Penghapusan langganan dibatalkan.");
}
```

---

## 4. Penerapan Encapsulation dan Inheritance

### A. Encapsulation & Access Modifier
Prinsip Encapsulation diterapkan dengan menyembunyikan data internal objek dan hanya memperbolehkan akses melalui method resmi (*getter* dan *setter*).

- **Access Modifier `private`**:
  Semua atribut di kelas model (`idSubscription`, `layanan`, `hargaBulanan`, `tanggalTagihan`, `status`, `kualitasResolusi`, `batasLayar`, `kapasitasStorage`, `lisensiUser`) berstatus `private`.
- **Access Modifier `public`**:
  Getter, setter, konstruktor, dan method operasional berstatus `public` agar dapat dipanggil oleh Controller dan View.
- **Bebas Deadcode Setter**:
  Seluruh setter aktif dipanggil pada konstruktor maupun fitur update program.
- **Validasi Nilai pada Setter**:
  Setter menyaring data agar objek selalu memiliki status yang valid:
  ```java
  // Contoh validasi dalam setter di Langganan.java
  public void setHargaBulanan(double hargaBulanan) {
      if (hargaBulanan >= 0) {
          this.hargaBulanan = hargaBulanan;
      } else {
          this.hargaBulanan = 0; // Mencegah harga bernilai minus
      }
  }

  public void setTanggalTagihan(int tanggalTagihan) {
      if (tanggalTagihan >= 1 && tanggalTagihan <= 31) {
          this.tanggalTagihan = tanggalTagihan;
      } else {
          this.tanggalTagihan = 1; // Default tanggal 1 jika input tidak valid
      }
  }
  ```

---

### B. Inheritance (Pewarisan)
Penerapan inheritance menggunakan struktur **1 Superclass** dan **2 Subclass**:

```
                 +-----------------------+
                 |       Langganan       |  <- (Superclass)
                 +-----------------------+
                 | - idSubscription      |
                 | - layanan             |
                 | - hargaBulanan        |
                 | - metode              |
                 | - tanggalTagihan      |
                 | - status              |
                 +-----------------------+
                             ▲
                             │ extends
             ┌───────────────┴───────────────┐
             │                               │
+-------------------------+     +--------------------------+
|   LanggananStreaming    |     |  LanggananProduktivitas  |  <- (Subclasses)
+-------------------------+     +--------------------------+
| - kualitasResolusi      |     | - kapasitasStorage       |
| - batasLayar            |     | - lisensiUser            |
+-------------------------+     +--------------------------+
```

1. **Superclass (`Langganan.java`)**:
   Menyimpan data dan perilaku umum yang dimiliki semua langganan (ID, Layanan, Harga Bulanan, Metode Pembayaran, Tanggal Tagihan, dan Status).
2. **Subclass 1 (`LanggananStreaming.java`)**:
   Mewarisi `Langganan` dan menambahkan atribut khusus video/audio streaming:
   - `kualitasResolusi`: Resolusi gambar / audio (misal: "4K UHD + HDR", "320 kbps").
   - `batasLayar`: Jumlah perangkat yang dapat menonton bersamaan.
3. **Subclass 2 (`LanggananProduktivitas.java`)**:
   Mewarisi `Langganan` dan menambahkan atribut khusus cloud/aplikasi kerja:
   - `kapasitasStorage`: Kuota penyimpanan awan (misal: "2 TB Google Drive", "1 TB OneDrive").
   - `lisensiUser`: Jumlah akun pengguna yang terdaftar.

Penggunaan keyword `super(...)` pada konstruktor subclass:
```java
public LanggananStreaming(String idSubscription, Layanan layanan, double hargaBulanan,
                          MetodePembayaran metode, int tanggalTagihan,
                          String kualitasResolusi, int batasLayar) {
    super(idSubscription, layanan, hargaBulanan, metode, tanggalTagihan); // Memanggil konstruktor Superclass
    setKualitasResolusi(kualitasResolusi);
    setBatasLayar(batasLayar);
}
```

---

## 5. Penjelasan Letak Nilai Tambah

### A. Arsitektur MVC & Penjelasan Struktur Packages
Program menerapkan pemisahan tugas menggunakan pola **Model-View-Controller (MVC)**. Kode diorganisasikan ke dalam struktur package berikut:

```
src/main/java/com/pbo/fareru/minpro/pbo/
│
├── model/                          <- [MODEL]
│   ├── Langganan.java              (Superclass entitas langganan)
│   ├── LanggananStreaming.java     (Subclass kategori streaming)
│   ├── LanggananProduktivitas.java (Subclass kategori produktivitas/cloud)
│   ├── Layanan.java                (Master data nama layanan & kategori)
│   └── MetodePembayaran.java       (Master data instrumen pembayaran)
│
├── view/                           <- [VIEW]
│   └── LanggananView.java          (Mengatur tampilan antarmuka CLI, tabel, dan format Rupiah)
│
├── controller/                     <- [CONTROLLER]
│   ├── LanggananController.java    (Logika alur CRUD, pengelola ArrayList, dan menu utama)
│   └── InputValidator.java         (Utilitas pembaca dan validasi input terminal anti-crash)
│
└── personalsubscriptiontracker/    <- [MAIN ENTRY POINT]
    └── Main.java                   (Titik awal untuk memulai aplikasi)
```

**Peran Setiap Package:**
- **Package `model`**: Menyimpan struktur data, enkapsulasi atribut, dan hierarki pewarisan. Bebas dari fungsi cetak terminal (`System.out.println`) dan input pengguna.
- **Package `view`**: Khusus mengurus presentasi visual ke pengguna di terminal (menu, format tabel, pesan sukses, pesan error, dan format mata uang Rupiah). Tidak memproses kalkulasi bisnis.
- **Package `controller`**: Menghubungkan Model dan View. Menerima input dari pengguna, mengelola data di `ArrayList`, menjalankan alur CRUD, dan memanggil View untuk menampilkan data.
- **Package `personalsubscriptiontracker`**: Berisi file `Main.java` sebagai entry point yang menginisialisasi View dan Controller lalu menjalankan aplikasi.

---

### B. Polymorphism (Overriding & Overloading)

#### 1. Method Overriding (Dynamic Polymorphism)
Subclass mengubah perilaku method milik superclass untuk menyesuaikan karakteristik masing-masing jenis langganan:

- **`getTipeLangganan()`**:
  - `Langganan` -> mengembalikan `"Umum"`.
  - `LanggananStreaming` -> di-override mengembalikan `"Streaming (Video/Audio)"`.
  - `LanggananProduktivitas` -> di-override mengembalikan `"Produktivitas & Cloud SaaS"`.
- **`tampilkanDetail()`**:
  - `Langganan` -> mencetak atribut dasar langganan.
  - `LanggananStreaming` -> memanggil `super.tampilkanDetail()` lalu menambahkan info resolusi dan batas layar.
  - `LanggananProduktivitas` -> memanggil `super.tampilkanDetail()` lalu menambahkan info kuota storage dan lisensi user.
- **`hitungEstimasiBiaya(int bulan)`**:
  - `Langganan` -> biaya standar (`hargaBulanan * bulan`).
  - `LanggananStreaming` -> jika durasi $\ge$ 12 bulan, otomatis diberikan diskon loyalitas tahunan sebesar **5%**.
  - `LanggananProduktivitas` -> jika durasi $\ge$ 12 bulan, otomatis diberikan diskon komitmen cloud sebesar **10%**.

```java
// Contoh Overriding hitungEstimasiBiaya di LanggananStreaming.java
@Override
public double hitungEstimasiBiaya(int bulan) {
    if (bulan <= 0) return 0;
    double total = getHargaBulanan() * bulan;
    if (bulan >= 12) {
        return total * 0.95; // Diskon 5% untuk langganan streaming >= 1 tahun
    }
    return total;
}
```

#### 2. Method Overloading (Static Polymorphism)
Method dengan nama yang sama tetapi memiliki parameter yang berbeda dalam satu kelas:

- **Pada Model `Langganan.java`**:
  - `tampilkanDetail()`: Mencetak format kartu lengkap per baris.
  - `tampilkanDetail(boolean ringkas)`: Mencetak format ringkas satu baris tabel jika parameter bernilai `true`.
  - `hitungEstimasiBiaya(int bulan)`: Menghitung biaya berdasarkan durasi bulan.
  - `hitungEstimasiBiaya(int bulan, double diskonPersen)`: Menghitung biaya dengan potongan diskon voucher tambahan (0–100%).
- **Pada Utility `InputValidator.java`**:
  - `bacaString(Scanner, prompt)` dan `bacaString(Scanner, prompt, allowEmpty)`
  - `bacaInt(Scanner, prompt)` dan `bacaInt(Scanner, prompt, min, max)`
  - `bacaDouble(Scanner, prompt)` dan `bacaDouble(Scanner, prompt, min)`

---

## 6. Mekanisme Validasi Input & Standar Kode

### Validasi Input Anti-Crash
Semua input melalui kelas `InputValidator` untuk menjamin program tidak berhenti tiba-tiba (*crash*) akibat kesalahan pengetikan pengguna:
1. **Penanganan Kesalahan Tipe Data**:
   Input angka dibungkus dengan blok `try-catch` (`NumberFormatException`). Jika pengguna memasukkan huruf saat diminta angka, sistem memberikan peringatan yang jelas dan meminta input ulang secara aman.
2. **Pencegahan Teks Kosong**:
   Input string divalidasi dengan `.trim().isEmpty()` sehingga pengguna tidak dapat melewati isian dengan spasi kosong.
3. **Pencegahan ID Duplikat**:
   Saat menambah langganan baru, sistem memeriksa seluruh ID yang sudah tersimpan agar tidak ada data yang bertabrakan.
4. **Validasi Rentang Nilai**:
   Pilihan menu dibatasi 1–7, tanggal dibatasi 1–31, dan harga minimal Rp 0.

### Konsistensi Penamaan Method (camelCase)
Seluruh penamaan identifier dalam kode program telah mengikuti standar konvensi Java:
- **Nama Class**: Menggunakan format `PascalCase` (contoh: `LanggananController`, `InputValidator`, `LanggananStreaming`).
- **Nama Method**: Menggunakan format `camelCase` secara konsisten di seluruh kelas (contoh: `jalankanAplikasi()`, `menuTambahLangganan()`, `tampilkanDaftarLangganan()`, `formatRupiah()`, `bacaString()`, `bacaInt()`, `bacaKonfirmasi()`).
- **Nama Variabel / Atribut**: Menggunakan format `camelCase` (contoh: `idSubscription`, `hargaBulanan`, `tanggalTagihan`, `listLangganan`).
