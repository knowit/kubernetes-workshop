
# Kubernetes tools
. <(kubectl completion bash)
. <(helm completion bash)

source ###LINUX_DIR###/app/kube-ps1.sh
PS1="\[\033[0;31m\]\w\[\033[0;33m\]\$(__git_ps1)\[\e[0m\] \$(kube_ps1)$ "
