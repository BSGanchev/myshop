document.addEventListener('DOMContentLoaded', function () {


    fetch('/api/auth/status')
        .then(response => response.text())
        .then(authStatus => {
            let loginLogoutIcon = document.getElementById('login-logout-icon');
            loginLogoutIcon.addEventListener('click', function () {
                if (authStatus === 'authenticated') {
                    let form = document.createElement('form');


                    form.method = 'POST';
                    form.action = '/users/logout';


                    let button = document.createElement('button');
                    button.type = 'submit';
                    button.textContent = 'Logout';

                    form.appendChild(button);
                    document.body.appendChild(form);

                    form.submit();
                } else {
                    window.location.href = '/users/login';
                }
            })
        });
});