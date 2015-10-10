package chap5;

import java.util.Arrays;

public class Cryptography {

	public static void main(String[] args) {
		int[] numbers = { 1, 3, 2, 1, 1, 3 };

		long ans = encrypt(numbers);
		System.out.println(ans);

	}

	public static long encrypt(int[] numbers) {

		long[] sums = new long[numbers.length];

		// 配列の乗算合計を算出する作業を繰り返す
		for (int i = 0; i < numbers.length; i++) {
			long sum = 1;
			for (int j = 0; j < numbers.length; j++) {
				if (j == i) {// 1を足す数の番になったら
					sum = sum * ((long) numbers[j] + 1);
				} else {
					sum = sum * (long) numbers[j];
				}
			}
			sums[i] = sum;
		}

		// sumsの中で一番大きい値をansとする
		long ans = 0;

		for (long i : sums) {
			ans = Math.max(ans, i);
		}

		return ans;
	}
	
	public static long encrypt2(int[] numbers) {
		
		long ret = 1;
		Arrays.sort(numbers);
		numbers[0]++;
		for (int i =  0; i < numbers.length; i++) {
			ret *= numbers[i];
		}
		return ret;
	}

}
