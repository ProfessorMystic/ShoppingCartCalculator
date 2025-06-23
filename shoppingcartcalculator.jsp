<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping cart Price Calculator</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <header>
        <h1>Shopping Cart Calculator</h1>
    </header>
    <main>
        <div class="calculator-results">
            <fieldset>
                <legend>Shopping Cart</legend>
                <p>You entered:</p>
                <ul>
                    <li>Number of items: <%= request.getAttribute("noItems") %></li>
                    <li>Price of each item: $<%= request.getAttribute("price") %></li>
                    <li>Tax rate: <%= request.getAttribute("tax") %> %</li>
                </ul>
                <p>The total price is calculated as:</p>
                <p>total = noItems * price * (1 + tax / 100)</p>
                <p>The total price is: <strong>$<%= request.getAttribute("total") %></strong></p>
            </fieldset>
        </div>
    </main>
    <footer>
    </footer>
</body>
</html>
