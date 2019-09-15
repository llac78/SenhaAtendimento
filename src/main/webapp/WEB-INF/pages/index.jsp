<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Pegue Sua Senha</title>
	
	<!-- Bootstrap -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

	<!-- Date & Time -->
	<script src="https://code.jquery.com/jquery-1.11.2.js"></script>
	<script type="text/javascript">
		jQuery(window).load(function($){
			updateDateTime();
		});
	</script>
	<script>
		function updateDateTime(){ 
			var currentTime = new Date();
			
			var value_hour   = currentTime.getHours();
			var value_minute = currentTime.getMinutes();
			var value_second = currentTime.getSeconds();
			
			var value_day   = currentTime.getDate();
			var value_month = currentTime.getMonth() + 1;
			var value_year  = currentTime.getFullYear();
			
			if (value_day < 10){ value_day = "0" + value_day;}
			if (value_month < 10){ value_month = "0" + value_month;}
			if (value_hour < 10){ value_hour = "0" + value_hour;}
			if (value_minute < 10){ value_minute = "0" + value_minute;}
			if (value_second < 10){ value_second = "0" + value_second;}
 
			dataFormat = value_day + " / " + value_month + " / " + value_year;
			horaFormat = value_hour + " : " + value_minute + " : " + value_second;
 
			document.getElementById("date").innerHTML = dataFormat;
			document.getElementById("time").innerHTML = horaFormat;
 
			setTimeout("updateDateTime()",1000);
		}
	</script>	
</head>

<body class="p-3 mb-2 bg-light text-dark">
	<div align="left" style="margin: 10px;">
		<output id="date" style="font-family: 'arial black', 'avant garde'; font-size: 18px;"></output>
		<output id="time" style="font-family: 'arial black', 'avant garde'; font-size: 18px;"></output>				
	</div>
	
	<h1 align="center" style="margimargin-bottom: 50px;" class="display-4">*** PEGUE AQUI A SUA SENHA ***</h1>
	
	<s:form action="${pageContext.request.contextPath }/salvar" method="post" modelAttribute="form_senha">
		<input type="hidden" id="dataGeracao" />
		
		<table align="center">
			<tr>
				<td><button type="submit" class="btn btn-success btn-lg btn-block">GERAR SENHA</button></td>
			</tr>
		</table>
	</s:form>
	
	<div align="center" style="margin: 40px;">
		<table>
			<tr>
				<td  align="center">
					<c:if test="${ultimaSenha != null}">
						<h4>Última Senha</h4>
						<h1 class="text-danger"><c:out value="${ultimaSenha}" /></h1>
					</c:if>
				</td>
			</tr>
		
		</table>
	</div>

</body>
</html>