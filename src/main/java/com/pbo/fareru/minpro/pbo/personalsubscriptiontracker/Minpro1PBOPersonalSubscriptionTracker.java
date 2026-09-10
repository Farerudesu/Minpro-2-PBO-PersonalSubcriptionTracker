/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.pbo.fareru.minpro.pbo.personalsubscriptiontracker;
import model.Langganan;
import model.MetodePembayaran;
import model.Layanan;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Fareru
 */
public class Minpro1PBOPersonalSubscriptionTracker {
    private static ArrayList<Layanan> listLayanan = new ArrayList<>();
    private static ArrayList<MetodePembayaran> listMetode = new ArrayList<>();
    private static ArrayList<Langganan> listLangganan = new ArrayList<>();
    
    public static void main(String[] args) {
        inisialisasiDataAwal();
        Scanner scanner = new Scanner(System.in);
        int pilihan = 0;
        
        while (pilihan != 6){
            System.out.println("\n==================================");
            System.out.println("   PERSONAL SUBSCRIPTION TRACKER   ");
            System.out.println("==================================");
            System.out.println("1. Tampilkan Daftar Langganan ");
            System.out.println("2. Tambah Langganan Baru");
            System.out.println("3. Edit Status & Harga");
            System.out.println("4. Batalkan/Hapus Langganan");
            System.out.println("5. Hitung Total Pengeluaran Bulanan");
            System.out.println("6. Keluar");
            System.out.print("Input Menu (1-6): ");
            
       if (scanner.hasNextInt()) {
           pilihan = scanner.nextInt();
           scanner.nextLine();
       } else {
            System.out.println("Masukan Angka yang valid!");
            scanner.nextLine();
            continue;    
        }
        System.out.println("----------------------------------");
        switch (pilihan){
            case 1:
                tampilkanSemuaLangganan();
                break;
            case 2:
                tambahLangganan(scanner);
                break;
            case 3:
                editLangganan(scanner);
                break;
            case 4:
                hapusLangganan(scanner);
                break;
            case 5:
                hitungTotalPengeluaran(scanner);
                break;
            case 6:
                System.out.println("Bye bye >..<");

                
                break;
            default:
                System.out.println("Pilihan tidak tersedia!");
                   
            
        }
       }
        scanner.close();
    }
    //READ
    private static void tampilkanSemuaLangganan(){
        if (listLangganan.isEmpty()){
            System.out.println("Belum ada data langganan yang tercatat.");
            return;
        }
        System.out.println("=== DAFTAR LANGGANAN AKTIF ===");
        for (Langganan sub : listLangganan) {
            System.out.println("ID Langganan : " + sub.getIdSubscription());
            System.out.println("Layanan      : " + sub.getLayanan().getNamaLayanan() + " (" + sub.getLayanan().getKategori() + ")");
            System.out.println("Harga        : Rp " + sub.getHargaBulanan());
            System.out.println("Bayar Lewat  : " + sub.getMetode().getNamaMetode());
            System.out.println("Tgl Tagihan  : Tanggal " + sub.getTanggalTagihan());
            System.out.println("Status       : " + sub.getStatus());
            System.out.println("------------------------------------");
        }     
            tekanEnterUntukLanjut();
    }

    //CREATE
    private static void tambahLangganan(Scanner scanner){
        System.out.println("=== TAMBAH LANGGANAN BARU ===");
        String idTerakhirSub = listLangganan.isEmpty() ? "Belum ada" : listLangganan.get(listLangganan.size() - 1).getIdSubscription();
        System.out.print("Masukkan ID Subscription baru (ID TERAKHIR = " + idTerakhirSub + "): ");
        String idSub = scanner.nextLine();
        for (Langganan sub : listLangganan) {
            if (sub.getIdSubscription().equalsIgnoreCase(idSub)) {
                System.out.println("ID Subscription tersebut sudah terdaftar!");
                return;
            }
        }

        System.out.println("\n--- PILIH LAYANAN ---");
        for (int i = 0; i < listLayanan.size(); i++) {
            System.out.println((i + 1) + ". " + listLayanan.get(i).getNamaLayanan() + " (" + listLayanan.get(i).getKategori() + ")");
        }
        System.out.println((listLayanan.size() + 1) + ". [Daftarkan Layanan Baru]");
        System.out.print("Pilihan Anda (1-" + (listLayanan.size() + 1) + "): ");
        int pilLayanan = 0;
        if (scanner.hasNextInt()) {
            pilLayanan = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println(" Masukkan angka yang valid!");
            scanner.nextLine();
            return;
        }
        Layanan layananTerpilih = null;
        if (pilLayanan >= 1 && pilLayanan <= listLayanan.size()) {
            layananTerpilih = listLayanan.get(pilLayanan - 1);
        } else if (pilLayanan == listLayanan.size() + 1) {
            String idTerakhirLyn = listLayanan.isEmpty() ? "Belum ada" : listLayanan.get(listLayanan.size() - 1).getIdLayanan();
            System.out.print("Masukkan ID Layanan Baru (ID TERAKHIR = " + idTerakhirLyn + "): ");
            String idLyn = scanner.nextLine();
            System.out.print("Masukkan Nama Layanan Baru (contoh: Disney+): ");
            String namaLyn = scanner.nextLine();
            System.out.print("Masukkan Kategori Layanan (contoh: Hiburan): ");
            String katLyn = scanner.nextLine();

            layananTerpilih = new Layanan(idLyn, namaLyn, katLyn);
            listLayanan.add(layananTerpilih);
            System.out.println("Layanan berhasil di daftarkan ke list!");
        } else {
            System.out.println("Pilihan tidak valid!");
            return;
        }

        System.out.println("\n--- PILIH METODE PEMBAYARAN ---");
        for (int i = 0; i < listMetode.size(); i++) {
            System.out.println((i + 1) + ". " + listMetode.get(i).getNamaMetode() + " (" + listMetode.get(i).getJenis() + ")");
        }
        System.out.println((listMetode.size() + 1) + ". [Daftarkan Metode Pembayaran Baru]");
        System.out.print("Pilihan Anda (1-" + (listMetode.size() + 1) + "): ");

        int pilMetode = 0;
        if (scanner.hasNextInt()) {
            pilMetode = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Masukan angka yang valid!");
            scanner.nextLine();
            return;
        }
        MetodePembayaran metodeTerpilih = null;
        if (pilMetode >= 1 && pilMetode <= listMetode.size()) {
            metodeTerpilih = listMetode.get(pilMetode - 1);
        } else if (pilMetode == listMetode.size() + 1) {
            String idTerakhirMetode = listMetode.isEmpty() ? "Belum ada" : listMetode.get(listMetode.size() - 1).getIdMetode();
            System.out.print("Masukkan ID Metode Baru (ID TERAKHIR = " + idTerakhirMetode + "): ");
            String idPay = scanner.nextLine();
            System.out.print("Masukkan Nama Metode Baru (contoh: ShopeePay): ");
            String namaPay = scanner.nextLine();
            System.out.print("Masukkan Jenis Metode (contoh: E-Wallet): ");
            String jenPay = scanner.nextLine();

            metodeTerpilih = new MetodePembayaran(idPay, namaPay, jenPay);
            listMetode.add(metodeTerpilih);
            System.out.println("Metode pembayaran berhasil di tambahkan!");
        } else {
            System.out.println("pilihan tidak valid!");
            return;
        }
        System.out.print("\nMasukkan Harga Bulanan (contoh: 49000): ");
        double harga = 0;
        if (scanner.hasNextDouble()) {
            harga = scanner.nextDouble();
            scanner.nextLine();
        } else {
            System.out.println("Input harga harus berupa angka! Pembatalan input.");
            scanner.nextLine();
            return;
        }
        System.out.println("Masukan Tanggal tagihan bulanan (1-31): ");
        int tanggal = 0;
        if (scanner.hasNextInt()) {
            tanggal = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Input harus berupa angkat bulat!");
            scanner.nextLine();
            return;
        }
        Langganan langgananBaru = new Langganan(idSub, layananTerpilih, harga, metodeTerpilih, tanggal);
        listLangganan.add(langgananBaru);
        System.out.println("Berhasil mencatat langganan~");
    }
    //UPDATE
    private static void editLangganan(Scanner scanner) {
        System.out.println("=== EDIT DATA LANGGANAN ===");
        System.out.print("Masukkan ID Subscription yang ingin diedit: ");
        String idCari = scanner.nextLine();
        
        Langganan langgananDitemukan = null;
        for (Langganan sub : listLangganan) {
            if (sub.getIdSubscription().equalsIgnoreCase(idCari)) {
                langgananDitemukan = sub;
                break;
            }
        }   
        
        if (langgananDitemukan == null) {
            System.out.println("[Error] Data langganan dengan ID '" + idCari + "' tidak ditemukan!");
            return;
        }   
        System.out.println("\nData saat ini:");
        System.out.println("- Layanan: " + langgananDitemukan.getLayanan().getNamaLayanan());
        System.out.println("- Harga  : Rp " + langgananDitemukan.getHargaBulanan());
        System.out.println("- Status : " + langgananDitemukan.getStatus());
        System.out.println("----------------------------------");
        
        System.out.println("Pilihan edit:");
        System.out.println("1. Ubah Harga Bulanan");
        System.out.println("2. Ubah Status (Aktif / Nonaktif)");
        System.out.println("3. Batal");
        System.out.print("Pilih Aksi (1-3): ");
        
        int pilihanEdit = 0;
        if (scanner.hasNextInt()) {
            pilihanEdit = scanner.nextInt();
            scanner.nextLine(); 
        } else {
            System.out.println("Input tidak valid! Pembatalan edit.");
            scanner.nextLine();
            return;
        }
        
        switch (pilihanEdit) {
            case 1:
                System.out.print("Masukkan Harga Bulanan Baru: ");
                if (scanner.hasNextDouble()) {
                    double hargaBaru = scanner.nextDouble();
                    scanner.nextLine();
                    
                    langgananDitemukan.setHargaBulanan(hargaBaru);
                    System.out.println("Harga bulanan berhasil diperbarui!");
                } else {
                    System.out.println("Input harga harus berupa angka!");
                    scanner.nextLine();
                }
                break;
                
            case 2:
                System.out.println("Ubah status menjadi:");
                System.out.println("1. Aktif");
                System.out.println("2. Nonaktif");
                System.out.print("Pilih status (1-2): ");
                int pilStatus = 0;
                if (scanner.hasNextInt()) {
                    pilStatus = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (pilStatus == 1) {
                        langgananDitemukan.setStatus("Aktif");
                        System.out.println("Status diubah menjadi Aktif.");
                    } else if (pilStatus == 2) {
                        langgananDitemukan.setStatus("Nonaktif");
                        System.out.println("Status diubah menjadi Nonaktif.");
                    } else {
                        System.out.println("Pilihan tidak valid!");
                    }
                } else {
                    System.out.println("Input tidak valid!");
                    scanner.nextLine();
                }
                break;
                
            case 3:
                System.out.println("Batal melakukan perubahan.");
                break;
                
            default:
                System.out.println("Pilihan tidak tersedia!");
        }
    }
    //DELETE
    private static void hapusLangganan(Scanner scanner) {
        System.out.println("=== HAPUS LANGGANAN ===");
        System.out.print("Masukkan ID Subscription yang ingin dihapus: ");
        String idHapus = scanner.nextLine();

        Langganan langgananDitemukan = null;
        for (Langganan sub : listLangganan) {
            if (sub.getIdSubscription().equalsIgnoreCase(idHapus)) {
                langgananDitemukan = sub;
                break;
            }
        }

        if (langgananDitemukan != null) {
            listLangganan.remove(langgananDitemukan);
            System.out.println("Langganan dengan ID '" + idHapus + "' berhasil dihapus!");
        } else {
            System.out.println("[Error] Data langganan dengan ID '" + idHapus + "' tidak ditemukan!");
        }
    }

    private static void hitungTotalPengeluaran(Scanner scanner) {
        System.out.println("=== TOTAL PENGELUARAN BULANAN ===");
        double total = 0;
        int jumlahAktif = 0;
        
        for (Langganan sub : listLangganan) {
            if (sub.getStatus().equalsIgnoreCase("Aktif")) {
                total += sub.getHargaBulanan();
                jumlahAktif++;
            }
        }
        
        System.out.println("Jumlah Layanan Aktif: " + jumlahAktif + " layanan");
        System.out.println("Total Biaya Bulanan : Rp " + total);
        System.out.println("---------------------------------");
        if (total > 500000) {
            System.out.println("Wah, pengeluaran langganan Anda sudah melebihi Rp 500.000! Coba evaluasi layanan yang jarang Anda pakai.");
        } else {
            System.out.println("Pengeluaran Anda masih dalam batas aman. Pertahankan kehemeatan Anda!");
        }
    }

    private static void inisialisasiDataAwal() {
        Layanan Netflix = new Layanan ("LYN01", "Netflix", "Hiburan");
        Layanan Spotify = new Layanan ("LYN02", "Spotify", "Musik");
        Layanan iCloud = new Layanan ("LYN03", "iCloud", "Penyimpanan");
        
        listLayanan.add(Netflix);
        listLayanan.add(Spotify);
        listLayanan.add(iCloud);
        
        MetodePembayaran gopay = new MetodePembayaran("PAY01", "GoPay", "E-Wallet");
        MetodePembayaran cc = new MetodePembayaran("PAY02", "Kartu Kredit", "Credit Card");
        
        listMetode.add(gopay);
        listMetode.add(cc);
        
        listLangganan.add(new Langganan("SUB01", Netflix, 186000, cc, 15));
        listLangganan.add(new Langganan("SUB02", Spotify, 54990, gopay, 20));
        listLangganan.add(new Langganan("SUB03", iCloud, 15000, gopay, 5));

        
    }

    private static void tekanEnterUntukLanjut() {
        System.out.println("Tekan ENTER utk melanjutkan...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    
    }
    
}
