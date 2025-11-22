class Transaksi {
    private String idTransaksi;
    private String jenis;
    private double jumlah;
    private Rekening rekening;

    public Transaksi(String idTransaksi, String jenis, double jumlah, Rekening rekening) {
        this.idTransaksi = idTransaksi;
        this.jenis = jenis;
        this.jumlah = jumlah;
        this.rekening = rekening;
    }

    public void prosesTransaksi() {
        System.out.println("\n--- Memproses Transaksi ---");
        System.out.println("ID: " + idTransaksi);
        System.out.println("Jenis: " + jenis);
        System.out.println("Jumlah: Rp " + jumlah);
        System.out.println("Rekening: " + rekening.getNomorRekening());

        switch (jenis.toLowerCase()) {
            case "deposit":
                rekening.deposit(jumlah);
                break;
            case "tarik":
                rekening.tarik(jumlah);
                break;
            default:
                System.out.println("Jenis transaksi tidak valid!");
        }
    }

    public void cetakBukti() {
        System.out.println("\n--- Bukti Transaksi ---");
        System.out.println("ID Transaksi: " + idTransaksi);
        System.out.println("Rekening: " + rekening.getNomorRekening());
        System.out.println("Pemilik: " + rekening.getNamaPemilik());
        System.out.println("Jenis: " + jenis);
        System.out.println("Jumlah: Rp " + jumlah);
        System.out.println("Saldo Akhir: Rp " + rekening.getSaldo());
    }

    public String getIdTransaksi() { return idTransaksi; }
    public Rekening getRekening() { return rekening; }
}
