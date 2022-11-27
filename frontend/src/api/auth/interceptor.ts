

function errorResponseHandler(error) {
    if (error) {
        if (error.response.status === 401) {
            location.href = '/login';
        } else {
            location.href = '/error';
        }
    } else {
        location.href = '/error';
    }
}

export function setInterceptors(instance) {
    // Add a request interceptor
    instance.interceptors.request.use(
        config => {
            const token = localStorage.getItem('token');
            return config;
        },
        error => {
            console.log(error);
            return Promise.reject(error.response);
        },
    );

    instance.interceptors.response.use(
        response => {
            if (response.headers.authorization) {
                localStorage.setItem('token', response.headers.authorization);
                console.log('set authorization');
            }
            return response;
        },
        error => {
            console.log(error);
            errorResponseHandler(error);
        });

    return instance;
}