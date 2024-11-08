FROM barber-shop-project as barber-shop

FROM openjdk:17-oracle as extractor
WORKDIR /notification-service
COPY  --from=barber-shop /root-app/notification-service/build/libs/app-0.0.1-SNAPSHOT.jar /notification-service/
RUN java -Djarmode=layertools -jar app-0.0.1-SNAPSHOT.jar extract

FROM  openjdk:17-oracle
WORKDIR /app
COPY --from=extractor /notification-service/dependencies/. /app/.
COPY --from=extractor /notification-service/spring-boot-loader/. /app/.
COPY --from=extractor /notification-service/snapshot-dependencies/. /app/.
COPY --from=extractor /notification-service/application/. /app/.
CMD ["java", "org.springframework.boot.loader.launch.JarLauncher"]