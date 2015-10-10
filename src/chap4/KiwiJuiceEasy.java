package chap4;

import java.util.Arrays;

public class KiwiJuiceEasy {

	public static void main(String[] args) {

		int[] capacities = { 30, 20, 10 };
		int[] bottles = { 10, 5, 5 };
		int[] fromId = { 0, 1, 2 };
		int[] toId = { 1, 2, 0 };
		
		int[] ans = thePouring(capacities, bottles, fromId, toId);
		
		System.out.println(Arrays.toString(ans));
		
	}

	public static int[] thePouring(int[] capacities, int[] bottles, int[] fromId,
			int[] toId) {
		
		for (int i = 0; i < fromId.length; i++) {
			int f = fromId[i];// 注ぎ元のID
			int t = toId[i];// 注ぎ先のID
			int space = capacities[t] - bottles[t];// 注ぎ先の空きを確認
			
			if (space >= bottles[f]) {// 注ぎ元に入っているジュースの量より注ぎ先の空きが大きいなら　＝　ジュースが全部入るなら
				int vol = bottles[f];// 注ぎ元の全ジュース量をvolとする
				bottles[t] += vol;// 注ぎ先にそれを足す
				bottles[f] = 0;// 注ぎ元のジュースを空にする
			} else {// 全部のジュースが入らないなら
				int vol = space;// 注ぎ先の空き容量をvolとする
				bottles[t] += vol;// 注ぎ先にそれを足す
				bottles[f] -= vol;// 注ぎ元からそれを引く
			}
		}
		
		return bottles;

	}
	
	public static int[] thePouring1(int[] capacities, int[] bottles, int[] fromId,
			int[] toId) {
		
		for (int i = 0; i < fromId.length; i++) {
			int f = fromId[i];// 注ぎ元のID
			int t = toId[i];// 注ぎ先のID
			// 注ぎ元のジュースの量と注ぎ先の空き容量を比較して小さい方をvolに代入
			int vol = Math.min(bottles[f], capacities[t] - bottles[t]);
			
			bottles[f] -= vol;// 注ぎ元からそれを引く
			bottles[t] += vol;// 注ぎ先にそれを足す
			
		}
		
		return bottles;

	}

	
	public static int[] thePouring2(int[] capacities, int[] bottles, int[] fromId,
			int[] toId) {
		
		for (int i = 0; i < fromId.length; i++) {
			int sum = bottles[fromId[i]] + bottles[toId[i]];// 注ぎ元のジュースと注ぎ先のジュースを足す
			bottles[toId[i]] = Math.min(sum,  capacities[toId[i]]);// sumと注ぎ先の容量の小さい方を注ぎ先のジュースの量とする
			bottles[fromId[i]] = sum - bottles[toId[i]];// sumから注ぎ先に入っているジュースの量を引く
			
		}
		
		return bottles;

	}

}
