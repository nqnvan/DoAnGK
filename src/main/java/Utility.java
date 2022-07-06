import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class Utility {
    public static String inputString(String outputMessage) {
        Scanner in = new Scanner(System.in);
        System.out.printf(outputMessage);
        return in.nextLine();
    }

    public static int inputInt(String outputMessage) {
        Scanner in = new Scanner(System.in);
        System.out.printf(outputMessage);
        return in.nextInt();
    }

    public static double inputDouble(String outputMessage) {
        Scanner in = new Scanner(System.in);
        System.out.printf(outputMessage);
        return in.nextDouble();
    }

    public static void xuatThongBao(String outputMessage) {
        System.out.println(outputMessage);
    }

    public static List<SinhVien> getDanhSachSinhVienFromFile(String filePath) {
        List<SinhVien> danhSachSinhVien = new ArrayList<>();
        try {
            Yaml yaml = new Yaml(new Constructor(SinhVien.class));
            InputStream inputStream = new FileInputStream(filePath);
            for (Object obj : yaml.loadAll(inputStream)) {
                if (obj instanceof SinhVien) {
                    danhSachSinhVien.add((SinhVien) obj);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return danhSachSinhVien;
    }

    public static List<SinhVien> convertToList(LinkedHashMap<String, SinhVien> map) {
        List<SinhVien> list = new ArrayList<>();
        for (Object key : map.keySet()) {
            list.add(map.get(key));
        }
        return list;
    }
}
