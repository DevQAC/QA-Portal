FROM node:10 as build
RUN NG_CLI_ANALYTICS=ci npm install -g @angular/cli
WORKDIR /build
COPY . .
RUN NG_CLI_ANALYTICS=ci npm install
RUN ng build --prod --configuration=production
FROM nginx:alpine
COPY --from=build /build/dist/portal-core /opt/portal-core
COPY nginx.conf /etc/nginx/nginx.conf
