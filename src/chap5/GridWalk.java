package chap5;

import java.util.Arrays;
import java.util.Stack;

public class GridWalk {

	static boolean[][] grid = new boolean[4][4];
	static int vx[] = { 1, -1, 0, 0 };
	static int vy[] = { 0, 0, 1, -1 };
	static int gx = grid.length - 1;
	static int gy = grid[0].length - 1;
	static int min = 50;
	static int max = 0;
	static int[] dists = new int[10000];
	static int discnt = 0;
	static int dist = 0;
	static Stack stack = new Stack();
	static boolean flg = false;

	public static void main(String[] args) {

		walk(2, 3);

		System.out.println("最長距離は" + max);
		System.out.println("最短距離は" + min);
		System.out.println(Arrays.toString(dists));
		int x = 0;
		for (int i = 0; i < dists.length; i++) {
			if (dists[i] != 0) {
				x++;
			}
		}
		System.out.println("ゴールまでのルートは "+ x + " 通り");
	}

	public static void walk(int x, int y) {
		
		grid[x][y] = true;// 現在地をtrueにする


		String xx = String.valueOf(x);
		String yy = String.valueOf(y);
		String disdis = String.valueOf(dist);
		String xxyy = "[" + xx + "]" + "[" + yy + "]" + "dist = " + disdis;
		stack.push(xxyy);// スタックに入れる
		
		System.out.println("現在地はgrid[" + x + "][" + y + "]");
		System.out.println("ここまでの移動距離は" + dist);

		if (x == gx && y == gy) {// ゴールに着いたら

			min = Math.min(min, dist);
			max = Math.max(max, dist);
			System.out.println("ゴールしました。");
			System.out.println("ここまでの移動距離は" + dist);
			
			
			grid[x][y] = false;// 現在地をfalseにする
			String w = (String) stack.pop();
			System.out.println(w + "をポップ");
			
			dists[discnt] = dist;
			discnt++;
			dist--;// 移動距離も一つ前のステップに戻す
			System.out.println("距離を戻します　dist = " + dist);

			return;
		}

		for (int i = 0; i < vx.length; i++) {// 全方向を試し終わるまで繰り返す（東西南北の順）
			int a = x + vx[i];
			int b = y + vy[i];
			if (0 <= a && a < grid.length && 0 <= b && b < grid[0].length) {// エリア内であれば
				if (grid[a][b] == false) {// その場所が空いていれば：埋まっていれば方向を変える（i++）
					switch (i) {
					case 0:
						System.out.println("東に進む");
						break;
					case 1:
						System.out.println("西に進む");
						break;
					case 2:
						System.out.println("南に進む");
						break;
					case 3:
						System.out.println("北に進む");
						break;
					}
					dist++;// 移動距離を一歩足す
					walk(a, b);
				}
			}
		}
		// 全方向埋まっていてこれ以上進めなければ
		grid[x][y] = false;// 現在地をfalseにする
		String w = (String) stack.pop();
		System.out.println(w + "をポップ");
		dist--;// 移動距離も一つ前のステップに戻す
		System.out.println("距離を戻します　dist = " + dist);

	}
}
