FROM gradle:8.8-jdk17 as builder
WORKDIR /root-app
COPY . .
RUN gradle build --no-daemon