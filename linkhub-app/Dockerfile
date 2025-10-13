FROM node:22-alpine AS builder 

WORKDIR /app

COPY package.json ./
RUN npm update && npm install 

COPY . .
RUN npm run build

FROM node:22-alpine AS runner 

WORKDIR /app

COPY --from=builder /app/.next/standalone ./
COPY --from=builder /app/.next/static ./.next/static
COPY --from=builder /app/public ./public

ENV NODE_ENV=production

EXPOSE 3000

LABEL author="Leonardo Barreto"
LABEL version="1.0.0"

ENTRYPOINT [ "node", "server.js" ]