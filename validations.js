document.addEventListener('DOMContentLoaded', function () {
  const form = document.getElementById('cartForm');

  form.addEventListener('submit', function (event) {
    let isValid = true;

    // Clear previous error messages
    document.querySelectorAll('.error').forEach(e => e.textContent = "");

    // Get values
    const customerName = document.getElementById('customerName').value.trim();
    const noItemsValue = document.getElementById('noItems').value.trim();
    const priceValue = document.getElementById('price').value.trim();
    const province = document.getElementById('province').value.trim();
    const taxValue = document.getElementById('tax').value.trim();

    // Convert values
    const noItems = parseInt(noItemsValue, 10);
    const price = parseFloat(priceValue);
    const tax = parseFloat(taxValue);

    // Validate fields
    if (customerName === "") {
      showError('customerName', "The customer name should not be empty");
      isValid = false;
    }

    if (province === "") {
      showError('province', "The shipping province should not be empty");
      isValid = false;
    }

    if (!/^\d+$/.test(noItemsValue) || noItems <= 0) {
      showError('noItems', "The number of items should be greater than zero");
      isValid = false;
    }

    if (isNaN(price) || price <= 0) {
      showError('price', "The price of each item should be greater than zero");
      isValid = false;
    }

    if (isNaN(tax) || tax <= 0) {
      showError('tax', "The tax rate should be greater than zero");
      isValid = false;
    }

    if (!isValid) {
      event.preventDefault(); // STOP form from submitting to servlet
    }
  });

  function showError(fieldId, message) {
    const input = document.getElementById(fieldId);
    const errorSpan = input.nextElementSibling;
    if (errorSpan && errorSpan.classList.contains("error")) {
      errorSpan.textContent = message;
    }
  }
});
