import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Menu {
    private String nama;
    private double harga;

    public Menu(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public String tampilkanMenu() {
        return nama + ": Rp " + harga;
    }
}

class Pemesanan {
    private List<Menu> pesanan;
    private List<Integer> jumlah;

    public Pemesanan() {
        pesanan = new ArrayList<>();
        jumlah = new ArrayList<>();
    }

    public void tambahPesanan(Menu menu, int jumlahPesanan) {
        pesanan.add(menu);
        jumlah.add(jumlahPesanan);
    }

    public double hitungTotal() {
        double total = 0;
        for (int i = 0; i < pesanan.size(); i++) {
            total += pesanan.get(i).getHarga() * jumlah.get(i);
        }
        return total;
    }

    public String tampilkanNota() {
        StringBuilder nota = new StringBuilder("Nota Pemesanan:\n");
        for (int i = 0; i < pesanan.size(); i++) {
            nota.append(pesanan.get(i).getNama())
                    .append(" x ")
                    .append(jumlah.get(i))
                    .append(" = Rp ")
                    .append(pesanan.get(i).getHarga() * jumlah.get(i))
                    .append("\n");
        }
        nota.append("Total: Rp ").append(hitungTotal());
        return nota.toString();
    }
}

class Restoran {
    private List<Menu> daftarMenu;

    public Restoran() {
        daftarMenu = new ArrayList<>();
    }

    public void tambahMenu(Menu menu) {
        daftarMenu.add(menu);
    }

    public String tampilkanMenu() {
        StringBuilder menuString = new StringBuilder();
        for (Menu menu : daftarMenu) {
            menuString.append(menu.tampilkanMenu()).append("\n");
        }
        return menuString.toString();
    }

    public void buatPemesanan() {
        Pemesanan pemesanan = new Pemesanan();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Daftar Menu:");
            System.out.println(tampilkanMenu());
            System.out.print("Pilih menu (atau ketik 'selesai' untuk menyelesaikan): ");
            String pilihan = scanner.nextLine();
            if (pilihan.equalsIgnoreCase("selesai")) {
                break;
            }
            System.out.print("Jumlah: ");
            int jumlah = Integer.parseInt(scanner.nextLine());
            Menu menuTerpilih = null;
            for (Menu menu : daftarMenu) {
                if (menu.getNama().equalsIgnoreCase(pilihan)) {
                    menuTerpilih = menu;
                    break;
                }
            }
            if (menuTerpilih != null) {
                pemesanan.tambahPesanan(menuTerpilih, jumlah);
            } else {
                System.out.println("Menu tidak ditemukan.");
            }
        }
        System.out.println(pemesanan.tampilkanNota());
    }
}

// Contoh Penggunaan
public class Main {
    public static void main(String[] args) {
        Restoran restoran = new Restoran();
        restoran.tambahMenu(new Menu("Nasi Goreng", 20000));
        restoran.tambahMenu(new Menu("Ayam Penyet", 25000));
        restoran.tambahMenu(new Menu("Mie Goreng", 18000));

        restoran.buatPemesanan();
    }
}