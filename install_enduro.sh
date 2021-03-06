__enduro-install-maven() {
    current_dir=$(pwd)
    mkdir $HOME/apps
    cd $HOME/apps
    echo "[ENDURO] Downloading maven"
    curl -O http://apache.mirrors.spacedump.net/maven/maven-3/3.1.1/source/apache-maven-3.1.1-src.zip
    echo "[ENDURO] Unpacking maven"
    unzip apache-maven-3.1.1-src.zip
    echo "[ENDURO] Installing maven"
    cd $HOME/apps/apache-maven-3.1.1 && echo "Successfully cd to directory. Running 'ant'"
    echo "[ENDURO] Exporting M2_HOME variable"
    export M2_HOME=$HOME/apps/maven/apache-maven-3.0-SNAPSHOT/
    ant || echo "[ENDURO] Something went wrong."
    echo "[ENDURO] Installed maven. Command executed with: ~/apps/maven/apache-maven-3.0-SNAPSHOT/bin/mvn"
    cd $current_dir
}

__enduro-install-source() {
    current_dir=$(pwd)
    echo "[ENDURO] Downloading enduro"
    echo $1
    echo " $1 "
    cd $target_dir && git clone git@github.com:buren/enduro.git && echo "[ENDURO] Downloaded enduro"
    echo "[ENDURO] Installing enduro"
    mkdir $target_dir/enduro
    cd $target_dir/enduro
    $HOME/apps/maven/apache-maven-3.0-SNAPSHOT/bin/mvn clean install
    echo "[ENDURO] Installed. Testing done."
    echo "[ENDURO] Check status."
    cd $current_dir
}

target_dir=$HOME/eda260_pvg/
mkdir -p $target_dir
echo "Created directory: $target_dir"

__enduro-install-maven $target_dir | tee -a $target_dir/_maven_install.log && \
__enduro-install-source "$1" | tee -a $target_dir/_enduro_install.log && \
echo "Installing git-story" && curl https://raw2.github.com/buren/git-story/master/setup/install.sh | bash
