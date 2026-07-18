import axios from 'axios';
import AuthService from '../services/AuthService';

// Setup request interceptor - attach token to all requests
axios.interceptors.request.use(
  (config) => {
    const token = AuthService.getToken();

    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Setup response interceptor - handle 401 errors
axios.interceptors.response.use(
  (response) => response,
  (error) => {
    // If token is invalid/expired (401), logout user
    if (error.response?.status === 401) {
      AuthService.logout();
      window.location.href = '/login';
    }

    return Promise.reject(error);
  }
);

export default axios;