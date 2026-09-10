/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Fareru
 */
public class Layanan {
    private String idLayanan;
    private String namaLayanan;
    private String kategori;
    

    public Layanan(String idLayanan, String namaLayanan, String kategori){
        this.idLayanan = idLayanan;
        this.namaLayanan = namaLayanan;
        this.kategori = kategori;


        }
    public String getIdLayanan(){
        return idLayanan;
    }
    public String getNamaLayanan(){
        return namaLayanan;

    }
    public String getKategori() {
        return kategori;
    }   

    public void setIdLayanan(String idLayanan){
        this.idLayanan=idLayanan;
        
        }
    public void SetNamaLayanan(String namaLayanan){
        this.namaLayanan=namaLayanan;
    
    }
    public void SetKategori(String kategori){
        this.kategori=kategori;
    }
}