FROM openjdk:17-oracle as extractor
WORKDIR /meeting-room-service
COPY /build/libs/app-0.0.1-SNAPSHOT.jar /meeting-room-service/
RUN java -Djarmode=layertools -jar app-0.0.1-SNAPSHOT.jar extract

FROM  openjdk:17-oracle
WORKDIR /app
COPY --from=extractor /meeting-room-service/dependencies/. /app/.
COPY --from=extractor /meeting-room-service/spring-boot-loader/. /app/.
COPY --from=extractor /meeting-room-service/snapshot-dependencies/. /app/.
COPY --from=extractor /meeting-room-service/application/. /app/.
CMD ["java", "org.springframework.boot.loader.launch.JarLauncher"]