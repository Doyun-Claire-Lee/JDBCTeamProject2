package com.test.admin;

import java.util.Scanner;

public class AdminEquipment {

	// 기자재 관리 메뉴	
	public void menu() {
		
		Scanner scan = new Scanner(System.in);
		
		while (true) {	
			
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t\t기자재 관리");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("1.기자재 조회");
			System.out.println("2.기자재 수정");
			System.out.println("3.기자재 추가");					
			System.out.println("0. 뒤로가기");
					
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("▷ 입력:");
									
			// 사용자에게 번호 입력받음
			String cho = scan.nextLine();

				if (cho.equals("1")) {
					
					// 기자재 관리 메뉴 -> 1.기자재 조회
					while (true) {	
						
						System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
						System.out.println("\t\t기자재 조회");
						System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
						System.out.println("1.기자재 조회(구입날짜별)");
						System.out.println("2.기자재 조회(강의실별)");										
						System.out.println("0. 뒤로가기");
								
						System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
						System.out.print("▷ 입력:");
												
						// 사용자에게 번호 입력받음
						cho = scan.nextLine();

							if (cho.equals("1")) {
								
								// 1.기자재 조회(구입날짜별)
							
							} else if (cho.equals("2")) {
								
								// 2.기자재 조회(강의실별)
													
							
							} else if (cho.equals("0")) {
								// 뒤로 가기
								System.out.println("뒤로가기를 선택하셨습니다.");
								System.out.println("엔터를 입력하시면 이전 페이지로 돌아갑니다.");
								scan.nextLine();
								break;
							} else {
								// 예외
								System.out.println("번호를 다시 입력해주세요");
							}
							
						}//while(기자재 조회)			
				
				} else if (cho.equals("2")) {
					
					// 2.기자재 수정
										
				} else if (cho.equals("3")) {
					
					// 3.기자재 추가
				
				} else if (cho.equals("0")) {
					// 뒤로 가기
					System.out.println("뒤로가기를 선택하셨습니다.");
					System.out.println("엔터를 입력하시면 이전 페이지로 돌아갑니다.");
					scan.nextLine();
					break;
				} else {
					// 예외
					System.out.println("번호를 다시 입력해주세요");
				}
				
			}//while(기자재 관리)
		
		
	}// mainMenu()
	
	
}//class
