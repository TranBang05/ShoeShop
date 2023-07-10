<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add User</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #0f2027, #203a43, #2c5364);
            font-family: 'Poppins', sans-serif;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form1 {
            width: 300px;
            padding: 30px;
            background-color: whitesmoke;
            border-radius: 4px;
            font-size: 12px;
        }

        .form1 h1 {
            color: #0f2027;
            text-align: center;
        }

        .form1 button {
            padding: 10px;
            margin-top: 10px;
            width: 100%;
            color: white;
            background-color: rgb(41, 57, 194);
            border: none;
            border-radius: 4px;
        }

        .input-control {
            display: flex;
            flex-direction: column;
        }

        .input-control label {
            margin-bottom: 5px;
        }

        .input-control input {
            border: 2px solid #f0f0f0;
            border-radius: 4px;
            display: block;
            font-size: 12px;
            padding: 10px;
            width: 100%;
        }

        .input-control input:focus {
            outline: 0;
        }

        .input-control.success input {
            border-color: #09c372;
        }

        .input-control.error input {
            border-color: #ff3860;
        }

        .input-control .error {
            color: #ff3860;
            font-size: 9px;
            height: 13px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="form1">

        <form action="addusers" method="post">
            <h1>Add User</h1>
            <div class="input-control">
                <label for="username">Username</label>
                <input type="text" id="username" name="username">
                <div class="error"></div>
            </div>
            <div class="input-control">
                <label for="email">Email</label>
                <input type="text" id="email" name="email">
                <div class="error"></div>
            </div>
            <div class="input-control">
                <label for="password">Password</label>
                <input type="password" id="password" name="password">
                <div class="error"></div>
            </div>
            <div class="input-control">
                <label for="address">Address</label>
                <input type="text" id="address" name="address">
                <div class="error"></div>
            </div>
            <div class="input-control">
                <label for="phone">Phone</label>
                <input type="text" id="phone" name="phone">
                <div class="error"></div>
            </div>
            <button type="submit">Add User</button>
        </form>



    </div>


</div>

<script>
    const form = document.querySelector('.form');
    const username = document.getElementById('username');
    const email = document.getElementById('email');
    const password = document.getElementById('password');
    const address = document.getElementById('address');
    const phone = document.getElementById('phone');

    form.addEventListener('submit', e => {
        e.preventDefault();
        validateInputs();
    });

    const setError = (element, message) => {
        const inputControl = element.parentElement;
        const errorDisplay = inputControl.querySelector('.error');

        errorDisplay.innerText = message;
        inputControl.classList.add('error');
        inputControl.classList.remove('success');
    };

    const setSuccess = element => {
        const inputControl = element.parentElement;
        const errorDisplay = inputControl.querySelector('.error');

        errorDisplay.innerText = '';
        inputControl.classList.add('success');
        inputControl.classList.remove('error');
    };

    const isValidEmail = email => {
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(email).toLowerCase());
    };

    const validateInputs = () => {
        const usernameValue = username.value.trim();
        const emailValue = email.value.trim();
        const passwordValue = password.value.trim();
        const addressValue = address.value.trim();
        const phoneValue = phone.value.trim();

        if (usernameValue === '') {
            setError(username, 'Username is required');
        } else {
            setSuccess(username);
        }

        if (emailValue === '') {
            setError(email, 'Email is required');
        } else if (!isValidEmail(emailValue)) {
            setError(email, 'Provide a valid email address');
        } else {
            setSuccess(email);
        }

        if (passwordValue === '') {
            setError(password, 'Password is required');
        } else if (passwordValue.length < 8) {
            setError(password, 'Password must be at least 8 characters.');
        } else {
            setSuccess(password);
        }

        if (addressValue === '') {
            setError(address, 'Address is required');
        } else {
            setSuccess(address);
        }

        if (phoneValue === '') {
            setError(phone, 'Phone is required');
        } else {
            setSuccess(phone);
        }
    };
</script>
</body>
</html>