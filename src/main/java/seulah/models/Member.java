package seulah.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {
	private String name;	// 참여자 이름
	private double balance; // 정산 잔액
	
	public void updateBalance(double amount) { // 정산 잔액 업데이트
		this.balance +=amount;
	}

}
