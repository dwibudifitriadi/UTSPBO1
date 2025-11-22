class Rekening {
    private String nomorRekening;
    private String namaPemilik;
    private double saldo;

    public Rekening(String nomorRekening, String namaPemilik, double saldo) {
        this.nomorRekening = nomorRekening;
        this.namaPemilik = namaPemilik;
        this.saldo = saldo;
    }

    public String getNomorRekening() { return nomorRekening; }
    public String getNamaPemilik() { return namaPemilik; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
    public void setNamaPemilik(String namaPemilik) { this.namaPemilik = namaPemilik; }

    public void cekSaldo() {
        System.out.println("Saldo rekening " + nomorRekening + ": Rp " + saldo);
    }

    public void deposit(double jumlah) {
        if (jumlah > 0) {
            saldo += jumlah;
            System.out.println("Deposit berhasil! Saldo baru: Rp " + saldo);
        } else {
            System.out.println("Jumlah deposit harus positif!");
        }
    }

    public boolean tarik(double jumlah) {
        if (jumlah > 0 && jumlah <= saldo) {
            saldo -= jumlah;
            System.out.println("Penarikan berhasil! Saldo baru: Rp " + saldo);
            return true;
        } else {
            System.out.println("Saldo tidak mencukupi atau jumlah invalid!");
            return false;
        }
    }
}
