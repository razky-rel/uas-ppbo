/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
import java.util.Scanner;

public class MainKafe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Transaksi transaksi = new Transaksi();
        int pilihan = 0;

        while (pilihan != 5) {
            System.out.println("=== SISTEM KASIR KAFE ===");
            System.out.println("1. Tambah Makanan Ke Pesanan");
            System.out.println("2. Tambah Minuman Ke Pesanan");
            System.out.println("3. Lihat Keranjang & Total Sementara");
            System.out.println("4. Cetak Struk & Bayar");
            System.out.println("5. Keluar Aplikasi");
            System.out.print("Pilih menu (1-5): ");
            
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println("Harap masukkan angka yang valid!");
                scanner.nextLine();
                continue;
            }

            switch (pilihan) {
                case 1:
                    System.out.print("Nama Makanan: ");
                    String namaMakan = scanner.nextLine();
                    System.out.print("Harga Dasar : ");
                    int hargaMakan = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Jenis Karbo (Nasi/Mie/Roti): ");
                    String karbo = scanner.nextLine();

                    
                    Menu makanan = new Makanan(namaMakan, hargaMakan, karbo);
                    transaksi.tambahPesanan(makanan);
                    break;

                case 2:
                    System.out.print("Nama Minuman: ");
                    String namaMinum = scanner.nextLine();
                    System.out.print("Harga Dasar : ");
                    int hargaMinum = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Ukuran Cup (Small/Medium/Large): ");
                    String size = scanner.nextLine();

                    
                    Menu minuman = new Minuman(namaMinum, hargaMinum, size);
                    transaksi.tambahPesanan(minuman);
                    break;

                case 3:
                    transaksi.tampilkanKeranjang();
                    break;

                case 4:
                    int total = transaksi.hitungTotalBelanja();
                    if (total == 0) {
                        System.out.println("Gagal cetak struk: Belum ada item yang dipesan!");
                        break;
                    }
                    
                    System.out.println("Total Belanja Anda: Rp" + total);
                    System.out.print("Masukkan Nominal Uang Bayar: ");
                    int bayar = scanner.nextInt();
                    scanner.nextLine();

                    if (bayar < total) {
                        System.out.println("❌ Uang tidak cukup! Transaksi pembayaran digagalkan.");
                    } else {
                        transaksi.cetakStruk(bayar);
                        transaksi = new Transaksi(); 
                    }
                    break;

                case 5:
                    System.out.println("Menutup sistem kasir kafe. Sampai jumpa!");
                    break;

                default:
                    System.out.println("Pilihan menu salah, silakan coba lagi.");
            }
            System.out.println(); 
        }
        scanner.close();
    }
}
