# 🧾 Registro de Usuarios - Spring Boot + Spring Security

Sistema simple de autenticación que permite registrar nuevos usuarios, iniciar sesión y proteger rutas privadas.

## ✅ Funcionalidades
- Registro de usuarios (con validación)
- Inicio de sesión con Spring Security
- Encriptación de contraseñas con BCrypt
- Rutas públicas y privadas
- Personalización de login
- DTOs para evitar exposición de entidades

## 🔐 Seguridad
- Filtro de seguridad (`SecurityFilterChain`)
- Encriptación de contraseñas (`BCryptPasswordEncoder`)
- Control de acceso a rutas:
  - Público: `/registro`, `/css/**`, `/js/**`
  - Protegido: cualquier otra ruta (`authenticated()`)

## 🚀 Tecnologías utilizadas
- Java 17+
- Spring Boot
- Spring Security
- Thymeleaf
- JPA / Hibernate
- Bootstrap

## 📂 Estructura de paquetes
- `controller`
- `dto`
- `entity`
- `repository`
- `service`
- `security`

## 🛠️ Para ejecutarlo
1. Clona el proyecto
2. Configura tu base de datos en `application.properties`
3. Ejecuta la clase principal
4. Accede desde el navegador en `http://localhost:8080`

## 👨‍💻 Autor
**Cristian Huarcaya Pumahualcca**  
Desarrollador Backend en Java  
[LinkedIn](https://www.linkedin.com/in/christian-huarcaya-pumahualcca) | [GitHub](https://github.com/ChristianHuarcaya)





