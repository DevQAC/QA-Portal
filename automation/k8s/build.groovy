class Pod {
    String bin = "~/.local/bin"
    String kubectl = "${bin}/kubectl"
    String name
    def sh
   
    void setName(name) {
        this.name = name
    }

    @NonCPS
    void sh(sh) {
        this.sh = sh
    }

    @NonCPS
    void exec(command) {
        sh "${kubectl} exec ${name} -- ${command}"    
    }

    @NonCPS
    void create() {
        sh "${kubectl} run --image=alpine:latest --restart=Never ${name} -- sh -c 'while true; do sleep 5; done'"
    }

    @NonCPS
    void delete() {
        sh "${kubectl} delete pod ${name}"
    }
}

node {
    String dockerCompose = "~/.local/bin/docker-compose"
    String docker = "~/.local/bin/docker"
    Pod pod
    stage("create build pod") {
        pod = new Pod()
        pod.setName("${PROJECT_NAME}-${env.BUILD_NUMBER}")
        pod.create()
        sleep 5
        pod.exec("hostname")
        pod.delete()
    }
}
