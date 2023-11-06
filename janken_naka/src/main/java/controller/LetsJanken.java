package controller;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/janken")
public class LetsJanken extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームから送信されたデータを取得
        String myHandSri = request.getParameter("rdo");
        HttpSession session = request.getSession();
        String playerCount = (String) session.getAttribute("playerCount");

        
        // 手を選ばなかった場合、元のページにリダイレクトさせる
        if (myHandSri == null) {
        	String view = "/WEB-INF/views/janken.jsp";
            request.getRequestDispatcher(view).forward(request, response);
        } else {
        	
	        int myHand = Integer.parseInt(myHandSri);//自分の手をint型に変形
	        int playerCounts = Integer.parseInt(playerCount);//対戦人数をint型に変形
	        String result = null;//結果入力用
	        
	        //敵の手を人数に応じてランダムに生成する
	        int[] enemyHands = makeenmyHands(playerCounts-1);
	        //自分の手と敵の手を渡して、checkResult関数を起動
	        result = checkResult(myHand,enemyHands);
		        
		    //自分の手をstring型に直してセット
	 		String myHandStr = Integer.toString(myHand);
	        request.setAttribute("myHand", myHandStr);
	        
	        // 敵の手をString型に変換してセット
	        String[] enemyHandStrs = new String[enemyHands.length]; // 文字列の配列を初期化
	        for (int i = 0; i < enemyHands.length; i++) {
	            enemyHandStrs[i] = Integer.toString(enemyHands[i]); // 整数を文字列に変換
	            request.setAttribute("enemyHand" + (i + 1), enemyHandStrs[i]); // 文字列を属性として保存
	        }

	        
	        // 勝敗結果をセット
	        request.setAttribute("result", result);
	       
	        // confirm.jsp にリダイレクト
	        String view = "/WEB-INF/views/result.jsp";
	        request.getRequestDispatcher(view).forward(request, response);
        }//if分岐終わり
     }//doPost終わり
        
    
    	//敵の手をランダム生成
        private int[] makeenmyHands(int numberEnemmys){
        	Random rand = new Random();
        	int[] hands = new int[numberEnemmys];//敵の手を入れる箱を作成
        	for (int i = 0; i < numberEnemmys; i++) {
				hands[i] = rand.nextInt(3);
			}
        	return hands;
        }
        
        
        //じゃんけん判定
        private String checkResult(int playerHand, int[] enemyHands) {
        	int[] HAND_FLAGS = {1, 2, 4}; // グー:1, チョキ:2, パー:4
            int handsFlag = HAND_FLAGS[playerHand]; // プレイヤーの手のビットフラグ

            for (int i = 0; i < enemyHands.length; i++) {
                handsFlag |= HAND_FLAGS[enemyHands[i]]; // 敵の手のビットフラグを追加
            }

            // 結果の判定
            if (handsFlag == 1 || handsFlag == 2 || handsFlag == 4 || handsFlag == 7) {
                return "引き分け";
            } else if (handsFlag == 3) {
                if (playerHand == 0) {
                    return "勝利！";
                } else {
                    return "敗北";
                }
            } else if (handsFlag == 5) {
                if (playerHand == 2) {
                    return "勝利！";
                } else {
                    return "敗北";
                }
            } else if (handsFlag == 6) {
                if (playerHand == 1) {
                    return "勝利！";
                } else {
                    return "敗北";
                }
            } else {
                return "不明な結果"; // このケースが発生することはない
            }
        }

}
