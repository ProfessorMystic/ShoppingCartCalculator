# 🛒 Shopping Cart Price Calculator

## 📖 Project Description

The **Shopping Cart Price Calculator** is a Java EE web application designed to calculate the final cost of a user's purchase based on form input. This includes computing subtotal, tax, and shipping fees dynamically, depending on user-specified values such as item quantity, price per item, tax rate, and shipping province.

The project integrates **client-side validation** using JavaScript with **server-side processing** via Java servlets and JSPs to ensure both a responsive user experience and secure backend logic. It demonstrates proper separation of concerns, input validation, dynamic content rendering, and the use of web deployment descriptors (`web.xml`) for default configurations.

This application can serve as a foundation for understanding full-stack web application development in Java EE, including servlet logic, JSP/JSPX templating, and front-end form validation.

---

## ✅ Features

- Input form for:
  - Customer name
  - Number of items
  - Price per item
  - Province
  - Tax rate
- Automatic calculation of:
  - Subtotal
  - Tax amount
  - Shipping cost (free in Ontario, $7.99 otherwise)
  - Final total
- Context-wide default values via `web.xml`
- Front-end validation using JavaScript
- Server-side validation and computation via Java
- Summary output using JSPX

---

## 🧰 Technologies Used

- Java EE (Servlets, JSP, JSPX)
- HTML5, CSS3
- JavaScript (DOM-based form validation)
- Apache Tomcat (or any Java EE-compliant servlet container)

---

## 📦 Folder Structure

