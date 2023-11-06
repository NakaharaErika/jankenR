<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=M+PLUS+1:wght@700&display=swap" rel="stylesheet">
</head>
<body>
<%
String playerCountStr0 = (String)session.getAttribute("playerCount");
String titleText = null;
titleText = playerCountStr0 + "人対戦の結果は・・・";
%>
<h1><%= titleText %></h1>



<div class="container">
	<div class="box-title-result">${result}</div>
		<br>
		<h3>あなた</h3>
		<img src="./img/${myHand}.png" with="200" height="200">
		
<%
String playerCountStr = (String) session.getAttribute("playerCount");
int playerCount = Integer.parseInt(playerCountStr); // プレイヤー数を整数に変換

// ループを使用してプレイヤーの数だけ画像を表示
for (int i = 1; i < playerCount; i++) { // 1から開始しているのは自分を除くため
    String enemyHand = (String) request.getAttribute("enemyHand" + i);
%>
    <h3>PC<%= i %></h3>
    <img src="./img/<%= enemyHand %>.png" width="200" height="200">
<%
}
%>
</div>

<br>
<br>
<form action="invalidate" method="post">
  <input type="submit" value="トップに戻る" />
</form>

</body>
</html>