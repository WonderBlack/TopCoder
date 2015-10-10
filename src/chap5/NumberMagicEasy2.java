package chap5;

// お手本のコード

import java.util.*;

public class NumberMagicEasy2 {

	public static void main(String[] args) {

		String[] answer = { "YNYY", "YNNN", "NNNN", "YYYY", "NYNY" };
		int[] answer2 = new int[answer.length];

		for (int i = 0; i < answer.length; i++) {
			answer2[i] = theNumber2(answer[i]);
		}

		System.out.println(Arrays.toString(answer2));
	}

	public static int theNumber(String answer) {

		int[] A = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] B = { 1, 2, 3, 4, 9, 10, 11, 12 };
		int[] C = { 1, 2, 5, 6, 9, 10, 13, 14 };
		int[] D = { 1, 3, 5, 7, 9, 11, 13, 15 };

		// １から１６までの数字が各配列に含まれているかをチェック
		for (int i = 1; i <= 16; i++) {
			// 数字ｉが配列Ａの中にあるか？また、それが花子がＹと言った数字が？
			// つまり配列の中の有無（ＹかＮか）と花子が言ったＹＮが一致している数字は答えの可能性がある数字
			// 全配列でその数字ｉがその基準をくぐり抜けたらそれが正解
			if (Check(A, i) != answer.charAt(0))
				continue;
			if (Check(B, i) != answer.charAt(1))
				continue;
			if (Check(C, i) != answer.charAt(2))
				continue;
			if (Check(D, i) != answer.charAt(3))
				continue;
			return i;
		}
		return 0;
	}

	// numberの数字が配列Xに含まれるかをチェック
	static char Check(int[] X, int number) {
		for (int x : X) {
			if (x == number) {
				return 'Y';
			}
		}
		return 'N';
	}

	public static int theNumber2(String answer) {

		String[] c = {
				"YYYYYYYYNNNNNNNN",
				"YYYYNNNNYYYYNNNN",
				"YYNNYYNNYYNNYYNN",
				"YNYNYNYNYNYNYNYN"
				};
		// 文字列tempを生成してそれがanswerと一致したら正解
		for (int i = 0; i <= 15; i++) {
			String temp = "";
			// 配列cの各要素を串刺ししながら文字列を生成
			for (int j = 0; j < 4; j++) {
				temp += c[j].charAt(i);
				if (answer.equals(temp)) {
					return i + 1;
				}
			}
		}
		return 0;
	}
	
	public static int theNumber3(String answer) {
		
		String[] c = {
				"YYYY",
				"YYYN",
				"YYNY",
				"YYNN",
				"YNYY",
				"YNYN",
				"YNNY",
				"YNNN",
				"NYYY",
				"NYYN",
				"NYNY",
				"NYNN",
				"NNYY",
				"NNYN",
				"NNNY",
				"NNNN"
		};
		for (int i = 0; i <= 15; i++) {
			if (answer.equals(c[i])) {
				return i + 1;
			}
		}
		return 0;
	}

}
