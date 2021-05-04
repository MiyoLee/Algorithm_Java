package programmers;

public class 카카오인턴_키패드누르기 {
	//static char[][] pad = { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' }, { '*', '0', '#' } };

	public static void main(String[] args) {
		int[] numbers = { 7,0,8,2,8,3,1,5,7,6,2 };
		String hand = "left";
		System.out.println(solution(numbers, hand));
	}

	public static String solution(int[] numbers, String hand) {
		String answer = "";
		
		char h = '\0';
		if(hand.equals("left"))h='L';
		else h='R';
		
		int left = 10;	//현재 왼쪽엄지의 위치
		int right = 12;	//현재 오른쪽 엄지의 위치
		
		for (int i = 0; i < numbers.length; i++) {
			if(numbers[i]==0) numbers[i]=11;
			
			if(numbers[i]%3==1) {		//1 4 7
				answer+="L";
				left=numbers[i];
			}else if(numbers[i]%3==0) {	//3 6 9 12
				answer+="R";
				right=numbers[i];
			}else {		//2 5 8 0 (가운댓줄)
				// 거리 비교 해야함.
				//left, right의 row
				int leftr=(left-1)/3;		
				int rightr=(right-1)/3;
				//left, right의 col
				int leftc=(left-1)%3;
				int rightc=(right-1)%3;
				
				//numbers[i]의 row
				int row = (numbers[i]-1)/3;
				//numbers[i]의 col
				int col = (numbers[i]-1)%3;
				
				int ldist = Math.abs(row-leftr)+Math.abs(col-leftc);
				int rdist = Math.abs(row-rightr)+Math.abs(col-rightc);
				
				if(ldist<rdist) {
					answer+="L";
					left=numbers[i];
				}else if(ldist>rdist) {
					answer+="R";
					right=numbers[i];
				}else {
					answer+=h;
					if(h=='L')left=numbers[i];
					else right=numbers[i];
				}
			}
		}
		return answer;
	}
}
