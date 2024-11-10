FROM jenkins/jenkins:lts

# Switch to root user to install dependencies
USER root

# Install kubectl based on system architecture
RUN ARCH=$(uname -m) && \
    if [ "$ARCH" = "x86_64" ]; then \
        curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"; \
    elif [ "$ARCH" = "aarch64" ]; then \
        curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/arm64/kubectl"; \
    else \
        echo "Unsupported architecture: $ARCH" && exit 1; \
    fi && \
    chmod +x kubectl && \
    mv kubectl /usr/local/bin/

# Switch back to the Jenkins user
USER jenkins
