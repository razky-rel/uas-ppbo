/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainForm extends JFrame {
    private JTable tableMenu;
    private DefaultTableModel tableModel;
    private JTextArea txtStruk;
    private JTextField txtBayar;
    private Transaksi transaksi; 

    public MainForm() {
        transaksi = new Transaksi();
        setTitle("SISTEM KASIR KAFE MODERN OOP");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        
        JLabel lblHeader = new JLabel("KAFE OOP - MANAGEMENT SYSTEM", JLabel.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblHeader, BorderLayout.NORTH);

        // Bagian Kiri: Tabel Menu dari Database 
        JPanel panelKiri = new JPanel(new BorderLayout());
        String[] kolom = {"Nama Menu", "Harga Dasar", "Tipe", "Detail"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableMenu = new JTable(tableModel);
        panelKiri.add(new JScrollPane(tableMenu), BorderLayout.CENTER);
        
        JPanel panelTombolKiri = new JPanel(new GridLayout(1, 2, 5, 5));

        JButton btnTambah = new JButton("Tambah ke Keranjang");
        JButton btnReset = new JButton("Reset Keranjang");

        panelTombolKiri.add(btnTambah);
        panelTombolKiri.add(btnReset);

        panelKiri.add(panelTombolKiri, BorderLayout.SOUTH);
        add(panelKiri, BorderLayout.WEST);

        // Bagian Kanan: Struk Nota & Pembayaran 
        JPanel panelKanan = new JPanel(new BorderLayout(5, 5));
        txtStruk = new JTextArea(20, 35);
        txtStruk.setEditable(false);
        txtStruk.setFont(new Font("Monospaced", Font.PLAIN, 12));
        panelKanan.add(new JScrollPane(txtStruk), BorderLayout.CENTER);

        // Panel Input Bayar & Tombol Cetak
        JPanel panelBayar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBayar.add(new JLabel("Uang Bayar: Rp"));
        txtBayar = new JTextField(10);
        panelBayar.add(txtBayar);
        
        JButton btnBayar = new JButton("Cetak Struk & Bayar");
        panelBayar.add(btnBayar);
        panelKanan.add(panelBayar, BorderLayout.SOUTH);
        add(panelKanan, BorderLayout.CENTER);

        
        
        // Aksi Tombol Tambah ke Keranjang (Polimorfisme Dinamis)
        btnTambah.addActionListener(e -> {
            int barisTerpilih = tableMenu.getSelectedRow();
            if (barisTerpilih == -1) {
                JOptionPane.showMessageDialog(this, "Silakan pilih menu terlebih dahulu!");
                return;
            }

            String nama = tableModel.getValueAt(barisTerpilih, 0).toString();
            int harga = Integer.parseInt(tableModel.getValueAt(barisTerpilih, 1).toString());
            String tipe = tableModel.getValueAt(barisTerpilih, 2).toString();
            String atribut = tableModel.getValueAt(barisTerpilih, 3).toString();

            Menu item;
            if (tipe.equalsIgnoreCase("Makanan")) {
                item = new Makanan(nama, harga, atribut); // Polimorfisme objek anak
            } else {
                item = new Minuman(nama, harga, atribut); // Polimorfisme objek anak
            }

            transaksi.tambahPesanan(item);
            updateTampilanStrukSementara();
        });

        // Aksi Tombol Cetak Struk & Bayar
        btnBayar.addActionListener(e -> {
            int total = transaksi.hitungTotalBelanja();
            if (total == 0) {
                JOptionPane.showMessageDialog(this, "Keranjang masih kosong!");
                return;
            }

            try {
                int uangBayar = Integer.parseInt(txtBayar.getText());
                if (uangBayar < total) {
                    JOptionPane.showMessageDialog(this, "Uang pembayaran tidak cukup!");
                    return;
                }

                int kembalian = uangBayar - total;
                StringBuilder sb = new StringBuilder();
                sb.append("=============================================\n");
                sb.append("               STRUK NOTA KAFE               \n");
                sb.append("=============================================\n\n");
                
                // Perulangan untuk memunculkan detail nama dan harga item di GUI
                for (Menu item : transaksi.getListPesanan()) {
                    sb.append("[").append(item instanceof Makanan ? "Makanan" : "Minuman").append("] ").append(item.getNama());
                    if (item instanceof Makanan) {
                        sb.append(" (").append(((Makanan)item).getJenisKarbo()).append(")\n");
                    } else {
                        sb.append(" (Size: ").append(((Minuman)item).getUkuranCup()).append(")\n");
                    }
                    sb.append("          Harga Dasar : Rp").append(item.getHargaDasar()).append("\n");
                    sb.append("          Harga Total : Rp").append(item.hitungHargaTotal()).append("\n");
                    sb.append("---------------------------------------------\n");
                }
                
                sb.append("\nTOTAL BELANJA : Rp").append(total).append("\n");
                sb.append("UANG BAYAR    : Rp").append(uangBayar).append("\n");
                sb.append("KEMBALIAN     : Rp").append(kembalian).append("\n");
                sb.append("=============================================\n");
                sb.append("     Terima Kasih Atas Kunjungan Anda!       \n");
                sb.append("=============================================\n");
                
                txtStruk.setText(sb.toString());
                JOptionPane.showMessageDialog(this, "Transaksi Berhasil!");
                
                // Reset data kasir
                transaksi = new Transaksi();
                txtBayar.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Masukkan nominal uang bayar yang valid!");
            }
        });
        
        // Aksi Tombol Reset Keranjang Belanja
        btnReset.addActionListener(e -> {
            int konfirmasi = JOptionPane.showConfirmDialog(this, 
                    "Apakah Anda yakin ingin mengosongkan seluruh isi keranjang?", 
                    "Konfirmasi Reset", JOptionPane.YES_NO_OPTION);
            
            if (konfirmasi == JOptionPane.YES_OPTION) {
                transaksi = new Transaksi(); 
                txtStruk.setText("--- KERANJANG BELANJA KOSONG ---\n\nSemua item pesanan berhasil dihapus.");
                txtBayar.setText("");
                JOptionPane.showMessageDialog(this, "Keranjang belanja berhasil dikosongkan!");
            }
        });

        loadDataDariDatabase();
    }

    // Fungsi mengambil data dari MySQL dan memasukkannya ke JTable GUI
    private void loadDataDariDatabase() {
        try {
            Connection conn = Koneksi.getKoneksi();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tbl_menu");

            while (rs.next()) {
                String nama = rs.getString("nama");
                int harga = rs.getInt("harga_dasar");
                String tipe = rs.getString("tipe");
                String atribut = rs.getString("atribut");
                
                tableModel.addRow(new Object[]{nama, harga, tipe, atribut});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data database: " + e.getMessage());
        }
    }

    // Menampilkan isi list pesanan sementara di layar JTextArea
    private void updateTampilanStrukSementara() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- KERANJANG BELANJA SEMENTARA ---\n\n");
        
        for (Menu item : transaksi.getListPesanan()) {
            if (item instanceof Minuman) {
                Minuman minum = (Minuman) item;
                String sizeInfo = minum.getUkuranCup();
                if (sizeInfo.equalsIgnoreCase("Large")) {
                    sizeInfo += " +5000";
                } else if (sizeInfo.equalsIgnoreCase("Medium")) {
                    sizeInfo += " +2000";
                }
                sb.append("- ").append(item.getNama()).append(" (").append(sizeInfo).append(") -> Rp").append(item.hitungHargaTotal()).append("\n");
            } else {
                sb.append("- ").append(item.getNama()).append(" -> Rp").append(item.hitungHargaTotal()).append("\n");
            }
        }
        
        sb.append("-----------------------------------\n");
        sb.append("Total Saat Ini: Rp").append(transaksi.hitungTotalBelanja()).append("\n\n");
        sb.append("Silakan klik Cetak & Bayar untuk memproses.");
        
        txtStruk.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainForm().setVisible(true));
    }
}
