/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */

public class Makanan extends Menu {
    private String jenisKarbo; 

    public Makanan(String nama, int hargaDasar, String jenisKarbo) {
        super(nama, hargaDasar); 
        this.jenisKarbo = jenisKarbo;
    }

    
    public String getJenisKarbo() {
        return jenisKarbo;
    }

   
    @Override
    public int hitungHargaTotal() {
        return getHargaDasar() + 2000;
    }

    @Override
    public void tampilkanDetail() {
        System.out.println("[Makanan] " + getNama() + " (" + jenisKarbo + ")");
        System.out.println("          Harga Dasar : Rp" + getHargaDasar());
        System.out.println("          Harga Total : Rp" + hitungHargaTotal());
        System.out.println("---------------------------------------------");
    }
}
