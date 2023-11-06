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
        private String checkResult(int m,int[] e) {
        	String result = null;//結果入力用
        	//じゃんけん判定二人用
        	if(e.length == 1) {
	        	int c;
	        	c = (m - e[0] + 3) % 3;
	        	if (c == 0) {
	        		result = "引き分け";
	        	} else if (c == 2) {
	        		result =  "勝利！";
	        	} else {
	        		result =  "敗北";
	        	}
	        }else if(e.length == 2) {
	        //じゃんけん判定3人用
	        	int sum = m + e[0] + e[1];
	            int remainder = sum % 3;
	
	            if (remainder == 0) {
	                // あいこの場合
	            	result =  "引き分け";
	            } else if (remainder == 1) {
	                // ひとりだけ負ける場合
	                if (e[0] == e[1]) result =  "敗北";
	                else result =  "勝利";
	            } else {
	                // ひとりだけ勝つ場合
	                if (e[0] == e[1]) result =  "勝利";
	                else result =  "敗北";
	            }
            }
			return result;
     }    
}
