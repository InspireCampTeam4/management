package seulah.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Expense {
	private String participant; // 참여자
	private String category;	// 지출 항목
	private double amount;		// 지출 금액
	private LocalDate date;		// 지출 날짜

}
