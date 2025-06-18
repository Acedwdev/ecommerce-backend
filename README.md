# Ecommerce Backend - Microservicios con Spring Boot, Kafka y JWT

Este repositorio contiene un sistema de ecommerce dividido en dos microservicios construidos con Spring Boot:

* `orderservice`: Servicio de pedidos
* `paymentservice`: Servicio de pagos

Incluye comunicación asíncrona mediante **Apache Kafka**, seguridad basada en **JWT**, y documentación de la API con **Swagger**.

---

## Tabla de contenido

1. [Tecnologías utilizadas](#tecnologías-utilizadas)
2. [Arquitectura general](#arquitectura-general)
3. [Estructura del proyecto](#estructura-del-proyecto)
4. [Instalación local](#instalación-local)
5. [Descripción de microservicios](#descripción-de-microservicios)
6. [Consideraciones técnicas](#consideraciones-técnicas)

---

## 🛠️ Tecnologías utilizadas

* Java 17
* Spring Boot 3
* Spring Security + JWT
* Kafka + Zookeeper
* MySQL
* Docker + Docker Compose
* JUnit 5 + Mockito
* Swagger/OpenAPI 3 (`springdoc-openapi`)

---

## 🧱 Arquitectura general

Este proyecto sigue una **arquitectura de microservicios** con enfoque **hexagonal (Ports and Adapters)** de manera simplificada. Cada servicio tiene sus capas bien separadas:

* `Controller` (entrada HTTP)
* `Service` (lógica de negocio)
* `Repository` (acceso a datos)
* `DTOs` y `Event Models` para comunicación

Además, se utiliza **Apache Kafka** como middleware para la comunicación asíncrona entre microservicios (por eventos).

---

## 📁 Estructura del proyecto

```
📦 ecommerce-backend/
├── orderservice/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   ├── dto/
│   ├── config/ (Security + JWT)
│   └── events/
├── paymentservice/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   └── events/
├── docker-compose.yml
└── README.md
```

---

## 🧪 Instalación local

### 1. Clonar el proyecto

```bash
git clone https://github.com/Acedwdev/ecommerce-backend.git
cd ecommerce-backend
```

### 2. Levantar servicios externos con Docker

```bash
docker-compose up -d
```

Esto iniciará:

* MySQL (puerto 3307)
* Zookeeper (puerto 2181)
* Kafka (puerto 9092)

### 3. Ejecutar cada microservicio

Desde dos terminales distintas:

```bash
cd orderservice
./mvnw spring-boot:run
```

```bash
cd paymentservice
./mvnw spring-boot:run
```

---

## 🛢️ Conexión a la base de datos

Una vez que Docker esté corriendo, puedes conectarte al contenedor de MySQL para hacer consultas o inserts:

```bash
docker exec -it mysql_orderservice mysql -uorders_user -porders_password orders_db
```

### Insertar cliente para pruebas

Para poder usar los endpoints de creación de pedidos, es necesario tener clientes registrados. Puedes usar este comando en la base de datos:

```sql
INSERT INTO customers (id, name, email)
VALUES ('abc123', 'Juan Perez', 'juan@mail.com');
```

---

## 🧩 Descripción de microservicios

### 🛒 `orderservice`

* Crea, consulta, actualiza y cancela pedidos.
* Publica eventos `OrderToPaymentEvent` en Kafka tras la creación de un pedido.
* Protegido con JWT y documentado con Swagger.

### 💳 `paymentservice`

* Escucha eventos `OrderToPaymentEvent` desde Kafka.
* Registra los pagos asociados en su propia base de datos.

---

## 📘 Consideraciones técnicas

### 🔐 Seguridad con JWT

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

### 📖 Documentación Swagger

Disponible para `orderservice` en:

* Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Configuración incluida en `application.properties` y `SecurityConfig` para permitir acceso sin autenticación.

---

### 🧪 Pruebas

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

## 🎯 Principios y patrones utilizados

### ✅ Arquitectura

* Microservicios
* Comunicación asíncrona vía Kafka

### ✅ Patrones de diseño

* **DTO**: comunicación entre capas
* **Service Layer**: lógica central del negocio
* **Event-Driven**: integración entre microservicios con Kafka
* **Factory pattern** (en tests al construir objetos)

### ✅ Principios SOLID aplicados

* **SRP**: cada clase tiene una única responsabilidad (ej. `JwtRequestFilter`, `OrderServiceImpl`, `OrderController`).
* **OCP**: código preparado para extensión sin modificar.
* **DIP**: uso de interfaces e inyección de dependencias (`@Autowired`, `@MockBean`).
* **LSP**: implementaciones pueden sustituir a interfaces sin romper comportamiento.
* **ISP**: interfaces pequeñas como `OrderService`.

---

## 📝 Autor

Proyecto desarrollado como ejercicio de arquitectura de microservicios con Spring Boot.

GitHub: [Acedwdev](https://github.com/Acedwdev)






