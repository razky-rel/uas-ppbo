/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
public abstract class Menu {
    private String nama;
    private int hargaDasar;
    
    public Menu(String nama, int hargaDasar) {
        this.nama = nama;
        this.hargaDasar = hargaDasar;
    }
    
    public String getNama(){
        return nama;
    }
    
    public int getHargaDasar(){
        return hargaDasar;
    }
    
    public void setHargaDasar(int hargaDasar){
        this.hargaDasar = hargaDasar;
    }
    
    public abstract int hitungHargaTotal();
    public abstract void tampilkanDetail();
}
