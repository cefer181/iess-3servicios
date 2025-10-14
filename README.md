# IESS — Banca (Backend + Frontend + Docker)

Proyecto de demo con:
- **Backend**: Quarkus (Java 21) — APIs REST para clientes, cuentas, movimientos y reportes.
- **Frontend**: Next.js — UI para login (básico), clientes, cuentas, movimientos y reportes.
- **DB**: PostgreSQL
- **Orquestación**: Docker Compose

## Estructura

## Requisitos
- Docker y Docker Compose
- (Opcional para desarrollo local)
  - Java 21 (Temurin u OpenJDK)
  - Maven 3.9+
  - Node.js 20+

## Variables de entorno (por servicio)

### DB
- `POSTGRES_DB=bankdb`
- `POSTGRES_USER=bankuser`
- `POSTGRES_PASSWORD=bankpass`

### Backend (Quarkus)
Definidas en `backend/src/main/resources/application.properties` (ya configurado).
Asegúrate de tener CORS abierto para el front:

