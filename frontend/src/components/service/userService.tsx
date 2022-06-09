import authHeader from './authHeader';
const API_URL = 'http://localhost:8080/';
export function  getPublicContent() {
    return fetch(API_URL + 'all', {
        method: "GET",
        headers: authHeader() });
  }
export function  getUserBoard() {
    return fetch(API_URL + 'user', {
        method: "GET",
        headers: authHeader() });
  }
export function getModeratorBoard() {
    return fetch(API_URL + 'mod', {
        method: "GET",
        headers: authHeader() });
  }
export function getAdminBoard() {
    return fetch(API_URL + 'admin', {
        method: "GET",
        headers: authHeader() });
  }