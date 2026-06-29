# API Documentation - AB Commerce Backend

## Base URL

- Desarrollo: `http://localhost:9000/api`
- Producción: `/api`

## Autenticación

### Login

- **Endpoint:** `POST /authenticate`
- **Body:**

```json
{
  "username": "admin",
  "password": "admin",
  "rememberMe": true
}
```
