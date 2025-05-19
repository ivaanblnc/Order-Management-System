# Order Management System

Sistema backend desarrollado en Java con Spring Boot para gestionar usuarios, roles, productos y órdenes. Utiliza autenticación y autorización basada en JWT para proteger los endpoints según roles (ADMIN, CUSTOMER).

---

## Tecnologías

- Java 17  
- Spring Boot 3.x  
- Spring Security con JWT  
- Hibernate / JPA  
- Base de datos relacional (MySQL o PostgreSQL) ejecutándose en Docker  
- Maven  
- Lombok  
- Postman para pruebas  

---

## Funcionalidades principales

- Registro y autenticación de usuarios con JWT  
- Gestión de roles (ADMIN y CUSTOMER)  
- CRUD de productos (solo ADMIN)  
- Gestión de órdenes (usuarios pueden crear, ADMIN puede administrar todas)  
- Control de acceso y seguridad granular con roles  
- Swagger para documentación de API (opcional)  

---

## Configuración y ejecución

### 1. Configurar la base de datos con Docker

Ejecuta el contenedor de la base de datos (ejemplo con MySQL):

```bash
docker run --name order_db -e MYSQL_ROOT_PASSWORD=tu_password -e MYSQL_DATABASE=order_db -p 3306:3306 -d mysql:latest
docker run --name order_db -e POSTGRES_PASSWORD=tu_password -e POSTGRES_DB=order_db -p 5432:5432 -d postgres:latest

### 2. Configura las propiedades de conexión a la base de datos en application.properties o application.yml:

spring.datasource.url=jdbc:mysql://localhost:3306/order_db
spring.datasource.username=root
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update

### 3. Ejecuta la aplicación
mvn spring-boot:run
La API estará disponible en http://localhost:8080

Endpoints principales
/auth/** - Registro, login y obtención de token JWT (público)

/products/** - CRUD de productos (GET público, POST/PUT/DELETE solo ADMIN)

/orders/** - Creación y consulta de órdenes (acceso según rol)

/user/** - Perfil, actualización y eliminación de usuarios

Ejemplo de petición para crear una orden (POST /orders):
{
  "userId": 1,
  "orderDate": "2025-05-19T10:30:00",
  "totalAmount": 150.00,
  "status": "PENDING",
  "orderItems": [
    {
      "productId": 2,
      "quantity": 3,
      "price": 50.00
    }
  ]
}

Seguridad
Autenticación basada en JWT

Roles y permisos controlados con Spring Security

Endpoints protegidos según rol (ADMIN o CUSTOMER)

Próximos pasos
- Implementar front end

Autor: Iván Del Llano Blanco


