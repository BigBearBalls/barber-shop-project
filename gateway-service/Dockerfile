FROM barber-shop-project as barber-shop

FROM openjdk:17-oracle as extractor
WORKDIR /gateway-service
COPY --from=barber-shop /root-app/gateway-service/build/libs/app-0.0.1-SNAPSHOT.jar /gateway-service/
RUN java -Djarmode=layertools -jar app-0.0.1-SNAPSHOT.jar extract

FROM  openjdk:17-oracle
WORKDIR /app
COPY --from=extractor /gateway-service/dependencies/. /app/.
COPY --from=extractor /gateway-service/spring-boot-loader/. /app/.
COPY --from=extractor /gateway-service/snapshot-dependencies/. /app/.
COPY --from=extractor /gateway-service/application/. /app/.
CMD ["java", "org.springframework.boot.loader.launch.JarLauncher"]