import java.text.DecimalFormat;

public class Diem {
    private double seminar;
    private double doAnGiuaKy;
    private double cuoiKy;

    public Diem() {
    }

    public Diem(double seminar, double doAnGiuaKy, double cuoiKy) {
        this.seminar = seminar;
        this.doAnGiuaKy = doAnGiuaKy;
        this.cuoiKy = cuoiKy;
    }

    public void nhapDiemSeminar() {
        double seminar = Utility.inputDouble("Nhap diem seminar: ");
        this.setSeminar(seminar);
    }

    public void nhapDiemDoAnGiuaKy() {
        double doAnGK = Utility.inputDouble("Nhap diem do an giua ky: ");
        this.setDoAnGiuaKy(doAnGK);
    }

    public void nhapDiemCuoiKy() {
        double cuoiKy = Utility.inputDouble("Nhap diem cuoi ky: ");
        this.setCuoiKy(cuoiKy);
    }

    public double xuatDiemTongKet() {
        double ketqua = seminar * 0.2 + doAnGiuaKy * 0.2 + cuoiKy * 0.6;
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(ketqua));
    }

    public double xuatDiemSeminar() {
        return seminar;
    }

    public double xuatDiemDoAnGiuaKy() {
        return doAnGiuaKy;
    }

    public double xuatDiemCuoiKy() {
        return cuoiKy;
    }

    public void print() {
        String output = "Diem seminar: " + xuatDiemSeminar() + "\n" +
                "Diem do an giua ky: " + xuatDiemDoAnGiuaKy() + "\n" +
                "Diem cuoi ky: " + xuatDiemCuoiKy() + "\n" +
                "Diem tong ket: " + xuatDiemTongKet();
        Utility.xuatThongBao(output);
    }

    public void capNhatThongTin(int option) {
        switch (option) {
            case 2:
                this.nhapDiemSeminar();
                break;
            case 3:
                this.nhapDiemDoAnGiuaKy();
                break;
            case 4:
                this.nhapDiemCuoiKy();
                break;
            default:
                Utility.xuatThongBao("Lua chon khong ho tro. Vui long chon lai!");
                break;
        }
    }


    public double getSeminar() {
        return seminar;
    }

    public void setSeminar(double seminar) {
        this.seminar = seminar;
    }

    public double getDoAnGiuaKy() {
        return doAnGiuaKy;
    }

    public void setDoAnGiuaKy(double doAnGiuaKy) {
        this.doAnGiuaKy = doAnGiuaKy;
    }

    public double getCuoiKy() {
        return cuoiKy;
    }

    public void setCuoiKy(double cuoiKy) {
        this.cuoiKy = cuoiKy;
    }

}
