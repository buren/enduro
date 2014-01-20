#!/bin/bash

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
    cd $target_dir && git clone git@github.com:buren/enduro.git && echo "[ENDURO] Downloaded enduro"
    echo "[ENDURO] Installing enduro"
    cd enduro
    $HOME/apps/maven/apache-maven-3.0-SNAPSHOT/bin/mvn install
    echo "[ENDURO] Running enduro tests"
    $HOME/apps/maven/apache-maven-3.0-SNAPSHOT/bin/mvn test
    echo "[ENDURO] Testing done."
    echo "[ENDURO] Check status."
    cd $current_dir
}

target_dir=$HOME/eda260_pvg/
mkdir -p $target_dir
echo "Created directory: $target_dir"

__enduro-install-maven | tee -a $target_dir/_maven_install.log
__enduro-install-source | tee -a $target_dir/_enduro_install.log
