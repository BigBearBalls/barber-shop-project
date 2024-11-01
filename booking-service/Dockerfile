FROM openjdk:17-oracle as extractor
WORKDIR /booking-service
COPY build/libs/app-0.0.1-SNAPSHOT.jar /booking-service/
RUN java -Djarmode=layertools -jar app-0.0.1-SNAPSHOT.jar extract

FROM  openjdk:17-oracle
WORKDIR /app
COPY --from=extractor /booking-service/dependencies/. /app/.
COPY --from=extractor /booking-service/spring-boot-loader/. /app/.
COPY --from=extractor /booking-service/snapshot-dependencies/. /app/.
COPY --from=extractor /booking-service/application/. /app/.
CMD ["java", "org.springframework.boot.loader.launch.JarLauncher"]