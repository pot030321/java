document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("registerForm").addEventListener("submit", function(event) {
        event.preventDefault();
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;
        const gmail = document.getElementById("gmail").value;

        const registerData = {
            username: username,
            password: password,
            gmail: gmail
        };

        fetch('/api/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(registerData)
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(text) });
                }
                return response.json();
            })
            .then(data => {
                if (data.success) {
                    document.getElementById("registerMessage").innerText = "Registration successful!";
                    // Redirect to the login page after successful registration
                    window.location.href = '/login';
                } else {
                    document.getElementById("registerMessage").innerText = "Registration failed: " + data.message;
                }
            })
            .catch(error => {
                document.getElementById("registerMessage").innerText = "An error occurred: " + error.message;
            });
    });
});
