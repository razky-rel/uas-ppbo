/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
import java.util.ArrayList;

public class Transaksi {
    private ArrayList<Menu> listPesanan;

    public Transaksi() {
        this.listPesanan = new ArrayList<>();
    }

    public void tambahPesanan(Menu item) {
        listPesanan.add(item);
        System.out.println("✓ " + item.getNama() + " berhasil ditambahkan ke keranjang.");
    }

    public int hitungTotalBelanja() {
        int total = 0;
        for (Menu item : listPesanan) {
            total += item.hitungHargaTotal(); 
        }
        return total;
    }

    public void tampilkanKeranjang() {
        if (listPesanan.isEmpty()) {
            System.out.println("Keranjang belanja masih kosong.");
            return;
        }
        
        System.out.println("\n--- ISI KERANJANG SEMENTARA ---");
        for (Menu item : listPesanan) {
            if (item instanceof Minuman) {
                Minuman minum = (Minuman) item;
                String sizeInfo = minum.getUkuranCup();
                
                if (sizeInfo.equalsIgnoreCase("Large")) {
                    sizeInfo += " +5000";
                } else if (sizeInfo.equalsIgnoreCase("Medium")) {
                    sizeInfo += " +2000";
                }
                System.out.println("- " + item.getNama() + " (" + sizeInfo + ") -> Rp" + item.hitungHargaTotal());
            } else {
                System.out.println("- " + item.getNama() + " -> Rp" + item.hitungHargaTotal());
            }
        }
        
        System.out.println("-------------------------------");
        System.out.println("Total Sementara : Rp" + hitungTotalBelanja());
        System.out.println("-------------------------------");
    }

    public void cetakStruk(int uangBayar) {
        System.out.println("\n=============================================");
        System.out.println("               STRUK NOTA KAFE               ");
        System.out.println("=============================================");
        
        for (Menu item : listPesanan) {
            item.tampilkanDetail();
        }

        int total = hitungTotalBelanja();
        int kembalian = uangBayar - total;

        System.out.println("TOTAL BELANJA : Rp" + total);
        System.out.println("UANG BAYAR    : Rp" + uangBayar);
        System.out.println("KEMBALIAN     : Rp" + kembalian);
        System.out.println("=============================================");
        System.out.println("     Terima Kasih Atas Kunjungan Anda!       ");
        System.out.println("=============================================\n");
    }
}
