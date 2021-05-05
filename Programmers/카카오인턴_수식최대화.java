package programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class 카카오인턴_수식최대화 {
	static List<Integer> operator;
	static long answer;

	public static void main(String[] args) {
		String expression = "50*6-3*2";
		System.out.println(solution(expression));
	}

	public static long solution(String expression) {
		answer = 0;
		boolean[] operatorCheck = new boolean[3];
		// 연산자 갯수 체크
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '+')
				operatorCheck[0] = true;
			else if (expression.charAt(i) == '-')
				operatorCheck[1] = true;
			else if (expression.charAt(i) == '*')
				operatorCheck[2] = true;
		}

		operator = new ArrayList<>();
		for (int i = 0; i < operatorCheck.length; i++) {
			if (operatorCheck[i])
				operator.add(i);
		}
		Per(0, operator.size(), new int[operator.size()], new boolean[operator.size()], expression);
		return answer;
	}

	private static void Per(int cnt, int n, int[] selected, boolean[] visited, String expression) {
		if (cnt == n) { // selected[]가 다 채워짐
			// System.out.println(Arrays.toString(selected));
			cal(selected, expression);
			return;
		}
		for (int i = 0; i < operator.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected[cnt] = operator.get(i);
				Per(cnt + 1, n, selected, visited, expression);
				visited[i] = false;
			}
		}
	}

	// 연산자 우선순위가 담겨있는 배열 priority를 사용해 수식을 계산하는 함수
	private static void cal(int[] priority, String expression) {
		// 0:+ 1:- 2:*
		// 각 연산자의 우선순위를 hashmap에 담음
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < priority.length; i++) {
			if (priority[i] == 0)
				map.put('+', i);
			else if (priority[i] == 1)
				map.put('-', i);
			else if (priority[i] == 2)
				map.put('*', i);
		}
		// expression에서 숫자 뽑아냄
		// StringTokenizer st = new StringTokenizer(expression,"+|-|*");
		// 1. 수식을 후위표기법으로 변환
		Stack<Character> stack = new Stack<>(); // postfix로 바꾸기 위해 필요한 스택
		String postfix = "";
		int index = 0; // 현재 탐색하는 expression 인덱스
		while(true) {
			while (index < expression.length() && expression.charAt(index) >= '0' && expression.charAt(index) <= '9') { // 숫자는 피연산자이므로 무조건 출력
				postfix += expression.charAt(index++);
			}
			postfix += " "; // 다음 피연산자와 구별하기 위해 공백 하나 넣어준다
			if(index == expression.length()) break;
			
			// index : 연산자 등장
			char op = expression.charAt(index);
			// 스택에 들어있는 연산자중 자신보다 우선순위가 높은것들은 다 출력한다.
			while (!stack.isEmpty() && map.get(stack.peek()) <= map.get(op)) {
				postfix += stack.pop() + " ";
			}
			stack.push(op);
			index++;
		}
		while(!stack.isEmpty()) postfix+=stack.pop()+" ";
		
		//2. 후위표기법으로 변환된 수식을 계산
		Stack<Long>stack2 = new Stack<>();
		StringTokenizer st = new StringTokenizer(postfix," ");
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			if(!token.equals("+") && !token.equals("-") && !token.equals("*")) {	//숫자라면
				stack2.push(Long.parseLong(token));
			}else {
				long op2=stack2.pop();
				long op1=stack2.pop();
				if(token.equals("+")) {
					stack2.push(op1+op2);
				}else if(token.equals("-")) {
					stack2.push(op1-op2);
				}else if(token.equals("*")) {
					stack2.push(op1*op2);
				}
			}
		}
		answer = Math.max(answer, Math.abs(stack2.peek()));
	}
}
