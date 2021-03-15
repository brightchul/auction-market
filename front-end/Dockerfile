FROM node:alpine
WORKDIR '/app'
COPY ./package.json ./
RUN npm update --force
RUN npm install 
COPY . .
CMD ["npm", "run", "start"]