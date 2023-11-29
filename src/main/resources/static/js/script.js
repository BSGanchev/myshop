// OPEN CLOSE CART
let cartIcon = document.querySelector('.fa-shopping-cart');
let cart = document.querySelector('#cart');
let closeIcon = document.querySelector('.fa-times');

cartIcon.addEventListener("click", (e) => {
    cart.classList.add("active");
    e.stopPropagation();

});
closeIcon.addEventListener("click", (e) => {
    cart.classList.remove("active");
    e.stopPropagation();
});

// Start only when document is ready

if (document.readyState === "loading") {
    document.addEventListener('DOMContentLoaded', start);
} else {
    start();
}

// start
function start() {
    addEvents();
}

// Update and render

function update() {
    addEvents();
    updateTotal();
}

// Add events

function addEvents() {
    // Remove items
    let cartRemove_btns = document.querySelectorAll('#cart-remove');
    console.log(cartRemove_btns);
    console.log(itemsAdded);
    cartRemove_btns.forEach(btn => {
        btn.addEventListener('click', handle_removeCardItem);
    });

    // change quantity

    let cartQuantity_inputs = document.querySelectorAll('.cart-quantity');
    cartQuantity_inputs.forEach(input => {
        input.addEventListener('change', handle_changeItemQuality);
    })

    // Add to cart
    let addCart_btn = document.querySelectorAll('#add-to-cart');
    addCart_btn.forEach(btn => {
        btn.addEventListener('click', handle_addCartItem);
    })
}

// Buy order

const buy_btn = document.querySelector('.btn-buy');
buy_btn.addEventListener('click', handle_BuyOrder);

//--------------------------handlers
// buy Order fetch
function handle_BuyOrder() {
    if (itemsAdded.length <= 0) {
        alert("The cart is empty!")
        return;
    }
    const cartContent = cart.querySelector('#cart-content');
    cartContent.innerHTML = '';

    const requestData = {
        items: itemsAdded,
    }
    fetch('http://localhost:8080/cart/buyOut', {
        method: 'POST', headers: {
            'Content-Type': 'application/json',
        }, body: JSON.stringify(requestData),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to place order');
            }
            return response.json();
        })
        .then(alert('order placed successfully'));

    itemsAdded = [];
    update();
}


function handle_removeCardItem() {
    this.parentElement.remove();
    console.log('reomve');
    console.log(this.parentElement.querySelector(".cart-product-title").innerHTML);

    itemsAdded = itemsAdded.filter((el) =>
        el.productName !== this.parentElement.querySelector(".cart-product-title").innerHTML);

    update();
}

function handle_changeItemQuality() {
    if (isNaN(this.value) || this.value < 1) {
        this.value = 1;
    }
    this.value = Math.floor(this.value);
    update();
}

let itemsAdded = [];

function handle_addCartItem() {

    let productId = document.getElementById('add-to-cart').getAttribute('value');
    const apiUrl = `http://localhost:8080/api/products/${productId}`;

    fetch(apiUrl)
        .then(response => {
            return response.json();
        })
        .then(data => {
            if (itemsAdded.find(el => el.id === data.id)) {
                alert('Already exist in cart');
                return;
            }
            itemsAdded.push(data);
            let cartBoxElement = CartBoxComponent(data.productName, data.price, data.pictureUrl);
            let newNode = document.createElement('div');
            newNode.classList.add('cart-box');
            newNode.id = 'cart-box';
            newNode.innerHTML = cartBoxElement;
            const cartContent = cart.querySelector('.cart-content');
            cartContent.appendChild(newNode);
            update();

        })
        .catch(error => {
            console.error('Error fetching product details:', error);
        });
    update();

}

function updateTotal() {
    let cartBoxes = document.querySelectorAll('#cart-box');
    console.log(cartBoxes);
    const totalElement = cart.querySelector('#total-price');
    let total = 0;
    cartBoxes.forEach(cartBox => {
        let priceElement = cartBox.querySelector('#cart-price');
        let price = parseFloat(priceElement.innerHTML);
        let quantity = cartBox.querySelector('#cart-quantity').value;

        total += price * quantity;

        totalElement.innerHTML = total.toFixed(2);
    })
}

// HTML components

function CartBoxComponent(title, price, pictureUrl) {
    return `
            
                <img src=${pictureUrl} alt="" class="cart-img">
                <div class="detail-box">
                    <div class="cart-product-title">${title}</div>
                    <div class="cart-price" id="cart-price">${price}</div>
                    <input type="number" value="1" min="0" class="cart-quantity" id="cart-quantity">
                    </div>
                <i class='fa fa-trash' id="cart-remove"></i>
            `
}