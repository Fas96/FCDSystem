import {postUserDetailsForAuthentication} from "@/api/Login.api";
import {getContext, storeDataInSession} from "@/util/commonUtils";

export  class LoginPage{

    static initLogin() {
        let loginButton = document.querySelector('.login__submit');
        loginButton.addEventListener('click', async (event) => {
            event.preventDefault();

            let loginForm = document.getElementById('loginPageForm') as HTMLFormElement;
            let formData = new FormData(loginForm);

            let data = {userId: formData.get('userId'), password: formData.get('password')}

              postUserDetailsForAuthentication(data).then((response) => {

                if (response.data) {
                    window.location.href = getContext() + '/home';
                    storeDataInSession(response.data);
                } else {
                    alert('Invalid User Id or Password');
                }
            }
            ).catch((error) => {
                alert('Invalid User Id or Password');
               return;
            });

        });

    }


}