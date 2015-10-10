package chap5;

import java.util.Arrays;

public class ThePalindrome {

	public static void main(String[] args) {

		String s1 = "abab";
		String s2 = "abcdcba";
		String s3 = "qwerty";
		String s4 = "abdfhdyrbdbsdfghjkllkjhgfds";
		String s5 = "abcddcba";

		int ans1 = find3(s5);
		int ans2 = find(s5);

		// int ans2 = find3(s2);
		// int ans3 = find3(s3);
		// int ans4 = find3(s4);

		System.out.println(ans1);
		System.out.println(ans2);
		// System.out.println(ans3);
		// System.out.println(ans4);

	}

	public static int find(String s) {
		int ans = 0;// 回文にするために必要な文字数
		boolean flg = false;

		String[] ss = s.split("", 0);

		int fp = 0;// 前ポインター
		int rp = s.length() - 1;// 後ポインター

		for (int i = 0; i < s.length(); i++) {

			if (ss[fp].equals(ss[rp])) {// 文字が同じなら

				int f = fp;
				int r = rp;

				while (true) {

					if (f == r && ss[f].equals(ss[r]) || (f + 1) == r
							&& ss[f].equals(ss[r])) {// 終了条件
						flg = true;
						break;
					}
					f++;
					r--;
					if (!ss[f].equals(ss[r])) {// 文字が違うなら
						fp++;// 前ポインターのみ後にずらす
						break;
					}
				}

			} else {// 文字が違うなら
				fp++;// 前ポインターのみ後にずらす
			}

			if (flg == true) {
				ans = fp + s.length();
				break;
			}

		}

		return ans;

	}

	public static int find2(String s) {

		int ans = 0;// 回文にするために必要な文字数
		String[] ss = s.split("", 0);
		int cp = ss.length / 2;// 中央値を割り出す、中央ポインター
		boolean flg = false;
		int i = cp;// 中央ポインター
		int mem = 0;// fpをcpの位置に動かした場合の調整用変数

		while (flg == false) {
			int fp = i - 1;// 前ポインター
			int rp = i + 1;// 後ポインター
			if (i >= ss.length - 1) {
				ans = i - ((ss.length - 1) - i);
				break;
			}
			if (ss[i].equals(ss[rp])) {// 中央値とその１つ後の値が同じ文字だったら
				fp = i;// fpをcpの１つ前ではなく、cpと同じにする
				mem = 1;
			}
			if (flg == false) {
				while (flg == false) {// rpが最後尾に達するまで
					if (ss[fp].equals(ss[rp])) {// fpとrpが同じならさらに探索範囲を前後に広げる
						fp--;
						rp++;
					} else {
						mem = 0;
						break;// fpとrpが一致しないなら、これ以上探索しても仕方がないので、cp (=i)をずらす
					}
					if (rp >= ss.length) {// 最後尾まで走査が完了したら
						flg = true;
						ans = i - ((ss.length - 1) - i);
					}
				}
			}
			i++;
		}
		ans += ss.length;
		ans += mem;

		return ans;
	}

	public static int find3(String s) {
		for (int i = s.length();; i++) {// iを回文の全長とする flagがtrueになるまでは無限ループ
			boolean flag = true;
			for (int j = 0; j < s.length(); j++) {
				// 反対側の文字が存在し、なおかつ違う文字であれば
				if ((i - j - 1) < s.length()
						&& s.charAt(j) != s.charAt(i - j - 1)) {// i - j -
																// 1がsの長さより短くかつ、両端の文字が違ったら
					flag = false;// flagをfalseにしてforを抜ける
					break;
				}
			}
			if (flag) {
				return i;
			}
		}
	}

}
