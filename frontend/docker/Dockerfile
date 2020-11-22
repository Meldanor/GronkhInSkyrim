FROM node:15-alpine as build

WORKDIR /app
COPY package.json yarn.lock ./
RUN yarn install
COPY . .
RUN yarn build

FROM nginx:1.19

COPY --from=build /app/dist /usr/share/nginx/html
COPY docker/nginx.default.conf /etc/nginx/conf.d/default.conf
