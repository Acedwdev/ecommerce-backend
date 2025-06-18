# 🛒 E-commerce Backend

Este proyecto es una solución de backend para un sistema de e-commerce, basado en microservicios desarrollados con **Spring Boot**, **Kafka**, **JWT** para autenticación, y **MySQL** como base de datos. Incluye dos microservicios principales:

- **Order Service:** gestiona pedidos y envía eventos a Kafka.
- **Payment Service:** consume eventos desde Kafka y procesa pagos.

El proyecto está preparado para ejecutarse localmente utilizando `Docker` y `docker-compose` para levantar servicios como MySQL y Kafka.

---

## 🚀 Características

- ✅ Arquitectura basada en microservicios.
- 🛒 **Order Service**
  - Crear y consultar pedidos.
  - Cancelar pedidos.
  - Enviar eventos `OrderToPaymentEvent` a Kafka.
  - Seguridad con JWT.
  - Documentación automática con Swagger/OpenAPI.
- 💳 **Payment Service**
  - Escucha eventos desde Kafka.
  - Procesa y registra pagos en base de datos.

### 🔧 Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Security + JWT
- Apache Kafka (con Zookeeper)
- Spring Kafka
- MySQL 8
- Spring Data JPA
- Springdoc OpenAPI 2.x (Swagger UI)
- Docker + Docker Compose
- JUnit 5 + Mockito (pruebas unitarias e integración)

## 📁 Estructura del Proyecto

El repositorio contiene dos microservicios independientes dentro de una misma raíz:

ecommerce-backend/
│
├── orderservice/ # Microservicio para gestión de pedidos
│ ├── src/ # Código fuente Java
│ ├── pom.xml # Dependencias y configuración Maven
│ └── application.properties # Configuración (MySQL, Kafka, JWT, Swagger)
│
├── paymentservice/ # Microservicio para procesamiento de pagos
│ ├── src/ # Código fuente Java
│ ├── pom.xml # Dependencias y configuración Maven
│ └── application.properties # Configuración (MySQL, Kafka)
│
├── docker-compose.yml # Levanta MySQL, Kafka y Zookeeper
└── README.md


## ⚙️ Requisitos Previos

Antes de comenzar, asegúrate de tener instalado lo siguiente en tu máquina:

- [Java 17+](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/products/docker-desktop)
- [Git](https://git-scm.com/)

## 🚀 Instalación Local

Sigue estos pasos para ejecutar el proyecto en tu entorno local:

### 1. Clonar el repositorio

```bash
git clone https://github.com/Acedwdev/ecommerce-backend.git
cd ecommerce-backend




