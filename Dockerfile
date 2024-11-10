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

# Install ArgoCD CLI
RUN if [ "$ARCH" = "x86_64" ]; then \
        curl -sSL -o argocd "https://github.com/argoproj/argo-cd/releases/latest/download/argocd-linux-amd64"; \
    elif [ "$ARCH" = "aarch64" ]; then \
        curl -sSL -o argocd "https://github.com/argoproj/argo-cd/releases/latest/download/argocd-linux-arm64"; \
    else \
        echo "Unsupported architecture for ArgoCD CLI: $ARCH" && exit 1; \
    fi && \
    chmod +x argocd && \
    mv argocd /usr/local/bin/

# Install yq
RUN if [ "$ARCH" = "x86_64" ]; then \
        curl -sSL -o /usr/local/bin/yq "https://github.com/mikefarah/yq/releases/latest/download/yq_linux_amd64"; \
    elif [ "$ARCH" = "aarch64" ]; then \
        curl -sSL -o /usr/local/bin/yq "https://github.com/mikefarah/yq/releases/latest/download/yq_linux_arm64"; \
    else \
        echo "Unsupported architecture for yq: $ARCH" && exit 1; \
    fi && \
    chmod +x /usr/local/bin/yq

# Switch back to the Jenkins user
USER jenkins
