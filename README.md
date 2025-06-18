# Ecommerce Backend - Microservicios con Spring Boot, Kafka y JWT

Este repositorio contiene un sistema de ecommerce dividido en dos microservicios construidos con Spring Boot:

* `orderservice`: Servicio de pedidos
* `paymentservice`: Servicio de pagos

Incluye comunicación asíncrona mediante **Apache Kafka**, seguridad basada en **JWT**, y documentación de la API con **Swagger**.

---

## ✅ Tabla de contenido

1. [Tecnologías](#tecnologías)
2. [Estructura del proyecto](#estructura-del-proyecto)
3. [Instalación local](#instalación-local)
4. [Descripción de los microservicios](#descripción-de-los-microservicios)
5. [Seguridad con JWT](#seguridad-con-jwt)
6. [Documentación Swagger](#documentación-swagger)
7. [Pruebas](#pruebas)

---

## 🛠 Tecnologías

* Java 17
* Spring Boot 3
* Spring Web, Spring Data JPA, Spring Security
* Apache Kafka
* JWT (JSON Web Token)
* MySQL
* Docker & Docker Compose
* Swagger / OpenAPI
* JUnit 5 & Mockito

---

## 📁 Estructura del proyecto

```
├── docker-compose.yml
├── orderservice
│   ├── src
│   ├── pom.xml
│   └── application.properties
├── paymentservice
│   ├── src
│   ├── pom.xml
│   └── application.properties
```

* Cada microservicio es un proyecto Spring Boot independiente.
* Kafka, MySQL y Zookeeper se levantan con Docker.

---

## 💻 Instalación local

### 1. Clona el repositorio

```bash
git clone https://github.com/Acedwdev/ecommerce-backend.git
cd ecommerce-backend
```

### 2. Levanta los servicios con Docker

```bash
docker-compose up -d
```

Esto levantará:

* MySQL en puerto 3307
* Zookeeper en 2181
* Kafka en 9092

### 3. Compila y ejecuta los microservicios

En terminales separadas:

```bash
cd orderservice
./mvnw spring-boot:run
```

```bash
cd paymentservice
./mvnw spring-boot:run
```

---

## 🧾 Descripción de los microservicios

### 🔹 `orderservice`

* CRUD de pedidos
* Envío de eventos `OrderToPaymentEvent` a Kafka tras crear un pedido
* Seguridad JWT
* Documentación Swagger

### 🔹 `paymentservice`

* Escucha eventos de pedidos desde Kafka
* Procesa y almacena pagos
* Utiliza otra base de datos separada (`payment_db`)

---

## 🔐 Seguridad con JWT

* Se utiliza Spring Security + JWT para proteger los endpoints
* Endpoint público: `/api/v1/auth/login`
* Rutas protegidas requieren enviar un token válido en el header:

```
Authorization: Bearer <token>
```

* Clases clave:

  * `SecurityConfig.java`
  * `JwtRequestFilter.java`
  * `JwtUtil.java`
  * `MyUserDetailsService.java`

---

## 📖 Documentación Swagger

Disponible para `orderservice` en:

* Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Configuración incluida en `application.properties` y `SecurityConfig` para permitir acceso sin autenticación.

---

## 🧪 Pruebas

Se incluyen pruebas para `orderservice`:

### ✅ Unitarias (JUnit + Mockito)

* Prueban lógica de negocio como `createOrder`, `cancelOrder`.

### ✅ Integración (Spring Boot Test + MockMvc)

* Prueban endpoints reales simulando usuarios autenticados con `@WithMockUser`

### ✅ Configuración para tests

* Perfil `test` evita filtros JWT reales
* Configuración `SecurityMockConfig` activa durante pruebas

Ejecutar pruebas:

```bash
./mvnw test
```

---

## 📝 Autor

Proyecto desarrollado como ejercicio de arquitectura de microservicios con Spring Boot.

GitHub: [Acedwdev](https://github.com/Acedwdev)

---




