package chap7;

import java.util.ArrayList;
import java.util.Arrays;

public class Knapsack {

	static int[] ws = { 3, 4, 1, 2, 3 };// 各商品の重量
	static int[] ps = { 2, 3, 2, 3, 6 };// 各商品の金額
	static int cap = 10;// ナップザックの最大重量
	static ArrayList<Integer> w = new ArrayList<Integer>();// パターン毎のナップザックの重さを保存
	static ArrayList<Integer> p = new ArrayList<Integer>();// パターン毎のナップザックの金額を保存
	static ArrayList<int[]> pattern = new ArrayList<int[]>();// 出来上がった組み合わせを保存
	static int[] pat = new int[5];// 商品の組み合わせを保存

	public static void main(String[] args) {
		Arrays.fill(pat, -1);// 0も組み合わせに使うのでpatを-1で初期化しておく
		search();
	}

	public static void search() {

		// 0,1,2,3,4の商品を重複なしで組み合わせる
		// 合計１０ｋｇまで
		// 荷物の量は１〜５のケースが考えられ、その全てにおいて、全組み合わせを出す
		
		System.out.println("商品一覧");
		for (int i = 0; i < ws.length; i++) {
			System.out.println("商品 " + i + " 重量 " + ws[i] + " 金額 " + ps[i] );
		}
		System.out.println();

		// ｉ個の荷物を選ぶ　ｉは荷物の量
		for (int i = 1; i <= 6; i++) {
			pack(0, i);
		}

//		System.out.println("出来上がったパターン");
//		// 出来上がった全てのパターンを表示
//		for (int i = 0; i < pattern.size(); i++) {
//			System.out.println(Arrays.toString(pattern.get(i)));
//		}
//		System.out.println();

		// 重複を除去する
		for (int i = 0; i < pattern.size(); i++) {
			// チェックするパターンを選び、それをtrue,false形式に変換
			boolean[] x = converter(pattern.get(i));
			// System.out.println("チェックする配列" + Arrays.toString(x));
			for (int j = 0; j < pattern.size(); j++) {
				if (j != i) {
					// 比較対象のパターンを選び、それをture,false形式に変換
					boolean[] y = converter(pattern.get(j));
					// System.out.println("比較する配列" + Arrays.toString(y));
					// 二つの配列が同じパターンだったら
					if (Arrays.equals(x, y)) {
//						System.out.println(Arrays.toString(x));
//						System.out.println(Arrays.toString(y));
//						System.out.println("両者は同じなのでパターン" + j + "を除去します");
						// そのパターンを消去
						pattern.remove(j);
					}
				}
			}
		}

//		// 重複除去後の全てのパターンを表示
//		System.out.println("重複を除去した後のパターン");
//		for (int i = 0; i < pattern.size(); i++) {
//			System.out.println(Arrays.toString(pattern.get(i)));
//		}
//		System.out.println();
		
		// 各パターンの金額と重量を計算
		// 計算対象のパターンを選ぶ
		for (int i = 0; i < pattern.size(); i++) {
			int totalW = 0;// 総重量
			int totalP = 0;// 総額
			// そのパターン内の重量と金額を集計
			for (int j = 0; j < pattern.get(i).length; j++) {
				int id = pattern.get(i)[j];// 金額や重量を検索する為の商品ＩＤ（ｊ）を取得
				if (id != -1) {// -1はnullなのでそれ以外を処理する
					// 該当ＩＤの重量と金額を合計に足し込む
					totalW += ws[id];
					totalP += ps[id];
				}
			}
			// 集計した物をリストに保存
			w.add(totalW);
			p.add(totalP);
		}
		
		// 集計後の全てのパターンと金額、重量を表示
		System.out.println("詰め合わせのパターンとその金額を表示");
		for (int i = 0; i < pattern.size(); i++) {
			System.out.print("パターン" + i + " ");
			System.out.print(Arrays.toString(pattern.get(i)) + "  ");
			System.out.println("重量 " + w.get(i) + " 金額 " + p.get(i));
		}
		System.out.println();
		
		// １０ｋｇ以下で、金額が最大の物を選ぶ
		int maxValue = 0;
		int id = 0;
		for (int i = 0; i < w.size(); i++) {
			// １０ｋｇ以下のパターンなら
			if (!(w.get(i) >= 11)) {
				// そのパターンの金額で最大金額を更新していく
				maxValue = Math.max(maxValue, p.get(i));
				id = i;// そのパターンのＩＤを取っておく
			}
		}
		
		System.out.println("一番金額が大きいのはパターン " + id + " で、金額は " + maxValue);
		
	}

	// パッキングの全組み合わせを順列で作成
	public static void pack(int cnt, int amt) {
		// 容量（個数）を超えていなければ再帰して別の商品を詰める
		if (cnt < amt) {
			// 商品ｉを選ぶ
			for (int i = 0; i < ws.length; i++) {
				// 選んだ商品がチェックリストになければ
				if (patCheck(i) == false) {
					pat[cnt] = i;
					// 次の商品を詰める
					pack(cnt + 1, amt);
				}
				// 重複するなら次の商品を選ぶ i++
			}
		} else {// 容量（個数）に達したら 新しい配列を作り、そこに組み合わせをコピーしてそれをpatternに入れる
			int[] pat2 = new int[pat.length];
			System.arraycopy(pat, 0, pat2, 0, pat.length);
			pattern.add(pat2);
		}

	}

	// 一つの組み合わせの中に同じ数字が混じらない様にする為重複チェック
	public static boolean patCheck(int x) {
		for (int i = 0; i < pat.length; i++) {
			// 組み合わせの中にｘと同じ数字（商品）があったら
			if (pat[i] == x) {
				return true;
			}
		}
		return false;
	}

	// 組み合わせの数値をtrue,false形式に変換
	public static boolean[] converter(int[] x) {
		boolean[] y = new boolean[x.length];
		// 検索対象の数字をピックアップ
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x.length; j++) {
				// 数字ｉが配列ｘの中に存在したら
				if (i == x[j]) {
					y[i] = true;// その数字をチェックする
				}
			}
		}
		return y;
	}

}
