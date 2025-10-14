# 🏦 IESS - Sistema de 3 Servicios (Demo FullStack)

Proyecto desarrollado con **Quarkus + Next.js + PostgreSQL**, listo para ejecutarse con **Docker Compose**.

---

## 🚀 Requisitos

- Docker y Docker Compose instalados  
- Git instalado

---

## ⚙️ Instalación rápida

```bash
# Clonar el repositorio
git clone git@github.com:cefer181/iess-3servicios.git
cd iess-3servicios

# Levantar toda la app
docker compose up --build -d
.
├── backend/                → API Quarkus (Puerto 8080)
│   ├── src/main/java/ec/iess/
│   ├── pom.xml
│   └── Dockerfile.jvm
│
├── frontend/               → Web Next.js (Puerto 3000)
│   ├── app/
│   ├── package.json
│   └── Dockerfile
│
├── docker-compose.yml      → Orquestación de contenedores
└── BaseDatos.sql           → Script inicial PostgreSQL
Servicios
Servicio	Puerto	Descripción
Frontend (Next.js)	3000	Interfaz web
Backend (Quarkus API)	8080	API REST
Base de datos (PostgreSQL)	5432	Persistencia

Endpoints principales
Método	Endpoint	Descripción
GET	/api/clientes	Lista de clientes
POST	/api/movimientos	Registra un movimiento
GET	/api/reportes?clienteId=1&fechaInicio=2025-01-01&fechaFin=2025-10-14	Estado de cuenta

Notas

Créditos suman saldo.

Débitos restan saldo.

Si el saldo no es suficiente → "Saldo no disponible".

Reportes en JSON según rango de fechas.
