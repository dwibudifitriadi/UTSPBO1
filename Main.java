import java.util.ArrayList;
import java.util.Scanner;
public class Main {
        private static ArrayList<Rekening> daftarRekening = new ArrayList<>();
    private static ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int transaksiCounter = 1;

    public static void main(String[] args) {
        while (true) {
            tampilkanMenu();
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahDataRekening();
                    break;
                case 2:
                    tampilkanDataRekening();
                    break;
                case 3:
                    cariDataRekening();
                    break;
                case 4:
                    ubahDataRekening();
                    break;
                case 5:
                    cekStatusRekening();
                    break;
                case 6:
                    lakukanTransaksi();
                    break;
                case 7:
                    System.out.println("Terima kasih telah menggunakan Bank Mini!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void tampilkanMenu() {
        System.out.println("\n=== APLIKASI REKENING BANK MINI ===");
        System.out.println("1. Tambah Data Rekening");
        System.out.println("2. Tampilkan Data Rekening");
        System.out.println("3. Cari Data Rekening");
        System.out.println("4. Ubah Data Rekening");
        System.out.println("5. Cek Status Rekening");
        System.out.println("6. Lakukan Transaksi");
        System.out.println("7. Keluar");
        System.out.print("Pilih menu: ");
    }

    private static void tambahDataRekening() {
        System.out.println("\n--- Tambah Rekening Baru ---");
        System.out.print("Jenis Rekening (1. Tabungan / 2. Giro): ");
        int jenis = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nomor Rekening: ");
        String noRek = scanner.nextLine();
        System.out.print("Nama Pemilik: ");
        String nama = scanner.nextLine();
        System.out.print("Saldo Awal: Rp ");
        double saldo = scanner.nextDouble();

        if (jenis == 1) {
            System.out.print("Suku Bunga (%): ");
            double bunga = scanner.nextDouble();
            System.out.print("Batas Penarikan/Bulan: ");
            int batas = scanner.nextInt();
            daftarRekening.add(new RekeningTabungan(noRek, nama, saldo, bunga, batas));
        } else if (jenis == 2) {
            System.out.print("Batas Overdraft: Rp ");
            double overdraft = scanner.nextDouble();
            System.out.print("Biaya Admin/Bulan: Rp ");
            double biaya = scanner.nextDouble();
            daftarRekening.add(new RekeningGiro(noRek, nama, saldo, overdraft, biaya));
        } else {
            System.out.println("Jenis rekening tidak valid!");
            return;
        }
        System.out.println("Rekening berhasil ditambahkan!");
    }

    private static void tampilkanDataRekening() {
        System.out.println("\n--- Daftar Semua Rekening ---");
        if (daftarRekening.isEmpty()) {
            System.out.println("Belum ada data rekening!");
            return;
        }

        for (int i = 0; i < daftarRekening.size(); i++) {
            Rekening r = daftarRekening.get(i);
            System.out.println((i + 1) + ". " + r.getNomorRekening() + " - " + 
                r.getNamaPemilik() + " - Rp " + r.getSaldo() + 
                " (" + (r instanceof RekeningTabungan ? "Tabungan" : "Giro") + ")");
        }
    }

    private static void cariDataRekening() {
        System.out.print("\nMasukkan nomor rekening yang dicari: ");
        String noRek = scanner.nextLine();

        for (Rekening r : daftarRekening) {
            if (r.getNomorRekening().equals(noRek)) {
                System.out.println("\n--- Data Ditemukan ---");
                System.out.println("Nomor Rekening: " + r.getNomorRekening());
                System.out.println("Nama Pemilik: " + r.getNamaPemilik());
                System.out.println("Saldo: Rp " + r.getSaldo());
                System.out.println("Jenis: " + (r instanceof RekeningTabungan ? "Tabungan" : "Giro"));
                return;
            }
        }
        System.out.println("Rekening tidak ditemukan!");
    }

    private static void ubahDataRekening() {
        System.out.print("\nMasukkan nomor rekening yang akan diubah: ");
        String noRek = scanner.nextLine();

        for (Rekening r : daftarRekening) {
            if (r.getNomorRekening().equals(noRek)) {
                System.out.print("Nama baru: ");
                String namaBaru = scanner.nextLine();
                r.setNamaPemilik(namaBaru);

                if (r instanceof RekeningTabungan) {
                    System.out.print("Suku bunga baru (%): ");
                    double bungaBaru = scanner.nextDouble();
                    ((RekeningTabungan) r).setSukuBunga(bungaBaru);
                } else {
                    System.out.print("Batas overdraft baru: Rp ");
                    double overdraftBaru = scanner.nextDouble();
                    ((RekeningGiro) r).setBatasOverdraft(overdraftBaru);
                }
                System.out.println("Data berhasil diubah!");
                return;
            }
        }
        System.out.println("Rekening tidak ditemukan!");
    }

    private static void cekStatusRekening() {
        System.out.print("\nMasukkan nomor rekening: ");
        String noRek = scanner.nextLine();

        for (Rekening r : daftarRekening) {
            if (r.getNomorRekening().equals(noRek)) {
                System.out.println("\n--- Status Rekening ---");
                r.cekSaldo();
                
                if (r instanceof RekeningTabungan) {
                    RekeningTabungan rt = (RekeningTabungan) r;
                    rt.hitungBunga();
                    rt.cekBatasPenarikan();
                } else {
                    RekeningGiro rg = (RekeningGiro) r;
                    rg.cekOverdraft();
                    rg.bayarBiayaAdmin();
                }
                return;
            }
        }
        System.out.println("Rekening tidak ditemukan!");
    }

    private static void lakukanTransaksi() {
        System.out.print("\nMasukkan nomor rekening: ");
        String noRek = scanner.nextLine();

        Rekening target = null;
        for (Rekening r : daftarRekening) {
            if (r.getNomorRekening().equals(noRek)) {
                target = r;
                break;
            }
        }

        if (target == null) {
            System.out.println("Rekening tidak ditemukan!");
            return;
        }

        System.out.print("Jenis transaksi (deposit/tarik): ");
        String jenis = scanner.nextLine();
        System.out.print("Jumlah: Rp ");
        double jumlah = scanner.nextDouble();

        String idTransaksi = "TXN" + transaksiCounter++;
        Transaksi t = new Transaksi(idTransaksi, jenis, jumlah, target);
        daftarTransaksi.add(t);
        t.prosesTransaksi();
        t.cetakBukti();
    }
}