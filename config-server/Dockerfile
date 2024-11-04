FROM openjdk:17-oracle as extractor
WORKDIR /config-service
COPY build/libs/app-0.0.1-SNAPSHOT.jar /config-service/
RUN java -Djarmode=layertools -jar app-0.0.1-SNAPSHOT.jar extract

FROM  openjdk:17-oracle
WORKDIR /app
COPY --from=extractor /config-service/dependencies/. /app/.
COPY --from=extractor /config-service/spring-boot-loader/. /app/.
COPY --from=extractor /config-service/snapshot-dependencies/. /app/.
COPY --from=extractor /config-service/application/. /app/.
CMD ["java", "org.springframework.boot.loader.launch.JarLauncher"]