package minseok;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Wishlist {
	// id값과 name은 매니징하면서 생성되는 데이터
	private String id;
	private String name;
	private String content;
	private int importance;
}
