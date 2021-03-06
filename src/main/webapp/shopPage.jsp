<%-- 
    Document   : shop
    Created on : 27-02-2018, 14:07:11
    Author     : Hupra Laptop
--%>

<%@page import="Data.CupCake"%>
<%@page import="java.util.Enumeration"%>
<%@page import="DBConnection.DAO"%>
<%@page import="Data.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Data.CupCakePiece"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute("user");
    ArrayList<CupCake> cart = (ArrayList<CupCake>) session.getAttribute("cart");
    double cartPrice = (double) session.getAttribute("cartPrice");
    ArrayList<CupCakePiece> toppings = (ArrayList) request.getAttribute("toppings");
    ArrayList<CupCakePiece> bottoms = (ArrayList) request.getAttribute("bottoms");
%>

<%@include file="header.jsp" %>
<div class="container">
    <div class="jumbotron">
        <div class="col-12">
            <p class="text-right">Balance: <% out.print(user.getBalance());%>$</p>
        </div>
        <h1 class="display-4">Hello <% out.print(user.getUsername());%>!</h1>
        <p class="lead">Add your own cakes to the cart and they will magically be ready instantly to pickup at our store!</p>
        <hr>
        <div class="input-group mb-3">
            <div class="col-12 col-md-7 mb-3">
                <p>Add cupcakes to your cart!</p>
                <form class="mb-2" name="create_form" method="post">
                    <div class="input-group-prepend mb-3">
                        <label class="input-group-text" for="bottoms">Bottom</label>
                        <select class="custom-select" id="bottoms" name="bottom">
                            <% for (CupCakePiece cp : bottoms)
                                { %>
                            <option value="<%out.print(cp.getId());%>"><%out.print(cp.getFlavor() + " (" + cp.getPrice() + " $)");%></option><%
                                } %>
                        </select>
                    </div>
                    <div class="input-group-prepend mb-3">
                        <label class="input-group-text" for="toppings">Topping</label>
                        <select class="custom-select" id="toppings" name="topping">
                            <% for (CupCakePiece cp : toppings)
                                { %>
                            <option value="<%out.print(cp.getId());%>"><%out.print(cp.getFlavor() + " (" + cp.getPrice() + " $)");%></option><%
                                }%>
                        </select>
                    </div>
                    <div class="input-group-prepend mb-3">
                        <label class="input-group-text" for="toppings">Amount</label>
                        <input class="form-control" type="number" min="1" max="100" name="amount" required>
                    </div>
                    <button type="submit" class="btn btn-primary mb-3" name="action" value="addToOrder" >Add to cart</button>
                </form>
                <form class="mb-2" name="create_form" method="post">
                    <button type="submit" class="btn btn-primary mb-3" name="action" value="checkOut" >Check out</button>
                </form>
            </div>
            <div class="col-12 col-md-5">
                <p>Shopping Cart</p>
                <form class="mb-2" name="update_form" method="post">
                <table class="table table-striped" style="background-color: white;">
                    <thead>
                        <tr>
                            <th scope="col">Bottom</th>
                            <th scope="col">Topping</th>
                            <th scope="col">Amount</th>
                            <th scope="col">Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int i = 0; i < cart.size(); i++)
                            {
                                out.println("<tr>");
                                out.println("<td>" + cart.get(i).getBottom().getFlavor() + "</td>");
                                out.println("<td>" + cart.get(i).getTopping().getFlavor() + "</td>");
                        %>
                    <td style="width:1%;white-space: nowrap">
                        <input type="text" style="width:50px" value="<%out.println(cart.get(i).getAmount());%>" name="<%out.print(i);%>">
                    </td>
                    <%//out.println("<td>" + c.getAmount() + "</td>");
                            out.println("<td>" + cart.get(i).getPrice() + "</td>");
                            out.println("</tr>");
                        }
                    %>
                    <tr>
                        <td>
                            total:
                        </td>
                        <td>
                            
                        </td>
                        <td>
                            
                        </td>
                        <td>
                            <%out.println(cartPrice);%>
                        </td>
                    </tr>
                    </tbody>
                </table>
                    <button type="submit" class="btn btn-primary mb-3" name="action" value="updateCart" >Update cart</button>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>