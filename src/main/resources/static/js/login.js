document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("loginForm").addEventListener("submit", function(event) {
        event.preventDefault();
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        const loginData = {
            username: username,
            password: password
        };

        fetch('/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(text) });
                }
                return response.json();
            })
            .then(data => {
                if (data.success) {
                    window.location.href = data.redirectUrl;
                } else {
                    document.getElementById("loginMessage").innerText = "Login failed: " + data.message;
                }
            })
            .catch(error => {
                document.getElementById("loginMessage").innerText = "An error occurred: " + error.message;
            });
    });
});
