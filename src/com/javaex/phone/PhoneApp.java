package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {
		
		System.out.println("*********************************");
		System.out.println("*      전화번호 관리 프로그램   *");
		System.out.println("*********************************");
		System.out.println("");
		
		PhoneDao phoneDao = new PhoneDao();
		List<PhoneVo> personList;
		
		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("1. 리스트 2.등록 3.수정 4.삭제 5.검색 6.종료");
			System.out.println("-------------------------------------");

			System.out.print(">메뉴번호: ");
			int num = sc.nextInt();

			if (num == 6) {

				System.out.println("*********************************");
				System.out.println("*           감사합니다          *");
				System.out.println("*********************************");

				break;
			
			}

			switch (num) {

			case 1:

				System.out.println("<1.리스트>");
				
				personList = phoneDao.getPersonList();
				
				printList(personList);

				break;

			case 2:

				System.out.println("<2.등록>");

				System.out.print(">이름: ");
				String pName = sc.next();

				System.out.print(">휴대전화: ");
				String pHp = sc.next();

				System.out.print(">회사번호: ");
				String pCompany = sc.next();

				PhoneVo iPhoneVo = new PhoneVo(pName, pHp, pCompany);
				
				int iCount = phoneDao.personInsert(iPhoneVo);
				
				if(iCount > 0) {
					System.out.println("[등록되었습니다.]");			
				} else {
					System.out.println("[관리자에게 문의하세요(" + iCount + ")]");
				}
				
				personList = phoneDao.getPersonList();
				
				printList(personList);

				break;
					
			case 3:
				
				System.out.println("<3.수정>");
				
				System.out.print("번호 : ");
				int dNum = sc.nextInt();
				
				System.out.println("<2.등록>");

				System.out.print(">이름: ");
				String fName = sc.next();

				System.out.print(">휴대전화: ");
				String fHp = sc.next();

				System.out.print(">회사번호: ");
				String fCompany = sc.next();
				
				PhoneVo uPhoneVo = new PhoneVo(dNum, fName, fHp, fCompany);
				
				int uCount = phoneDao.personUpdate(uPhoneVo);
				
				if(uCount > 0) {
					System.out.println("[수정되었습니다.]");
				} else {
					System.out.println("[관리자에게 문의하세요(" + uCount + ")]");
				}
				
				personList = phoneDao.getPersonList();
				
				printList(personList);
				
				break;
				
			case 4:

				System.out.println("<4.삭제>");

				System.out.print("번호 : ");
				int delNum = sc.nextInt();

				int dCount = phoneDao.personDelete(delNum);
				
				if(dCount > 0) {
					System.out.println("[삭제되었습니다.]");
				} else {
					System.out.println("[관리자에게 문의하세요(" + dCount + ")]");
				}
				
				personList = phoneDao.getPersonList();
				
				printList(personList);
		
				break;

			case 5:

				System.out.println("<5. 검색>");
				System.out.print("검색어: ");
				String keyword = sc.next();
					
				PhoneVo sPhoneVo = new PhoneVo(keyword, keyword, keyword);
				
				int sCount = phoneDao.personSearch(sPhoneVo);
				
				if(sCount>0) {
					System.out.println("[검색이 완료되었습니다.]");
				}else {
					System.out.println("[관리자에게 문의하세요(" + sCount + ")]");
				}
				
				personList = phoneDao.getPersonList();
				
				printList(personList);
			
				break;

			default:

				System.out.println("[다시 입력해 주세요.]");

				break;

					
			}
		}
		
		sc.close();
		
		}


		//////////////////////LIST////////////////////////
		
		private static void printList(List<PhoneVo> personList) {
			
			for(int i=0; i<personList.size(); i++) {
				
				PhoneVo phoneVo = personList.get(i);
				
				int personId = phoneVo.getPersonId();
				String personName = phoneVo.getName();
				String personHp = phoneVo.getHp();
				String personCompany = phoneVo.getCompany();
				
				System.out.println(personId + "\t" + personName + "\t" + personHp + "\t" + personCompany);
				
			
			}
			
			System.out.println("");
			
		}

}