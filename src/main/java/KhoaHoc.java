import java.util.*;

public class KhoaHoc {
    private String m_tenKhoaHoc;
    private LinkedHashMap<String, SinhVien> m_sinhVien = new LinkedHashMap<>();

    public KhoaHoc() {
        this("HE DIEU HANH DI DONG");
    }

    public KhoaHoc(String tenKhoaHoc) {
        m_tenKhoaHoc = tenKhoaHoc;
    }

    public void themSinhVien() {
        String mssv = Utility.inputString("Nhap MSSV: ");
        if (m_sinhVien.containsKey(mssv)) {
            Utility.xuatThongBao(String.format("MSSV %s da ton tai trong khoa hoc, vui long nhap MSSV khac!", mssv));
            return;
        } else {
            String hoTen = Utility.inputString("Nhap ho ten: ");
            SinhVien sv = new SinhVien(mssv, hoTen);
            this.printAsTable(Arrays.asList(sv));
            m_sinhVien.put(sv.getMssv(), sv);
            Utility.xuatThongBao("Them sinh vien vao khoa hoc thanh cong!");
        }
    }

    public void docDanhSachSinhVien(String filePath) {
        if (filePath == null) {
            filePath = Utility.inputString("Nhap duong dan den file danh sach sinh vien: ");
        }
        List<SinhVien> danhSach = Utility.getDanhSachSinhVienFromFile(filePath);
        Utility.xuatThongBao(String.format("Danh sach sinh vien doc duoc tu file %s", filePath));
        printAsTable(danhSach);
        for (SinhVien sv : danhSach) {
            m_sinhVien.put(sv.getMssv(), sv);
        }
        Utility.xuatThongBao("Nhap danh sach sinh vien vao khoa hoc thanh cong!");
    }

    public void xuatDanhSachTatCaSinhVien() {
        printAsTable(Utility.convertToList(m_sinhVien));
    }

    public SinhVien timKiemSinhVienTheoMssv(String mssv) {
        if (mssv == null) {
            mssv = Utility.inputString("Nhap MSSV can tim kiem: ");
        }
        SinhVien sv;
        if (!m_sinhVien.containsKey(mssv)) {
            Utility.xuatThongBao(String.format("Khong tim thay sinh vien co MSSV %s", mssv));
            return null;
        } else {
            sv = m_sinhVien.get(mssv);
            printAsTable(Arrays.asList(sv));
        }
        return sv;
    }

    public void capNhatSinhVien() {
        SinhVien sinhvien = timKiemSinhVienTheoMssv(null);
        if (sinhvien != null) {
            SinhVien newSinhVien = new SinhVien(sinhvien);
            int soLanCapNhat = newSinhVien.capNhatThongTin();
            if (soLanCapNhat > 0) {
                printAsTable(Arrays.asList(newSinhVien));
                String confirm = Utility.inputString(String.format("Xac nhan cap nhat MSSV %s [Y/n]: ", newSinhVien.getMssv()));
                if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
                    this.m_sinhVien.put(newSinhVien.getMssv(), newSinhVien);
                }
            }
        }
    }

    public void xoaSinhVien(String mssv) {
        if (mssv == null) {
            mssv = Utility.inputString("Nhap MSSV can xoa: ");
        }
        if (!m_sinhVien.containsKey(mssv)) {
            Utility.xuatThongBao(String.format("MSSV %s khong ton tai trong khoa hoc!", mssv));
        } else {
            this.printAsTable(Arrays.asList(m_sinhVien.get(mssv)));
            String confirm = Utility.inputString(String.format("Xac nhan xoa MSSV %s [Y/n]: ", mssv));
            if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
                m_sinhVien.remove(mssv);
            }
            Utility.xuatThongBao(String.format("Xoa MSSV %s khoi khoa hoc thanh cong!", mssv));
        }
    }

    public void sapXepSinhVienTheoMSSV() {
        TreeMap<String, SinhVien> treeMap = new TreeMap<>(this.m_sinhVien);
        this.m_sinhVien.clear();
        for (String key : treeMap.keySet()) {
            this.m_sinhVien.put(treeMap.get(key).getMssv(), treeMap.get(key));
        }
        this.printAsTable(Utility.convertToList(m_sinhVien));
        Utility.xuatThongBao("Danh sach sinh vien da duoc sap xep thu tu tang dan theo MSSV.");
    }

    public void sapXepSinhVienTheoDiemTongKet(boolean sapXepGiamDan) {
        List<Map.Entry<String, SinhVien>> danhSachSinhVien = new ArrayList<Map.Entry<String, SinhVien>>(m_sinhVien.entrySet());
        Collections.sort(danhSachSinhVien, new Comparator<Map.Entry<String, SinhVien>>() {
            @Override
            public int compare(Map.Entry<String, SinhVien> sinhVien1, Map.Entry<String, SinhVien> sinhVien2) {
                int tangDan = 1;
                int giamDan = -1;
                if (sapXepGiamDan) {
                    tangDan = -1;
                    giamDan = 1;
                }
                double diemTongKetSV1 = sinhVien1.getValue().getDiemThi().xuatDiemTongKet();
                double diemTongKetSV2 = sinhVien2.getValue().getDiemThi().xuatDiemTongKet();
                if (diemTongKetSV2 > diemTongKetSV1)
                    return giamDan;
                else if (diemTongKetSV2 < diemTongKetSV1)
                    return tangDan;
                else return 0;
            }
        });
        m_sinhVien.clear();
        for (Map.Entry<String, SinhVien> sinhVien : danhSachSinhVien) {
            m_sinhVien.put(sinhVien.getKey(), sinhVien.getValue());
        }
        printAsTable(Utility.convertToList(m_sinhVien));
    }

    public void printAsTable(List<SinhVien> danhSachSinhVien) {
        Utility.xuatThongBao("" +
                    "+------------------------+------------------------------------------+--------------------------------------------+---------------+\n" +
                    "| MSSV                   | Ho ten                                   |                  Diem thi                  | Diem tong ket |\n" +
                    "|                        |                                          +--------------+---------------+-------------+               |\n" +
                    "|                        |                                          | Diem seminar | Diem do an GK | Cuoi ky     |               |\n" +
                    "+------------------------+------------------------------------------+--------------+---------------+-------------+---------------+");
        for (SinhVien sv : danhSachSinhVien) {
            Utility.xuatThongBao("" +
                    "|" + String.format("%-24s", sv.getMssv()) + "|" + String.format("%-42s", sv.getHoTen()) + "|" + String.format("%-14s", sv.getDiemThi().xuatDiemSeminar()) + "|" + String.format("%-15s", sv.getDiemThi().xuatDiemDoAnGiuaKy()) + "|" + String.format("%-13s", sv.getDiemThi().xuatDiemCuoiKy()) + "|" + String.format("%-15s", sv.getDiemThi().xuatDiemTongKet()) + "|\n" +
                    "+------------------------+------------------------------------------+--------------+---------------+-------------+---------------+");
        }
    }

    public void quanLyKhoaHoc() {
        while (true) {
            Utility.xuatThongBao(String.format("Khoa hoc: %s - So luong sinh vien: %s \n", this.m_tenKhoaHoc, this.m_sinhVien.size()) +
                    "+-------+----------------------------------+\n" +
                    "| Input |             Lua chon             |\n" +
                    "+-------+----------------------------------+\n" +
                    "|   0   | Thoat khoi trinh quan ly         |\n" +
                    "+-------+----------------------------------+\n" +
                    "|   1   | Xuat danh sach sinh vien tu file |\n" +
                    "+-------+----------------------------------+\n" +
                    "|   2   | Them sinh vien vao khoa hoc      |\n" +
                    "+-------+----------------------------------+\n" +
                    "|   3   | Nhap diem cho sinh vien          |\n" +
                    "+-------+----------------------------------+\n" +
                    "|   4   | Xoa sinh vien khoi khoa hoc      |\n" +
                    "+-------+----------------------------------+\n" +
                    "|   5   | Tim kiem sinh vien               |\n" +
                    "+-------+----------------------------------+\n" +
                    "|   6   | Xuat danh sach sinh vien         |\n" +
                    "+-------+----------------------------------+\n" +
                    "|   7   | Sap xep danh sach theo MSSV      |\n" +
                    "+-------+----------------------------------+\n" +
                    "|   8   | Sap xep diem tong ket tang dan   |\n" +
                    "+-------+----------------------------------+\n" +
                    "|   9   | Sap xep diem tong ket giam dan   |\n" +
                    "+-------+----------------------------------+");
            int input = Utility.inputInt("Nhap lua chon: ");
            switch (input) {
                case 0:
                    return;
                case 1:
                    this.docDanhSachSinhVien("src/main/resources/danhSachSinhVien.yaml");
                    break;
                case 2:
                    this.themSinhVien();
                    break;
                case 3:
                    this.capNhatSinhVien();
                    break;
                case 4:
                    this.xoaSinhVien(null);
                    break;
                case 5:
                    this.timKiemSinhVienTheoMssv(null);
                    break;
                case 6:
                    this.xuatDanhSachTatCaSinhVien();
                    break;
                case 7:
                    this.sapXepSinhVienTheoMSSV();
                    break;
                case 8:
                    this.sapXepSinhVienTheoDiemTongKet(false);
                    break;
                case 9:
                    this.sapXepSinhVienTheoDiemTongKet(true);
                    break;
            }
        }
    }

}
