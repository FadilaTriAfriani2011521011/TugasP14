package Koneksi_Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringDate 
{
    public StringDate(){}

    public void Date()
    {
          Calendar calendar = Calendar.getInstance();
          System.out.println();
          System.out.println("Date and Time       : " + calendar.getTime());

          Date date = new Date();
          SimpleDateFormat d = new SimpleDateFormat("E yyyy.MM.dd");
          System.out.print("Tanggal Transaksi   : " +d.format(date)); 
          
          System.out.print("\nWaktu               : " + calendar.get(Calendar.HOUR_OF_DAY));
          System.out.print("." + calendar.get(Calendar.MINUTE));
          System.out.print("." + calendar.get(Calendar.SECOND));
          System.out.println();

    }

    public void StringtoUpperCase()
    {
        System.out.println();
        String cetak = "           Selamat Datang di Toko Fadila ";
        System.out.print(cetak.toUpperCase());
        System.out.println();
    }

    public void StringTrim()
    {
            System.out.println();
            String AlamatTk = "        Jln.Moh.Hatta    ";
            String NoHp = "     082327980976     ";
            System.out.println(AlamatTk.trim());
            System.out.println(NoHp.trim());
            System.out.println();
           
        
    }
}

