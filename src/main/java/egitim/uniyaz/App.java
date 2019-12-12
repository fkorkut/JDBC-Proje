package egitim.uniyaz;

public class App 
{
    public static void main( String[] args )
    {
        Menu menu=new Menu();
        SqlIslemleri sqlIslemleri=new SqlIslemleri();
        System.out.println( "Hero - Movie Sorgu Uygulaması " );
        boolean isConnected= sqlIslemleri.baglantiyiKontrolEt();
        boolean isDevam=true;
        while (isDevam){
            menu.menuEkrani();
            if(isConnected){
                int secim=menu.secimYap();
                switch (secim){
                    case 1:
                        sqlIslemleri.getHeroList();
                        break;
                     case 2:
                         sqlIslemleri.getMovieList();
                         break;
                    case 3:
                        sqlIslemleri.getHeroTotalBudget();
                        break;
                    case 4:
                        sqlIslemleri.getHeroMovieCount();
                        break;
                    case 5:
                        isDevam=false;
                        break;

                }
            }
       }

    }
}
