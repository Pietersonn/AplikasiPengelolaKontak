/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasipengelolakontak;


public class Contact {
    private int id;
    private String nama;
    private String nomorTelepon;
    private String kategori;

    // Constructor tanpa id untuk kontak baru
    public Contact(String nama, String nomorTelepon, String kategori) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.kategori = kategori;
    }

    // Constructor dengan id untuk kontak yang sudah ada
    public Contact(int id, String nama, String nomorTelepon, String kategori) {
        this.id = id;
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.kategori = kategori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}

