/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Fareru
 */
public class MetodePembayaran {
    
    private String idMetode;
    private String namaMetode;
    private String jenis;
    
    public MetodePembayaran(String idMetode, String  namaMetode, String jenis){
        this.idMetode=idMetode;
        this.namaMetode=namaMetode;
        this.jenis=jenis;

    }

    public String getIdMetode() {
        return idMetode;    
    }
    public String getNamaMetode() {
        return namaMetode;
    }   
    public String getJenis(){
        return jenis;
    }
    
    public void setidMetode(String idMetode){
        this.idMetode=idMetode;
   
    }
    public void setNamaMetode(String namaMetode){
        this.namaMetode=namaMetode;
    }
    public void setJenis(String jenis) {
        this.jenis=jenis;
    }
 }
