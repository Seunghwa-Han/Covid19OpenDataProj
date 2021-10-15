package com.example;

import java.util.Scanner;

import com.example.dao.Covid19HospitalList;
import com.example.dao.Datum;
import com.example.service.Util;

public class Main {

	public static void main(String[] args) {
		int totalCount = Util.getTotalCount();
		
		System.out.println("현재 전국의 코로나19 접종예방센터는 " + totalCount + "개가 등록되어 있습니다.");

		Covid19HospitalList list = Util.getCovid19HospitalInfo(1, totalCount);
		
		Scanner s = new Scanner(System.in);
		while(true) {
			System.out.print("\n시설명 또는 지역 입력 (X 종료): ");
			String line = s.nextLine();
			if(line.equals("X"))
				break;
			
			System.out.println("-----------------------");
			for(Datum item : list.getData()) {
				if(item.getAddress().contains(line)|| item.getFacilityName().contains(line)) {
					System.out.println("센터이름: " + item.getCenterName());
					System.out.println("주소: " + item.getAddress());
					System.out.println("시설명: " + item.getFacilityName());
					System.out.println("전화번호: " + item.getPhoneNumber());
					System.out.println("수정일: " + item.getUpdatedAt());
					System.out.println("");
				}
			}
		}
		System.out.println("종료되었습니다~ ");
	}

}
