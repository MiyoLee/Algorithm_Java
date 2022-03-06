package 구현;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PRO_신고결과받기 {
	public static void main(String[] args) {
		String[] id_list = {"muzi", "frodo", "apeach", "neo"};
		String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
		int k = 2;
		solution(id_list, report, k);
	}
	
	static public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        Map<String,Integer> id_index = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
			id_index.put(id_list[i], i);
		}     
        
        Map<String, List<String>> idsReportedMe = new HashMap<>();
        
        for (int i = 0; i < report.length; i++) {
			String[] ids = report[i].split(" ");
			String from = ids[0];
			String to = ids[1];
			if(!idsReportedMe.containsKey(to)) {
				idsReportedMe.put(to, new ArrayList<>());
			}
			if (!idsReportedMe.get(to).contains(from)) {
				idsReportedMe.get(to).add(from);
			}
		}
        
        for (int i = 0; i < id_list.length; i++) {
        	List<String> fromList = idsReportedMe.get(id_list[i]);
			if(fromList!=null && fromList.size()>=k) {
				for (int j = 0; j < fromList.size(); j++) {
					++answer[id_index.get(fromList.get(j))];
				}
			}
		}
        
        return answer;
    }
}
