package com.pbo.fareru.minpro.pbo.model;

public class LanggananStreaming extends Langganan {
    private String kualitasResolusi;
    private int batasLayar;

    public LanggananStreaming(String idSubscription, Layanan layanan, double hargaBulanan,
                              MetodePembayaran metode, int tanggalTagihan,
                              String kualitasResolusi, int batasLayar) {
        super(idSubscription, layanan, hargaBulanan, metode, tanggalTagihan);
        this.kualitasResolusi = kualitasResolusi;
        setBatasLayar(batasLayar);
    }

    public String getKualitasResolusi() {
        return kualitasResolusi;
    }

    public void setKualitasResolusi(String kualitasResolusi) {
        if (kualitasResolusi != null && !kualitasResolusi.trim().isEmpty()) {
            this.kualitasResolusi = kualitasResolusi;
        }
    }

    public int getBatasLayar() {
        return batasLayar;
    }

    public void setBatasLayar(int batasLayar) {
        if (batasLayar >= 1) {
            this.batasLayar = batasLayar;
        } else {
            this.batasLayar = 1;
        }
    }

    @Override
    public String getTipeLangganan() {
        return "Streaming (Video/Audio)";
    }

    @Override
    public void tampilkanDetail() {
        super.tampilkanDetail();
        System.out.println("Resolusi     : " + kualitasResolusi);
        System.out.println("Batas Layar  : " + batasLayar + " Perangkat / Layar");
    }

    @Override
    public double hitungEstimasiBiaya(int bulan) {
        if (bulan <= 0) {
            return 0;
        }
        double total = getHargaBulanan() * bulan;
        if (bulan >= 12) {
            return total * 0.95;
        }
        return total;
    }
}
