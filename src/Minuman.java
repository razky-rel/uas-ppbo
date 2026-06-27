/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */

public class Minuman extends Menu {
    private String ukuranCup; 

    public Minuman(String nama, int hargaDasar, String ukuranCup) {
        super(nama, hargaDasar); 
        this.ukuranCup = ukuranCup;
    }

    public String getUkuranCup() {
        return ukuranCup;
    }

    @Override
    public int hitungHargaTotal() {
        if (ukuranCup.equalsIgnoreCase("Large")) {
            return getHargaDasar() + 5000;
        } else if (ukuranCup.equalsIgnoreCase("Medium")) {
            return getHargaDasar() + 2000;
        }
        return getHargaDasar();
    }

    @Override
    public void tampilkanDetail() {
        System.out.println("[Minuman] " + getNama() + " (Size: " + ukuranCup + ")");
        System.out.println("          Harga Dasar : Rp" + getHargaDasar());
        System.out.println("          Harga Total : Rp" + hitungHargaTotal());
        System.out.println("---------------------------------------------");
    }
}
