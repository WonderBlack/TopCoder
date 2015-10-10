package chap5;

// お手本のコード

import java.util.*;

public class MazeMaker3 {

	public int LongestPath(String[] maze, int startRow, int startCol,
			int[] moveRow, int[] moveCol) {

		int max = 0;
		int width = maze[0].length();
		int height = maze.length;
		int[][] board = new int[height][width];// 各マスまでの移動回数を保持

		// ボードを未訪問（-1）で初期化
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				board[i][j] = -1;
			}
		}

		board[startRow][startCol] = 0;// 出発点を訪問済みにする

		Queue<Integer> queueX = new LinkedList<Integer>();
		Queue<Integer> queueY = new LinkedList<Integer>();
		queueX.add(startRow);
		queueY.add(startCol);

		while (!queueX.isEmpty()) {

			int x = queueX.poll();// キューから現在地のｘ座標を取り出す
			int y = queueY.poll();// キューから現在地のｙ座標を取り出す
			
			// 全ての移動パターンを使って次のマスを割り出す
			for (int i = 0; i < moveRow.length; i++) {
				int nextx = x + moveCol[i];// パターンiで移動後のｘ座標
				int nexty = y + moveRow[i];// パターンiで移動後のｙ座標
				// 移動後のマスがエリア内で、なおかつ未訪問で、なおかつ障害物がないなら
				if (0 <= nextx && nextx < width && 0 <= nexty && nexty < height
						&& board[nexty][nextx] == -1
						&& maze[nexty].charAt(nextx) == '.') {
					board[nexty][nextx] = board[y][x] + 1;// 移動後のマスに前回のマスの移動回数に１を足した物を記録
					queueX.add(nextx);// ｘ座標のキューに移動後の値を入れる
					queueY.add(nexty);// ｙ座標のキューに移動後の値を入れる

				}
			}
		}
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (maze[i].charAt(j) == '.' && board[i][j] == -1) {
					return -1;
				} else {
					max = Math.max(max, board[i][j]);
				}
			}
		}
		
		return max;

	}

}
