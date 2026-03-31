const plan = localStorage.getItem("plan");
document.getElementById("planText").innerText = "Plan: " + plan;

async function pay(){

    const res = await fetch('/api/payment/create-order', {
        method:'POST'
    });

    const order = await res.json();

    const options = {
        key: "YOUR_KEY_ID",
        amount: order.amount,
        currency: "INR",
        name: "Netflix Clone",
        description: "Premium Subscription",
        order_id: order.id,

        method: {
            upi: true
        },

        handler: function (response){

            fetch('/api/payment/verify', {
                method:'POST',
                headers:{'Content-Type':'application/json'},
                body: JSON.stringify({
                    paymentId: response.razorpay_payment_id,
                    plan: plan
                })
            })
            .then(res => res.text())
            .then(data => {
                alert("Payment Successful");
                window.location.href="/home";
            });
        }
    };

    const rzp = new Razorpay(options);
    rzp.open();
}