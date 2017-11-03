<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
        .box{
            width: 175px;
            height: 200px;
            background-color:#7583A9;
            border: 1px solid black;
            margin: 5px;
            text-align: center;
        }
        .row1 {
            width: 100%;
            margin-right: auto;
            margin-left: auto;
            display: inline-flex;
        }
        .col-w-3 {
            width: 25%;
        }
        .content{
            height:200px;
            width:100%;
            border:1px solid #747474;
            overflow:auto;
        }
        .red{
            color: red;
        }
        .green{
            color: green;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Ninja Gold</h1>
        <h3>Your Gold: ${gold}</h3>

        <div class="row1">
            <div class="col-w-3 box">
                <h4>Farm</h4>
                <p>(earns 10-20 golds)</p>
                <form action="/process_money" method="post">
                    <input type="hidden" name="building" value="farm" />
                    <input type="submit" value="Find Gold!" />
                </form>
            </div>
            <div class="col-w-3 box">
                <h4>Cave</h4>
                <p>(earns 10-20 golds)</p>
                <form action="/process_money" method="post">
                    <input type="hidden" name="building" value="cave" />
                    <input type="submit" value="Find Gold!" />
                </form>
            </div>
            <div class="col-w-3 box">
                <h4>House</h4>
                <p>(earns 10-20 golds)</p>
                <form action="/process_money" method="post">
                    <input type="hidden" name="building" value="house" />
                    <input type="submit" value="Find Gold!" />
                </form>
            </div>
            <div class="col-w-3 box">
                <h4>Casino</h4>
                <p>(earns 10-20 golds)</p>
                <form action="/process_money" method="post">
                    <input type="hidden" name="building" value="casino" />
                    <input type="submit" value="Find Gold!" />
                </form>

            </div>
        </div>
        <h4>Activities:</h4>
        <div class="content">
	    		<c:forEach var="current" items="${activity}" > 
	    			<c:choose>
	    				<c:when test = "${current.contains('lost')}">
	    					<p style="color: red"><c:out value="${current}"/><p>
	    				</c:when>
	    				<c:otherwise>
	    					<p style="color: green"><c:out value="${current}"/><p> 
	    				</c:otherwise>
	    			</c:choose>   
			</c:forEach>         
        <p class="green"></p>
        </div>
        <form action="/reset">
            <button type="submit" class="btn btn-default">Reset</button>
        </form>
    </div>
</body>
</html>