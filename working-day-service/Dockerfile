FROM barber-shop-project as barber-shop

FROM openjdk:17-oracle as extractor
WORKDIR /working-day-service
COPY  --from=barber-shop /root-app/notification-service/build/libs/app-0.0.1-SNAPSHOT.jar /working-day-service/
RUN java -Djarmode=layertools -jar app-0.0.1-SNAPSHOT.jar extract

FROM  openjdk:17-oracle
WORKDIR /app
COPY --from=extractor /working-day-service/dependencies/. /app/.
COPY --from=extractor /working-day-service/spring-boot-loader/. /app/.
COPY --from=extractor /working-day-service/snapshot-dependencies/. /app/.
COPY --from=extractor /working-day-service/application/. /app/.
CMD ["java", "org.springframework.boot.loader.launch.JarLauncher"]