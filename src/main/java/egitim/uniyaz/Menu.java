package egitim.uniyaz;

import java.util.Scanner;

public class Menu {
    public void menuEkrani(){
        System.out.println("");
        System.out.println("1.Kahramanları Listele");
        System.out.println("2.Filmleri Listele");
        System.out.println("3.Kahramanların tüm filmlerindeki toplam bütcelerini görüntüle");
        System.out.println("4.Kahramanların oynadığı toplam film sayılarını görüntüle");
        System.out.println("5.Çıkış");
        System.out.println("");

    }

    public  int secimYap(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Seçim yapınız");
        int secim=scanner.nextInt();
        return secim;
    }
}
