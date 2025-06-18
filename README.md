# 🛒 E-commerce Backend

Este proyecto es una solución de backend para un sistema de e-commerce, basado en microservicios desarrollados con **Spring Boot**, **Kafka**, **JWT** para autenticación, y **MySQL** como base de datos. Incluye dos microservicios principales:

- **Order Service:** gestiona pedidos y envía eventos a Kafka.
- **Payment Service:** consume eventos desde Kafka y procesa pagos.

El proyecto está preparado para ejecutarse localmente utilizando `Docker` y `docker-compose` para levantar servicios como MySQL y Kafka.

---
