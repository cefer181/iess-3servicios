# ============================
# Frontend Next.js (Node 20)
# Dockerfile tolerante a que aún no exista el proyecto
# ============================

# Etapa deps: instala dependencias si hay package.json
FROM node:20-alpine AS deps
WORKDIR /app
COPY package*.json ./ 2>/dev/null || true
RUN [ -f package.json ] && (npm ci || npm i) || true

# Etapa build: compila Next si existe
FROM node:20-alpine AS builder
WORKDIR /app
COPY --from=deps /app/node_modules ./node_modules 2>/dev/null || true
COPY . .
ARG NEXT_PUBLIC_API_BASE=http://localhost:8080
ENV NEXT_PUBLIC_API_BASE=$NEXT_PUBLIC_API_BASE
RUN [ -f package.json ] && npm run build || echo "Pendiente crear proyecto Next.js"

# Etapa run: sirve la app si existe
FROM node:20-alpine AS runner
WORKDIR /app
ENV NODE_ENV=production
COPY --from=builder /app/.next ./.next 2>/dev/null || true
COPY --from=builder /app/public ./public 2>/dev/null || true
COPY package*.json ./ 2>/dev/null || true
RUN [ -f package.json ] && npm ci --omit=dev || true
EXPOSE 3000
CMD [ "sh", "-c", "if [ -f package.json ]; then npm start; else echo 'Front aún no está creado. Agrega Next.js y reconstruye.' && tail -f /dev/null; fi" ]