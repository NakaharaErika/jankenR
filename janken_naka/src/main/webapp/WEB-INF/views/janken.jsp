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
<h1>じゃんけんしようよ</h1>

<form action="janken" method="post">

<div class="container">
	<%
    String playerCount = (String)session.getAttribute("playerCount");
    String titleText = null;
    titleText = "どれかをクリック（" + playerCount + "人）";
	%>
	<div class="box-title"><%= titleText %></div>
		<div>
		  <input id="rock" type="radio" value="0" name="rdo">
		  <label for="rock"><img src="./img/0.png" with="150" height="150"></label>
		  <input id="seaser" type="radio" value="1" name="rdo">
		  <label for="seaser"><img src="./img/1.png" with="150" height="150"></label>
		  <input id="paper" type="radio" value="2" name="rdo">
		  <label for="paper"><img src="./img/2.png" with="150" height="150"></label>
		</div>	
</div>	
	
	<input type="submit" value="勝負じゃあ！">
</form>	

</body>
</html>