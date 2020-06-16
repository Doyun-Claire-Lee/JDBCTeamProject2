import java.util.Random;

public class dummyInsert {
	public static void main(String[] args) {
		
//	makeQuestiondummy();
	makeScoreDummy();
	}//main
	



	private static void makeScoreDummy() {
		Random rnd = new Random();
		for(int i=333; i<=358;i++) {
			for(int j=143; j<=152; j++) {
				System.out.printf("insert into tblscorebysubject values (SCOREBYSUBJECTSEQ.nextval, %d, %d, %d, %d, 100);\n",i,j,rnd.nextInt(40)+60,rnd.nextInt(40)+60);
			}
		}
		
	}


	private static void makeQuestiondummy() {
	      Random rnd = new Random();
	      
	      String[] qList_p = {"해당 언어의 개념에 대해 간략히 하시오",
	    		  			  "어느 분야에서 가장 큰 장점을 발휘하는가?",
	    		  			  "GUI 프로그래밍에 장점을 드러내는지에 대해 설명하시오.",
	    		  			  "데이터 분석에 이용한다면 어떤 장점을 갖는가?",
	    		  			  "프로그래밍 접근 방식이 제한적인가?",
	    		  			  "모바일 컴퓨팅과 브라우저 부재에 대하여 설명하시오",
	    		  			  "언어 설계 면에 있어서의 문제점을 설명하시오.",
	    		  			  "트랜잭션의 병행제어 목적을 설명하시오.",
	    		  			  "이행적 함수 종속 관계를 기호로 설명하시오",
	    		  			  "DBA(DataBase Administration)을 설명하시오"};
	      
	      String[] aList_p = {"웹 개발에서 가장 큰 장점을 발휘하는 언어이다.",
	    		  			  "객체 지향 언어이며 타언어와는 다르게 컴파일러에 구애받지 않는다는 장점이 있다.",
	    		  			  "함께 설치되는 Tkinter를 사용하여 GUI를 제작할 수 있다.",
	    		  			  "R이라는 언어가 있지만 판다스라는 모듈을 통해 쉽고 빠르게 진행할 수 있다.",
	    		  			  "자바와 달리 멀티패러다임이고 객체지향을 지원하며, 절차적이고 함수형 프로그래밍 스타일이다.",
	    		  			  "아직도 정말 안전한 파이썬용 샌드박스/감옥(jail)이 없어서 C파이썬(표준 이행)도 기본적으로 불가능한 것으로 간주된다",
	    		  			  "언어가 동적 입력 형태를 띠기 때문에, 더 많은 테스팅을 필요로 하고 실행시간에서만 드러나는 오류가 있다",
	    		  			  "데이터베이스의 일관성 최소화",
	    		  			  "A->B이고 B->C일 때, A->C를 만족하는 관계",
	    		  			  "데이터베이스의 전체적인 관리 운영에 대한 최고의 책임"};
	      
	      String[] qList_s = {"2016년 11월 영화 예매 순위 기준 top3는 다음과 같다. 파이썬 리스트에 딕셔너리를 넣는 형태로 다음 표를 표현하라.",
	    		  			  " lang1,2 리스트가 있을 때 lang1과 lang2의 원소를 모두 갖고 있는 langs 리스트를 만들어라.",
	    		  			  "다음과 같이 날짜로 구성된 리스트가 있을 때 이를 역순으로 정렬하라.",
	    		  			  "다음은 대신증권의 최근 5일 종가 데이터이다. 최근 5일치 종가의 평균 값을 출력하라.",
	    		  			  "인사테이블에서 연봉이 가장 높은 사람의 성함과 연봉을 출력하라(tblInsa)",
	    		  			  "다음 JAVA 언어로 구현된 배열과 코드를 참고하여 예상 출력 결과를 작성하라",
	    		  			  "크기가 8인 배열 T(8)에 8비트 2의 보수 형태로 저장된 2진수를 10진수로 변환하여 출력시 빈칸에 알맞은 내용은?",
	    		  			  "업무 프로세스 관리(BPM)의 생애주기 항목을 순서대로 올바르게 쓰시오",
	    		  			  "GIS (지리정보시스템) 약어로 된 전상영어의 완전 이름을 쓰시오"};
	      
	      
	      String[] aList_s = {" list1=[{\"닥터 스트레인지\":{36.6,7.7}},{\"스플릿\":{18.8,8.1}},{\"럭키\":{12.7,7.6}}]",
	    		  			  ">>> lang1 = [\"C\", \"C++\", \"JAVA\"]","nums = [2, 3, 1, 7, 10, 4, 5, 8]",
	    		  			  "tlist=['2016-09-05', '2016-09-06', '2016-09-07', '2016-09-08', '2016-09-09']",
	    		  			  "김원호,42000000","프로세스설계>시뮬레이션>구현>실행>모니터링>최적화",
	    		  			  "[1,4,7,10,13]", "GIS : Geographic information system",
	    		  			  "N+S, A(C,S), N+1, N+R2", "DEC+(K*S(P)), DEC=128-DEC"};
	      
	      
	      
	      String question = "";
	      String answer = "";
	      for(int i=143; i<=152; i++) {
	         
	         for(int j=0; j<8; j++) {
	            if(j<4) {
	               System.out.printf("insert into tblquestionbyexam values (QUESTIONBYEXAMSEQ.nextval, %d, '필기', '%s', '%s');\n",i,qList_p[rnd.nextInt(qList_p.length)],aList_p[rnd.nextInt(aList_p.length)]);
	            } else {
	               System.out.printf("insert into tblquestionbyexam values (QUESTIONBYEXAMSEQ.nextval, %d, '실기', '%s', '%s');\n",i,qList_s[rnd.nextInt(qList_s.length)],aList_s[rnd.nextInt(aList_s.length)]);
	               
	            }
	            
	         }
	      }


	      
	   }
	
	
	
	
	
	
}
