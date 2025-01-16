package minseok;

import java.util.*;

// 위시리스트 관리로직
public class WishlistManagement {
	// 이름 기준 위시리스트들을 받아줄 객체
	private Map<String, List<Wishlist>> wishlistMap = new HashMap<>(); 
	// 중복 체크
	// 내용이 중복되는지 이름 기준으로 확인
    private Map<String, Set<String>> contentSetMap  = new HashMap<>();
    // 작성자별 ID 카운터
    private Map<String, Integer> idCounters = new HashMap<>(); 
    
    Scanner sc = new Scanner(System.in);
	
	public void addWishlist() {
		System.out.print("작성자 이름을 입력하세요: ");
		String name = sc.nextLine();
		
		System.out.println("위시리스트를 입력하세요: ");
		String content = sc.nextLine();
		
		// 이름이 처음들어온다면 그에 해당되는 중복체킹 set 생성
		contentSetMap.putIfAbsent(name, new HashSet<>());
		// 해당 맵에 중복값이 존재하면 중단
		if(contentSetMap.get(name).contains(content)) {
			System.out.println("이미 존재하는 위시리스트입니다. 처음부터 다시 입력하세요");
			return;
		}
		
		System.out.println("중요도 입력 (1,2,3): ");
		int importance = sc.nextInt();
		sc.nextLine(); // 자간 정리
		
	    // 작성자별 ID 카운터 생성 및 증가
	    idCounters.putIfAbsent(name, 1); // 처음 추가되는 작성자는 ID 1부터 시작
	    int idNumber = idCounters.get(name);
	    String id = idNumber + ""; // int 값 문자열로 변환함
	    idCounters.put(name, idNumber + 1); // ID 카운터 증가
	    
	    Wishlist wishlist = new Wishlist(id, name, content, importance);
	    
	    // 작성자별 위시리스트 추가
	    wishlistMap.putIfAbsent(name, new ArrayList<>());
	    wishlistMap.get(name).add(wishlist);
	    contentSetMap.get(name).add(content);
	    
	    System.out.println("위시리스트가 추가되었습니다: " + wishlist);
	}
	// 전체 출력
	public void printAllWishlists() {
		// 위시리스트 이름별 맵 데이터가 존재하지 않으면 함수 중단
		if(wishlistMap.isEmpty()) {
			System.out.println("위시리스트가 존재하지 않습니다.");
			return;
		}
		System.out.println("\n전체 리스트 출력(작성자별): ");
		wishlistMap.keySet().stream()
			.sorted()
			.forEach(name -> {
				System.out.println("-----------------" + name +"-----------------");
				// 이름에 해당되는 데이터 불러오기
				List<Wishlist> wishlists = wishlistMap.get(name);
				// 불러온 데이터 모두 출력 
				wishlists.forEach(w -> 
					System.out.println(w.getId() + " 번 - " + w.getContent() + "/ 중요도 - " + w.getImportance())
				);
		});
	}
	// 위시리스트 삭제
	public void deleteWishlist() {
		System.out.println("삭제할 리스트를 가진 작성자 이름을 입력하세요");
		String name = sc.nextLine();
		
		if (!wishlistMap.containsKey(name)) {
			System.out.println("해당 이름의 작성자가 존재하지 않습니다...");
			return;
		}
		// 작성자의 위시리스트 출력
	    List<Wishlist> wishlists = wishlistMap.get(name);
	    System.out.println("\n" + name + " 님의 위시리스트: ");
	    wishlists.forEach(wishlist -> System.out.println(wishlist.getId() + " - " + wishlist.getContent()));
		
		System.out.println("삭제할 위시리스트의 순번을 입력하세요: ");
		String id = sc.nextLine();
		
		// 작성자의 위시리스트에서 해당 순번을 가진 위시리스트 탐색
		boolean removed = wishlists.removeIf(w -> w.getId().equals(id));
		
		if(removed) {
			System.out.println("위시리스트가 삭제되었습니다.");
		} else {
			System.out.println("삭제할 위시리스트가 존재하지 않습니다.");
		}
	}
	// 작성자, 중요도별 위시리스트 확인하기
	public void searchWishlistByImportance() {
		System.out.print("조회하길 원하는 작성자명: ");
		String name = sc.nextLine();
		
		// 해당 작성자가 없는 경우
		if (!wishlistMap.containsKey(name)) {
			System.out.println("작성자의 위시리스트가 존재하지 않습니다.");
			return;
		}
		
		// 작성자의 위시리스트 목록
		List<Wishlist> wishlists = wishlistMap.get(name);
		
		// 중요도 입력받기
		System.out.print("우선순위별조회 (1,2,3 순위 / 0은 모든 조회 >> ");
		int importance = sc.nextInt();
		sc.nextLine(); // 초기화
		
		// 중요도가 0이면 해당 작성자의 위시리스트 출력
		if(importance == 0) {
			wishlists.forEach(w -> System.out.println(w.getId() + " - " + w.getContent()));
		} else {
			boolean found = false;
			System.out.println("\n" + name + "님의 " + importance + "번째 우선순위 리스트");
			for(Wishlist w : wishlists) {
				if(w.getImportance() == importance) {
					System.out.println(w.getId() + " - " + w.getContent());
					found = true;
				}
				if(!found) {
					System.out.println("해당 우선순위의 위시리스트가 없습니다.");
				}
			}
		}
	}
	
}
