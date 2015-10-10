package chap5;

import java.util.Arrays;

public class NumberMagicEasy {

	public static void main(String[] args) {

		String[] answer = { "YNYY", "YNNN", "NNNN", "YYYY", "NYNY" };
		int[] answer2 = new int[answer.length];

		for (int i = 0; i < answer.length; i++) {
			answer2[i] = theNumber(answer[i]);
		}

		System.out.println(Arrays.toString(answer2));
	}

	public static int theNumber(String answer) {

		System.out.println(answer);

		int ans = 16;

		if (answer.equals("NNNN")) {
			return ans;
		}

		// int[][] card = {
		// { 1, 2, 3, 4, 5, 6, 7, 8 },
		// { 1, 2, 3, 4, 9, 10, 11, 12 },
		// { 1, 2, 5, 6, 9, 10, 13, 14 },
		// { 1, 3, 5, 7, 9, 11, 13, 15 }
		// };

		int[][] card = { { 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0 },
				{ 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0 },
				{ 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 } };

		// Ｎのカードに含まれる数値を取り除く
		for (int i = 0; i < answer.length(); i++) {
			if (answer.charAt(i) == 'N') {
				for (int j = 0; j < card[i].length; j++) {
					if (card[i][j] == 1) {// 数字をピックアップし、他のカードから取る
						for (int k = 0; k < card.length; k++) {
							card[k][j] = 0;
						}
					}
				}
			}
		}
		System.out.println("Yのカードのみ表示");
		for (int i = 0; i < card.length; i++) {
			if (answer.charAt(i) == 'Y') {
				System.out.println(Arrays.toString(card[i]));
			}
		}

		// 残った数字から共通の物を探し答えとする
		for (int i = 0; i < answer.length(); i++) {
			if (answer.charAt(i) == 'Y') {
				for (int j = 0; j < card[i].length; j++) {
					// Ｙのカードｉから数字ｊをピックアップし、他のＹのカードの中にも存在するか調べる
					if (card[i][j] == 1) {
						boolean flg = true;
						for (int k = 0; k < card.length; k++) {
							if (answer.charAt(k) == 'Y' && card[k][j] == 0) {// 他のＹのカードには存在しなかった
								flg = false;
							}
						}
						if (flg) {
							return j;
						}
					}
				}
			}
		}

		return ans;

	}

}
