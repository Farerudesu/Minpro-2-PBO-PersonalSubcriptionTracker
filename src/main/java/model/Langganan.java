/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Fareru
 */
public class Langganan {
    private String idSubscription;
    private Layanan layanan;
    private double hargaBulanan;
    private MetodePembayaran metode;
    private int tanggalTagihan;
    private String status;

    public Langganan(String idSubscription, Layanan layanan, double hargaBulanan, MetodePembayaran metode, int tanggalTagihan) {
        this.idSubscription = idSubscription;
        this.layanan = layanan;
        this.setHargaBulanan(hargaBulanan);
        this.metode = metode;
        this.setTanggalTagihan(tanggalTagihan);
        this.status = "Aktif";
    }

    public String getIdSubscription() {
        return idSubscription;
    }

    public Layanan getLayanan() {
        return layanan;
    }

    public double getHargaBulanan() {
        return hargaBulanan;
    }

    public MetodePembayaran getMetode() {
        return metode;
    }

    public int getTanggalTagihan() {
        return tanggalTagihan;
    }

    public String getStatus() {
        return status;    
    }
    
    public void setIdSubscription(String idSubscription) {
        this.idSubscription = idSubscription;
    }

    public void setLayanan(Layanan layanan) {
        this.layanan = layanan;
    }

    public void setHargaBulanan(double hargaBulanan) {
        if (hargaBulanan >= 0) {
            this.hargaBulanan = hargaBulanan;
        } else {
            System.out.println("Harga Bulanan tidak boleh kurang dari 0!, Default ke 0");
            this.hargaBulanan = 0;
        }
    }

    public void setMetode(MetodePembayaran metode) {
        this.metode = metode;
    } 

    public void setTanggalTagihan(int tanggalTagihan) {
        if (tanggalTagihan >= 1 && tanggalTagihan <= 31) {
            this.tanggalTagihan = tanggalTagihan;
        } else {
            System.out.println("Tanggal tagihan tidak valid, Default ke 1");
            this.tanggalTagihan = 1;       
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
