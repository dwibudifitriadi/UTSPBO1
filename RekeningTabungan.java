class RekeningTabungan extends Rekening {
    private double sukuBunga;
    private int batasPenarikan;

    public RekeningTabungan(String nomorRekening, String namaPemilik, double saldo, double sukuBunga, int batasPenarikan) {
        super(nomorRekening, namaPemilik, saldo);
        this.sukuBunga = sukuBunga;
        this.batasPenarikan = batasPenarikan;
    }

    public void hitungBunga() {
        double bunga = getSaldo() * sukuBunga / 100;
        System.out.println("Bunga bulan ini: Rp " + bunga);
        deposit(bunga);
    }

    public void cekBatasPenarikan() {
        System.out.println("Batas penarikan bulanan: " + batasPenarikan + " kali");
    }

    @Override
    public boolean tarik(double jumlah) {
        if (batasPenarikan > 0) {
            boolean success = super.tarik(jumlah);
            if (success) batasPenarikan--;
            return success;
        } else {
            System.out.println("Batas penarikan bulanan telah habis!");
            return false;
        }
    }

    public double getSukuBunga() { return sukuBunga; }
    public void setSukuBunga(double sukuBunga) { this.sukuBunga = sukuBunga; }
    public int getBatasPenarikan() { return batasPenarikan; }
}