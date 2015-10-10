package chap5;

import java.util.Arrays;

public class FriendScore {

	public static void main(String[] args) {

		// String[] friends = { "NYY", "YNY", "YYN" };
		String[] friends = { "NYNNN", "YNYNN", "NYNYN", "NNYNY", "NNNYN" };
		int ans = highestScore(friends);
		System.out.println(ans);

	}

	public static int highestScore(String[] friends) {
		int max = 0;

		for (int i = 0; i < friends.length; i++) {// friends[i]を走査対象としてピックアップ
			int cnt = 0;// 友人の数をカウントするための一時変数
			boolean[] check = new boolean[friends.length];// 重複除去の為の配列

			for (int j = 0; j < friends[i].length(); j++) {
				if (friends[i].charAt(j) == 'Y' && j != i) {// friends[i]内に直接の友人がいたら
					check[j] = true;

					for (int k = 0; k < friends[i].length(); k++) {// friends[j]内の友人（friends[i]の間接の友人）を捜査
						if (friends[j].charAt(k) == 'Y' && k != i) {// 友人が居たら
							check[k] = true;
						}
					}

				}
			}

			for (int j = 0; j < check.length; j++) {
				if (check[j] == true) {
					cnt++;
				}
			}

			max = Math.max(max, cnt);
		}

		return max;
	}

}
