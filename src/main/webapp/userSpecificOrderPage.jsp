<%-- 
    Document   : userOrdersPage
    Created on : 01-03-2018, 11:20:51
    Author     : Hupra Laptop
--%>

<%@page import="Data.OrderLine"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="Data.User"%>

<%@include file="header.jsp" %>


<%

 ArrayList<OrderLine> items = (ArrayList<OrderLine>)session.getAttribute("orderDetails");

%>


<div class="container">

    <div class="jumbotron">


        <h1 class="display-4">Hello Daniel!</h1>

        <h3>Balance: 45$</h3>

        <hr>

        <p>Your Ordernumber: <% out.print(items.get(0).getOrderId()); %></p>
        <table class="table table-striped" style="background-color: white;">
            <thead>
                <tr >
                <th scope="col">Amount</th>
                <th scope="col">Bottom</th>
                <th scope="col">Topping</th>
                <th scope="col">Price</th>
                </tr>
            </thead>
            <tbody>
                
                
                <%
                
                double price = 0;
                for(OrderLine o : items){
                    out.println("<tr>");
                    out.println("<td>" + o.getCupCake().getAmount() + "</td>");
                    out.println("<td>" + o.getCupCake().getBottom().getFlavor() + "</td>");
                    out.println("<td>" + o.getCupCake().getTopping().getFlavor()+ "</td>");
                    out.println("<td>" + o.getCupCake().getPrice()*o.getCupCake().getAmount() + "</td>");
                    out.println("</tr>");
                    
                    price += (o.getCupCake().getPrice()*o.getCupCake().getAmount());
                    
                }
                

                %>
                
            </tbody>
        </table>
                
        <% out.println("<h4>Total price: " + price + "</h4>"); %>

        <a href="users" class="btn btn-primary mb-3 mt-3">Go Back</a>
    </div>
</div>

<%@include file="footer.jsp" %>