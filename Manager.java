package ticket;

import java.util.Scanner;

public class Manager extends BasicSearch{
	
	public void searchInManager() {
		String keep;
		
		do{
		System.out.println("請輸入功能代碼：");
		System.out.println("例：\t 輸入1：查詢現正撥放電影場次");
		System.out.println("\t 輸入2：查詢場次座位");
		Scanner scanner_i=new Scanner(System.in);
		int i =scanner_i.nextInt();
		
		switch (i) {
			case 1:
				System.out.println("請輸入欲查詢的電影編號（輸入0時列出全部撥放清單）：");
				Scanner scanner_p=new Scanner(System.in);
				int p =scanner_p.nextInt();
				
				if (p==0) {
					showPlaylist();
				}else {
					showPlaylist(p);
				}
				
				System.out.println("是否查詢此電影所有場次的剩餘座位（y/n）：");
				Scanner scanner_ps=new Scanner(System.in);
				
				if (scanner_ps.nextLine().equals("y")) {
					showSeats(p);
				}
				
				break;
				
			case 2:
				System.out.println("請輸入欲查詢的電影編號：");
				Scanner scanner_s=new Scanner(System.in);
				int s =scanner_s.nextInt();
				
				showSeats(s);
				break;
				
			default:
				System.out.println("輸入錯誤");
				break;
			}
			
			System.out.println("是否繼續使用查詢（y/n）：");
			Scanner scanner_l=new Scanner(System.in);
			keep=scanner_l.nextLine();
			
		}while(keep.equals("y"));
	}

}
