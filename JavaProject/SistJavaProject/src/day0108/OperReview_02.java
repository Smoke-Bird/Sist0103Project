package day0108;

import java.util.Calendar;
import java.util.Scanner;

public class OperReview_02 {

	public static void main(String[] args) {
		// 띠: 로직에서 사용하는 순서는 원숭이
		// 원숭이 닭 쥐 소 호랑이 토끼 용 뱀 말 양 개 돼지

		Scanner sc = new Scanner(System.in);

		int myBirthYear, age;
		String name, ddi;

		System.out.println("이름입력");
		name = sc.nextLine();

		System.out.println("태어난 연도 입력");
		myBirthYear = sc.nextInt();

		Calendar cal = Calendar.getInstance();
		age = cal.get(cal.YEAR) - myBirthYear;

		ddi = myBirthYear % 12 == 0 ? "원숭이"
				: myBirthYear
						% 12 == 1
								? "닭"
								: myBirthYear % 12 == 2 ? "쥐"
										: myBirthYear % 12 == 3 ? "소"
												: myBirthYear % 12 == 4 ? "호랑이"
														: myBirthYear % 12 == 5 ? "토끼"
																: myBirthYear % 12 == 6 ? "용"
																		: myBirthYear % 12 == 7 ? "뱀"
																				: myBirthYear % 12 == 8 ? "말"
																						: myBirthYear % 12 == 9 ? "양"
																								: myBirthYear % 12 == 10
																										? "개"
																										: "돼지";
		System.out.println("이름:"+name);
		System.out.println("현재나이:"+age+"세");
		System.out.println("띠:"+ddi+"띠");

	}

}
