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
        	
	        
	        int myHand = Integer.parseInt(myHandSri);//int型に変形
	        int enemyHand1 = makeenmyHand();//敵の手1を設定
	        int enemyHand2 = makeenmyHand();//敵の手2を設定
	        
	        String result = null;//結果入力用
	        
	        
		        if(playerCount.equals("2")) {
				       //じゃんけん判定２人用の結果を取得
				        result = checkResult2(myHand,enemyHand1);
		        
		        } else if(playerCount.equals("3")) {
			        	//じゃんけん判定3人用の結果を取得
				        result = checkResult3(myHand,enemyHand1,enemyHand2);
				        
				        String enemyHandStr2 = String.valueOf(enemyHand2);
				        request.setAttribute("enemyHand2", enemyHandStr2);
		        }
		        
		    //じゃんけんの手をstring型に直してセット
	 		String myHandStr = String.valueOf(myHand);
	        request.setAttribute("myHand", myHandStr);
	        String enemyHandStr1 = String.valueOf(enemyHand1);
	        request.setAttribute("enemyHand1", enemyHandStr1);
	        
	        // 勝敗結果をセット
	        request.setAttribute("result", result);
	       
	        // confirm.jsp にリダイレクト
	        String view = "/WEB-INF/views/result.jsp";
	        request.getRequestDispatcher(view).forward(request, response);
        }//if分岐終わり
     }//doPost終わり
        
    
    	//敵の手をランダム生成
        private int makeenmyHand(){
        	Random rand = new Random();
        	return rand.nextInt(3);
        }
        
        
        //じゃんけん判定
        private String checkResult2(int m,int e) {
        	int c;
        	c = (m - e + 3) % 3;
        	if (c == 0) {
        		return "引き分け";
        	} else if (c == 2) {
        		return "勝利！";
        	} else {
        		return "敗北";
        	}
        }
        
      //じゃんけん判定3人用
        private String checkResult3(int m1,int e1, int e2) {
        	int sum = m1 + e1 + e2;
            int remainder = sum % 3;

            if (remainder == 0) {
                // あいこの場合
                return "引き分け";
            } else if (remainder == 1) {
                // ひとりだけ負ける場合
                if (e1 == e2) return "敗北";
                else return "勝利";
            } else {
                // ひとりだけ勝つ場合
                if (e1 == e2) return "勝利";
                else return "敗北";
            }
        
        }
        
}
