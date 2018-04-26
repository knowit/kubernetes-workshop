: "${LINUX_DIR:?Needs to be non-empty.}"

# Show kube context and namespace in PS1. Use kubeon and kubeoff to retry.
sed s%###LINUX_DIR###%$LINUX_DIR% kubetools_bashrc_template.sh >> ~/.bashrc

# Auto completion for path/kube{ctx,ns}
sudo cp kubectx_completion/* /etc/bash_completion.d
sudo chmod u=rw,g=r,o=r /etc/bash_completion.d/kubectx.bash
sudo chmod u=rw,g=r,o=r /etc/bash_completion.d/kubens.bash
