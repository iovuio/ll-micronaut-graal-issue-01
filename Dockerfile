FROM oracle/graalvm-ce:20.0.0-java8 as graalvm
# For JDK 11
#FROM oracle/graalvm-ce:20.0.0-java11 as graalvm
RUN gu install native-image

COPY . /home/app/hello-world
WORKDIR /home/app/hello-world

RUN native-image --no-server -cp target/hello-world-*.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/hello-world/hello-world /app/hello-world
ENTRYPOINT ["/app/hello-world"]
