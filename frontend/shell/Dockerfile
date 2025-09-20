# Step 1: Build Angular app
FROM node:20-alpine as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build --prod

# Step 2: Serve with Nginx
FROM nginx:alpine
# COPY --from=build ./app/dist/shell /usr/share/nginx/html
EXPOSE 4200
CMD ["nginx", "-g", "daemon off;"]
