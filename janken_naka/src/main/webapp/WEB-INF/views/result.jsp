<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
<h1>結果は・・・・</h1>

<div class="container">
	<div class="box-title-result">${result}</div>
		<br>
		<h3>あなた</h3>
		<img src="./img/${myHand}.png" with="200" height="200">
		
		<h3>PC</h3>
		<img src="./img/${enemyHand1}.png" with="200" height="200">
<!-- 3人対戦の時のみ、敵2の結果を表示（sessionから呼び出す -->
<% 
String playerCount = (String) session.getAttribute("playerCount");
if (playerCount.equals("3")){ %>
		<h3>PC2</h3>
		<img src="./img/${enemyHand2}.png" with="200" height="200">
<%} %>
</div>

<br>
<br>
<p class="textlink textlink01"><a href="start">トップに戻る</a></p>


</body>
</html>