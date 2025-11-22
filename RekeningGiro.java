class RekeningGiro extends Rekening {
    private double batasOverdraft;
    private double biayaAdmin;

    public RekeningGiro(String nomorRekening, String namaPemilik, double saldo, double batasOverdraft, double biayaAdmin) {
        super(nomorRekening, namaPemilik, saldo);
        this.batasOverdraft = batasOverdraft;
        this.biayaAdmin = biayaAdmin;
    }

    public void cekOverdraft() {
        System.out.println("Batas overdraft: Rp " + batasOverdraft);
    }

    public void bayarBiayaAdmin() {
        if (super.tarik(biayaAdmin)) {
            System.out.println("Biaya admin berhasil dibayar!");
        } else {
            System.out.println("Gagal bayar biaya admin!");
        }
    }

    @Override
    public boolean tarik(double jumlah) {
        if (jumlah > 0 && (getSaldo() - jumlah) >= -batasOverdraft) {
            setSaldo(getSaldo() - jumlah);
            System.out.println("Penarikan berhasil! Saldo baru: Rp " + getSaldo());
            return true;
        } else {
            System.out.println("Melebihi batas overdraft atau jumlah invalid!");
            return false;
        }
    }

    public double getBatasOverdraft() { return batasOverdraft; }
    public void setBatasOverdraft(double batasOverdraft) { this.batasOverdraft = batasOverdraft; }
    public double getBiayaAdmin() { return biayaAdmin; }
}