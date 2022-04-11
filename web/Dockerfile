FROM node:16-alpine as builder

WORKDIR /app
COPY . .
RUN npm install --silent && npm run ng build

FROM nginx:alpine

VOLUME /var/cache/nginx
COPY --from=builder app/dist/desafiodev-frontend /usr/share/nginx/html
COPY ./nginx/nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
