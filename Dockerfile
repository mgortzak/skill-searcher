FROM hirokimatsumoto/alpine-openjdk-11

WORKDIR /

COPY /build/libs/skill-searcher-fat-jar.jar /skill-searcher.jar

EXPOSE 4567

CMD ["java", "-jar", "skill-searcher.jar"]
