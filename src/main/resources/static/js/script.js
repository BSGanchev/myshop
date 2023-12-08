// OPEN CLOSE CART
let cartIcon = document.querySelector('.fa-shopping-cart');
let cart = document.querySelector('#cart');
let closeIcon = document.querySelector('.fa-times');
let itemsAdded = [];
let id;


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
    handleCart();
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

    cartRemove_btns.forEach(btn => {
        btn.addEventListener('click', handle_removeCardItem);
    });

    // change quantity

    let cartQuantity_inputs = document.querySelectorAll('.cart-quantity');
    cartQuantity_inputs.forEach(input => {
        input.addEventListener('change', handle_changeItemQuality);
    })

    // Add to cart
    let addCart_btn = document.querySelectorAll('.cart-btn');
    addCart_btn.forEach(btn => {
        btn.addEventListener('click', handle_addCartItem);
    })
}
function handleCart(){
    loadCart();
    renderCart();
    updateTotal();
}
function renderCart(){
        itemsAdded.forEach(item => {
            let boxElement = CartBoxComponent(item.productName, item.price, item.pictureUrl);
            let newNode = document.createElement('div');
            newNode.classList.add('cart-box');
            newNode.id = 'cart-box';
            newNode.innerHTML = boxElement;
            const cartContent = cart.querySelector('.cart-content');
            cartContent.appendChild(newNode);
        })
}
function saveCart() {
    localStorage.setItem("cart", JSON.stringify(itemsAdded));
}

function loadCart(){
    let cartJSON = localStorage.getItem("cart");
    itemsAdded = cartJSON ? JSON.parse(cartJSON) : [];

}


// Buy order

const buy_btn = document.querySelector('.btn-buy');
buy_btn.addEventListener('click', handle_BuyOrder);



function handle_BuyOrder() {
    if (itemsAdded.length <= 0) {
        alert("The cart is empty!")
        return;
    }


    const requestData = {
        items: itemsAdded

    }
    fetch('http://localhost:8080/orders/buy', {
        method: 'POST',
        mode: "cors",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        body: JSON.stringify(requestData),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to place order');
            }
            return response.json();
        }).catch(err => {
        console.log(err)
    });

    handle_removeAll();
    itemsAdded = [];
    let total = cart.querySelector('.total-price');
    total.innerHTML = 0.00.toFixed(2);
    localStorage.clear();
    update();
}

function handle_removeCardItem() {
    this.parentElement.remove();
    itemsAdded = itemsAdded.filter((el) =>
        el.productName !== this.parentElement.querySelector(".cart-product-title")
            .innerHTML);
    saveCart();
    update();
    handleCart();

}

function handle_changeItemQuality() {
    if (isNaN(this.value) || this.value < 1) {
        this.value = 1;
    }
    this.value = Math.floor(this.value);
    update();
    saveCart();
}

function handle_removeAll() {
    let cartBoxes = document.querySelectorAll('.cart-box');
    let total = document.querySelectorAll('.total-price');
    cartBoxes.forEach(box => {
        box.remove();
        total.innerHTML = 0.00.toFixed(2);
    });
    itemsAdded = [];
    localStorage.setItem('cart', JSON.stringify(itemsAdded));

}


function getAddBtnId() {
    return new Promise(function (resolve) {
        document.addEventListener('click', function (event) {
            if (event.target.classList.contains('cart-btn')) {
                const productId = event.target.getAttribute('value');

                resolve(productId);
                event.stopPropagation();

            }
        });
    });
}

function handle_addCartItem() {
    getAddBtnId().then(function (productId) {

        const apiUrl = `http://localhost:8080/products/${productId}`;

        fetch(apiUrl)
            .then(response => {
                return response.json();
            })
            .then(data => {
                if (itemsAdded.find(el => el.id === data.id)) {
                    alert('Already exist in cart');
                    return;
                }

                let cartBoxElement = CartBoxComponent(data.productName, data.price, data.picture);
                let newNode = document.createElement('div');
                newNode.classList.add('cart-box');
                newNode.id = 'cart-box';
                newNode.innerHTML = cartBoxElement;
                const cartContent = cart.querySelector('.cart-content');
                cartContent.appendChild(newNode);
                itemsAdded.push(data);
                update();

            })
            .catch(error => {
                console.error('Error fetching product details:', error);
            });
        cart.classList.add("active");

        update();
        console.log(localStorage.length)

    });

}

function updateTotal() {
    let cartBoxes = document.querySelectorAll('.cart-box');
    const totalElement = cart.querySelector('.total-price');
    let total = 0;
    if (cartBoxes.length === 0) {
        total = 0;
        totalElement.innerHTML = total.toFixed(2);
        return;
    }

    cartBoxes.forEach(cartBox => {
        let priceElement = cartBox.querySelector('.cart-price');
        let price = parseFloat(priceElement.innerHTML);
        // let quantity = cartBox.querySelector('.cart-quantity').value;

        total += price;

        totalElement.innerHTML = total.toFixed(2);
    })
}

// HTML components

function CartBoxComponent(title, price, picture) {
    return `
                <img src= "${'data:' + picture.contentType +';base64,' + picture.encodedContent}" alt="" class="cart-img">
                <div class="detail-box">
                    <div class="cart-product-title">${title}</div>
                    <div class="cart-price" id="cart-price">${price}</div>
                    <input hidden="hidden" type="number" value="1" min="0" class="cart-quantity" id="cart-quantity">
                    </div>
                <i class='fa fa-trash' id="cart-remove"></i>
            `
}

// product page picture change
let bigImg = document.querySelector('.image img');
let smallImage = document.querySelectorAll('.small-images img');

smallImage.forEach(img => {
    img.addEventListener('click', showImage);
});

function showImage() {
    bigImg.src = this.src;
}
