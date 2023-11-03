<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
<h1>じゃんけんしようよ</h1>

<form action="start" method="post">
<div class="container">
	<div class="box-title-result">どっちでやる？</div>
		<div class="selectContainer">
                <button type="submit" name="playerCount" value="2" class="btn btn-a02">2人</button>
                <button type="submit" name="playerCount" value="3" class="btn btn-a03">3人</button>
        </div> 
	</div>	
	
</form>	

</body>
</html>