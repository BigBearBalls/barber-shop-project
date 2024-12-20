FROM openjdk:17-oracle as extractor
WORKDIR /department-service
COPY /build/libs/app-0.0.1-SNAPSHOT.jar /department-service/
RUN java -Djarmode=layertools -jar app-0.0.1-SNAPSHOT.jar extract

FROM  openjdk:17-oracle
WORKDIR /app
COPY --from=extractor /department-service/dependencies/. /app/.
COPY --from=extractor /department-service/spring-boot-loader/. /app/.
COPY --from=extractor /department-service/snapshot-dependencies/. /app/.
COPY --from=extractor /department-service/application/. /app/.
CMD ["java", "org.springframework.boot.loader.launch.JarLauncher"]