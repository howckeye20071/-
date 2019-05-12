package ticket;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User extends BasicSearch implements Menu {

	@Override
	public void jump() {
		boolean check_menu = true;

		while (check_menu) {
			System.out.println("請輸入欲前往的頁面代碼：");
			System.out.println("a：" + a);
			System.out.println("b：" + b);
			System.out.println("c：" + c);
			System.out.println("d：" + d);

			try {
				Scanner jumpid = new Scanner(System.in);
				String jump = jumpid.nextLine();
				switch (jump) {
				case "a":
					check_menu = false;
					searchInUser();
					break;
				case "b":
					check_menu = false;
					orderInUser();
					break;
				case "c":
					check_menu = false;
					new Main();
					break;
				case "d":
					check_menu = false;
					System.out.println("感謝您的使用！");
					break;
				default:
					System.out.println("無法識別代碼，請重新輸入");
					break;
				}
			} catch (Exception e) {
				System.out.println("無法識別代碼，請重新輸入");
			}
		}

	}

	public void searchInUser() {
		boolean keep_search = true;
		try {
			while (keep_search) {
				System.out.println("請輸入查詢代碼：");
				System.out.println("例：\t 輸入1：查詢現正撥放電影場次");
				System.out.println("\t 輸入2：查詢剩餘座位");
				try {
					Scanner scanner_i = new Scanner(System.in);
					int i = scanner_i.nextInt();

					switch (i) {
					case 1:
						System.out.println("請輸入欲查詢的電影編號（輸入0時列出全部撥放清單）：");
						Scanner scanner_p = new Scanner(System.in);
						int p = scanner_p.nextInt();

						if (p == 0) {
							showPlaylist();
						} else {
							checkPlaylist(p);
							showPlaylist(p);
							if (super.checkPlaylist_movieid) {
								System.out.println("是否查詢此電影所有場次的剩餘座位（y/n）：");
								Scanner scanner_ps = new Scanner(System.in);

								if (scanner_ps.nextLine().equals("y")) {
									showSeats(p);
								} else {
									System.out.println("結束查詢");
								}
							} else {
								System.out.println("結束查詢");
							}
						}
						jump();
						break;

					case 2:
						try {
							System.out.println("請輸入欲查詢的電影編號：");
							Scanner scanner_s = new Scanner(System.in);
							int s = scanner_s.nextInt();
							checkPlaylist(s);
							if (super.checkPlaylist_movieid) {
								showSeats(s);
							} else {
								System.out.println("結束查詢");
							}
						} catch (Exception e) {
							System.out.println("結束查詢");
						}
						break;

					default:
						System.out.println("輸入錯誤");
						break;
					}

					boolean check1 = true;
					while (check1 = true) {
						System.out.println("是否繼續使用查詢系統（y/n）：");
						Scanner scanner_l = new Scanner(System.in);
						String keep_l = scanner_l.nextLine();
						if (keep_l.equals("y")) {
							searchInUser();
						} else if (keep_l.equals("n")) {
							jump();
						} else {
							System.out.println("輸入錯誤，請重新輸入");
						}
					}

				} catch (InputMismatchException e) {
					System.out.println("輸入值型態錯誤");
				}
			}
			System.out.println("已退出查詢系統");
		} catch (InputMismatchException e) {
			System.out.println("輸入錯誤，請重新輸入");
		}

	}

	public void orderInUser() {
		boolean keep_order = true;
		boolean check = true;

		while (check = true) {
			try {
				System.out.println("請輸入欲訂購票券的電影編號：");
				Scanner scanner_o = new Scanner(System.in);
				int o = scanner_o.nextInt();

				checkPlaylist(o);

				if (super.checkPlaylist_movieid) {

					showSeats(o);

					while (keep_order) {
						try {
							System.out.println("請選擇欲訂購的票券座位（列-行）：");
							Scanner scanner_checkseat = new Scanner(System.in);
							String seat_num = scanner_checkseat.nextLine();

							checkSeats(o, seat_num);

							if (checkSeats_num) {

								searchSeats(o, seat_num);

								boolean check_order_deny = true;
								while (check_order_deny) {
									System.out.println("是否訂購此票券 (y/n)");
									Scanner scanner_ord = new Scanner(System.in);
									String order = scanner_ord.nextLine();

									if (order.equals("y")) {
										orderSeats(o, seat_num, order);
										check_order_deny = false;
										keep_order = false;
										System.out.println("訂購完成");
									} else if (order.equals("n")) {
										check_order_deny = true;
										keep_order = false;
										break;
									} else {
										System.out.println("輸入錯誤，請重新輸入");
									}
								}
							} else {
								System.out.println("您輸入的座位號碼不存在，請重新輸入");
							}
						} catch (Exception e) {
							System.out.println("輸入錯誤，請重新輸入");
						}
					}
				} else {
					System.out.println("您查詢的電影代碼不存在，訂單已取消");
				}
			} catch (Exception e) {
				System.out.println("您查詢的電影代碼不存在，訂單已取消");
				;
			}

			while (check = true) {
				try {
					System.out.println("是否繼續使用訂票系統（y/n）：");
					Scanner scanner_keep_o = new Scanner(System.in);
					String keep_o = scanner_keep_o.nextLine();

					if (keep_o.equals("y")) {
						orderInUser();
					} else if (keep_o.equals("n")) {
						jump();
					} else {
						System.out.println("輸入錯誤，請重新輸入");
					}
				} catch (Exception e) {
					System.out.println("輸入錯誤，請重新輸入");
				}
			}
		}
	}
}
