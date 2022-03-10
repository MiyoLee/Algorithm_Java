package 구현;

public class PRO_k진수에서소수개수구하기_L2 {
	public static void main(String[] args) {
		int n = 789437674;
		int k = 16;
		System.out.println(solution(n,k));
	}

	public static int solution(int n, int k) {
		int answer = 0;
		
		String convertedN = conversion(n,k);
		String number = "";	//조건에 맞는 소수 후보
		for (int i = 0; i < convertedN.length(); i++) {
			if(convertedN.charAt(i)=='0') {
				//number가 소수인지 체크
				if(number!="" && isPrime(Long.parseLong(number))) {
					++answer;
				}
				number="";	//초기화
			}else {
				number+=convertedN.charAt(i);
			}
		}
		if(number!="" && isPrime(Long.parseLong(number))) {
			++answer;
		}
        return answer;
	}
	
	private static boolean isPrime(long number) {
		if(number==1) return false;
		
		for (long i = 2; i <= Math.sqrt(number); i++) {
			if(number%i==0) return false;
		}
		return true;
	}

	public static String conversion(int n, int k){
		String res = "";
        while(n > 0) {
        	if(n%k < 10) {
        		res = n % k + res;
        	}else {
        		res = (char)(n % k - 10 + 'A') + res;
        	}
            n /= k;
        }
        return res;
    }
}
