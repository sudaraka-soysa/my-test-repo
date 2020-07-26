FROM java8base
ADD ARTIFACT_ID.jar /root
ADD my-test-repo/src/main/resources/application.properties /root
#ADD bootstrap.properties /root
#ADD log4j2.xml /root
RUN cd ../ && \
    cd /root
CMD ["/bin/sh", "-l", "-c", "cd /root && java -jar /root/ARTIFACT_ID.jar --spring.config.location=/root/application.properties"]
