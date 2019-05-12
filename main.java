package ticket;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int count = 0;
		while (count < 3) {
			try {
				count++;
				System.out.println("請選擇身分（0=管理員，1=使用者）：");
				Scanner scanner_i = new Scanner(System.in);
				int identify = scanner_i.nextInt();
				switch (identify) {
				case 0:
					count=0;
					Manager manager = new Manager();
					manager.searchInManager();
					break;

				case 1:
					count=0;
					User user = new User();
					user.jump();
					break;

				default:
					System.out.println("無法判別身分，請重新輸入");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("輸入值型態錯誤，請重新輸入");
			}
		}
		System.out.println("錯誤次數過多，請稍後再試");
		count = 0;

	}

}
