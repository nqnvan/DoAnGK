public class SinhVien {
    private String mssv;
    private String hoTen;
    private Diem diemThi = new Diem();

    public SinhVien() { }

    public SinhVien(String mssv, String hoTen) {
        this.hoTen = hoTen;
        this.mssv = mssv;
        diemThi = new Diem();
    }

    public SinhVien(SinhVien sv) {
        this.hoTen = sv.getHoTen();
        this.mssv = sv.getMssv();
        this.diemThi = sv.getDiemThi();
    }

    public SinhVien(String hoTen, String mssv, Diem diemThi) {
        this.hoTen = hoTen;
        this.mssv = mssv;
        this.diemThi = diemThi;
    }

    public void nhapHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void nhapMSSV(String mssv) {
        this.mssv = mssv;
    }

    public void print() {
        String output = "MSSV: " + mssv + "\n" +
                "Ho ten: " + hoTen;
        System.out.println(output);
        diemThi.print();
    }

    public int capNhatThongTin() {
        int soLanCapNhat = 0;
        while (true) {
            Utility.xuatThongBao("" +
                    "+-------+-------------------------+\n" +
                    "| Input |         Lua chon        |\n" +
                    "+-------+-------------------------+\n" +
                    "|   0   | Thoat khoi cap nhat!    |\n" +
                    "+-------+-------------------------+\n" +
                    "|   1   | Cap nhat ho ten         |\n" +
                    "+-------+-------------------------+\n" +
                    "|   2   | Nhap diem seminar       |\n" +
                    "+-------+-------------------------+\n" +
                    "|   3   | Nhap diem do an giua ky |\n" +
                    "+-------+-------------------------+\n" +
                    "|   4   | Nhap diem cuoi ky       |\n" +
                    "+-------+-------------------------+");
            int option = Utility.inputInt("Chon thong tin can cap nhat: ");
            soLanCapNhat += option;
            switch (option) {
                case 0:
                    return soLanCapNhat;
                case 1:
                    String hoTen = Utility.inputString("Nhap ho ten: ");
                    this.nhapHoTen(hoTen);
                    break;
                default:
                    this.getDiemThi().capNhatThongTin(option);
                    break;
            }
        }
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Diem getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(Diem diemThi) {
        this.diemThi = diemThi;
    }

}
