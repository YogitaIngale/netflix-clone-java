const loginForm = document.getElementById('adminLoginForm');

loginForm.addEventListener('submit', function(e) {
    e.preventDefault();

    const email = loginForm.email.value.trim();
    const password = loginForm.password.value.trim();

    fetch('/admin/api/login', {
        method: 'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify({ email, password })
    })
    .then(res => {
        if(res.ok) return res.json();
        else if(res.status === 401) throw new Error("Invalid admin credentials");
        else throw new Error("Server error");
    })
    .then(admin => {
        if(admin.role && admin.role.toUpperCase() === "ADMIN"){
            window.location.href = "/admin/dashboard";
        } else {
            alert("You are not an admin");
        }
    })
    .catch(err => alert(err.message));
});