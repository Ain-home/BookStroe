<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${empty(cart.bookMap)}">
    	空空如也呢！
	</c:if>

    <c:if test="${!empty(cart.bookMap)}">
        <h1>您购物车下有如下的商品：</h1><br>
		<table border="1px">
        	<tr>
            	<td>书名：</td>
            	<td>单价：</td>
            	<td>数量：</td>
            	<td>价钱：</td>
            	<td>操作</td>
        	</tr>
        	<c:forEach items="${cart.bookMap}" var="cartItme">
            	<tr>
                	<td>${cartItme.value.book.name}</td>
                	<td>${cartItme.value.book.price}</td>
                	<td><input type="text" name="quantity" value="${cartItme.value.quantity }" onchange="update(this,${cartItme.key },value)" /></td>
                	<td>${cartItme.value.cost}</td>
                	<td><a href="${pageContext.request.contextPath }/CartServlet?method=delete&book_id=${cartItme.value.book.id }">删除</a></td>
            	</tr>  	
        	</c:forEach>
        	<tr>
                <td><a href="${pageContext.request.contextPath }/CartServlet?method=clear" onclick="return clearCart()">清空购物车</a></td>

                <td colspan="2">合计：</td>
                <td>${cart.totalcost}</td>
                <td><a href="${pageContext.request.contextPath }/OrderServlet?method=create">生成订单</a></td>
            </tr>
		</table>
		
	</c:if>
	
<script type="text/javascript">
  /*
   * @input 将输入框本身填入（这样可以获取得到输入框的值）
   * @id   将书本的id传递进来，告诉服务器是修改哪一个购物项（书）
   * @oldValue 原本的值，如果用户不想修改了，就修改为原本的值（下面会询问用户是否确定修改）
   */
  	function update(input,id,oldValue) {

  		//获取得到输入框的数据
  		var quantity = input.value;
  		
  		//询问用户是否真的修改
  		var flag = window.confirm("你确定修改吗？");

 		//如果确定修改，就跳转到修改的Servlet上
 		if(flag) {
    		window.location.href = "${pageContext.request.contextPath }/CartServlet?method=update&book_id=" + id + "&quantity=" + quantity + "";
  		}else {
    		//如果不确定修改，把输入框的数据改成是原来的
    		input.value = oldValue;
  		}
	}
  
   //清空购物车
   function clearCart(){
	   
	   var flag = window.confirm("您确定要清空购物车吗？");
	   
	   //如果确定
	   if(flag){
		   return true;
	   }else{
		   return false;
	   }
   }
</script>	
	
</body>
</html>