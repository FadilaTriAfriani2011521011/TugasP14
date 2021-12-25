package Koneksi_Database;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Program {
	
	//static Scanner scanner;
	static Connection conn;
		
	public static void main(String[] args) throws Exception 
	{
		Barang B = new Barang(null,0);
        StringDate std = new StringDate();

		Scanner inputan = new Scanner (System.in);
		String pilihan;
		boolean cont = true;
						
		String url = "jdbc:mysql://localhost:3306/toko_db";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","");	
			
			std.Date();
			std.StringtoUpperCase();
            B.NamaBarang();
            std.StringTrim();

			while (cont) {
				System.out.println("1. Lihat Stok Barang di Toko Fadila");
				System.out.println("2. Restok Barang di Toko");
				System.out.println("3. Edit Data Barang");
				System.out.println("4. Hapus Data Barang");
				System.out.println("5. Cari Data Barang yang Ada di Toko");
				
				System.out.print("\nSilahkan Masukkan Pilihan anda : ");
				pilihan = inputan.next();
				
				switch (pilihan) {
				case "1":
					lihatdata();
					break;
				case "2":
					tambahdata();
					break;
				case "3":
					ubahdata();
					break;
				case "4":
					hapusdata();
					break;
				case "5":
					caridata();
					break;
				default:
					System.err.println("\n     Maaf, Nomor yang Diinputkan Tidak Tersedia");
				}
				
				System.out.print("\nIngin Memilih Lagi? [y/n]? ");
				pilihan = inputan.next();
				cont = pilihan.equalsIgnoreCase("y");
				System.out.println();
			}
			
			System.out.println("   Terimakasih Sudah Menggunakan Program Toko Kami\n");
			
		}
		catch(ClassNotFoundException ex) {
			System.err.println("\nMohon Maaf, Ada Error");
			System.exit(0);
		}
		catch(SQLException e){
			System.err.println("\nKoneksi tidak berhasil;");
		}
	}
	
	private static void lihatdata() throws SQLException {
		String text1 = "\n    STOK BARANG YANG TERSEDIA";
		System.out.println(text1.toUpperCase());
						
		String sql ="SELECT * FROM toko";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		
		while(result.next()){
			System.out.print("\nNama Barang\t: ");
            System.out.print(result.getString("NamaBarang"));

            System.out.print("\nNomor Faktur\t: ");
            System.out.print(result.getString("NoFaktur"));

            System.out.print("\nHarga Barang\t: ");
            System.out.print(result.getInt("Harga"));

            System.out.print("\nJumlah Barang\t: ");
            System.out.print(result.getInt("Jumlah"));
            System.out.print("\n");
		}
	}
		
	private static <Int> void tambahdata() throws SQLException{
		String text2 = "\n    RESTOK BARANG DI TOKO ";
		System.out.println(text2.toUpperCase());
		
		Scanner inputan = new Scanner (System.in);
				
		try 
		{
			System.out.print("Nama Barang\t: ");
			String NamaBarang = inputan.nextLine();

			System.out.print("Nomor Faktur\t: ");
			String NoFaktur = inputan.nextLine();

			System.out.print("Harga Barang\t: ");
			int Harga = inputan.nextInt();

  			System.out.print("Jumlah Barang\t: ");
			int Jumlah = inputan.nextInt();
		
		String sql = "INSERT INTO toko (NamaBarang, NoFaktur, Harga, Jumlah ) VALUES ('"+NamaBarang+"','"+NoFaktur+"','"+Harga+"','"+Jumlah+"')";
					
        Statement statement = conn.createStatement();
        statement.execute(sql);
        System.out.println("\n  Yap, Data Anda Berhasil Ditambahkan");
	
	    } catch (SQLException e) {
	        System.err.println("\n  Mohon Maaf, Ada Kesalahan Saat Input Data");
	    } catch (InputMismatchException e) {
	    	System.err.println("\n  Hanya Inputkan Angka!");
	   	}
	}
	
	private static void ubahdata() throws SQLException{
		String text3 = "\n    EDIT DATA BARANG";
		System.out.println(text3.toUpperCase());
		
		Scanner inputan = new Scanner (System.in);
		
		try {
            lihatdata();
            System.out.print("\nMasukkan Nomor Faktur Barang yang akan di ubah : ");
			String NoFaktur = inputan.next();
            String sql = "SELECT * FROM toko WHERE NoFaktur = '" +NoFaktur+"'";
            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            if(result.next()){
                
                System.out.print("NamaBarang["+result.getString("NamaBarang")+"] : ");
                String NamaBarang = inputan.next();
                
                System.out.print("Harga["+result.getInt("Harga")+"]\t: ");
                int Harga = inputan.nextInt();

				System.out.print("Jumlah["+result.getInt("Jumlah")+"]\t: ");
                int Jumlah = inputan.nextInt();
                   
                sql = "UPDATE toko SET NamaBarang='"+NamaBarang+"',Harga= '"+Harga+"', Jumlah= '"+Jumlah+"' WHERE NoFaktur='"+NoFaktur+"'";

                if(statement.executeUpdate(sql) > 0){
                    System.out.println("\n   Data Barang Sudah Berhasil Diubah (NoFaktur "+NoFaktur+")");
                }
            }
            statement.close();        
        } catch (SQLException e) {
            System.err.println("\n  Terjadi kesalahan dalam mengubah data");
            System.err.println(e.getMessage());
        }
		}
	
	private static void hapusdata() {
		String text4 = "\n   HAPUS DATA BARANG";
		System.out.println(text4.toUpperCase());
		
		Scanner inputan = new Scanner (System.in);
		
		try{
	        lihatdata();
	        System.out.print("\nMasukkan Nomor Faktur Barang yang akan dihapus : ");
	        String NoFaktur = inputan.next();
	        
	        String sql = "DELETE FROM toko WHERE NoFaktur = '"+NoFaktur+"'";
	        Statement statement = conn.createStatement();
	        //ResultSet result = statement.executeQuery(sql);
	        
	        if(statement.executeUpdate(sql) > 0){
	            System.out.println("\n  Selamat, Anda Berhasil menghapus data (NoFaktur "+NoFaktur+")");
	        }
	   }catch(SQLException e){
	        System.out.println("\n  Oh Tidak, Ada Kesalahan :(");
	        }
		}
	
	private static void caridata () throws SQLException {
		String text5 = "\n    CARI DATA BARANG YANG TERSEDIA DI TOKO";
		System.out.println(text5.toUpperCase());
		
		Scanner inputan = new Scanner (System.in);
				
		System.out.print("Masukkan Nama Barang yang Ingin Anda Cari : ");
        
		String keyword = inputan.nextLine();
        Statement statement = conn.createStatement();
        String sql = "SELECT * FROM toko WHERE NamaBarang LIKE '%"+keyword+"%'";
        ResultSet result = statement.executeQuery(sql); 
                
        while(result.next()){
        	System.out.print("\nNama Barang\t: ");
            System.out.print(result.getString("NamaBarang"));
            System.out.print("\nNomor Faktur\t: ");
            System.out.print(result.getString("NoFaktur"));
            System.out.print("\nHarga Barang\t: ");
            System.out.print(result.getInt("Harga"));
			System.out.print("\nJumlah Barang\t: ");
            System.out.print(result.getInt("Jumlah"));
            System.out.print("\n");
        }
	}

	
}
