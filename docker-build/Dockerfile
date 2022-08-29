FROM  dbscale/base-centos7.8-java:1-amd64
LABEL maintainer="WEB-server Docker Maintainer @bsgchina.com" \
      name="WEB-manager" \
      summary="Tomcat:8.0.53 image"

ENV CONSUL_PORT=8500

ADD apache-tomcat-7.0.94.tar.gz /usr/local
COPY spdb /etc/spdb
RUN ln -sf /usr/local/apache-tomcat-7.0.94 /usr/local/tomcat && \
    mkdir -p /usr/local/tomcat/report && \
    groupadd tomcat && \
    useradd -s /bin/bash -g tomcat tomcat && \
    chown -Rf tomcat.tomcat /usr/local/tomcat && \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai    /etc/localtime

COPY server.xml /usr/local/tomcat/conf/server.xml

ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH
RUN mkdir -p "$CATALINA_HOME"
WORKDIR $CATALINA_HOME

COPY war/spdb.war /usr/local/tomcat/webapps/spdb.war
COPY start.sh /WEB-MANAGER/start.sh
COPY stop.sh /WEB-MANAGER/stop.sh

WORKDIR /WEB-MANAGER
CMD ["/bin/bash"]
