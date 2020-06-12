import java.util.Scanner;

import com.test.admin.AdminUser;
import com.test.student.StudentUser;
import com.test.teacher.TeacherUser;

public class main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("\t교육센터 관리 시스템");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			System.out.println("1. 관리자 로그인");
			System.out.println("2. 교사 로그인");
			System.out.println("3. 교육생 로그인");
			System.out.println("4. 종료");

			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("▷ 입력: \n");

			String cho = sc.nextLine();
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			if (cho.equals("1")) {
				// 관리자 로그인
				AdminUser adminUser = new AdminUser();
				adminUser.login(adminUser);

			} else if (cho.equals("2")) {
				TeacherUser teacherUser = new TeacherUser();
				teacherUser.login(teacherUser);
			} else if (cho.equals("3")) {
				StudentUser studentUser = new StudentUser();
				// 로그인
				studentUser.login(studentUser);
			} else if (cho.equals("4")) {
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("프로그램을 종료합니다."); // needMore
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				break;
			} else {
				System.out.println("번호를 다시 입력해주세요.");
			}
		}

	}
}
