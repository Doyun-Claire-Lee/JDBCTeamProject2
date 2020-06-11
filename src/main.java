import java.util.Scanner;

import com.test.admin.AdminUser;
import com.test.student.StudentUser;
import com.test.teacher.TeacherUser;

public class main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while (true) {

			System.out.println("============================================");
			System.out.println("\t교육센터 관리 시스템");
			System.out.println("============================================");

			System.out.println("1. 관리자 로그인");
			System.out.println("2. 교사 로그인");
			System.out.println("3. 교육생 로그인");
			System.out.println("4. 종료");

			System.out.println("============================================");
			System.out.print("▷ 입력:");
			int cho = sc.nextInt();
			System.out.println("============================================");

			if (cho == 1) {
				// 관리자 로그인
				AdminUser adminmain = new AdminUser();
				adminmain.login(adminmain);

			} else if (cho == 2) {
				TeacherUser tUser = new TeacherUser();
	            tUser.login(tUser);
			} else if (cho == 3) {
				
				StudentUser sUser = new StudentUser();
				// 로그인
				sUser.login(sUser);

			} else {
				break;
			}
		}

	}
}
