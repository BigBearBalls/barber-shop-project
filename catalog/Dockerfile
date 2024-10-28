FROM openjdk:17-oracle as extractor
WORKDIR /catalog
COPY build/libs/app-0.0.1-SNAPSHOT.jar /catalog/
RUN java -Djarmode=layertools -jar app-0.0.1-SNAPSHOT.jar extract

FROM  openjdk:17-oracle
WORKDIR /app
COPY --from=extractor /catalog/dependencies/. /app/.
COPY --from=extractor /catalog/spring-boot-loader/. /app/.
COPY --from=extractor /catalog/snapshot-dependencies/. /app/.
COPY --from=extractor /catalog/application/. /app/.
CMD ["java", "org.springframework.boot.loader.launch.JarLauncher"]