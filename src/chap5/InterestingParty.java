package chap5;

import java.util.Arrays;
import java.util.HashMap;

public class InterestingParty {

	public static void main(String[] args) {

		String[] first = { "fishing", "gardening", "swiming", "fishing" };
		String[] second = { "hunting", "fishing", "fishing", "biting" };

		int ans = bestInvitation(first, second);
		int ans2 = bestInvitation2(first, second);

		System.out.println(ans);
		System.out.println(ans2);

	}

	public static int bestInvitation(String[] first, String[] second) {
		int[] cntf = new int[first.length];
		int[] cnts = new int[second.length];

		// キーワードと同じ文字列を検索
		for (int i = 0; i < first.length; i++) {
			String f = first[i];
			String s = second[i];
			for (int j = i; j < first.length; j++) {
				if (f.equals(first[j])) {
					cntf[i]++;
				}
				if (f.equals(second[j])) {
					cntf[i]++;
				}
				if (s.equals(first[j])) {
					cnts[i]++;
				}
				if (s.equals(second[j])) {
					cnts[i]++;
				}
			}
		}

		int max = cntf[0];

		for (int i = 0; i < cntf.length; i++) {
			if (max < cntf[i]) {
				max = cntf[i];
			}
			if (max < cnts[i]) {
				max = cnts[i];
			}
		}

		return max;
	}

	public static int bestInvitation2(String[] first, String[] second) {// 重複を除去したバージョン
		HashMap<String, Integer> dic = new HashMap<String, Integer>();

		for (int i = 0; i < first.length; i++) {
			dic.put(first[i], 0);
			dic.put(second[i], 0);
		}

		for (int i = 0; i < first.length; i++) {
			dic.put(first[i], dic.get(first[i]) + 1);
			dic.put(second[i], dic.get(second[i]) + 1);
		}

		int ans = 0;

		for (String key : dic.keySet()) {
			ans = Math.max(ans, dic.get(key));
		}
		
		// ハッシュマップの中身を表示
		for (String key : dic.keySet()) {
			System.out.println(key + ":" + dic.get(key));
		}

		return ans;
	}

}
