package chap5;

import java.util.Arrays;

public class NQueen {

	private enum Status {
		FREE, // 利き筋になっていない（置ける）
		NOT_FREE// 利き筋になっている（置けない｝
	}

	private final int N; // クイーンの数
	private int[] pos; // 各行に置かれたクイーンの位置
	private Status[] col; // クイーンが垂直方向に利いているかを示す配列
	private Status[] down; // クイーンが右斜め下向きに利いているかを示す配列
	private Status[] up; // クイーンが右斜め上向きに利いているかを示す配列

	/**
	 * Ｎクイーンの問題を解くためのオブジェクトを生成する
	 * 
	 * @param numberOfQueens
	 *            　クイーンの個数
	 */
	public NQueen(int numberOfQueens) {
		// 配列を割り当てる
		N = numberOfQueens;
		pos = new int[N];
		col = new Status[N];
		down = new Status[2 * N - 1];
		up = new Status[2 * N - 1];

		// クイーンの位置と利き筋を初期化する
		Arrays.fill(pos, -1);
		Arrays.fill(col, Status.FREE);
		Arrays.fill(down, Status.FREE);
		Arrays.fill(up, Status.FREE);
	}

	/**
	 * 行ａ以降の全ての行にクイーンを置く
	 * 
	 * @param a
	 *            この行以降にクイーンを置く
	 * @return クイーンが置けたらture,置けなかったらfalseを返す
	 */
	public boolean tryQueen(int a) {

		// 左から右に向かって順番にクイーンが置けるかどうかを調べる
		for (int b = 0; b < N; b++) {

			// 行ａのｂ番目に置けるかどうかを調べる
			if (col[b] == Status.FREE && up[a + b] == Status.FREE && down[a - b + (N - 1)] == Status.FREE) {

				// 置くことが出来た。場所を記録して、利き筋をセットする
				pos[a] = b;
				col[b] = Status.NOT_FREE;
				up[a + b] = Status.NOT_FREE;
				down[a - b + (N - 1)] = Status.NOT_FREE;

				// Ｎ個のクイーンをすべて置くことが出来れば成功である（最終ステップの停止条件）
				if (a + 1 >= N) {
					return true;
				} else {
					// 行a+1以降のすべての行に置けたら成功である（自ステップの停止条件）
					if (tryQueen(a + 1)) {
						return true;
					} else {
						// 失敗した。クイーンを取り除く。
						pos[a] = -1;
						col[b] = Status.FREE;
						up[a + b] = Status.FREE;
						down[a - b + (N - 1)] = Status.FREE;
					}
				}
			}
		}
		// 結局この行には置ける場所がなかった
		return false;
	}

	/**
	 * クイーンの位置を出力する
	 */
	public void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pos[i] == j) {
					System.out.print("Q ");
				} else {
					System.out.print(". ");
				}
			}
			System.out.println("");
		}
		System.out.println();
	}

}
