package chap5;

import java.util.Arrays;
import java.util.StringJoiner;

public class AllCombination {

	static String[] way = { "E", "W", "S", "N" };
	static String[] ss;// 文字列を保持
	static String[] pattern;
	static int cnt = 0;
	static int n;// ステップ数
	static String s = "";

	public static void main(String[] args) {
		n = 3;
		ss = new String[n];// 文字列の長さはnステップ分
		System.out.println("ステップn = " + n);
		pattern = new String[(int) Math.pow(way.length, n)];
		System.out.println("pattern = " + pattern.length);
		comb(0);
		System.out.println(Arrays.toString(pattern));

	}

	public static void comb(int idx) {

		for (int i = 0; i < way.length; i++) {
			if (idx < n) {
				ss[idx] = way[i];// 自桁に文字を入力
				comb(idx + 1);// 桁を１つずらして再帰
			} else {// 末尾桁に達したら
				for (int j = 0; j < ss.length; j++) {// 文字列を生成
					s += ss[j];
				}
				pattern[cnt] = s;// 生成した文字列を保存
				s = "";// 文字列をリセット
				cnt++;
				return;
			}
		}

	}

}
