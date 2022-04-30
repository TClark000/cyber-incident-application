FROM openjdk:18-alpine@sha256:25b910311bfe15547ecab6895d5eb3f4ec718d6d53cced7eec78e4b889962e1f AS restful-ci
ENV NODE_ENV development

RUN apk add --no-cache curl tar bash
ARG MAVEN_VERSION=3.8.5
ARG USER_HOME_DIR="/root"
RUN mkdir -p /usr/share/maven && \
curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC /usr/share/maven --strip-components=1 && \
ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
ENTRYPOINT ["/usr/bin/mvn"]

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY pom.xml /usr/src/app
RUN mvn -T 1C install && rm -rf target

COPY src /usr/src/app/src