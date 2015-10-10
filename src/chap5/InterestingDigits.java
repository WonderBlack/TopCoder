package chap5;

import java.util.ArrayList;
import java.util.Arrays;

public class InterestingDigits {

	public static void main(String[] args) {

		int base = 5;
		
		int []ans = digits(base);
		
		System.out.println(Arrays.toString(ans));

	}

	public static int[] digits(int base) {

		ArrayList<Integer> v = new ArrayList<Integer>();
		
		// base進数
		// nは仮の答え

		for (int n = 2; n < base; n++) {// 1000の位
			boolean ok = true;
			// base進数における、100の位までの全ての数値の組み合わせを生成。5進数なら000 ~ 444まで
			for (int k1 = 0; k1 < base; k1++) {// 100の位
				for (int k2 = 0; k2 < base; k2++) {// 10の位
					for (int k3 = 0; k3 < base; k3++) {// 1の位
						// (k1 + k2 * base + k3 * base * base)の部分は生成された数値の組み合わせをbase進数から10進数に変換
						if ((k1 + k2 * base + k3 * base * base) % n == 0
								&& (k1 + k2 + k3) % n != 0) {// 生成された数値が割り切れる（その数値はnの倍数である）なおかつ、各桁の総和からnを割った剰余が0ではないか？（各桁の総和もnの倍数である）
							ok = false;//この条件式が満たされると４重になっているfor文の外に出て、boolean okがtrueに切り替わり、結果としてその数が答えの一つとして配列に加わる
							break;
						}
					}
					if (!ok) {
						break;
					}
				}
				if (!ok) {
					break;
				}
			}
			if (ok) {
				v.add(n);
			}
		}

		int[] ans = new int[v.size()];
		for (int i = 0; i < v.size(); i++) {
			ans[i] = v.get(i);
		}

		return ans;
	}

}
