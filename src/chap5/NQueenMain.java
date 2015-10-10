package chap5;
/**
 * Ｎクイーンのメインプログラム（最初の解のみを表示）
 * 
 * 起動法：java NQueenMain　クイーンの数
 */

public class NQueenMain {
	
	/**
	 * 起動法とメッセージmessageを表示して、プログラムを異常終了させる
	 */
	private static void abort(String message) {
		System.err.println("起動法：java NQueenMain クイーンの数");
		System.err.println("message");
		System.exit(1);// ステータスコード１は異常終了を示す
	}
	
	/**
	 * メインプログラム。Ｎクイーンの最初の解を表示する。
	 * クイーンの個数は、コマンドライン引数で指定する
	 */
	public static void main(String[] args) {
		
		// パラメータの個数は１個でなければならない
		if (args.length != 1) {
			abort("パラメータの個数が違います。");
		}
		
		// パラメータで指定されたクイーンの数を取得してｎにセットする
		int n = 0;
		try {
			n = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			abort("クイーンの数には正の整数を指定してください：" + args[0]);
		}
		if (n <= 0) {
			abort("クイーンの数には正の整数を指定してください：" + args[0]);
		}
		
		// Ｎクイーンを解いて、成功すれば解を表示する
		NQueen nq = new NQueen(n);
		if (nq.tryQueen(0)) {
			// 成功したので解を表示する
			nq.print();
		} else {
			// 失敗したので、その旨のメッセージを表示する
			System.out.println("残念ながら、解は存在しません。");
		}
	}

}
