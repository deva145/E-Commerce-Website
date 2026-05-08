FROM fluent/fluentd:v1.16-debian

USER root

# Install elasticsearch 8.x compatible client version first, then the plugin
RUN gem install elasticsearch:8.13.0 && \
    gem install fluent-plugin-elasticsearch:5.4.3

USER fluent